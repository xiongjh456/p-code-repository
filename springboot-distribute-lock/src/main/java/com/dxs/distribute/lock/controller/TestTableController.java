package com.dxs.distribute.lock.controller;

import com.dxs.common.utils.SysResult;
import com.dxs.distribute.lock.aop.annotation.RedisLock;
import com.dxs.distribute.lock.aop.annotation.RedisNxLock;
import com.dxs.distribute.lock.service.TestTableService;
import com.dxs.model.request.AddTestTableReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test-table")
@RestController
public class TestTableController {
    @Autowired
    private TestTableService testTableService;

    @RedisLock
    @PostMapping("/add")
    public SysResult<Object> add(@RequestBody AddTestTableReq addTestTableReq) {
        return testTableService.add(addTestTableReq);
    }

    @RedisNxLock
    @PostMapping("/add2")
    public SysResult<Object> add2(@RequestBody AddTestTableReq addTestTableReq) {
        return testTableService.add(addTestTableReq);
    }
}
