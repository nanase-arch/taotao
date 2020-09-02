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
    <script type="text/javascript" src="/js/index.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">

            <a style="color: #009688" href="/index.jsp">淘淘商城后台</a>
        </div>


        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item"><a href="javascript:;"> <img
                    src="/images/header.jpg"
                    class="layui-nav-img"> clive</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="">基本资料</a>
                    </dd>
                    <dd>
                        <a href="">安全设置</a>
                    </dd>
                </dl></li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">商品分类添加</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">商品分类查询</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">商品添加</a>
                        </dd>
                        <dd>
                            <a id="findTbItemAll" href="javascript:;">商品查询</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">商品规格参数模板添加</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">商品规格参数模板查询</a>
                        </dd>
                    </dl></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">cms内容管理系统</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">内容分类管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">内容管理</a>
                        </dd>

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">查询订单</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">查询用户</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">活动管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">添加活动</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">查询活动</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">权限管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">添加权限</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">查询权限</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <iframe id="content" class="layadmin-iframe ifrem_voice" src="/statistics.jsp"
                    width="100%" height="700px" frameborder="0" name="voiceList"
                    scrolling="no"></iframe>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © www.clive.com - 淘淘后台
    </div>
</div>

</body>
</html>