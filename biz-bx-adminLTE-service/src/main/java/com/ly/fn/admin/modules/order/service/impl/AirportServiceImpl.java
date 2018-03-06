package com.ly.fn.admin.modules.order.service.impl;

import com.ly.fn.admin.common.exception.ServiceException;
import com.ly.fn.admin.modules.order.dao.AirportDao;
import com.ly.fn.admin.modules.order.domain.Airport;
import com.ly.fn.admin.modules.order.service.AirportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("airportService")
public class AirportServiceImpl implements AirportService {
    @Resource
    private AirportDao airportDao;

    @Override
    public Airport getById(Long id) {
        return airportDao.getById(id);
    }

    @Override
    @Transactional
    public Airport addAirport(Airport airport) {
        try {
            airportDao.addAirport(airport);
        } catch (Exception e) {
            throw new ServiceException("新增机场异常", e);
        }
        return getById(airport.getId());
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport) {
        try {
            airportDao.updateAirport(airport);
        } catch (Exception e) {
            throw new ServiceException("更新机场异常", e);
        }
        return getById(airport.getId());
    }
}
