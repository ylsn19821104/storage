/**
 * Created by luopotaotao on 2016/4/18.
 */
$(function () {
    bindHandlers();
    bindT2Handlers();
    var t1Url = '/rent';
    var t2Url = '/rentDtl'

    function bindHandlers() {
        loadDic();
        $('#btn_add').bind('click', toAdd);
        $('#btn_edit').bind('click', edit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
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

    function loadDic() {
        $.ajax({
            url: '/dic/rentStatus',
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
        $('#editPanel').dialog('open');
    }

    function edit() {
        var rows = $('#dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                currentItem = rows[0];
                var url = t1Url + '/findById';
                $.ajax({
                    url: url,
                    data: {id: rows[0]['id']},
                    dataType: 'json',
                    type: 'get'
                }).success(function (ret) {
                    if (ret && ret['id']) {
                        $('#t2_dg').datagrid('loadData', {total: 0, rows: []});
                    }
                    t2Query();
                    if (ret.beginDate) {
                        var value = new Date(ret.beginDate);
                        ret.beginDate = value.getFullYear() + '-' + (value.getMonth() + 1) + '-' + value.getDate();
                    }
                    if (ret.endDate) {
                        var value = new Date(ret.endDate);
                        ret.endDate = value.getFullYear() + '-' + (value.getMonth() + 1) + '-' + value.getDate();
                    }
                    $('#editForm').form('load', ret);
                    $(ret).each(function (i, val) {
                        $('#' + i).val(val);
                    });
                    $('#editPanel').dialog('open');
                }).error(function (err) {
                    $.messager.alert('系统提示!', '获取数据!请重新尝试或联系管理员!');
                });
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
                if (name) {
                    $.currentItem[name] = $(val).val();
                }
            });
            var details = $('#t2_dg').datagrid('getChanges');
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
                    //TODO 关闭
                    //$('#editForm').form('clear');
                    //$('#editPanel').dialog('close');
                    //query();
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
    }

    function t2Query() {
        var url = t2Url + '/findAllById?id=' + currentItem['id'];
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