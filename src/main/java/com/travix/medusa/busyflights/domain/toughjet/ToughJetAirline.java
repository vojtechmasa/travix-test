package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.Airline;
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
public class ToughJetAirline implements Airline {
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

  private ToughJetRequest toToughJetRequest(BusyFlightsRequest busyFlightsRequest) {
    ToughJetRequest toughJetRequest = new ToughJetRequest();

    toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
    toughJetRequest.setTo(busyFlightsRequest.getDestination());
    toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
    toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
    toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());

    return toughJetRequest;
  }

  private static BusyFlightsResponse toBusyFlightsResponse(ToughJetResponse toughJetResponse) {
    return new BusyFlightsResponse(
        toughJetResponse.getCarrier(),
        "toughJet",
        calculateFinalPrice(toughJetResponse),
        toughJetResponse.getDepartureAirportName(),
        toughJetResponse.getArrivalAirportName(),
        toIsoLocalDateTime(toughJetResponse.getOutboundDateTime()),
        toIsoLocalDateTime(toughJetResponse.getInboundDateTime())
    );
  }

  private static double calculateFinalPrice(ToughJetResponse toughJetResponse) {
    return toughJetResponse.getBasePrice() + toughJetResponse.getTax() * (1 - toughJetResponse.getDiscount()/100);
  }

  private static String toIsoLocalDateTime(String isoInstant) {
    Instant instant = Instant.parse(isoInstant);

    return DateTimeFormatter.ISO_DATE_TIME
        .withZone(ZoneId.systemDefault())
        .format(instant);
  }
}
