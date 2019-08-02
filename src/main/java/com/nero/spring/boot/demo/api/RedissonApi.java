package com.nero.spring.boot.demo.api;

import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author Nero
 * @since 2019-07-18 14:24
 */
@RestController
@RequestMapping(value = "redissonApi")
public class RedissonApi {

    private static final Logger logger = LoggerFactory.getLogger(RedissonApi.class);

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation(value = "获取Redisson锁", httpMethod = "GET", notes = "获取Redisson锁")
    @RequestMapping(value = "getLock", method = RequestMethod.GET)
    public String getLock(){
        RLock rLock = redissonClient.getLock("redissonTestLockKey");
        String result = null;
        boolean locked = false;
        try {
            locked = rLock.tryLock(3, 10, TimeUnit.SECONDS);
            if (locked){
                TimeUnit.SECONDS.sleep(5);
                logger.info("success return");
                result = "I got the lock;";
            }else {
                logger.info("fail return");
                result = "I could not got the lock;";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (locked) {
                rLock.unlock();
            }
        }

        return result;
    }

    @Autowired
    private JedisPool jedisPool;

    @ApiOperation(value = "redis测试", httpMethod = "GET", notes = "redis测试")
    @RequestMapping(value = "redisTest", method = RequestMethod.GET)
    public void redisTest(){

    }
}
