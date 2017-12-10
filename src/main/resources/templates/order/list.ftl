<html>
<#include "../common/header.ftl">
<body>

    <div id="wrapper" class="toggled">
        <#-- sidebar -->

        <#include "../common/nav.ftl">

        <#-- content -->
            <div id="page-content-wrapper">

                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                                    <td>${orderDTO.getPayStatusEnum().message}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td>
                                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                    </td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>

                    <#--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">

                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else >
                                <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage-1}">上一页</a></li>
                            </#if>

                            <#list 1..orderDTOPage.getTotalPages() as page>
                                <#if currentPage == page>
                                    <li class="disabled"><a href="#">${page}</a></li>
                                <#else >
                                    <li><a href="/sell/seller/order/list?size=${size}&page=${page}">${page}</a></li>
                                </#if>
                            </#list>

                            <#if currentPage gte orderDTOPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else >
                                <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage+1}">下一页</a></li>
                            </#if>

                            </ul>
                        </div>
                    </div>
                </div>

            </div>
    </div>

    <#--&lt;#&ndash; 消息弹窗 &ndash;&gt;-->
    <#--<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
        <#--<div class="modal-dialog">-->
            <#--<div class="modal-content">-->
                <#--<div class="modal-header">-->
                    <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                    <#--<h4 class="modal-title" id="myModalLabel">-->
                        <#--提醒-->
                    <#--</h4>-->
                <#--</div>-->
                <#--<div class="modal-body">-->
                    <#--你有新的订单-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                    <#--<button onclick="document.getElementById('notice').pause();location.reload()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <#--&lt;#&ndash;播放音乐&ndash;&gt;-->
    <#--<audio id="notice" loop="loop">-->
        <#--<source src="/sell/music/song.mp3" type="audio/mpeg"/>-->
    <#--</audio>-->

    <#--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>-->
    <#--<script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>-->
    <#--<script>-->
        <#--var websocket = null;-->
        <#--if('WebSocket' in window){-->
            <#--websocket = new WebSocket('ws://127.0.0.1/sell/webSocket');-->
        <#--}else {-->
            <#--alert('该浏览器不支持websocket');-->
        <#--}-->

        <#--websocket.onopen = function (event) {-->
            <#--console.log("建立连接");-->
        <#--}-->

        <#--websocket.onclose = function (event) {-->
            <#--console.log("连接关闭");-->
        <#--}-->

        <#--websocket.onmessage = function (event) {-->
            <#--console.log("收到消息："+event.data)-->
            <#--//弹窗提醒，播放音乐-->
            <#--$("#myModal").modal('show');-->

            <#--document.getElementById("notice").play();-->
        <#--}-->

        <#--websocket.onerror = function () {-->
            <#--alert("websocket通信发生错误");-->
        <#--}-->

        <#--window.onbeforeunload = function () {-->
            <#--websocket.close();-->
        <#--}-->
    <#--</script>-->
</body>
</html>



