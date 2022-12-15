package stocks.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jsonparser.JSONArray;
import jsonparser.JSONObject;
import stocks.model.StocksModelInterface;
import stocks.view.StocksViewGUIInterface;


/**
 * The StocksControllerGUI class implements the Features interface and the
 * StocksControllerInterface. Thus, it implements all the functionalities in the Features and the
 * StocksControllerInterface. This implementation mediates between our model and the GUI, and calls
 * methods from both, at appropriate times.
 */
public class StocksControllerGUI implements Feature, StocksControllerInterface {

  private final StocksModelInterface stocksModel;
  private final StocksViewGUIInterface stocksView;
  private List<String> stockNamesList;
  private List<String> stockDatesList;
  private List<Double> stockQuantitiesList;
  private List<Double> stockCommissionsList;
  private List<Double> stockWeightagesList;
  private String portfolioName;
  private String userName;
  private double investmentValue;
  private int interval;
  private boolean isOngoingFlag;

  /**
   * A constructor of the StocksControllerGUI class. This constructor is used to initialize the
   * objects of the stocksView and the modelInflexiblePortfolio interfaces.
   *
   * @param stocksView  An object of the StocksViewGUIInterface. Gives access to methods in the
   *                    view.
   * @param stocksModel An object of the StocksModelInterface. Gives access to methods in the
   *                    flexible portfolio model.
   */
  public StocksControllerGUI(StocksViewGUIInterface stocksView, StocksModelInterface stocksModel) {
    this.stocksView = stocksView;
    this.stocksModel = stocksModel;
  }


  /**
   * Acts as a helper method, that gets an ongoing investment strategy up-to-date, i.e., completes
   * transactions in the portfolio as specified in the strategy upto the current date.
   *
   * @param userName the username as input by the user.
   * @param jsonName the portfolio or jsonname as input by the user.
   */
  private void getOngoingStrategyUpToDate(String userName, String jsonName) {
    try {
      String[] strategyOngoing = stocksModel.isStrategyOngoing(userName, jsonName);
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

        stocksModel.investmentStrategy(strategyOngoing[1], strategyOngoing[0],
                strategyOngoing[2], strategyOngoing[4], "", Integer.parseInt(strategyOngoing[3]),
                Double.parseDouble(strategyOngoing[5]), stockNamesArray, stockWeightsArray,
                stockCommissionsArray, false);
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Facilitates the login functionality in our program.
   *
   * @param userName the username as input by the user.
   * @param password the password as input by the user.
   */
  @Override
  public void login(String userName, String password) {
    try {
      if (stocksModel.validateLoginCredentials(userName, password)) {
        this.userName = userName;
        stocksView.mainMenu();
        stocksView.mainMenuFeatures(this);
      } else {
        stocksView.printMessage(
                "INVALID USERNAME OR PASSWORD. PLEASE RE-ENTER A VALID USERNAME AND PASSWORD.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Facilitates the sign-up functionality in our program.
   *
   * @param userName the username as input by the user.
   * @param password the password as input by the user.
   */
  @Override
  public void signUp(String userName, String password) {
    try {
      if (stocksModel.checkValidUserName(userName)) {
        this.userName = userName;
        stocksModel.createUserDirectories(userName);
        stocksModel.saveLoginCredentials(userName, password);
        stocksView.mainMenu();
        stocksView.mainMenuFeatures(this);
      } else {
        stocksView.printMessage(
                "USERNAME ALREADY EXISTS. PLEASE LOGIN OR ENTER A UNIQUE USERNAME.");

      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Facilitates going back to the main menu functionality.
   */
  @Override
  public void backToMainMenu() {
    stocksView.mainMenu();
    stocksView.mainMenuFeatures(this);
  }


  /**
   * The buy stocks or shares functionality in our program.
   */
  @Override
  public void buy() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.portfolioNameMenu();
    stocksView.portfolioNameMenuFeatures(this, "BUY");
  }


  /**
   * The sell stocks or shares functionality in our program.
   */
  @Override
  public void sell() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.portfolioNameMenu();
    stocksView.portfolioNameMenuFeatures(this, "SELL");
  }


  /**
   * Takes a portfolio name as the input and validates it.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param operation     the operation that is being performed (buy/ sell).
   */
  @Override
  public void validPortfolioParameters(String portfolioName, String operation) {
    this.portfolioName = portfolioName;
    stockNamesList = new ArrayList<>();
    stockDatesList = new ArrayList<>();
    stockQuantitiesList = new ArrayList<>();
    stockCommissionsList = new ArrayList<>();
    boolean flag = true;
    getOngoingStrategyUpToDate(userName, portfolioName);

    if (operation.equals("SELL")) {
      try {
        if (!stocksModel.checkValidFileNameForExamination(portfolioName, userName)) {
          stocksView.printMessage(
                  "INVALID PORTFOLIO NAME. THE PORTFOLIO DOES NOT EXIST. PLEASE RE-ENTER A VALID "
                          + "PORTFOLIO NAME.");
          flag = false;

        }
      } catch (IOException e) {
        stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
      }
    }
    if (flag) {
      stocksView.getStockData(false);
      stocksView.getStockDataFeatures(this, false, operation);
    }
  }


  /**
   * Gets the stock data as input by the user, and sets it for further use.
   *
   * @param operation  the operation that is being performed (buy/ sell).
   * @param stockName  the stock name as input by the user.
   * @param quantity   the quantity as input by the user.
   * @param commission the commission as input by the user.
   * @param year       the year as input by the user.
   * @param month      the month as input by the user.
   * @param date       the date as input by the user.
   */
  @Override
  public void setStockData(String operation, String stockName, double quantity,
          double commission, String year, String month, String date) {
    getOngoingStrategyUpToDate(userName, portfolioName);
    boolean flag = true;
    try {
      if (stocksModel.checkValidSymbolName(stockName)) {
        if (stocksModel.checkValidQuantity(quantity)) {
          if (stocksModel.checkValidYear(year) && stocksModel.checkValidMonth(month)
                  && stocksModel.checkValidDate(date, month, year)) {
            String transactionDate = String.format("%d-%02d-%02d", Integer.parseInt(year),
                    Integer.parseInt(month), Integer.parseInt(date));
            if (stocksModel.checkValidBuyDate(transactionDate)) {
              if (operation.equals("SELL")) {
                if (stocksModel.validSellOrderQuantity(stockName, quantity, transactionDate,
                        userName, portfolioName)) {
                  stockNamesList.add(stockName);
                  stockDatesList.add(transactionDate);
                  stockQuantitiesList.add(quantity);
                  stockCommissionsList.add(commission);
                } else {
                  flag = false;
                  stocksView.printMessage(
                          "CANNOT SELL A STOCK THAT YOU HAVEN'T BOUGHT "
                                  + "OR A QUANTITY MORE THAN WHAT YOU'VE BOUGHT.");
                }
              } else {
                if (quantity % 1 == 0) {
                  stockNamesList.add(stockName);
                  stockDatesList.add(transactionDate);
                  stockQuantitiesList.add(quantity);
                  stockCommissionsList.add(commission);
                } else {
                  flag = false;
                  stocksView.printMessage(
                          "INVALID INPUT. CANNOT BUY FRACTIONAL SHARES. "
                                  + "PLEASE RE-ENTER A VALID INPUT.");
                }
              }
            } else {
              flag = false;
              stocksView.printMessage(
                      "DATE CANNOT BE A WEEKEND. PLEASE RE-ENTER A VALID DATE.");
            }
          } else {
            flag = false;
            stocksView.printMessage(
                    "INVALID DATE. PLEASE RE-ENTER A VALID DATE.");
          }
        } else {
          flag = false;
          stocksView.printMessage(
                  "INVALID INPUT. NUMBER OF STOCKS MUSE BE >= 1. PLEASE RE-ENTER A "
                          + "VALID NUMBER OF STOCKS.");
        }
      } else {
        flag = false;
        stocksView.printMessage(
                "INVALID STOCK NAME. COULD NOT FIND THE STOCK NAME ON "
                        + "THE API SERVER.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
    if (flag) {
      stocksView.getStockData(true);
      stocksView.getStockDataFeatures(this, true, operation);
    }
  }


  /**
   * Writes the data as input by the user to a portfolio (JSON).
   *
   * @param operation the operation that is being performed (buy/ sell).
   */
  @Override
  public void writeToPortfolio(String operation) {
    String[] stockNamesArray = new String[stockNamesList.size()];
    String[] stockDatesArray = new String[stockNamesList.size()];
    double[] stockQuantitiesArray = new double[stockNamesList.size()];
    double[] stockCommissionsArray = new double[stockNamesList.size()];

    for (int i = 0; i < stockNamesList.size(); i++) {
      stockNamesArray[i] = stockNamesList.get(i);
      stockDatesArray[i] = stockDatesList.get(i);
      stockQuantitiesArray[i] = stockQuantitiesList.get(i);
      stockCommissionsArray[i] = stockCommissionsList.get(i);
    }

    try {
      if (stocksModel.writePortfolioToJson(userName, portfolioName, stockNamesArray,
              stockDatesArray,
              stockQuantitiesArray, stockCommissionsArray, operation)) {
        stocksView.mainMenu();
        stocksView.mainMenuFeatures(this);
      } else {
        stocksView.printMessage("CREATING A NEW PORTFOLIO AND "
                + "WRITING IT TO JSON FAILED.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * The dollar cost investment strategy functionality.
   */
  @Override
  public void dollarCostAveraging() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.dollarCostAveraging();
    stocksView.dollarCostAveragingFeatures(this);
  }


  /**
   * Gets the data for the dollar cost investment strategy functionality.
   *
   * @param portfolioName   the name of the portfolio.
   * @param investmentValue the value to be invested every time.
   * @param interval        days between successive investments.
   * @param isOngoingFlag   true if the strategy is ongoing, false otherwise.
   */
  @Override
  public void dollarCostAveragingGetDataOne(String portfolioName, double investmentValue,
          int interval, boolean isOngoingFlag) {
    this.portfolioName = portfolioName;
    this.isOngoingFlag = isOngoingFlag;
    stockNamesList = new ArrayList<>();
    stockDatesList = new ArrayList<>();
    stockCommissionsList = new ArrayList<>();
    stockWeightagesList = new ArrayList<>();
    getOngoingStrategyUpToDate(userName, portfolioName);

    try {
      if (stocksModel.checkValidInvestmentValue(investmentValue)) {
        this.investmentValue = investmentValue;
        if (stocksModel.checkInterval(interval)) {
          this.interval = interval;
          stocksView.dollarCostAveragingGetDataOne(false);
          stocksView.dollarCostAveragingGetDataOneFeatures(this, false);
        } else {
          stocksView.printMessage(
                  "INVALID INPUT. THE TIME INTERVAL MUST BE >= 1. PLEASE "
                          + "RE-ENTER A VALID TIME INTERVAL.");
        }
      } else {
        stocksView.printMessage(
                "INVALID INPUT. THE TOTAL INVESTMENT VALUE MUST BE > 0. PLEASE "
                        + "RE-ENTER A VALID INVESTMENT VALUE.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Sets the data as input by the user for the dollar cost investment strategy functionality, for
   * further use.
   *
   * @param stockName  the stock name as input by the user.
   * @param commission the commission as input by the user.
   * @param weightage  the weightage as input by the user.
   */
  @Override
  public void dollarCostAveragingSetDataOne(String stockName, double commission, double weightage) {
    double[] weightageArray = new double[stockWeightagesList.size()];
    for (int i = 0; i < stockWeightagesList.size(); i++) {
      weightageArray[i] = stockWeightagesList.get(i);
    }

    try {
      if (stocksModel.checkValidSymbolName(stockName)) {
        if (stocksModel.checkValidCommission(commission)) {
          if (stocksModel.checkValidWeightage(weightageArray, weightage)) {
            stockNamesList.add(stockName);
            stockCommissionsList.add(commission);
            stockWeightagesList.add(weightage);
            stocksView.dollarCostAveragingGetDataOne(true);
            stocksView.dollarCostAveragingGetDataOneFeatures(this, true);
          } else {
            stocksView.printMessage(
                    "INVALID WEIGHTAGE. THE INDIVIDUAL WEIGHTAGE MUST BE BETWEEN "
                            + "0 & 100, AND THE SUM OF WEIGHTAGES OF INDIVIDUAL STOCKS "
                            + "IN A PORTFOLIO MUST NOT EXCEED 100.");
          }
        } else {
          stocksView.printMessage(
                  "INVALID INPUT. COMMISSION MUSE BE > 0. PLEASE RE-ENTER A "
                          + "VALID COMMISSION.");
        }
      } else {
        stocksView.printMessage(
                "INVALID STOCK NAME. COULD NOT FIND THE STOCK NAME ON "
                        + "THE API SERVER.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Acts as a helper method for dollarCostAveragingGetDataOne.
   */
  @Override
  public void dollarCostAveragingGetDataTwo() {
    stocksView.dollarCostAveragingGetDataTwo(isOngoingFlag);
    stocksView.dollarCostAveragingGetDataTwoFeatures(this, isOngoingFlag);
  }


  /**
   * Sets the data as input by the user for the dollar cost investment strategy functionality, for
   * further use. Acts as a helper method to dollarCostAveragingSetDataOne.
   *
   * @param year1  the start year.
   * @param month1 the start month.
   * @param date1  the start date.
   * @param year2  the end year.
   * @param month2 the end month.
   * @param date2  the end date.
   */
  @Override
  public void dollarCostAveragingSetDataTwo(String year1, String month1, String date1, String year2,
          String month2, String date2) {
    boolean flag = true;
    String[] stockNamesArray = new String[stockNamesList.size()];
    double[] stockWeightagesArray = new double[stockNamesList.size()];
    double[] commissionArray = new double[stockNamesList.size()];

    for (int i = 0; i < stockNamesList.size(); i++) {
      stockNamesArray[i] = stockNamesList.get(i);
      stockWeightagesArray[i] = stockWeightagesList.get(i);
      commissionArray[i] = stockCommissionsList.get(i);
    }

    try {
      if (stocksModel.checkValidYear(year1) && stocksModel.checkValidMonth(month1)
              && stocksModel.checkValidDate(date1, month1, year1)) {
        String startDate = String.format("%d-%02d-%02d", Integer.parseInt(year1),
                Integer.parseInt(month1), Integer.parseInt(date1));
        String endDate = "";
        if (!isOngoingFlag) {
          if (stocksModel.checkValidYear(year2) && stocksModel.checkValidMonth(month2)
                  && stocksModel.checkValidDate(date2, month2, year2)) {
            endDate = String.format("%d-%02d-%02d", Integer.parseInt(year2),
                    Integer.parseInt(month2), Integer.parseInt(date2));
          } else {
            flag = false;
            stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A "
                    + "VALID DATE.");
          }
        }
        if (stocksModel.investmentStrategy("DOLLAR COST AVERAGING", portfolioName, userName,
                startDate, endDate, interval, investmentValue, stockNamesArray,
                stockWeightagesArray, commissionArray, !isOngoingFlag)) {
          stocksView.mainMenu();
          stocksView.mainMenuFeatures(this);
        } else {
          flag = false;
          stocksView.printMessage("DOLLAR COST AVERAGING FAILED.");
        }
      } else {
        flag = false;
        stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A "
                + "VALID DATE.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }

    if (flag) {
      stocksView.mainMenu();
      stocksView.mainMenuFeatures(this);
    }
  }


  /**
   * Implements the examine composition of an existing portfolio functionality.
   */
  @Override
  public void examineComposition() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.examineComposition();
    stocksView.examineCompositionFeatures(this);
  }


  /**
   * Gets the composition of a portfolio.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param year          the year as input by the user.
   * @param month         the month as input by the user.
   * @param date          the date as input by the user.
   */
  @Override
  public void getComposition(String portfolioName, String year, String month, String date) {
    getOngoingStrategyUpToDate(userName, portfolioName);
    try {
      if (stocksModel.checkValidFileNameForExamination(portfolioName, userName)) {

        if (stocksModel.checkValidYear(year) && stocksModel.checkValidMonth(month)
                && stocksModel.checkValidDate(date, month, year)) {

          String examineDate = String.format("%d-%02d-%02d", Integer.parseInt(year),
                  Integer.parseInt(month), Integer.parseInt(date));

          JSONArray stocksData = stocksModel.getCompositionHelper(examineDate, userName,
                  portfolioName);
          if (stocksData != null && stocksData.size() != 0) {
            Object[][] rows = new Object[stocksData.size()][8];
            Object[] cols = {"STOCK NAME", "QUANTITY", "FIRST BUY DATE", "LAST TRANSACTION DATE",
                "LAST BUY PRICE", "AVG BUY PRICE", "LAST TRADED PRICE", "COMMISSION"};
            for (int i = 0; i < stocksData.size(); i++) {
              JSONObject temp = (JSONObject) stocksData.get(i);
              rows[i][0] = temp.get("stock_name");
              rows[i][1] = Double.parseDouble((String) temp.get("quantity"));
              rows[i][2] = temp.get("transaction_date");
              rows[i][3] = temp.get("last_transaction_date");
              rows[i][4] = temp.get("price");
              rows[i][5] = temp.get("avg_price");
              rows[i][6] = String.valueOf(
                      stocksModel.getLastValidPrice((String) temp.get("stock_name")));
              if (rows[i][6].equals("-1")) {
                rows[i][6] = "NA";
              }
              rows[i][7] = temp.get("commission");
            }

            stocksView.displayComposition(rows, cols);
            stocksView.displayCompositionFeatures(this);

          } else {
            stocksView.printMessage(
                    "NO DATA AVAILABLE IN THE PORTFOLIO ON OR BEFORE DATE: " + examineDate + ".");
          }
        } else {
          stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A VALID DATE.");
        }
      } else {
        stocksView.printMessage(
                "INVALID PORTFOLIO NAME. THE PORTFOLIO DOES NOT EXIST. PLEASE RE-ENTER A VALID "
                        + "PORTFOLIO NAME.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Implements the cost value basis functionality.
   */
  @Override
  public void costValueBasis() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.costValueBasis();
    stocksView.costValueBasisFeatures(this);
  }


  /**
   * Gets the cost value basis of a portfolio.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param year          the year as input by the user.
   * @param month         the month as input by the user.
   * @param date          the date as input by the user.
   */
  @Override
  public void getCostValueBasis(String portfolioName, String year, String month, String date) {
    getOngoingStrategyUpToDate(userName, portfolioName);
    try {
      if (stocksModel.checkValidFileNameForExamination(portfolioName, userName)) {

        if (stocksModel.checkValidYear(year) && stocksModel.checkValidMonth(month)
                && stocksModel.checkValidDate(date, month, year)) {
          String costValueDate = String.format("%d-%02d-%02d", Integer.parseInt(year),
                  Integer.parseInt(month), Integer.parseInt(date));
          if (stocksModel.checkValidBuyDate(costValueDate)) {

            JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, portfolioName);
            if (stocksDataArray != null) {
              double costBasis = stocksModel.getCostValueBasis(stocksDataArray, costValueDate);
              stocksView.displayCostValueBasis(costBasis, portfolioName, costValueDate);
              stocksView.displayCostValueBasisFeatures(this);
            }

          } else {
            stocksView.printMessage(
                    "BUY DATE CANNOT BE A WEEKEND. PLEASE RE-ENTER A VALID DATE.");
          }
        } else {
          stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A VALID DATE.");
        }
      } else {
        stocksView.printMessage(
                "INVALID PORTFOLIO NAME. THE PORTFOLIO DOES NOT EXIST. PLEASE RE-ENTER A VALID "
                        + "PORTFOLIO NAME.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Implements the total portfolio value functionality.
   */
  @Override
  public void totalPortfolioValue() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.totalPortfolioValue();
    stocksView.totalPortfolioValueFeatures(this);
  }


  /**
   * Gets the total value of a portfolio.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param year          the year as input by the user.
   * @param month         the month as input by the user.
   * @param date          the date as input by the user.
   */
  @Override
  public void getTotalPortfolioValue(String portfolioName, String year, String month, String date) {
    getOngoingStrategyUpToDate(userName, portfolioName);
    try {
      if (stocksModel.checkValidFileNameForExamination(portfolioName, userName)) {

        if (stocksModel.checkValidYear(year) && stocksModel.checkValidMonth(month)
                && stocksModel.checkValidDate(date, month, year)) {
          String totalValueDate = String.format("%d-%02d-%02d", Integer.parseInt(year),
                  Integer.parseInt(month), Integer.parseInt(date));
          if (stocksModel.checkValidBuyDate(totalValueDate)) {

            JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, portfolioName);
            if (stocksDataArray != null) {
              double portfolioValue = stocksModel.getPortfolioValue(stocksDataArray,
                      totalValueDate);
              if (portfolioValue == -1) {
                stocksView.printMessage(
                        "INVALID DATE. THE MARKET IS CLOSED ON WEEKENDS & HOLIDAYS.");
              } else {
                stocksView.displayTotalPortfolioValue(portfolioValue, portfolioName,
                        totalValueDate);
                stocksView.displayTotalPortfolioValueFeatures(this);
              }
            }

          } else {
            stocksView.printMessage(
                    "BUY DATE CANNOT BE A WEEKEND. PLEASE RE-ENTER A VALID DATE.");
          }
        } else {
          stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A VALID DATE.");
        }
      } else {
        stocksView.printMessage(
                "INVALID PORTFOLIO NAME. THE PORTFOLIO DOES NOT EXIST. PLEASE RE-ENTER A VALID "
                        + "PORTFOLIO NAME.");
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }


  /**
   * Implements the portfolio performance functionality.
   */
  @Override
  public void portfolioPerformance() {
    getOngoingStrategyUpToDate(userName, portfolioName);
    stocksView.portfolioPerformance();
    stocksView.portfolioPerformanceFeatures(this);
  }


  /**
   * Implements the get portfolio performance functionality. Prints the portfolio performance
   * graph.
   *
   * @param portfolioName the portfolio name as input by the user.
   * @param year2         the end year as input by the user.
   * @param month2        the end month as input by the user.
   * @param date2         the end date as input by the user.
   * @param year1         the start year as input by the user.
   * @param month1        the start month as input by the user.
   * @param date1         the start date as input by the user.
   * @param chartType         either line chart or a bar graph.
   */
  @Override
  public void getPortfolioPerformance(String portfolioName, String year2, String month2,
          String date2, String year1, String month1, String date1, String chartType) {
    getOngoingStrategyUpToDate(userName, portfolioName);
    try {
      if (stocksModel.checkValidFileNameForExamination(portfolioName, userName)) {
        if (stocksModel.checkValidDate(date1, month1, year1)
                && stocksModel.checkValidDate(date2, month2, year2)) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String begin = String.format("%d-%02d-%02d", Integer.parseInt(year1),
                  Integer.parseInt(month1), Integer.parseInt(date1));
          String finish = String.format("%d-%02d-%02d", Integer.parseInt(year2),
                  Integer.parseInt(month2), Integer.parseInt(date2));
          Date beginDate = new Date();
          Date finishDate = new Date();

          try {
            beginDate = dateFormat.parse(begin);
            finishDate = dateFormat.parse(finish);
          } catch (ParseException e) {
            stocksView.printMessage("FAILED TO PARSE DATE.");
          }

          if (beginDate.compareTo(finishDate) < 0) {
            JSONArray stocksDataArray = stocksModel.getPortfolioFromJson(userName, portfolioName);
            Map<String, Double> performanceMap = stocksModel.portfolioPerformance(stocksDataArray,
                    begin,
                    finish);
            if (performanceMap != null) {
              if (chartType.equals("BAR")) {
                stocksView.displayPortfolioPerformance(performanceMap, portfolioName, begin, finish,
                        performanceMap.get("portfolio_performance_scale"));
                stocksView.displayPortfolioPerformanceFeatures(this);
              } else if (chartType.equals("LINE")) {
                stocksView.displayPortfolioLineChart(performanceMap, portfolioName, begin, finish,
                        performanceMap.get("portfolio_performance_scale"));
                stocksView.displayPortfolioLineChartFeatures(this);
              }
            }
          } else {
            stocksView.printMessage(
                    "THE START DATE CANNOT BE ON THE SAME DATE, OR AFTER THE END DATE. "
                            + "PLEASE RE-ENTER A VALID DATE RANGE.");
          }
        } else {
          stocksView.printMessage("INVALID DATE. PLEASE RE-ENTER A VALID DATE.");
        }
      }
    } catch (IOException e) {
      stocksView.printMessage("RUNTIME ERROR ENCOUNTERED");
    }
  }



  /**
   * Facilitates creating a line chart.
   */
  @Override
  public void lineChart() {
    stocksView.lineChart();
    stocksView.lineChartFeatures(this);
  }


  /**
   * Facilitates exiting our program.
   */
  @Override
  public void exit() {
    System.exit(0);
  }


  /**
   * The go method handles the complete flow and execution of the program. It calls the various
   * methods in the view and the model depending on as and when they are required.
   *
   * @throws IOException if it fails to append the logger, that keeps track of the activities.
   */
  @Override
  public void run() throws IOException {
    stocksModel.setValidSymbols();
    stocksModel.setCSVSymbols();

    stocksView.loginMenu();
    stocksView.loginMenuFeatures(this);
  }
}
