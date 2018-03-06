package com.ly.fn.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @package：com.ly.fn.admin.controller
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2018/1/9-15:34
 */
@RequestMapping(value = "dictManage")
@Controller
public class DictController extends BaseController {

    @RequestMapping(value = "list")
    public String list() {
        return "/system/dictList";
    }
}
