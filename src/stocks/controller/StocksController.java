package stocks.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import jsonparser.JSONArray;
import jsonparser.JSONObject;
import java.io.IOException;
import java.util.Scanner;
import stocks.model.StocksModelInterface;
import stocks.view.StocksViewInterface;


/**
 * stocksController is an of the stocksControllerInterface. It implements the methods in the
 * stocksControllerInterface. The stockController is responsible for the overall handling and
 * execution of the entire program. Only the stocksController knows about the existence of the
 * stocksView and stocksModel.
 */
public class StocksController implements StocksControllerInterface {
  /**
   * This enum is used to specify the portfolio operations that the user can perform. The operations
   * that a user can perform are -
   * <ol>
   *     <li>Portfolio: For Inflexible Portfolio</li>
   *     <li>Buy: For </li>
   *     <li>Sell: </li>
   * </ol>
   */
  enum PortfolioOperations {
    PORTFOLIO,
    BUY,
    SELL
  }

  private final Scanner scan;
  private final StocksModelInterface stocksModelFlexible;
  private final StocksViewInterface stocksView;
  private final StocksModelInterface stocksModelInflexible;

  /**
   * A constructor of the stocksController class. This constructor is used to initialize the objects
   * of the stocksView and the modelInflexiblePortfolio interfaces and the readable and appendable
   * streams.
   *
   * @param stocksView               An object of the StocksViewInterface. Gives access to methods
   *                                 in the view.
   * @param modelInflexiblePortfolio An object of the StocksModelInterface. Gives access to methods
   *                                 in the inflexible portfolio model.
   * @param modelFlexiblePortfolio   An object of the StocksModelInterface. Gives access to methods
   *                                 in the flexible portfolio model.
   * @param in                       The input Readable stream. Used for inputs.
   * @param out                      The output Appendable stream. Used for outputs.
   */
  public StocksController(StocksViewInterface stocksView,
          StocksModelInterface modelInflexiblePortfolio,
          StocksModelInterface modelFlexiblePortfolio,
          Readable in, Appendable out) {
    this.stocksView = stocksView;
    this.stocksModelInflexible = modelInflexiblePortfolio;
    this.stocksModelFlexible = modelFlexiblePortfolio;
    this.scan = new Scanner(in);
  }

  /**
   * The go method handles the complete flow and execution of the program. It calls the various
   * methods in the view and the model depending on as and when they are required.
   *
   * @throws IOException if it fails to append the logger, that keeps track of the activities.
   */
  @Override
  public void run() throws IOException {
    String userName = "";
    int choice;
    stocksModelInflexible.setValidSymbols();
    stocksModelInflexible.setCSVSymbols();
    stocksModelFlexible.setValidSymbols();
    stocksModelFlexible.setCSVSymbols();

    stocksView.loginMenu();
    choice = Integer.parseInt(scan.next());
    switch (choice) {
      case 1:
        // Login Helper
        userName = loginHelper();
        break;
      case 2:
        // Sign Up Helper
        userName = signUpHelper();
        break;
      case 3:
        // Exit
        return;
      default:
        stocksView.printMessage("INVALID CHOICE. PLEASE RE-ENTER A VALID OPTION.");
    }

    stocksView.portfolioTypeMenu();
    choice = Integer.parseInt(scan.next());
    switch (choice) {
      case 1:
        // Inflexible Portfolio
        inflexiblePortfolio(userName);
        break;

      case 2:
        // Flexible Portfolio
        flexiblePortfolio(userName);
        break;

      case 3:
        // Exit
        return;

      default:
        stocksView.printMessage("INVALID CHOICE. PLEASE RE-ENTER A VALID OPTION.");
    }
  }


  /**
   * A login helper method. Gets the username and the password from the user, and validates it
   * against the login_credentials file.
   *
   * @return the username, once the username is valid.
   * @throws IOException if it fails to append the logger, that keeps track of the activities.
   */
  private String loginHelper() throws IOException {
    while (true) {
      stocksView.getUserName();
      String userName = scan.next();
      stocksView.getPassword();
      String password = scan.next();
      if (stocksModelInflexible.validateLoginCredentials(userName, password)) {
        return userName;
      } else {
        stocksView.printMessage(
                "INVALID USERNAME OR PASSWORD. PLEASE RE-ENTER A VALID USERNAME AND PASSWORD.");
      }
    }
  }


  /**
   * A sign-up helper method. Gets the username and the password from the user, and completes the
   * sign-up process by creating user directories and adding the user credentials in the login
   * credentials file.
   *
   * @return the username, once the username is valid.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private String signUpHelper() throws IOException {
    while (true) {
      stocksView.getUserName();
      String userName = scan.next();
      stocksView.getPassword();
      String password = scan.next();
      if (stocksModelInflexible.checkValidUserName(userName)) {
        stocksModelInflexible.createUserDirectories(userName);
        stocksModelInflexible.saveLoginCredentials(userName, password);
        return userName;
      } else {
        stocksView.printMessage(
                "USERNAME ALREADY EXISTS. PLEASE LOGIN OR ENTER A UNIQUE USERNAME.");

      }
    }
  }


  /**
   * Helper method for the functionalities offered by the inflexible portfolio. Contains the switch
   * case for the inflexible portfolio.
   *
   * @param userName the username as input by the user.
   * @throws IOException if it fails to append the logger, that keeps track of the activities.
   */
  private void inflexiblePortfolio(String userName) throws IOException {
    String jsonName;
    while (true) {
      stocksView.getInflexibleMenu();
      int choice = Integer.parseInt(scan.next());
      switch (choice) {
        case 1:
          // Create new portfolio
          jsonName = createNewPortfolioNameHelper(stocksModelInflexible, userName, true);
          portfolioHelper(stocksModelInflexible, jsonName, userName,
                  String.valueOf(PortfolioOperations.PORTFOLIO));
          break;

        case 2:
          // Examine an existing portfolio
          jsonName = getExistingPortfolioNameHelper(stocksModelInflexible, userName);
          examinePortfolioHelper(stocksModelInflexible, jsonName, userName, false);
          break;

        case 3:
          // Get value of an existing portfolio
          jsonName = getExistingPortfolioNameHelper(stocksModelInflexible, userName);
          portfolioValueHelper(stocksModelInflexible, jsonName, userName);
          break;

        case 4:
          // Exit
          return;

        default:
          stocksView.printMessage("INVALID CHOICE. PLEASE RE-ENTER A VALID OPTION.");
      }
    }
  }


  /**
   * Helper method for the functionalities offered by the flexible portfolio. Contains the switch
   * case for the flexible portfolio.
   *
   * @param userName the username as input by the user.
   * @throws IOException if it fails to append the logger, that keeps track of the activities.
   */
  private void flexiblePortfolio(String userName) throws IOException {
    String jsonName;
    while (true) {
      stocksView.getFlexibleMenu();
      int choice = Integer.parseInt(scan.next());
      switch (choice) {
        case 1:
          // Buy
          jsonName = createNewPortfolioNameHelper(stocksModelFlexible, userName, false);
          portfolioHelper(stocksModelFlexible, jsonName, userName,
                  String.valueOf(PortfolioOperations.BUY));
          break;

        case 2:
          // Sell
          jsonName = getExistingPortfolioNameHelper(stocksModelFlexible, userName);
          portfolioHelper(stocksModelFlexible, jsonName, userName,
                  String.valueOf(PortfolioOperations.SELL));
          break;

        case 3:
          // Dollar-cost averaging
          jsonName = createNewPortfolioNameHelper(stocksModelFlexible, userName, false);
          dollarCostAveragingHelper(stocksModelFlexible, jsonName, userName);
          break;

        case 4:
          // Examine Portfolio
          jsonName = getExistingPortfolioNameHelper(stocksModelFlexible, userName);
          examinePortfolioHelper(stocksModelFlexible, jsonName, userName, true);
          break;

        case 5:
          // Cost value basis
          jsonName = getExistingPortfolioNameHelper(stocksModelFlexible, userName);
          costBasisHelper(stocksModelFlexible, jsonName, userName);
          break;

        case 6:
          // Total value of portfolio
          jsonName = getExistingPortfolioNameHelper(stocksModelFlexible, userName);
          portfolioValueHelper(stocksModelFlexible, jsonName, userName);
          break;

        case 7:
          // Portfolio performance over time
          jsonName = getExistingPortfolioNameHelper(stocksModelFlexible, userName);
          portfolioPerformanceHelper(stocksModelFlexible, jsonName, userName);
          break;

        case 8:
          // Exit
          return;

        default:
          stocksView.printMessage("INVALID CHOICE. PLEASE RE-ENTER A VALID OPTION.");
      }
    }
  }


  /**
   * A helper method that gets a new portfolio name for the flexible and inflexible portfolios. A
   * new portfolio name is valid if it doesn't exit in the location.
   *
   * @param stocksModel         an object of the stocks model interface.
   * @param userName            the username as input by the user.
   * @param checkFileExistsFlag the operation; one of BUY, SELL or PORTFOLIO.
   * @return the name of the portfolio.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private String createNewPortfolioNameHelper(StocksModelInterface stocksModel, String userName,
          boolean checkFileExistsFlag)
          throws IOException {
    while (true) {
      stocksView.getJSONName();
      String jsonName = scan.next();
      if (checkFileExistsFlag) {
        if (stocksModel.checkValidFileName(jsonName, userName)) {
          return jsonName;
        } else {
          stocksView.printMessage(
                  "INVALID PORTFOLIO NAME. THE PORTFOLIO ALREADY EXISTS.");
        }
      } else {
        return jsonName;
      }
    }
  }


  /**
   * A helper method that gets the name of the portfolio for the flexible and the inflexible
   * portfolios. A portfolio name is valid if the file exists in the folder.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param userName    the username as input by the user.
   * @return the name of the portfolio if it's valid.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private String getExistingPortfolioNameHelper(StocksModelInterface stocksModel, String userName)
          throws IOException {
    while (true) {
      stocksView.getJSONName();
      String jsonName = scan.next();
      if (stocksModel.checkValidFileNameForExamination(jsonName, userName)) {
        return jsonName;
      } else {
        stocksView.printMessage(
                "INVALID PORTFOLIO NAME. THE PORTFOLIO DOES NOT EXIST. PLEASE RE-ENTER A VALID "
                        + "PORTFOLIO NAME.");
      }
    }
  }


  /**
   * A helper method for taking user input while creating a new portfolio, buying stocks and selling
   * stocks.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param jsonName    the portfolio name as input by the user.
   * @param userName    the username as input by the user.
   * @param operation   the operation; one of BUY, SELL or PORTFOLIO.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private void portfolioHelper(StocksModelInterface stocksModel, String jsonName, String userName,
          String operation) throws IOException {
    int numberOfStocks;
    String[] stockNamesArray;
    String[] stockDatesArray;
    double[] stockQuantitiesArray;

    getOngoingStrategyUpToDate(userName, jsonName);

    while (true) {
      stocksView.getStocksInEachPortfolio();
      numberOfStocks = Integer.parseInt(scan.next());
      if (stocksModel.checkValidQuantity(numberOfStocks)) {
        break;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. NUMBER OF STOCKS IN A PORTFOLIO MUSE BE >= 1. PLEASE "
                        + "RE-ENTER A VALID NUMBER OF STOCKS.");
      }
    }

    stockNamesArray = new String[numberOfStocks];
    stockDatesArray = new String[numberOfStocks];
    stockQuantitiesArray = new double[numberOfStocks];
    double[] commissionArray = new double[numberOfStocks];

    for (int i = 0; i < numberOfStocks; i++) {
      stocksView.stocksHeader(i);
      stockNamesArray[i] = getStockNameHelper(stocksModel, operation, jsonName, userName);

      if (operation.equals("SELL")) {
        while (true) {
          stockDatesArray[i] = getDateHelper(operation, false);
          stockQuantitiesArray[i] = getQuantityHelper(operation);
          if (stocksModel.validSellOrderQuantity(stockNamesArray[i], stockQuantitiesArray[i],
                  stockDatesArray[i], userName, jsonName)) {
            break;
          } else {
            stocksView.printMessage("CANNOT SELL MORE THAN WHAT YOU'VE BOUGHT.");
          }
        }
      } else {
        stockDatesArray[i] = getDateHelper(operation, false);
        stockQuantitiesArray[i] = getQuantityHelper(operation);
      }
      if (operation.equals("BUY") || operation.equals("SELL")) {
        commissionArray[i] = getCommissionHelper();
      }
    }

    if (!stocksModel.writePortfolioToJson(userName, jsonName, stockNamesArray,
            stockDatesArray, stockQuantitiesArray, commissionArray, operation)) {
      stocksView.printMessage("CREATING A NEW PORTFOLIO AND "
              + "WRITING IT TO JSON FAILED.");
    }
  }


  /**
   * A helper method for taking the commission per transaction for buying and selling from the
   * user.
   *
   * @return the validated commission.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private double getCommissionHelper() throws IOException {
    while (true) {
      double commission;
      stocksView.getCommission();
      commission = scan.nextDouble();

      if (stocksModelFlexible.checkValidCommission(commission)) {
        return commission;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. COMMISSION MUSE BE > 0. PLEASE RE-ENTER A "
                        + "VALID COMMISSION.");
      }
    }
  }


  /**
   * A helper method for the examination of a portfolio.
   *
   * @param stocksModel     an object of the stocks model interface.
   * @param jsonName        the portfolio name as input by the user.
   * @param userName        the username as input by the user.
   * @param transactionFlag whether to take date input or not.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private void examinePortfolioHelper(StocksModelInterface stocksModel, String jsonName,
          String userName, boolean transactionFlag) throws IOException {
    String[] stockNameArray;
    String[] transactionDateArray;
    double[] quantityArray;
    String[] priceArray;
    String[] avgPriceArray;
    String[] commissionArray;
    String[] lastTransactionDateArray;
    String[] lastValidPriceArray;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = new Date();
    String date = dateFormat.format(currentDate);

    getOngoingStrategyUpToDate(userName, jsonName);

    if (transactionFlag) {
      date = getDateHelper("EXAMINATION", true);
    }

    JSONArray stocksData = stocksModel.getCompositionHelper(date, userName, jsonName);
    if (stocksData != null && stocksData.size() != 0) {
      stockNameArray = new String[stocksData.size()];
      transactionDateArray = new String[stocksData.size()];
      quantityArray = new double[stocksData.size()];
      priceArray = new String[stocksData.size()];
      avgPriceArray = new String[stocksData.size()];
      lastTransactionDateArray = new String[stocksData.size()];
      commissionArray = new String[stocksData.size()];
      lastValidPriceArray = new String[stocksData.size()];
      for (int i = 0; i < stocksData.size(); i++) {
        JSONObject temp = (JSONObject) stocksData.get(i);
        lastValidPriceArray[i] = String.valueOf(
                stocksModel.getLastValidPrice((String) temp.get("stock_name")));
        if (lastValidPriceArray[i].equals("-1")) {
          lastValidPriceArray[i] = "NA";
        }
        stockNameArray[i] = (String) temp.get("stock_name");
        transactionDateArray[i] = (String) temp.get("transaction_date");
        quantityArray[i] = Double.parseDouble((String) temp.get("quantity"));
        priceArray[i] = (String) temp.get("price");
        lastTransactionDateArray[i] = (String) temp.get("last_transaction_date");
        avgPriceArray[i] = (String) temp.get("avg_price");
        commissionArray[i] = (String) temp.get("commission");
      }
      stocksView.portfolioHeader(transactionFlag);
      stocksView.printStocksData(stockNameArray, transactionDateArray, quantityArray,
              lastTransactionDateArray, priceArray, avgPriceArray, commissionArray,
              lastValidPriceArray, stocksData.size(), transactionFlag);
    } else {
      stocksView.printMessage(
              "NO DATA AVAILABLE IN THE PORTFOLIO ON OR BEFORE DATE: " + date + ".");
    }
  }


  /**
   * A helper method for getting the portfolio value.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param jsonName    the portfolio name as input by the user.
   * @param userName    the username as input by the user.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private void portfolioValueHelper(StocksModelInterface stocksModel, String jsonName,
          String userName) throws IOException {
    getOngoingStrategyUpToDate(userName, jsonName);

    String date = getDateHelper("VALUE", false);

    JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, jsonName);
    if (stocksDataArray != null) {
      double portfolioValue = stocksModel.getPortfolioValue(stocksDataArray, date);
      if (portfolioValue == -1) {
        stocksView.printMessage(
                "INVALID DATE. THE MARKET IS CLOSED ON WEEKENDS & HOLIDAYS.");
      } else {
        stocksView.printPortfolioValue(portfolioValue, date);
      }
    }
  }


  /**
   * Gets a valid stock name from the user. A stock name is valid if the symbol exists on the
   * AlphaVantage API server.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param operation   an operation; one of BUY, SELL or PORTFOLIO.
   * @param jsonName    portfolio name, as input by the user.
   * @param userName    the username, as input by the user.
   * @return the validated stock name.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private String getStockNameHelper(StocksModelInterface stocksModel, String operation,
          String jsonName, String userName) throws IOException {
    while (true) {
      stocksView.getStockNameForPortfolio();
      String stockName = scan.next();
      if (operation.equals("SELL")) {
        stocksModel.setValidSellSymbols(jsonName, userName);
        if (stocksModel.checkValidSymbolName(stockName)) {
          if (stocksModel.checkValidSellSymbols(stockName)) {
            return stockName;
          } else {
            stocksView.printMessage("YOU CANNOT SELL A STOCK THAT HAS NOT BEEN PURCHASED YET.");
          }
        } else {
          stocksView.printMessage(
                  "INVALID STOCK NAME. COULD NOT FIND THE STOCK NAME ON "
                          + "THE API SERVER.");
        }
      } else {
        if (stocksModel.checkValidSymbolName(stockName)) {
          return stockName;
        } else {
          stocksView.printMessage(
                  "INVALID STOCK NAME. COULD NOT FIND THE STOCK NAME ON "
                          + "THE API SERVER.");
        }
      }
    }
  }


  /**
   * Gets the stock quantity from the user. A quantity is valid if the number of shares is not
   * fractional and the quantity is greater than zero.
   *
   * @return the validated quantity.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private double getQuantityHelper(String operation) throws IOException {
    while (true) {
      double quantity;
      if (operation.equals("BUY") || operation.equals("PORTFOLIO")) {
        while (true) {
          stocksView.getStockQuantity();
          String stringQuantity = scan.next();
          if (!stringQuantity.contains(".")) {
            quantity = Double.parseDouble(stringQuantity);
            break;
          } else {
            stocksView.printMessage(
                    "INVALID INPUT. CANNOT BUY FRACTIONAL SHARES. "
                            + "PLEASE RE-ENTER A VALID INPUT.");
          }
        }
      } else {
        stocksView.getStockQuantity();
        quantity = Double.parseDouble(scan.next());
      }
      if (stocksModelInflexible.checkValidQuantity(quantity)) {
        return quantity;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. NUMBER OF STOCKS MUSE BE >= 1. PLEASE RE-ENTER A "
                        + "VALID NUMBER OF STOCKS.");
      }
    }
  }


  /**
   * Gets the valid date from the user. A date is valid and must be in the yyyy-MM-dd format.
   *
   * @param header the header that is passed to the view. Basically gives the purpose for which the
   *               user is asked to input date.
   * @param flag   false if the date can be a weekend, else true.
   * @return a validated date.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private String getDateHelper(String header, boolean flag) throws IOException {
    String year;
    String month;
    String date;
    while (true) {
      stocksView.getDateHeader(header);
      StringBuilder dateBuilder = new StringBuilder();
      while (true) {
        stocksView.getYear();
        year = scan.next();
        if (stocksModelInflexible.checkValidYear(year)) {
          dateBuilder.append(year);
          break;
        } else {
          stocksView.printMessage("INVALID YEAR. THE YEAR CAN ONLY BETWEEN "
                  + "1999 AND THE CURRENT YEAR. PLEASE RE-ENTER A VALID YEAR.");
        }
      }

      while (true) {
        stocksView.getMonth();
        month = scan.next();
        if (stocksModelInflexible.checkValidMonth(month)) {
          String formattedMonth = String.format("%02d", Integer.parseInt(month));
          dateBuilder.append("-").append(formattedMonth);
          break;
        } else {
          stocksView.printMessage("INVALID MONTH. THE MONTH CAN ONLY BETWEEN "
                  + "1 AND 12. PLEASE RE-ENTER A VALID MONTH.");
        }
      }

      while (true) {
        stocksView.getDate();
        date = scan.next();
        if (stocksModelInflexible.checkValidDate(date, month, year)) {
          String formattedDate = String.format("%02d", Integer.parseInt(date));
          dateBuilder.append("-").append(formattedDate);
          break;
        } else {
          stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A "
                  + "VALID DATE.");
        }
      }

      String validDate = String.valueOf(dateBuilder);
      if (stocksModelInflexible.checkValidBuyDate(validDate) || flag) {
        return validDate;
      } else {
        stocksView.printMessage(
                "INVALID DATE. THE DATE CANNOT BE A WEEKEND AS THE MARKET IS "
                        + "CLOSED AND CANNOT BE LATER THAN TODAY'S DATE. "
                        + "PLEASE RE-ENTER A VALID BUYING DATE.\n");
      }
    }
  }


  /**
   * A cost basis helper. Gets the cost basis for a flexible portfolio.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param jsonName    the portfolio name, as input by the user.
   * @param userName    the username, as input by the user.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private void costBasisHelper(StocksModelInterface stocksModel, String jsonName, String userName)
          throws IOException {
    getOngoingStrategyUpToDate(userName, jsonName);

    String date = getDateHelper("COST BASIS", false);

    JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, jsonName);
    if (stocksDataArray != null) {
      double costBasis = stocksModel.getCostValueBasis(stocksDataArray, date);
      stocksView.printCostBasisValue(costBasis, date);
    }
  }


  /**
   * A portfolio performance helper. Gets the portfolio performance for a flexible performance.
   *
   * @param stocksModel an object of the stocks model interface.
   * @param jsonName    the portfolio user, as input by the user.
   * @param userName    the username, as input by the user.
   * @throws IOException if it fails to append to the logger in the MockModel implementation.
   */
  private void portfolioPerformanceHelper(StocksModelInterface stocksModel, String jsonName,
          String userName) throws IOException {
    getOngoingStrategyUpToDate(userName, jsonName);

    JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, jsonName);
    String begin;
    String finish;

    Date beginDate = new Date();
    Date finishDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    while (true) {
      begin = getDateHelper("START", true);
      finish = getDateHelper("END", true);
      try {
        beginDate = dateFormat.parse(begin);
        finishDate = dateFormat.parse(finish);
      } catch (ParseException e) {
        stocksView.printMessage("FAILED TO PARSE DATE.");
      }
      if (beginDate.compareTo(finishDate) < 0) {
        break;
      } else {
        stocksView.printMessage(
                "THE START DATE CANNOT BE ON THE SAME DATE, OR AFTER THE END DATE. "
                        + "PLEASE RE-ENTER A VALID DATE RANGE.");
      }
    }

    Map<String, Double> performanceMap = stocksModel.portfolioPerformance(stocksDataArray, begin,
            finish);
    if (performanceMap != null) {
      stocksView.printPortfolioPerformance(performanceMap, jsonName, begin, finish,
              performanceMap.get("portfolio_performance_scale"));
    }
  }


  private void dollarCostAveragingHelper(StocksModelInterface stocksModel, String jsonName,
          String userName) throws IOException {
    int numberOfStocks;
    int interval;
    double investmentValue;
    String[] stockNamesArray;
    double[] stockWeightagesArray;
    double[] commissionArray;

    while (true) {
      stocksView.getStocksInEachPortfolio();
      numberOfStocks = Integer.parseInt(scan.next());
      if (stocksModel.checkValidQuantity(numberOfStocks)) {
        break;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. NUMBER OF STOCKS IN A PORTFOLIO MUSE BE >= 1. PLEASE "
                        + "RE-ENTER A VALID NUMBER OF STOCKS.");
      }
    }

    while (true) {
      stocksView.getInvestmentValue();
      investmentValue = Double.parseDouble(scan.next());
      if (stocksModel.checkValidInvestmentValue(investmentValue)) {
        break;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. THE TOTAL INVESTMENT VALUE MUST BE > 0. PLEASE "
                        + "RE-ENTER A VALID INVESTMENT VALUE.");
      }
    }

    while (true) {
      stocksView.getInterval();
      interval = Integer.parseInt(scan.next());
      if (stocksModel.checkInterval(interval)) {
        break;
      } else {
        stocksView.printMessage(
                "INVALID INPUT. THE TIME INTERVAL MUST BE >= 1. PLEASE "
                        + "RE-ENTER A VALID TIME INTERVAL.");
      }
    }

    String startDate = getDateHelper("DOLLAR COST AVERAGING START", true);
    String endDate = "";
    boolean endDateExistsFlag = true;
    while (true) {
      stocksView.printMessage("IS IT ONGOING?(Y/ N): ");
      String endDateExists = scan.next();
      if (endDateExists.equals("N")) {
        endDate = getDateHelper("DOLLAR COST AVERAGING END", true);
        break;
      } else if (endDateExists.equals("Y")) {
        endDateExistsFlag = false;
        break;
      } else {
        stocksView.printMessage("INVALID OPTION. PLEASE RE-ENTER A VALID OPTION.");
      }
    }

    stockNamesArray = new String[numberOfStocks];
    stockWeightagesArray = new double[numberOfStocks];
    commissionArray = new double[numberOfStocks];

    for (int i = 0; i < numberOfStocks; i++) {
      stocksView.stocksHeader(i);
      stockNamesArray[i] = getStockNameHelper(stocksModel, String.valueOf(PortfolioOperations.BUY),
              jsonName, userName);
      stockWeightagesArray[i] = getWeightageHelper(stockWeightagesArray);
      commissionArray[i] = getCommissionHelper();
    }

    if (!stocksModel.investmentStrategy("DOLLAR COST AVERAGING", jsonName, userName,
            startDate, endDate, interval, investmentValue, stockNamesArray,
            stockWeightagesArray, commissionArray, endDateExistsFlag)) {
      stocksView.printMessage("DOLLAR COST AVERAGING FAILED.");
    }
  }


  private double getWeightageHelper(double[] stockWeightagesArray) throws IOException {
    double weightage;
    while (true) {
      stocksView.getStockWeightage();
      String stringWeightage = scan.next();
      weightage = Integer.parseInt(stringWeightage);

      if (stocksModelFlexible.checkValidWeightage(stockWeightagesArray, weightage)) {
        return weightage;
      } else {
        stocksView.printMessage(
                "INVALID WEIGHTAGE. THE INDIVIDUAL WEIGHTAGE MUST BE BETWEEN 0 & 100, AND THE SUM "
                        + "OF WEIGHTAGES OF INDIVIDUAL STOCKS IN A PORTFOLIO MUST NOT EXCEED 100.");
      }
    }
  }

  private void getOngoingStrategyUpToDate(String userName, String jsonName) throws IOException {
    String[] strategyOngoing = stocksModelFlexible.isStrategyOngoing(userName, jsonName);
    if (strategyOngoing.length > 1) {
      String[] stockNames = strategyOngoing[6].substring(1, strategyOngoing[6].length() - 1)
              .split(",");
      String[] stockNamesArray = new String[stockNames.length];
      for (int i = 0; i < stockNamesArray.length; i++) {
        stockNamesArray[i] = stockNames[i].trim();
      }

      String[] stockWeights = strategyOngoing[7].substring(1, strategyOngoing[7].length() - 1)
              .split(",");
      double[] stockWeightsArray = new double[stockWeights.length];
      for (int i = 0; i < stockWeightsArray.length; i++) {
        stockWeightsArray[i] = Double.parseDouble(stockWeights[i]);
      }

      String[] stockCommissions = strategyOngoing[8].substring(1, strategyOngoing[8].length() - 1)
              .split(",");
      double[] stockCommissionsArray = new double[stockCommissions.length];
      for (int i = 0; i < stockCommissionsArray.length; i++) {
        stockCommissionsArray[i] = Double.parseDouble(stockCommissions[i]);
      }

      stocksModelFlexible.investmentStrategy(strategyOngoing[1], strategyOngoing[0],
              strategyOngoing[2], strategyOngoing[4], "", Integer.parseInt(strategyOngoing[3]),
              Double.parseDouble(strategyOngoing[5]), stockNamesArray, stockWeightsArray,
              stockCommissionsArray, false);
    }
  }
}
