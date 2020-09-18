<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品信息</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/js/showAddItem.js"></script>
    <link rel="stylesheet" href="/layui/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/layui/lay/jquery.ztree.core.js"></script>
    <style>
        #sellPoint {
            min-height: 60px;
        }

        .layui-upload-img {
            width: 92px;
            height: 92px;
            margin: 0 10px 10px 0;
        }
    </style>
</head>
<body>
<form class="layui-form" id="addItem" lay-filter="example">
    <div class="layui-form-item">
        <label class="layui-form-label">商品类目</label>
        <div class="layui-input-block">
            <button type="button" id="showZTree" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius">选择类目
            </button>
            <span style="margin-left: 20px" id="itemCatName"></span>
        </div>
    </div>
    <%--商品标题--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品标题</label>
        <div class="layui-input-inline">
            <input placeholder="请输入商品标题" id="title" type="text" name="title" class="layui-input">
        </div>
    </div>
    <%--商品卖点--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品卖点</label>
        <div class="layui-input-inline">
            <textarea placeholder="请输入商品卖点" id="sellPoint" name="sellPoint" class="layui-textarea"></textarea>
        </div>
    </div>
    <%--商品价格--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品价格</label>
        <div class="layui-input-inline">
            <input placeholder="请输入商品价格" id="price" type="text" name="price" class="layui-input">
        </div>
    </div>
    <%--商品库存--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品库存</label>
        <div class="layui-input-inline">
            <input placeholder="请输入商品库存" id="num" type="text" name="num" class="layui-input">
        </div>
    </div>
    <%--商品条形码--%>
    <div class="layui-form-item">
        <label class="layui-form-label">条形码</label>
        <div class="layui-input-inline">
            <input placeholder="请输入条形码" id="barcode" type="text" name="barcode" class="layui-input">
        </div>
    </div>
    <%--商品图片--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品图片</label>
        <div class="layui-input-block">
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="uploadPic">上传图片</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    预览图：
                    <div class="layui-upload-list" id="showPic"></div>
                </blockquote>
            </div>
        </div>
    </div>

    <%--商品描述--%>
    <div class="layui-form-item">
        <label class="layui-form-label">商品描述</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" name="des" id="des" style="display: none">

            </textarea>
        </div>
    </div>
    <%--商品规格参数--%>
    <div class="layui-form-item">
        <label class="layui-form-label">规格参数</label>
        <div class="layui-input-block">
            <table id="table1" style="border-collapse:separate; border-spacing:0px 10px;">

            </table>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" id="saveBtn" class="layui-btn layui-bg-red" lay-submit="">立即提交</button>
            <button type="reset" class="layui-btn layui-bg-orange">重置</button>
        </div>
    </div>
</form>
<div id="treeBox" style="display:none;">
    <ul id="itemCatTree" class="ztree"></ul>
</div>
</body>
</html>
