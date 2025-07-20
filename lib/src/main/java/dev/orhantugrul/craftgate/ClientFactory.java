package dev.orhantugrul.craftgate;

import dev.orhantugrul.craftgate.backaccounttracking.BankAccountTrackingClient;
import dev.orhantugrul.craftgate.common.data.Credentials;
import dev.orhantugrul.craftgate.common.data.Options;

public record ClientFactory(Credentials credentials, Options options) {
  public BankAccountTrackingClient bankAccountTracking() {
    return new BankAccountTrackingClient(credentials, options);
  }
}
