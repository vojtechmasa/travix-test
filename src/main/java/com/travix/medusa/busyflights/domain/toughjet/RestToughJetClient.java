package com.travix.medusa.busyflights.domain.toughjet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("prod")
public class RestToughJetClient implements ToughJetClient {
  private final String toughJetUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public RestToughJetClient(@Value("${airline.url.toughJet}") String toughJetUrl, RestTemplate restTemplate) {
    this.toughJetUrl = toughJetUrl;
    this.restTemplate = restTemplate;
  }

  @Override
  public ToughJetResponse[] get(ToughJetRequest toughJetRequest) {
    return restTemplate.getForObject(toughJetUrl, ToughJetResponse[].class, toughJetUrl);
  }
}
