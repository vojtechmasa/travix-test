package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CrazyAirAirlineShould {

  private CrazyAirRequest crazyAirRequest;
  private CrazyAirResponse crazyAirResponse;
  private BusyFlightsRequest busyFlightsRequest;
  private BusyFlightsResponse busyFlightsResponse;

  private CrazyAirClient fakeCrazyAirClient = (crazyAirRequest -> new CrazyAirResponse[] {crazyAirResponse});

  @Before
  public void setUp() {
    crazyAirRequest = new CrazyAirRequest();
    crazyAirRequest.setOrigin("AHA");
    crazyAirRequest.setDestination("HAH");
    crazyAirRequest.setDepartureDate("2016-05-06");
    crazyAirRequest.setReturnDate("2016-05-07");
    crazyAirRequest.setPassengerCount(3);

    crazyAirResponse = new CrazyAirResponse();
    crazyAirResponse.setAirline("Air1");
    crazyAirResponse.setPrice(20);
    crazyAirResponse.setCabinclass("cabinclass1");
    crazyAirResponse.setDepartureAirportCode("AHA");
    crazyAirResponse.setDestinationAirportCode("HAH");
    crazyAirResponse.setDepartureDate("2016-01-02T12:00:00");
    crazyAirResponse.setArrivalDate("2016-02-03T12:00:00");

    busyFlightsRequest = new BusyFlightsRequest();
    busyFlightsRequest.setOrigin("AHA");
    busyFlightsRequest.setDestination("HAH");
    busyFlightsRequest.setDepartureDate("2016-05-06");
    busyFlightsRequest.setReturnDate("2016-05-07");
    busyFlightsRequest.setNumberOfPassengers(3);

    busyFlightsResponse = new BusyFlightsResponse();
    busyFlightsResponse.setAirline("Air1");
    busyFlightsResponse.setSupplier("CrazyAir");
    busyFlightsResponse.setFare(20);
    busyFlightsResponse.setDepartureAirportCode("AHA");
    busyFlightsResponse.setDestinationAirportCode("HAH");
    busyFlightsResponse.setDepartureDate("2016-01-02T12:00:00");
    busyFlightsResponse.setArrivalDate("2016-02-03T12:00:00");
  }

  @Test
  public void convertCorrectly_fromBusyFlightRequest_toCrazyAirRequest() {
    //WHEN
    CrazyAirRequest actualCrazyAirRequest = CrazyAirAirline.toCrazyAirRequest(busyFlightsRequest);

    //THEN
    CrazyAirRequest expectedCrazyAirRequest = crazyAirRequest;

    assertThat(actualCrazyAirRequest, is(equalTo(expectedCrazyAirRequest)));
  }

  @Test
  public void convertCorrectly_fromCrazyAirResponse_toBusyFlightsResponse() {
    //WHEN
    BusyFlightsResponse actualBusyFlightsResponse = CrazyAirAirline.toBusyFlightsResponse(crazyAirResponse);

    //THEN
    BusyFlightsResponse expectedBusyFlightResponse = busyFlightsResponse;

    assertThat(actualBusyFlightsResponse, is(equalTo(expectedBusyFlightResponse)));
  }

  @Test
  public void getFlights_providedByCrazyAir() {
    //WHEN
    Collection<BusyFlightsResponse> actualFlights = new CrazyAirAirline(fakeCrazyAirClient).getFlights(busyFlightsRequest);

    //THEN
    assertThat(actualFlights, contains(busyFlightsResponse));


  }

}