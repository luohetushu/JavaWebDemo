package com.mysql.bean;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.util.List;

//分页bean
public class PageBean<T> {
    int pageCode;  //当前页码
    int totalRecords; //总记录数
    int pageRecodes; //当前页记录数
    List<T> pageList;  //当前页记录

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    //获取总页数
    public int getTotalPages(){
        int total = this.totalRecords / this.pageRecodes;
        return this.totalRecords % this.pageRecodes == 0 ? total : total + 1;
    }

    public int getPageRecodes() {
        return pageRecodes;
    }

    public void setPageRecodes(int pageRecodes) {
        this.pageRecodes = pageRecodes;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
