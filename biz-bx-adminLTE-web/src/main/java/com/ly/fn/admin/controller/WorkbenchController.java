package com.ly.fn.admin.controller;

import com.ly.fn.admin.modules.system.domain.TreeDomain;
import com.ly.fn.admin.modules.system.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping(value = "workbench")
public class WorkbenchController extends BaseController {
    @Resource
    private SystemService systemService;

    @RequestMapping(value = "list")
    public String list() {
        return "/system/workbenchList";
    }

    @RequestMapping(value = "getTree")
    @ResponseBody
    public List<TreeDomain> getTreeList(String treeCode,TreeDomain treeDomain) {
        return systemService.getTreeList(treeCode,treeDomain.getId());
    }

}
