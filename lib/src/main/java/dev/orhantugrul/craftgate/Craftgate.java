package dev.orhantugrul.craftgate;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.orhantugrul.craftgate.backaccounttracking.BankAccountTrackingClient;
import dev.orhantugrul.craftgate.common.data.Credentials;
import dev.orhantugrul.craftgate.common.data.Options;

public class Craftgate {
  private final Injector injector;

  public Craftgate(final Credentials credentials, final Options options) {
    final var module = new CraftgateModule(credentials, options);
    this.injector = Guice.createInjector(module);
  }

  public BankAccountTrackingClient bankAccountTrackingClient() {
    return injector.getInstance(BankAccountTrackingClient.class);
  }
}
