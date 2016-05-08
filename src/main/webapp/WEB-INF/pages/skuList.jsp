<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">

        var url;

        function searchColor() {
            $("#dg").datagrid('load', {
                "itemName": $("#s_itemName").val(),
            });
        }

        function openColorAddDialog() {
            resetValue();
            $("#dlg").dialog("open").dialog("setTitle", "添加Sku");
            url = "${pageContext.request.contextPath}/color/save";

            $("#dg").datagrid('load', {
                "name": "",
                "code": ""
            });
        }

        function openColorModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑Sku信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/color/save?id=" + row.id;
        }

        function saveColor() {
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

        function closeColorDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deleteColor() {
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
                    $.post("${pageContext.request.contextPath}/color/delete", {ids: ids}, function (result) {
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
    <title>Insert title here</title>
</head>
<body style="margin: 1px">
<table id="dg" title="" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/sku/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="itemName" width="50" align="center">商品名称</th>
        <th field="colorName" width="50" align="center">颜色</th>
        <th field="sizeDtlName" width="50" align="center">尺码</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        &nbsp;商品名称：&nbsp;<input type="text" id="s_itemName" size="20" onkeydown="if(event.keyCode==13) searchColor()"/>
        <a href="javascript:searchColor()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:620px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>Sku名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>

                <td>Sku代码：</td>
                <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
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
                    &nbsp;<span class="required">*</span>
                </td>
            </tr>
             -->
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveColor()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeColorDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>