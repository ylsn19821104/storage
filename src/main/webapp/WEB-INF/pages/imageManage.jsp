<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图片管理</title>
    <jsp:include page="common.jsp"/>
    <style type="text/css">
        #preview {
            width: 260px;
            height: 190px;
            border: 1px solid #000;
            overflow: hidden;
        }

        #imghead {
            filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
        }
    </style>
    <script type="text/javascript">
        $(function () {
            bindHandlers();
            var t1Url = '/image';

            function bindHandlers() {
                $('#btn_add').bind('click', add);
                $('#btn_edit').bind('click', edit);
                $('#btn_query').bind('click', query);
                $('#btn_remove').bind('click', remove);
                $('#btn_edit_save').bind('click', save);
                $('#btn_edit_close').bind('click', closeEditPanel);
                $.previewImage = previewImage;
            }

            function add() {
                $.currentItem = null;
                $('#id').combobox('reload');
                $('#editForm').form('clear');
                $('#editPanel').dialog('open');
            }

            function edit() {
                var rows = $('#dg').datagrid('getSelections');
                if (rows) {
                    if (rows.length > 1) {
                        $.messager.alert('系统提示!', '只能对一行进行编辑!')
                    } else if (rows.length == 1) {
                        $.currentItem = rows[0];
                        var url = t1Url + '/findById';
                        $.ajax({
                            url: url,
                            data: {id: rows[0]['id']},
                            dataType: 'json',
                            type: 'get'
                        }).success(function (ret) {
                            $('#imghead').attr({
                                'src': '/resources/images/upload/' + ret.id + ret.suffix,
                                width: 300,
                                height: 400
                            });
                            $('#editForm').form('load', ret);
                            $('#editPanel').dialog('open');
                        }).error(function (err) {
                            $.messager.alert('系统提示!', '获取数据!请重新尝试或联系管理员!');
                        });
                    }
                }

                else {
                    $.messager.alert('系统提示!', '请选择要编辑的行!')
                }
            }

            function remove() {
                var rows = $('#dg').datagrid('getSelections');
                var ids = [];
                if (rows && rows.length > 0) {
                    $(rows).each(function (i, v, r) {
                        ids.push(v['id']);
                    });
                }
                if (ids.length > 0) {
                    $.ajax({
                        url: t1Url + '/remove',
                        data: {ids: ids},
                        dataType: 'json',
                        type: 'post'
                    }).success(function (ret) {
                        if (ret && ret.flag > 0) {
                            $.messager.alert('系统提示!', '删除成功!');
                            query();
                        } else {
                            $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                        }
                    }).error(function (err) {
                        $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                    });
                }
            }

            function query() {
                var url = t1Url + '/list';
                $.ajax({
                    url: url,
                    dataType: 'json',
                    type: 'get'
                }).success(function (ret) {
                    if (ret && ret.rows) {
                        $('#dg').datagrid('loadData', ret);
                    } else {
                        $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
                    }
                }).error(function (err) {
                    $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
                });
            }

            function save() {
                if (!$.currentItem && !$('input[name="img"]').val()) {
                    $.messager.alert('系统提示', '请选择要上传的文件!');
                    return;
                }
                if ($('#editForm').form('validate')) {
                    $('#editForm').form('submit', {
                                url: t1Url + ($.currentItem ? '/update' : '/save'),
                                ajax: false,
                                onSubmit: function () {
                                    $.messager.progress();
                                    return true;	// 返回false终止表单提交
                                },
                                success: function (ret) {
                                    ret = JSON.parse(ret);
                                    if (ret && ret.flag) {
                                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                                        $.messager.alert('系统提示!', '保存成功!');
                                        $('#editForm').form('clear');
                                        closeEditPanel();
                                        query();
                                        $.currentItem = null;
                                    } else {
                                        $.messager.progress('close');
                                        $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                                    }

                                }
                            }
                    );
                }


            }

            function closeEditPanel() {
                $('#editPanel').dialog('close');
                $('#imghead').attr('src', '');
            }

            function previewImage(file) {
                var MAX_WIDTH = 600;
                var MAX_HEIGHT = 275;
                var div = $('#preview');
                if (file.files && file.files[0]) {
                    if (!(/image.*/.test(file.files[0].type))) {
                        $.messager.alert('系统提示', '请选择图片文件!');
                        $('#img').filebox('clear');
                        $('#imghead').attr('src', '');
                        return;
                    }
                    div.html('<img id="imghead">');
                    var img = document.getElementById('imghead');
                    img.onload = function () {
                        var rect = calcImgZoomParam(MAX_WIDTH, MAX_HEIGHT, img.offsetWidth, img.offsetHeight);
                        img.width = rect.width;
                        img.height = rect.height;
                        img.style.marginTop = rect.top + 'px';
                    }
                    var reader = new FileReader();
                    reader.onload = function (evt) {
                        img.src = evt.target.result;
                    }
                    reader.readAsDataURL(file.files[0]);
                }
                else if (document.selection)//兼容IE
                {
                    var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
                    file.select();
                    var src = document.selection.createRange().text;
                    div.html('<img id="imghead">');
                    var img = document.getElementById('imghead');
                    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                    var rect = calcImgZoomParam(MAX_WIDTH, MAX_HEIGHT, img.offsetWidth, img.offsetHeight);
                    var status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
                    div.html("<div id='divhead' style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>");
                } else {
                    $('#imghead').attr("src", "");
                }
            }

            function calcImgZoomParam(maxWidth, maxHeight, width, height) {
                var param = {top: 0, left: 0, width: width, height: height};
                if (width > maxWidth || height > maxHeight) {
                    var rateWidth = width / maxWidth;
                    var rateHeight = height / maxHeight;

                    if (rateWidth > rateHeight) {
                        param.width = maxWidth;
                        param.height = Math.round(height / rateWidth);
                    } else {
                        param.width = Math.round(width / rateHeight);
                        param.height = maxHeight;
                    }
                }

                param.left = Math.round((maxWidth - param.width) / 2);
                param.top = Math.round((maxHeight - param.height) / 2);
                return param;
            }

        })
        ;
    </script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="图片列表" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'${pageContext.request.contextPath}/image/list',method:'get',toolbar:'#menu'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80">SKU</th>
        <th data-options="field:'comment',width:80">描述</th>
        <th data-options="field:'preview',width:80,
            formatter:function(value,row,index){
            var pre = '<img width=\'80px\' height=\'80px\'src=\'${pageContext.request.contextPath}/resources/images/upload/';
            var suf = '\'/>';
            return pre+row.id+row.suffix+suf;
        }">预览图
        </th>
    </tr>
    </thead>

</table>
<div id="menu" style="padding:2px 5px;">
    <a id="btn_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    <a id="btn_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a id="btn_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a id="btn_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<%--以下为编辑面板的内容--%>
<div id="editPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:650px;height:450px;"
     data-options="minimizable: true,maximizable: true,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>SKU</td>
                <td>
                    <select class="easyui-combobox" name="id" style="width:200px;" data-options="
                    required:true,
                    valueField: 'id',
                    textField: 'id',
                    method:'get',
                    url: '${pageContext.request.contextPath}/image/comboList'">
                    </select>
                </td>
            </tr>
            <tr>
                <td>描述信息</td>
                <td>
                    <input class="easyui-textbox" type="text" name="comment" style="width:200px">
                </td>
            </tr>
            <tr>
                <td>文件</td>
                <td>
                    <input type="file" accept="image/*" class="easyui-filebox" name="img" data-options="required:true"
                           onchange="$.previewImage(this)">
                </td>
            </tr>

            <tr>
                <td>
                    <div id="preview"><img id="imghead" border=0 src=''></div>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="editPanel-buttons">
    <a id="btn_edit_save" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
    <a id="btn_edit_close" href="javascript:void(0)" class="easyui-linkbutton">取消</a>
</div>

</body>
</html>