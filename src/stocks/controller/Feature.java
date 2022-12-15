package stocks.controller;

/**
 * Defines the Feature interface. The feature interface contains all the different functionalities
 * or features, offered by our program. These features are implemented in the controller and called
 * by the GUI, as when certain actions (button clicks, etc) are performed.
 */
public interface Feature {

  /**
   * Facilitates the login functionality in our program.
   *
   * @param userName  the username as input by the user.
   * @param password  the password as input by the user.
   */
  void login(String userName, String password);


  /**
   * Facilitates the sign-up functionality in our program.
   *
   * @param userName  the username as input by the user.
   * @param password  the password as input by the user.
   */
  void signUp(String userName, String password);


  /**
   * The buy stocks or shares functionality in our program.
   */
  void buy();


  /**
   * The sell stocks or shares functionality in our program.
   */
  void sell();


  /**
   * Takes a portfolio name as the input and validates it.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param operation     the operation that is being performed (buy/ sell).
   */
  void validPortfolioParameters(String portfolioName, String operation);


  /**
   * Writes the data as input by the user to a portfolio (JSON).
   *
   * @param operation the operation that is being performed (buy/ sell).
   */
  void writeToPortfolio(String operation);


  /**
   * Gets the stock data as input by the user, and sets it for further use.
   *
   * @param operation   the operation that is being performed (buy/ sell).
   * @param stockName   the stock name as input by the user.
   * @param quantity    the quantity as input by the user.
   * @param commission  the commission as input by the user.
   * @param year        the year as input by the user.
   * @param month       the month as input by the user.
   * @param date        the date as input by the user.
   */
  void setStockData(String operation, String stockName, double quantity, double commission,
          String year, String month, String date);


  /**
   * The dollar cost investment strategy functionality.
   */
  void dollarCostAveraging();


  /**
   * Gets the data for the dollar cost investment strategy functionality.
   *
   * @param portfolioName     the name of the portfolio.
   * @param investmentValue   the value to be invested every time.
   * @param interval          days between successive investments.
   * @param isOngoingFlag     true if the strategy is ongoing, false otherwise.
   */
  void dollarCostAveragingGetDataOne(String portfolioName, double investmentValue,
          int interval, boolean isOngoingFlag);


  /**
   * Acts as a helper method for dollarCostAveragingGetDataOne.
   */
  void dollarCostAveragingGetDataTwo();


  /**
   * Sets the data as input by the user for the dollar cost investment strategy
   * functionality, for further use.
   *
   * @param stockName     the stock name as input by the user.
   * @param commission    the commission as input by the user.
   * @param weightage     the weightage as input by the user.
   */
  void dollarCostAveragingSetDataOne(String stockName, double commission, double weightage);


  /**
   * Sets the data as input by the user for the dollar cost investment strategy
   * functionality, for further use. Acts as a helper method to dollarCostAveragingSetDataOne.
   *
   * @param year1     the start year.
   * @param month1    the start month.
   * @param date1     the start date.
   * @param year2     the end year.
   * @param month2    the end month.
   * @param date2     the end date.
   */
  void dollarCostAveragingSetDataTwo(String year1, String month1, String date1,
          String year2, String month2, String date2);


  /**
   * Implements the examine composition of an existing portfolio functionality.
   */
  void examineComposition();


  /**
   * Gets the composition of a portfolio.
   *
   * @param portfolioName   the portfolio name as input by the user.
   * @param year            the year as input by the user.
   * @param month           the month as input by the user.
   * @param date            the date as input by the user.
   */
  void getComposition(String portfolioName, String year, String month, String date);


  /**
   * Implements the cost value basis functionality.
   */
  void costValueBasis();


  /**
   * Gets the cost value basis of a portfolio.
   *
   * @param portfolioName   the portfolio name as input by the user.
   * @param year            the year as input by the user.
   * @param month           the month as input by the user.
   * @param date            the date as input by the user.
   */
  void getCostValueBasis(String portfolioName, String year, String month, String date);


  /**
   * Implements the total portfolio value functionality.
   */
  void totalPortfolioValue();


  /**
   * Gets the total value of a portfolio.
   *
   * @param portfolioName   the portfolio name as input by the user.
   * @param year            the year as input by the user.
   * @param month           the month as input by the user.
   * @param date            the date as input by the user.
   */
  void getTotalPortfolioValue(String portfolioName, String year, String month, String date);


  /**
   * Implements the portfolio performance functionality.
   */
  void portfolioPerformance();


  /**
   * Implements the get portfolio performance functionality. Prints the portfolio performance graph.
   *
   * @param portfolioName     the portfolio name as input by the user.
   * @param year2             the end year as input by the user.
   * @param month2            the end month as input by the user.
   * @param date2             the end date as input by the user.
   * @param year1             the start year as input by the user.
   * @param month1            the start month as input by the user.
   * @param date1             the start date as input by the user.
   * @param chartType         either line chart or a bar graph.
   */
  void getPortfolioPerformance(String portfolioName, String year2, String month2,
          String date2, String year1, String month1, String date1, String chartType);


  /**
   * Facilitates going back to the main menu functionality.
   */
  void backToMainMenu();


  /**
   * Facilitates exiting our program.
   */
  void exit();


  /**
   * Facilitates creating a line chart.
   */
  void lineChart();
}
