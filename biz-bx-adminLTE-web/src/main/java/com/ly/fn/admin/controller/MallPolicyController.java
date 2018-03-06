package com.ly.fn.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "policyManage")
@Controller
public class MallPolicyController extends BaseController {

    @RequestMapping(value = "list")
    public String list() {
        return "/order/orderList";
    }
}
