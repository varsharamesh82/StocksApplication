package stocks.view;

import java.io.IOException;
import java.util.Map;

/**
 * An implementation of the StocksViewInterface. This class implements all methods in the
 * StocksViewInterface. The view is responsible just for displaying the outputs to the user, and
 * more specifically in the case of our program; for printing out the console-based UI and output.
 * The view is independent of the model and controller, and does not know about their existence. The
 * controller is responsible for calling the view and it's methods as and when required.
 */
public class StocksView implements StocksViewInterface {

  private final Appendable out;

  /**
   * A constructor of the StocksView class. This constructor initializes the Appendable output class
   * variable that is used to print out the events on the console.
   *
   * @param out the output Appendable object.
   */
  public StocksView(Appendable out) {
    this.out = out;
  }


  /**
   * Prints the login or sign-up menu on the console.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void loginMenu() throws IOException {
    this.out.append("\n\n---------------------");
    this.out.append("\n  LOGIN OR REGISTER  ");
    this.out.append("\n---------------------");
    this.out.append("\n1. LOGIN");
    this.out.append("\n2. SIGN UP");
    this.out.append("\n3. EXIT");
    this.out.append("\nENTER YOUR CHOICE: ");
  }


  /**
   * Prints the prompt to ask user for a username input.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getUserName() throws IOException {
    this.out.append("\nENTER THE USERNAME: ");
  }


  /**
   * Prints the prompt to ask user for a password prompt.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getPassword() throws IOException {
    this.out.append("\nENTER THE PASSWORD: ");
  }


  @Override
  public void portfolioTypeMenu() throws IOException {
    this.out.append("\n\n---------------------");
    this.out.append("\n  PORTFOLIO TYPE  ");
    this.out.append("\n---------------------");
    this.out.append("\n1. INFLEXIBLE (CREATE A PORTFOLIO & EXAMINE)");
    this.out.append("\n2. FLEXIBLE (BUY & SELL SHARES)");
    this.out.append("\n3. EXIT");
    this.out.append("\nENTER YOUR CHOICE: ");
  }


  /**
   * Prints the Inflexible portfolio main menu of the program.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getInflexibleMenu() throws IOException {
    this.out.append("\n\n-----------------------------------------------");
    this.out.append("\n  STOCK MARKET – CREATE A PORTFOLIO & EXAMINE  ");
    this.out.append("\n-----------------------------------------------");
    this.out.append("\n1. CREATE A NEW PORTFOLIO");
    this.out.append("\n2. EXAMINE COMPOSITION OF AN EXISTING PORTFOLIO");
    this.out.append("\n3. GET TOTAL VALUE OF A PORTFOLIO ON A DATE");
    this.out.append("\n4. EXIT");
    this.out.append("\nENTER YOUR CHOICE: ");
  }


  /**
   * Prints the Flexible portfolio main menu of the program.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getFlexibleMenu() throws IOException {
    this.out.append("\n\n------------------------------------");
    this.out.append("\n  STOCK MARKET – BUY & SELL SHARES  ");
    this.out.append("\n------------------------------------");
    this.out.append("\n1. BUY SHARES");
    this.out.append("\n2. SELL SHARES");
    this.out.append("\n3. CREATE A DOLLAR COST AVERAGING PORTFOLIO");
    this.out.append("\n4. EXAMINE COMPOSITION OF AN EXISTING PORTFOLIO");
    this.out.append("\n5. COST VALUE BASIS");
    this.out.append("\n6. GET TOTAL VALUE OF A PORTFOLIO ON A DATE");
    this.out.append("\n7. PORTFOLIO PERFORMANCE OVER TIME");
    this.out.append("\n8. EXIT");
    this.out.append("\nENTER YOUR CHOICE: ");
  }


  /**
   * Prints the prompt to ask user for the number of stocks in a portfolio.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getStocksInEachPortfolio() throws IOException {
    this.out.append("\nNUMBER OF STOCKS: ");
  }


  /**
   * Prints the prompt to ask user for the name of the portfolio.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getStockNameForPortfolio() throws IOException {
    this.out.append("\nSTOCK NAME: ");
  }


  /**
   * Prints the prompt to ask user for the number of stocks bought.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getStockQuantity() throws IOException {
    this.out.append("\nQUANTITY: ");
  }


  /**
   * Prints the prompt to ask user for the commission.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getCommission() throws IOException {
    this.out.append("\nCOMMISSION: ");
  }


  /**
   * Prints the stock data header. Basically just the stock and it's number. For eg, FOR STOCK 1
   *
   * @param i the number or index (the ith stock).
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void stocksHeader(int i) throws IOException {
    this.out.append("\n-----------");
    this.out.append(String.format("\nFOR STOCK %d", (i + 1)));
    this.out.append("\n-----------");
  }


  /**
   * Prints the prompt to ask user for the name of the json.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getJSONName() throws IOException {
    this.out.append("\nNAME OF THE PORTFOLIO: ");
  }


  /**
   * Prints the portfolio header. Basically, just the table headers.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void portfolioHeader(boolean transactionFlag) throws IOException {
    this.out.append(
            "\n----------------------------------------------------------------------------------"
                    + "-------------------------------------------------------------------------");
    this.out.append(
            String.format("\n%s%15s%22s%28s%20s%17s%25s", "STOCK NAME", "QUANTITY",
                    "FIRST BUY DATE", "LAST TRANSACTION DATE", "LAST BUY PRICE",
                    "AVG BUY PRICE", "LAST TRADED PRICE"));
    if (transactionFlag) {
      this.out.append(String.format("%15s", "COMMISSION"));
    }

    this.out.append(
            "\n----------------------------------------------------------------------------------"
                    + "-------------------------------------------------------------------------");
  }


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
  @Override
  public void printStocksData(String[] stockNamesArray, String[] stockDatesArray,
          double[] quantitiesArray, String[] lastTransactionDateArray, String[] buyPriceArray,
          String[] avgPriceArray, String[] commissionsArray, String[] lastValidPriceArray,
          int size, boolean transactionFlag) throws IOException {
    for (int i = 0; i < size; i++) {
      this.out.append(
              String.format("\n%s%20s%23s%22s%23s%20s%20s", stockNamesArray[i], quantitiesArray[i],
                      stockDatesArray[i], lastTransactionDateArray[i], buyPriceArray[i],
                      avgPriceArray[i],
                      lastValidPriceArray[i]));
      if (transactionFlag) {
        this.out.append(String.format("%15s", commissionsArray[i]));
      }
    }
    this.out.append(
            "\n----------------------------------------------------------------------------------"
                    + "-------------------------------------------------------------------------");
  }


  /**
   * Prints the prompt to ask user for the buying date.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getDateHeader(String header) throws IOException {
    this.out.append("\n---------------------");
    this.out.append("\nENTER THE " + header + " DATE");
    this.out.append("\n---------------------\n");
  }


  /**
   * Prints the prompt to ask user for the year.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getYear() throws IOException {
    this.out.append("YEAR (yyyy): ");
  }


  /**
   * Prints the prompt to ask user for the month.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getMonth() throws IOException {
    this.out.append("MONTH (mm): ");
  }


  /**
   * Prints the prompt to ask user for the date.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getDate() throws IOException {
    this.out.append("DATE (dd): ");
  }


  /**
   * Prints an error message on the console for the user to see.
   *
   * @param message the error message.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void printMessage(String message) throws IOException {
    this.out.append(message);
  }


  /**
   * Prints the total portfolio value on the console. This function is used to display the total
   * value of a portfolio on a particular date.
   *
   * @param portfolioValue the portfolio value.
   * @param date           the date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void printPortfolioValue(double portfolioValue, String date) throws IOException {
    this.out.append(String.format(
            "\nTOTAL PORTFOLIO VALUE ON " + date + ": $" + String.format("%.2f", portfolioValue)));
  }


  /**
   * Prints the total printCostBasisValue on the console. This function is used to display the total
   * value of a portfolio on a particular date.
   *
   * @param costBasis the portfolio value.
   * @param date      the date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void printCostBasisValue(double costBasis, String date) throws IOException {
    this.out.append(String.format(
            "\nCOST VALUE BASIS ON " + date + ": $" + String.format("%.2f", costBasis)));
  }


  /**
   * Prints the portfolio performance in a month wise manner. Gives a graphical representation of
   * the performance of the portfolio.
   *
   * @param portfolioPerformanceMap the hashmap with the portfolio values for the last working day
   *                                of the corresponding months.
   * @param jsonName                the name of the portfolio.
   * @param startDate               the start date.
   * @param endDate                 the end date.
   */
  @Override
  public void printPortfolioPerformance(Map<String, Double> portfolioPerformanceMap,
          String jsonName,
          String startDate, String endDate, double scale) throws IOException {
    this.out.append("\nPERFORMANCE OF PORTFOLIO " + jsonName + " FROM " + startDate + " TO "
            + endDate + "\n");
    for (String key : portfolioPerformanceMap.keySet()) {
      if (!key.equals("portfolio_performance_scale")) {
        double value = portfolioPerformanceMap.get(key);
        int asterisksCount = (int) Math.round(value / scale);
        this.out.append("\n" + key + ": ");
        for (int i = 0; i < asterisksCount; i++) {
          this.out.append("*");
        }
      }
    }
    this.out.append("\n\nScale: * = " + scale);
  }


  /**
   * Prints the prompt to ask user for the weightage of each stock.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getStockWeightage() throws IOException {
    this.out.append("\nWEIGHTAGE [BETWEEN 0 & 100]: ");
  }


  /**
   * Prints the prompt to ask user for the value to be invested for the dollar value averaging.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getInvestmentValue() throws IOException {
    this.out.append("\nTOTAL MONEY TO BE INVESTED EVERY SINGLE TIME: ");
  }


  /**
   * Prints the prompt to ask user for the interval for the dollar value averaging.
   *
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  @Override
  public void getInterval() throws IOException {
    this.out.append("\nTIME INTERVAL: ");
  }
}
