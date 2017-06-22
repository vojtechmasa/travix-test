package com.travix.medusa.busyflights.domain.crazyair;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
    crazyAirResponse1.setDepartureDate(crazyAirRequest.getDepartureDate());
    crazyAirResponse1.setArrivalDate(crazyAirRequest.getReturnDate());

    CrazyAirResponse crazyAirResponse2 = new CrazyAirResponse();
    crazyAirResponse1.setAirline("A2");
    crazyAirResponse1.setPrice(25.2);
    crazyAirResponse1.setDepartureAirportCode(crazyAirRequest.getOrigin());
    crazyAirResponse1.setDestinationAirportCode(crazyAirRequest.getDestination());
    crazyAirResponse1.setDepartureDate(crazyAirRequest.getDepartureDate());
    crazyAirResponse1.setArrivalDate(crazyAirRequest.getReturnDate());

    return new CrazyAirResponse[] {
        crazyAirResponse1, crazyAirResponse2
    };
  }
}
