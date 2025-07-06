package dev.orhantugrul.craftgate.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NoSha256AlgorithmExceptionTest {
  @Test
  void shouldHaveDefaultMessage() {
    assertThat(new NoSha256AlgorithmException().getMessage())
        .isEqualTo("SHA-256 algorithm not found");
  }
}
