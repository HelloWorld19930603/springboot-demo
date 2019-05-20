<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
    Date d = new Date();
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
    String now1 = df1.format(d);
    String now2 =df2.format(d);

    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    if (w < 0)
        w = 0;
    String week = weekDays[w];
%>
<style>
    p{
        font-size: 18px;
    }
</style>

<link href="/static/css/animate.min.css" rel="stylesheet">
<div class="left-side sticky-left-side">

    <!--logo and iconic logo start-->
    <div class="logo">
        <a href="index"><div class="dv"><%=now1 %></div><div style="font-size: 21px;">&nbsp;&nbsp;<%=week %>&nbsp;&nbsp;&nbsp;&nbsp;<span id="time"><%=now2 %></span> </div></a>
    </div>

    <div class="logo-icon text-center">
        <a href="#"><img src="" alt="" width="40px" height="40px"></a>
    </div>
    <!--logo and iconic logo end-->

    <div class="left-side-inner">

        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav">

            <div class="panel" style="background-color: #424f63">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked labels-info ">
                        <li> <h5>园区总人数：<span id="total">0</span></h5> </li>
                        <li> <a href="#"> <i class="fa fa-male text-success"></i> 客户 <p style="font-size: 18px;" id="p1">0</p></a>  </li>
                        <li> <a href="#"> <i class="fa fa-male text-info"></i> 内部员工 <p style="font-size: 18px;" id="p2">0</p></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-primary"></i> TK分公司人员 <p style="font-size: 18px;" id="p3">0</p></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-area"></i> 供应商 <p style="font-size: 18px;" id="p4">0</p ></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-muted "></i> 临时来访 <p style="font-size: 18px;" id="p5">0</p></a></li>
                        <li> <a href="#"> <i class="fa fa-male text-danger "></i> 异常人员 <p style="font-size: 18px;" id="p6">0</p></a>  </li>
                    </ul>
                </div>
            </div>
        </ul>
        <!--sidebar nav end-->

    </div>
</div>
