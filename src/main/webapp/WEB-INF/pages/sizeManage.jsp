<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">

        var url;

        function searchSize() {
            $("#dg").datagrid('load', {
                "code": $("#s_code").val()
            });
        }

        function openSizeAddDialog() {
            resetValue();
            $("#dlg").dialog("open").dialog("setTitle", "添加尺码表信息");
            url = "${pageContext.request.contextPath}/size/save";
        }

        function openSizeModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }

            var row = selectedRows[0];
            row['xxprimary'] = row.primaryCategoryId;
            $("#xxprimary").combobox('setValue', row.primaryCategoryId);//.combobox('setText',row.brandName);


            $("#dlg").dialog("open").dialog("setTitle", "编辑尺码表信息");
            $("#fm").form("load", row);

            url = "${pageContext.request.contextPath}/size/save?id=" + row.id;
        }

        function saveSize() {
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
        }

        function closeSizeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deleteSize() {
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
                    $.post("${pageContext.request.contextPath}/size/delete", {ids: ids}, function (result) {
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

        function openSizeDtl() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要管理的数据！");
                return;
            }
            window.parent.openTab('尺码管理', 'sizeDtl?sizeId=' + selectedRows[0].id, 'icon-lxr');
        }

        function setPrimaryValue(newVal, oldVal) {
            //$('#primaryCategoryId').val(newVal);
            $('#primaryCategoryId').val($('#xxcolor').combotree('getValues'));
        }

    </script>
    <title>Insert title here</title>
</head>
<body style="margin: 1px">
<table id="dg" title="尺码表管理" class="easyui-datagrid"
       pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/size/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="code" width="150" align="center">尺码表代码</th>
        <th field="name" width="150" align="center">尺码表描述</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openSizeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
        <a href="javascript:openSizeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteSize()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="javascript:openSizeDtl()" class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">尺码管理</a>
    </div>
    <div>
        &nbsp;尺码表代码：&nbsp;<input type="text" id="s_code" size="20" onkeydown="if(event.keyCode==13) searchSize()"/>
        <a href="javascript:searchSize()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>


<div id="dlg" class="easyui-dialog" style="width:750px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>代码：</td>
                <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>

                <td>名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveSize()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeSizeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>