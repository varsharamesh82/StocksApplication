package stocks.api;

import java.io.IOException;


/**
 * This interface facilitates fetching the ticker data from various sources. Currently, the program
 * fetches the ticker data from the Alpha Vantage API. However, we can similarly fetch the data
 * from multiple other sources, without modifying any of the MVC code.
 */
public interface API {

  /**
   * Returns an array of strings, with the complete ticker data for a particular symbol.
   * @param stockSymbol   the stock name, for which we want to fetch the data.
   * @return              an array of strings with the complete data for a particular symbol.
   * @throws IOException  if it fails to call the URL.
   */
  String[] getTickerData(String stockSymbol) throws IOException;
}
