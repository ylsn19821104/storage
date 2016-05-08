<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>大分类管理</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">

        var url;

        function searchPrimaryCategory() {
            $("#dg").datagrid('load', {
                "name": $("#s_name").val(),
                "code": $("#s_code").val()
            });
        }

        function openPrimaryCategoryAddDialog() {
            resetValue();
            $("#dlg").dialog("open").dialog("setTitle", "添加小分类");
            url = "${pageContext.request.contextPath}/category/primary/save";

            $("#dg").datagrid('load', {
                "name": "",
                "code": ""
            });
        }

        function openPrimaryCategoryModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑小分类信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/category/primary/save?id=" + row.id;
        }

        function savePrimaryCategory() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败！");
                        return;
                    }
                }
            });
        }

        function resetValue() {
            $("#name").val("");
            $("#code").val("");
            //$("#stat").val("使用");
        }

        function closePrimaryCategoryDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deletePrimaryCategory() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].id);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确定要删除这<span class='required'>" + selectedRows.length + "</span>条数据吗？", function (r) {
                if (r) {
                    $.post("${pageContext.request.contextPath}/category/primary/delete", {ids: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "数据已成功删除！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
                        }
                    }, "json");
                }
            });
        }
    </script>

</head>
<body style="margin: 1px">
<table id="dg" title="大分类管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/category/primary/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="name" width="50" align="center">分类名称</th>
        <th field="code" width="50" align="center">分类代码</th>
        <th field="create_time" width="50" align="center">创建时间</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openPrimaryCategoryAddDialog()" class="easyui-linkbutton" iconCls="icon-add"
           plain="true">添加</a>
        <a href="javascript:openPrimaryCategoryModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit"
           plain="true">修改</a>
        <a href="javascript:deletePrimaryCategory()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;分类名称：&nbsp;<input type="text" id="s_name" size="20"
                                onkeydown="if(event.keyCode==13) searchPrimaryCategory()"/>
        &nbsp;分类代码：&nbsp;<input type="text" id="s_code" size="20"
                                onkeydown="if(event.keyCode==13) searchPrimaryCategory()"/>
        <a href="javascript:searchPrimaryCategory()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:620px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>分类名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<span
                        class="required">*</span></td>
                <%----%>
                <td>分类代码：</td>
                <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<span
                        class="required">*</span></td>
            </tr>
            <!--
            <tr>
                <td>状态：</td>
                <td>
                    <select class="easyui-combobox" id="stat" name="stat" style="width: 143px" editable="false" panelHeight="auto">
                        <option value="">请选择状态...</option>
                        <option value="使用" selected="selected">使用</option>
                        <option value="停用">停用</option>
                    </select>
                    &nbsp;<p class="required">*</p>
                </td>
            </tr>
             -->
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:savePrimaryCategory()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePrimaryCategoryDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>