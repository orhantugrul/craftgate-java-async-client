package dev.orhantugrul.craftgate.http;


import dev.orhantugrul.craftgate.common.util.Json;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpClient {
  private HttpClient() {
    throw new IllegalStateException();
  }

  /**
   * Sends an HTTP GET request to the specified URL with the provided headers and deserializes the
   * response into an object of the specified type.
   *
   * @param url     the target URL for the HTTP GET request.
   * @param headers a map of key-value pairs representing custom HTTP headers to be included in the
   *                request.
   * @param clazz   the class of the type to deserialize the HTTP response into.
   * @param <T>     the type of the response object.
   * @return a CompletableFuture containing an object of type T, representing the deserialized
   * response received from the server.
   */
  public static <T> CompletableFuture<T> get(
      final String url,
      final Map<String, String> headers,
      final Class<T> clazz) {
    final var request = HttpUtil.ofRequest(url, headers).GET().build();

    return java.net.http.HttpClient.newHttpClient()
        .sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpUtil::ofResponse)
        .thenApply(response -> Json.from(response, clazz));
  }

  /**
   * Sends an HTTP POST request to the specified URL with the provided payload, headers, and an
   * expected response type. The request body is created by serializing the payload into JSON, and
   * the response is deserialized into an object of the specified type.
   *
   * @param url     the target URL for the HTTP POST request.
   * @param payload the payload to be sent in the HTTP request body, serialized as JSON.
   * @param headers a map of key-value pairs representing custom HTTP headers to be included in the
   *                request.
   * @param clazz   the class of the type to deserialize the HTTP response into.
   * @param <T>     the type of the response object.
   * @return a CompletableFuture containing an object of type T, representing the deserialized
   * response received from the server.
   */
  public static <T> CompletableFuture<T> post(
      final String url,
      final Object payload,
      final Map<String, String> headers,
      final Class<T> clazz) {
    final var json = Json.to(payload);
    final var body = BodyPublishers.ofString(json, StandardCharsets.UTF_8);
    final var request = HttpUtil.ofRequest(url, headers).POST(body).build();

    return java.net.http.HttpClient.newHttpClient()
        .sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpUtil::ofResponse)
        .thenApply(response -> Json.from(response, clazz));
  }

  /**
   * Sends an HTTP PUT request to the specified URL with the provided payload, headers, and an
   * expected response type. The request body is created by serializing the payload into JSON, and
   * the response is deserialized into an object of the specified type.
   *
   * @param url     the target URL for the HTTP PUT request.
   * @param payload the payload to be sent in the HTTP request body, serialized as JSON.
   * @param headers a map of key-value pairs representing custom HTTP headers to be included in the
   *                request.
   * @param clazz   the class of the type to deserialize the HTTP response into.
   * @param <T>     the type of the response object.
   * @return a CompletableFuture containing an object of type T, representing the deserialized
   * response received from the server.
   */
  public static <T> CompletableFuture<T> put(
      final String url,
      final Object payload,
      final Map<String, String> headers,
      final Class<T> clazz) {
    final var json = Json.to(payload);
    final var body = BodyPublishers.ofString(json, StandardCharsets.UTF_8);
    final var request = HttpUtil.ofRequest(url, headers).PUT(body).build();

    return java.net.http.HttpClient.newHttpClient()
        .sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpUtil::ofResponse)
        .thenApply(response -> Json.from(response, clazz));
  }

  /**
   * Sends an HTTP DELETE request to the specified URL with the provided headers and handles the
   * response. The response body is expected to be empty or ignored. Any non-successful HTTP status
   * codes will result in an exception.
   *
   * @param url     the target URL for the HTTP DELETE request.
   * @param headers a map of key-value pairs representing HTTP headers to be included in the
   *                request.
   * @return a CompletableFuture representing the pending computation for the HTTP DELETE request.
   * It resolves to {@code Void} upon successful completion or throws an exception if the request
   * fails.
   */
  public static CompletableFuture<Void> delete(
      final String url,
      final Map<String, String> headers) {
    final var request = HttpUtil.ofRequest(url, headers).DELETE().build();

    return java.net.http.HttpClient.newHttpClient()
        .sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpUtil::ofResponse)
        .thenApply(response -> Json.from(response, Void.class));
  }
}