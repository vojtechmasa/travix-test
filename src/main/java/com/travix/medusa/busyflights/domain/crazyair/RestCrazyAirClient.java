package com.travix.medusa.busyflights.domain.crazyair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("prod")
public class RestCrazyAirClient implements CrazyAirClient {
  private final String crazyAirUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public RestCrazyAirClient(@Value("${airline.url.crazyAir}") String crazyAirUrl, RestTemplate restTemplate) {
    this.crazyAirUrl = crazyAirUrl;
    this.restTemplate = restTemplate;
  }

  @Override
  public CrazyAirResponse[] get(CrazyAirRequest crazyAirRequest) {
    return restTemplate.getForObject(crazyAirUrl, CrazyAirResponse[].class, crazyAirRequest);
  }
}
