<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>尺码明细表</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

    <script type="text/javascript">
        $(function () {
            $.post("${pageContext.request.contextPath}/size/findById", {id: '${param.sizeId}'}, function (result) {
                var code = result.code;
                var name = result.name;
                $("#scode").val(result.code);
                $("#sname").val(result.name);
            }, "json");

            $("#dg").edatagrid({
                url: '${pageContext.request.contextPath}/sizeDtl/list?sizeId=${param.sizeId}',
                saveUrl: '${pageContext.request.contextPath}/sizeDtl/save?sizeId=${param.sizeId}',
                updateUrl: '${pageContext.request.contextPath}/sizeDtl/save',
                destroyUrl: '${pageContext.request.contextPath}/sizeDtl/delete'
            });
        });
    </script>
</head>
<body style="margin: 15px;">
<div id="p" class="easyui-panel" title="尺码表基本信息" style="width: 700px; height: 100px;padding: 10px;">
    <table cellpadding="">
        <tr>
            <td>尺码表代码：</td>
            <td><input type="text" id="scode" name="scode" readonly="readonly"/></td>

            <td>尺码表描述：</td>
            <td><input type="text" id="sname" name="sname" readonly="readonly"/></td>
        </tr>
    </table>
</div>

<br>
<table id="dg" title="尺码明细" style="width:700px;height:250px" toolbar="#toolbar" idField="id" rownumbers="true"
       fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="id" width="50" hidden="true">id</th>
        <th field="code" width="50" editor="{type:'validatebox',options:{required:true}}">代码</th>
        <th field="name" width="100" editor="{type:'validatebox',options:{required:true}}">名称</th>
    </tr>
    </thead>
</table>

<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
       onclick="javascript:$('#dg').edatagrid('addRow')">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
       onclick="javascript:$('#dg').edatagrid('saveRow');$('#dg').edatagrid('reload')">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true"
       onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销行</a>
</div>
</body>
</html>