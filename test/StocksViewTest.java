import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import stocks.view.StocksView;
import stocks.view.StocksViewInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests the functionality for the StocksView class which is an implementation of the
 * StocksViewInterface.
 */
public class StocksViewTest {
  private StocksViewInterface stocksView;
  private StringBuilder log;

  @Before
  public void setUp() {
    this.log = new StringBuilder();
    this.stocksView = new StocksView(this.log);
  }

  @Test
  public void loginMenuTest() {
    try {
      stocksView.loginMenu();
      String expectedString = "\n"
              + "\n"
              + "---------------------\n"
              + "  LOGIN OR REGISTER  \n"
              + "---------------------\n"
              + "1. LOGIN\n"
              + "2. SIGN UP\n"
              + "3. EXIT\n"
              + "ENTER YOUR CHOICE: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getUserNameTest() {
    try {
      stocksView.getUserName();
      String expectedString = "\n"
              + "ENTER THE USERNAME: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getPasswordTest() {
    try {
      stocksView.getPassword();
      String expectedString = "\n"
              + "ENTER THE PASSWORD: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getInflexibleMenuTest() {
    try {
      stocksView.getInflexibleMenu();
      String expectedString = "\n"
              + "\n"
              + "-----------------------------------------------\n"
              + "  STOCK MARKET – CREATE A PORTFOLIO & EXAMINE  \n"
              + "-----------------------------------------------\n"
              + "1. CREATE A NEW PORTFOLIO\n"
              + "2. EXAMINE COMPOSITION OF AN EXISTING PORTFOLIO\n"
              + "3. GET TOTAL VALUE OF A PORTFOLIO ON A DATE\n"
              + "4. EXIT\n"
              + "ENTER YOUR CHOICE: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getFlexibleMenuTest() {
    try {
      stocksView.getFlexibleMenu();
      String expectedString = "\n"
              + "\n"
              + "------------------------------------\n"
              + "  STOCK MARKET – BUY & SELL SHARES  \n"
              + "------------------------------------\n"
              + "1. BUY SHARES\n"
              + "2. SELL SHARES\n"
              + "3. CREATE A DOLLAR COST AVERAGING PORTFOLIO\n"
              + "4. EXAMINE COMPOSITION OF AN EXISTING PORTFOLIO\n"
              + "5. COST VALUE BASIS\n"
              + "6. GET TOTAL VALUE OF A PORTFOLIO ON A DATE\n"
              + "7. PORTFOLIO PERFORMANCE OVER TIME\n"
              + "8. EXIT\n"
              + "ENTER YOUR CHOICE: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getStocksInEachPortfolioTest() {
    try {
      stocksView.getStocksInEachPortfolio();
      String expectedString = "\n"
              + "NUMBER OF STOCKS: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getStockNameForPortfolioTest() {
    try {
      stocksView.getStockNameForPortfolio();
      String expectedString = "\n"
              + "STOCK NAME: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getBuyingDateTest() {
    try {
      stocksView.getDateHeader("BUYING");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE BUYING DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getStockQuantityTest() {
    try {
      stocksView.getStockQuantity();
      String expectedString = "\n"
              + "QUANTITY: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getCommissionTest() {
    try {
      stocksView.getCommission();
      String expectedString = "\n"
              + "COMMISSION: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void stocksHeaderTest() {
    try {
      stocksView.stocksHeader(0);
      String expectedString = "\n"
              + "-----------\n"
              + "FOR STOCK 1\n"
              + "-----------";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getJSONNameTest() {
    try {
      stocksView.getJSONName();
      String expectedString = "\n"
              + "NAME OF THE PORTFOLIO: ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void portfolioHeaderTest() {
    try {
      stocksView.portfolioHeader(false);
      String expectedString = "\n"
              + "-------------------------------------------------------------------------------"
              + "----------------------------------------------------------------------------\n"
              + "STOCK NAME       QUANTITY        FIRST BUY DATE       LAST TRANSACTION DATE      "
              + "LAST BUY PRICE    AVG BUY PRICE        LAST TRADED PRICE\n"
              + "--------------------------------------------------------------------------------"
              + "---------------------------------------------------------------------------";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void printStocksDataTest() {
    try {
      stocksView.printStocksData(new String[]{"GOOG"}, new String[]{"2022-10-31"}, new double[]{10},
              new String[]{"2022-10-31"}, new String[]{"94.66"},  new String[]{"90.5"},
              new String[]{}, new String[]{"90.50"}, 1, false);
      String expectedString = "\n"
              + "GOOG                10.0             2022-10-31            2022-10-31         "
              + "         94.66                90.5               90.50\n"
              + "------------------------------------------------------------------------------"
              + "-----------------------------------------------------------------------------";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateHeaderBuyingTest() {
    try {
      stocksView.getDateHeader("BUY");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE BUY DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateHeaderSellingTest() {
    try {
      stocksView.getDateHeader("SELL");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE SELL DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateCostBasisTest() {
    try {
      stocksView.getDateHeader("COST BASIS");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE COST BASIS DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateStartTest() {
    try {
      stocksView.getDateHeader("START");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE START DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateEndTest() {
    try {
      stocksView.getDateHeader("END");
      String expectedString = "\n"
              + "---------------------\n"
              + "ENTER THE END DATE\n"
              + "---------------------\n";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getYearTest() {
    try {
      stocksView.getYear();
      String expectedString = "YEAR (yyyy): ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getMonthTest() {
    try {
      stocksView.getMonth();
      String expectedString = "MONTH (mm): ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void getDateTest() {
    try {
      stocksView.getDate();
      String expectedString = "DATE (dd): ";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void printMessageTest() {
    try {
      stocksView.printMessage("Printing error on the console in view.");
      String expectedString = "Printing error on the console in view.";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void printPortfolioValueTest() {
    try {
      stocksView.printPortfolioValue(2135.32, "2022-10-31");
      String expectedString = "\n"
              + "TOTAL PORTFOLIO VALUE ON 2022-10-31: $2135.32";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void printCostBasisValueTest() {
    try {
      stocksView.printCostBasisValue(2135.32, "2022-10-31");
      String expectedString = "\n"
              + "COST VALUE BASIS ON 2022-10-31: $2135.32";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void printPortfolioPerformanceTest() {
    try {
      Map<String, Double> performanceMap = new HashMap<>();
      performanceMap.put("May 2022", 0.0);
      performanceMap.put("Aug 2022", 500.12);
      performanceMap.put("Sept 2022", 1222.23);
      performanceMap.put("Oct 2022", 0.0);
      performanceMap.put("Nov 2022", 2135.32);
      stocksView.printPortfolioPerformance(performanceMap,
              "sample.json", "2022-05-01", "2022-11-12", 250);
      String expectedString = "\n"
              + "PERFORMANCE OF PORTFOLIO sample.json FROM 2022-05-01 TO 2022-11-12\n"
              + "\n"
              + "May 2022: \n"
              + "Sept 2022: *****\n"
              + "Aug 2022: **\n"
              + "Oct 2022: \n"
              + "Nov 2022: *********\n"
              + "\n"
              + "Scale: * = 250.0";
      assertEquals(expectedString, log.toString());
    } catch (IOException e) {
      fail();
    }
  }
}