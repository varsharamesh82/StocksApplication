package stocks.driver;

import java.io.IOException;
import java.io.InputStreamReader;
import stocks.controller.StocksController;
import stocks.controller.StocksControllerGUI;
import stocks.controller.StocksControllerInterface;
import stocks.model.StocksModelFlexible;
import stocks.model.StocksModelInflexible;
import stocks.model.StocksModelInterface;
import stocks.view.StocksView;
import stocks.view.StocksViewGUI;
import stocks.view.StocksViewGUIInterface;
import stocks.view.StocksViewInterface;

/**
 * A StocksMVC class, whose objective is to just call the controller and run the program. Creates an
 * object of the StocksControllerInterface and calls the run method in the controller, leveraging
 * this object.
 */
public class StocksMVC {

  /**
   * Creates the Readable in and Appendable out variables and objects of StocksViewInterface,
   * StocksModelInterface and StocksControllerInterface, and calls the run (main execution method)
   * in the controller using the controller's object.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    Appendable out = System.out;

    StocksModelInterface modelInflexiblePortfolio = new StocksModelInflexible();
    StocksModelInterface modelFlexiblePortfolio = new StocksModelFlexible();

    StocksViewInterface stocksView = new StocksView(out);
    StocksControllerInterface stocksController = new StocksController(stocksView,
            modelInflexiblePortfolio, modelFlexiblePortfolio, in, out);

    if (args.length == 1 && args[0].equals("GUI")) {
      StocksViewGUIInterface stocksViewGUI = new StocksViewGUI("STOCK MARKET");
      stocksController = new StocksControllerGUI(stocksViewGUI, modelFlexiblePortfolio);
    }

    try {
      stocksController.run();
    } catch (IOException e) {
      System.out.println("Failed to append to the log.");
    }
  }
}
