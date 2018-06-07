package com.ctpsp.common.page;

import java.io.Serializable;

/**
 * Created by sunhaijian on 16/5/9.
 */
public class PageModel implements Serializable {
    private int page = 1;
    private int size = 10;
    private int start;
    private int end;
    private int total;
    private int totalCount;


    public PageModel() {

    }

    /**
     * 构造函数.
     *
     * @param start
     * @param size
     */
    public PageModel(int start, int size) {
        this.start = start;
        this.size = size;
        this.end = this.start + this.size;
        this.page = (int) Math.floor((this.start * 1.0d) / this.size) + 1;
    }

    /**
     * @param start
     * @param size
     * @param total
     */
    public PageModel(int start, int size, int total) {
        this(start, size);
        this.total = total;
    }

    /**
     * 设置页数，自动计算数据范围.
     *
     * @param page
     */
    public PageModel(int page) {
        this.page = page;
        page = page > 0 ? page : 1;
        this.start = this.size * (page - 1);
        this.end = this.size * page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        page = page > 0 ? page : 1;
        this.start = this.size * (page - 1);
        this.end = this.size * page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
//        if (this.start != 0) {
//            this.page = (int) Math.floor((this.start * 1.0d) / this.size) + 1;
//        }
    }

    public int getStart() {
        return (page - 1) * size > 0 ? (page - 1) * size : 0;
    }

    public void setStart(int start) {
        this.start = start;
        if (this.size != 0) {
            this.page = (int) Math.floor((this.start * 1.0d) / this.size) + 1;
        }
    }

    public int getEnd() {
        return page * size;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalRecords() {
        return total;
    }

    public void setTotalRecords(int total) {
        this.total = total;
        this.totalCount = (int) Math.floor((this.total * 1.0d) / this.size);
        if (this.total % this.size != 0) {
            this.totalCount++;
        }
    }

    public int getPageCount() {
        return totalCount;
    }

    public void setPageCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
