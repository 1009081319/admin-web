<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <title>订单管理</title>
</head>
<body>
<section class="content-header">
    <h1>订单列表<small>Blank example to the fixed layout</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#">订单管理</a></li>
        <li class="active">订单列表</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" id="fastSearchWord" class="form-control" placeholder="订单号/身份证号/姓名">
                        </div>
                        <a id="search" class="btn btn-primary">查询</a>
                        <a id="complexSearch" class="btn btn-primary">高级查询</a>
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
                    </form>
                </div>
                <div class="box-body">
                    <table id="orderListTable" class="table table-bordered table-hover dataTable text-center"></table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">高级查询</h4>
                </div>
                <div class="modal-body">
                    <form id="searchForm" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-md-2 control-label">投保人：</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="holderName">
                            </div>
                            <label class="col-md-2 control-label">产品名称：</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="productName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">订单类型：</label>
                            <div class="col-md-4">
                                <select class="form-control" name="orderType">
                                    <option value="">请选择</option>
                                    <option value="1">普通订单</option>
                                    <option value="2">众安赠险</option>
                                    <option value="3">健康险</option>
                                </select>
                            </div>
                            <label class="col-md-2 control-label">订单状态：</label>
                            <div class="col-md-4">
                                <select class="form-control" name="orderStatus">
                                    <option value="">请选择</option>
                                    <option value="1">待支付</option>
                                    <option value="2">已支付</option>
                                    <option value="4">已退款</option>
                                    <option value="5">已取消</option>
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="doSearch">查询</button>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

function orderStatusRender(data,type,full) {
    if (data==1) {
        return "待支付";
    }else if (data==2) {
        return "已支付";
    }else if (data==3) {
        return "已关闭";
    }else if (data=4) {
        return "已退款";
    }else if (data==5) {
        return "已取消";
    }
}

$(function () {

    //dataTables 初始化
    var orderTable = $("#orderListTable").DataTable({
        ajax:{
            type:"post",
            url:"/mallOrder/findPageList"
        },
        columns:[
            {data:"id",name:"id",title:"id",visible:false},
            {data:"orderCode",name:"orderCode",title:"订单号",orderable:false},
            {data:"productName",name:"productName",title:"产品名称",orderable:false},
            {data:"planName",name:"planName",title:"计划名称",orderable:false,width:"100"},
            {data:"safeguardStartTime",name:"safeguardStartTime",title:"保障开始时间",orderable:false},
            {data:"safeguardEndTime",name:"safeguardEndTime",title:"保障截至时间",orderable:false},
            {data:"holderName",name:"holderName",title:"投保人",orderable:false,width:"100"},
            {data:"holderPhone",name:"holderPhone",title:"手机号",orderable:false},
            {data:"orderFee",name:"orderFee",title:"订单金额"},
            {data:"orderStatus",name:"orderStatus",title:"订单状态",render:orderStatusRender},
            {data:"createTime",name:"createTime",title:"下单时间"}
        ],
        aLengthMenu:[15,20,25]
    });

    //动态查询
    $("#search").click(function () {
        orderTable.settings()[0].ajax.data = {
            "fastSearchWord": $("#fastSearchWord").val()
        };
        orderTable.ajax.reload();
    });

    //高级查询框
    $("#complexSearch").click(function () {
        //清除数据
        $("#searchForm")[0].reset();
        $("#searchModal").modal({
            backdrop: "static",
            keyboard: false
        });
    });

    //隐藏模态框时清除数据
    $("#searchModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $("#doSearch").click(function () {
        //封装查询条件
        var postData = {
            "orderStatus":$("select[name='orderStatus']").val(),
            "orderType":$("select[name='orderType']").val(),
            "holderName":$("input[name='holderName']").val(),
            "productName":$("input[name='productName']").val()
        };
        //关闭对话框
        $("#searchModal").modal("hide");
        //查询
        orderTable.settings()[0].ajax.data = postData;
        orderTable.ajax.reload();
    });

});
</script>
</body>
</html>