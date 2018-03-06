package com.ly.fn.admin.modules.order.service;


import com.ly.fn.admin.modules.order.domain.Airport;

public interface AirportService {

    Airport getById(Long id);

    Airport addAirport(Airport airport);

    Airport updateAirport(Airport airport);
}
