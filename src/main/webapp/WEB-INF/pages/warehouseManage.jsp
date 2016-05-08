<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">

        var url;

        function searchWarehouse() {
            $("#dg").datagrid('load', {
                "name": $("#s_warehouseName").val(),
                "code": $("#s_warehouseCode").val(),
                "stat": $("#s_warehouseState").val()
            });
        }

        function openWarehouseAddDialog() {
            resetValue();
            $("#dlg").dialog("open").dialog("setTitle", "添加仓库");
            url = "${pageContext.request.contextPath}/warehouse/save";
        }

        function openWarehouseModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑仓库信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/warehouse/save?id=" + row.id;
        }

        function saveWarehouse() {
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
            $("#address").val("");
            $("#stat").val("使用");
            $("#phone").val("");
        }

        function closeWarehouseDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deleteWarehouse() {
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
                    $.post("${pageContext.request.contextPath}/warehouse/delete", {ids: ids}, function (result) {
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

        function openLocationDtl() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要管理的数据！");
                return;
            }
            window.parent.openTab('仓位管理', 'warehouseLocation?warehouseId=' + selectedRows[0].id, 'icon-lxr');
        }
    </script>
    <title>Insert title here</title>
</head>
<body style="margin: 1px">
<table id="dg" title="仓库管理" class="easyui-datagrid"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,fitColumns:true,
       url:'${pageContext.request.contextPath}/warehouse/list',method:'get',toolbar:'#tb'">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="name" width="50" align="center">仓库名称</th>
        <th field="code" width="50" align="center">仓库代码</th>
        <th field="phone" width="50" align="center">联系电话</th>
        <th field="address" width="50" align="center">地址</th>
        <th field="stat" width="50" align="center">状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openWarehouseAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openWarehouseModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit"
           plain="true">修改</a>
        <a href="javascript:deleteWarehouse()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="javascript:openLocationDtl()" class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">仓位管理</a>
    </div>
    <div>
        &nbsp;仓库名称：&nbsp;<input type="text" id="s_warehouseName" size="20"
                                onkeydown="if(event.keyCode==13) searchWarehouse()"/>
        &nbsp;仓库代码：&nbsp;<input type="text" id="s_warehouseCode" size="20"
                                onkeydown="if(event.keyCode==13) searchWarehouse()"/>
        &nbsp;仓库状态：&nbsp;<input type="text" id="s_warehouseState" size="20"
                                onkeydown="if(event.keyCode==13) searchWarehouse()"/>
        <a href="javascript:searchWarehouse()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:750px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>仓库名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>

                <td>仓库代码：</td>
                <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>

            <tr>
                <td>联系电话：</td>
                <td><input type="text" id="phone" name="phone"/></td>

                <td>地址：</td>
                <td><input type="text" id="address" name="address"/></td>
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
    <a href="javascript:saveWarehouse()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeWarehouseDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>