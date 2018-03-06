package com.ly.fn.admin.modules.order.service.impl;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.order.dao.MallOrderDao;
import com.ly.fn.admin.modules.order.domain.MallOrder;
import com.ly.fn.admin.modules.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private MallOrderDao mallOrderDao;

    @Override
    public MallOrder getById(Long id) {
        return mallOrderDao.getById(id);
    }

    @Override
    public List<MallOrder> findList(MallOrder mallOrder) {
        return mallOrderDao.findList(mallOrder);
    }

    @Override
    public PageInfo<MallOrder> findPageList(MallOrder mallOrder) {
        List<MallOrder> orderList = findList(mallOrder);
        return new PageInfo(mallOrder.getDraw(), mallOrderDao.getCount(mallOrder), orderList);
    }
}
