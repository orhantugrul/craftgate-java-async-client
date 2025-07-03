package io.craftgate.common;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;

public final class Crypto {
  private Crypto() {
    throw new IllegalStateException();
  }

  /**
   * Encodes the given string into its Base64 representation.
   *
   * @param value the string to be encoded
   * @return the Base64 encoded representation of the input string
   */
  public static String base64(final String value) {
    final var bytes = value.getBytes();
    return Base64.getEncoder().encodeToString(bytes);
  }

  /**
   * Computes the SHA-256 hash of the specified string and returns its hexadecimal representation.
   *
   * @param value the input string to hash
   * @return the hexadecimal representation of the computed SHA-256 hash
   * @throws NoSha256AlgorithmException if the SHA-256 algorithm is not available in the
   *                                    environment
   */
  public static String sha256(final String value) {
    try {
      final var bytes = value.getBytes();
      final var digest = MessageDigest.getInstance("SHA-256").digest(bytes);
      return HexFormat.of().formatHex(digest);
    } catch (final NoSuchAlgorithmException e) {
      throw new NoSha256AlgorithmException();
    }
  }

  public static String sign(
      final Credentials credentials,
      final String url,
      final Object payload,
      final String random) {
    final var secret = credentials.apiKey() + credentials.secretKey();
    final var decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8);
    final var json = Optional.ofNullable(payload).map(Json::to).orElse("");

    final var encode = Crypto.base64(secret + decodedUrl + json + random);
    return Crypto.sha256(encode);
  }
}

class NoSha256AlgorithmException extends RuntimeException {
  NoSha256AlgorithmException() {
    super("SHA-256 algorithm not found");
  }
}
