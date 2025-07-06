package dev.orhantugrul.craftgate.common.data;

import java.util.Optional;

public record Options(String base, String language) {
  public Options {
    base = Optional.ofNullable(base).orElse("https://api.craftgate.io");
    language = Optional.ofNullable(language).orElse("en");
  }
}
