package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ToughJetAirlineShould {

  private ToughJetRequest toughJetRequest;
  private ToughJetResponse toughJetResponse;
  private BusyFlightsRequest busyFlightsRequest;
  private BusyFlightsResponse busyFlightsResponse;

  private ToughJetClient fakeToughJetClient = (toughJetRequest -> new ToughJetResponse[] {toughJetResponse});

  @Before
  public void setUp() {
    toughJetRequest = new ToughJetRequest();
    toughJetRequest.setFrom("AHA");
    toughJetRequest.setTo("HAH");
    toughJetRequest.setOutboundDate("2016-05-06");
    toughJetRequest.setInboundDate("2016-05-07");
    toughJetRequest.setNumberOfAdults(3);

    toughJetResponse = new ToughJetResponse();
    toughJetResponse.setCarrier("Air1");
    toughJetResponse.setBasePrice(20);
    toughJetResponse.setTax(20);
    toughJetResponse.setDiscount(5);
    toughJetResponse.setDepartureAirportName("AHA");
    toughJetResponse.setArrivalAirportName("HAH");
    toughJetResponse.setOutboundDateTime("2016-01-02T10:15:30Z");
    toughJetResponse.setInboundDateTime("2016-02-03T10:15:30Z");

    busyFlightsRequest = new BusyFlightsRequest();
    busyFlightsRequest.setOrigin("AHA");
    busyFlightsRequest.setDestination("HAH");
    busyFlightsRequest.setDepartureDate("2016-05-06");
    busyFlightsRequest.setReturnDate("2016-05-07");
    busyFlightsRequest.setNumberOfPassengers(3);

    busyFlightsResponse = new BusyFlightsResponse();
    busyFlightsResponse.setAirline("Air1");
    busyFlightsResponse.setSupplier("ToughJet");
    busyFlightsResponse.setFare(22.8);
    busyFlightsResponse.setDepartureAirportCode("AHA");
    busyFlightsResponse.setDestinationAirportCode("HAH");
    busyFlightsResponse.setDepartureDate("2016-01-02T10:15:30Z[UTC]");
    busyFlightsResponse.setArrivalDate("2016-02-03T10:15:30Z[UTC]");
  }

  @Test
  public void convertCorrectly_fromBusyFlightRequest_toCrazyAirRequest() {
    //WHEN
    ToughJetRequest actualToughJetRequest = ToughJetAirline.toToughJetRequest(busyFlightsRequest);

    //THEN
    ToughJetRequest expectedToughJetRequest = toughJetRequest;

    assertThat(actualToughJetRequest, is(equalTo(expectedToughJetRequest)));
  }

  @Test
  public void convertCorrectly_fromCrazyAirResponse_toBusyFlightsResponse() {
    //WHEN
    BusyFlightsResponse actualBusyFlightsResponse = ToughJetAirline.toBusyFlightsResponse(toughJetResponse);

    //THEN
    BusyFlightsResponse expectedBusyFlightResponse = busyFlightsResponse;

    assertThat(actualBusyFlightsResponse, is(equalTo(expectedBusyFlightResponse)));
  }

  @Test
  public void getFlights_providedByCrazyAir() {
    //WHEN
    Collection<BusyFlightsResponse> actualFlights = new ToughJetAirline(fakeToughJetClient).getFlights(busyFlightsRequest);

    //THEN
    assertThat(actualFlights, contains(busyFlightsResponse));


  }

}