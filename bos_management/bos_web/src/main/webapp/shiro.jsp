<%--
  Created by IntelliJ IDEA.
  User: wuyunlong
  Date: 2018/7/28
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css">
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>

<!-- 引入ztree css js -->
<link rel="stylesheet" href="js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>


<body>
<!-- 认证后可以显示标签中的内容 -->
<shiro:authenticated>
    如果你看到这个内容，说明已经认证了
</shiro:authenticated>

<!-- 判断当前用户是否具有area权限，如果有就显示标签中的内容 -->
<shiro:hasPermission name="area">
    当前用户有area权限
</shiro:hasPermission>

<!-- 判断当前用户是否有admin角色权限，如果有就显示标签中的内容 -->
<shiro:hasRole name="admin">
    当前用户有admin角色权限
</shiro:hasRole>




<!-- table容器 存放datagrid -->
<table id="dg" style="width:605px"></table>
<script type="text/javascript">
    $(function() {
        var editIndex ; //undefined
        $('#dg').datagrid({
            url : 'data/menu.json', //请求json数据地址
            columns : [ [
                {
                    field : 'id',//字段名（对应json文件中key名称）
                    title : '编号',//显示列名称
                    width : 200,
                    editor :{
                        type : 'validatebox',
                        options : {
                            required: true //验证必须输入值
                        }
                    }
                },
                {
                    field : 'pId',
                    title : '父节点',
                    width : 200,
                    editor :{
                        type : 'validatebox',
                        options : {
                            required: true
                        }
                    }
                },
                {
                    field : 'name',
                    title : '数据名称',
                    width : 200,
                    align : 'right',
                    editor :{
                        type : 'validatebox',
                        options : {
                            required: true
                        }
                    }
                }
            ] ],

            toolbar : [

            ////////////////////////////////////////////////
                <shiro:hasPermission name="areaxx">
                {
                    iconCls : 'icon-add', //图标
                    text:'添加一行',//标题
                    handler : function() {
                        if(editIndex != undefined){alert("1");
                            $("#dg").datagrid('endEdit',editIndex);
                        }
                        if(editIndex==undefined){alert("2");
                            //alert("快速添加电子单...");
                            $("#dg").datagrid('insertRow',{
                                index : 0,
                                row : {}
                            });
                            $("#dg").datagrid('beginEdit',0);
                            editIndex = 0;
                        }
                    }
                },
                </shiro:hasPermission>
/////////////////////////////////////////////////////

                {
                    iconCls : 'icon-edit',
                    text:'修改',//标题
                    handler : function() {
                        alert('help')
                    }
                } ,  {
                    iconCls : 'icon-cancel',
                    text:'作废',//标题
                    handler : function() {
                        alert('help')
                    }
                } ,  {
                    iconCls : 'icon-save',
                    text:'保存',//标题
                    handler : function() {
                        $("#dg").datagrid('endEdit',0);//第一行
                    }
                } ],
            pagination:true, //true显示分页栏datagrid底。
            pageList:[1,2,3], //当设置分页属性,初始化页面大小选择列表。
            pageSize:5, //当设置分页属性,初始化页面大小。   pageSize一定要在pageList中
            onAfterEdit : function(rowIndex, rowData, changes){alert("3");
                console.info(rowData);
                editIndex = undefined;
            }

        });
    })
</script>




</body>
</html>
