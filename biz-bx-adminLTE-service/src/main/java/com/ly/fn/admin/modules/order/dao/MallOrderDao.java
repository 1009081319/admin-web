package com.ly.fn.admin.modules.order.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.order.domain.MallOrder;

import java.util.List;


@MyBatisDao
public interface MallOrderDao {

    MallOrder getById(Long id);

    int getCount(MallOrder mallOrder);

    List<MallOrder> findList(MallOrder mallOrder);
}
