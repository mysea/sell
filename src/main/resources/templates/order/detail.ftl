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

                <div class="col-md-4 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetails as orderDetail>
                        <tr>
                            <td>${orderDetail.productId}</td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#if orderDTO.getOrderStatusEnum().message == "新订单">
                <div class="col-md-12 column">
                    <a type="button" class="btn btn-default btn-primary" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
                    <a type="button" class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                </div>
            </#if>

            </div>
        </div>

    </div>
</div>

</body>
</html>



