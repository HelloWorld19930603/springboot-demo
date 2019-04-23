<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
%>


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

            <div class="panel">
                <div class="panel-body">
                    <ul class="p-info">
                        <li>
                            <div class="title">Gender</div>
                            <div class="desk">Male</div>
                        </li>
                        <li>
                            <div class="title">Founder</div>
                            <div class="desk">ABC Inc.</div>
                        </li>
                        <li>
                            <div class="title">Education</div>
                            <div class="desk">BSC. in CSE</div>
                        </li>
                        <li>
                            <div class="title">Project Done</div>
                            <div class="desk">50+</div>
                        </li>
                        <li>
                            <div class="title">Skills</div>
                            <div class="desk">HTML, CSS, JavaScript.</div>
                        </li>
                    </ul>
                </div>
            </div>
        </ul>
        <!--sidebar nav end-->

    </div>
</div>
