package dev.orhantugrul.craftgate.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.reflect.TypeToken;
import java.util.Map;
import org.junit.jupiter.api.Test;

class JsonTest {
  @Test
  void shouldSerializeAndDeserializeObject() {
    final var actual = Map.of("foo", "bar");
    final var excepted = Json.from(Json.to(actual), Map.class);

    assertThat(actual).isEqualTo(excepted);
  }

  @Test
  void shouldDeserializeWithTypeToken() {
    final var json = "[{\"name\":\"bar\",\"value\":42,\"time\":\"2023-01-01T12:00:00\"}]";
    final var actual = Json.from(json, new TypeToken<java.util.List<Map<String, String>>>() {});

    assertThat(actual).hasSize(1);
    assertThat(actual.get(0)).containsEntry("name", "bar");
    assertThat(actual.get(0)).containsEntry("value", "42");
    assertThat(actual.get(0)).containsEntry("time", "2023-01-01T12:00:00");
  }
}
