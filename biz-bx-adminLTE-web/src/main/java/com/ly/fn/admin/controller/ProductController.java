package com.ly.fn.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("product")
@Controller("productController")
public class ProductController {

    @RequestMapping("list")
    public String productList() {
        return "product/productList";
    }
}
