package io.craftgate.common;

import java.util.Map;

public interface Parameterizable {
  Map<String, String> toParameters();
}
