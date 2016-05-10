<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${propName}</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">
        $(function () {
            $.propId = ${id};
            var id = ${id};
            var url = id+'/';

            bindHandlers();
            function bindHandlers() {
                $('#btn_add').bind('click', toAdd);
                $('#btn_edit').bind('click', toEdit);
                $('#btn_query').bind('click', query);
                $('#btn_remove').bind('click', remove);

                $('#btn_edit_save').bind('click', save);
                $('#btn_edit_close').bind('click', closeEditPanel);
            }

            function toAdd() {
                $.currentItem = {val: ''};
                $('#editForm').form('clear');
                $('#editPanel').dialog('open');
            }

            function toEdit() {
                var rows = $('#dg').datagrid('getChecked');
                if (rows) {
                    if (rows.length > 1) {
                        $.messager.alert('系统提示!', '只能对一行进行编辑!')
                    } else if (rows.length == 1) {
                        var row = rows[0];
                        $.currentItem = row;
                        $('#editForm').form('load', $.currentItem);
                        $('#editPanel').dialog('open');
                    }
                } else {
                    $.messager.alert('系统提示!', '请选择要编辑的行!')
                }
            }

            function remove() {
                var rows = $('#dg').datagrid('getChecked');
                var ids = [];
                if (rows && rows.length > 0) {
                    $(rows).each(function (i, v, r) {
                        ids.push(v['id']);
                    });
                }
                if (ids.length > 0) {
                    $.ajax({
                        url: 'remove',
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
                $('#dg').datagrid('reload');
            }

            function save() {
//                $('#btn_edit_save').unbind();
//                setTimeout(function () {
//                    $('#btn_edit_save').bind(save);
//                }, 1000);
                if ($('#editForm').form('validate')) {
                    $('#editForm input').each(function (i, val) {
                        var name = $(val).attr('name');
                        if (name && $(val).val()) {
                            $.currentItem[name] = $(val).val();
                        }
                    });

                    $.ajax({
                        url: url + 'save',
                        type: 'post',
                        data: $.currentItem
                    }).success(function (ret) {
                        if (ret && ret.flag) {
                            $.messager.alert('系统提示!', '保存成功!');
                            $('#editForm').form('clear');
                            $('#editPanel').dialog('close');
                            query();
                        } else {
                            $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                        }
                    }).error(function (e) {
                        $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                    }).complete(function (e) {
                        $.currentItem = {};
                        $.messager.progress('close');
                    });
                }
            }
            function closeEditPanel() {
                $('#editPanel').dialog('close');
            }
        });
    </script>

</head>
<body>
<table id="dg" class="easyui-datagrid" title="${propName}" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'${id}/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'val',width:60">名称</th>
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
<div id="editPanel" class="easyui-dialog edit-panel" title="编辑"
     data-options="minimizable: true,maximizable: true,resizable: true,width:400,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table>
            <tr>
                <td class="label">名称</td>
                <td><input class="easyui-textbox input" data-options="required:true" type="text" name="val"
                           id="val">
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