package dev.orhantugrul.craftgate.backaccounttracking;

import dev.orhantugrul.craftgate.common.data.Credentials;
import dev.orhantugrul.craftgate.common.data.Options;
import dev.orhantugrul.craftgate.common.util.Request;
import dev.orhantugrul.craftgate.http.HttpClient;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class BankAccountTrackingClient {
  private static final String ENDPOINT =
      "/bank-account-tracking/v1/merchant-bank-account-trackings";

  private final Credentials credentials;
  private final Options options;

  public BankAccountTrackingClient(final Credentials credentials, final Options options) {
    this.credentials = credentials;
    this.options = options;
  }

  public CompletableFuture<List<BankAccountRecord>> getRecords(
      final BankAccountTrackingParameters bankAccountTrackingParameters) {
    final var url = Request.url(ENDPOINT + "/records", bankAccountTrackingParameters, options);
    final var headers = Request.headers(url, credentials, options);
    return HttpClient.get(url, headers, BankAccountRecord[].class).thenApply(Arrays::asList);
  }

  public CompletableFuture<BankAccountRecord> getRecord(final Long id) {
    final var url = Request.url(ENDPOINT + "/records" + id, options);
    final var headers = Request.headers(url, credentials, options);
    return HttpClient.get(url, headers, BankAccountRecord.class);
  }
}
