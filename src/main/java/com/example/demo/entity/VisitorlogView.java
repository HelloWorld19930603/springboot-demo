package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cc
 * @since 2019-04-23
 */
@TableName("visitorlog_view")
public class VisitorlogView extends Model<VisitorlogView> {

    private static final long serialVersionUID = 1L;

    private String visitorPin;

    private String visitorMobile;

    private String visitorCompany;

    private String name;

    private String pin;

    private String visitorStatu;

    private String inAddress;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private String visitorType;


    public String getVisitorPin() {
        return visitorPin;
    }

    public void setVisitorPin(String visitorPin) {
        this.visitorPin = visitorPin;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getVisitorCompany() {
        return visitorCompany;
    }

    public void setVisitorCompany(String visitorCompany) {
        this.visitorCompany = visitorCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getVisitorStatu() {
        return visitorStatu;
    }

    public void setVisitorStatu(String visitorStatu) {
        this.visitorStatu = visitorStatu;
    }

    public String getInAddress() {
        return inAddress;
    }

    public void setInAddress(String inAddress) {
        this.inAddress = inAddress;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    @Override
    protected Serializable pkVal() {
        return this.visitorPin;
    }

    @Override
    public String toString() {
        return "VisitorlogView{" +
        "visitorPin=" + visitorPin +
        ", visitorMobile=" + visitorMobile +
        ", visitorCompany=" + visitorCompany +
        ", name=" + name +
        ", pin=" + pin +
        ", visitorStatu=" + visitorStatu +
        ", inAddress=" + inAddress +
        ", inTime=" + inTime +
        ", outTime=" + outTime +
        ", visitorType=" + visitorType +
        "}";
    }
}
