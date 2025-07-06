package dev.orhantugrul.craftgate.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDateTime;

public final class Json {
  static final Gson SERIALIZER = new GsonBuilder().registerTypeAdapter(
          LocalDateTime.class,
          (JsonDeserializer<LocalDateTime>)
              (json, type, context) -> LocalDateTime.parse(json.getAsString()))
      .create();

  private Json() {
    throw new IllegalStateException();
  }

  /**
   * Deserializes the specified JSON string into an object of the given type.
   *
   * @param json  the JSON string to be deserialized
   * @param clazz the class of the type to deserialize to
   * @param <T>   the type of the desired object
   * @return an object of type T, populated with the data from the JSON string
   */
  public static <T> T from(final String json, final Class<T> clazz) {
    return SERIALIZER.fromJson(json, clazz);
  }


  /**
   * Deserializes the specified JSON string into an object of the given type using a
   * {@link TypeToken}.
   *
   * @param json the JSON string to be deserialized
   * @param type the {@code TypeToken} representing the type to deserialize to
   * @param <T>  the type of the desired object
   * @return an object of type T, populated with the data from the JSON string
   */
  public static <T> T from(final String json, final TypeToken<T> type) {
    return SERIALIZER.fromJson(json, type.getType());
  }

  /**
   * Serializes the specified object into an JSON string.
   *
   * @param object the object to be serialized into a JSON string
   * @return the JSON string representation of the specified object
   */
  public static String to(final Object object) {
    return SERIALIZER.toJson(object);
  }
}
