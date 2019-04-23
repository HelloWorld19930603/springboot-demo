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



}
