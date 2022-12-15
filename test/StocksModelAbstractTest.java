import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import jsonparser.JSONArray;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import stocks.model.StocksModelFlexible;
import stocks.model.StocksModelInflexible;
import stocks.model.StocksModelInterface;


/**
 * Tests the functionality for the StocksModel class which is an implementation of the
 * StocksModelInterface.
 */
public class StocksModelAbstractTest {

  private StocksModelInterface stocksModel;
  private StocksModelInterface stocksModelFlexible;

  @Before
  public void setUp() {
    this.stocksModel = new StocksModelInflexible();
    this.stocksModelFlexible = new StocksModelFlexible();
  }

  @Test
  public void checkValidUserNameTest() {
    try {
      assertEquals(true, this.stocksModel.checkValidUserName("amod2"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidUserNameAlreadyExistsTest() {
    try {
      assertEquals(false, this.stocksModel.checkValidUserName("admin"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void saveLoginCredentialsTest() {
    try {
      assertEquals(true, this.stocksModel.saveLoginCredentials(
              "varsha", "varsha"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void validateLoginCredentialsValidTest() {
    try {
      assertEquals(true, this.stocksModel.validateLoginCredentials(
              "varsha", "varsha"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void validateLoginCredentialsInvalidTest() {
    try {
      assertEquals(false, this.stocksModel.validateLoginCredentials(
              "varsha", "abc"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void setValidSymbolsTest() {
    try {
      assertEquals(true, this.stocksModel.setValidSymbols());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void setCSVSymbolsTest() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidSymbolNameSymbolValidTest() {
    try {
      assertEquals(true, this.stocksModel.setValidSymbols());
      assertEquals(true, this.stocksModel.checkValidSymbolName("GOOG"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidSymbolNameSymbolInvalidTest() {
    try {
      assertEquals(true, this.stocksModel.setValidSymbols());
      assertEquals(false, this.stocksModel.checkValidSymbolName("XYZ"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidFileNameTest() {
    try {
      assertEquals(true, this.stocksModel.checkValidFileName(
              "res/users/yt/sample.json", "yt"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidFileNameForExaminationTest() {
    try {
      assertEquals(true, this.stocksModel.checkValidFileName(
              "res/users/yt/sample.json", "yt"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidQuantityValidTest() {
    try {
      assertEquals(true, this.stocksModel.checkValidQuantity(10));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidQuantityInvalidTest() {
    try {
      assertEquals(false, this.stocksModel.checkValidQuantity(-5));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidQuantityZeroTest() {
    try {
      assertEquals(false, this.stocksModel.checkValidQuantity(0));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidCommission() {
    try {
      assertEquals(true, this.stocksModel.checkValidCommission(1.5));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkInValidCommission() {
    try {
      assertEquals(false, this.stocksModel.checkValidCommission(-1.5));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkZeroCommission() {
    try {
      assertEquals(true, this.stocksModel.checkValidCommission(0));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidYear() {
    try {
      assertEquals(true, this.stocksModel.checkValidYear("2022"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkInvalidYear() {
    try {
      assertEquals(false, this.stocksModel.checkValidYear("2000222"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidMonth() {
    try {
      assertEquals(true, this.stocksModel.checkValidMonth("12"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkInvalidMonth() {
    try {
      assertEquals(false, this.stocksModel.checkValidMonth("14"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidDate() {
    try {
      assertEquals(true, this.stocksModel.checkValidDate("10", "11",
              "2022"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkInvalidDate() {
    try {
      assertEquals(false, this.stocksModel.checkValidDate("87", "12",
              "2022"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidBuyDateValidTest() {
    try {
      assertEquals(true, this.stocksModel.checkValidBuyDate("2022-10-24")); //Monday
      assertEquals(true, this.stocksModel.checkValidBuyDate("2022-10-25")); //Tuesday
      assertEquals(true, this.stocksModel.checkValidBuyDate("2022-10-26")); //Wednesday
      assertEquals(true, this.stocksModel.checkValidBuyDate("2022-10-27")); //Thursday
      assertEquals(true, this.stocksModel.checkValidBuyDate("2022-10-28")); //Friday
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidBuyDateInvalidTest() {
    try {
      assertEquals(false, this.stocksModel.checkValidBuyDate("2022-10-29"));  //Saturday
      assertEquals(false, this.stocksModel.checkValidBuyDate("2022-10-30"));  //Sunday
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void readFromResourceTest() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      BufferedReader reader = new BufferedReader(
              new FileReader("res/users/admin/inflexible/sample.json"));
      List<String> data = this.stocksModel.readFromResource(reader, ";");
      assertEquals(true, data.size() > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void writePortfolioToJsonValidTestInflexible() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      assertEquals(true,
              this.stocksModel.writePortfolioToJson("admin", "sample.json",
                      new String[]{"GOOG"}, new String[]{"2022-10-31"}, new double[]{10},
                      new double[]{}, "PORTFOLIO"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void writePortfolioToJsonValidTestFlexible() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      assertEquals(true,
              this.stocksModel.writePortfolioToJson("admin", "sample.json",
                      new String[]{"GOOG"}, new String[]{"2022-10-31"},
                      new double[]{10}, new double[]{0}, "BUY"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void writePortfolioToJsonInvalidTestInflexible() {
    try {
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(false,
              this.stocksModelFlexible.writePortfolioToJson("amod2",
                      "sample.json", new String[]{"GOOG"}, new String[]{"2022-10-31"},
                      new double[]{10}, new double[]{}, "PORTFOLIO"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void writePortfolioToJsonInvalidTestFlexible() {
    try {
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(false,
              this.stocksModelFlexible.writePortfolioToJson("amod2",
                      "sample.json", new String[]{"GOOG"}, new String[]{"2022-10-31"},
                      new double[]{10}, new double[]{0}, "BUY"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPortfolioFromJsonTestInflexible() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      JSONArray jsonArray = this.stocksModel.getPortfolioFromJson("admin",
              "sample.json");
      assertEquals(1, jsonArray.size());
      assertEquals("[{\"transaction_date\":\"2022-10-31\",\"quantity\":\"10.0\",\""
              + "stock_name\":\"GOOG\",\"price\":\"94.66\",\"operation\":\""
              + "PORTFOLIO\"}]", jsonArray.toJSONString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPortfolioFromJsonTestFlexible() {
    try {
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "sample.json");
      assertEquals(3, jsonArray.size());
      assertEquals(
              "[{\"transaction_date\":\"2022-10-31\",\"quantity\":\"10.0\",\""
                      + "stock_name\":\"GOOG\",\"price\":\"94.66\",\"operation\":\""
                      + "PORTFOLIO\"},{\"transaction_date\":\"2022-11-22\",\"quantity\":\"5.26\",\""
                      + "stock_name\":\"AMZN\",\"price\":\"93.2\",\"commission\":\"10.0\",\""
                      + "operation\":\"BUY\"},{\"transaction_date\":\"2022-11-22\",\"quantity\":\""
                      + "5.03\",\"stock_name\":\"GOOG\",\"price\":\"97.33\",\"commission\":\""
                      + "10.0\",\"operation\":\"BUY\"}]",
              jsonArray.toJSONString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void callAPITest() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      Map<String, Double> datePriceMap = this.stocksModel.callAPI("AMZN");
      assertEquals(true, datePriceMap.size() > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPriceTest() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      assertEquals(Double.valueOf(94.66), this.stocksModel.getPrice("GOOG",
              "2022-10-31", false));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getLastValidPriceTest() {
    try {
      assertEquals(true, this.stocksModel.setCSVSymbols());
      assertEquals(true, this.stocksModel.getLastValidPrice("GOOG") != -1);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPortfolioValueTestInflexible() {
    try {
      JSONArray jsonArray = this.stocksModel.getPortfolioFromJson("admin",
              "sample.json");
      assertEquals(true, this.stocksModel.setCSVSymbols());
      assertEquals(Double.valueOf(946.5999999999999), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-10-31")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPortfolioValueTestFlexible() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "sample.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(1893.1999999999998), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-10-31")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void encryptTest() {
    String text = "sample";
    try {
      assertNotEquals(text, this.stocksModel.encrypt(text));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void decryptTest() {
    String text = "sample";
    try {
      String encrypt = this.stocksModel.encrypt(text);
      assertNotEquals(text, encrypt);
      assertEquals(text, this.stocksModel.decrypt(encrypt));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getCompositionTest() {
    try {
      JSONArray jsonArray = this.stocksModel.getCompositionHelper("2022-11-13",
              "admin", "sample.json");
      assertEquals(true, jsonArray.size() > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void setValidSellSymbolsTest() {
    try {
      assertEquals(false, stocksModelFlexible.checkValidSellSymbols("GOOG"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void validSellQuantityTest() {
    try {
      assertEquals(true, stocksModelFlexible.validSellOrderQuantity("GOOG",
              10, "2022-11-01", "admin", "sample.json"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void invalidSellQuantityTest() {
    try {
      assertEquals(false, stocksModelFlexible.validSellOrderQuantity("GOOG",
              1000, "2022-11-01", "admin", "sample.json"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void costValueBasisTest() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "sample.json");
      assertEquals(0.0, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-11-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void portfolioPerformanceTest() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "sample.json");
      Map<String, Double> performanceMap = stocksModelFlexible.portfolioPerformance(stocksData,
              "2022-01-01", "2022-11-01");
      assertEquals(true, performanceMap.size() > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidInvestmentValueValid() {
    try {
      assertEquals(true, stocksModel.checkValidInvestmentValue(100));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidInvestmentValueInvalid() {
    try {
      assertEquals(false, stocksModel.checkValidInvestmentValue(-100));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidInvestmentValueZero() {
    try {
      assertEquals(false, stocksModel.checkValidInvestmentValue(0));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidIntervalValid() {
    try {
      assertEquals(true, stocksModel.checkInterval(2));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidIntervalInvalid() {
    try {
      assertEquals(false, stocksModel.checkInterval(-2));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidIntervalZero() {
    try {
      assertEquals(false, stocksModel.checkInterval(0));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidWeightageValid() {
    try {
      assertEquals(true, stocksModel.checkValidWeightage(new double[]{20.0, 50.0}, 30));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void checkValidWeightageGreaterThan100() {
    try {
      assertEquals(false, stocksModel.checkValidWeightage(new double[]{20.0, 50.0}, 50));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void investmentStrategyDollarCostAveragingValid() {
    try {
      assertEquals(true, stocksModel.investmentStrategy(
              "DOLLAR COST AVERAGING", "sample.json",
              "admin", "2022-01-01", "2022-11-30",
              15, 1000, new String[]{"AMZN", "GOOG"},
              new double[]{50, 50}, new double[]{10, 10}, true));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void investmentStrategyDollarCostAveragingInvalid() {
    try {
      assertEquals(false, stocksModel.investmentStrategy(
              "GOLDEN CROSS", "sample.json", "admin",
              "2022-01-01", "2022-11-30", 15, 1000,
              new String[]{"AMZN", "GOOG"}, new double[]{50, 50}, new double[]{10, 10},
              true));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void isStrategyOngoingYes() {
    try {
      String[] strategyOngoing = stocksModel.isStrategyOngoing("admin", "1.json");
      assertEquals(true, strategyOngoing.length > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void isStrategyOngoingNo() {
    try {
      String[] strategyOngoing = stocksModel.isStrategyOngoing("admin", "sample.json");
      assertEquals(false, strategyOngoing.length > 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCACostValueBasisTestBeforeStart() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(0.0, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-01-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCACostValueBasisTestAfterEnd() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(7981.8823999999995, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-05-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCACostValueBasisTestInMid1() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(2004.4501999999998, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-02-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCACostValueBasisTestInMid2() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(3981.8842, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-03-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCACostValueBasisTestInMid3() {
    try {
      JSONArray stocksData = stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(5990.258099999999, stocksModelFlexible.getCostValueBasis(stocksData,
              "2022-04-01"), 0);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCAGetPortfolioValueTestBeforeStart() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(0.0), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-01-01")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCAGetPortfolioValueTestAfterEnd() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(7127.803999999998), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-05-01")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCAGetPortfolioValueTestInMid1() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(1948.3232000000003), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-02-01")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCAGetPortfolioValueTestInMid2() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(3720.2016), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-03-01")));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void DCAGetPortfolioValueTestInMid3() {
    try {
      JSONArray jsonArray = this.stocksModelFlexible.getPortfolioFromJson("admin",
              "dca.json");
      assertEquals(true, this.stocksModelFlexible.setCSVSymbols());
      assertEquals(Double.valueOf(6103.2257), Double.valueOf(
              this.stocksModel.getPortfolioValue(jsonArray, "2022-04-01")));
    } catch (IOException e) {
      fail();
    }
  }
}