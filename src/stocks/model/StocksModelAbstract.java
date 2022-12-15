package stocks.model;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import jsonparser.JSONArray;
import jsonparser.JSONObject;
import jsonparser.JSONParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import stocks.api.API;
import stocks.api.APIImpl;


/**
 * This abstract class that implements the StocksModelInterface. The StocksModelAbstract class is
 * extended by to implement the flexible and inflexible portfolio functionalities. The class
 * implements all the methods in the stocks model interface.
 */
abstract class StocksModelAbstract implements StocksModelInterface {
  private List<String> validSymbols;
  private List<String> csvStockSymbols;
  private List<String> validSellSymbols;
  private final int encryptionKey;
  private final API callAlphaVantageAPI;

  /**
   * A constructor of the StocksModel class. It initializes the class variables <ol>
   * <li>validSymbols - Stores the valid stock names from a CSV.</li>
   * <li>csvStockSymbols - Stores the stock names for which the ticker data is stored locally.</li>
   * <li>key - The encryption and decryption key.</li>
   * </ol>
   */
  public StocksModelAbstract() {
    this.validSymbols = new ArrayList<>();
    this.csvStockSymbols = new ArrayList<>();
    this.validSellSymbols = new ArrayList<>();
    this.encryptionKey = 4;
    this.callAlphaVantageAPI = new APIImpl();
  }


  /**
   * Checks if a username is valid or not. Returns true if the username is valid.
   *
   * @param userName the username as input by the user.
   * @return true if the username is valid, else return false.
   */
  @Override
  public boolean checkValidUserName(String userName) throws IOException {
    String loginData;
    Path file = Path.of("login_credentials.csv");
    try {
      loginData = Files.readString(file);
      String[] loginDataArray = loginData.split("\n");
      if (loginDataArray.length > 0) {
        for (String s : loginDataArray) {
          String[] login_password = s.split(",");
          if (login_password.length > 0) {
            if (decrypt(login_password[0]).equals(userName)) {
              return false;
            }
          } else {
            return false;
          }
        }
      } else {
        return false;
      }
    } catch (IOException e) {
      File loginFile = new File(String.valueOf(file));
      boolean loginFileCreated = loginFile.createNewFile();
      while (!loginFileCreated) {
        loginFileCreated = loginFile.createNewFile();
      }
    }
    return true;
  }


  /**
   * Creates a directory for the user with the username as the name of the directory. All portfolios
   * for a user are stored inside this folder.
   *
   * @param userName the username which is used as the name of the directory.
   */
  @Override
  public void createUserDirectories(String userName) {
    File userDirectory = new File("res/users/" + userName + "/");
    File localCache = new File("res/stock_data/");
    File inflexibleDirectory = new File("res/users/" + userName + "/inflexible/");
    File flexibleDirectory = new File("res/users/" + userName + "/flexible/");
    File ongoingStrategies = new File("res/users/" + userName + "/flexible/ongoing_strategies.csv");

    userDirectory.mkdirs();
    localCache.mkdirs();
    inflexibleDirectory.mkdirs();
    flexibleDirectory.mkdirs();
    try {
      ongoingStrategies.createNewFile();
    } catch (IOException ignored) {
    }
  }


  /**
   * Used to save the username and password in a separate user_credentials file. The username and
   * password are stored by encrypting them, to prevent illegal access.
   *
   * @param userName the username as input by the user.
   * @param password the password as input by the user.
   */
  @Override
  public boolean saveLoginCredentials(String userName, String password) {
    File loginCredentials = new File("login_credentials.csv");
    try (BufferedWriter bf = new BufferedWriter(new FileWriter(loginCredentials, true))) {
      bf.write(encrypt(userName) + "," + encrypt(password));
      bf.newLine();
      bf.flush();
      return true;
    } catch (IOException ex) {
      System.out.println(ex);
    }
    return false;
  }


  /**
   * Checks the input username and password with the credentials stored in the user_credentials file
   * to validate login. The usernames and passwords in the user_credentials file are encrypted and
   * hence need to be decrypted before they can be used.
   *
   * @param userName the username as input by the user.
   * @param password the password as input by the user.
   * @return true if the username and password are correct, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public boolean validateLoginCredentials(String userName, String password) throws IOException {
    String loginData;
    Path file = Path.of("login_credentials.csv");
    try {
      loginData = Files.readString(file);
      String[] loginDataArray = loginData.split("\n");
      for (String s : loginDataArray) {
        String[] login_password = s.split(",");
        if (decrypt(login_password[0]).equals(userName) && decrypt(login_password[1]).equals(
                password)) {
          return true;
        }
      }
    } catch (IOException e) {
      File loginFile = new File(String.valueOf(file));
      loginFile.createNewFile();
    }
    return false;
  }


  /**
   * Sets the ticker names or the stock names that are valid. Reads the 'valid_symbols.csv' file to
   * get all the valid stock symbols. This is later used to verify if a stock name entered by the
   * user is valid or not.
   *
   * @return true if the valid_symbols.csv file is set successfully, else false.
   */
  @Override
  public boolean setValidSymbols() {
    try (
            InputStream input = this.getClass().getResourceAsStream("/valid_stocks.csv");
            InputStreamReader inputReader = new InputStreamReader(Objects.requireNonNull(input));
            BufferedReader reader = new BufferedReader(inputReader)
    ) {
      this.validSymbols = readFromResource(reader, ",");
      return true;
    } catch (IOException e) {
      System.out.println("Failed to read the ticker_symbols file.");
    }
    return false;
  }


  /**
   * Sets the stock names for which the ticker data is stored locally as a CSV. Reads the
   * 'csv_symbols.csv' file to get all the stocks for which the data is stored locally. This is
   * later used to minimize the calls to the API by fetching the data from the local files for the
   * stocks for which the data is available locally.
   *
   * @return true if the csv_symbols.csv file is set successfully, else false.
   */
  @Override
  public boolean setCSVSymbols() {
    try (
            InputStream input = this.getClass().getResourceAsStream("/csv_symbols.csv");
            InputStreamReader inputReader = new InputStreamReader(Objects.requireNonNull(input));
            BufferedReader reader = new BufferedReader(inputReader)
    ) {
      this.csvStockSymbols = readFromResource(reader, ",");
      return true;
    } catch (IOException e) {
      System.out.println("Failed to read the ticker_symbols file.");
    }
    return false;
  }


  /**
   * Returns a List from an input BufferedReader by splitting the data in the file by the separator.
   * Works as a helper method for setValidSymbols and setCSVSymbols methods.
   *
   * @param reader    the BufferedReader or file input.
   * @param separator the separator for which we've to split the data.
   * @return a list of the data, split by the separator.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public List<String> readFromResource(BufferedReader reader, String separator) throws IOException {
    StringBuilder builder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }
    String builderString = builder.toString();
    String[] builderArray = builderString.split(separator);
    return Arrays.asList(builderArray);
  }

  /**
   * Checks if the stock name as input by the user is valid or not. Compares the stock name with a
   * list of the valid stock names that is initialized by the setValidSymbols function.
   *
   * @param stockName stock name as input by the user.
   * @return true if the stock name is valid, else false.
   */
  @Override
  public boolean checkValidSymbolName(String stockName) {
    return this.validSymbols.contains(stockName);
  }


  /**
   * Checks if the file name as input by the user is valid or not. The file name is invalid if the
   * file already exists. This function acts as a helper for the create portfolio functionality.
   *
   * @param jsonName the name of the json as input by the user.
   * @param userName the username as input by the user.
   * @return true if the json name is valid, else false.
   */
  @Override
  public boolean checkValidFileName(String jsonName, String userName) {
    File file = new File("res/users/" + userName + "/inflexible/" + jsonName);
    if (this instanceof StocksModelFlexible) {
      file = new File("res/users/" + userName + "/flexible/" + jsonName);
    }
    return !file.exists();
  }


  /**
   * Checks if the file name as input by the user is valid or not. The file name is invalid if it
   * does not exist. This function acts as a helper for the examine portfolio and get total value of
   * a portfolio functionality.
   *
   * @param jsonName the name of the json as input by the user.
   * @param userName the username as input by the user.
   * @return true if the json name is valid, else false.
   */
  @Override
  public boolean checkValidFileNameForExamination(String jsonName, String userName) {
    File file = new File("res/users/" + userName + "/inflexible/" + jsonName);
    if (this instanceof StocksModelFlexible) {
      file = new File("res/users/" + userName + "/flexible/" + jsonName);
    }
    return file.exists();
  }


  /**
   * Checks if the number of stocks or stock quantity input by the user is valid or not. The stock
   * quantity for a particular stock cannot be less than or equal to 0.
   *
   * @param stockQuantity the quantity as input by the user.
   * @return true if the stock quantity is greater than or equal to 1, else false.
   */
  @Override
  public boolean checkValidQuantity(double stockQuantity) {
    return stockQuantity > 0;
  }


  /**
   * Checks if the investment value per portfolio as input by the user is valid or not. The
   * investment value is valid if it is greater than or equal to 0.
   *
   * @param investmentValue investment value as input by the user.
   * @return true if the investment value is valid, else false.
   */
  @Override
  public boolean checkValidInvestmentValue(double investmentValue) {
    return investmentValue > 0;
  }


  /**
   * Checks if the weightage per stock as input by the user is valid or not. The stock weightage is
   * valid if it is greater than or equal to 0, less than or equal to 100 and the sum of all
   * weightages is 100.
   *
   * @param stockWeightagesArray the valid stock weightages upto this point.
   * @param weightage            the weightage as input by the user.
   * @return true if the stock weightage valid, else false.
   */
  @Override
  public boolean checkValidWeightage(double[] stockWeightagesArray, double weightage) {
    double currentTotalWeightage = 0;
    for (double v : stockWeightagesArray) {
      currentTotalWeightage += v;
    }
    return weightage >= 0 && weightage <= 100 && currentTotalWeightage + weightage >= 0
            && currentTotalWeightage + weightage <= 100;
  }


  /**
   * Checks if the commission per transaction input by the user is valid or not. The commission for
   * a particular transaction cannot be less than 0.
   *
   * @param commission the commission as input by the user.
   * @return true if the commission is greater than 0, else false.
   */
  @Override
  public boolean checkValidCommission(double commission) {
    return commission >= 0;
  }


  /**
   * Checks if the interval value per portfolio as input by the user is valid or not. The interval
   * value is valid if it is greater than or equal to 1.
   *
   * @param interval the interval value as input by the user.
   * @return true if the interval value is valid, else false.
   */
  @Override
  public boolean checkInterval(int interval) {
    return interval >= 1;
  }


  /**
   * Checks if the year as input by the user is valid or not. The year must be greater than 1999 and
   * year lesser than or equal to currentYear.
   *
   * @param year the year as input by the user.
   * @return true if the year is valid, else false.
   */
  @Override
  public boolean checkValidYear(String year) {
    int integerYear = Integer.parseInt(year);
    return integerYear >= 1999 && integerYear <= Calendar.getInstance().get(Calendar.YEAR);
  }


  /**
   * Checks if the month as input by the user is valid or not. The month must be greater than 1 and
   * year lesser than or equal 12.
   *
   * @param month the year as input by the user.
   * @return true if the month is valid, else false.
   */
  @Override
  public boolean checkValidMonth(String month) {
    int integerMonth = Integer.parseInt(month);
    return integerMonth >= 1 && integerMonth <= 12;
  }


  /**
   * Checks if the date as input by the user is valid or not.
   *
   * @param date the year as input by the user.
   * @return true if the date is valid, else false.
   */
  @Override
  public boolean checkValidDate(String date, String month, String year) {
    YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    int daysInMonth = yearMonthObject.lengthOfMonth();
    int integerDate = Integer.parseInt(date);
    return integerDate >= 1 && integerDate <= daysInMonth;
  }


  /**
   * Checks if the buying date as input by the user is valid or not. The buying date cannot be on a
   * weekend, as the market is closed. Hence, no stocks are traded or available on the weekends.
   *
   * @param buyDate the buying date as input by the parameter.
   * @return true if the date is valid or not a weekend, else false.
   */
  @Override
  public boolean checkValidBuyDate(String buyDate) {
    Date currentDate = new Date();
    Date userInputDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      userInputDate = dateFormat.parse(buyDate);
    } catch (ParseException e) {
      System.out.println("Failed to parse the string date to date");
    }

    if (currentDate.compareTo(userInputDate) < 0) {
      return false;
    }

    Date inputDate;
    Calendar cal = Calendar.getInstance();
    try {
      inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(buyDate);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    cal.setTime(inputDate);
    int day = cal.get(Calendar.DAY_OF_WEEK);
    return !(day == Calendar.SATURDAY || day == Calendar.SUNDAY);
  }


  /**
   * Writes the stock ticker data to a JSON. It gets the buying price for a stock name and buying
   * date as entered by the user. This function is used to create a new portfolio and save it as a
   * json.
   *
   * @param userName             the username as input by the user.
   * @param jsonFileName         the jsonName as input by the user.
   * @param stockNamesArray      an array of the stock names.
   * @param stockBuyDatesArray   an array of the buying dates.
   * @param stockQuantitiesArray an array of the stock quantities.
   * @param commissionsArray     an array of the commission per transaction.
   * @param operation            the operation to be performed. Eg: Buy / Sell.
   * @return true if it is able to write the data successfully, else false.
   */
  @Override
  public boolean writePortfolioToJson(String userName, String jsonFileName,
          String[] stockNamesArray, String[] stockBuyDatesArray,
          double[] stockQuantitiesArray, double[] commissionsArray, String operation) {
    int n = stockNamesArray.length;

    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    if (operation.equals("BUY") || operation.equals("SELL")) {
      jsonArray = getPortfolioFromJson(userName, jsonFileName);
    }

    for (int i = 0; i < n; i++) {
      JSONObject temp = new JSONObject();
      temp.put("operation", operation);
      temp.put("stock_name", stockNamesArray[i]);
      temp.put("transaction_date", stockBuyDatesArray[i]);
      temp.put("quantity", stockQuantitiesArray[i]);
      try {
        temp.put("price", getPrice(stockNamesArray[i], stockBuyDatesArray[i], false));
      } catch (IllegalArgumentException | IOException e) {
        System.out.println(e);
        return false;
      }
      if (operation.equals("BUY") || operation.equals("SELL")) {
        temp.put("commission", commissionsArray[i]);
      }
      jsonArray.add(temp);
    }
    jsonObject.put("stocks", jsonArray.toJSONString());

    try {
      FileWriter file;
      if (operation.equals("PORTFOLIO")) {
        file = new FileWriter("res/users/" + userName + "/inflexible/" + jsonFileName);
      } else {
        file = new FileWriter("res/users/" + userName + "/flexible/" + jsonFileName);
      }

      file.write(jsonObject.toJSONString(false));
      file.close();
    } catch (IOException ex) {
      System.out.println("Unable to open the file.");
      return false;
    }
    return true;
  }


  /**
   * Gets or reads the stock data from an existing JSON. This is used to examine the portfolio and
   * to get the value of the portfolio on a certain date. It returns the data in the JSON as a
   * JSONArray.
   *
   * @param userName     username as input by the user.
   * @param jsonFileName jsonName as input by the user.
   * @return jsonArray with the data in the JSON.
   */
  @Override
  public JSONArray getPortfolioFromJson(String userName, String jsonFileName) {
    JSONParser jsonParser = new JSONParser();
    JSONArray jsonArray = new JSONArray();
    try {
      FileReader file = null;
      if (this instanceof StocksModelInflexible) {
        file = new FileReader("res/users/" + userName + "/inflexible/" + jsonFileName);
      } else if (this instanceof StocksModelFlexible) {
        file = new FileReader("res/users/" + userName + "/flexible/" + jsonFileName);
      }
      jsonArray = jsonParser.parse(file);
    } catch (IOException ex) {
      if (this instanceof StocksModelInflexible) {
        System.out.println("File does not exist.");
      }
    }

    return jsonArray;
  }


  /**
   * Gets or reads the stock data from an existing JSON. This is used to get the composition of a
   * portfolio on a specific date.
   *
   * @param date         the date as input by the user.
   * @param userName     username as input by the user.
   * @param jsonFileName jsonName as input by the user.
   * @return jsonArray with the data in the JSON.
   */
  @Override
  public JSONArray getCompositionHelper(String date, String userName, String jsonFileName) {
    JSONArray jsonArray = getPortfolioFromJson(userName, jsonFileName);
    JSONArray resultJsonArray = new JSONArray();
    List<String> processedStocks = new ArrayList<>();

    for (int index = 0; index < jsonArray.size(); index++) {
      JSONObject existingObject = (JSONObject) jsonArray.get(index);
      String stockName = (String) existingObject.get("stock_name");
      String transactionDateString = (String) existingObject.get("transaction_date");
      double totalQuantity = Double.parseDouble((String) existingObject.get("quantity"));
      double oldQuantity;
      double price = Double.parseDouble((String) existingObject.get("price"));
      String lastTransactionDateString = transactionDateString;
      String firstTransactionDateString = transactionDateString;
      double avgPrice = price;
      String commission = (String) existingObject.get("commission");
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date transactionDate = new Date();
      Date userInputDate = new Date();
      Date firstTransactionDate = new Date();
      Date lasTransactionDate = new Date();
      try {
        transactionDate = dateFormat.parse(transactionDateString);
        userInputDate = dateFormat.parse(date);
        firstTransactionDate = transactionDate;
        lasTransactionDate = transactionDate;
      } catch (ParseException e) {
        System.out.println("Failed to parse the string date to date");
      }

      if (transactionDate.compareTo(userInputDate) <= 0) {
        if (!processedStocks.contains(stockName)) {
          JSONObject temp = new JSONObject();

          for (int i = 0; i < jsonArray.size(); i++) {
            if (i != index) {
              JSONObject newObject = (JSONObject) jsonArray.get(i);
              String newStockName = (String) newObject.get("stock_name");
              String newTransactionDateString = (String) newObject.get("transaction_date");
              double newPrice = Double.parseDouble((String) newObject.get("price"));
              double newQuantity = Double.parseDouble((String) newObject.get("quantity"));
              String newOperation = (String) newObject.get("operation");
              Date newTransactionDate = new Date();
              try {
                newTransactionDate = dateFormat.parse(newTransactionDateString);
              } catch (ParseException e) {
                System.out.println("Failed to parse the string date to date");
              }

              if (newTransactionDate.compareTo(userInputDate) <= 0 && newStockName.equals(
                      stockName)) {

                if (newTransactionDate.compareTo(firstTransactionDate) < 0) {
                  firstTransactionDateString = newTransactionDateString;
                } else if (newTransactionDate.compareTo(lasTransactionDate) > 0) {
                  lastTransactionDateString = newTransactionDateString;
                }

                if (newOperation.equals("BUY")) {
                  oldQuantity = totalQuantity;
                  totalQuantity += newQuantity;
                  totalQuantity = Math.round(totalQuantity * 100.0) / 100.0;
                  avgPrice = ((avgPrice * oldQuantity) + (newPrice * newQuantity)) / totalQuantity;
                  avgPrice = Math.round(avgPrice * 100.0) / 100.0;
                } else {
                  totalQuantity -= newQuantity;
                  totalQuantity = Math.round(totalQuantity * 100.0) / 100.0;
                }
              }
            }

          }
          processedStocks.add(stockName);
          if (totalQuantity > 0) {
            temp.put("stock_name", stockName);
            temp.put("quantity", String.valueOf(totalQuantity));
            temp.put("transaction_date", firstTransactionDateString);
            temp.put("price", String.valueOf(price));
            temp.put("last_transaction_date", lastTransactionDateString);
            temp.put("avg_price", String.valueOf(avgPrice));
            temp.put("commission", String.valueOf(commission));
            resultJsonArray.add(temp);
          }
        }
      }

    }
    return resultJsonArray;
  }


  /**
   * The getPrice method is used to determine the closing price of a stock on a particular date.
   *
   * @param stockSymbol the stock name as input by the user.
   * @param date        the buying date as input by the user.
   * @return the price on that day. -1 if it couldn't find the price on that day.
   * @throws IOException if the url used to hit the API OR if it fails to append to the logger in *
   *                     the MockModel implementation.
   */
  @Override
  public Double getPrice(String stockSymbol, String date, boolean lastValidPriceFlag)
          throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    File localCache = new File("res/stock_data/" + stockSymbol + ".csv");
    Calendar buyDate = Calendar.getInstance();
    Calendar lastCSVDate = Calendar.getInstance();
    Double get_price;

    try {
      buyDate.setTime(dateFormat.parse(date));
      lastCSVDate.setTime(dateFormat.parse("2022-10-31"));
    } catch (ParseException e) {
      System.out.println("Failed to parse date.");
    }

    if (this.csvStockSymbols.contains(stockSymbol) && buyDate.compareTo(lastCSVDate) <= 0) {
      try (
              InputStream input = this.getClass()
                      .getResourceAsStream("/stock_data/" + stockSymbol + ".csv");
              InputStreamReader inputReader = new InputStreamReader(Objects.requireNonNull(input));
              BufferedReader reader = new BufferedReader(inputReader)
      ) {
        List<String> tickerArray = readFromResource(reader, ";");
        for (String s : tickerArray) {
          String[] date_price = s.split(",");
          if (date_price[0].equals(date)) {
            return Double.valueOf(date_price[1]);
          }
        }
      }
    }

    if (localCache.exists()) {
      String tickerData;
      Path file = Path.of("res/stock_data/" + stockSymbol + ".csv");
      tickerData = Files.readString(file);
      String[] tickerArray = tickerData.split(";");
      for (String s : tickerArray) {
        String[] date_price = s.split(",");
        if (date_price[0].equals(date)) {
          return Double.valueOf(date_price[1]);
        }
      }
    }

    get_price = getPriceHelper(stockSymbol, date, lastValidPriceFlag);
    return get_price;
  }


  /**
   * Acts as a helper method for the getPrice function. It calls the API and saves the data to a csv
   * for future reference.
   *
   * @param stockSymbol the stock name as input by the user.
   * @param date        the date as input by the function.
   * @param flag        set to true when computing last valid price.
   * @return the price on that particular day.
   * @throws IOException if the URL for the API is invalid.
   */
  @Override
  public Double getPriceHelper(String stockSymbol, String date, boolean flag) throws IOException {
    Map<String, Double> date_price = callAPI(stockSymbol);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar buyDate = Calendar.getInstance();
    Calendar currentDate = Calendar.getInstance();

    try {
      buyDate.setTime(dateFormat.parse(date));
    } catch (ParseException e) {
      System.out.println("Failed to parse date.");
    }

    Double get_price = date_price.get(date);
    if (get_price == null) {
      if (flag) {
        return (double) -1;
      }
    }

    while (get_price == null && buyDate.compareTo(currentDate) < 0) {
      buyDate.add(Calendar.DATE, 1);
      date = dateFormat.format(buyDate.getTime());
      get_price = date_price.get(date);
    }
    return get_price;
  }


  /**
   * Calls the API and returns the API data as a Map and more specifically as a HashMap with the key
   * as the date, and the value as the closing price on that day.
   *
   * @param stockSymbol the stock name as input by the user.
   * @return returns the Map of values from the API.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public Map<String, Double> callAPI(String stockSymbol) throws IOException {
    String[] ticker_data = callAlphaVantageAPI.getTickerData(stockSymbol);
    if (ticker_data.length == 3) {
      throw new IllegalArgumentException("Error Message: Invalid API call. Please retry or "
              + "visit the documentation (https://www.alphavantage.co/documentation/)"
              + " for TIME_SERIES_DAILY.");
    }

    Map<String, Double> date_price = new LinkedHashMap<>();
    for (int i = 1; i < ticker_data.length; i++) {
      String[] parts = ticker_data[i].split(",");
      date_price.put(parts[0], Double.valueOf(parts[4]));
    }

    File localCache = new File("res/stock_data/" + stockSymbol + ".csv");
    try (BufferedWriter bf = new BufferedWriter(new FileWriter(localCache))) {
      for (Map.Entry<String, Double> entry : date_price.entrySet()) {
        bf.write(entry.getKey() + "," + entry.getValue() + ";");
      }
      bf.flush();
    } catch (IOException ex) {
      System.out.println(ex);
    }

    return date_price;
  }


  /**
   * To get the lastValidPrice on any particular day. For example, the lastValidPrice on a weekend
   * would be the last traded price. Thus return the last traded price.
   *
   * @param stockName the stock name as input by the user.
   * @return the last traded price for a stock name.
   */
  @Override
  public double getLastValidPrice(String stockName) {
    double getPrice = -1;
    Calendar currentDate = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(currentDate.getTime());
    try {
      getPrice = getPrice(stockName, date, true);
      while (getPrice == -1.0) {
        currentDate.add(Calendar.DATE, -1);
        date = dateFormat.format(currentDate.getTime());
        getPrice = getPrice(stockName, date, true);
      }
    } catch (IllegalArgumentException | IOException e) {
      System.out.println(e);
      return getPrice;
    }
    return getPrice;
  }


  /**
   * Get the total portfolio value on a particular day, as entered by the user. Multiplying the
   * quantity by the buying price for each stock in a portfolio, gives the total value of that
   * portfolio.
   *
   * @param stocksDataArray the stocks date as input by the user.
   * @param date            the date as input by the user.
   * @return returns the total portfolio value for a particular portfolio.
   */
  @Override
  public double getPortfolioValue(JSONArray stocksDataArray, String date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = new Date();
    Date transactionDate = new Date();
    double portfolioValue = 0;

    for (int i = 0; i < stocksDataArray.size(); i++) {
      JSONObject temp = (JSONObject) stocksDataArray.get(i);
      try {
        currentDate = dateFormat.parse(date);
        transactionDate = dateFormat.parse((String) temp.get("transaction_date"));
      } catch (ParseException ex) {
        System.out.println("Failed to parse the string date to date");
      }

      if (currentDate.compareTo(transactionDate) >= 0) {
        try {
          double price = getPrice((String) temp.get("stock_name"), date, false);
          if (price == -1) {
            return -1;
          }
          if (temp.get("operation").equals("SELL")) {
            portfolioValue -= price * Double.parseDouble((String) temp.get("quantity"));
          } else {
            portfolioValue += price * Double.parseDouble((String) temp.get("quantity"));
          }
        } catch (IllegalArgumentException | IOException e) {
          System.out.println("Invalid API call for " + temp.get("stock_name"));
          return -1;
        }
      }
    }
    return portfolioValue;
  }


  /**
   * Sets the validSellSymbols list, that contains the stocks names in a particular portfolio that
   * can be sold by the user.
   *
   * @param jsonName the json name as entered by the user.
   * @param userName the username as entered by the user.
   */
  @Override
  public void setValidSellSymbols(String jsonName, String userName) {
    List<String> validSellSymbols = new ArrayList<>();
    JSONArray stocksDataArray = getPortfolioFromJson(userName, jsonName);

    for (int i = 0; i < stocksDataArray.size(); i++) {
      JSONObject temp = (JSONObject) stocksDataArray.get(i);
      if (temp.get("operation").equals("BUY")) {
        String stockName = (String) temp.get("stock_name");
        if (!validSellSymbols.contains(stockName)) {
          validSellSymbols.add(stockName);
        }
      }
    }
    this.validSellSymbols = validSellSymbols;
  }


  /**
   * Checks if the stock name entered by the user for selling has been bought earlier in the
   * portfolio.
   *
   * @param stockName the stock name as input by the user.
   * @return true if it has bought earlier in the portfolio, else false.
   */
  @Override
  public boolean checkValidSellSymbols(String stockName) {
    return this.validSellSymbols.contains(stockName);
  }

  /**
   * Checks if a sell order placed by the user is valid or not. A user cannot sell stocks that he/
   * she does not own yet, and a user can at-most sell number of stocks equal to the number of
   * stocks that he/ she has bought earlier.
   *
   * @param stockName    stock name entered by the user to sell.
   * @param quantity     number of shares that the user wants to sell.
   * @param date         the date on which the user sold the shares.
   * @param userName     username of the user.
   * @param jsonFileName the name of the portfolio.
   * @return             true if the sell order is valid, i.e, the user has bought that
   *                     stock earlier and the quantity of buy is greater than or equal
   *                     to the quantity of sell.
   */
  public boolean validSellOrderQuantity(String stockName, double quantity, String date,
          String userName, String jsonFileName) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    JSONArray stocksDataArray = getPortfolioFromJson(userName, jsonFileName);
    Date sellDate = new Date();
    Date buyDate = new Date();

    try {
      sellDate = dateFormat.parse(date);
    } catch (ParseException ex) {
      System.out.println("Failed to parse the string date to date");
    }

    double totalStocksBought = 0;
    for (int i = 0; i < stocksDataArray.size(); i++) {
      JSONObject temp = (JSONObject) stocksDataArray.get(i);
      try {
        buyDate = dateFormat.parse((String) temp.get("transaction_date"));
      } catch (ParseException ex) {
        System.out.println("Failed to parse the string date to date");
      }

      if (sellDate.compareTo(buyDate) >= 0 && stockName.equals(temp.get("stock_name"))) {
        if (temp.get("operation").equals("BUY")) {
          totalStocksBought += Double.parseDouble((String) temp.get("quantity"));
        } else if (temp.get("operation").equals("SELL")) {
          totalStocksBought -= Double.parseDouble((String) temp.get("quantity"));
        }
      }
    }
    return totalStocksBought >= quantity;
  }


  /**
   * Used to encrypt the username and password, so that the login credentials are not susceptible to
   * any attacks. The encrypted credentials are saved to the login_credentials file.
   *
   * @param text the text that needs to be encrypted.
   * @return the encrypted string.
   */
  @Override
  public String encrypt(String text) {
    String b64encoded = Base64.getEncoder().encodeToString(text.getBytes());
    String reverse = new StringBuffer(b64encoded).reverse().toString();
    StringBuilder tmp = new StringBuilder();
    for (int i = 0; i < reverse.length(); i++) {
      tmp.append((char) (reverse.charAt(i) + this.encryptionKey));
    }
    return tmp.toString();
  }


  /**
   * Used to decrypt the username and password stored in the login_credentials file. The credentials
   * in the login_credentials file are encrypted to protect them from attacks. Thus, we need to
   * decrypt the values before they can be compared and validated.
   *
   * @param text the text that needs to be decrypted.
   * @return the decrypted string.
   */
  @Override
  public String decrypt(String text) {
    StringBuilder tmp = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      tmp.append((char) (text.charAt(i) - this.encryptionKey));
    }
    String reversed = new StringBuffer(tmp.toString()).reverse().toString();
    return new String(Base64.getDecoder().decode(reversed));
  }


  /**
   * Calculates the cost value basis for a portfolio on a give date. The cost value basis takes the
   * commission and the money invested by the user till date. It does not take net selling into
   * account.
   *
   * @param stocksDataArray the data in a portfolio.
   * @param date            the date as input by the user.
   * @return the cost value basis for a portfolio till that date.
   */
  @Override
  public double getCostValueBasis(JSONArray stocksDataArray, String date) {
    double costBasis = 0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date transactionDate = new Date();
    Date userInputDate = new Date();
    for (int i = 0; i < stocksDataArray.size(); i++) {
      JSONObject temp = (JSONObject) stocksDataArray.get(i);
      String transactionDateString = (String) temp.get("transaction_date");

      try {
        transactionDate = dateFormat.parse(transactionDateString);
        userInputDate = dateFormat.parse(date);
      } catch (ParseException e) {
        System.out.println("Failed to parse the string date to date");
      }

      if (transactionDate.compareTo(userInputDate) <= 0) {
        try {
          if (temp.get("operation").equals("BUY")) {
            costBasis += Double.parseDouble((String) temp.get("commission"))
                    + Double.parseDouble((String) temp.get("price")) * Double.parseDouble(
                    (String) temp.get("quantity"));
          } else if (temp.get("operation").equals("SELL")) {
            costBasis += Double.parseDouble((String) temp.get("commission"));
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid API call for " + temp.get("stock_name"));
        }
      }
    }
    return costBasis;
  }


  /**
   * Calculate the portfolio performance for a give portfolio over a time range. The portfolio
   * performance is either daily, weekly, monthly or yearly depending on the start date and the end
   * date given by the user.
   *
   * @param stocksDataArray the data in a portfolio.
   * @param begin           the start date.
   * @param finish          the end date.
   * @return a map that contains the portfolio value for a corresponding key (time-range).
   */
  @Override
  public Map<String, Double> portfolioPerformance(JSONArray stocksDataArray, String begin,
          String finish) {
    Map<String, Double> portfolioPerformance = new LinkedHashMap<>();
    long dateRange = ChronoUnit.DAYS.between(LocalDate.parse(begin), LocalDate.parse(finish));

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();

    double minimum = 0;
    double maximum = 0;

    try {
      startDate.setTime(dateFormat.parse(begin));
      endDate.setTime(dateFormat.parse(finish));
    } catch (ParseException e) {
      System.out.println("Failed to parse date.");
    }

    if (dateRange <= 30) {
      // Date as the key

      DateFormat keyFormatter = new SimpleDateFormat("dd MMM yyyy");
      while (startDate.before(endDate) || startDate.equals(endDate)) {
        String date = dateFormat.format(startDate.getTime());
        double value = getPortfolioValue(stocksDataArray, date);
        while (value == -1) {
          startDate.add(Calendar.DATE, 1);
          date = dateFormat.format(startDate.getTime());
          value = getPortfolioValue(stocksDataArray, date);
        }
        String dateKey = keyFormatter.format(startDate.getTime());
        startDate.add(Calendar.DATE, 1);
        if (value > 0) {
          minimum = Math.min(minimum, value);
        }
        maximum = Math.max(maximum, value);
        portfolioPerformance.put(dateKey, value);
      }


    } else if (dateRange <= 200) {
      // Week as the key

      LocalDate firstFriday = LocalDate.parse(begin).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
      String date = firstFriday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      DateFormat keyFormatter = new SimpleDateFormat("dd MMM yyyy");
      while (startDate.before(endDate) || startDate.equals(endDate)) {
        double value = getPortfolioValue(stocksDataArray, date);
        while (value == -1) {
          startDate.add(Calendar.DATE, -1);
          date = dateFormat.format(startDate.getTime());
          value = getPortfolioValue(stocksDataArray, date);
        }
        String dateKey = keyFormatter.format(startDate.getTime());
        startDate.add(Calendar.DATE, 7);
        date = dateFormat.format(startDate.getTime());
        if (value > 0) {
          minimum = Math.min(minimum, value);
        }
        maximum = Math.max(maximum, value);
        portfolioPerformance.put(dateKey, value);
      }

    } else if (dateRange <= 1600) {
      // Month as the key
      String[] startDateArray = begin.split("-");
      String[] endDateArray = finish.split("-");

      StringBuilder start = new StringBuilder();
      StringBuilder end = new StringBuilder();
      for (int i = 0; i < startDateArray.length - 1; i++) {
        start.append(startDateArray[i]);
        end.append(endDateArray[i]);
        if (i == 0) {
          start.append("-");
          end.append("-");
        }
      }

      DateFormat monthYearFormatter = new SimpleDateFormat("yyyy-MM");
      DateFormat keyFormatter = new SimpleDateFormat("MMM yyyy");
      try {
        startDate.setTime(monthYearFormatter.parse(String.valueOf(start)));
        endDate.setTime(monthYearFormatter.parse(String.valueOf(end)));
      } catch (ParseException e) {
        System.out.println("Failed to parse date.");
      }

      while (startDate.before(endDate) || startDate.equals(endDate)) {
        String month = monthYearFormatter.format(startDate.getTime());
        String firstDayOfMonth = month + "-01";

        Date convertedDate = new Date();
        try {
          convertedDate = dateFormat.parse(firstDayOfMonth);
        } catch (ParseException e) {
          System.out.println("Failed to parse date.");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(convertedDate);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastValidDate = dateFormat.format(c.getTime());

        double value = getPortfolioValue(stocksDataArray, lastValidDate);
        while (value == -1) {
          LocalDate newDate = LocalDate.parse(lastValidDate).minusDays(1);
          value = getPortfolioValue(stocksDataArray, String.valueOf(newDate));
          lastValidDate = String.valueOf(newDate);
        }

        String monthKey = keyFormatter.format(convertedDate);
        startDate.add(Calendar.MONTH, 1);
        if (value > 0) {
          minimum = Math.min(minimum, value);
        }
        maximum = Math.max(maximum, value);
        portfolioPerformance.put(monthKey, value);
      }

    } else {
      // Year as the key

      String[] startDateArray = begin.split("-");
      String[] endDateArray = finish.split("-");

      StringBuilder start = new StringBuilder();
      StringBuilder end = new StringBuilder();
      start.append(startDateArray[0]).append("-12-31");
      end.append(endDateArray[0]).append("-12-31");

      Date currentDate = new Date();
      Date lastValidDate = new Date();
      boolean flag = false;

      try {
        startDate.setTime(dateFormat.parse(String.valueOf(start)));
        endDate.setTime(dateFormat.parse(String.valueOf(end)));
      } catch (ParseException e) {
        System.out.println("Failed to parse date.");
      }

      DateFormat keyFormatter = new SimpleDateFormat("yyyy");
      while ((startDate.before(endDate) || startDate.equals(endDate)) && !flag) {
        String date = dateFormat.format(startDate.getTime());

        try {
          currentDate = dateFormat.parse(date);
        } catch (ParseException ex) {
          System.out.println("Failed to parse date.");
        }

        if (currentDate.compareTo(lastValidDate) > 0) {
          date = dateFormat.format(lastValidDate);
          startDate = Calendar.getInstance();
          flag = true;
        }

        double value = getPortfolioValue(stocksDataArray, date);
        while (value == -1) {
          startDate.add(Calendar.DATE, -1);
          date = dateFormat.format(startDate.getTime());
          value = getPortfolioValue(stocksDataArray, date);
        }
        String dateKey = keyFormatter.format(startDate.getTime());
        startDate.add(Calendar.YEAR, 1);
        if (value > 0) {
          minimum = Math.min(minimum, value);
        }
        maximum = Math.max(maximum, value);
        portfolioPerformance.put(dateKey, value);
      }
    }
    maximum /= 50;
    double roundMax = Math.pow(10, -Math.floor(Math.log10(maximum)));
    double scale = Math.ceil(maximum * roundMax) / roundMax;
    portfolioPerformance.put("portfolio_performance_scale", scale);
    return portfolioPerformance;
  }


  /**
   * Executes and builds the portfolio for an investment strategy, for instance, dollar cost
   * averaging.
   *
   * @param strategyName         the strategy name.
   * @param jsonName             the portfolio name as specified by the user.
   * @param userName             the username.
   * @param startDateString      the start date of the investment strategy.
   * @param endDateString        the end date of the investment strategy. If it is an empty string,
   *                             then the strategy is ongoing.
   * @param interval             the time interval between successive investments.
   * @param investmentValue      the total value to be invested every single time.
   * @param stockNamesArray      the StockNamesArray. Contains all the stock names.
   * @param stockWeightagesArray the StockWeightagesArray. Contains all the weightages for the
   *                             investment strategy.
   * @param commissionArray      contains the commissions per stock.
   * @param endDateExists        tells whether end date exists or not.
   * @return true if the investment strategy is executed successfully.
   */
  @Override
  public boolean investmentStrategy(String strategyName, String jsonName, String userName,
          String startDateString, String endDateString, int interval,
          double investmentValue, String[] stockNamesArray, double[] stockWeightagesArray,
          double[] commissionArray, boolean endDateExists) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();
    Calendar currentDate = Calendar.getInstance();

    double weightageSum = 0;
    double multiplyFactor;
    for (double v : stockWeightagesArray) {
      weightageSum += v;
    }

    if (weightageSum < 100) {
      multiplyFactor = 100 / weightageSum;
      for (int i = 0; i < stockWeightagesArray.length; i++) {
        stockWeightagesArray[i] *= multiplyFactor;
      }
    }

    if (strategyName.equals("DOLLAR COST AVERAGING")) {
      if (!endDateExists) {
        // End Date is not present
        endDateString = dateFormat.format(currentDate.getTime());
      }

      try {
        startDate.setTime(dateFormat.parse(startDateString));
        endDate.setTime(dateFormat.parse(endDateString));
      } catch (ParseException e) {
        System.out.println("Failed to parse date.");
      }

      while (startDate.before(endDate) || startDate.equals(endDate)) {
        int day = startDate.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SATURDAY) {
          Calendar newDate = startDate;
          newDate.add(Calendar.DATE, 2);
          if (newDate.before(endDate) || newDate.equals(endDate)) {
            startDate = newDate;
          } else {
            break;
          }
        } else if (day == Calendar.SUNDAY) {
          Calendar newDate = startDate;
          newDate.add(Calendar.DATE, 1);
          if (newDate.before(endDate) || newDate.equals(endDate)) {
            startDate = newDate;
          } else {
            break;
          }
        }

        String date = dateFormat.format(startDate.getTime());
        double[] quantitiesArray = new double[stockNamesArray.length];
        String[] buyDatesArray = new String[stockNamesArray.length];
        for (int i = 0; i < stockNamesArray.length; i++) {
          try {
            double stockPrice = getPrice(stockNamesArray[i], date, false);
            double quantity =
                    ((investmentValue * stockWeightagesArray[i] / 100) - commissionArray[i])
                            / stockPrice;
            quantity = Math.round(quantity * 100.0) / 100.0;
            quantitiesArray[i] = quantity;
          } catch (IOException e) {
            System.out.println(e);
          }
          buyDatesArray[i] = date;
        }
        writePortfolioToJson(userName, jsonName, stockNamesArray, buyDatesArray, quantitiesArray,
                commissionArray, "BUY");
        startDate.add(Calendar.DATE, interval);
      }

      if (!endDateExists) {
        File ongoingStrategies = new File(
                "res/users/" + userName + "/flexible/ongoing_strategies.csv");

        String fileData;
        String newFileData = "";
        Path file = Path.of("res/users/" + userName + "/flexible/ongoing_strategies.csv");
        try {
          fileData = Files.readString(file);
          String[] fileDataArray = fileData.split("\n");
          boolean flag = false;
          for (int i = 0; i < fileDataArray.length; i++) {
            String metaData = fileDataArray[i];
            String[] data = metaData.split(";");
            if (data[0].equals(jsonName)) {
              data[4] = dateFormat.format(startDate.getTime());
              flag = true;
            }

            String newMetaData = "";
            for (int j = 0; j < data.length; j++) {
              newMetaData += data[j];
              if (j != data.length - 1) {
                newMetaData += ";";
              }
            }
            fileDataArray[i] = newMetaData;
            newFileData += fileDataArray[i];
            newFileData += "\n";
          }
          if (!flag) {
            newFileData += jsonName + ";" + "DOLLAR COST AVERAGING" + ";" + userName + ";"
                    + interval + ";" + dateFormat.format(startDate.getTime()) + ";"
                    + investmentValue
                    + ";" + Arrays.toString(stockNamesArray) + ";" + Arrays.toString(
                    stockWeightagesArray) + ";" + Arrays.toString(commissionArray);
          }
        } catch (IOException e) {
          System.out.println(e);
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(ongoingStrategies))) {
          bf.write(newFileData);
          bf.flush();
        } catch (IOException ex) {
          System.out.println(ex);
        }
      }
      return true;
    }
    return false;
  }


  /**
   * Checks if a strategy is ongoing. Helps to refresh the portfolio to the current date. It is
   * possible that a strategy was declared 1 year ago, hence the portfolio needs to be refreshed to
   * the current date before performing any other operation in program.
   *
   * @param userName the username as input by the user.
   * @param jsonName the portfolio name as input by the user.
   * @return         An array of strings with the metadata for the ongoing strategy,
   *                 else returns an empty array.
   */
  @Override
  public String[] isStrategyOngoing(String userName, String jsonName) {
    String fileData;
    Path file = Path.of("res/users/" + userName + "/flexible/ongoing_strategies.csv");
    try {
      fileData = Files.readString(file);
      String[] fileDataArray = fileData.split("\n");
      for (String s : fileDataArray) {
        String[] data = s.split(";");
        if (data[0].equals(jsonName)) {
          return data;
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }
    return new String[]{};
  }
}