package dev.orhantugrul.craftgate.backaccounttracking;

import dev.orhantugrul.craftgate.common.data.Currency;
import dev.orhantugrul.craftgate.common.util.Parameterizable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public record BankAccountTrackingParameters(
    Currency currency,
    String description,
    String senderName,
    String senderIban,
    LocalDateTime minRecordDate,
    LocalDateTime maxRecordDate,
    Integer page,
    Integer size) implements Parameterizable {
  public BankAccountTrackingParameters {
    Objects.requireNonNull(currency);
    page = Optional.ofNullable(page).orElse(0);
    size = Optional.ofNullable(size).orElse(10);
  }

  @Override
  public Map<String, String> toParameters() {
    final var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    return Map.ofEntries(
        Map.entry("currency", currency.value),
        Map.entry("description", description),
        Map.entry("senderName", senderName),
        Map.entry("senderIban", senderIban),
        Map.entry("minRecordDate", dateTimeFormatter.format(minRecordDate)),
        Map.entry("maxRecordDate", dateTimeFormatter.format(maxRecordDate)),
        Map.entry("page", page.toString()),
        Map.entry("size", size.toString()));
  }
}
