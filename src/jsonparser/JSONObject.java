package jsonparser;

import java.util.HashMap;
import java.util.Map;

/**
 * The JSONObject class is used to recreate the key-value pairs in a JSON. It converts a set of
 * key-value pairs into it's JSON equivalent. It leverages a map to recreate the key-value paris in
 * a JSON.
 */
public class JSONObject {

  private final Map<String, Object> JSONHashMap;

  /**
   * A constructor of the JSONObject class, that initializes the map as an empty HashMap, where the
   * key is of type String, and the value is of generic type Object.
   */
  public JSONObject() {
    this.JSONHashMap = new HashMap<>();
  }


  /**
   * The put method takes the key and the value as parameters, and inserts the key value pair in out
   * hash map. Thus, it recreates the key-value pairs in a JSON.
   *
   * @param key   the key. It's of type String.
   * @param value the value. It's of generic type Object.
   */
  public void put(String key, Object value) {
    this.JSONHashMap.put(key, value);
  }


  /**
   * Returns the value for a particular key. Leverages the hash map to get the value for any key.
   *
   * @param key the key for which we've to determine the value.
   * @return the value for that key.
   */
  public Object get(String key) {
    return JSONHashMap.get(key);
  }


  /**
   * Converts the key, value pairs in our hash map to a JSON equivalent string, by appending '{' in
   * the beginning and the end, etc.
   *
   * @param flag a boolean flag, used for advanced formatting especially when the JSONObject is a
   *             part of the JSONArray.
   * @return a JSON equivalent of the key, value pairs.
   */
  public String toJSONString(Boolean flag) {
    StringBuilder jsonString = new StringBuilder("{");
    int index = 0;
    for (String key : this.JSONHashMap.keySet()) {
      jsonString.append("\"").append(key).append("\"").append(":");

      Object value = JSONHashMap.get(key);
      if (value instanceof String && flag) {
        jsonString.append("\"").append(value).append("\"");
      } else {
        jsonString.append(value);
      }
      index++;
      if (index < this.JSONHashMap.size()) {
        jsonString.append(",");
      }
    }
    jsonString.append("}");
    return String.valueOf(jsonString);
  }
}
