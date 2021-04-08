package com.spring.pagehelper.mapper;

import com.spring.pagehelper.entity.Coffee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CoffeeMapper {
    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWitchRowBounds(RowBounds rowBounds);

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
