/**
 * Created by luopotaotao on 2016/4/18.
 */
$(function () {
    bindHandlers();
    bindT2Handlers();
    var t1Url = '/storage/ycrk';
    var t2Url = '/storage/ycrkDtl'
    var currentItem = null;

    function bindHandlers() {
        $('#btn_add').bind('click', add);
        $('#btn_edit').bind('click', edit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
    }

    function bindT2Handlers() {
        $('#btn_t2_add').bind('click', t2Add);
        $('#btn_t2_edit').bind('click', t2Edit);
        $('#btn_t2_query').bind('click', t2Query);
        $('#btn_t2_remove').bind('click', t2Remove);
        $('#btn_t2_edit_save').bind('click', t2Save);
        $('#btn_t2_edit_close').bind('click', t2CloseEditPanel);
    }

    function add() {
        currentItem = null;
        switchAddOrEdit(true);
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
                var url = t1Url + '/findById.do';
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
                    if(ret.beginDate){
                        var value = new Date(ret.beginDate);
                        //ret.beginDate = (value.getMonth()+1)+'/'+value.getDate()+'/'+value.getFullYear();
                        ret.beginDate = value.getFullYear()+'-'+(value.getMonth()+1)+'-'+value.getDate();
                    }
                    if(ret.endDate){
                        var value = new Date(ret.endDate);
                        //ret.endDate = (value.getMonth()+1)+'/'+value.getDate()+'/'+value.getFullYear();
                        ret.endDate = value.getFullYear()+'-'+(value.getMonth()+1)+'-'+value.getDate();
                    }
                    //ret.c12 = null;
                    //ret.c13 = null;
                    $('#editForm').form('load', ret);
                    $(ret).each(function (i, val) {
                        $('#' + i).val(val);
                    });
                    $('#editPanel').dialog('open');
                    switchAddOrEdit(false);
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
                url: t1Url + '/delete.do',
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
    	var url = t1Url + '/list.do';
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
            $('#editForm').form('submit', {
                    url: t1Url + (currentItem ? '/update.do' : '/save.do'),
                    ajax: false,
                    contentType: "application/json",
                    onSubmit: function () {
                        $.messager.progress();
                        return true;	// 返回false终止表单提交
                    },
                    success: function (ret) {
                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                        $.messager.alert('系统提示!', '保存成功!');
                        $('#editForm').form('clear');
                        $('#editPanel').dialog('close');
                        query();
                    },
                    error: function (err) {
                        $.messager.progress('close');
                        $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                    }
                }
            );
        }


    }

    function t2Add() {
        var id = currentItem['id'];
        $('#t2EditPanel').dialog('open');
        $('#t2EditForm').form('clear');
        $('#t2EditForm').form('load', {id: id});
    }

    function t2Edit() {
        var rows = $('#t2_dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
            	var url=t2Url + '/findById.do';
                $.ajax({
                    url: url,
                    data: {id: rows[0]['dtlId']},
                    dataType: 'json',
                    type: 'get'
                }).success(function (ret) {
                    if (ret && ret['dtlId']) {
                        $('#t2EditForm').form('clear');
                        $('#t2EditForm').form('load', ret);
                        $('#t2EditPanel').dialog('open');
                        switchAddOrEdit(false);
                    } else {
                        $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
                    }
                }).error(function (err) {
                    $.messager.alert('系统提示!', '获取数据!请重新尝试或联系管理员!');
                });
            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function t2Save() {
        if ($('#t2EditForm').form('validate')) {
        	var url =t2Url + ($('#dtlId').val() ? '/update.do' : '/save.do')+'?id='+currentItem['id']; 
            $('#t2EditForm').form('submit', {
                url: url,
                ajax: false,
                contentType: "application/json",
                onSubmit: function () {
                    $.messager.progress();
                    return true;	// 返回false终止表单提交
                },
                success: function (ret) {
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                    $.messager.alert('系统提示!', '保存成功!');
                    $('#t2EditForm').form('clear');
                    $('#t2EditPanel').dialog('close');
                    t2Query();
                },
                error: function (err) {
                    $.messager.progress('close');
                    $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                }
            }
            );
        }
    }

    function t2Remove() {
        var rows = $('#t2_dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                ids.push(v['dtlId']);
            });
        }
        if (ids.length > 0) {
        	var url = t2Url + '/delete.do';
            $.ajax({
                url: url,
                data: {ids: ids},
                dataType: 'json',
                type: 'post'
            }).success(function (ret) {
                if (ret && ret.flag > 0) {
                    $.messager.alert('系统提示!', '删除成功!');
                    t2Query();
                } else {
                    $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                }
            }).error(function (err) {
                $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
            });
        }
    }

    function t2Query() {
    	var url = t2Url + '/findAllById.do?id=' + currentItem['id'];
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
        currentItem = null;
        $('#t2EditPanel').dialog('close');
    }

    function switchAddOrEdit(flag) {
        if (flag) {
            $('#t2_panel').hide();
            //$('#c01').numberbox({editable: true});
        } else {
            $('#t2_panel').show();
            //$('#c01').numberbox({editable: false});
        }
    }
})
;