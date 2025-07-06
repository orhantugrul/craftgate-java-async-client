package dev.orhantugrul.craftgate.http;

import com.google.gson.reflect.TypeToken;
import dev.orhantugrul.craftgate.common.util.Json;
import java.net.HttpURLConnection;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

final class HttpUtil {
  private HttpUtil() {
    throw new IllegalStateException();
  }

  /**
   * Creates an {@link HttpRequest.Builder} instance with the specified URL and headers. Initializes
   * the request builder with a timeout of 10 seconds and sets default headers including
   * "Content-Type" and "Accept" as "application/json". Additional headers provided in the input map
   * are also included in the request.
   *
   * @param url     the target URL for the HTTP request.
   * @param headers a map containing additional headers to set on the request. If null, no extra
   *                headers are added.
   * @return a configured {@link HttpRequest.Builder} for constructing the HTTP request.
   */
  static HttpRequest.Builder ofRequest(
      final String url,
      final Map<String, String> headers) {
    final var requestBuilder = java.net.http.HttpRequest.newBuilder()
        .uri(java.net.URI.create(url))
        .timeout(Duration.ofSeconds(10L))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json");

    Optional.ofNullable(headers).orElse(Map.of()).forEach(requestBuilder::header);
    return requestBuilder;
  }

  /**
   * Processes an HTTP response and returns its body content as a string. If the response status
   * code indicates an error (HTTP status 400 or above), it attempts to extract error details from
   * the response body. If the error details are found, it throws an {@link HttpException} with the
   * extracted information; otherwise, it throws a generic {@link HttpException}.
   *
   * @param response the HTTP response to process, containing both the status code and the body
   * @return the body of the HTTP response as a string if the status code is below 400
   * @throws HttpException if the response status code indicates an error, with additional error
   *                       details extracted from the response body if available
   */
  static String ofResponse(final HttpResponse<String> response) {
    if (response.statusCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
      final var type = new TypeToken<Map<String, Map<String, String>>>() {};
      final var body = Json.from(response.body(), type);

      if (body.containsKey("errors")) {
        final var errors = body.get("errors");
        final var code = errors.get("errorCode");
        final var description = errors.get("errorDescription");
        final var group = errors.get("errorGroup");

        throw new HttpException(code, description, group);
      }

      throw new HttpException();
    }

    return response.body();
  }
}
