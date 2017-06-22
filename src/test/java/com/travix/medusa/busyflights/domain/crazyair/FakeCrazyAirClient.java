package com.travix.medusa.busyflights.domain.crazyair;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@Profile("test")
public class FakeCrazyAirClient implements CrazyAirClient {
  @Override
  public CrazyAirResponse[] get(CrazyAirRequest crazyAirRequest) {
    CrazyAirResponse crazyAirResponse1 = new CrazyAirResponse();
    crazyAirResponse1.setAirline("A1");
    crazyAirResponse1.setPrice(20.2);
    crazyAirResponse1.setDepartureAirportCode(crazyAirRequest.getOrigin());
    crazyAirResponse1.setDestinationAirportCode(crazyAirRequest.getDestination());
    crazyAirResponse1.setDepartureDate(toIsoDateTime(crazyAirRequest.getDepartureDate()));
    crazyAirResponse1.setArrivalDate(toIsoDateTime(crazyAirRequest.getReturnDate()));

    CrazyAirResponse crazyAirResponse2 = new CrazyAirResponse();
    crazyAirResponse2.setAirline("A2");
    crazyAirResponse2.setPrice(25.2);
    crazyAirResponse2.setDepartureAirportCode(crazyAirRequest.getOrigin());
    crazyAirResponse2.setDestinationAirportCode(crazyAirRequest.getDestination());
    crazyAirResponse2.setDepartureDate(toIsoDateTime(crazyAirRequest.getDepartureDate()));
    crazyAirResponse2.setArrivalDate(toIsoDateTime(crazyAirRequest.getReturnDate()));

    return new CrazyAirResponse[] {
        crazyAirResponse1, crazyAirResponse2
    };
  }

  private static String toIsoDateTime(String isoLocalDate) {
    return LocalDateTime.of(LocalDate.parse(isoLocalDate, DateTimeFormatter.ISO_LOCAL_DATE), LocalTime.NOON)
        .format(DateTimeFormatter.ISO_DATE_TIME);
  }
}
