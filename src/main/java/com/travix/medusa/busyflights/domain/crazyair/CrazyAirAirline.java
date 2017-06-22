package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.ExternalAirline;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class CrazyAirAirline implements ExternalAirline {
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

  //VisibleForTesting
  static CrazyAirRequest toCrazyAirRequest(BusyFlightsRequest busyFlightsRequest) {
    CrazyAirRequest crazyAirRequest = new CrazyAirRequest();

    crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
    crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
    crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
    crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
    crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());

    return crazyAirRequest;
  }

  //VisibleForTesting
  static BusyFlightsResponse toBusyFlightsResponse(CrazyAirResponse crazyAirResponse) {
    BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();

    busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
    busyFlightsResponse.setSupplier("CrazyAir");
    busyFlightsResponse.setFare(crazyAirResponse.getPrice());
    busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
    busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
    busyFlightsResponse.setDepartureDate(toIsoDateTime(crazyAirResponse.getDepartureDate()));
    busyFlightsResponse.setArrivalDate(toIsoDateTime(crazyAirResponse.getArrivalDate()));

    return busyFlightsResponse;
  }

  private static String toIsoDateTime(String isoLocalDateTime) {
    return LocalDateTime.parse(isoLocalDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .format(DateTimeFormatter.ISO_DATE_TIME);
  }
}
