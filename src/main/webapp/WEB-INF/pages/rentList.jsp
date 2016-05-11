<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="common.jsp"/>

<script type="text/javascript">
 
 $(function(){
	 $('#beginDate').datebox({
         onSelect: function(date){
             var end = $('#endDate').datebox('getValue');
             if(date&&end){
                 end = new Date(end);
                 if(date.getTime()>end.getTime()){
                     $.messager.alert('系统提示!','开始时间不能大于开始时间!')
                     $(this).datebox('setValue',null);
                     ('#rentDay').numberbox('setValue',null);
                     return;
                 }
                 var interval = date.getTime()-(new Date(end)).getTime();
                 $('#rentDay').numberbox('setValue',(interval).toFixed(2)/86400000+1);
             }
         }
     });
     $('#endDate').datebox({
         onSelect: function(date){
             var start = $('#beginDate').datebox('getValue');

             if(date&&start){
                 start = new Date(start);
                 if(start.getTime()>date.getTime()){
                     $.messager.alert('系统提示!','结束时间不能小于开始时间!')
                     $(this).datebox('setValue',null);
                     ('#rentDay').numberbox('setValue',null);
                     return;
                 }
                 var interval = date.getTime()-start.getTime();
                 $('#rentDay').numberbox('setValue',((interval).toFixed(2)/86400000)+1);
             }
         }
     });
	 
 })
 
 var url;

 function searchRent(){
	 $("#dg").datagrid('load',{
		"billNo":$("#s_billNo").val(),
		"customerName":$("#s_customerName").val()
	 });
 }
 
 function openRentMainAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","出租单客户信息");
	 url="${pageContext.request.contextPath}/rent/save";
 }
 
 function openRentMainModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 
	 var row=selectedRows[0];
	 $("#xxstat").combobox('setValue',row.stat);
	 $("#xxwarehouse").combobox('setValue',row.warehouseId);
	 
	 $("#dlg").dialog("open").dialog("setTitle","编辑出租单客户信息");
	 $("#fm").form("load",row);
	 url="${pageContext.request.contextPath}/rent/save?id="+row.id;
 }
 
 function saveRent(){
	 $("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			/*
			if($("#area").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择客户地区！");
				return false;
			}
			if($("#cusManager").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择客户经理！");
				return false;
			}
			if($("#level").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择客户等级！");
				return false;
			}
			if($("#myd").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择客户满意度！");
				return false;
			}
			if($("#xyd").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择客户信用度！");
				return false;
			}
			*/
			return $(this).form("validate");
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result.success){
				$.messager.alert("系统提示","保存成功！");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示","保存失败！");
				return;
			}
		}
	 });
 }
 
 function resetValue(){
	 $("#name").val("");
 }
 
 function closeCustomerDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 function deleteRent(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<span class='required'>"+selectedRows.length+"</span>条数据吗？",function(r){
		if(r){
			$.post("${pageContext.request.contextPath}/rent/delete",{ids:ids},function(result){
				if(result.success){
					 $.messager.alert("系统提示","数据已成功删除！");
					 $("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
				}
			},"json");
		} 
	 });
 }
  
 function openRentDetail(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要管理的数据！");
		 return;
	 }
	 window.parent.openTab('客户联系人管理','rentDtl2.jsp?id='+selectedRows[0].id,'icon-lxr');
 }
 
 function openCustomerContact(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要管理的数据！");
		 return;
	 }
	 window.parent.openTab('客户交往记录管理','contactManage.jsp?cusId='+selectedRows[0].id,'icon-jwjl');
 }
 
 function openCustomerOrder(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要管理的数据！");
		 return;
	 }
	 window.parent.openTab('客户历史订单查询','orderManage.jsp?cusId='+selectedRows[0].id,'icon-lsdd');
 }
 
 function setWarehouseValue(newVal,oldVal){
	 $('#warehouseId').val(newVal);
 }
 
 function setStatValue(newVal,oldVal){
	 $('#stat').val(newVal);
 }
</script>
<title>Insert title here</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="出租单列表" class="easyui-datagrid"
    pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/rent/list" fit="true" toolbar="#tb">
   <thead data-options="frozen:true">
		<tr>
			<th field="cb" checkbox="true" align="center"></th>
            <th data-options="field:'id',width:50, hidden:'true'">ID</th>
            <th data-options="field:'billNo',width:100">出租单号</th>
            <th data-options="field:'stat',width:100">状态</th>
            <th data-options="field:'customerName',width:100">客户名称</th>
			<th data-options="field:'rentMoney',width:100">租金总额</th>
            <th data-options="field:'repoMoney',width:100">押金总额</th>
            <th data-options="field:'rentDay',width:100">租用天数</th>
        </tr>
	</thead>
	<thead>
		<tr>
			<th data-options="field:'customerPhone',width:100">联系电话</th>
            <th data-options="field:'customerAddr',width:150">地址</th>
            <th data-options="field:'customerCard',width:150">证件号</th>
            <th data-options="field:'warehouseId',width:50, hidden:'true'">warehouseId</th>
            <th data-options="field:'warehouseName',width:150">出库仓库</th>
			<th data-options="field:'supplierId',width:150">物流公司</th>
            <th data-options="field:'expressBillNo',width:150">快递单号</th>
            <th data-options="field:'returnBillNo',width:150">归还单号</th>
            <th data-options="field:'beginDate',width:150">使用开始日期</th>
            <th data-options="field:'endDate',width:150">使用结束日期</th>
		</tr>
	</thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openRentMainAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
 		<a href="javascript:openRentMainModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteRent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 		
 		<a href="javascript:openRentDetail()" class="easyui-linkbutton" iconCls="icon-lxr" plain="true">出租明细</a>
 	</div>
 	<div>
 		&nbsp;出租单号：&nbsp;<input type="text" id="s_billNo" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
 		&nbsp;客户名称：&nbsp;<input type="text" id="s_customerName" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
 		<a href="javascript:searchRent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:900px;height:450px;padding: 10px 20px;resizable:true;maximizable:true"
   closed="true" buttons="#dlg-buttons">
   <form id="fm" method="post">
	   	<table cellspacing="8px">
	   		<tr>
	   			<input type="hidden" id="id" name="id">
	   			<td>出租单号：</td>
	   			<td><input type="text" id="billNo" name="billNo" class="easyui-validatebox" readonly="readonly"/></td>

	   			<td>单据状态：</td>
	   			<td>
	   				<input class="easyui-combobox" id="xxstat" name="xxstat" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/billStat/comboList'
	   				,onChange:setStatValue"/>
	   			</td>
	   			<input type="hidden" id="stat" name="stat">
	   		</tr>
	   		<tr>
	   			<td>出库仓库：</td>
	   			<td>
	   				<input class="easyui-combobox" id="xxwarehouse" name="xxwarehouse" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/warehouse/comboList'
	   				,onChange:setWarehouseValue"/>&nbsp;<span class="required">*</span>
	   			</td>
	   			<input type="hidden" id="warehouseId" name="warehouseId">

	   			<td>客户：</td>
	   			<td>
	   				<input type="text" id="customerName" name="customerName" class="easyui-validatebox"  required="true"/>&nbsp;<span class="required">*</span>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>联系电话：</td>
	   			<td>
	   				<input type="text" id="customerPhone" name="customerPhone" class="easyui-validatebox"  required="true"/>&nbsp;<span class="required">*</span>
	   			</td>

	   			<td>证件号：</td>
	   			<td><input type="text" id="customerCard" name="customerCard" class="easyui-validatebox" /></td>
	   			
	   		</tr>
	   		<tr>
	   			<td>联系地址：</td>
	   			<td><input type="text" id="customerAddr" name="customerAddr" class="easyui-validatebox" required="true"/>&nbsp;<span class="required">*</span></td>

	   			<td>物流公司：</td>
	   			<td><input type="text" id="supplierId" name="supplierId" class="easyui-validatebox" required="true"/>&nbsp;<span class="required">*</span></td>
	   		</tr>
	   		<tr>
	   			<td>快递单号：</td>
	   			<td><input type="text" id="expressBillNo" name="expressBillNo" class="easyui-validatebox" required="true"/>&nbsp;<span class="required">*</span></td>

	   			<td>归还单号：</td>
	   			<td>
	   				<input type="text" id="returnBillNo" name="returnBillNo"  class="easyui-validatebox" readonly="readonly" /></font>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>使用开始日期：</td>
	   			<td><input id="beginDate" class="easyui-datebox" name="beginDate" data-options="editable:false"</td>

	   			<td>使用结束日期：</td>
	   			<td><input id="endDate" class="easyui-datebox" name="endDate"  data-options="editable:false"></td>

	   			
	   		</tr>
	   		<tr>
	   			<td>租用天数：</td>
	   			<td><input type="text" id="rentDay" name="rentDay" class="easyui-numberbox" required="true"/></td>

	   			<td>租金总额：</td>
	   			<td><input type="text" id="rentMoney" name="rentMoney" class="easyui-numberbox" /></td>
	   		</tr>
	   		<tr>
	   			<td>押金总额：</td>
	   			<td><input type="text" id="repoMoney" name="repoMoney" class="easyui-numberbox" /></td>
	   		</tr>
	 	</table>
 	</form>
 </div>  		
  
 
 <div id="dlg-buttons">
 	<a href="javascript:saveRent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>