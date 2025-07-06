package dev.orhantugrul.craftgate.http;

public class HttpException extends RuntimeException {
  public final String code;
  public final String description;
  public final String group;

  public HttpException() {
    this("0", "An error occurred.", "Unknown");
  }

  public HttpException(final String code, final String description, final String group) {
    super(description);
    this.code = code;
    this.description = description;
    this.group = group;
  }
}
