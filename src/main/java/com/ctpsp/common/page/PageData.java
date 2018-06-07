package com.ctpsp.common.page;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunhaijian on 16/5/9.
 */
public class PageData<T> implements Serializable {

    private int page;
    private int total;
    private int totalCount;
    private int size;
    private List<T> list;

    public PageData(){

    }

    public PageData(Page<T> p){
//        Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=21, pages=3, countSignal=false, orderBy='null', orderByOnly=false, reasonable=true, pageSizeZero=true}
        this.page = p.getPageNum();
        this.total = p.getPages();
        this.totalCount = (int)p.getTotal();
        this.size = p.getPageSize();
        this.list = p.getResult();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page + 1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
