package com.ly.fn.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @package：com.ly.fn.admin.controller
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2018/1/5-16:36
 */
@RequestMapping(value = "common")
@Controller
public class CommonController extends BaseController {

//    @RequestMapping(value = "chinese2Pinyin")
//    @ResponseBody
//    public Map<String, String> chinese2Pinyin(String chinese) {
//        return PinyinUtils.chinese2Pinyin(chinese);
//    }
}
