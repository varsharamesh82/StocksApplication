package stocks.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * An implementation of the API interface. Thus, this class implements all the methods and
 * functionalities in the API Interface.
 */
public class APIImpl implements API {
  private final String apiKey;

  /**
   * This constructor sets the api key, that is required for making a call to the Alpha Vantage API
   * server.
   */
  public APIImpl() {
    this.apiKey = "NMBJV9I1JXOWWEZ6";
  }


  /**
   * Returns an array of strings, with the complete ticker data for a particular symbol.
   * @param stockSymbol   the stock name, for which we want to fetch the data.
   * @return              an array of strings with the complete data for a particular symbol.
   * @throws IOException  if it fails to call the URL.
   */
  @Override
  public String[] getTickerData(String stockSymbol) throws IOException {
    URL url;
    InputStream in;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol=" + stockSymbol
              + "&datatype=json"
              + "&apikey=" + this.apiKey
              + "&datatype=csv");
      in = url.openStream();
    } catch (IOException e) {
      throw new IOException("Incorrect call to the AlphaVantage API.");
    }

    StringBuilder output = new StringBuilder();
    int b;

    while ((b = in.read()) != -1) {
      output.append((char) b);
    }

    String[] ticker_data = output.toString().split("\n");
    return ticker_data;
  }
}
