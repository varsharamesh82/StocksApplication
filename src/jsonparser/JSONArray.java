package jsonparser;

import java.util.ArrayList;

/**
 * The JSONArray class is used to recreate the JSON Array. A JSON array consists of a List of
 * JSONObjects. It converts multiple JSONObjects to a list of JSONs which is the JSONArray.
 */
public class JSONArray {

  private final ArrayList<Object> JSONArray;


  /**
   * A constructor of the JSONArray class, that initializes the list as an empty list. This list is
   * used to store the JSONObjects.
   */
  public JSONArray() {
    this.JSONArray = new ArrayList<>();
  }


  /**
   * To add a JSONObject to the List. It adds an element to the end of the existing List or
   * JSONArray.
   *
   * @param value the JSONObject which we need to add to the JSONArray.
   */
  public void add(Object value) {
    this.JSONArray.add(value);
  }


  /**
   * Gets the JSONObject at a particular index in the List of the JSONArray.
   *
   * @param index the index for which we want the JSONObject.
   * @return the JSONObject at a particular index.
   */
  public Object get(int index) {
    return this.JSONArray.get(index);
  }


  /**
   * Sets a value at a particular index of the JSONArray.
   * @param index the index at which we have to set a value.
   * @param value the value.
   * @return  the JSONObject with the modified value at a particular index.
   */
  public Object set(int index, Object value) {
    this.JSONArray.set(index, value);
    return this.JSONArray;
  }


  /**
   * Gives the length or the size of the List or the JSONArray.
   *
   * @return the length of the JSONArray.
   */
  public int size() {
    return this.JSONArray.size();
  }


  /**
   * Converts the list of JSONObjects to its equivalent JSON string, by formatting it correctly.
   *
   * @return the JSON equivalent of a JSONArray.
   */
  public String toJSONString() {
    StringBuilder jsonString = new StringBuilder("[");
    int index = 0;
    for (int i = 0; i < this.JSONArray.size(); i++) {
      JSONObject temp = (JSONObject) this.JSONArray.get(i);
      jsonString.append(temp.toJSONString(true));
      index++;
      if (index < this.JSONArray.size()) {
        jsonString.append(",");
      }
    }
    jsonString.append("]");
    return String.valueOf(jsonString);
  }
}
