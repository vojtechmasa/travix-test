package com.travix.medusa.busyflights;


import com.travix.medusa.busyflights.domain.Airline;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class BusyFlightsController {

  private final Collection<Airline> airlines;

  @Autowired
  public BusyFlightsController(Collection<Airline> airlines) {
    this.airlines = airlines;
  }

  @RequestMapping(path = "/busyFlights", method = RequestMethod.GET)
  List<BusyFlightsResponse> get(BusyFlightsRequest busyFlightsRequest) {

    //TODO move to a service layer
    return airlines.stream()
        .map(airline -> airline.getFlights(busyFlightsRequest))
        .flatMap(Collection::stream)
        .sorted(Comparator.comparingDouble(BusyFlightsResponse::getFare))
        .collect(toList());
  }
}
