$(function () {
    var itemCatName;
    var pic = [];
    var cId = "";
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

    function zTreeOnClick(event, treeId, treeNode) {
        //使用from表单传递过去的应该是分类id 但是页面看到的应该是分类名称
        cId = treeNode.id;
        itemCatName = treeNode.name;

    };

    layui.use(['form', 'upload', 'layedit'], function () {
        var form = layui.form
            , layer = layui.layer
            , upload = layui.upload
            , layedit = layui.layedit;

        layedit.set({
            uploadImage: {
                url: '/item/addPicDes' //接口url
                , type: 'post' //默认post
            }
        });
        //构建一个默认的编辑器
        var index = layedit.build('des');
        //from表单提交数据 是以name = des value = 用户输入的值
        layedit.sync(index);
        //编辑器外部操作
        var active = {
            content: function () {
                alert(layedit.getContent(index)); //获取编辑器内容
            }
            , text: function () {
                alert(layedit.getText(index)); //获取编辑器纯文本内容
            }
            , selection: function () {
                alert(layedit.getSelection(index));
            }
        };

        //只要用户点击上传图片按钮以后 选择了图片以后 就会执行这个代码
        upload.render({
            elem: '#uploadPic'
            , url: '/item/addPic' //改成您自己的上传接口
            , multiple: true
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    //往showPic（div）里面创建img标签设置他的一系列东西
                    $('#showPic').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
                });
            }
            , done: function (res) {
                pic.push(res.data);
            }
        });

        //树形结构按钮的点击事件
        $("#showZTree").click(function () {
            layer.open({
                type: 1,
                offset: 't',
                skin: 'layui-layer-lan', //加上边框
                area: ['330px', '80%'], //宽高
                content: $('#treeBox'),
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (itemCatName == undefined) {
                        itemCatNameObj.text("请勾选商品分类信息");
                    } else {
                        itemCatNameObj.text(itemCatName);
                    }

                    layer.close(index);
                    //发起ajax请求拿分类id去查询规格参数组信息 并且返回规格参数组信息
                    $.ajax({
                        type: "POST",
                        url: "/param/getParam",
                        dataType: "json",
                        data: "cId=" + cId,
                        success: function (res) {
                            if (res.data.length == 0) {
                                layer.alert("该分类木有规格参数信息，请先添加规格参数模板");
                                $("#saveBtn").attr("class", "layui-btn layui-btn-disabled");
                            } else {
                                var tabObj = $("#table1");
                                var jsonArr = res.data;
                                $.each(jsonArr, function (index, node) {
                                    //得到了每一个json对象
                                    var json = jsonArr[index];
                                    //根据key 取value 得到组名
                                    var groupName = json["groupName"];
                                    //根据key 取value 得到组里面的项数组对象
                                    var paramArr = json["paramKeys"];
                                    $.each(paramArr, function (i, n) {
                                        //创建tr标签对象
                                        var trObj = $("<tr></tr>");
                                        if (i == 0) {
                                            var td1 = $("<td rowspan='"+paramArr.length+"' style='border-right: 1px solid red;' >"+groupName+"</td>");
                                            td1.appendTo(trObj);
                                        }
                                        var td2 = $("<td>" + n.paramName + "</td>");
                                        td2.appendTo(trObj);
                                        var td2 = $("<td><input id='" + n.id + "' name='paramId' type='text'/></td>");
                                        td2.appendTo(trObj);
                                        trObj.appendTo($("#table1"));
                                    })
                                })

                            }

                        }
                    });
                }
            })
        });
        //点击保存按钮的点击事件
        $("#saveBtn").click(function () {
            var json = {};
            if (cId == "") {
                layer.alert("请选择商品分类");
                return;
            }
            json.cId = cId;
            var inputArr = $("input[name='paramId']");
            var params = [];
            $.each(inputArr, function (i,n) {
                var j = {};
                j.paramId = $(n).attr("id");
                j.paramValue = $(n).val();
                params.push(j);
            })
            var titleVal = $("#title").val();
            var sellPointVal = $("#sellPoint").val();
            var priceVal = $("#price").val();
            var numVal = $("#num").val();
            var barcodeVal = $("#barcode").val();
            var imageVal = pic.join(",");
            var desVal = layedit.getContent(index);
            json.title = titleVal;
            json.sellPoint = sellPointVal;
            json.price = priceVal*100;
            json.num = numVal;
            json.barcode = barcodeVal;
            json.image = imageVal;
            json.des = desVal;
            json.params = params;

            $.ajax({
                type: "POST",
                url: "/item/addItem",
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                data: JSON.stringify(json),
                success: function (msg) {
                    if (msg.code == "ErrorResponse") {
                        layer.alert("添加商品失败请联系管理员");
                    } else {
                        layer.alert(msg.msg);
                    }
                    //自己清空分类id值
                    itemCatNameObj.text("");
                    //使用layui情况form表单input输入框的值
                    $("#addItem")[0].reset();
                    layui.form.render();
                    //自己情况 多图片上传的图片
                    $("#showPic>img").remove();
                    index = layedit.build('des');

                }
            });

        })
    });
})