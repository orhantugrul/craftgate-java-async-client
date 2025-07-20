package dev.orhantugrul.craftgate.backaccounttracking;

import dev.orhantugrul.craftgate.common.data.Currency;
import dev.orhantugrul.craftgate.common.data.Source;
import dev.orhantugrul.craftgate.common.data.Type;
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