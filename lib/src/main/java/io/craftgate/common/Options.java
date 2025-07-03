package io.craftgate.common;

import java.util.Optional;

public record Options(String base, String language) {
  public Options {
    base = Optional.ofNullable(base).orElse("https://api.craftgate.io");
    language = Optional.ofNullable(language).orElse("en");
  }
}
