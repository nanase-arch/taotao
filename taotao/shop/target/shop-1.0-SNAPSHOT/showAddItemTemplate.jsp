<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品规格参数信息</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/js/showAddItemTemplate.js"></script>
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
    <input type="hidden" id="cId" name="cId"/>
    <div class="layui-form-item">
        <label class="layui-form-label">商品类目</label>
        <div class="layui-input-block">
            <button type="button" id="showZTree" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius">选择类目
            </button>
            <span style="margin-left: 20px" id="itemCatName"></span>
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
