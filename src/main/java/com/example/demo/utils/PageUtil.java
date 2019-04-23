package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;



public class PageUtil implements Serializable {


    String status;

    int totals;

    Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageUtil(int totals, Object data) {
        this.status = "success";
        this.totals = totals;
        this.data = data;
    }

    public PageUtil() {
        this.status = "failed";

    }

}