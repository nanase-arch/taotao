<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>淘淘后台管理页面</title>
    <link rel="stylesheet"
          href="/layui/css/layui.css">
    <script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/js/showItem.js"></script>

</head>
<body>

<div>
    <form class="layui-form" action="/item/multiQuery ">

        <div class="layui-form-item">
            <%--商品标题--%>
            <label class="layui-form-label">商品标题</label>
            <div class="layui-input-inline">
                <input type="text" name="title" class="layui-input">
            </div>
            <%--商品价格区间--%>
            <div class="layui-inline">
                <label class="layui-form-label">价格范围</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <%--价格最小值--%>
                    <input type="text" name="price1" placeholder="￥" class="layui-input">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <%--价格最大值--%>
                    <input type="text" name="price2" placeholder="￥" class="layui-input">
                </div>
            </div>
                <%--商品状态 1:正常 2:下架 3:删除--%>
            <div class="layui-inline">
                <label class="layui-form-label">状态选择</label>
                <div class="layui-input-inline">
                    <select name="status">
                        <option value="1">正常</option>
                        <option value="2">下架</option>
                        <option value="3">删除</option>
                    </select>
                </div>
            </div>
                <%--日期范围--%>
            <div class="layui-inline">
                <label class="layui-form-label">范围</label>
                <div class="layui-input-inline">
                    <%--日期开始时间--%>
                    <input type="text" name="date1" id="date1" lay-verify="date" autocomplete="off"  placeholder="开始时间"
                            class="layui-input">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline">
                    <%--日期开始时间--%>
                    <input type="text" name="date2" id="date2"  placeholder="结束时间" class="layui-input">
                </div>
            </div>
                <button type="submit" class="layui-btn layui-bg-red" lay-submit="" lay-filter="demo1">立刻搜索</button>
        </div>
    </form>


</div>

<table class="layui-hide" id="itemTableAll" lay-filter="test"></table>
<div style="display: none" class="layui-btn-container" id="topBtnGroup">
    <button class="layui-btn layui-btn-sm" lay-event="delItem">选中删除</button>
    <button class="layui-btn layui-btn-sm" lay-event="addItem">新增商品</button>
    <button class="layui-btn layui-btn-sm" lay-event="upload">商品上架</button>
    <button class="layui-btn layui-btn-sm" lay-event="offload">商品下架</button>
</div>
<div style="display: none" type="text/html" id="rightBtnGroup">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>

<script type="text/html" id="transform">
    {{#  if(d.status == 1){ }}
        <span>正常</span>
    {{#  } else if(d.status==2){ }}
        <span>下架</span>
    {{#  } else if(d.status==3){ }}
        <span>删除</span>
    {{#  } }}
</script>
</body>
</html>