package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author cc
 * @since 2019-04-23
 */
@Data
@TableName("acclog_view")
public class AcclogView extends Model<AcclogView> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String pin;

    private String name;

    private String mobile;

    private String deptname;

    private String readerName;

    private String deviceName;

    private String sn;

    private LocalDateTime time;

    private String eventType;


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    protected Serializable pkVal() {
        return this.pin;
    }

    @Override
    public String toString() {
        return "AcclogView{" +
                "pin=" + pin +
                ", name=" + name +
                ", mobile=" + mobile +
                ", deptname=" + deptname +
                ", readerName=" + readerName +
                ", deviceName=" + deviceName +
                ", sn=" + sn +
                ", time=" + time +
                ", eventType=" + eventType +
                "}";
    }
}
