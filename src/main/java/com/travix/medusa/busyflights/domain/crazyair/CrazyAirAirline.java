package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.Airline;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class CrazyAirAirline implements Airline {
  private final CrazyAirClient crazyAirClient;

  @Autowired
  public CrazyAirAirline(CrazyAirClient crazyAirClient) {
    this.crazyAirClient = crazyAirClient;
  }

  @Override
  public Collection<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest) {
    CrazyAirRequest crazyAirRequest = toCrazyAirRequest(busyFlightsRequest);

    return Stream.of(crazyAirClient.get(crazyAirRequest))
        .map(CrazyAirAirline::toBusyFlightsResponse)
        .collect(toList());
  }

  private CrazyAirRequest toCrazyAirRequest(BusyFlightsRequest busyFlightsRequest) {
    CrazyAirRequest crazyAirRequest = new CrazyAirRequest();

    crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
    crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
    crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
    crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
    crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());

    return crazyAirRequest;
  }

  private static BusyFlightsResponse toBusyFlightsResponse(CrazyAirResponse crazyAirResponse) {
    return new BusyFlightsResponse(
        crazyAirResponse.getAirline(),
        "crazyAirline", //TODO extract as a dep / constant
        crazyAirResponse.getPrice(),
        crazyAirResponse.getDepartureAirportCode(),
        crazyAirResponse.getDestinationAirportCode(),
        crazyAirResponse.getDepartureDate(),
        crazyAirResponse.getArrivalDate()

    );
  }
}
