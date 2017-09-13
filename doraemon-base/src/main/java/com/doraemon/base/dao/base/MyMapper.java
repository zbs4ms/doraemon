package com.doraemon.base.dao.base;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created by zbs on 16/6/28.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MyBaseInsertProvider.class, method = "dynamicSQL")
    int insertReturnId(T record);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MyBaseInsertProvider.class, method = "dynamicSQL")
    int insertSelectiveReturnId(T record);

    @InsertProvider(type = MyBaseInsertProvider.class, method = "dynamicSQL")
    int insertListNotKey(List<T> recordList);
}
