import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import stocks.controller.StocksController;
import stocks.controller.StocksControllerInterface;
import stocks.model.StocksModelInterface;
import stocks.view.StocksView;
import stocks.view.StocksViewInterface;


/**
 * Tests the functionality for the StocksController class which is an implementation of the
 * StocksControllerInterface. The controller is tested by using the MockModel implementation of the
 * StocksModelInterface.
 */
public class StocksControllerTest {
  private StocksControllerInterface stocksController;
  private StocksViewInterface stocksView;
  private StocksModelInterface mockModel;
  private StringBuilder log;

  @Before
  public void setUp() {
    this.log = new StringBuilder();
    this.stocksView = new StocksView(System.out);

    this.mockModel = new MockModel(this.log);
    this.stocksController = new StocksController(this.stocksView, this.mockModel, this.mockModel,
            new InputStreamReader(System.in), System.out);
  }

  @Test
  public void signUpTest() {
    StringReader in = new StringReader("2 admin admin 3");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = admin\n"
            + "\n"
            + "createUserDirectory called with input username = admin\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = admin and password = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginTest() {
    StringReader in = new StringReader("1 admin admin 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginAndCreateNewInflexiblePortfolioTest() {
    StringReader in = new StringReader(
            "1 admin admin 1 1 test.json 2 AMZN 2022 10 31 10 GOOG 2022 10 28 100 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileName called with input jsonName = test.json and userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = test.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 10.0\n"
            + "\n"
            + "checkValidSymbolName called with input GOOG\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = test.json\n"
            + "\n"
            + "StockNames = [AMZN, GOOG]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28]\n"
            + "\n"
            + "Quantities = [10.0, 100.0]\n"
            + "\n"
            + "Commissions = [0.0, 0.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginAndExamineInflexiblePortfolioTest() {
    StringReader in = new StringReader("1 admin admin 1 2 test.json 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = test.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = test.json\n"
            + "\n"
            + "getCompositionHelper called with input jsonName = test.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginAndGetInflexiblePortfolioValueTest() {
    StringReader in = new StringReader("1 admin admin 1 3 test.json 2022 10 31 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = test.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = test.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = test.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginCreateAnInflexiblePortfolioExamineThatPortfolioAndGetItsValueTest() {
    StringReader in = new StringReader(
            "1 admin admin 1 1 sample.json 3 AMZN 2022 10 31 10 AAPL 2022 10 28 100 "
                    + "TSLA 2022 10 27 100 2 sample.json 3 sample.json 2022 10 31 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileName called with input jsonName = sample.json and userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 3.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 10.0\n"
            + "\n"
            + "checkValidSymbolName called with input AAPL\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 27, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-27\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, AAPL, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28, 2022-10-27]\n"
            + "\n"
            + "Quantities = [10.0, 100.0, 100.0]\n"
            + "\n"
            + "Commissions = [0.0, 0.0, 0.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "getCompositionHelper called with input jsonName = sample.json, userName = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void signUpCreateAnInflexiblePortfolioExamineThatPortfolioAndGetItsValueTest() {
    StringReader in = new StringReader(
            "2 user user 1 1 sample.json 3 AMZN 2022 10 31 10 AAPL 2022 10 28 100 "
                    + "TSLA 2022 10 27 100 2 sample.json 3 sample.json 2022 10 31 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = user\n"
            + "\n"
            + "createUserDirectory called with input username = user\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = user and password = user\n"
            + "\n"
            + "checkValidFileName called with input jsonName = sample.json and userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 3.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 10.0\n"
            + "\n"
            + "checkValidSymbolName called with input AAPL\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 27, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-27\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, AAPL, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28, 2022-10-27]\n"
            + "\n"
            + "Quantities = [10.0, 100.0, 100.0]\n"
            + "\n"
            + "Commissions = [0.0, 0.0, 0.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "getCompositionHelper called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = user";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void exitLoginSignUpTest() {
    StringReader in = new StringReader("3");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    try {
      this.stocksController.run();
      String expectedOutput = "\n"
              + "\n"
              + "setValidSymbols called.\n"
              + "\n"
              + "setCSVSymbols called.\n"
              + "\n"
              + "setValidSymbols called.\n"
              + "\n"
              + "setCSVSymbols called.";
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void exitSignInTest() {
    StringReader in = new StringReader("1 admin admin 3");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    try {
      this.stocksController.run();
      String expectedOutput = "\n"
              + "\n"
              + "setValidSymbols called.\n"
              + "\n"
              + "setCSVSymbols called.\n"
              + "\n"
              + "setValidSymbols called.\n"
              + "\n"
              + "setCSVSymbols called.\n"
              + "\n"
              + "validateLoginCredentials called with inputs username = admin and password = admin";
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test(expected = NumberFormatException.class)
  public void characterAsInflexibleMenuChoiceInput() {
    StringReader in = new StringReader("a admin admin 1 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    try {
      this.stocksController.run();
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test(expected = NumberFormatException.class)
  public void characterAsNumberOfStocksInflexible() {
    StringReader in = new StringReader("1 admin admin 1 1 sample.json a");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    try {
      this.stocksController.run();
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test(expected = NumberFormatException.class)
  public void characterAsQuantityInflexible() {
    StringReader in = new StringReader("1 admin admin 1 1 sample.json 1 AMZN 2022 10 31 a");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    try {
      this.stocksController.run();
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void fractionalSharesInflexible() {
    StringReader in = new StringReader("1 admin admin 1 1 sample.json 1 AMZN 2022 10 31 1.1 1 4");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileName called with input jsonName = sample.json and userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [1.0]\n"
            + "\n"
            + "Commissions = [0.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginAndBuySharesTest() {
    StringReader in = new StringReader("1 admin admin 2 1 sample.json 2 AMZN 2022 10 31 100 1 "
            + "TSLA 2022 10 28 100 4 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void signUpAndBuySharesTest() {
    StringReader in = new StringReader("2 user user 2 1 sample.json 2 AMZN 2022 10 31 100 1 "
            + "TSLA 2022 10 28 100 4 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = user\n"
            + "\n"
            + "createUserDirectory called with input username = user\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = user and password = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void buySharesInExistingFlexiblePortfolioTest() {
    StringReader in = new StringReader("1 admin admin 2 1 sample.json 2 AMZN 2022 10 31 100 1 "
            + "TSLA 2022 10 28 100 4 1 sample.json 1 AAPL 2022 10 25 100 2 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-31, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input AAPL\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 25, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-25\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AAPL]\n"
            + "\n"
            + "BuyDates = [2022-10-25]\n"
            + "\n"
            + "Quantities = [100.0]\n"
            + "\n"
            + "Commissions = [2.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void loginBuySharesAndSellSharesTest() {
    StringReader in = new StringReader("1 admin admin 2 1 sample.json 2 AMZN 2022 10 28 100 1 "
            + "TSLA 2022 10 28 100 4 2 sample.json 1 AMZN 2022 10 31 100 2 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-28, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "setValidSellSymbols called with jsonName = sample.json and userName = admin\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidSellSymbols called with stockName = AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "validSellOrder called with inputs stockName = AMZN, quantity = 100.0, "
            + "date = 2022-10-31, username = admin and jsonFileName = sample.json\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [100.0]\n"
            + "\n"
            + "Commissions = [2.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void signUpBuySharesAndSellSharesTest() {
    StringReader in = new StringReader("2 admin admin 2 1 sample.json 2 AMZN 2022 10 28 100 1 "
            + "TSLA 2022 10 28 100 4 2 sample.json 1 AMZN 2022 10 31 100 2 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = admin\n"
            + "\n"
            + "createUserDirectory called with input username = admin\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-28, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "setValidSellSymbols called with jsonName = sample.json and userName = admin\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidSellSymbols called with stockName = AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "validSellOrder called with inputs stockName = AMZN, quantity = 100.0, "
            + "date = 2022-10-31, username = admin and jsonFileName = sample.json\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [100.0]\n"
            + "\n"
            + "Commissions = [2.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void examineCompositionFlexible() {
    StringReader in = new StringReader("1 admin admin 2 4 sample.json 2022 11 01 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getCompositionHelper called with input jsonName = sample.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void costValueBasisFlexible() {
    StringReader in = new StringReader("1 admin admin 2 5 sample.json 2022 11 01 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void getPortfolioValueFlexible() {
    StringReader in = new StringReader("1 admin admin 2 6 sample.json 2022 11 01 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "validateLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = admin";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void getPortfolioPerformanceTest() {
    StringReader in = new StringReader("2 user user 2 1 sample.json 2 AMZN 2022 10 28 100 1 "
            + "TSLA 2022 10 28 100 4 2 sample.json 1 AMZN 2022 10 31 100 2 7 sample.json "
            + "2022 01 01 2022 11 01 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = user\n"
            + "\n"
            + "createUserDirectory called with input username = user\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = user and password = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-28, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "setValidSellSymbols called with jsonName = sample.json and userName = user\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidSellSymbols called with stockName = AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "validSellOrder called with inputs stockName = AMZN, quantity = 100.0, "
            + "date = 2022-10-31, username = user and jsonFileName = sample.json\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [100.0]\n"
            + "\n"
            + "Commissions = [2.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 01\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 01 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-01-01\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getCostValueBasis called with inputs \n"
            + "\n"
            + "StockData from the portfolio\n"
            + "\n"
            + "Start date = 2022-01-01\n"
            + "\n"
            + "End date = 2022-11-01";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void signUpFlexibleOperations() {
    StringReader in = new StringReader("2 user user 2 1 sample.json 2 AMZN 2022 10 28 100 1 "
            + "TSLA 2022 10 28 100 4 2 sample.json 1 AMZN 2022 10 31 100 2 4 sample.json "
            + "2022 11 01 5 sample.json 2022 11 01 6 sample.json 2022 11 01 7 "
            + "sample.json 2022 01 01 2022 11 01 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = user\n"
            + "\n"
            + "createUserDirectory called with input username = user\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = user and password = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 2.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input TSLA\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 28, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-28\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "checkValidCommission called with input 4.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN, TSLA]\n"
            + "\n"
            + "BuyDates = [2022-10-28, 2022-10-28]\n"
            + "\n"
            + "Quantities = [100.0, 100.0]\n"
            + "\n"
            + "Commissions = [1.0, 4.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "setValidSellSymbols called with jsonName = sample.json and userName = user\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidSellSymbols called with stockName = AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 100.0\n"
            + "\n"
            + "validSellOrder called with inputs stockName = AMZN, quantity = 100.0, "
            + "date = 2022-10-31, username = user and jsonFileName = sample.json\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [100.0]\n"
            + "\n"
            + "Commissions = [2.0]\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getCompositionHelper called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidFileNameForExamination called with input jsonName = sample.json and "
            + "userName = user\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = user\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "getPortfolioFromJson called with input jsonName = sample.json, userName = user\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 01\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 01 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-01-01\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 11\n"
            + "\n"
            + "checkValidDate called with input date = 01, month = 11 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-11-01\n"
            + "\n"
            + "getCostValueBasis called with inputs \n"
            + "\n"
            + "StockData from the portfolio\n"
            + "\n"
            + "Start date = 2022-01-01\n"
            + "\n"
            + "End date = 2022-11-01";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }

  @Test
  public void fractionalSharesFlexible() {
    StringReader in = new StringReader("2 admin admin 2 1 sample.json 1 "
            + "AMZN 2022 10 31 1.1 1 2 8");
    this.stocksController = new StocksController(this.stocksView, this.mockModel,
            this.mockModel, in, this.log);
    String expectedOutput = "\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "setValidSymbols called.\n"
            + "\n"
            + "setCSVSymbols called.\n"
            + "\n"
            + "checkValidUserName called with input username = admin\n"
            + "\n"
            + "createUserDirectory called with input username = admin\n"
            + "\n"
            + "saveLoginCredentials called with inputs username = admin and password = admin\n"
            + "\n"
            + "isStrategyOngoing called with inputs\n"
            + "\n"
            + "userName = admin\n"
            + "\n"
            + "jsonName = sample.json\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "checkValidSymbolName called with input AMZN\n"
            + "\n"
            + "checkValidYear called with input 2022\n"
            + "\n"
            + "checkValidMonth called with input 10\n"
            + "\n"
            + "checkValidDate called with input date = 31, month = 10 and year = 2022\n"
            + "\n"
            + "checkValidBuyDate called with input 2022-10-31\n"
            + "\n"
            + "checkValidQuantity called with input 1.0\n"
            + "\n"
            + "checkValidCommission called with input 2.0\n"
            + "\n"
            + "writePortfolioToJson called with inputs\n"
            + "\n"
            + "JSONFileName = sample.json\n"
            + "\n"
            + "StockNames = [AMZN]\n"
            + "\n"
            + "BuyDates = [2022-10-31]\n"
            + "\n"
            + "Quantities = [1.0]\n"
            + "\n"
            + "Commissions = [2.0]";
    try {
      this.stocksController.run();
      assertEquals(expectedOutput, log.toString());
    } catch (IOException e) {
      fail("Failed as the statements could not be appended to logger.");
    }
  }
}