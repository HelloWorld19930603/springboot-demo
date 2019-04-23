<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="header-section">

    <!--toggle button start-->
    <a class="toggle-btn"><i class="fa fa-bars"></i></a>
    <!--toggle button end-->

    <!--search start-->

    <!--search end-->

    <!--notification menu start -->
    <div class="menu-right">
        <ul class="notification-menu">
            <li>
                <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <img src="<%=path%>/images/photos/user.jpg" alt="" />
                    欢迎您，管理员${user.name}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                    <li onclick="modifyPassword()"><a href="#"><i class="fa fa-cog"></i>  修改密码</a></li>
                    <li><a href="loginout.do"><i class="fa fa-sign-out"></i> 注销</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!--notification menu end -->
    <!-- visible to small devices only -->
    <div class="visible-xs hidden-sm hidden-md hidden-lg">
        <div class="media logged-user">
            <img alt="" src="<%=path%>/images/photos/user.jpg" class="media-object">
            <div class="media-body">
                <h4><a href="#">欢迎您，管理员${user.name}</a></h4>
                <span></span>
            </div>
        </div>

        <h5 class="left-nav-title"></h5>
        <ul class="nav nav-pills nav-stacked custom-nav">
            <li onclick="modifyPassword()"><a href="#" ><i class="fa fa-cog"></i>  修改密码</a></li>
            <li><a href="loginout.do"><i class="fa fa-sign-out"></i> 注销</a></li>
        </ul>
    </div>
</div>
<script language="javascript">

    function modifyPassword(){
        var user_id = $("#user_id").val();
        var path = "/modifyPwd.do";
        var left1 = (screen.width-700)/2;
        var top1 = (screen.height-400)/2;
        window.open(path, "", "width=600, height=400, left=" + left1.toString() + ", top=" + top1.toString());
    }
</script>