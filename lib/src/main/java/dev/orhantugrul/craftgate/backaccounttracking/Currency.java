package dev.orhantugrul.craftgate.backaccounttracking;

public enum Currency {
  TRY("TRY"),
  USD("USD"),
  EUR("EUR"),
  GBP("GBP"),
  ARS("ARS"),
  BRL("BRL"),
  CNY("CNY"),
  AED("AED"),
  IQD("IQD"),
  AZN("AZN"),
  KZT("KZT"),
  KWD("KWD"),
  SAR("SAR"),
  BHD("BHD"),
  RUB("RUB"),
  JPY("JPY"),
  EGP("EGP"),
  MXN("MXN");

  public final String value;

  Currency(final String value) {
    this.value = value;
  }
}
