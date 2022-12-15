package stocks.view;

import java.io.IOException;
import java.util.Map;

/**
 * Defines the StocksViewInterface. The view is responsible just for displaying the outputs to the
 * user, and more specifically in the case of our program; for printing out the console-based UI and
 * output. The view is independent of the model and controller, and does not know about their
 * existence. The controller is responsible for calling the view and it's methods as and when
 * required.
 */
public interface StocksViewInterface {


  /**
   * Prints the login or sign-up menu on the console.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void loginMenu() throws IOException;


  /**
   * Prints the prompt to ask user for a username input.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getUserName() throws IOException;


  /**
   * Prints the prompt to ask user for a password prompt.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getPassword() throws IOException;


  /**
   * Prints the Inflexible portfolio main menu of the program.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getInflexibleMenu() throws IOException;

  /**
   * Prints the Inflexible portfolio main menu of the program.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getFlexibleMenu() throws IOException;


  /**
   * Prints the portfolio type menu.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void portfolioTypeMenu() throws IOException;


  /**
   * Prints the prompt to ask user for the number of stocks in a portfolio.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getStocksInEachPortfolio() throws IOException;


  /**
   * Prints the prompt to ask user for the name of the portfolio.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getStockNameForPortfolio() throws IOException;


  /**
   * Prints the prompt to ask user for the number of stocks bought.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getStockQuantity() throws IOException;


  /**
   * Prints the prompt to ask user for the commission.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getCommission() throws IOException;


  /**
   * Prints the portfolio header. Basically, just the table headers.
   *
   * @param transactionFlag true if the transaction is of type BUY/ SELL, else false.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void portfolioHeader(boolean transactionFlag) throws IOException;

  /**
   * Prints the stock data header. Basically just the stock and it's number. For eg, FOR STOCK 1
   *
   * @param i the number or index (the ith stock).
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void stocksHeader(int i) throws IOException;

  /**
   * Prints the prompt to ask user for the name of the json.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getJSONName() throws IOException;

  /**
   * Prints the stockData after reading it from a json. This function is used to examine a
   * portfolio. The function outputs the data for all the stocks in a particular portfolio.
   *
   * @param stockNamesArray     the names of the stocks.
   * @param stockDatesArray     the buying dates.
   * @param quantitiesArray     the quantities.
   * @param buyPriceArray       the buy prices.
   * @param lastValidPriceArray the last valid prices.
   * @param size                the number of stocks.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void printStocksData(String[] stockNamesArray, String[] stockDatesArray,
          double[] quantitiesArray, String[] lastTransactionDateArray, String[] buyPriceArray,
          String[] avgPriceArray, String[] commissionArray, String[] lastValidPriceArray,
          int size, boolean transactionFlag) throws IOException;


  /**
   * Prints the prompt to ask user for the buying date.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getDateHeader(String operation) throws IOException;


  /**
   * Prints the prompt to ask user for the year.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getYear() throws IOException;


  /**
   * Prints the prompt to ask user for the month.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getMonth() throws IOException;


  /**
   * Prints the prompt to ask user for the date.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getDate() throws IOException;


  /**
   * Prints an error message on the console for the user to see.
   *
   * @param message the error message.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void printMessage(String message) throws IOException;

  /**
   * Prints the total portfolio value on the console. This function is used to display the total
   * value of a portfolio on a particular date.
   *
   * @param portfolioValue the portfolio value.
   * @param date           the date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void printPortfolioValue(double portfolioValue, String date) throws IOException;


  /**
   * Prints the total printCostBasisValue on the console. This function is used to display the total
   * value of a portfolio on a particular date.
   *
   * @param costBasis the portfolio value.
   * @param date      the date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void printCostBasisValue(double costBasis, String date) throws IOException;


  /**
   * Prints the portfolio performance in a month wise manner. Gives a graphical representation of
   * the performance of the portfolio.
   *
   * @param portfolioPerformanceMap the hashmap with the portfolio values for the last working day
   *                                of the corresponding months.
   * @param jsonName                the name of the portfolio.
   * @param startDate               the start date.
   * @param endDate                 the end date.
   * @param scale                   the amount represented by one asterisk.
   */
  void printPortfolioPerformance(Map<String, Double> portfolioPerformanceMap, String jsonName,
          String startDate, String endDate, double scale) throws IOException;


  /**
   * Prints the prompt to ask user for the weightage of each stock.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getStockWeightage() throws IOException;


  /**
   * Prints the prompt to ask user for the value to be invested for the dollar value averaging.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getInvestmentValue() throws IOException;


  /**
   * Prints the prompt to ask user for the interval for the dollar value averaging.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  void getInterval() throws IOException;
}
