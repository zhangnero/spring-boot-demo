package com.nero.spring.boot.demo.api;

import com.nero.spring.boot.demo.entities.Table1;
import com.nero.spring.boot.demo.service.Table1Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nero
 * @since 2019-07-17 16:42
 */
@RestController
@RequestMapping(value = "table1Api")
public class Table1Api {

    @Autowired
    private Table1Service table1Service;

    @ApiOperation(value = "向Table1添加数据", httpMethod = "GET", notes = "向Table1添加数据")
    @RequestMapping(value = "addTable1", method = RequestMethod.GET)
    public int addTable1(@RequestParam String name){
        return table1Service.addTable1(name);
    }

    @ApiOperation(value = "查询table1", httpMethod = "GET", notes = "查询table1")
    @RequestMapping(value = "queryTable1", method = RequestMethod.GET)
    public List<Table1> queryTable1(@RequestParam(required = false) String query,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize){
        return table1Service.queryTable1(query, pageNumber, pageSize);
    }
}
