package com.dxs.distribute.lock.service;

import com.dxs.distribute.lock.model.request.AddOrUpdateProjectInsuranceReq;
import com.dxs.distribute.lock.utils.SysResult;

/**
  * 锁示例service
  * @className  LockExampleService
  * @author  Mr.Xiong
  * @date  2022/08/29
  */
public interface LockExampleService {

    SysResult<Object> add(AddOrUpdateProjectInsuranceReq addOrUpdateProjectInsuranceReq);
}
