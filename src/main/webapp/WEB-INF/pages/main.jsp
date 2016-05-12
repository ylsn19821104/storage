<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>服装租赁系统</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">

        var url;

        function openTab(text, url, iconCls) {
            if ($("#tabs").tabs("exists", text)) {
                $("#tabs").tabs("select", text);
            } else {
                var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/" + url + "'></iframe>";
                $("#tabs").tabs("add", {
                    title: text,
                    iconCls: iconCls,
                    closable: true,
                    content: content
                });
            }
        }

        function openPasswordModifyDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "修改密码");
            url = "${pageContext.request.contextPath}/user/modifyPassword?id=${currentUser.id}";
        }

        function modifyPassword() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    var oldPassword = $("#oldPassword").val();
                    var newPassword = $("#newPassword").val();
                    var newPassword2 = $("#newPassword2").val();
                    if (!$(this).form("validate")) {
                        return false;
                    }
                    if (oldPassword != '${currentUser.password}') {
                        $.messager.alert("系统提示", "用户原密码输入错误！");
                        return false;
                    }
                    if (newPassword != newPassword2) {
                        $.messager.alert("系统提示", "确认密码输入错误！");
                        return false;
                    }
                    return true;
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "密码修改成功，下一次登录生效！");
                        resetValue();
                        $("#dlg").dialog("close");
                    } else {
                        $.messager.alert("系统提示", "密码修改失败！");
                        return;
                    }
                }
            });
        }

        function closePasswordModifyDialog() {
            resetValue();
            $("#dlg").dialog("close");
        }

        function resetValue() {
            $("#oldPassword").val("");
            $("#newPassword").val("");
            $("#newPassword2").val("");
        }

        function logout() {
            $.messager.confirm("系统提示", "您确定要退出系统吗？", function (r) {
                if (r) {
                    window.location.href = '${pageContext.request.contextPath}/user/logout';
                }
            });
        }

        function fillZero(v) {
            if (v < 10) {
                v = '0' + v;
            }
            return v;
        }

        function timeshow() {
            var date = 0;
            var type = 0;


            var d;
            var ev = 'system_time';
            var Y, M, D, W, H, I, S;
            var Week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
            if (date) {
                d = new Date(date * 1000);
            } else {
                d = new Date();
            }

            Y = d.getFullYear();
            M = fillZero(d.getMonth() + 1);
            D = fillZero(d.getDate());
            W = Week[d.getDay()];
            H = fillZero(d.getHours());
            I = fillZero(d.getMinutes());
            S = fillZero(d.getSeconds());

            if (type && type == 12) {
                if (H < 10 && H >= 6) {
                    H = '早晨:' + H;
                } else if (H >= 10 && H < 12) {
                    H = '上午:' + fillZero(H);
                } else if (H >= 12 && H < 14) {
                    H = '中午:' + H;
                } else if (H >= 14 && H < 18) {
                    H = '下午:' + H;
                } else if (H >= 18 && H < 24) {
                    H = '晚上:' + H;
                } else if (H == 0) {
                    H = '凌晨:00';
                } else if (H > 0 && H < 6) {
                    H = '夜晚:' + H;
                }
            }
            var showData = Y + '年' + M + '月' + D + '日' + ' ' + H + ':' + I + ':' + S + '';

            $('#' + ev).html(showData);
            if (date) {
                date++;
            }
            setTimeout(function () {
                timeshow()
            }, 1000);
        }
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
    <table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                <img alt="logo" src="${pageContext.request.contextPath}/resources/images/bglogo.png">
            </td>
            <td valign="bottom" align="right" width="50%">
                <font size="4">&nbsp;&nbsp;<strong>欢迎：</strong>${currentUser.userName }【${currentUser.trueName }】</font>
                <a href="javascript:logout()" style="height:17px;width:52px"><font size="4">退出系统</font></a>
            </td>
        </tr>
    </table>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="blue" size="10">欢迎使用L1服装租赁系统</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="基础数据" data-options="iconCls:'icon-jcsjgl'" style="padding:10px">
            <a href="javascript:openTab('供应商资料','supplier','icon-sjzdgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-sjzdgl'" style="width: 150px;">供应商管理</a>
            <a href="javascript:openTab('仓库','warehouse','icon-cpxxgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-cpxxgl'" style="width: 150px;">仓库</a>
        </div>
        <div title="商品管理" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px">
            <a href="javascript:openTab('商品列表','item','icon-yxjhgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">商品</a>
            <a href="javascript:openTab('Sku列表','sku','icon-yxjhgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">Sku列表</a>
            <a href="javascript:openTab('颜色资料','color','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">颜色资料</a>
            <a href="javascript:openTab('尺码资料','size','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">尺码资料</a>
            <a href="javascript:openTab('品牌','brand','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">品牌</a>
            <a href="javascript:openTab('大分类','category/primary','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">大分类</a>
            <a href="javascript:openTab('小分类','category/minor','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">小分类</a>
            <a href="javascript:openTab('商品图片维护','image','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">图片维护</a>
            <a href="javascript:openTab('自定义属性1','customProps/1','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">自定义属性1</a>
            <a href="javascript:openTab('自定义属性2','customProps/2','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">自定义属性2</a>
            <a href="javascript:openTab('自定义属性3','customProps/3','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">自定义属性3</a>
        </div>
        <div title="采购管理" data-options="iconCls:'icon-khgl'" style="padding:10px;">
            <a href="javascript:openTab('采购单','purchase','icon-khxxgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">采购单</a>
        </div>
        <div title="租赁管理" data-options="iconCls:'icon-fwgl'" style="padding:10px">
            <a href="javascript:openTab('出租单列表','rent','icon-fwcj')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">出租单列表</a>
            <a href="javascript:openTab('归还单列表','return','icon-fwfp')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">归还单</a>
        </div>
        <div title="库存管理" data-options="iconCls:'icon-tjbb'" style="padding:10px">
            <!--
            <a href="javascript:openTab('盘点单','pd','icon-khgxfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgxfx'" style="width: 150px;">盘点单</a>
            <a href="javascript:openTab('盘点录入','pdlr','icon-khgcfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgcfx'" style="width: 150px;">盘点录入</a>
             -->
            <a href="javascript:openTab('库存调整','kctz','icon-khfwfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khfwfx'" style="width: 150px;">库存调整</a>
            <a href="javascript:openTab('移仓入库','ycrk','icon-khlsfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khlsfx'" style="width: 150px;">移仓入库</a>
            <a href="javascript:openTab('移仓出库','ycck','icon-khlsfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khlsfx'" style="width: 150px;">移仓出库</a>
            <a href="javascript:openTab('Sku库存','inventory','icon-khgcfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khgcfx'" style="width: 150px;">Sku库存</a>
        </div>
        <div title="报表中心" data-options="iconCls:'icon-item'" style="padding:10px">
            <a href="javascript:openTab('盘点盈亏表','pdyk','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">盘点盈亏表</a>
            <a href="javascript:openTab('出租预期报表','czyq','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">出租预期报表</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-item'" style="padding:10px">
            <a href="javascript:openTab('操作员管理','user','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
            <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出</a>
        </div>
    </div>
</div>
<div region="south" style="height: 28px;padding: 5px ;background-color: #FFF" align="center">
    版本所有 hongxp <a href="http://www.hongxp.com" target="_blank">http://www.hongxp.com</a>(2016-2017)
    <div style="float:right;padding-right: 100px" id="system_time"></div>
    <script type="text/javascript">timeshow();</script>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName" readonly="readonly"
                           value="${currentUser.userName }" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>原密码：</td>
                <td><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>