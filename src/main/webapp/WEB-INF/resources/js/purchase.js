$(function () {
    bindHandlers();
    bindT2Handlers();
    var t1Url = '/storage/purchase';
    var t2Url = '/storage/purchaseDtl'

    function bindHandlers() {
        loadDic();
        $('#btn_add').bind('click', toAdd);
        $('#btn_edit').bind('click', toEdit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
        $('#btn_finish').bind('click', finish);
        $('#btn_unfinish').bind('click',unfinish);
    }

    function finish(){
        $('#btn_finish').unbind();
        setTimeout(function () {
            $('#btn_finish').bind(finish);
        },1000);
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        var warehouseIds=[];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if(v['stat']!=1){
                    ids.push(v['id']);
                    var warehouseId= v['warehouseId'];
                    warehouseIds.push(warehouseId);
                }
            });
        }
        if(ids&&ids.length>0){
            $.ajax({
                url:t1Url+'/finish',
                type:'POST',
                dataType:'json',
                data:{ids:ids,warehouseIds:warehouseIds}
            }).success(function (ret) {
                if(ret&&ret.flag){
                    $.messager.alert('系统提示','审核完成!');
                    query();
                }else{
                    $.messager.alert('系统提示','操作失败,请重新尝试或联系管理员!');
                }
            }).error(function (e) {
                $.messager.alert('系统提示','操作失败,请重新尝试或联系管理员!');
            });
        }else{
            $.messager.alert('系统提示','请选择未完成的条目进行审核!');
        }
    }
    
    function unfinish(){
        $('#btn_unfinish').unbind();
        setTimeout(function () {
            $('#btn_unfinish').bind(unfinish);
        },1000);
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        var warehouseIds=[];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if(v['stat']!=0){
                    ids.push(v['id']);
                    warehouseIds.push(v['warehouseId']);
                }
            });
        }
        if(ids&&ids.length>0){
            $.ajax({
                url:t1Url+'/unfinish',
                type:'POST',
                dataType:'json',
                data:{ids:ids,warehouseIds:warehouseIds}
            }).success(function (ret) {
                if(ret&&ret.flag){
                    $.messager.alert('系统提示','取消审核完成!');
                    query();
                }else{
                    $.messager.alert('系统提示','操作失败,请重新尝试或联系管理员!');
                }
            }).error(function (e) {
                $.messager.alert('系统提示','操作失败,请重新尝试或联系管理员!');
            });
        }else{
            $.messager.alert('系统提示','请选择未完成的条目进行审核!');
        }
    }
    
    function loadDic() {
        $.ajax({
            url: '/storage/dic/billStatus',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.length) {
                var dic = {};
                $(ret).each(function (i, val) {
                    dic[val.valueField] = val.textField;
                })
                $.statusDic = dic;
            } else {
                $.messager.alert('系统提示', '加载数据字典失败,请刷新页面或联系管理员!');
            }
        }).error(function () {
            $.messager.alert('系统提示', '加载数据字典失败,请刷新页面或联系管理员!');
        });
    }

    function bindT2Handlers() {
        $('#btn_t2_add').bind('click', t2ToAdd);
        $('#btn_t2_edit').bind('click', t2ToEdit);
        $('#btn_t2_query').bind('click', t2Query);
        $('#btn_t2_remove').bind('click', t2Remove);
        $('#btn_t2_edit_save').bind('click', t2Save);
        $('#btn_t2_edit_close').bind('click', t2CloseEditPanel);
    }

    function toAdd() {
        $.currentItem = {type:'new'};
        $('#editForm').form('clear');
        $('#stat').combobox('setValue',0);
        $("#t2_dg").datagrid('loadData',{total:0,rows:[]});
        $('#editPanel').dialog('open');
    }

    function formatDate(obj,names){
        function format(name) {
            obj[name] = (obj&&obj[name])?new Date(obj[name]).format("yyyy-MM-dd"):null;
        }
        for(var i in names){
            obj[names[i]] = format(names[i]);
        }
    }
    function toEdit() {
        var rows = $('#dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                var row = rows[0];

                formatDate(row,['billDate','create_time','update_time']);

                $.currentItem = row;

                $('#editForm').form('load', $.currentItem);
                t2Query();
                $('#editPanel').dialog('open');

            }
        }

        else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function remove() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                ids.push(v['id']);
            });
        }
        if (ids.length > 0) {
            $.ajax({
                url: t1Url + '/delete',
                data: {ids: ids},
                dataType: 'json',
                type: 'post'
            }).success(function (ret) {
                if (ret && ret.flag > 0) {
                    $.messager.alert('系统提示!', '删除成功!');
                    query();
                } else {
                    $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                }
            }).error(function (err) {
                $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
            });
        }
    }

    function query() {
        var url = t1Url + '/list';
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.rows) {
                $('#dg').datagrid('loadData', ret);
            } else {
                $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
            }
        }).error(function (err) {
            $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
        });
    }

    function save() {
        if ($('#editForm').form('validate')) {
            $('#editForm input').each(function (i, val) {
                var name = $(val).attr('name');
                if (name&&$(val).val()) {
                    $.currentItem[name] = $(val).val();
                }
            });
            var details = $('#t2_dg').datagrid('getData').rows;
            $(details).each(function (i,val) {
                if(val.dtlId<0){
                    delete val.dtlId;
                }
            })
            if (details)
                $.currentItem.details = JSON.stringify(details);
            delete $.currentItem.type;
            $.ajax({
                url: t1Url + '/save',
                type: 'post',
                data: $.currentItem
            }).success(function (ret) {
                if (ret && ret.flag) {
                    $.messager.alert('系统提示!', '保存成功!');
                    //如果是修改的话不关闭当前页面,新增的话才关闭
                    if($.currentItem.id){
                        $.get(t1Url+'/findById',{id:$.currentItem.id},function (data) {
                            formatDate(data,['billDate','create_time','update_time']);
                            $.currentItem = data;
                            $('#editForm').form('load', data);
                        });
                        query();
                        t2Query();
                    }else{
                        $('#editForm').form('clear');
                        $('#editPanel').dialog('close');
                    }

                    query();
                } else {
                    $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                }
            }).error(function (e) {
                $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
            }).complete(function (e) {
                $.currentItem = {};
                $.messager.progress('close');
            });
        }


    }

    function t2ToAdd() {
        $.t2CurrentItem = {dtlId:-(new Date().getTime()), type: 'new'};
        $('#t2EditPanel').dialog('open');
        $('#t2EditForm').form('clear');
        $('#t2EditForm').form('load', {dtlId: $.t2CurrentItem});
    }

    function t2ToEdit() {
        var rows = $('#t2_dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                $('#t2EditForm').form('clear');
                $.t2CurrentItem = rows[0];
                $('#t2EditForm').form('load', $.t2CurrentItem);
                $('#t2EditPanel').dialog('open');
            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }
    
    function calcTotal(){
    	var rows = $('#t2_dg').datagrid('getRows');
    	var total=null;
    	
    	if(rows && rows.length){
    		total=0;
    		$.each(rows,function(i,row){
    			var itemTotal = parseFloat(row['itemTotal']);
    			total+=(isNaN(itemTotal)? 0 :itemTotal);
    		});
    	}
    	
    	$('#total').val(total);
    }
    
    function t2Save() {
        if ($('#t2EditForm').form('validate')) {
            var item = $.t2CurrentItem;
            if (item) {
                var type = item.type
                $('#t2EditForm input').each(function (i, val) {
                    var key = $(val).attr('name');
                    if (key) {
                        item[key] = $(val).val();
                    }
                });
                if (item.type) {
                    delete item.type;
                    $('#t2_dg').datagrid('appendRow', item);
                } else {
                    var index = $('#t2_dg').datagrid('getRowIndex', item);
                    $('#t2_dg').datagrid('updateRow', {index: index, row: item});
                }
            }
            
            calcTotal();
            delete $.t2CurrentItem;
            $('#t2EditForm').form('clear');
            $('#t2EditPanel').dialog('close');
        }
    }

    function t2Remove() {
        var grid = $('#t2_dg');
        var rows = grid.datagrid('getChecked');

        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                var index = grid.datagrid('getRowIndex', v);
                grid.datagrid('deleteRow', index);
            });
        }
        
        calcTotal();
    }

    function t2Query() {
        var url = t2Url + '/findAllById?id=' + $.currentItem['id'];
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.rows) {
                $('#t2_dg').datagrid('loadData', ret);
            } else {
                $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
            }
        }).error(function (err) {
            $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
        });
    }

    function closeEditPanel() {
        $('#editPanel').dialog('close');
    }

    function t2CloseEditPanel() {
        $.currentItem = null;
        $('#t2EditPanel').dialog('close');
    }
})
;