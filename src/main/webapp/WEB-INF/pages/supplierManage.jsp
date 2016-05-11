<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>供应商管理</title>
    <jsp:include page="common.jsp"/>
    <script type="text/javascript">

        var url;

        function searchSupplier() {
            $("#dg").datagrid('load', {
                "supplierName": $("#s_supplierName").val(),
                "stat":$('#s_supplierState').val()
            });
        }

        function openSupplierAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
            url = "${pageContext.request.contextPath}/supplier/save";
        }

        function openSupplierModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑供应商信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/supplier/save?id=" + row.id;
        }

        function saveSupplier() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        $("#fm").form('clear');
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败！");
                        return;
                    }
                }
            });
        }

        function closeSupplierDialog() {
            $("#dlg").dialog("close");
            $("#fm").form('clear');
        }

        function deleteSupplier() {
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
                    $.post("${pageContext.request.contextPath}/supplier/delete", {ids: ids}, function (result) {
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
<table id="dg" title="供应商管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/supplier/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="supplierName" width="50" align="center">供应商名称</th>
        <th field="supplierCode" width="50" align="center">供应商代码</th>
        <th field="mobile" width="50" align="center">联系电话</th>
        <th field="type" width="50" align="center">供应商类型</th>
        <th field="stat" width="50" align="center">状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openSupplierAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openSupplierModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit"
           plain="true">修改</a>
        <a href="javascript:deleteSupplier()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;供应商名称：&nbsp;<input type="text" id="s_supplierName" size="20"
                                 onkeydown="if(event.keyCode==13) searchSupplier()"/>
        &nbsp;状态：&nbsp;<input type="text" id="s_supplierState" size="20"
                                 onkeydown="if(event.keyCode==13) searchSupplier()"/>
        <a href="javascript:searchSupplier()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:640px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>供应商名称：</td>
                <td><input type="text" id="supplierName" name="supplierName" class="easyui-validatebox"
                           required="true"/>&nbsp;<span class="required">*</span></td>

                <td>供应商代码：</td>
                <td><input type="text" id="supplierCode" name="supplierCode" class="easyui-validatebox"
                           required="true"/>&nbsp;<span class="required">*</span></td>
            </tr>

            <tr>
                <td>联系电话：</td>
                <td><input type="text" id="mobile" name="mobile"/></td>

                <td>供应商类型</td>
                <td>
                    <select class="easyui-combobox" id="type" name="type" style="width: 173px" editable="false"
                            panelHeight="auto">
                        <option value="">请选择供应商类型...</option>
                        <option value="委外">委外</option>
                        <option value="供货">供货</option>
                        <option value="物流">物流</option>
                    </select>
                    &nbsp;<span class="required">*</span>
                </td>
            </tr>

            <tr>
                <td>状态：</td>
                <td>
                    <select class="easyui-combobox" id="stat" name="stat" style="width: 173px" editable="false"
                            panelHeight="auto">
                        <option value="">请选择状态...</option>
                        <option value="使用" selected="selected">使用</option>
                        <option value="停用">停用</option>
                    </select>
                    &nbsp;<span class="required">*</span>
                </td>

            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveSupplier()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeSupplierDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>