package com.ly.fn.admin.controller;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.common.util.LoggerUtils;
import com.ly.fn.admin.modules.order.domain.MallOrder;
import com.ly.fn.admin.modules.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("mallOrder")
@Controller("mallOrderController")
public class MallOrderController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private OrderService orderService;

    @RequestMapping("list")
    public String orderList(){
        return "/order/orderList";
    }

    @RequestMapping("findPageList")
    @ResponseBody
    public PageInfo findPageList(HttpServletRequest request, MallOrder mallOrder) {
        LoggerUtils.info(logger,"订单管理","订单列表","获取订单列表数据");
        mallOrder.setSortStr(getSortStr(request));
        return orderService.findPageList(mallOrder);
    }

    @RequestMapping("getById/{id}")
    @ResponseBody
    public MallOrder getById(@PathVariable Long id) {
        return orderService.getById(id);
    }
}
