$(function () {
    var itemCatName;
    var cId;
    var setting = {
        async: {
            enable: true,
            url: "/itemCat/showItemCat",
            autoParam: ["id"],
            dataFilter: filter,

        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    //初始化树形结构
    $.fn.zTree.init($("#itemCatTree"), setting);

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i = 0, l = childNodes.length; i < l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }

    var itemCatNameObj = $("#itemCatName");

    //点击了 树形结构就会执行这里面的代码
    function zTreeOnClick(event, treeId, treeNode) {
        //使用from表单传递过去的应该是分类id 但是页面看到的应该是分类名称
        cId = treeNode.id;
        itemCatName = treeNode.name;

    };

    layui.use(['form', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
        /*
        * 树形结构下面的确定按钮点击事件
        * */
        $("#showZTree").click(function () {
            layer.open({
                type: 1,
                offset: 't',
                skin: 'layui-layer-lan', //加上边框
                area: ['330px', '10%'], //宽高
                content: $('#treeBox'),
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (itemCatName == undefined) {
                        itemCatNameObj.text("请勾选商品分类信息");
                    } else {
                        itemCatNameObj.text(itemCatName);
                    }
                    layer.close(index);
                    //根据分类id查询规格参数组信息
                    $.ajax({
                        type: "POST",
                        url: "/param/getParam",
                        dataType: "json",
                        data: "cId=" + cId,
                        success: function (res) {
                            /*
                            *   TaotaoResult
                            *       code
                            *       msg
                            *       data 如果data为空 表示
                            *       这个分类id下 没有规格参数信息 就要人工添加规格参数信息
                            * */
                            if (res.data.length == 0) {
                                $("#paramTemplate").show();
                            } else {
                                $("#paramTemplate").show();
                                //创建了一个模板

                                $("#groupAndKey").append("<div class='layui-form-item'><label class='layui-form-label'>商品描述</label><div class='layui-input-inline' id='updateParam'></div></div>");
                                $.each(res.data,function (index,node) {
                                    var divObj1 = $("<div class='layui-form-item'><label class='layui-form-label'>规格参数组</label><div id='"+node.id+"' class='layui-input-inline layui-row layui-col-space10' style='width: 320px;'><div class='layui-col-md7'><input type='text' name='group' class='layui-input' value='"+node.groupName+"'/></div><div class='layui-col-md5'><input type='button' value='&#xe624;' onclick='addParamKey(this)' class='layui-btn layui-bg-blue layui-icon layui-icon-addition' /><input type='button' class='layui-btn layui-bg-red layui-icon layui-icon-addition' value='&#xe640;' id='"+node.id+"' onclick='delParamKey(this)'/></div></div></div>");
                                    divObj1.appendTo($("#updateParam"));
                                    var params=node.paramKeys;
                                    $.each(params,function (i,n) {
                                        console.log(node.id);
                                        var divObj2 = $("<div class='layui-col-md3'>|___</div><div class='layui-col-md9'><input type='text' id='"+n.id+"' value='"+n.paramName+"' name='groupkey' class='layui-input'/></div>");
                                        divObj2.appendTo($("#"+node.id));
                                    })
                                })
                            }
                        }
                    });
                }
            })
        });
    });
    //动态创建规格组
    $("#addParamGroup").click(function () {
        $("#groupAndKey").append("<div class='layui-form-item'><label class='layui-form-label'>规格参数组</label><div style='width: 320px;' class='layui-input-inline layui-row layui-col-space10'><div class='layui-col-md7'><input type='text' name='group' class='layui-input'></div> <div class='layui-col-md5'><input type='button' value='&#xe624;' onclick='addParamKey(this)'class='layui-btn layui-bg-blue layui-icon layui-icon-addition'><input type='button' value='&#xe640;' onclick='delParamKey(this)'class='layui-btn layui-bg-red layui-icon layui-icon-addition'></div></div></div>");
    })
    //保存按钮
    $("#saveBtn").click(function () {
        //我们找到的是
        var divArr = $("#groupAndKey .layui-form-item");
        var jsonArr = [];
        $.each(divArr,function (index,node) {

            //这里得到的是组名
            var groupName = $("input[name='group']").eq(index).val();
            //定义了一个 json对象 但是是空对象 （key:value）
            var j = {};
            j.itemCatId = cId;
            //定义 给json 的key赋值value  key（group）:groupName
            j.groupName = groupName;
            var paramsArr = [];
            var groupkeyArr = $(node).find("input[name='groupkey']");
            $.each(groupkeyArr,function (i,n) {
                //全新的json对象
                var jj = {};
                jj.paramName = $(n).val();
                paramsArr.push(jj);

            })
            //   [{"group":"主体",params:[{},{}]},{"group":"主体",params:[{},{}]}]
            j.paramKeys = paramsArr;
            jsonArr.push(j);

        })
        console.log(jsonArr);
        //使用ajax发起请求了
        $.ajax({
            type: "POST",
            url: "/param/addParam",
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(jsonArr),
            success: function (res) {
                layer.alert(res.msg);
                window.location.href = "http://localhost:8080/showAddParam.jsp";
            }
        });
    });

})

//点击添加按钮 动态创建规格项
function addParamKey(e) {
    $(e).parent().parent().append("<div class='layui-col-md3'>|____</div><div class='layui-col-md9'><input type='text' name='groupkey' class='layui-input'></div>");
}

//点击删除按钮 动态删除规格组
function delParamKey(e) {
    $(e).parent().parent().parent().remove();
}



