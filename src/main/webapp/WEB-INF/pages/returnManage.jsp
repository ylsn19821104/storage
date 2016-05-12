<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>归还单</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">
        $(function(){
            $.statusDic ={
                <c:forEach items="${statusDic}" var="item" varStatus="status">
                    '${item.valueField}':'${item.textField}'<c:if test="${!status.last}"> , </c:if>
                </c:forEach>
            };
        });
    </script>
    <script type="text/javascript" src="resources/js/return.js"></script>
    <script type="text/javascript" src="resources/js/databox-formatter.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="归还单" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'return/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:105">归还单号</th>
        <th data-options="field:'stat',width:80,formatter:function(value,row){ return $.statusDic[String(value)];}">归还状态
        </th>
        <th data-options="field:'warehouseName',width:80">出库仓库</th>
        <th data-options="field:'returnDay',width:60">租用天数</th>
        <th data-options="field:'returnMoney',width:70">租金总金额</th>
        <th data-options="field:'repoMoney',width:60">押金总额</th>
        <th data-options="field:'damageMoney',width:60">赔偿金总额</th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th data-options="field:'customerName',width:100">客户</th>
        <th data-options="field:'customerPhone',width:100">联系电话</th>
        <th data-options="field:'customerCard',width:100">证件号</th>
        <th data-options="field:'customerAddr',width:100">地址</th>
        <th data-options="field:'supplierId',width:100">物流公司</th>
        <th data-options="field:'expressBillNo',width:100">快递单号</th>
        <th data-options="field:'returnBillNo',width:100">归还单号</th>
        <th data-options="field:'beginDate',width:80,formatter:function(value,row){ if(value) value = new Date(value);return value?value.getFullYear()+'-'+value.getMonth()+'-'+value.getDate():'';}">
            使用开始时间
        </th>
        <th data-options="field:'endDate',width:80,formatter:function(value,row){ if(value) value = new Date(value);return value?value.getFullYear()+'-'+value.getMonth()+'-'+value.getDate():'';}">
            使用结束时间
        </th>

    </tr>
    </thead>
</table>
<div id="menu" style="padding:2px 5px;">
    <a id="btn_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    <a id="btn_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a id="btn_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a id="btn_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a id="btn_finish" href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核</a>
</div>
<%--以下为编辑面板的内容--%>
<div id="editPanel" class="easyui-dialog edit-panel" title="编辑"
     data-options="minimizable: true,maximizable: true,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table>
            <tr>
                <td class="label">归还单号</td>
                <td><input class="easyui-textbox input" data-options="editable:false" type="text" name="billNo"
                           id="billNo" readonly>
                </td>
                <td class="label">归还状态</td>
                <td colspan="2">
                    <select class="easyui-combobox input" name="stat" style="width:120px;" data-options="
                    valueField: 'valueField',
                    textField: 'textField',
                    method:'get',
                    url: 'dic/returnStatus'">
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">出库仓库</td>
                <td>
                    <select class="easyui-combobox input" name="warehouseId" data-options="
                    valueField: 'id',
                    textField: 'text',
                    method:'get',
                    url: 'warehouse/comboList'">
                    </select>
                </td>
                <td class="label">客户</td>
                <td><input class="easyui-textbox input" type="text" name="customerName">
                </td>
            </tr>
            <tr>
                <td class="label">联系电话</td>
                <td><input class="easyui-textbox input" type="text" name="customerPhone">
                </td>
                <td class="label">证件号</td>
                <td><input class="easyui-textbox input" type="text" name="customerCard">
            </tr>
            <tr>
                </td>
                <td class="label">地址</td>
                <td colspan="3"><input class="easyui-textbox input" type="text" name="customerAddr"
                                       style="width:397px"></td>
            </tr>
            <tr>
                <td class="label">物流供应商</td>
                <td>
                    <select class="easyui-combobox input" name="supplierId" data-options="
                    editable:false,
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/supplier/comboList'">
                    </select>
                </td>
                <td class="label">快递单号</td>
                <td><input class="easyui-textbox input" type="text" name="expressBillNo">
                </td>
            </tr>
            <tr>
                <td class="label">归还单号</td>
                <td><input class="easyui-textbox input" type="text" name="returnBillNo">
                </td>
            </tr>
            <tr>
                <td class="label">使用开始时间</td>
                <td><input id="start_time" class="easyui-datebox input" name="beginDate"
                           data-options="editable:false">
                </td>
                <td></td>
                <td colspan="3" rowspan="5">SKU图片</td>
            </tr>
            <tr>
                <td class="label">使用结束时间</td>
                <td><input id="end_time" class="easyui-datebox input" name="endDate"
                           data-options="editable:false">
                </td>
                <td></td>
            </tr>
            <tr>
                <td class="label">租用天数</td>
                <td><input id="days" class="easyui-numberbox input" type="text" name="returnDay" readonly>
                </td>
                <td></td>
            </tr>
            <tr>
                <td class="label">租金总金额</td>
                <td><input class="input" readonly type="text" name="returnMoney" id="returnMoney">
                </td>
                <td></td>
            </tr>
            <tr>
                <td class="label">押金总额</td>
                <td><input class="input" readonly type="text" name="repoMoney" id="repoMoney">
                </td>
                <td></td>
            </tr>
            <input type="hidden" name="details" id="details"/>
        </table>
    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="归还明细"
               data-options="pagination:'false',rownumbers:true,singleSelect:false,method:'get',toolbar:'#t2_menu'">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'dtlId',width:80,hidden:true">dtlId</th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'skuId',width:80">SKU</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'itemPrice',width:80,align:'right'">单价</th>
                <th data-options="field:'itemAmount',width:60,align:'right'">数量</th>
                <th data-options="field:'itemRent',width:60">金额</th>
                <th data-options="field:'itemRepo',width:60,align:'center'">押金</th>
                <th data-options="field:'statName',width:60,align:'center',formatter:function(value,row){
							return $.statusDic[row.stat];
						}">状态
                </th>
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
            <form id="t2EditForm">
                <input type="hidden" name="statName" id="statName">
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <select class="easyui-combobox" name="skuId" id="skuId" style="width:150px;"
                                    data-options="valueField: 'id',
                                    textField: 'text',
                                    url: 'sku/comboList',
                                    required:true,missingMessage:'必填字段',
                                    formatter: function(row){
                                        var opts = $(this).combobox('options');
                                        return row[opts.valueField]+' '+row[opts.textField];
                                    },
                                    onHidePanel:function(){
                                        var val = $('input[name=skuId]').val();
                                        if(!val){
                                            return;
                                        }

                                        var opt = $(this).combobox('options');
                                        var data =$(this).combobox('getData');
                                        var contains = false;
                                        for(var i=0;i<data.length;i++){
                                            if(data[i][opt.valueField]==val){
                                            $(this).combobox('setValue',val+' '+data[i][opt.textField]);
                                                return;
                                            }
                                        }
                                        if(!contains){
                                            $.messager.alert('系统提示','只能从下拉框中选择值!');
                                            $(this).combobox('reset');
                                        }
                                    }

                                    ">
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
                        	$('#itemRent').val(newValue*amount);
                        }"></td>

                        <td>数量</td>
                        <td><input class="easyui-numberbox" type="text" name="itemAmount" id="itemAmount"
                                   style="width:150px"
                                   data-options="min:1,precision:0,required:true,onChange:function(newValue,oldValue){
                        	var itemPrice = $('#itemPrice').numberbox('getValue');
                        	$('#itemRent').val(newValue*itemPrice);
                        }"></td>

                    </tr>
                    <tr>
                        <td>金额</td>
                        <td><input type="text" name="itemRent" id="itemRent"
                                   style="width:150px"></td>

                        <td>押金</td>
                        <td><input class="easyui-numberbox" type="text" name="itemRepo" style="width:150px">
                        </td>
                    </tr>
                </table>
            </form>
            <div id="t2EditPanel-buttons">
                <a id="btn_t2_edit_save" href="javascript:void(0)" class="easyui-linkbutton">确定</a>
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