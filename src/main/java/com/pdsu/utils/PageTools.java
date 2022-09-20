package com.pdsu.utils;

import com.pdsu.bean.User;

import java.util.List;

public class PageTools {
    //基础参数
    private int currentPage;//当前页
    private int pageSize;//每页显示的条数
    private int totalSize;//总条数

    //都是通过上面三个参数计算过来的
    private int index;//索引
    private int prePage;//上一页
    private int totalPage;//总页数
    private int nextPage;//下一页

    private List<User> userList;


    //提供一个三个参数的构造方法
    public PageTools(int currentPage, int pageSize, int totalSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;

        //在赋值
        index = pageSize * (currentPage - 1);
        prePage = (currentPage == 1 ? 1 : currentPage - 1);
        totalPage = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);
        nextPage = (currentPage == totalPage ? currentPage : currentPage + 1);
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPrePage() {
        return prePage;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public String toString() {
        return "PageTools{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", index=" + index +
                ", prePage=" + prePage +
                ", totalPage=" + totalPage +
                ", nextPage=" + nextPage +
                '}';
    }
}
