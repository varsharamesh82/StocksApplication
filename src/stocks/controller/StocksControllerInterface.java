package stocks.controller;

import java.io.IOException;

/**
 * Defines the stocksControllerInterface. The controller basically moderates the overall flow of the
 * program. It is responsible for calling the various view and model methods and maintaining the
 * order of execution. Only the controller is aware about the existence of the model and the view.
 */
public interface StocksControllerInterface {

  /**
   * The go method handles the complete flow and execution of the program. It calls the various
   * methods in the view and the model depending on as and when they are required.
   *
   * @throws IOException  if it fails to append the logger, that keeps track of the activities.
   */
  void run() throws IOException;
}
