package dev.orhantugrul.craftgate.common.util;

import java.util.Map;

public interface Parameterizable {
  Map<String, String> toParameters();
}
