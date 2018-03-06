<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <title>权限管理</title>
</head>
<body>
<section class="content-header">
    <h1>权限管理
        <small>Blank example to the fixed layout</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#">系统设置</a></li>
        <li class="active">权限管理</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <a class="btn btn-primary btn-sm" href="javascript:;" id="addPermission">添加权限</a>
                    <a class="btn btn-primary btn-sm" href="javascript:;" id="modifyPermission">修改权限</a>
                    <a class="btn btn-danger btn-sm" href="javascript:;" id="deletePermission">删除权限</a>
                </div>
                <div class="box-body">
                    <div>
                        <ul class="ztree" id="permissionTree"></ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="maintainPermissionDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加权限</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="/permissionManage/addPermission" id="maintainPermissionForm">
                    <input type="hidden" name="id">
                    <input type="hidden" name="parentId">
                    <div class="form-group">
                        <label for="permissionCname" class="col-sm-4 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="cname" id="permissionCname" placeholder="角色名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permissionEname" class="col-sm-4 control-label">英文名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="ename" id="permissionEname" placeholder="英文名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permissionType" class="col-sm-4 control-label">权限类型：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="displayType" id="permissionType">
                                <option value="">请选择</option>
                                <option value="1">一级菜单</option>
                                <option value="2">二级菜单</option>
                                <option value="3">功能按钮</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permissionUrl" class="col-sm-4 control-label">权限URL：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="url" id="permissionUrl" placeholder="权限URL">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remarks" class="col-sm-4 control-label">备注：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="remarks" id="remarks" placeholder="备注">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submitPermission">确定</button>
            </div>
        </div>
    </div>
</div>

<script>
    var zTreeObject;
    var setting = {
        async:{
            enable:true,
            autoParam:["id","pid","name"],
            dataType:"json",
            url:"/workbench/getTree?treeCode=permission"
        },
        view:{
            showLine:false,//是否显示节点连线
            selectedMulti:false,//是否支持多选
            txtSelectedEnable:false//是否支持选中文本
        }
    };

    var zNodes = [];

    function getSelectedNode() {
        var selectedNodes = zTreeObject.getSelectedNodes();
        if (selectedNodes.length==0) {
            //弹框插件
            return false;
        }
        return selectedNodes[0];
    }

    function fillPermissionDlg(permission) {
        $("#maintainPermissionDlg #myModalLabel").html("修改权限");
        $("#maintainPermissionForm input[name='id']").val(permission.id);
        $("#maintainPermissionForm input[name='cname']").val(permission.cname);
        $("#maintainPermissionForm input[name='ename']").val(permission.ename);
        $("#maintainPermissionForm input[name='url']").val(permission.url);
        $("#maintainPermissionForm select[name='displayType']").val(permission.displayType);
        $("#maintainPermissionForm input[name='remarks']").val(permission.remarks);
        $("#maintainPermissionDlg").modal({
            backdrop: "static",
            keyboard: false
        });
    }

    $(function () {
        zTreeObject = $.fn.zTree.init($("#permissionTree"), setting, zNodes);

        $("#addPermission").click(function () {
            $("#maintainPermissionDlg #myModalLabel").html("添加权限");
            var pid = getSelectedNode().id;
            $("#maintainPermissionForm")[0].reset();
            $("#maintainPermissionForm input[name='parentId']").val(pid);
            $("#maintainPermissionDlg").modal({
                backdrop: "static",
                keyboard: false
            });
        });

        $("#deletePermission").click(function () {
            var selectedNode = getSelectedNode();
            $.ajax({
                type:"post",
                url:"/permissionManage/deleteById",
                data:{"id":selectedNode.id},
                success:function (data) {
                    if (data==true||data=="true"){
                        zTreeObject.removeNode(selectedNode);
                    }
                }
            });
        });

        $("#modifyPermission").click(function () {
            var pid = getSelectedNode().id;
            $.ajax({
                type:"post",
                url:"/permissionManage/getById",
                data:{"id":pid},
                success:function (data) {
                    fillPermissionDlg(data);
                }
            });
        });

        $("#submitPermission").click(function () {
            $("#maintainPermissionForm").ajaxSubmit({
                success:function (data) {
                    if (data!=null&&data.id!=null) {
                        $("#maintainPermissionDlg").modal("hide");
                        //重新加载本级树
                        zTreeObject.reAsyncChildNodes(getSelectedNode(), "refresh");
                    }
                }
            });
        });
    });
</script>
</body>
</html>
