package com.goeuro.busroute.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectBusRouteResponse {

    @JsonProperty("dep_sid")
    private Integer departureStationId;

    @JsonProperty("arr_sid")
    private Integer arrivalStationId;

    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;

    public Integer getDepartureStationId() {
	return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
	this.departureStationId = departureStationId;
    }

    public Integer getArrivalStationId() {
	return arrivalStationId;
    }

    public void setArrivalStationId(Integer arrivalStationId) {
	this.arrivalStationId = arrivalStationId;
    }

    public boolean isDirectBusRoute() {
	return directBusRoute;
    }

    public void setDirectBusRoute(boolean directBusRoute) {
	this.directBusRoute = directBusRoute;
    }

}
