package com.travix.medusa.busyflights.domain.toughjet;

public interface ToughJetClient {
  ToughJetResponse[] get(ToughJetRequest toughJetRequest);
}
