package dev.orhantugrul.craftgate;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dev.orhantugrul.craftgate.common.data.Credentials;
import dev.orhantugrul.craftgate.common.data.Options;

public final class CraftgateModule extends AbstractModule {
  private final Credentials credentials;
  private final Options options;

  public CraftgateModule(final Credentials credentials, final Options options) {
    this.credentials = credentials;
    this.options = options;
  }

  @Override
  protected void configure() {
    bind(Credentials.class).toInstance(credentials);
    bind(Options.class).toInstance(options);
  }

  @Provides
  @Singleton
  ClientFactory provideClientFactory(final Credentials credentials, final Options options) {
    return new ClientFactory(credentials, options);
  }
}
