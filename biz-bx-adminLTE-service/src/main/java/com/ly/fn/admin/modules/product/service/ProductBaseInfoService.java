package com.ly.fn.admin.modules.product.service;


import com.ly.fn.admin.modules.product.domain.ProductBaseInfo;

import java.util.List;

public interface ProductBaseInfoService {

    ProductBaseInfo getById(Long id);

    List<ProductBaseInfo> findList(ProductBaseInfo productBaseInfo);
}
