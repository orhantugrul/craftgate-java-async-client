package dev.orhantugrul.craftgate.backaccounttracking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankAccountTrackingData(
    Long id,
    String key,
    Currency currency,
    String senderName,
    String senderIban,
    String description,
    LocalDateTime recordDate,
    BigDecimal amount,
    Type type,
    Source source) {}