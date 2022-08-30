package com.dxs.distribute.lock.controller;

import com.dxs.distribute.lock.aop.annotation.RedisLock;
import com.dxs.distribute.lock.model.request.AddTestTableReq;
import com.dxs.distribute.lock.service.TestTableService;
import com.dxs.distribute.lock.utils.SysResult;
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
}
