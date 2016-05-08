<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">

    <title>移仓出库单</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ycck.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="移仓出库单列表" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'${pageContext.request.contextPath}/ycck/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:80">单据编号</th>
        <th data-options="field:'statName',width:80">单据状态</th>
        <th data-options="field:'outWarehouseName',width:80">出库仓库</th>
        <th data-options="field:'inWarehouseName',width:80">入库仓库</th>
        <th data-options="field:'total',width:80">移仓总数</th>
        <th data-options="field:'billDate',width:80,formatter:
        function(value,row){ if(value) value = new Date(value);
        return value?value.getFullYear()+'-'+value.getMonth()+'-'+value.getDate():'';}">单据日期
        </th>
        <th data-options="field:'makerName',width:80">制单员</th>
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
<div id="editPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:650px;height:450px;"
     data-options="minimizable: true,maximizable: true,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table cellspacing="8px">
            <tr>
                <td>单据单号</td>
                <td><input class="easyui-textbox" type="text" name="billNo" style="width:173px"
                           data-options="required:true,missingMessage:'必填字段'">
                </td>

                <td>单据日期</td>
                <td>
                    <input id="billDate" class="easyui-datebox" name="billDate"
                           style="width:100px" data-options="editable:false">
                </td>
            </tr>
            <tr>
                <td>单据状态</td>
                <td>
                    <select class="easyui-combobox" name="stat" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/billStat/comboList',required:true,editable:false,missingMessage:'必填字段'">
                    </select>
                </td>

                <td>出库仓库</td>
                <td>
                    <select class="easyui-combobox" name="outWarehouseId" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/warehouse/comboList'">
                    </select>
                </td>
            </tr>
            <tr>
                <td>入库仓库</td>
                <td>
                    <select class="easyui-combobox" name="inWarehouseId" style="width:173px;" data-options="
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/warehouse/comboList'">
                    </select>
                </td>

                <td>移库总数</td>
                <td><input class="easyui-numberbox" type="text" name="total" style="width:173px">
                </td>
                <td></td>
            </tr>
        </table>

    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="移仓出库明细"
               data-options="pagination:'true',rownumbers:true,singleSelect:false,url:'${pageContext.request.contextPath}/ycrkDtl/list',method:'get',toolbar:'#t2_menu'">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'dtlId',width:80,hidden:true">id</th>
                <th data-options="field:'skuName',width:80">Sku</th>
                <th data-options="field:'warehouseName',width:80">出库仓库</th>
                <th data-options="field:'locationName',width:80">出库仓位</th>
                <th data-options="field:'skuAmount',width:80">Sku数量</th>
                <th data-options="field:'outAmount',width:80,align:'right'">出库数量</th>
            </tr>
            </thead>
        </table>
        <div id="t2_menu" style="padding:2px 5px;">
            <a id="btn_t2_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
            <a id="btn_t2_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            <a id="btn_t2_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
            <a id="btn_t2_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div id="t2EditPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:650px;"
             data-options="modal:true,closed:true,buttons: '#t2EditPanel-buttons'">
            <form id="t2EditForm" method="post">
                <input type="hidden" name="dtlId" id="dtlId">
                <!-- <input type="hidden" name="id" id="id"> -->
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <select class="easyui-combobox" name="skuId" style="width:173px;"
                                    data-options="valueField: 'id',textField: 'text',url: '${pageContext.request.contextPath}/sku/comboList',required:true,editable:false,missingMessage:'必填字段'">

                            </select>
                        </td>

                        <td>出库仓库</td>
                        <td>
                            <select class="easyui-combobox" name="warehouseId" style="width:173px;" data-options="
		                    valueField: 'id',
		                    textField: 'text',
		                    url: '${pageContext.request.contextPath}/warehouse/comboList'">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>出库仓位</td>
                        <td>
                            <select class="easyui-combobox" name="locationId" style="width:173px;" data-options="
		                    valueField: 'id',
		                    textField: 'text',
		                    url: '${pageContext.request.contextPath}/warehouseLocation/manageComboList'">
                            </select>
                        </td>

                        <td>Sku库存</td>
                        <td><input class="easyui-numberbox" type="text" name="skuAmount" id="itemPrice"
                                   style="width:173px"
                                   data-options="min:0,required:true"></td>
                    </tr>
                    <tr>
                        <td>出库数量</td>
                        <td><input class="easyui-numberbox" type="text" name="outAmount" id="itemPrice"
                                   style="width:173px"
                                   data-options="min:0,required:true"></td>
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