package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

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
@Data
@TableName("visitorlog_view")
public class VisitorlogView{

    private static final long serialVersionUID = 1L;

    @TableId
    private String visitorPin;

    private String visitorName;

    private String visitorMobile;

    private String visitorCompany;

    private String visitorPlate;

    private String name;

    private String pin;

    private String visitorStatu;

    private String inAddress;

    private String inTime;

    private String outTime;

    private String visitorType;


}
