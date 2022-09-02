package com.dxs.distribute.lock.mapper;


import com.dxs.model.entity.TestTable;

public interface TestTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestTable record);

    int insertSelective(TestTable record);

    TestTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestTable record);

    int updateByPrimaryKey(TestTable record);
}