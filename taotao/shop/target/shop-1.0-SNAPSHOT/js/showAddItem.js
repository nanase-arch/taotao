$(function () {
    var itemCatName;
    var pic = [];
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
    //根据id获取对象
    var cIdObj = $("#cId");
    var itemCatNameObj = $("#itemCatName");
    function zTreeOnClick(event, treeId, treeNode) {
        //使用from表单传递过去的应该是分类id 但是页面看到的应该是分类名称
        cIdObj.val(treeNode.id);
        itemCatName = treeNode.name;

    };

    layui.use(['form','upload','layedit'], function () {
        var form = layui.form
            , layer = layui.layer
            , upload = layui.upload
            , layedit = layui.layedit;

        layedit.set({
            uploadImage: {
                url: '/item/addPicDes' //接口url
                ,type: 'post' //默认post
            }
        });
        //构建一个默认的编辑器
        var index = layedit.build('des');
        //from表单提交数据 是以name = des value = 用户输入的值
        layedit.sync(index);
        //编辑器外部操作
        var active = {
            content: function(){
                alert(layedit.getContent(index)); //获取编辑器内容
            }
            ,text: function(){
                alert(layedit.getText(index)); //获取编辑器纯文本内容
            }
            ,selection: function(){
                alert(layedit.getSelection(index));
            }
        };

        //只要用户点击上传图片按钮以后 选择了图片以后 就会执行这个代码
        upload.render({
            elem: '#uploadPic'
            ,url: '/item/addPic' //改成您自己的上传接口
            ,multiple: true
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    //往showPic（div）里面创建img标签设置他的一系列东西
                    $('#showPic').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                });
            }
            ,done: function(res){
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
                    if(itemCatName==undefined){
                        itemCatNameObj.text("请勾选商品分类信息");
                    }else{
                        itemCatNameObj.text(itemCatName);
                    }

                    layer.close(index);
                }
            })


        });
        //点击保存按钮的点击事件
        $("#saveBtn").click(function () {
           //在这里我们应该真正的发起ajax请求  吧值提交过去 存入到数据库
            var cIdVal = cIdObj.val();
            if(cIdVal==""){
                layer.alert("请选择商品分类");
                return;
            }

            var titleVal = $("#title").val();
            var sellPointVal = $("#sellPoint").val();
            var priceVal = $("#price").val();
            var numVal = $("#num").val();
            var barcodeVal = $("#barcode").val();
            var imageVal = pic.join(",");
            var desVal = layedit.getContent(index);

            $.ajax({
                type: "POST",
                url: "/item/addItem",
                dataType: "json",
                data: "cId="+cIdVal+"&title="+titleVal+"&sellPoint="+sellPointVal+"&price="+priceVal*100+"&num="+numVal+"&barcode="+barcodeVal+"&image="+imageVal+"&des="+desVal,
                success: function (msg) {
                    if(msg.code=="ErrorResponse"){
                        alert("添加商品失败请联系管理员");
                    }else{
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