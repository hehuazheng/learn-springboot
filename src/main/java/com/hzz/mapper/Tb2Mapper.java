package com.hzz.mapper;

import com.hzz.model.Tb2;
import com.hzz.model.Tb2Example;
import java.util.List;

public interface Tb2Mapper {
    int deleteByExample(Tb2Example example);

    int insert(Tb2 record);

    int insertSelective(Tb2 record);

    List<Tb2> selectByExample(Tb2Example example);

    Tb2 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb2 record);

    int updateByPrimaryKey(Tb2 record);
}