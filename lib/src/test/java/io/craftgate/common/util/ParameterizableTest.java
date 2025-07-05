package io.craftgate.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

class ParameterizableTest {
  static class DummyParam implements Parameterizable {
    @Override
    public Map<String, String> toParameters() {
      return Map.of("foo", "bar", "baz", "qux");
    }
  }

  @Test
  void shouldReturnParameters() {
    assertThat(new DummyParam().toParameters())
        .containsEntry("foo", "bar")
        .containsEntry("baz", "qux");
  }
}
