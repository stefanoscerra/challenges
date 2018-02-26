package com.goeuro.busroute;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.goeuro.busroute.importers.BusRouteImporter;
import com.goeuro.busroute.services.implementations.BusRouteService;
import com.goeuro.busroute.services.implementations.Route;

@Component
public class StartupHandler implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String ROUTE_FILEPATH_ARGUMENT = "routes.filepath";

    @Autowired
    Environment environment;

    @Autowired
    private BusRouteImporter busRouteImporter;

    @Autowired
    private BusRouteService busRouteService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
	String routesFilePath = environment.getProperty(ROUTE_FILEPATH_ARGUMENT);
	
	if (routesFilePath != null) {
	    try {
		runBusRouteImporter(routesFilePath);
	    } catch (IOException e) {
		log.error("Error while importing route data: {}", e.getMessage());
	    }
	} else {
	    log.warn("No route data file specified!");
	}
    }

    private void runBusRouteImporter(String routesFilePath) throws IOException {
	List<Route> routes = busRouteImporter.importBusRouteData(routesFilePath);
	busRouteService.setRoutes(routes);
    }
}
