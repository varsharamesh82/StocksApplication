package jsonparser;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The JSONParser class is used to read a JSON file and convert it into its equivalent JSONObject
 * and JSONArray. The parser opens and reads a JSON and puts the key, value pairs in its equivalent
 * JSONObjects and JSONArrays.
 */
public class JSONParser {

  private final Map<String, Object> JSONParseHashMap;

  /**
   * A constructor of the JSONParser class. It initializes an empty map that is used to store the
   * key, value pairs. The key is of type String, whereas the value is of generic type Object as the
   * value can either be a JSONObject or a JSONArray.
   */
  public JSONParser() {
    this.JSONParseHashMap = new HashMap<>();
  }


  /**
   * Opens and reads a JSON file and starts processing it for getting the key, value pairs from the
   * JSON file. It returns a JSONArray that consists of all the JSONObjects in that file.
   *
   * @param file the JSON file.
   * @return a JSONArray with all the JSONObjects in that JSON file.
   * @throws IOException if the file is not a valid JSON, or is not in the specified format.
   */
  public JSONArray parse(FileReader file) throws IOException {
    StringBuilder fileContent = new StringBuilder();
    JSONArray jsonArray = new JSONArray();
    int i;
    while ((i = file.read()) != -1) {
      fileContent.append((char) i);
    }
    try {
      getHashMap(String.valueOf(fileContent));
      jsonArray = getJsonArray();
    } catch (IllegalArgumentException ex) {
      System.out.println("File is not a valid JSON.");
    }
    return jsonArray;
  }


  /**
   * Puts the data or the file content in a hash map to set the key, value pairs. This method acts
   * as a helper method to the parse function.
   *
   * @param jsonString the file contents as a string.
   * @throws IllegalArgumentException if the file is not a valid JSON, or is not in the specified
   *                                  format.
   */
  public void getHashMap(String jsonString) throws IllegalArgumentException {
    if (jsonString.charAt(0) == '{' && jsonString.charAt(jsonString.length() - 1) == '}') {
      jsonString = jsonString.substring(1, jsonString.length() - 1);
      jsonString = jsonString.replace("\"", "");
      String[] keyValues = jsonString.split(":", 2);
      if (keyValues.length == 2) {
        this.JSONParseHashMap.put(keyValues[0], keyValues[1]);
      } else {
        throw new IllegalArgumentException("File is not a valid JSON.");
      }
    } else {
      throw new IllegalArgumentException("File is not a valid JSON.");
    }
  }


  /**
   * Puts the JSONObjects from the file in a JSONArray. This method acts as a helper method to the
   * parse function.
   *
   * @return a JSONArray with all the JSONObjects (key, value) pairs from the file.
   */
  public JSONArray getJsonArray() {
    String stocks = (String) this.JSONParseHashMap.get("stocks");
    stocks = stocks.substring(1, stocks.length() - 1);
    String[] stocksArray = stocks.split("},");
    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < stocksArray.length; i++) {
      JSONObject temp = new JSONObject();
      String individualStock = stocksArray[i];
      individualStock = individualStock.substring(1);
      if (i == stocksArray.length - 1) {
        individualStock = individualStock.substring(0, individualStock.length() - 1);
      }
      String[] tickerDate = individualStock.split(",");
      for (String s : tickerDate) {
        String[] keyValuePairs = s.split(":", 2);
        temp.put(keyValuePairs[0], keyValuePairs[1]);
      }
      jsonArray.add(temp);
    }
    return jsonArray;
  }
}