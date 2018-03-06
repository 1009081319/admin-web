package com.ly.fn.admin.modules.order.service;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.order.domain.MallOrder;

import java.util.List;


public interface OrderService {

    MallOrder getById(Long id);

    List<MallOrder> findList(MallOrder mallOrder);

    PageInfo findPageList(MallOrder mallOrder);

}
