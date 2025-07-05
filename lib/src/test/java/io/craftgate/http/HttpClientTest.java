package io.craftgate.http;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import java.util.Map;
import org.junit.jupiter.api.Test;

@WireMockTest
class HttpClientTest {
  @Test
  void shouldGetAndDeserializeResponse(final WireMockRuntimeInfo wireMock) {
    record Response(String foo) {}

    final var url = String.format("http://localhost:%d", wireMock.getHttpPort());
    final var headers = Map.of("Authorization", "Bearer token");

    stubFor(get("/").willReturn(okJson("{\"foo\":\"bar\"}")));

    final var future = HttpClient.get(url, headers, Response.class);
    assertThat(future.join()).isEqualTo(new Response("bar"));
  }

  @Test
  void shouldPostAndDeserializeResponse(final WireMockRuntimeInfo wireMock) {
    record Response(Boolean success) {}

    final var url = String.format("http://localhost:%d", wireMock.getHttpPort());
    final var payload = Map.of("foo", "bar");
    final var headers = Map.of("Authorization", "Bearer token");

    stubFor(post("/").willReturn(okJson("{\"success\": true}")));

    final var future = HttpClient.post(url, payload, headers, Response.class);
    assertThat(future.join()).isEqualTo(new Response(true));
  }

  @Test
  void shouldPutAndDeserializeResponse(final WireMockRuntimeInfo wireMock) {
    record Response(Boolean success) {}

    final var url = String.format("http://localhost:%d", wireMock.getHttpPort());
    final var payload = Map.of("foo", "bar");
    final var headers = Map.of("Authorization", "Bearer token");

    stubFor(put("/").willReturn(okJson("{\"success\":true}")));

    final var future = HttpClient.put(url, payload, headers, Response.class);
    assertThat(future.join()).isEqualTo(new Response(true));
  }

  @Test
  void shouldDeleteAndReturnVoid(final WireMockRuntimeInfo wireMock) {
    final var url = String.format("http://localhost:%d", wireMock.getHttpPort());
    final var headers = Map.of("Authorization", "Bearer token");

    stubFor(delete("/").willReturn(ok()));

    final var future = HttpClient.delete(url, headers);
    assertThat(future.join()).isNull();
  }
}
