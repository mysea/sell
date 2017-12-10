<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li>
            <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <#--<a href="/sell/seller/product/list" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>-->
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header"><h5>商品</h5></li>
                <li><a href="/sell/seller/product/list">列表</a></li>
                <li><a href="/sell/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <#--<a href="/sell/seller/category/list" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>-->
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header"><h5>类目</h5></li>
                <li><a href="/sell/seller/category/list">列表</a></li>
                <li><a href="/sell/seller/category/index">新增</a></li>
            </ul>
        </li>

        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>