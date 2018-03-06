<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <title>角色管理</title>
</head>
<body>
<section class="content-header">
    <h1>角色管理<small>Blank example to the fixed layout</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#">系统设置</a></li>
        <li class="active">角色管理</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <a class="btn btn-primary" href="javascript:;" id="addRole">新增角色</a>
                </div>
                <div class="box-body">
                    <table id="roleList" class="table table-bordered table-hover dataTable text-center"></table>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="modal fade" id="addRolePermissionDlg" tabindex="-1" role="dialog" aria-labelledby="rolePermissionLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="rolePermissionLabel">分配权限</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" name="roleId">
                <ul class="ztree" id="roleHasPermissionTree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submit">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="maintainRoleDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="/roleManage/addRole" id="maintainRoleForm">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="roleCname" class="col-sm-4 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="cname" id="roleCname" placeholder="角色名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ename" class="col-sm-4 control-label">英文名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="ename" id="ename" readonly placeholder="英文名称">
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
                <button type="button" class="btn btn-primary" id="submitRole">确定</button>
            </div>
        </div>
    </div>
</div>
<script>

    var zTreeObject;

    function getSetting(roleId) {
        var setting = {
            async:{
                enable:true,
                autoParam:["id","pid","name"],
                dataType:"json",
                url:"/roleManage/getRoleHasPermission?roleId="+roleId
            },
            view:{
                showLine:false,//是否显示节点连线
                selectedMulti:true,//是否支持多选
                txtSelectedEnable:false//是否支持选中文本
            },
            check:{
                enable:true,
                chkStyle:"checkbox",
                autoCheckTrigger: false,
                chkboxType: { "Y": "ps", "N": "ps" },
                nocheckInherit: true,
                chkDisabledInherit: false
            }
        };
        return setting;
    }

    var zNodes = [];


    function operateFormatter(data,type,full) {
        return "<a class='modify-role' href='javascript:;' title='修改角色' id='"+data+"'><span class='glyphicon glyphicon-pencil'><span></a>" +
                "<span style='color: #dddddd;margin: 0 3px;'>|</span><a id='"+data+"' class='give-permission' href='javascript:;' title='分配权限'><span class='glyphicon glyphicon-cog'></span></a>" +
                "<span style='color: #dddddd;margin: 0 3px;'>|</span><a id='"+data+"' class='delete-role' href='javascript:;' title='删除角色'><span class='glyphicon glyphicon-trash'></span></a>";
    }

    $(function () {

        var listObject = $("#roleList");

        var roleList = listObject.DataTable({
            ajax:{
                type:"post",
                url:"/roleManage/findPageList"
            },
            columns:[
                {data:"id",name:"id",title:"id",visible:false},
                {data:"id",title:"操作",orderable:false,width:"100",render:operateFormatter},
                {data:"cname",name:"cname",title:"中文名称",orderable:false},
                {data:"ename",name:"ename",title:"英文名称",orderable:false},
                {data:"deleteFlag",name:"deleteFlag",title:"是否删除",orderable:false,width:"100"},
                {data:"createTime",name:"createTime",title:"创建时间",orderable:false},
                {data:"updateTime",name:"updateTime",title:"更新时间",orderable:false}
            ],
            aLengthMenu:[15,20,25]
        });

        $("#addRole").click(function () {
            $("#maintainRoleForm")[0].reset();
            $("#maintainRoleDlg").modal({
                backdrop: "static",
                keyboard: false
            });
        });

        $("#roleCname").change(function () {
            var chinese = $(this).val();
            if (chinese!=null&&chinese!='') {
                $.ajax({
                    type:"post",
                    url:"/common/chinese2Pinyin",
                    data:{"chinese":$(this).val()},
                    success:function (data) {
                        console.log(data);
                        if (data!=null){
                            $("#ename").val(data.fullPinyin);
                        }
                    }
                });
            }
        });

        $("#submitRole").click(function () {
            $("#maintainRoleForm").ajaxSubmit({
                success:function (data) {
                    if (data!=null&&data.code=='0000'){
                        $("#maintainRoleDlg").modal("hide");
                        roleList.ajax.reload();
                    }
                }
            });
        });

        $("#submit").click(function () {
            var nodes = zTreeObject.getCheckedNodes(true);
            var nodeIds='';
            for (var i=0;i<nodes.length;i++) {
                nodeIds += nodes[i].id + ",";
            }
            nodeIds = nodeIds.substring(0, nodeIds.length - 1);
            console.log("nodeIds:" + nodeIds);
            $.ajax({
                type:"post",
                url:"/roleManage/addRolePermission",
                data:{
                    "roleId":$("input[name='roleId']").val(),
                    "permissionIds":nodeIds
                },
                success:function (data) {
                    if(data==true||data=='true'){
                        $("#addRolePermissionDlg").modal("hide");
                    }
                }
            });
        });



        listObject.delegate(".delete-role", "click", function () {
            alert("delete role where id=" + $(this).attr("id"));
        });

        listObject.delegate(".modify-role", "click", function () {
            alert("modify role where id=" + $(this).attr("id"));
        });

        listObject.delegate(".give-permission", "click", function () {
            var roleId = $(this).attr("id");
            $("input[name='roleId']").val(roleId);
            //初始化权限树
            zTreeObject = $.fn.zTree.init($("#roleHasPermissionTree"), getSetting(roleId), zNodes);
            $("#addRolePermissionDlg").modal({
                backdrop: "static",
                keyboard: false
            });
        });

    });
</script>
</body>
</html>
