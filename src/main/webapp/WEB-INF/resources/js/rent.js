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
                calcRentDays(this);
            }
        });
        $('#end_time').datebox({
            onSelect: function (date) {
                calcRentDays(this);
            }
        });


    }

    function calcRentDays(which) {
        var start = $('#start_time').datebox('getValue');
        var end = $('#end_time').datebox('getValue');

        if (start && end) {
            start = new Date(start);
            end = new Date(end);
            if (start.getTime() > end.getTime()) {
                $.messager.alert('系统提示!', '结束时间不能小于开始时间!')
                $(which).datebox('setValue', null);
                $('#days').numberbox('setValue', null);
                return;
            }
            var interval = end.getTime() - start.getTime();
            $('#days').numberbox('setValue', (interval / 86400000) + 1);
        }
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
        $.currentItem = {};
        $('#editForm').form('clear');
        $('#editPanel').dialog('open');

        $('#billStat').combobox('setValue', 0);
        $('#stat').combobox('setValue', 1);

        $('#t2_dg').datagrid('loadData', {total: 0, rows: []});
    }

    function formatDate(obj, names) {
        function format(name) {
            return (obj && obj[name]) ? new Date(obj[name]).format("yyyy-MM-dd") : null;
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
                $('#beginDate').datebox('setValue',new Date($.currentItem.beginDate).format('yyyy-MM-dd'));
                $('#endDate').datebox('setValue',new Date($.currentItem.endDate).format('yyyy-MM-dd'));
                setEditable($.currentItem.billStat != 1);//已完成状态不可编辑
                t2Query();
                initSkuCombo($.currentItem.id);
                $('#editPanel').dialog('open');

            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function setEditable(flag) {
        if (flag) {
            $('#btn_edit_save').linkbutton('enable');
            $('#t2_menu a').linkbutton('enable');
            bindHandlers();
            bindT2Handlers();
        } else {
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
        if ($('#editForm').form('validate')) {
            $('#editForm input').each(function (i, val) {
                var name = $(val).attr('name');
                if (name && $(val).val()) {
                    $.currentItem[name] = $(val).val();
                }
            });

            if ($.currentItem.id) {
                var deleted = $('#t2_dg').datagrid('getChanges', 'deleted');
                if (deleted && deleted.length > 0) {
                    var deletedIds = [];
                    $.each(deleted, function (i, row) {
                        deletedIds.push(row['dtlId']);
                    });
                    $.currentItem.deleted = deletedIds;
                }
                var updated = $('#t2_dg').datagrid('getChanges', 'updated');
                if (updated && updated.length > 0) {
                    $.currentItem.updated = JSON.stringify(updated);
                }
            }
            var inserted = $('#t2_dg').datagrid('getChanges', 'inserted');
            if (inserted && inserted.length > 0) {
                $.currentItem.inserted = JSON.stringify(inserted);
            }

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
                $.messager.progress('close');
            });
        }


    }

    function finish() {
        $('#btn_finish').unbind();
        setTimeout(function () {
            $('#btn_finish').bind(finish);
        }, 1000);
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if (v['stat'] != 6) {
                    ids.push(v['id']);
                }
            });
        }
        if (ids && ids.length > 0) {
            $.ajax({
                url: t1Url + '/finish',
                type: 'post',
                dataType: 'json',
                data: {ids: ids}
            }).success(function (ret) {
                if (ret && ret.flag) {
                    $.messager.alert('系统提示', '审核完成!');
                    query();
                } else {
                    $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                }
            }).error(function (e) {
                $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
            });
        } else {
            $.messager.alert('系统提示', '请选择未完成的条目进行审核!');
        }
    }

    function t2ToAdd() {
        initSkuCombo();
        var skuInfo = $('#skuId').combobox('getData');
        var rows = $('#t2_dg').datagrid('getRows');
        if ((rows && rows.length) && (!skuInfo || skuInfo.length < 1)) {
            $.messager.alert('系统提示', '该仓库所有sku都已选择,无法继续添加,如需修改,请编辑明细信息!');
            return;
        }
        //没有id且t2_dg没有数据,说明是没有初始化过的,需要初始化
        var warehouseId = $('#warehouseId').combobox('getValue');
        if (!warehouseId) {
            $.messager.alert('系统提示', '填写明细单前请选择出库仓库');
            return;
        }
        $.t2CurrentItem = {};
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

    function initSkuCombo() {
        var rows = $('#t2_dg').datagrid('getRows');
        if (!rows || rows.length < 1) {
            $.ajax({
                url: 'sku/getAvailableSkuInfo',
                type: 'get',
                data: {billId: $.currentItem.id},
                dataType: 'json',
                async: false
            }).success(function (ret) {
                $('#skuId').combobox({
                        valueField: 'id',
                        textField: 'text',
                        data: ret,
                        required: true,
                        missingMessage: '必填字段',
                        formatter: function (row) {
                            var opts = $(this).combobox('options');
                            return row[opts.valueField] + ' ' + row[opts.textField];
                        },
                        onHidePanel: function () {
                            var val = $('input[name=skuId]').val();
                            if (!val) {
                                return;
                            }
                            var opt = $(this).combobox('options');
                            var data = $(this).combobox('getData');
                            var contains = false;
                            for (var i = 0; i < data.length; i++) {
                                if (data[i][opt.valueField] == val) {
                                    $(this).combobox('setValue', val + ' ' + data[i][opt.textField]);
                                    $('input[name=skuId]').val(val);
                                    $.loadSkuInfo(val);
                                    return;
                                }
                            }
                            if (!contains) {
                                $.messager.alert('系统提示', '只能从下拉框中选择值!');
                                $(this).combobox('reset');
                            }
                        }
                    }
                )
            }).error(function () {
                $.messager.alert('系统提示', '加载SKU信息失败!');
            });
        }


    }

    function t2Save() {
        if ($('#t2EditForm').form('validate')) {

            var item = $.t2CurrentItem;
            if (item) {
                $('#t2EditForm input').each(function (i, val) {
                    var key = $(val).attr('name');
                    if (key) {
                        item[key] = $(val).val();
                    }
                });
                //保存时将一选sku从选项中删除
                var skuInfo = $('#skuId').combobox('getData');
                $.each(skuInfo, function (i, rec) {
                    if (item.skuId == rec.id) {
                        item.skuName = rec.text;
                        skuInfo.splice(i, 1);
                        $('#skuId').combobox('loadData', skuInfo);
                        return false;
                    }
                });
                if (!item.dtlId) {
                    $('#t2_dg').datagrid('appendRow', item);
                } else {
                    var index = $('#t2_dg').datagrid('getRowIndex', item);
                    $('#t2_dg').datagrid('updateRow', {index: index, row: item});
                }

                //保存时将一选sku从选项中删除
                // var rows = $('#t2_dg').datagrid('getRows');
                // if (rows && rows.length) {
                //     var data = $('#skuId').combobox('getData');
                //     if(data&&data.length){
                //         $.each(rows, function (i, row) {
                //             $.each(data,function (i, item) {
                //                 if(row['skuId']==item.id){
                //                     data.remove(item);
                //                 }
                //             });
                //         });
                //     }
                //
                // }
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
                rentMoney += (isNaN(itemRent) ? 0 : itemRent);
                repoMoney += (isNaN(itemRepo) ? 0 : itemRepo);
            });

        }
        $('#rentMoney').numberbox('setValue', rentMoney);
        $('#repoMoney').numberbox('setValue', repoMoney);
    }

    function t2Remove() {
        var grid = $('#t2_dg');
        var rows = grid.datagrid('getChecked');

        var skuInfo = $('#skuId').combobox('getData');
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                var index = grid.datagrid('getRowIndex', v);
                skuInfo.push({id: v.skuId, text: v.skuName});
                grid.datagrid('deleteRow', index);
            });
        }
        skuInfo.sort(function (a, b) {
            return a.id - b.id;
        });
        $('#skuId').combobox('loadData', skuInfo);
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

    $.loadSkuInfo = function (id) {
        $.ajax({
            url: 'sku/findById',
            type: 'get',
            data: {id: id},
            dataType: 'json'
        }).success(function (ret) {
            if (ret && ret.id) {
                $('#t2EditForm').form('load', ret);
                if (ret.suffix) {
                    $('#skuImage').attr('src', 'resources/images/upload' + ret.id + ret.suffix);
                }
            } else {
                $.messager.alert('系统提示', '加载SKU信息失败!');
            }
        }).error(function () {
            $.messager.alert('系统提示', '加载SKU信息失败!');
        }).complete(function () {

        });
    }
})
;