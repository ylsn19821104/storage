/**
 * Created by luopotaotao on 2016/4/18.
 */
$(function () {
    bindHandlers();
    bindT2Handlers();
    var t1Url = 'rent';
    var t2Url = 'rentDtl'

    function bindHandlers() {
        $('a.easyui-linkbutton').unbind();
        // loadDic();
        $('#btn_add').bind('click', toAdd);
        $('#btn_edit').bind('click', toEdit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_finish').bind('click', finish);

        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
        $('#start_time').datebox({
            onSelect: function (date) {
                var end = $('#end_time').datebox('getValue');
                if (date && end) {
                    end = new Date(end);
                    if (date.getTime() > end.getTime()) {
                        $.messager.alert('系统提示!', '开始时间不能大于开始时间!')
                        $(this).datebox('setValue', null);
                        ('#days').numberbox('setValue', null);
                        return;
                    }

                    var interval = date.getTime() - (new Date(end)).getTime();
                    $('#days').numberbox('setValue', (interval).toFixed(2) / 86400000 + 1);
                }
            }
        });
        $('#end_time').datebox({
            onSelect: function (date) {
                var start = $('#start_time').datebox('getValue');

                if (date && start) {
                    start = new Date(start);
                    if (start.getTime() > date.getTime()) {
                        $.messager.alert('系统提示!', '结束时间不能小于开始时间!')
                        $(this).datebox('setValue', null);
                        ('#days').numberbox('setValue', null);
                        return;
                    }
                    var interval = date.getTime() - start.getTime();
                    $('#days').numberbox('setValue', ((interval).toFixed(2) / 86400000) + 1);
                }
            }
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
        setEditable(true);
        $.currentItem = {type: 'new'};
        $('#editForm').form('clear');
        $('#editPanel').dialog('open');
    }

    function formatDate(obj, names) {
        function format(name) {
            obj[name] = (obj && obj[name]) ? new Date(obj[name]).format("yyyy-MM-dd") : null;
        }

        for (var i in names) {
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

                formatDate(row, ['beginDate', 'endDate', 'create_time', 'update_time']);

                $.currentItem = row;

                $('#editForm').form('load', $.currentItem);
                setEditable($.currentItem.stat!=6);//已完成状态不可编辑
                t2Query();
                $('#editPanel').dialog('open');

            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function setEditable(flag) {
        if(flag){
            $('#btn_edit_save').linkbutton('enable');
            $('#t2_menu a').linkbutton('enable');
            bindHandlers();
            bindT2Handlers();
        }else{
            $('#btn_edit_save').linkbutton('disable');
            $('#btn_edit_save').unbind('click');
            $('#t2_menu a').linkbutton('disable');
            $('#t2_menu').unbind('click');
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
        $('#btn_edit_save').unbind();
        setTimeout(function () {
            $('#btn_edit_save').bind(save);
        },1000);
        if ($('#editForm').form('validate')) {
            $('#editForm input').each(function (i, val) {
                var name = $(val).attr('name');
                if (name && $(val).val()) {
                    $.currentItem[name] = $(val).val();
                }
            });
            var details = $('#t2_dg').datagrid('getData').rows;
            $(details).each(function (i, val) {
                if (val.dtlId < 0) {
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
                    if ($.currentItem.id) {
                        $.get(t1Url + '/findById', {id: $.currentItem.id}, function (data) {
                            formatDate(data, ['beginDate', 'endDate', 'create_time', 'update_time']);
                            $.currentItem = data;
                            $('#editForm').form('load', data);
                        });
                        query();
                        t2Query();
                    } else {
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
    function finish(){
        $('#btn_finish').unbind();
        setTimeout(function () {
            $('#btn_finish').bind(finish);
        },1000);
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if(v['stat']!=6){
                    ids.push(v['id']);
                }
            });
        }
        if(ids&&ids.length>0){
            $.ajax({
                url:t1Url+'/finish',
                type:'post',
                dataType:'json',
                data:{ids:ids}
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
    function t2ToAdd() {
        $.t2CurrentItem = {dtlId: -(new Date().getTime()), type: 'new'};
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

    function calcTotal() {
        var rows = $('#t2_dg').datagrid('getRows');
        var rentMoney = null;
        var repoMoney = null;
        if (rows && rows.length) {
            rentMoney = 0;
            repoMoney = 0;
            $.each(rows, function (i, row) {
                var itemRent = parseFloat(row['itemRent']);
                var itemRepo = parseFloat(row['itemRepo']);
                rentMoney += (isNaN(itemRent) ? 0 : itemRent)
                repoMoney += (isNaN(itemRepo) ? 0 : itemRepo)
            });

        }
        $('#rentMoney').val(rentMoney);
        $('#repoMoney').val(repoMoney);
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