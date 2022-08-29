package com.dxs.distribute.lock.controller;

import com.dxs.distribute.lock.aop.annotation.RedisLock;
import com.dxs.distribute.lock.model.request.AddOrUpdateProjectInsuranceReq;
import com.dxs.distribute.lock.utils.SysResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lock-example")
@RestController
public class LockExampleController {

    @RedisLock
    @PostMapping("/add")
    public SysResult<Object> add(@RequestBody AddOrUpdateProjectInsuranceReq addOrUpdateProjectInsuranceReq) {

    }
}
