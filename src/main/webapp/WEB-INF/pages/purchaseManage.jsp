<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>采购单列表</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/purchase.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="采购单" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'${pageContext.request.contextPath}/purchase/list',method:'get',toolbar:'#menu'">
    <thead>
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:80">采购单号</th>
        <th data-options="field:'statName',width:80">单据状态</th>
        <th data-options="field:'billDate',width:80,formatter:
        function(value,row){ if(value) value = new Date(value);
        return value?value.getFullYear()+'-'+value.getMonth()+'-'+value.getDate():'';}">单据日期
        </th>
        <th data-options="field:'supplierName',width:80">供应商</th>
        <th data-options="field:'warehouseName',width:80">入库仓库</th>
        <th data-options="field:'total',width:80">采购总金额</th>
    </tr>
    </thead>
</table>
<div id="menu" style="padding:2px 5px;">
    <a id="btn_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    <a id="btn_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a id="btn_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a id="btn_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<%--以下为编辑面板的内容--%>
<div id="editPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:700px;height:450px;"
     data-options="minimizable: true,maximizable: true,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table cellspacing="8px">
            <tr>
                <td>采购单号：</td>
                <td><input class="easyui-textbox" type="text" name="billNo" style="width:173px"
                           data-options="required:true,missingMessage:'必填字段'">
                </td>

                <td>单据日期：</td>
                <td>
                    <input id="billDate" class="easyui-datebox" name="billDate"
                           style="width:173px" data-options="editable:false">
                </td>
            </tr>
            <tr>
                <td>单据状态：</td>
                <td>
                    <select class="easyui-combobox" name="stat" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    
                    url: '${pageContext.request.contextPath}/billStat/comboList',required:true,editable:false,missingMessage:'必填字段'">
                    </select>
                </td>

                <td>入库仓库：</td>
                <td>
                    <select class="easyui-combobox" name="warehouseId" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/warehouse/comboList'">
                    </select>
                </td>
            </tr>

            <tr>
                <td>入库数量：</td>
                <td><input class="easyui-numberbox" type="text" name="total" id="total" style="width:173px"
                           data-options="min:1,required:true"></td>

                <td>供应商：</td>
                <td>
                    <select class="easyui-combobox" name="supplierId" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/supplier/comboList'">
                    </select>

                </td>
            </tr>
        </table>

    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="采购明细"
               data-options="pagination:'true',rownumbers:true,singleSelect:false,url:'${pageContext.request.contextPath}/purchaseDtl/list',method:'get',toolbar:'#t2_menu'">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'dtlId',width:80,hidden:true">id</th>
                <th data-options="field:'itemName',width:80">SKU</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'itemPrice',width:80,align:'right'">单价</th>
                <th data-options="field:'itemAmount',width:80,align:'right'">数量</th>
                <th data-options="field:'itemTotal',width:80">金额</th>
            </tr>
            </thead>
        </table>
        <div id="t2_menu" style="padding:2px 5px;">
            <a id="btn_t2_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
            <a id="btn_t2_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            <a id="btn_t2_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
            <a id="btn_t2_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div id="t2EditPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:550px;"
             data-options="modal:true,closed:true,buttons: '#t2EditPanel-buttons'">
            <form id="t2EditForm" method="post">
                <input type="hidden" name="dtlId" id="dtlId">
                <!-- <input type="hidden" name="id" id="id"> -->
                <input type="hidden" name="statName" id="statName">
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <select class="easyui-combobox" name="skuId" style="width:150px;"
                                    data-options="valueField: 'id',textField: 'text',
                                    url: '${pageContext.request.contextPath}/sku/comboList',required:true,editable:false,missingMessage:'必填字段'">

                            </select>
                        </td>

                        <td>商品名称</td>
                        <td>
                            <input class="easyui-textbox" name="itemName" id="itemName" style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>单价</td>
                        <td><input class="easyui-numberbox" type="text" name="itemPrice" id="itemPrice"
                                   style="width:150px"
                                   data-options="min:0,precision:2,required:true,onChange:function(newValue,oldValue){
                        	var amount= $('#itemAmount').numberbox('getValue');
                        	$('#itemTotal').numberbox('setValue',newValue*amount);
                        }"></td>

                        <td>数量</td>
                        <td><input class="easyui-numberbox" type="text" name="itemAmount" id="itemAmount"
                                   style="width:150px"
                                   data-options="min:1,precision:0,required:true,onChange:function(newValue,oldValue){
                        	var itemPrice = $('#itemPrice').numberbox('getValue');
                        	$('#itemTotal').numberbox('setValue',newValue*itemPrice);
                        }"></td>

                    </tr>
                    <tr>
                        <td>金额</td>
                        <td><input class="easyui-numberbox" type="text" name="itemTotal" id="itemTotal"
                                   style="width:150px"></td>

                    </tr>

                </table>
            </form>
            <div id="t2EditPanel-buttons">
                <a id="btn_t2_edit_save" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
                <a id="btn_t2_edit_close" href="javascript:void(0)" class="easyui-linkbutton">取消</a>
            </div>
        </div>
    </div>
</div>
<div id="editPanel-buttons">
    <a id="btn_edit_save" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
    <a id="btn_edit_close" href="javascript:void(0)" class="easyui-linkbutton">取消</a>
</div>

</body>
</html>