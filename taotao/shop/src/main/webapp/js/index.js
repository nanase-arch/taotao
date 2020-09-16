$(function () {
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    //layui内容部分对象
    var contentObj = $("#content");
    //商品查询按钮
    $("#findTbItemAll").click(function () {
        contentObj.attr("src","/showItem.jsp");
    })
    $("#showAddItem").click(function () {
        contentObj.attr("src","/showAddItem.jsp");
    })
    $("#showAddItemTemplate").click(function () {
        contentObj.attr("src","/showAddItemTemplate.jsp");
    })
})