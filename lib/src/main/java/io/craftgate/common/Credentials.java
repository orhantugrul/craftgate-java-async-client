package io.craftgate.common;

import java.util.Objects;

public record Credentials(String apiKey, String secretKey) {
  public Credentials {
    Objects.requireNonNull(apiKey);
    Objects.requireNonNull(secretKey);
  }
}
