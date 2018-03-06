<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <title>用户管理</title>
</head>
<body>
<section class="content-header">
    <h1>用户管理<small>Blank example to the fixed layout</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#">系统设置</a></li>
        <li class="active">用户列表</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" id="fastSearchWord" class="form-control" placeholder="姓名">
                        </div>
                        <a id="search" class="btn btn-primary">查询</a>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                导出
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">导出本页</a></li>
                                <li><a href="#">导出全部</a></li>
                            </ul>
                        </div>

                        <a class="btn btn-info" href="javascript:;" id="addUser">新增用户</a>
                    </form>
                </div>
                <div class="box-body">
                    <table id="userListTable" class="table table-bordered table-hover dataTable text-center"></table>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="maintainUserDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="userForm" action="/userManage/maintainUser">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" name="loginName" class="form-control" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input type="password" name="password" class="form-control" placeholder="默认密码：123456">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">用户昵称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="nickName" class="form-control" placeholder="用户昵称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">手机号码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="phone" class="form-control" placeholder="手机号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">邮箱：</label>
                        <div class="col-sm-6">
                            <input type="email" name="email" class="form-control" placeholder="邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属角色：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="role.id">
                                <option value="">请选择角色</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}">${role.cname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">备注：</label>
                        <div class="col-sm-6">
                            <input type="text" name="remarks" class="form-control" placeholder="备注">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="doSubmit">确定</button>
            </div>
        </div>
    </div>
</div>
<script>

    function operateFormatter(data,type,full) {
        return "<a class='modify-user' href='javascript:;' title='修改用户' id='"+data+"'><span class='glyphicon glyphicon-pencil'><span></a>" +
            "<span style='color: #dddddd;margin: 0 3px;'>|</span><a id='"+data+"' class='delete-user' href='javascript:;' title='删除用户'><span class='glyphicon glyphicon-trash'></span></a>";
    }

    function deleteFormatter(data,type,full) {
        if (data===1) {
            return "是";
        }
        return "否";
    }

    var maintainUserDlg = $("#maintainUserDlg");

    $(function () {
        //dataTables 初始化
        var userTable = $("#userListTable").DataTable({
            ajax:{
                type:"post",
                url:"/userManage/findPageList"
            },
            columns:[
                {data:"id",name:"id",title:"id",visible:false},
                {data:"id",title:"操作",orderable:false,width:"100",render:operateFormatter},
                {data:"loginName",name:"loginName",title:"登录名称",orderable:false},
                {data:"phone",name:"phone",title:"手机号",orderable:false},
                {data:"email",name:"email",title:"邮箱",orderable:false},
                {data:"nickName",name:"nickName",title:"昵称",orderable:false},
                {data:"role.cname",name:"roleName",title:"所属角色",orderable:false},
                {data:"deleteFlag",name:"deleteFlag",title:"是否删除",orderable:false,width:"100",render:deleteFormatter},
                {data:"lastLoginTime",name:"lastLoginTime",title:"上次登录时间",orderable:false},
                {data:"createTime",name:"createTime",title:"创建时间",orderable:false},
                {data:"updateTime",name:"updateTime",title:"更新时间",orderable:false},
                {data:"remarks",name:"remarks",title:"备注",orderable:false}
            ],
            aLengthMenu:[15,20,25]
        });

        $("#addUser").click(function () {
            maintainUserDlg.find(".modal-title").html("新增用户");
            $("#userForm")[0].reset();
            maintainUserDlg.modal({
                backdrop: "static",
                keyboard: false
            });
        });

        $("#userListTable").delegate(".modify-user", "click", function () {
            var userId = $(this).attr("id");
            $.ajax({
                type:"post",
                url:"/userManage/getById",
                data:{"id":userId},
                success:function (data) {
                    if(data!==null&&data.id!==null){
                        maintainUserDlg.find(".modal-title").html("修改用户");
                        var userForm = $("#userForm");
                        userForm.find("input[name='id']").val(data.id);
                        userForm.find("input[name='loginName']").val(data.loginName);
                        userForm.find("input[name='password']").val(data.password);
                        userForm.find("input[name='nickName']").val(data.nickName);
                        userForm.find("input[name='phone']").val(data.phone);
                        userForm.find("input[name='email']").val(data.email);
                        userForm.find("select[name='role.id']").val(data.role.id);
                        userForm.find("input[name='remarks']").val(data.remarks);
                        maintainUserDlg.modal({
                            backdrop: "static",
                            keyboard: false
                        });
                    }
                }
            });

        });

        $("#doSubmit").click(function () {
            $("#userForm").ajaxSubmit({
                success:function (data) {
                    if (data===true||data==='true'){
                        $("#maintainUserDlg").modal("hide");
                        userTable.ajax.reload();
                    }
                }
            });
        });

    });
</script>
</body>
</html>
