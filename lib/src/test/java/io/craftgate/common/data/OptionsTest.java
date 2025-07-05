package io.craftgate.common.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OptionsTest {
  @Test
  void shouldUseDefaultValuesWhenNullsProvided() {
    final var actual = new Options(null, null);

    assertThat(actual.base()).isEqualTo("https://api.craftgate.io");
    assertThat(actual.language()).isEqualTo("en");
  }

  @Test
  void shouldUseProvidedValues() {
    final var actual = new Options("https://custom.url", "tr");

    assertThat(actual.base()).isEqualTo("https://custom.url");
    assertThat(actual.language()).isEqualTo("tr");
  }
}
