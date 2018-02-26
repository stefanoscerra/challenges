package com.goeuro.busroute.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.busroute.services.implementations.Route;

import static org.junit.Assert.*;


public class RouteTest {

    private Route route;
    
    @Before
    public void setUp() {
	route = new Route();
    }    
    
    @Test
    public void testNoConnectionIfDepartureStationMissing() {
	route.addStation(2);
	route.addStation(3);
	boolean result = route.areConnected(1, 2);
	assertFalse(result);
    }
    
    @Test
    public void testNoConnectionIfArrivalStationMissing() {
	route.addStation(1);
	route.addStation(3);
	boolean result = route.areConnected(1, 2);
	assertFalse(result);
    }
    
    @Test
    public void testNoConnectionIfUnsuitableOrder() {
	route.addStation(2);
	route.addStation(1);
	
	boolean result = route.areConnected(1, 2);
	assertFalse(result);
    }
    
    @Test
    public void testDirectConnectionExists() {
	route.addStation(1);
	route.addStation(2);
	
	boolean result = route.areConnected(1, 2);
	assertTrue(result);
    }
    
    @Test
    public void testTransitiveConnectionExists() {
	route.addStation(1);
	route.addStation(2);
	route.addStation(3);
	
	boolean result = route.areConnected(1, 3);
	assertTrue(result);
    }
}
