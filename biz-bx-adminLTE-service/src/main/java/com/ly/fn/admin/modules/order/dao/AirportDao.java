package com.ly.fn.admin.modules.order.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.order.domain.Airport;

@MyBatisDao
public interface AirportDao {

    Airport getById(Long id);

    int addAirport(Airport airport);

    int updateAirport(Airport airport);
}
