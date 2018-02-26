package com.goeuro.busroute.services.implementations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goeuro.busroute.services.interfaces.RouteService;

@Service
public class BusRouteService implements RouteService {

    private List<Route> routes = new ArrayList<Route>();

    public void setRoutes(List<Route> routes) {
	this.routes = routes;
    }

    @Override
    /**
     *	Checks if exists a route connecting the departure station to the arrival station.
     *	Connection check is done in O(R) time, with R number of routes.
     */
    public boolean areConnected(int departureStationId, int arrivalStationId) {
	return routes.stream().anyMatch(route -> route.areConnected(departureStationId, arrivalStationId));
    }
}
