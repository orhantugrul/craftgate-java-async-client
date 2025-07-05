package io.craftgate.common.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CredentialsTest {
  @Test
  void shouldAssignValuesCorrectly() {
    final var actual = new Credentials("api-key", "secret-key");

    assertThat(actual.apiKey()).isEqualTo("api-key");
    assertThat(actual.secretKey()).isEqualTo("secret-key");
  }

  @Test
  void shouldThrowExceptionWhenApiKeyIsNull() {
    assertThatThrownBy(() -> new Credentials(null, "secret-key"))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrowExceptionWhenSecretKeyIsNull() {
    assertThatThrownBy(() -> new Credentials("api-key", null))
        .isInstanceOf(NullPointerException.class);
  }
}
