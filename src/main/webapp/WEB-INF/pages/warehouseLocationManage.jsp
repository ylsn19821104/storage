<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓位明细表</title>
	<jsp:include page="common.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<script type="text/javascript">
	$(function(){
		$.get("${pageContext.request.contextPath}/warehouse/findById",{warehouseId:'${param.warehouseId}'},function(result){
			$("#scode").val(result.code);
			$("#sname").val(result.name);
		},"json");
		
		$("#dg").edatagrid({
			url:'${pageContext.request.contextPath}/warehouseLocation/list?warehouseId=${param.warehouseId}',
			saveUrl:'${pageContext.request.contextPath}/warehouseLocation/save?warehouseId=${param.warehouseId}',
			updateUrl:'${pageContext.request.contextPath}/warehouseLocation/save',
			destroyUrl:'${pageContext.request.contextPath}/warehouseLocation/delete'
		});
	});
</script>
</head>
<body style="margin: 15px;">
	<div id="p" class="easyui-panel" title="仓位明细" style="width: 700px; height: 100px;padding: 10px;">
		<table cellpadding="">
			<tr>
				<td>仓库代码：</td>
				<td><input type="text" id="scode" name="scode" readonly="readonly"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   				<td>仓库名称：</td>
   				<td><input type="text" id="sname" name="sname" readonly="readonly"/></td>
			</tr>
		</table>	
	</div>
	
	<br>
	<table id="dg" title="仓位明细" style="width:700px;height:250px" toolbar="#toolbar" idField="id" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="50">id</th>
				<th field="code" width="50" editor="{type:'validatebox',options:{required:true}}">代码</th>
				<th field="name" width="100" editor="{type:'validatebox',options:{required:true}}">名称</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
	 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">添加</a>
	 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
	 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow');$('#dg').edatagrid('reload')">保存</a>
	 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销行</a>
    </div>
</body>
</html>