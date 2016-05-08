<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>
    <script type="text/javascript">
        var url;
        function searchItem() {
            $("#dg").datagrid('load', {
                "code": $("#s_code").val(),
                "name": $("#s_name").val()
            });
        }

        function openItemAddDialog() {
            resetValue();

            $("#dlg").dialog("open").dialog("setTitle", "添加商品信息");
            url = "${pageContext.request.contextPath}/item/save";
        }

        function openItemModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }

            var row = selectedRows[0];
            row['xxbrand'] = row.brandId;
            row['xxprimary'] = row.primaryCategoryId;
            row['xxminor'] = row.minorCategoryId;

            $("#xxbrand").combobox('setValue', row.brandId).combobox('setText', row.brandName);

            $("#xxprimary").combobox('setValue', row.primaryCategoryId);
            $("#xxminor").combobox('setValue', row.minorCategoryId);

            var color = row.color;
            if (color != null || color.length > 0) {
                var colors = color.split(',');
                $('#xxcolor').combotree('setValues', colors);
            }

            var size = row.size;
            if (size != null || size.length > 0) {
                var sizes = size.split(',');
                $('#xxsize').combotree('setValues', sizes);
            }

            $("#dlg").dialog("open").dialog("setTitle", "编辑商品信息");
            $("#fm").form("load", row);

            url = "${pageContext.request.contextPath}/item/save?id=" + row.id;
        }

        function saveItem() {
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
            $('#fm').form('clear');
        }

        function closeItemDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deleteItem() {
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
                    $.post("${pageContext.request.contextPath}/item/delete", {ids: ids}, function (result) {
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

        function openItemColors() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要管理的数据！");
                return;
            }
            window.parent.openTab('商品联系人管理', 'itemColorsManage.jsp?itemId=' + selectedRows[0].id, 'icon-lxr');
        }

        function openItemContact() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要管理的数据！");
                return;
            }
            window.parent.openTab('商品交往记录管理', 'contactManage.jsp?cusId=' + selectedRows[0].id, 'icon-jwjl');
        }

        function openItemOrder() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要管理的数据！");
                return;
            }
            window.parent.openTab('商品历史订单查询', 'orderManage.jsp?cusId=' + selectedRows[0].id, 'icon-lsdd');
        }

        function setBrandValue(newVal, oldVal) {
            $('#brandId').val(newVal);
        }

        function setMinorValue(newVal, oldVal) {
            $('#minorCategoryId').val(newVal);
        }

        function setPrimaryValue(newVal, oldVal) {
            $('#primaryCategoryId').val(newVal);
        }

        function setColorValue(newVal, oldVal) {
            $('#color').val($('#xxcolor').combotree('getValues'));
        }

        function setSizeValue(newVal, oldVal) {
            $('#size').val($('#xxsize').combotree('getValues'));
        }


    </script>
    <title>Insert title here</title>
</head>
<body style="margin: 1px">
<table id="dg" title="" class="easyui-datagrid"
       pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/item/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="code" width="150" align="center">商品编号</th>
        <th field="name" width="150" align="center">商品名称</th>
        <th field="style" width="150" align="center">款号</th>
        <th field="brandName" width="150" align="center">品牌</th>
        <th field="primaryCategoryName" width="150" align="center">大分类</th>
        <th field="minorCategoryName" width="150" align="center">小分类</th>
        <th field="primaryCategoryId" width="150" align="center" hidden="true">大分类</th>
        <th field="minorCategoryId" width="150" align="center" hidden="true">小分类</th>
        <th field="rental1" width="150" align="center">租金1</th>
        <th field="rental2" width="150" align="center">租金2</th>
        <th field="deposit" width="150" align="center">押金</th>
        <th field="colorName" width="150" align="left">颜色</th>
        <th field="sizeName" width="150" align="left">尺码</th>
        <th field="color" width="50" align="center" hidden="true">color</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openItemAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
        <a href="javascript:openItemModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteItem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <%--<a href="javascript:openItemSku()" class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">Sku查看</a>--%>
    </div>
    <div>
        &nbsp;商品编号：&nbsp;<input type="text" id="s_code" size="20" onkeydown="if(event.keyCode==13) searchItem()"/>
        &nbsp;商品名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode==13) searchItem()"/>
        <a href="javascript:searchItem()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>


<div id="dlg" class="easyui-dialog" style="width:800px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <input type="hidden" id="brandId" name="brandId">
                <input type="hidden" id="brandName" name="brandName">
                <td>商品名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>

                <td>商品代码：</td>
                <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>款号：</td>
                <td><input type="text" id="style" name="style" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>

                <td>品牌：</td>
                <td>
                    <input class="easyui-combobox" id="xxbrand" name="xxbrand"
                           data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/brand/itemManagerComboList'
   				,onChange:setBrandValue"/>&nbsp;
                    <font color="red">*</font>
                </td>

                <!--<td>创建日期：</td>
                <td><input class="easyui-datebox" id="create_time" name="create_time"></input></td> -->
            </tr>

            <tr>
                <td>大分类：</td>
                <td>
                    <input class="easyui-combobox" id="xxprimary" name="xxprimary"
                           data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/category/primary/itemManagerComboList'
   				,onChange:setPrimaryValue"/>&nbsp;
                    <font color="red">*</font>
                </td>
                <input type="hidden" id="primaryCategoryId" name="primaryCategoryId">
                <input type="hidden" id="primaryCategoryName" name="primaryCategoryName">

                <td>小分类：</td>
                <td>
                    <input class="easyui-combobox" id="xxminor" name="xxminor"
                           data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/category/minor/itemManagerComboList'
   				,onChange:setMinorValue"/>&nbsp;
                    <font color="red">*</font>
                </td>
                <input type="hidden" id="minorCategoryId" name="minorCategoryId">
                <input type="hidden" id="minorCategoryName" name="minorCategoryName">
            </tr>

            <tr>
                <td>租金1：</td>
                <td><input type="text" id="rental1" name="rental1" class="easyui-numberbox" precision="2" min="1"
                           required="true"/>&nbsp;<span class="required">*</span></td>



                <td>租金2：</td>
                <td><input type="text" id="rental2" name="rental2" class="easyui-numberbox" precision="2" min="1"
                           required="true"/>&nbsp;<span class="required">*</span></td>
            </tr>

            <tr>
                <td>押金：</td>
                <td><input type="text" id="deposit" name="deposit" class="easyui-numberbox" precision="2" min="1"
                           required="true"/>&nbsp;<span class="required">*</span></td>

            </tr>

            <tr>
                <input type="hidden" id="color" name="color">
                <input type="hidden" name="colorName" id="colorName">
                <td>颜色：</td>
                <td><select class="easyui-combotree" id="xxcolor" name="xxcolor" style="width:173px;"
                            url='${pageContext.request.contextPath}/color/itemManagerComboList'
                            data-options="required:true,multiple:true,valueField:'id',textField: 'name',panelHeight: 'auto',onChange:setColorValue"></select>
                </td>


                <input type="hidden" id="size" name="size">
                <input type="hidden" name="sizeName" id="sizeName">
                <td>尺码：</td>
                <td><select class="easyui-combotree" id="xxsize" name="xxsize" style="width:173px;"
                            url='${pageContext.request.contextPath}/sizeDtl/comboList'
                            data-options="required:true,multiple:true,valueField:'id',textField: 'name',panelHeight: 'auto',onChange:setSizeValue"></select>
                </td>
            </tr>

        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveItem()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeItemDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>