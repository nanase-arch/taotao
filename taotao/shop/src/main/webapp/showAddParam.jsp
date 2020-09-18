<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加规格参数模板</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/js/showAddParam.js"></script>
    <link rel="stylesheet" href="/layui/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/layui/lay/jquery.ztree.core.js"></script>
</head>
<body>
<form class="layui-form" id="addItem" lay-filter="example">

    <div class="layui-form-item">
        <label class="layui-form-label">商品类目</label>
        <div class="layui-input-block">
            <button type="button" id="showZTree" class="layui-btn layui-btn-sm">选择类目
            </button>
            <span style="margin-left: 20px" id="itemCatName"></span>
        </div>
    </div>

    <div style="display: none" id="paramTemplate" class="layui-form-item">
        <label class="layui-form-label">规格模板</label>
        <div class="layui-input-block">
            <button id="addParamGroup" type="button" class="layui-btn layui-btn-radius">添加规格参数组</button>
        </div>
    </div>
    <div id="groupAndKey">

    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" id="saveBtn" class="layui-btn layui-bg-red">保存规格参数</button>
            <button type="reset" class="layui-btn">取消</button>
        </div>
    </div>
</form>
<div id="treeBox" style="display:none;">
    <ul id="itemCatTree" class="ztree"></ul>
</div>
</body>
</html>
