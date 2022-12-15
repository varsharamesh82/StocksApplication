package stocks.model;

import jsonparser.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Defines the StocksModelInterface. The model handles all the logical functionality and
 * calculations of the program. The model works independently of the view and the controller, and
 * does not know about their existence. It's contains the actual logic of the program. The
 * controller is responsible for calling the methods in the model by using an object of the model's
 * interface.
 */
public interface StocksModelInterface {

  /**
   * Checks if a username is valid or not. Returns true if the username is valid.
   *
   * @param userName the username as input by the user.
   * @return true if the username is valid, else return false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidUserName(String userName) throws IOException;


  /**
   * Creates a directory for the user with the username as the name of the directory. All portfolios
   * for a user are stored inside this folder.
   *
   * @param userName the username which is used as the name of the directory.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void createUserDirectories(String userName) throws IOException;


  /**
   * Used to save the username and password in a separate user_credentials file. The username and
   * password are stored by encrypting them, to prevent illegal access.
   *
   * @param userName the username as input by the user.
   * @param password the password as input by the user.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean saveLoginCredentials(String userName, String password) throws IOException;


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
  boolean validateLoginCredentials(String userName, String password) throws IOException;


  /**
   * Sets the ticker names or the stock names that are valid. Reads the 'valid_symbols.csv' file to
   * get all the valid stock symbols. This is later used to verify if a stock name entered by the
   * user is valid or not.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean setValidSymbols() throws IOException;


  /**
   * Sets the stock names for which the ticker data is stored locally as a CSV. Reads the
   * 'csv_symbols.csv' file to get all the stocks for which the data is stored locally. This is
   * later used to minimize the calls to the API by fetching the data from the local files for the
   * stocks for which the data is available locally.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean setCSVSymbols() throws IOException;


  /**
   * Returns a List from an input BufferedReader by splitting the data in the file by the separator.
   * Works as a helper method for setValidSymbols and setCSVSymbols methods.
   *
   * @param reader    the BufferedReader or file input.
   * @param separator the separator for which we've to split the data.
   * @return a list of the data, split by the separator.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  List<String> readFromResource(BufferedReader reader, String separator) throws IOException;


  /**
   * Checks if the stock name as input by the user is valid or not. Compares the stock name with a
   * list of the valid stock names that is initialized by the setValidSymbols function.
   *
   * @param stockName stock name as input by the user.
   * @return true if the stock name is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidSymbolName(String stockName) throws IOException;


  /**
   * Checks if the file name as input by the user is valid or not. The file name is invalid if the
   * file already exists. This function acts as a helper for the create portfolio functionality.
   *
   * @param jsonName the name of the json as input by the user.
   * @param userName the username as input by the user.
   * @return true if the json name is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidFileName(String jsonName, String userName) throws IOException;


  /**
   * Checks if the file name as input by the user is valid or not. The file name is invalid if it
   * does not exist. This function acts as a helper for the examine portfolio and get total value of
   * a portfolio functionality.
   *
   * @param jsonName the name of the json as input by the user.
   * @param userName the username as input by the user.
   * @return true if the json name is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidFileNameForExamination(String jsonName, String userName) throws IOException;


  /**
   * Checks if the number of stocks or stock quantity input by the user is valid or not. The stock
   * quantity for a particular stock cannot be less than or equal to 0.
   *
   * @param stockQuantity the quantity as input by the user.
   * @return true if the stock quantity is greater than or equal to 1, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidQuantity(double stockQuantity) throws IOException;


  /**
   * Checks if the investment value per portfolio as input by the user is valid or not. The
   * investment value is valid if it is greater than or equal to 0.
   *
   * @param investmentValue investment value as input by the user.
   * @return true if the investment value is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidInvestmentValue(double investmentValue) throws IOException;


  /**
   * Checks if the interval value per portfolio as input by the user is valid or not. The interval
   * value is valid if it is greater than or equal to 1.
   *
   * @param interval the interval value as input by the user.
   * @return true if the interval value is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkInterval(int interval) throws IOException;


  /**
   * Executes and builds the portfolio for an investment strategy, for instance, dollar cost
   * averaging.
   *
   * @param strategyName         the strategy name.
   * @param jsonName             the portfolio name as specified by the user.
   * @param userName             the username.
   * @param startDate            the start date of the investment strategy.
   * @param endDate              the end date of the investment strategy. If it is an empty string,
   *                             then the strategy is ongoing.
   * @param interval             the time interval between successive investments.
   * @param investmentValue      the total value to be invested every single time.
   * @param stockNamesArray      the StockNamesArray. Contains all the stock names.
   * @param stockWeightagesArray the StockWeightagesArray. Contains all the weightages for the
   *                             investment strategy.
   * @param commissionArray      contains the commissions per stock.
   * @param endDateExists        tells whether end date exists or not.
   * @return true if the investment strategy is executed successfully.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean investmentStrategy(String strategyName, String jsonName, String userName,
          String startDate, String endDate, int interval, double investmentValue,
          String[] stockNamesArray, double[] stockWeightagesArray,
          double[] commissionArray, boolean endDateExists) throws IOException;


  /**
   * Checks if the weightage per stock as input by the user is valid or not. The stock weightage is
   * valid if it is greater than or equal to 0, less than or equal to 100 and the sum of all
   * weightages is 100.
   *
   * @param stockWeightagesArray the valid stock weightages upto this point.
   * @param stockWeightage       the weightage as input by the user.
   * @return true if the stock weightage is valid, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidWeightage(double[] stockWeightagesArray, double stockWeightage)
          throws IOException;


  /**
   * Checks if the commission per transaction input by the user is valid or not. The commission for
   * a particular transaction cannot be less than 0.
   *
   * @param commission the commission as input by the user.
   * @return true if the commission is greater than 0, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidCommission(double commission) throws IOException;


  /**
   * Checks if the year as input by the user is valid or not. The year must be greater than 1999 and
   * year lesser than or equal to currentYear.
   *
   * @param year the year as input by the user.
   * @return true if the year is valid, else false.
   */
  boolean checkValidYear(String year) throws IOException;


  /**
   * Checks if the month as input by the user is valid or not. The month must be greater than 1 and
   * year lesser than or equal 12.
   *
   * @param month the year as input by the user.
   * @return true if the month is valid, else false.
   */
  boolean checkValidMonth(String month) throws IOException;


  /**
   * Checks if the date as input by the user is valid or not.
   *
   * @param date the year as input by the user.
   * @return true if the date is valid, else false.
   */
  boolean checkValidDate(String date, String month, String year) throws IOException;


  /**
   * Checks if the buying date as input by the user is valid or not. The buying date cannot be on a
   * weekend, as the market is closed. Hence, no stocks are traded or available on the weekends.
   *
   * @param buyDate the buying date as input by the parameter.
   * @return true if the date is valid or not a weekend, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidBuyDate(String buyDate) throws IOException;


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
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean writePortfolioToJson(String userName, String jsonFileName, String[] stockNamesArray,
          String[] stockBuyDatesArray, double[] stockQuantitiesArray, double[] commissionsArray,
          String operation) throws IOException;


  /**
   * Gets or reads the stock data from an existing JSON. This is used to examine the portfolio and
   * to get the value of the portfolio on a certain date. It returns the data in the JSON as a
   * JSONArray.
   *
   * @param userName     username as input by the user.
   * @param jsonFileName jsonName as input by the user.
   * @return jsonArray with the data in the JSON.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  JSONArray getPortfolioFromJson(String userName, String jsonFileName) throws IOException;


  /**
   * Gets or reads the stock data from an existing JSON. This is used to get the composition of a
   * portfolio on a specific date.
   *
   * @param date         the date as input by the user.
   * @param userName     username as input by the user.
   * @param jsonFileName jsonName as input by the user.
   * @return jsonArray with the data in the JSON.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  JSONArray getCompositionHelper(String date, String userName, String jsonFileName)
          throws IOException;


  /**
   * Calls the API and returns the API data as a Map and more specifically as a HashMap with the key
   * as the date, and the value as the closing price on that day.
   *
   * @param stockSymbol the stock name as input by the user.
   * @return returns the Map of values from the API.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  Map<String, Double> callAPI(String stockSymbol) throws IOException;


  /**
   * The getPrice method is used to determine the closing price of a stock on a particular date.
   *
   * @param stockSymbol        the stock name as input by the user.
   * @param date               the buying date as input by the user.
   * @param lastValidPriceFlag set to true, when computing the last valid price.
   * @return the price on that day. -1 if it couldn't find the price on that day.
   * @throws IOException if the url used to hit the API failed.
   */
  Double getPrice(String stockSymbol, String date, boolean lastValidPriceFlag)
          throws IOException;


  /**
   * Acts as a helper method for the getPrice function. It calls the API and saves the data to a csv
   * for future reference.
   *
   * @param stockSymbol        the stock name as input by the user.
   * @param date               the date as input by the function.
   * @param lastValidPriceFlag set to true, when computing the last valid price.
   * @return the price on that particular day.
   */
  Double getPriceHelper(String stockSymbol, String date, boolean lastValidPriceFlag)
          throws IOException;


  /**
   * To get the lastValidPrice on any particular day. For example, the lastValidPrice on a weekend
   * would be the last traded price. Thus return the last traded price.
   *
   * @param stockName the stock name as input by the user.
   * @return the last traded price for a stock name.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  double getLastValidPrice(String stockName) throws IOException;


  /**
   * Get the total portfolio value on a particular day, as entered by the user. Multiplying the
   * quantity by the buying price for each stock in a portfolio, gives the total value of that
   * portfolio.
   *
   * @param stocksDataArray the stocks date as input by the user.
   * @param date            the date as input by the user.
   * @return returns the total portfolio value for a particular portfolio.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  double getPortfolioValue(JSONArray stocksDataArray, String date) throws IOException;

  /**
   * Sets the validSellSymbols list, that contains the stocks names in a particular portfolio that
   * can be sold by the user.
   *
   * @param jsonName the json name as entered by the user.
   * @param userName the username as entered by the user.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void setValidSellSymbols(String jsonName, String userName) throws IOException;


  /**
   * Checks if the stock name entered by the user for selling has been bought earlier in the
   * portfolio.
   *
   * @param stockName the stock name as input by the user.
   * @return true if it has bought earlier in the portfolio, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean checkValidSellSymbols(String stockName) throws IOException;


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
   *                     stock earlier and the quantity of buy is greater than or
   *                     equal to the quantity of sell.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  boolean validSellOrderQuantity(String stockName, double quantity, String date, String userName,
          String jsonFileName) throws IOException;


  /**
   * Used to encrypt the username and password, so that the login credentials are not susceptible to
   * any attacks. The encrypted credentials are saved to the login_credentials file.
   *
   * @param text the text that needs to be encrypted.
   * @return the encrypted string.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  String encrypt(String text) throws IOException;


  /**
   * Used to decrypt the username and password stored in the login_credentials file. The credentials
   * in the login_credentials file are encrypted to protect them from attacks. Thus, we need to
   * decrypt the values before they can be compared and validated.
   *
   * @param text the text that needs to be decrypted.
   * @return the decrypted string.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  String decrypt(String text) throws IOException;


  /**
   * Calculates the cost value basis for a portfolio on a give date. The cost value basis takes the
   * commission and the money invested by the user till date. It does not take net selling into
   * account.
   *
   * @param stocksDataArray the data in a portfolio.
   * @param date            the date as input by the user.
   * @return the cost value basis for a portfolio till that date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  double getCostValueBasis(JSONArray stocksDataArray, String date) throws IOException;


  /**
   * Calculate the portfolio performance for a give portfolio over a time range. The portfolio
   * performance is either daily, weekly, monthly or yearly depending on the start date and the end
   * date given by the user.
   *
   * @param stockDataArray the data in a portfolio.
   * @param begin          the start date.
   * @param finish         the end date.
   * @return a map that contains the portfolio value for a corresponding key (time-range).
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  Map<String, Double> portfolioPerformance(JSONArray stockDataArray, String begin, String finish)
          throws IOException;

  /**
   * Checks if a strategy is ongoing. Helps to refresh the portfolio to the current date. It is
   * possible that a strategy was declared 1 year ago, hence the portfolio needs to be refreshed to
   * the current date before performing any other operation in program.
   *
   * @param userName the username as input by the user.
   * @param jsonName the portfolio name as input by the user.
   * @return  An array of strings with the metadata for the ongoing strategy, else returns an empty
   *          array.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  String[] isStrategyOngoing(String userName, String jsonName) throws  IOException;
}