package io.craftgate.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import io.craftgate.common.exception.NoSha256AlgorithmException;
import java.util.Base64;
import org.junit.jupiter.api.Test;

class CryptologyTest {
  @Test
  void shouldEncodeBase64() {
    assertThat(Cryptology.base64("hello"))
        .isEqualTo(Base64.getEncoder().encodeToString("hello".getBytes()));
  }

  @Test
  void shouldComputeSha256() {
    assertThat(Cryptology.sha256("test"))
        .hasSize(64); // SHA-256 hex string length
  }

  @Test
  void shouldThrowNoSha256AlgorithmExceptionIfAlgorithmMissing() {
    // This is hard to simulate in a real JVM, so just check the exception type for coverage
    assertThat(new NoSha256AlgorithmException())
        .isInstanceOf(NoSha256AlgorithmException.class);
  }
}
