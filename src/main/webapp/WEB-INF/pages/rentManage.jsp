<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>出租单</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">
        $(function () {
            $.statusDic = {
                <c:forEach items="${statusDic}" var="item" varStatus="status">
                '${item.valueField}': '${item.textField}'<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            };
        });
    </script>
    <script type="text/javascript" src="resources/js/rent.js"></script>
    <script type="text/javascript" src="resources/js/databox-formatter.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="出租单" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'rent/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:105">出租单号</th>
        <th data-options="field:'stat',width:80,formatter:function(value,row){ return $.statusDic[String(value)];}">
            出租状态
        </th>
        <th data-options="field:'billStat',hidden:true">单据状态</th>
        <th data-options="field:'billStatName',width:80">单据状态</th>
        <th data-options="field:'warehouseName',width:80">出库仓库</th>
        <th data-options="field:'rentDay',width:60">租用天数</th>
        <th data-options="field:'rentMoney',width:70">租金总金额</th>
        <th data-options="field:'repoMoney',width:60">押金总额</th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th data-options="field:'customerName',width:100">客户</th>
        <th data-options="field:'customerPhone',width:100">联系电话</th>
        <th data-options="field:'customerCard',width:100">证件号</th>
        <th data-options="field:'customerAddr',width:100">地址</th>
        <th data-options="field:'supplierId',width:100,hidden:true">物流Id</th>
        <th data-options="field:'supplierName',width:100">物流公司</th>
        <th data-options="field:'expressBillNo',width:100">快递单号</th>
        <th data-options="field:'returnBillNo',width:100">出租快递单号</th>
        <th data-options="field:'beginDate',width:80,hidden:true">
            使用开始时间
        </th>
        <th data-options="field:'beginDateStr',width:80,formatter:function(value,row){ if(row.beginDate) {return new Date(row.beginDate).format('yyyy-MM-dd')};}">
            使用开始时间
        </th>
        <th data-options="field:'endDate',width:80,hidden:true">
            使用结束时间
        </th>
        <th data-options="field:'endDateStr',width:80,formatter:function(value,row){ if(row.beginDate) {return new Date(row.beginDate).format('yyyy-MM-dd')};}">
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
<div id="editPanel" class="easyui-dialog edit-panel" title="编辑" style="height: 650px;height: 500px;"
     data-options="minimizable: false,maximizable: false,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table>
            <tr>
                <td class="label">出租单号</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" name="billNo"
                           id="billNo" readonly>
                </td>
                <td class="label">单据状态</td>
                <td>
                    <select class="easyui-combobox input" name="billStat" id="billStat" style="width:120px;"
                            data-options="
                    disabled:true,
                    valueField: 'valueField',
                    textField: 'textField',
                    data:[{valueField:'0',textField:'未审核'},{valueField:'1',textField:'已审核'}]">
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">出租状态</td>
                <td colspan="2">
                    <select class="easyui-combobox input" name="stat" id="stat" style="width:120px;"
                            data-options="
                            disabled:true,
                            valueField: 'valueField',
                            textField: 'textField',
                            method:'get',
                            url: 'dic/rentStatus'">
                    </select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td class="label">客户</td>
                <td><input class="easyui-textbox input" type="text" name="customerName">
                </td>
                <td class="label">证件号</td>
                <td><input class="easyui-textbox input" type="text" name="customerCard">
            </tr>
            <tr>
                <td class="label">联系电话</td>
                <td><input class="easyui-textbox input" type="text" name="customerPhone">
                </td>
                </td>
                <td class="label">地址</td>
                <td colspan="3"><input class="easyui-textbox input" type="text" name="customerAddr"></td>
            </tr>
            <tr>

                <td class="label">出库仓库</td>
                <td>
                    <select class="easyui-combobox input" name="warehouseId" id="warehouseId" data-options="
                    required:true,
                    editable:false,
                    valueField: 'id',
                    textField: 'text',
                    method:'get',
                    url: 'warehouse/comboList',
                    onSelect:function(ret){
                        var rows = $('#t2_dg').datagrid('getRows');
                        if(rows&&rows.length>0){
                            $.messager.confirm('系统提示','重新选择仓库需要重新填写出租明细,是否继续?？',function(r){
                                if (r){
                                    $('#t2_dg').datagrid('loadData',{total:0,rows:[]});
                                }
                            });
                        }
                    }">
                    </select>
                </td>
                <td class="label">物流供应商</td>
                <td>
                    <select class="easyui-combobox input" name="supplierId" data-options="
                    editable:false,
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/supplier/comboList'">
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">出租快递单号</td>
                <td><input class="easyui-textbox input" type="text" name="expressBillNo">
                </td>
                <td class="label">归还快递单号</td>
                <td><input class="easyui-textbox input" type="text" name="returnBillNo">
                </td>

            </tr>
            <tr>
                <td class="label">使用开始时间</td>
                <td><input id="beginDate" class="easyui-datebox input" name="beginDate"
                           data-options="editable:false,required:true">
                </td>


                <td class="label">使用结束时间</td>
                <td><input id="endDate" class="easyui-datebox input" name="endDate"
                           data-options="editable:false,required:true">
                </td>
            </tr>
            <tr>
                <td class="label">租用天数</td>
                <td><input id="days" class="easyui-numberbox input" type="text" name="rentDay" readonly>
                </td>
                <td class="label">制单时间</td>
                <td><input id="create_time" class="easyui-datebox input" name="create_time"
                           data-options="editable:false">
                </td>
            </tr>
            <tr>
                <td class="label">租金总金额</td>
                <td><input class="easyui-numberbox input" readonly type="text" name="rentMoney" id="rentMoney">
                </td>
                <td class="label">押金总额</td>
                <td><input class="easyui-numberbox input" readonly type="text" name="repoMoney" id="repoMoney">
                </td>
            </tr>
            <tr>
                <td class="label">操作员</td>
                <td><input class="easyui-textbox input" readonly type="text" name="createdBy" id="createdBy">
                </td>
                </td>
            </tr>
        </table>
    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="出租明细"
               data-options="pagination:false,rownumbers:true,singleSelect:false,method:'get',toolbar:'#t2_menu'">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'dtlId',width:80,hidden:true">dtlId</th>
                <th data-options="field:'skuId',width:80">SKU</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'itemPrice',width:80,align:'right'">单价</th>
                <th data-options="field:'itemAmount',width:60,align:'right'">数量</th>
                <th data-options="field:'itemRent',width:60">金额</th>
                <th data-options="field:'itemRepo',width:60,align:'center'">押金</th>

            </tr>
            </thead>
        </table>
        <div id="t2_menu" style="padding:2px 5px;">
            <%--<a id="btn_t2_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>--%>
            <a id="btn_t2_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            <a id="btn_t2_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
            <a id="btn_t2_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div id="t2EditPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:700px;"
             data-options="modal:true,closed:true,buttons: '#t2EditPanel-buttons'">
            <form id="t2EditForm">
                <input type="hidden" name="statName" id="statName">
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <select class="easyui-combobox" name="skuId" id="skuId" style="width:150px;">
                            </select>
                        </td>

                        <td>商品名称</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="itemName"
                                   style="width:150px">
                        </td>
                        <td rowspan="5"><img id="skuImage" src="resources/images/upload/example.jpg"
                                             style="width: 200px;height: 200px"></td>
                    </tr>
                    <tr>
                        <td>颜色</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="colorName"
                                   style="width:150px">
                        </td>
                        <td>尺码</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="sizeDtlName"
                                   style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>数量</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="itemAmount" id="itemAmount"
                                   style="width:150px"
                                   data-options="min:1,precision:0,required:true,missingMessage:'请填写数量',
                               onChange:function(newValue,oldValue){
                                    var itemPrice = $('#itemPrice').numberbox('getValue');
                                    $('#itemRent').numberbox('setValue',newValue*itemPrice);
                                }">
                        </td>
                        <td>当前库存</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="amount"
                                   style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>单价</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="itemPrice" id="itemPrice"
                                   style="width:150px"
                                   data-options="min:0,precision:2,required:true,
                               onChange:function(newValue,oldValue){
                                    var amount= $('#itemAmount').numberbox('getValue');
                                    $('#itemRent').numberbox('setValue',newValue*amount);
                                }">
                        </td>
                        <td></td>
                        <td></td>


                    </tr>
                    <tr>
                        <td>金额</td>
                        <td><input class="easyui-numberbox" data-options="disabled:true" type="text" name="itemRent"
                                   id="itemRent"
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