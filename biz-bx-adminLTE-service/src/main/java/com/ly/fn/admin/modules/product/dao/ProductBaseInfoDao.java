package com.ly.fn.admin.modules.product.dao;


import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.product.domain.ProductBaseInfo;

import java.util.List;

@MyBatisDao
public interface ProductBaseInfoDao {

    ProductBaseInfo getById(Long id);

    List<ProductBaseInfo> findList(ProductBaseInfo productBaseInfo);
}
