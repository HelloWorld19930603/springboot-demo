<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
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
        <a href="index"><div class="dv"><%=now %></div></a>
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
                        <li> <h5>园区总人数：<span id="total">123</span></h5> </li>
                        <li> <a href="#"> <i class="fa fa-male text-success"></i> vip <p style="font-size: 18px;">32</p></a>  </li>
                        <li> <a href="#"> <i class="fa fa-male text-info"></i> 内部员工 <p style="font-size: 18px;">49</p></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-primary"></i> TK内部访客 <p style="font-size: 18px;">25</p></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-area"></i> 供应商 <p style="font-size: 18px;">17</p ></a> </li>
                        <li> <a href="#"> <i class="fa fa-male text-muted "></i> TK临时访客 <p style="font-size: 18px;">16</p></a></li>
                        <li> <a href="#"> <i class="fa fa-male text-danger "></i> 异常人员 <p style="font-size: 18px;">9</p></a>  </li>
                    </ul>
                </div>
            </div>
        </ul>
        <!--sidebar nav end-->

    </div>
</div>
