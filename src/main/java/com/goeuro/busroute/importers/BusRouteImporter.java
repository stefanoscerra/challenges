package com.goeuro.busroute.importers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.goeuro.busroute.services.implementations.Route;

@Component
public class BusRouteImporter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public List<Route> importBusRouteData(String filePath) throws IOException {
	log.info("Reading route data from {}", filePath);
	
	List<Route> routes = new ArrayList<Route>();	
	
	try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
	    lines.skip(1)
	    .forEach(line -> {
		Route route = new Route();
		
		String[] tokens = line.split("\\s");		
		Arrays.stream(tokens).skip(1).mapToInt(stationId -> Integer.parseInt(stationId))
		.forEach(stationId -> route.addStation(stationId));
		
		routes.add(route);
		log.info("Added route {}", route.toString());
	    });
	    
	    return routes;	    
	} catch (IOException e) {
	    log.error("Error while reading route data: {}", e.getMessage());
	    throw e;
	}
    }
}
