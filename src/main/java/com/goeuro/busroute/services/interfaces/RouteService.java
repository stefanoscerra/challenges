package com.goeuro.busroute.services.interfaces;


public interface RouteService {
    /**
     * Checks if exists a route connecting the departure station to the arrival station.
     *      
     */
    boolean areConnected(int departureStationId, int arrivalStationId);
}
