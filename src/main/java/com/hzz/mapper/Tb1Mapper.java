package com.hzz.mapper;

import com.hzz.model.Tb1;
import com.hzz.model.Tb1Example;
import java.util.List;

public interface Tb1Mapper {
    int deleteByExample(Tb1Example example);

    int insert(Tb1 record);

    int insertSelective(Tb1 record);

    List<Tb1> selectByExample(Tb1Example example);

    Tb1 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb1 record);

    int updateByPrimaryKey(Tb1 record);
}