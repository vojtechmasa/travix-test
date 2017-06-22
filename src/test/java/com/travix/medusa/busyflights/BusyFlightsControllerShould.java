package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.ExternalAirline;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class BusyFlightsControllerShould {
  private final ExternalAirline fakeExternalAirline1 = busyFlightsRequest -> {
    BusyFlightsResponse busyFlightsResponse1 = new BusyFlightsResponse();
    busyFlightsResponse1.setFare(20);
    BusyFlightsResponse busyFlightsResponse2 = new BusyFlightsResponse();
    busyFlightsResponse2.setFare(10);

    return Arrays.asList(busyFlightsResponse1, busyFlightsResponse2);
  };

  private final ExternalAirline fakeExternalAirline2 = busyFlightsRequest -> {
    BusyFlightsResponse busyFlightsResponse1 = new BusyFlightsResponse();
    busyFlightsResponse1.setFare(5);
    BusyFlightsResponse busyFlightsResponse2 = new BusyFlightsResponse();
    busyFlightsResponse2.setFare(30);

    return Arrays.asList(busyFlightsResponse1, busyFlightsResponse2);
  };

  @Test
  public void returnSortedFlightsFromExternalAirlines_inAscendingOrder_pricesWithTwoDecimals() {
    BusyFlightsController busyFlightsController = new BusyFlightsController(Arrays.asList(fakeExternalAirline1, fakeExternalAirline2));

    List<BusyFlightsResponse> busyFlightsResponses = busyFlightsController.get(null);

    List<String> prices = busyFlightsResponses.stream()
        .map(BusyFlightsResponse::getFare)
        .collect(toList());

    assertThat(prices, contains("5.00", "10.00", "20.00", "30.00"));
  }
}