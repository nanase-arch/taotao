$(function () {
    layui.use(['form', 'layedit', 'laydate', 'table'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            , table = layui.table;
        laydate.render({
            elem: '#date1',
            theme: '#393D49',
            showBottom: false
        });
        laydate.render({
            elem: '#date2',
            theme: '#393D49',
            showBottom: false
        });
        table.render({
            elem: '#itemTableAll'
            , url: '/item/getItemByPage'
            , toolbar: '#topBtnGroup' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '商品列表'
            , cols: [
                [
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
                    , {field: 'title', title: '商品名称', width: 150, edit: 'text'}
                    , {field: 'sellPoint', title: '商品卖点', width: 150, edit: 'text'}
                    , {field: 'itemPrice', title: '商品价格', width: 120, edit: 'text', sort: true}
                    , {field: 'num', title: '商品数量', width: 100}
                    , {field: 'itemImage', title: '商品图片', width: 160, templet: '#itemImage'}
                    , {field: 'tbItemCatName', title: '分类名称', width: 120}
                    , {field: 'status', title: '商品状态', width: 120, sort: true, templet: '#statusTransform'}
                    , {
                    field: 'created',
                    title: '创建时间',
                    width: 160,
                    templet: "<div>{{layui.util.toDateString(d.created, 'yyyy-MM-dd HH:mm:ss')}}</div>"
                }
                    , {
                    field: 'updated',
                    title: '更新时间',
                    width: 160,
                    templet: "<div>{{layui.util.toDateString(d.created, 'yyyy-MM-dd HH:mm:ss')}}</div>"
                }
                    , {fixed: 'right', title: '操作', toolbar: '#rightBtnGroup', width: 150}
                ]
            ]

            , page: true
        });
        /*
        *   注意这里绑定的不是table表的id值 而是lay-filter
        * */
        table.on('toolbar(itemTableAll)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'delItem':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    /*
                    *  在这里我们用第一种做法
                    * */
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要删除的数据");
                        break;
                    }
                    //发起ajax请求 来controller完成真正的删除
                    $.ajax({
                        type: "POST",
                        url: "/item/delItemById",
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        data: jsonStr,
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });
                    break;
                case 'addItem':

                    break;
                case 'upload':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要上架的商品");
                        break;
                    }
                    //定义了一个空数组
                    var ids = [];
                    //json代表你要循环的数据 i代表下标 n代表对象
                    //layui的这个造型他认为是一个json字符串 不是一个真正的json对象
                    var json = eval('(' + jsonStr + ')');
                    $.each(json, function (i, n) {
                        ids[i] = n["id"];
                    });

                    $.ajax({
                        type: "POST",
                        url: "/item/changeItemStatus",
                        dataType: "json",
                        data: "ids=" + ids + "&statusCode=1",
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });

                    break;
                case 'offload':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要上架的商品");
                        break;
                    }
                    //定义了一个空数组
                    var ids = [];
                    //json代表你要循环的数据 i代表下标 n代表对象
                    //layui的这个造型他认为是一个json字符串 不是一个真正的json对象
                    var json = eval('(' + jsonStr + ')');
                    $.each(json, function (i, n) {
                        ids[i] = n["id"];
                    });

                    $.ajax({
                        type: "POST",
                        url: "/item/changeItemStatus",
                        dataType: "json",
                        data: "ids=" + ids + "&statusCode=2",
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });
                    break;

            }
            ;

        });
        // 监听行工具事件
        table.on('tool(itemTableAll)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                var jsonStr = JSON.stringify(data);
                var json = eval('(' + jsonStr + ')');
                console.log(json.id);
                $.ajax({
                    type: "POST",
                    url: "/item/changeItemStatus",
                    dataType: "json",
                    data: "ids=" + json.id + "&statusCode=3",
                    success: function (msg) {
                        layer.alert(msg.msg);
                        table.reload('itemTableAll', {
                            url: '/item/getItemByPage'
                        });
                    }
                });
            } else if (obj.event === 'edit') {

            }
        });

    });
})


