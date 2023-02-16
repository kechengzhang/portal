package com.zgtech.portal.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zkc
 * @date 2021/06/01
 * 分页
 *
 */
public class PageData {
    /**
     * 当前页数
     *
     */
    private int page;
    /**
     * 每页条数
     */
    private int size;
    /**
     * 总条数
     */
    private long total;
    /**
     * 返回数据
     *
     */
    private List<? extends Object> rows;
    public PageData(PageInfo pageInfo){
        this.page = pageInfo.getPageNum();
        this.size = pageInfo.getPageSize();
        this.rows = pageInfo.getList();
        this.total = pageInfo.getTotal();
    }

    public PageData(int page, int size, long total, List<? extends Object> rows){
        this.page = page;
        this.size = size;
        this.rows = rows;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<? extends Object> getRows() {
        return rows;
    }

    public void setRows(List<? extends Object> rows) {
        this.rows = rows;
    }
}
