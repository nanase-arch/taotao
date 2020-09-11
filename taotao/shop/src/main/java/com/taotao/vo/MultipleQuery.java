package com.taotao.vo;

import java.io.Serializable;
import java.util.Date;

public class MultipleQuery implements Serializable{
    private Integer page;
    private Integer limit;
    private String title;
    //已经被乘以100的了
    private Long price1;
    private Long price2;
    private Integer status;
    //开始时间
    private Date date1;
    //结束时间
    private Date date2;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice1() {
        return price1;
    }

    public void setPrice1(Long price1) {
        this.price1 = price1;
    }

    public Long getPrice2() {
        return price2;
    }

    public void setPrice2(Long price2) {
        this.price2 = price2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "MultipleQuery{" +
                "page=" + page +
                ", limit=" + limit +
                ", title='" + title + '\'' +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", status=" + status +
                ", date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}
