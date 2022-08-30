package com.dxs.distribute.lock.service;

import com.dxs.distribute.lock.model.request.AddTestTableReq;
import com.dxs.distribute.lock.utils.SysResult;

/**
  * 项目保险service
  * @className  ProjectInsuranceService
  * @author  Mr.Xiong
  * @date  2022/08/29
  */
public interface TestTableService {

    /**
     * 添加项目保险
     * @methodName  add
     * @param addTestTableReq
     * @return  com.dxs.distribute.lock.utils.SysResult<java.lang.Object> 
     * @throws  
     * @author  Mr.Xiong
     * @date  2022/08/30
     */
    SysResult<Object> add(AddTestTableReq addTestTableReq);
}
