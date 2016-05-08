<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

<%
	

%>
<script type="text/javascript">
 
 var skuJson;
 $(function(){
	 if(skuJson==null){
		 $.post("${pageContext.request.contextPath}/sku/comboList",null,function(result){
			 if(result){
				 skuJson=result;
			 }
			},"json");
	 }
	 
	 $.post("${pageContext.request.contextPath}/rent/findById",{id:'${param.id}'},function(result){
		 $("#customerName").val(result.customerName);
		 $("#billNo").val(result.billNo);
		},"json");
	 
	 $("#dg").edatagrid({
		url:'${pageContext.request.contextPath}/rentDtl/list?id=${param.id}',
		saveUrl:'${pageContext.request.contextPath}/rentDtl/save?id=${param.id}',
		updateUrl:'${pageContext.request.contextPath}/rentDtl/save',
		destroyUrl:'${pageContext.request.contextPath}/rentDtl/delete'
	 });
 });
 
 function setItemName (){
	 
 }
 
 
 function skuFormartter(value,row){
	 var par='<%=request.getAttribute("combo")%>';
	 if(value){
	 	return skujson[value];
	 }
 }
 
</script>
<title>Insert title here</title>
</head>
<body style="margin: 15px">
 
 <div id="p" class="easyui-panel" title="出租单基本信息" style="width: 900px;height: 200px;padding: 10px">
 	<table cellspacing="8px">
   		<tr>
   			<td>单据编号：</td>
   			<td><input type="text" id="billNo" name="billNo" readonly="readonly"/></td>

   			<td>客户名称</td>
   			<td><input type="text" id="customerName" name="customerName" readonly="readonly"/></td>
   		</tr>
   	</table>
 </div>
 
 <br/>
 <table id="dg" title="出租单明细" style="width:900px;height:350px"
   toolbar="#toolbar" idField="id" rownumbers="true" fitColumns="true" singleSelect="true">
   <thead>
   	<tr>
   		<th field="id" width="50" hidden="true">编号</th>
   		<th field="dtlId" width="50" hidden="true">编号</th>
   		<th field="skuId" width="50" editor="{type:'combobox',
   			options:{
   				valueField:'id',
   				textField:'text',
   				url:'${pageContext.request.contextPath}/sku/comboList',
   				required:true,
   				editable:false,
   				panelHeight:'auto',
   				onChange:setItemName,
   				formatter:skuFormartter
   		    }}">Sku</th>
   		<th field="itemName" width="100" editor="{type:'validatebox',options:{required:true}}">商品名称</th>   
   		<th field="itemPrice" width="100" editor="{type:'validatebox',options:{required:true}}">单价</th>
   		<th field="itemAmount" width="100" editor="{type:'validatebox',options:{required:true}}">数量</th>
   		<th field="itemRent" width="100" editor="{type:'validatebox',options:{required:true}}">金额</th>
   		<th field="itemRepo" width="100" editor="{type:'validatebox',options:{required:true}}">押金</th>
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