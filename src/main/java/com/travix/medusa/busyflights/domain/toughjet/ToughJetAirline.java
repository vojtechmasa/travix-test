package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.ExternalAirline;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class ToughJetAirline implements ExternalAirline {
  private final ToughJetClient toughJetClient;

  @Autowired
  public ToughJetAirline(ToughJetClient toughJetClient) {
    this.toughJetClient = toughJetClient;
  }

  @Override
  public Collection<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest) {
    ToughJetRequest toughJetRequest = toToughJetRequest(busyFlightsRequest);

    return Stream.of(toughJetClient.get(toughJetRequest))
        .map(ToughJetAirline::toBusyFlightsResponse)
        .collect(toList());
  }

  //VisibleForTesting
  static ToughJetRequest toToughJetRequest(BusyFlightsRequest busyFlightsRequest) {
    ToughJetRequest toughJetRequest = new ToughJetRequest();

    toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
    toughJetRequest.setTo(busyFlightsRequest.getDestination());
    toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
    toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
    toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());

    return toughJetRequest;
  }

  //VisibleForTesting
  static BusyFlightsResponse toBusyFlightsResponse(ToughJetResponse toughJetResponse) {
    BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();

    busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
    busyFlightsResponse.setSupplier("ToughJet");
    busyFlightsResponse.setFare(calculateFinalPrice(toughJetResponse));
    busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
    busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
    busyFlightsResponse.setDepartureDate(toIsoDateTime(toughJetResponse.getOutboundDateTime()));
    busyFlightsResponse.setArrivalDate(toIsoDateTime(toughJetResponse.getInboundDateTime()));

    return busyFlightsResponse;
  }

  private static double calculateFinalPrice(ToughJetResponse toughJetResponse) {
    double discount = toughJetResponse.getBasePrice()*(toughJetResponse.getDiscount()/100);
    double discountedPrice = toughJetResponse.getBasePrice() - discount;

    double tax = (discountedPrice/100)*toughJetResponse.getBasePrice();

    return discountedPrice + tax;
  }

  private static String toIsoDateTime(String isoInstant) {
    Instant instant = Instant.parse(isoInstant);

    return DateTimeFormatter.ISO_DATE_TIME
        .withZone(ZoneId.of("UTC"))
        .format(instant);
  }
}
