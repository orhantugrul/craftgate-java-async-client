package dev.orhantugrul.craftgate.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import dev.orhantugrul.craftgate.common.data.Credentials;
import dev.orhantugrul.craftgate.common.data.Options;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RequestTest {
  static class DummyParam implements Parameterizable {
    @Override
    public Map<String, String> toParameters() {
      return Map.of("foo", "bar", "baz", "qux");
    }
  }

  @Test
  void shouldBuildUrlWithoutQuery() {
    final var options = new Options("https://api.example.com", "en");
    final var actual = Request.url(options, "/test");
    assertThat(actual).isEqualTo("https://api.example.com/test");
  }

  @Test
  void shouldBuildUrlWithQuery() {
    final var options = new Options("https://api.example.com", "en");
    final var actual = Request.url(options, "/test", new DummyParam());

    assertThat(actual)
        .startsWith("https://api.example.com/test?")
        .contains("foo=bar")
        .contains("baz=qux");
  }

  @Test
  void shouldBuildQueryString() {
    assertThat(Request.query(new DummyParam()))
        .startsWith("?")
        .contains("foo=bar")
        .contains("baz=qux");
  }

  @Test
  void shouldBuildHeaders() {
    final var credentials = new Credentials("api", "secret");
    final var options = new Options("https://api.example.com", "tr");
    final var url = "https://api.example.com/test";
    final var headers = Request.headers(url, credentials, options);

    assertThat(headers)
        .containsKeys("x-rnd-key", "x-signature")
        .containsEntry("x-api-key", credentials.apiKey())
        .containsEntry("x-api-key", "api")
        .containsEntry("x-auth-version", "v1")
        .containsEntry("x-client-version", "craftgate-java-client:1.0.69")
        .containsEntry("lang", "tr");
  }
}
