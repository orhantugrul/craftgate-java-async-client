package io.craftgate.common.exception;

public class NoSha256AlgorithmException extends RuntimeException {
  public NoSha256AlgorithmException() {
    super("SHA-256 algorithm not found");
  }
}
