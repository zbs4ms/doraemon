package com.doraemon.base.dao.base;

import javax.persistence.Transient;

/**
 * Created by zbs on 16/7/28.
 */
public class BaseEntity {

    @Transient
    private Integer page = 1;
    @Transient
    private Integer rows = 10;
    @Transient
    private Integer total = 0;


    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getRows() {
        return rows;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
}
