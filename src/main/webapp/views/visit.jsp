<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../commons/path.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">

    <title><%=title%></title>

    <!--common-->
    <link href="<%=path%>/css/style.css" rel="stylesheet">
    <link href="<%=path%>/css/style-responsive.css" rel="stylesheet">
    <link href="<%=path%>/css/gm.css" rel="stylesheet">
    <link href="<%=path%>/css/grid.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=path%>/js/html5shiv.js"></script>
    <script src="<%=path%>/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    * { touch-action: pan-y; }

    .search-wrapper .input-holder {
        overflow: hidden;
        background: rgba(255,255,255,0);
        border-radius:6px;
        position: relative;
        width:70px;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out;
    }
    .search-wrapper.active .input-holder {
        border-radius: 30px;
        width: 200px;
        background: rgba(0,0,0,0.5);
        -webkit-transition: all .5s cubic-bezier(0.000, 0.105, 0.035, 1.570);
        -moz-transition: all .5s cubic-bezier(0.000, 0.105, 0.035, 1.570);
        transition: all .5s cubic-bezier(0.000, 0.105, 0.035, 1.570);
    }

    .search-wrapper .input-holder .search-input {
        width:100%;
        height: 35px;
        padding: 0px 50px 0 10px;
        opacity: 0;
        position: absolute;
        top:0px;
        left:0px;
        background: linear-gradient(to right, #f78ca0 0%, #f9748f 19%, #fd868c 60%, #fe9a8b 100%);;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        border:none;
        outline:none;
        font-family:"Open Sans", Arial, Verdana;
        font-size: 16px;
        font-weight: 400;
        line-height: 20px;
        color:#FFF;
        -webkit-transform: translate(0, 60px);
        -moz-transform: translate(0, 60px);
        transform: translate(0, 60px);
        -webkit-transition: all .3s cubic-bezier(0.000, 0.105, 0.035, 1.570);
        -moz-transition: all .3s cubic-bezier(0.000, 0.105, 0.035, 1.570);
        transition: all .3s cubic-bezier(0.000, 0.105, 0.035, 1.570);

        -webkit-transition-delay: 0.3s;
        -moz-transition-delay: 0.3s;
        transition-delay: 0.3s;
    }
    .search-wrapper.active .input-holder .search-input {
        opacity: 1;
        -webkit-transform: translate(0, 10px);
        -moz-transform: translate(0, 10px);
        transform: translate(0, 10px);
        color: white;
    }
    .search-wrapper input::-webkit-input-placeholder {
        color: white;
    }
    .search-wrapper .input-holder .search-icon {
        width:70px;
        height:35px;
        border: 1px solid #ccc;
        border-radius: 30px;
        background: #FFF;
        padding:0px;
        outline:none;
        position: relative;
        z-index: 2;
        float:right;
        cursor: pointer;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out;
    }
    .search-wrapper.active .input-holder .search-icon {
        width: 40px;
        height: 35px;
        margin: 10px;
        border-radius: 30px;
    }
    .search-wrapper .input-holder .search-icon span {
        width:22px;
        height:22px;
        display: inline-block;
        vertical-align: middle;
        position:relative;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        transform: rotate(45deg);
        -webkit-transition: all .4s cubic-bezier(0.650, -0.600, 0.240, 1.650);
        -moz-transition: all .4s cubic-bezier(0.650, -0.600, 0.240, 1.650);
        transition: all .4s cubic-bezier(0.650, -0.600, 0.240, 1.650);

    }
    .search-wrapper.active .input-holder .search-icon span {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        transform: rotate(-45deg);
    }
    .search-wrapper .input-holder .search-icon span::before, .search-wrapper .input-holder .search-icon span::after {
        position: absolute;
        content:'';
    }
    .search-wrapper .input-holder .search-icon span::before {
        width: 4px;
        height: 11px;
        left: 9px;
        top: 15px;
        border-radius: 2px;
        background: #FE007F;
    }
    .search-wrapper .input-holder .search-icon span::after {
        width: 14px;
        height: 14px;
        left: 3px;
        top: 3px;
        border-radius: 16px;
        border: 4px solid #FE007F;
    }


    .search-wrapper .result-container {
        width: 100%;
        position: absolute;
        top:80px;
        left:0px;
        text-align: center;
        font-family: "Open Sans", Arial, Verdana;
        font-size: 14px;
        display:none;
        color:#B7B7B7;
    }


    @media screen and (max-width: 560px) {
        .search-wrapper.active .input-holder {width:200px;}
    }
</style>
<body class="sticky-header" >

<section>
    <!-- left side start-->
    <%@include file="../commons/left.jsp" %>
    <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">

        <!-- header section start-->
        <%@include file="../commons/header.jsp" %>
        <!-- header section end-->

        <!-- page heading start-->
        <div class="page-heading">

            <ul class="breadcrumb">
                <li>
                    <a href="#">访客</a>
                </li>
                <li class="active"> 访客查询</li>
            </ul>
            <div class="btn-group" style="float:right;">
                <button id="share1" class="btn btn-primary" style="font-size: 12px;padding: 4px 4px;margin-top: 3px;">
                    导出访客报表 <i class="fa fa-share-square-o"></i>
                </button>
                <button id="share2" class="btn btn-info" style="font-size: 12px;padding: 4px 4px;margin-top: 3px;">
                    导出门禁报表1 <i class="fa fa-share-square-o"></i>
                </button>
                <button id="share3" class="btn btn-file" style="font-size: 12px;padding: 4px 4px;margin-top: 3px;">
                    导出门禁报表2 <i class="fa fa-share-square-o"></i>
                </button>
                <button id="share4" class="btn btn-block" style="font-size: 12px;padding: 4px 4px;margin-top: 3px;">
                    导出门禁报表3 <i class="fa fa-share-square-o"></i>
                </button>
            </div>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">

            <section class="search-area panel">
                <%--<c:if test="${!(userDetail eq null) && userDetail.isAdmin}">--%>
                <div class="sa-ele">
                    <label class="se-title">访客名称:</label>
                    <input class="se-con" name="name"/>
                </div>
                <div class="sa-ele">
                    <label class="se-title">手机号:</label>
                    <input class="se-con" name="mobile"/>
                </div>
                 <div class="sa-ele">
                     <label class="se-title">车牌号:</label>
                     <input class="se-con" name="plate"/>
                  </div>
                <div class="sa-ele">
                    <button class="search-action">搜索</button>
                    <button class="reset-action">重置</button>
                </div>
            </section>
            <section class="grid-main" style="margin-top: 5px;">
                <table></table>
            </section>
        </div>
        <!--body wrapper end-->


    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script src="<%=path%>/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="<%=path%>/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=path%>/js/jquery.validate.min.js"></script>
<script src="<%=path%>/js/jquery.stepy.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/modernizr.min.js"></script>
<script src="<%=path%>/js/jquery.nicescroll.js"></script>


<!--common scripts for all pages-->
<script src="<%=path%>/js/scripts.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/gm.js"></script>


<script src="<%=path%>/js/sweetalert/sweetalert2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/js/sweetalert/sweetalert2.min.css">
<!-- IE support -->
<script src="<%=path%>/js/sweetalert/es6-promise.min.js"></script>
</body>
</html>
<script type="text/javascript">


    // GridManager 渲染
    var table = document.querySelector('table');
    function init() {
        table.GM({
            supportRemind: true
            ,gridManagerName: 'test'
            ,height: '100%'
            ,supportAjaxPage:true
            ,supportSorting: true
            ,supportCheckbox: false
            ,isCombSorting: false
            ,disableCache: false
            ,checkbox:false
            // ajax_url 将在v2.6.0以上版本废弃，请不要再使用
            // ,ajax_url: 'http://www.lovejavascript.com/blogManager/getBlogList'
            ,ajax_data: function () {
                return '/list';
            }
            // ,firstLoading: false // 初始渲染时是否加载数据
            ,ajax_type: 'POST'
            ,supportMenu: true


            // AJAX失败事件函数
            ,ajax_error: function(error){
                console.log('ajax_error');
            }
            ,query: {serise: -1}
            ,dataKey: 'list'  // 注意: 这里是用来测试responseHandler 是否生效; 数据本身返回为data, 而在这里我把数据名模拟为list, 再通过responseHandler去更改
            ,pageSize:10

            // 通过该方法修改全部的请求参数
            ,requestHandler: function(request){
                request.newParams = '这个参数是通过 requestHandler 函数新增的';
                // 更改这个参数后, 将会强制createDate字段使用 降序排序.
                // 'sort_' 通过 配置项 sortKey 进行配置
                // 'DESC' 通过 配置项 sortDownText 进行配置
                //			request.sort_createDate = 'DESC';
                return request;
            }
            // 可以通过该方法修改返回的数据
            ,responseHandler: function(response){

                // 数据本身返回为data, 通过responseHandler更改为与dataKey匹配的值
                response.list = response.data;
                return response;
            }
            ,columnData: [
/*                {
                    key: 'visitorPin',
                    remind: 'the pic',
                    text: '编号',
                    isShow: false
                },*/
                {
                    key: 'visitorName',
                    remind: 'the pic',
                    width: '60px',
                    align: 'center',
                    text: '访客名称',
                    // 使用函数返回 dom node
                    template: function(visitorName, rowObject) {

                        return visitorName==null?"":visitorName;
                    }
                },{
                    key: 'visitorMobile',
                    remind: 'the title',
                    align: 'center',
                    width: '60px',
                    text: '访客联系方式',
                    sorting: '',
                    // 使用函数返回 dom node
                    template: function(visitorMobile, rowObject) {

                        return visitorMobile==null?"":visitorMobile;
                    }
                }, {
                    key: 'visitorCompany',
                    remind: 'the pic',
                    width: '100px',
                    align: 'center',
                    text: '访客公司',
                    // 使用函数返回 dom node
                    template: function(visitorCompany, rowObject) {

                        return visitorCompany==null?"":visitorCompany;
                    }
                }, {
                    key: 'visitorPlate',
                    remind: 'the pic',
                    width: '100px',
                    align: 'center',
                    text: '访客车牌号',
                    // 使用函数返回 dom node
                    template: function(visitorPlate, rowObject) {

                        return visitorPlate==null?"":visitorPlate;
                    }
                },{
                    key: 'inTime',
                    remind: 'the title',
                    align: 'center',
                    width: '120px',
                    text: '进入时间',
                    sorting: '',
                    // 使用函数返回 dom node
                    template: function(inTime, rowObject) {

                        return inTime==null?"":inTime.replace("T"," ");
                    }
                },{
                    key: 'outTime',
                    remind: 'the title',
                    align: 'center',
                    width: '120px',
                    text: '离开时间',
                    sorting: '',
                    // 使用函数返回 dom node
                    template: function(outTime, rowObject) {

                        return outTime==null?"":outTime.replace("T"," ");
                    }
                }, {
                    key: 'visitorType',
                    remind: 'the pic',
                    width: '100px',
                    align: 'center',
                    text: '访客类型',
                    // 使用函数返回 dom node
                    template: function(visitorType, rowObject) {

                        return visitorType==null?"":visitorType;
                    }
                }
            ]
            // 排序后事件
            ,sortingAfter: function (data) {
                console.log('sortAfter', data);
            }
        }, function(query){
            // 渲染完成后的回调函数
            console.log('渲染完成后的回调函数:', query);
        });
    }



    (function(){
        // 绑定搜索事件
        document.querySelector('.search-action').addEventListener('click', function () {
            var _query = {
                name: document.querySelector('[name="name"]').value,
                mobile: document.querySelector('[name="mobile"]').value,
                plate: document.querySelector('[name="plate"]').value,
                index: 1
            };
            table.GM('setQuery', _query, function(){
                console.log('setQuery执行成功');
            });
        });

        // 绑定重置
        document.querySelector('.reset-action').addEventListener('click', function () {
            document.querySelector('[name="name"]').value = '';
            document.querySelector('[name="mobile"]').value = '';
            document.querySelector('[name="plate"]').value = '';
        });


        init();
        var current = new Date(new Date().getTime() - 2 * 3600000 );
        var time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
        var start = current.getTime() - time + 2 * 3600000;
        $.ajax({
                url: "/count?startTime="+dateFtt("yyyy-MM-dd hh:mm:ss", new Date(start)),
                type: "get",
                success: function (data) {
                    //num1 内部员工
                    //num2 TK分公司人员
                    //num3 异常人员
                    //num4 客户
                    //num5 临时供应商
                    //num6 临时来访
                    //num7 长期供应商
                    $("#p1").text(data.num5 + data.num6);
                    $("#p2").text(data.num4);
                    $("#p3").text(data.num7);
                    $("#p4").text(data.num1 + data.num2);
                    $("#p5").text(data.num3);
                    $("#total").text(data.all);
                },
                error: function (data) {

                    console.log("count请求异常");
                }
            });
    })();

    $("#share1").click(function () {
        exportVisit("所有访客")

    })


    $("#share2").click(function () {
        exportAcc("门禁报表1")

    })

    $("#share3").click(function () {
        exportAcc2("门禁报表2")

    })

    $("#share4").click(function () {
        exportAcc3("门禁报表1")

    })


/*    $("#a1").click(function () {
        exportVisit("客户")

    })

    $("#a3").click(function () {
        exportVisit("内部人员（分公司）")

    })

    $("#a4").click(function () {
        exportVisit("供应商")

    })

    $("#a5").click(function () {
        exportVisit("访客")

    })*/

    function exportVisit(type) {
        swal({
            title: '确认导出今天'+type+'数据吗？',
            showCancelButton: true,
            animation: "slide-from-top",
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            preConfirm: function (result) {
                return new Promise(function (resolve) {
                    if (result) {
                        resolve([
                        ]);
                    }
                });
            }
        }).then(function (result) {
            if (result) {
                var current = new Date(new Date().getTime() - 2 * 3600000 );
                var time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
                var start = current.getTime() - time + 2 * 3600000;
                var end = current.getTime() - time + 26 * 3600000;
                window.location.href = "/exportVisitor?startDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(start)) + "&endDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(end))+"&type="+type;
            }
        })
    }

    function exportAcc(type){
        swal({
            title: '确认导出今天'+type+'数据吗？',
            showCancelButton: true,
            animation: "slide-from-top",
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            preConfirm: function (result) {
                return new Promise(function (resolve) {
                    if (result) {
                        resolve([
                        ]);
                    }
                });
            }
        }).then(function (result) {
            if (result) {
                var current = new Date(new Date().getTime() - 2 * 3600000 );
                var time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
                var start = current.getTime() - time + 2 * 3600000;
                var end = current.getTime() - time + 26 * 3600000;
                window.location.href = "/exportAcc?startDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(start)) + "&endDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(end))+"&type="+type;
            }
        })
    }

    function exportAcc2(type){
        swal({
            title: '确认导出今天'+type+'数据吗？',
            showCancelButton: true,
            animation: "slide-from-top",
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            preConfirm: function (result) {
                return new Promise(function (resolve) {
                    if (result) {
                        resolve([
                        ]);
                    }
                });
            }
        }).then(function (result) {
            if (result) {
                var current = new Date(new Date().getTime() - 2 * 3600000 );
                var time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
                var start = current.getTime() - time + 2 * 3600000;
                var end = current.getTime() - time + 26 * 3600000;;
                window.location.href = "/exportAcc2?startDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(start)) + "&endDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(end))+"&type="+type;
            }
        })
    }

    function exportAcc3(type){
        swal({
            title: '确认导出昨天'+type+'数据吗？',
            showCancelButton: true,
            animation: "slide-from-top",
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            preConfirm: function (result) {
                return new Promise(function (resolve) {
                    if (result) {
                        resolve([
                        ]);
                    }
                });
            }
        }).then(function (result) {
            if (result) {
                var current = new Date(new Date().getTime() - 26 * 3600000 );
                var time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
                var start = current.getTime() - time + 2 * 3600000;
                var end = current.getTime() - time + 26 * 3600000;
                window.location.href = "/exportAcc?startDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(start)) + "&endDate=" + dateFtt("yyyy-MM-dd hh:mm:ss", new Date(end))+"&type="+type;
            }
        })
    }


        $(function () {
            window.setInterval(function() {

                $("#time").text(dateFtt("hh:mm:ss",new Date()))

            },1000)
        })
</script>