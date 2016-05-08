$(function () {
    bindHandlers();
    bindT2Handlers();
    var t1Url = '/table1'
    var t2Url = '/table2'
    var currentItem = null;

    function bindHandlers() {
        $('#btn_add').bind('click', add);
        $('#btn_edit').bind('click', edit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
        $('#start_time').datebox({
            onSelect: function(date){
                var end = $('#end_time').datebox('getValue');
                if(date&&end){
                    end = new Date(end);
                    if(date.getTime()>end.getTime()){
                        $.messager.alert('系统提示!','开始时间不能大于开始时间!')
                        $(this).datebox('setValue',null);
                        ('#days').numberbox('setValue',null);
                        return;
                    }
                    var interval = date.getTime()-(new Date(end)).getTime();
                    $('#days').numberbox('setValue',(interval).toFixed(2)/86400000+1);
                }
            }
        });
        $('#end_time').datebox({
            onSelect: function(date){
                var start = $('#start_time').datebox('getValue');

                if(date&&start){
                    start = new Date(start);
                    if(start.getTime()>date.getTime()){
                        $.messager.alert('系统提示!','结束时间不能小于开始时间!')
                        $(this).datebox('setValue',null);
                        ('#days').numberbox('setValue',null);
                        return;
                    }
                    var interval = date.getTime()-start.getTime();
                    $('#days').numberbox('setValue',((interval).toFixed(2)/86400000)+1);
                }
            }
        });


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
                $.ajax({
                    url: t1Url + '/findById',
                    data: {id: rows[0]['c01']},
                    dataType: 'json',
                    type: 'get'
                }).success(function (ret) {
                    if (ret && ret['c01']) {
                        //$('#t2_dg').datagrid('loadData', {total:0,rows:[]});
                        t2Query();

                        //$('#editForm').form('load', ret);
                        $(ret).each(function (i,val) {
                            $('#'+i).val(val);
                        });
                        $('#editPanel').dialog('open');
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

    function remove() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                ids.push(v['c01']);
            });
        }
        if (ids.length > 0) {
            $.ajax({
                url: t1Url + '/remove',
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
        $.ajax({
            url: t1Url + '/query',
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
                    url: t1Url + (currentItem?'/update':'/save'),
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
        var c01 = currentItem['c01'];
        $('#t2EditPanel').dialog('open');
        $('#t2EditForm').form('loadData', {c01: c01});
    }

    function t2Edit() {
        var rows = $('#t2_dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                $.ajax({
                    url: t2Url + '/findById',
                    data: {id: rows[0]['c00']},
                    dataType: 'json',
                    type: 'get'
                }).success(function (ret) {
                    if (ret && ret['c00']) {
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

    function t2Save(){
        if ($('#t2EditForm').form('validate')) {
            $('#t2EditForm').form('submit', {
                    url: t2Url + $('#c00').val?'/update':'/save',
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
                ids.push(v['c00']);
            });
        }
        if (ids.length > 0) {
            $.ajax({
                url: t2Url + '/remove',
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
        $.ajax({
            url: t2Url + '/query/'+currentItem['c01'],
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
            $('#c01').numberbox({editable: true});
        } else {
            $('#t2_panel').show();
            $('#c01').numberbox({editable: false});
        }
    }
});