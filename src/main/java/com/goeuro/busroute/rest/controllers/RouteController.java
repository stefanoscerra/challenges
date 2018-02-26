package com.goeuro.busroute.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.busroute.rest.models.DirectBusRouteResponse;
import com.goeuro.busroute.services.interfaces.RouteService;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET)
    public ResponseEntity<DirectBusRouteResponse> directBusRoute(
	    @RequestParam("dep_sid") Integer departureStationId,
	    @RequestParam("arr_sid") Integer arrivalStationId) {

	if (departureStationId == null || departureStationId < 0
		|| arrivalStationId == null || arrivalStationId < 0) {
	    return ResponseEntity.badRequest().body(null);
	}

	boolean result = routeService.areConnected(departureStationId, arrivalStationId);

	DirectBusRouteResponse response = new DirectBusRouteResponse();
	response.setDepartureStationId(departureStationId);
	response.setArrivalStationId(arrivalStationId);
	response.setDirectBusRoute(result);

	return ResponseEntity.ok(response);
    }
}
