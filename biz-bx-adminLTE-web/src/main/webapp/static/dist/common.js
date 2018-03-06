$.extend($.fn.DataTable.defaults,{
    searching: false,
    processing: true,
    serverSide: true,
    bFilter: false,
    responsive: true,
    bAutoWidth: true,
    language:{
        "emptyTable": "未查询到数据",
        "zeroRecords": "未查询到数据",
        "infoEmpty": "无记录",
        "info": "第 _PAGE_/_PAGES_ 页（共 _TOTAL_ 条记录）",
        "infoPostFix": "",
        "infoFiltered": "共 _MAX_ 条数据。",
        "loadingRecords": "请稍等，数据加载中...",
        "lengthMenu": "显示 _MENU_ 条记录",
        "processing": "正在处理中...",
        "paginate":{
            "first": "首页",
            "previous": "上一页",
            "next": "下一页",
            "last": "尾页"
        }
    }
});

$.extend($.fn.modal.defaults, {
    backdrop: "static",
    keyboard: false
});