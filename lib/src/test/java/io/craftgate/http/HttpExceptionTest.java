package io.craftgate.http;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HttpExceptionTest {
  @Test
  void shouldUseDefaultConstructor() {
    final var exception = new HttpException();

    assertThat(exception.code).isEqualTo("0");
    assertThat(exception.description).isEqualTo("An error occurred.");
    assertThat(exception.group).isEqualTo("Unknown");
    assertThat(exception.getMessage()).isEqualTo("An error occurred.");
  }

  @Test
  void shouldUseCustomConstructor() {
    final var exception = new HttpException("404", "Not Found", "ClientError");

    assertThat(exception.code).isEqualTo("404");
    assertThat(exception.description).isEqualTo("Not Found");
    assertThat(exception.group).isEqualTo("ClientError");
    assertThat(exception.getMessage()).isEqualTo("Not Found");
  }
}
