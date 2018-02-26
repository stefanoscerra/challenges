package com.goeuro.busroute.services.implementations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * Represents a route as a map of entries (stationId -> position).
 * This allows for checking connection between stations in O(1) time. 
 *
 */
public class Route {

    private Map<Integer, Integer> stationsPosition = new LinkedHashMap<>();

    public void addStation(int stationId) {
	if (stationsPosition.containsKey(stationId)) {
	    throw new IllegalArgumentException("Station already exists in route");
	}

	int position = stationsPosition.size();
	stationsPosition.put(stationId, position);
    }

    public boolean areConnected(int departureStationId, int arrivalStationId) {
	Integer departureStationPosition = stationsPosition.get(departureStationId);

	if (departureStationPosition == null) {
	    return false;
	}

	Integer arrivalStationPosition = stationsPosition.get(arrivalStationId);

	if (arrivalStationPosition == null) {
	    return false;
	}

	return arrivalStationPosition > departureStationPosition;
    }
    
    public List<Integer> getStations() {
	return new ArrayList<Integer>(stationsPosition.keySet());
    }

    @Override
    public String toString() {
	return stationsPosition.entrySet().stream().map(entry -> entry.getKey())
		.collect(Collectors.toList()).toString();
    }

}
