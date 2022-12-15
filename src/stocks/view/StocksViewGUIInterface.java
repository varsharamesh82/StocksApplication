package stocks.view;

import java.util.Map;
import stocks.controller.Feature;

/**
 * Defines the StocksViewInterface. The view is responsible just for displaying the outputs to the
 * user, and more specifically in the case of our program; for printing out the console-based UI and
 * output. The view is independent of the model and controller, and does not know about their
 * existence. The controller is responsible for calling the view and it's methods as and when
 * required.
 */
public interface StocksViewGUIInterface {


  /**
   * Prints the login or sign-up menu on the console.
   */
  void loginMenu();


  /**
   * The features for the loginMenu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void loginMenuFeatures(Feature feature);


  /**
   * Prints the main menu of the program.
   */
  void mainMenu();


  /**
   * The features for the main menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void mainMenuFeatures(Feature feature);


  /**
   * Prints the menu that asks for a portfolio name.
   */
  void portfolioNameMenu();


  /**
   * The features for the portfolio name menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void portfolioNameMenuFeatures(Feature feature, String operation);


  /**
   * Prints an error message on the console for the user to see.
   *
   * @param message the error message.
   */
  void printMessage(String message);


  /**
   * Prints the main for taking the stock data (name, commission, etc) as input from the  user.
   */
  void getStockData(boolean flag);


  /**
   * The features for the get stock data menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void getStockDataFeatures(Feature feature, boolean flag, String operation);


  /**
   * Prints the menu for examining composition of a portfolio.
   */
  void examineComposition();


  /**
   * The features for the examine composition menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void examineCompositionFeatures(Feature feature);


  /**
   * Prints the composition of a portfolio.
   */
  void displayComposition(Object[][] rows, Object[] columns);


  /**
   * The features for the print composition page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void displayCompositionFeatures(Feature feature);


  /**
   * Prints the menu for calculating cost value basis of a portfolio.
   */
  void costValueBasis();


  /**
   * The features for the calculate cost value basis menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void costValueBasisFeatures(Feature feature);


  /**
   * Prints the cost value basis of a portfolio.
   * @param costBasis the cost value.
   * @param portfolioName the name of the portfolio.
   * @param date  the date on which the cost value is calculated.
   */
  void displayCostValueBasis(double costBasis, String portfolioName, String date);


  /**
   * The features for the cost value basis page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void displayCostValueBasisFeatures(Feature feature);


  /**
   * Prints the total portfolio value menu.
   */
  void totalPortfolioValue();


  /**
   * The features for the total portfolio value page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void totalPortfolioValueFeatures(Feature feature);


  /**
   * Prints the total portfolio value on the UI.
   *
   * @param portfolioValue  the portfolio value.
   * @param portfolioName the name of the portfolio.
   * @param date  the date for which the value is calculated.
   */
  void displayTotalPortfolioValue(double portfolioValue, String portfolioName, String date);


  /**
   * The features for the display portfolio value page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void displayTotalPortfolioValueFeatures(Feature feature);


  /**
   * Prints the portfolio performance menu.
   */
  void portfolioPerformance();


  /**
   * The features for the portfolio performance page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void portfolioPerformanceFeatures(Feature feature);



  /**
   * Prints the portfolio performance helper menu.
   */
  void displayPortfolioPerformance(Map<String, Double> portfolioPerformanceMap, String jsonName,
          String startDate, String endDate, double scale);



  /**
   * The features for the portfolio performance helper page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void displayPortfolioPerformanceFeatures(Feature feature);


  /**
   * Prints the menu for the dollar cost averaging page.
   */
  void dollarCostAveraging();


  /**
   * The features for the dollar cost averaging page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void dollarCostAveragingFeatures(Feature feature);


  /**
   * Print the menu for the dollar cost averaging get data page.
   * @param flag  to set the 'Done' button.
   */
  void dollarCostAveragingGetDataOne(boolean flag);


  /**
   * The features for the dollar cost averaging get data page. Helps communicate with the
   * controller.
   *
   * @param feature an object of the controller.
   * @param flag to set the 'Done' button
   */
  void dollarCostAveragingGetDataOneFeatures(Feature feature, boolean flag);


  /**
   * Prints the menu for the dollar cost averaging get data helper page.
   * @param isOngoing to determine if a strategy has an end date.
   */
  void dollarCostAveragingGetDataTwo(boolean isOngoing);


  /**
   * The features for the dollar cost averaging get data helper page. Helps communicate with the
   * controller.
   *
   * @param feature an object of the controller.
   * @param isOngoing to determine if a strategy has an end date.
   */
  void dollarCostAveragingGetDataTwoFeatures(Feature feature, boolean isOngoing);


  /**
   * Gets the data for a line chart.
   */
  void lineChart();


  /**
   * The features for the get line chart data page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void lineChartFeatures(Feature feature);


  /**
   * Prints a line chart on the page.
   *
   * @param portfolioPerformanceMap   the time, price data.
   * @param jsonName                  the portfolio name.
   * @param startDate                 the start date as input by the user.
   * @param endDate                   the end date as input by the user.
   * @param scale                     the scale for our graph.
   */
  void displayPortfolioLineChart(Map<String, Double> portfolioPerformanceMap, String jsonName,
          String startDate, String endDate, double scale);


  /**
   * The features for the display line chart data page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  void displayPortfolioLineChartFeatures(Feature feature);
}
