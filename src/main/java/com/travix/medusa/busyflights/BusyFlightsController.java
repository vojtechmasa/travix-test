package com.travix.medusa.busyflights;


import com.travix.medusa.busyflights.domain.ExternalAirline;
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

  private final Collection<ExternalAirline> externalAirlines;

  @Autowired
  public BusyFlightsController(Collection<ExternalAirline> externalAirlines) {
    this.externalAirlines = externalAirlines;
  }

  @RequestMapping(path = "/busyFlights", method = RequestMethod.GET)
  List<BusyFlightsResponse> get(BusyFlightsRequest busyFlightsRequest) {

    //TODO move to a service layer
    return externalAirlines.stream()
        .map(externalAirline -> externalAirline.getFlights(busyFlightsRequest))
        .flatMap(Collection::stream)
        .sorted(Comparator.comparingDouble(BusyFlightsResponse::fareAsDouble))
        .collect(toList());
  }
}
