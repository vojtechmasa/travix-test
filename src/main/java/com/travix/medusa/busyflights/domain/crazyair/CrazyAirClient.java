package com.travix.medusa.busyflights.domain.crazyair;

public interface CrazyAirClient {
  CrazyAirResponse[] get(CrazyAirRequest crazyAirRequest);
}
