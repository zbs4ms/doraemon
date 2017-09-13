package com.doraemon.base.dao.target;

import java.lang.annotation.*;

/**
 * Created by zbs on 2017/9/12.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface WriteDataSource {
}
