package com.doraemon.base.controller.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;

/**
 * Created by zbs on 2017/9/13.
 */
@Data
@ApiModel("分页信息")
public class PageBean {
    @Transient
    @ApiModelProperty("多少页")
    int pageNum = 1;
    @Transient
    @ApiModelProperty("每页多少条")
    int pageSize = 10;
}
