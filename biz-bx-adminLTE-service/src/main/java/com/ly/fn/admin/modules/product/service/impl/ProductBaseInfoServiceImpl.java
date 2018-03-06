package com.ly.fn.admin.modules.product.service.impl;

import com.ly.fn.admin.modules.product.dao.ProductBaseInfoDao;
import com.ly.fn.admin.modules.product.domain.ProductBaseInfo;
import com.ly.fn.admin.modules.product.service.ProductBaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productBaseInfoService")
public class ProductBaseInfoServiceImpl implements ProductBaseInfoService {

    @Resource
    private ProductBaseInfoDao productBaseInfoDao;

    @Override
    public ProductBaseInfo getById(Long id) {
        return productBaseInfoDao.getById(id);
    }

    @Override
    public List<ProductBaseInfo> findList(ProductBaseInfo productBaseInfo) {
        return productBaseInfoDao.findList(productBaseInfo);
    }

}
