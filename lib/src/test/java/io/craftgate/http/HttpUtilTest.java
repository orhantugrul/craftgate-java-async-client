package io.craftgate.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.craftgate.common.util.Json;
import java.net.http.HttpResponse;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HttpUtilTest {
  @Test
  void shouldBuildRequestWithHeaders() {
    final var url = "https://api.example.com";
    final var headers = Map.of("Authorization", "Bearer token");
    final var builder = HttpUtil.ofRequest(url, headers);
    final var request = builder.GET().build();

    assertThat(request.uri().toString()).hasToString(url);
    assertThat(request.headers().firstValue("Authorization")).contains("Bearer token");
    assertThat(request.headers().firstValue("Content-Type")).contains("application/json");
    assertThat(request.headers().firstValue("Accept")).contains("application/json");
  }

  @Test
  void shouldReturnBodyOnSuccessResponse() {
    final var response = mock(HttpResponse.class);
    
    when(response.statusCode()).thenReturn(200);
    when(response.body()).thenReturn("{\"foo\":\"bar\"}");

    final var body = HttpUtil.ofResponse(response);
    assertThat(body).isEqualTo("{\"foo\":\"bar\"}");
  }

  @Test
  void shouldThrowHttpExceptionWithDetailsOnErrorResponse() {
    final var response = mock(HttpResponse.class);
    final var json =
        Json.to(
            Map.of(
                "errors",
                Map.of(
                    "errorCode", "123",
                    "errorDescription", "desc",
                    "errorGroup", "group")));

    when(response.statusCode()).thenReturn(400);
    when(response.body()).thenReturn(json);

    assertThatThrownBy(() -> HttpUtil.ofResponse(response))
        .isInstanceOf(HttpException.class)
        .hasFieldOrPropertyWithValue("code", "123")
        .hasFieldOrPropertyWithValue("description", "desc")
        .hasFieldOrPropertyWithValue("group", "group");
  }

  @Test
  void shouldThrowGenericHttpExceptionOnErrorResponseWithoutDetails() {
    final var response = mock(HttpResponse.class);

    when(response.statusCode()).thenReturn(400);
    when(response.body()).thenReturn("{}");

    assertThatThrownBy(() -> HttpUtil.ofResponse(response))
        .isInstanceOf(HttpException.class)
        .hasFieldOrPropertyWithValue("code", "0");
  }
}
