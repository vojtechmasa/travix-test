package com.travix.medusa.busyflights.domain.toughjet;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
@Profile("test")
public class FakeToughJetClient implements ToughJetClient {
  @Override
  public ToughJetResponse[] get(ToughJetRequest toughJetRequest) {
    ToughJetResponse toughJetResponse1 = new ToughJetResponse();
    toughJetResponse1.setCarrier("X2");
    toughJetResponse1.setBasePrice(35);
    toughJetResponse1.setDiscount(20);
    toughJetResponse1.setDepartureAirportName(toughJetRequest.getFrom());
    toughJetResponse1.setArrivalAirportName(toughJetRequest.getTo());
    toughJetResponse1.setInboundDateTime(toughJetRequest.getInboundDate());
    toughJetResponse1.setOutboundDateTime(toughJetRequest.getOutboundDate());

    ToughJetResponse toughJetResponse2 = new ToughJetResponse();
    toughJetResponse1.setCarrier("X2");
    toughJetResponse1.setBasePrice(35);
    toughJetResponse1.setDiscount(20);
    toughJetResponse1.setDepartureAirportName(toughJetRequest.getFrom());
    toughJetResponse1.setArrivalAirportName(toughJetRequest.getTo());
    toughJetResponse1.setInboundDateTime(toInstantFormat(toughJetRequest.getInboundDate()));
    toughJetResponse1.setOutboundDateTime(toInstantFormat(toughJetRequest.getOutboundDate()));

    return new ToughJetResponse[] {
        toughJetResponse1,
        toughJetResponse2
    };
  }

  private static String toInstantFormat(String isoLocalDate) {
    LocalDateTime localDateTime = LocalDateTime.of(
        LocalDate.parse(isoLocalDate, DateTimeFormatter.ISO_LOCAL_DATE),
        LocalTime.NOON);
    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

    return zonedDateTime
        .format(DateTimeFormatter.ISO_INSTANT);
  }
}
