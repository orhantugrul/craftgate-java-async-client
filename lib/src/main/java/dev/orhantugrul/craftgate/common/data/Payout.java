package dev.orhantugrul.craftgate.common.data;

import java.math.BigDecimal;

public record Payout(
    Currency currency,
    BigDecimal parity,
    BigDecimal paidPrice,
    BigDecimal merchantPayoutAmount,
    BigDecimal subMerchantMemberPayoutAmount) {}
