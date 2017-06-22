package com.travix.medusa.busyflights.domain;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.Collection;

/**
 * Represents an external airline which flights need to be aggregated.
 */
public interface Airline {
  Collection<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest);
}
