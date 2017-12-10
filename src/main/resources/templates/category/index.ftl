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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" name="categoryName" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input type="text" name="categoryType" class="form-control" value="${(category.categoryType)!''}"/>
                        </div>
                        <input type="hidden" name="categoryId" value="${(category.categoryId)!''}"/>
                        <button type="submit" class="btn btn-default">保存</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>



