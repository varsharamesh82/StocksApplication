# Design Changes:
- Created a new interface **Features** that contains the features or functionalities offered by the GUI.
  - This was done so as to facilitate the controller to interact with the GUI, and to implement the functionalities of our program.
  - Since, this approach provides the lowest level of coupling possible, thus we decided to create a new **Features** interface.
- Created a new controller **StocksControllerGUI** that implements the **Features** and the **StocksControllerInterface**.
  - This new **StocksControllerGUI** was created to implement the features specified by the **Features** interface. 
  - The new GUI calls these features or methods indirectly by using Lambda functions.
- Created a new interface **StocksViewGUIInterface** for the methods of the Graphical User Interface.
  - This was done to create an interface for the GUI methods.
  - Since the console based UI and the GUI have very different methods and especially method signatures (the console based UI does not interact with the features object), thus creating a new interface became essential.
- Created a new **StocksViewGUI** that implements the methods in the **StocksViewGUIInterface**.
  - This class actually implements the GUI.
- Created a new package **api**
  - This package contains the **API** interface that defines the method for fetching the data from the server.
  - The **APIImpl** implements the **API** interface and actually makes the calls to the AlphaVantage server.
  - This was done to separate out the API call from the MVC code. 
  - Thus, we can fetch the ticker data from a new source in the future without modifying the MVC code.
  - This is known as the **Decorator** pattern.
- Modified the **StocksModelInterface** to add the additional methods that were needed to implement new functionalities.
    - Since the **quantity**, can now be fractional (which was earlier prohibited), hence changed its datatype everywhere to **double** from **int**.  
    - Changed the method signature from **boolean checkValidQuantity(int stockQuantity)** to **boolean checkValidQuantity(double stockQuantity):** As the quantity can now be a **double**.
    - Added **boolean checkValidInvestmentValue(double investmentValue):** Checks if the investment value as input by the user for the Dollar Cost Averaging is valid. 
    - Added **boolean checkInterval(int interval):** Checks if the time interval input by the user for the Dollar Cost Averaging is valid. 
    - Added **boolean checkValidWeightage(double[] stockWeightagesArray, double stockWeightage):** Checks if the weightages input by the user are valid. All the weightages must add up to 100.
    - Added **boolean investmentStrategy(String strategyName, String jsonName, String userName, String startDate, String endDate, int interval, double investmentValue, String[] stockNamesArray, double[] stockWeightagesArray, double[] commissionArray, boolean endDateExists):** A generic method that can be used to implement other investment strategies in the future. Currently added to implement the **Dollar Cost Averaging**.
    - Added **String[] isStrategyOngoing(String userName, String jsonName):** To get an ongoing investment strategy up-to-date.
    - Changed the method signature from **String[] stockBuyDatesArray, int[] stockQuantitiesArray, double[] commissionsArray, String operation)** to **String[] stockBuyDatesArray, double[] stockQuantitiesArray, double[] commissionsArray, String operation):** As the quantities can now be a double.
    - Changed the method signature from **Double getPrice(String stockSymbol, String date)** to **Double getPrice(String stockSymbol, String date, boolean lastValidPriceFlag):** To help us compute the last valid price for a stock.
    - Changed the method signature from **boolean validSellOrderQuantity(String stockName, int quantity, String date, String userName, String jsonFileName)** to **boolean validSellOrderQuantity(String stockName, double quantity, String date, String userName, String jsonFileName):** Since the quantity can now be double.
- Modified the **StocksViewInterface** to add the additional methods that were needed to implement new functionalities.
    - Added **void getStockWeightage():** Prints the prompt to ask user for the weightage.
    - Added **void getInvestmentValue():** Prints the prompt to ask user for the investment value.
    - Added **void getInterval():** Prints the prompt to ask the user for an interval input.
    - Changed signature from **void printStocksData(String[] stockNamesArray, String[] stockDatesArray, int[] quantitiesArray, String[] lastTransactionDateArray, String[] buyPriceArray, String[] avgPriceArray, String[] commissionArray, String[] lastValidPriceArray, int size, boolean transactionFlag)** to **void printStocksData(String[] stockNamesArray, String[] stockDatesArray, double[] quantitiesArray, String[] lastTransactionDateArray, String[] buyPriceArray, String[] avgPriceArray, String[] commissionArray, String[] lastValidPriceArray, int size, boolean transactionFlag):** As the quantities can now be double.


# Design of our system:
# MVC Working in general: 

## Model: 
- In our model, it represents an object "abc" that carries all the data "xyz". 
- The model handles the overall functionality performed by a program.
- The model does not know about the existence of the view and the controller.

## View: 
- In our view, it represents the visualization of the data that the model contains.
- The view is primarily used just for displaying or for user interface.
- The view does not know about the existence of the model and the controller.

## Controller: 
- In our controller, it controls the data flow into the model object and updates the view whenever the data changes. It keeps the view and model separate.
- The controller calls the model and the view functions and determines the overall control flow of the program.


# MVC in Stock System:
## StocksControllerInterface
- The StocksControllerInterface, handles the functionalities of our stocks controller.
- It calls the various methods in the stocks view and the stocks model implementations, as and when required.
- Only the stocks controller knows about the existence of the view and the model.
- The go method in the controller handles the complete flow and execution of the program. It calls the various methods in the view and the model depending on as and when they are required.


## StocksViewInterface
- The StocksViewInterface, handles the console statements and printing for our program.
- The view does not know about the existence of the model and the controller.
- The methods in the view are called by using an object of the type StocksViewInterface in the controller.


## StocksModelInterface
- The StocksModelInterface, handles and implements the actual logic and functioning of our program. 
- The model does not know about the existence of the view and the controller.
- The methods in the model are called by using an object of the type StocksModelInterface in the controller.



# Advantages of using MVC in stock system:
- The MVC model resulted in higher development speeds, since we were able to split the work of view component and model, and the other developer can focus on controller.
- The MVC architecture gave the stock system the power to develop different view components for the model component. 
- MVC architecture helped in limiting the code duplication and separated the business logic and the data from the display.
- Since the model, view and controller are independent of each other, making changes in the the MVC architecture was rather simple because they didn't have an effect on each other, hence it was easier to debug the program.
- We were able to call the repeated components and used them in multiple interfaces.



# Advantages of using MVC in stock system during testing:
- We were able to test the controller separately by just implementing a mock of the actual model.
- This meant that we could start testing the controller, without actually implementing the model.

# Overall Program Flow and Execution:
## StocksView Interface:
- The view is responsible just for displaying the outputs to the user, and more specifically in the case of our program; for printing out the console-based UI and  output. The view is independent of the model and controller, and does not know about their existence. The controller is responsible for calling the view and it's methods as and when required.
- It contains the following functionalities:
- loginMenu - Prints the login or sign-up menu on the console.
- getUserName - Ask user for a username input.
- getPassword - Ask user for a password prompt.
- getMenu- Prints the main menu of the program
- getStocksInEachPortfolio- Ask user for the number of stocks in a portfolio.
- getStockNameForPortfolio- Ask user for the name of the portfolio.
- getStockDateForPortfolio- Ask user for the buying date of a stock.
- getStockQuantity -  Ask user for the number of stocks bought.
- portfolioHeader- Table headers are printed.
- stocksHeader- Prints the Stock and it's number. For eg, FOR STOCK 1
- getJSONName- Ask user for the name of the json.
- printStocksData- This function is used to examine a portfolio.
- getDate- Ask user for the date.
- printInvalidMessage- Prints an error message on the console for the user to see.
- printPortfolioValue- Prints the total portfolio value on the console.


## StocksModel Interface:
- The model handles all the logical functionality and  calculations of the program. The model works independently of the view and the controller, and does not know about their existence. It's contains the actual logic of the program. The controller is responsible for calling the methods in the model by using an object of the model's interface.
- It contains the following functionalities:
- checkValidUserName- Checks if a username is valid or not. Returns true if the username is valid.
- createUserDirectory- Creates a directory for the user with the username as the name of the directory, here all portfolios are stored inside this folder.
- saveLoginCredentials-  Used to save the username and password in a separate user_credentials file. Encryption is performed on this for more protection of the data.
- validateLoginCredentials- Checks the input username and password with the credentials stored in the user_credentials file  to validate login and since the username and password are encrypted, we need to decrypt them.
- setValidSymbols- Sets the ticker names or the stock names that are valid. Reads the 'valid_symbols.csv' file to  get all the valid stock symbols which is further used to verify if the user is valid or not.
- setCSVSymbols- Sets the stock names for which the ticker data is stored locally as a CSV.Reads the 'csv_symbols.csv' file to get all the stocks for which the data is stored locally. This is later used to minimize the calls to the API by fetching the data from the local files for the stocks for which the data is available locally.
- checkValidSymbolName- Checks if the stock name as input by the user is valid or not. Compares the stock name with a list of the valid stock names that is initialized by the setValidSymbols function.
- checkValidFileName- Checks if the file name as input by the user is valid or not. The file name is invalid if the file already exists. This function acts as a helper for the create portfolio functionality.
- checkValidFileNameForExamination- Checks if the file name as input by the user is valid or not. The file name is invalid if it does not exist. This function acts as a helper for the examine portfolio and get total value of a portfolio functionality.
- checkValidQuantity-  Checks if the number of stocks or stock quantity input by the user is valid or not. The stock quantity for a particular stock cannot be <= 0.
- checkValidBuyDate- Checks if the buying date as input by the user is valid or not. The buying date cannot be on a  weekend, as the market is closed. Hence, no stocks are traded or available on the weekends.
- writePortfolioToJson-  Writes the stock ticker data to a JSON. It gets the buying price for a stock name and buying date as entered by the user. This function is used to create a new portfolio and save it as a json.
- getPortfolioFromJson- Gets or reads the stock data from an existing JSON. This is used to examine the portfolio and to get the value of the portfolio on a certain date. It returns the data in the JSON as a JSONArray.
- callAPI- Calls the API and returns the API data as a Map and more specifically as a HashMap with the key as the date, and the value as the closing price on that day.
- getPrice- The getPrice method is used to determine the closing price of a stock on a particular date.
- getPriceHelper-  Acts as a helper method for the getPrice function. It calls the API and saves the data to a csv for future reference.
- getLastValidPrice- To get the lastValidPrice on any particular day. For example, the lastValidPrice on a weekend would be the last traded price. Thus return the last traded price.
- getPortfolioValue- Get the total portfolio value on a particular day, as entered by the user. Multiplying the quantity by the buying price for each stock in a portfolio, gives the total value of that  portfolio.
- encrypt- Used to encrypt the username and password, so that the login credentials are not susceptible to any attacks. The encrypted credentials are saved to the login_credentials file.
- decrypt-  Used to decrypt the username and password stored in the login_credentials file. The credentials in the login_credentials file are encrypted to protect them from attacks. Thus, we need to decrypt the values before they can be compared and validated.


## StocksController:
- The stockController is responsible for the overall handling and execution of the entire program.
- It contains two parameters - stocksView and stocksModel, they give access to the methods in the view and model respectively.
- The go method handles the complete flow and execution of the program. It calls the various methods in the view and the model depending on as and when they are required.
- The controller contains the four choices in the login menu, when each of them are called respectively.
- In option 1, we get the username and password that validates the username and the password of the user.
- In option 2, we validate if the given username exists or not and saves it in the directory accordingly.
- In option 3, it exists the program.
- By default, we print an invalid message.
- Once we have the menu options from the user, 
- We check if the file exists or not, if it doesn't , we ask the user to re enter a valid file name.
- Next, we check if the number of stocks given by the user is a valid number, i.e, >=1, else, we ask the user to re enter a valid number of stocks.We also check if the quantity given by the user has fractional shares or not, if the user has entered a fractional share, we ask the user to enter a valid input.
- Once we check the quantity, we next check if the stock name matches with the stockSymbol CSV, if it does not exist, we know it is an invalid stock name and ask the user to enter a valid stock name.
- Once we have the stock name, we ask the user to enter a date, we further check if the buying date is a weekend or not, if it is, we ask the user to re enter a valid buying date.
- If the portfolio file is unable to be created, we also throw an invalid message that the file could not be written to json.
- If the user enters an input number outside of the four options, we throw an invalid message that it is an invalid choice and we ask him to re enter a valid option.


## Driver (Main class):
- In this file, we have defined a InputStreamReader for our input and an appendable for the outputs.
- We have our StocksViewInterface stocksView, StocksModelInterface stocksModel and StocksControllerInterface stocksController defined here.
- We call our run function from this file, and we catch an IOException if it fails to append to the log.