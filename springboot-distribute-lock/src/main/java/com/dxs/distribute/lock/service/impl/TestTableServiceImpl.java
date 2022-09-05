package com.dxs.distribute.lock.service.impl;

import com.dxs.common.utils.SysResult;
import com.dxs.distribute.lock.constants.SystemErrorMsgConstant;
import com.dxs.distribute.lock.mapper.TestTableMapper;
import com.dxs.distribute.lock.service.TestTableService;
import com.dxs.distribute.lock.utils.DateUtil;
import com.dxs.model.entity.TestTable;
import com.dxs.model.request.AddTestTableReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
  * 测试表service实现
  * @className  TestTableServiceImpl
  * @author  Mr.Xiong
  * @date  2022/08/29
  */
@Service
public class TestTableServiceImpl implements TestTableService {
    @Autowired
    private TestTableMapper testTableMapper;

    @Transactional
    @Override
    public SysResult<Object> add(AddTestTableReq addTestTableReq) {
        TestTable testTable = new TestTable();
        BeanUtils.copyProperties(addTestTableReq , testTable);
        testTable.setCreateTime(DateUtil.nowDate());
        testTable.setCreateId(0);
        int res = testTableMapper.insertSelective(testTable);
        if (res <= 0) {
            throw new RuntimeException(SystemErrorMsgConstant.SYSTEM_ERROR);
        }
        return SysResult.ok();
    }
}
