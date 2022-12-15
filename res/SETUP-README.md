# External Libraries Used:
- jcommon-1.0.23.jar  - https://mvnrepository.com/artifact/org.jfree/jcommon/1.0.23
- jfreechart-1.0.19.jar  - https://mvnrepository.com/artifact/org.jfree/jfreechart/1.0.19
- These 2 external libraries are used to implement the Line Chart. However, the jar works without having to load these 2 external libraries.

# How To Run the program from the JAR file:
- Run the JAR file on the console.
- To execute the JAR, navigate to the folder on your terminal using the 'cd' command. 
- The resources or the 'res' folder that contains the JAR has all the other dependencies that are needed for the JAR to function correctly.
- You have 2 options for the UI. You can either opt for the traditional console based UI, or for a graphical user interface.
- For console based UI, run the following command: java -jar stocks.jar
- For graphical UI, run the following command: java -jar stocks.jar GUI
- Give inputs on the console or the GUI depending on your choice.


# Create Flexible Portfolios
## Purchase 3 different stocks and get Portfolio Composition and Cost Value Basis
- Execute the JAR on the console by navigating to the folder. 
- Select '2' to Sign Up as a new user. Enter the username and password.
- Select '2' to select flexible portfolios.
- Select '1' to purchase shares. 
  - Enter the name of the portfolio, say sample_1.json. 
  - Enter the number of stocks. 3 in this case.
  - Enter the data for the 3 stocks. For each stock you need to enter the stock name, buying date and the quantity. For example -
    - Stock Name: AMZN
    - Year: 2022
    - Month: 10
    - Date: 31
    - Quantity: 10
    - Commission: 5

    - Stock Name: GOOG
    - Year: 2022
    - Month: 10
    - Date: 25
    - Quantity: 20
    - Commission: 3

    - Stock Name: AAPL
    - Year: 2022
    - Month: 10
    - Date: 27
    - Quantity: 30
    - Commission: 2.5

- Select '3' if you want to examine the created portfolio.
  - Enter the name of the JSON, sample_1.json in our case.
  - Enter the date for which you want to get the portfolio composition. Suppose you want to get the composition till 10/31/2022. Enter
    - Year: 2022
    - Month: 10
    - Date: 31

- Select '4' to determine the cost value basis of the above created portfolio on any particular date. 
  - Enter the name of the JSON, sample_1.json in our case.
  - Enter the date for which you want to get the portfolio composition. Suppose you want to get the composition till 10/31/2022. Enter
    - Year: 2022
    - Month: 10
    - Date: 31

- Select '3' if you want to examine the created portfolio.
  - Enter the name of the JSON, sample_1.json in our case.
  - Enter the date for which you want to get the portfolio composition. Suppose you want to get the composition till 10/25/2022. Enter
    - Year: 2022
    - Month: 10
    - Date: 25

- Select '4' to determine the cost value basis of the above created portfolio on any particular date. 
  - Enter the name of the JSON, sample_1.json in our case.
  - Enter the date for which you want to get the portfolio composition. Suppose you want to get the composition till 10/25/2022. Enter
    - Year: 2022
    - Month: 10
    - Date: 25

- Exit the program by selecting '7'.


# Create Inflexible Portfolios
## A portfolio with 3 different stocks
- Execute the JAR on the console by navigating to the folder. 
- Select '2' to Sign Up as a new user. Enter the username and password.
- Select '1' to create an inflexible portfolio.
- Select '1' to create a new portfolio. 
  - Enter the name of the portfolio, say sample_1.json. 
  - Enter the number of stocks. 3 in this case.
  - Enter the data for the 3 stocks. For each stock you need to enter the stock name, buying date and the quantity. For example -
    - AMZN
    - 2022-10-31
    - 10

    - GOOG
    - 2022-10-25
    - 100

    - AAPL
    - 2022-10-27
    - 50
- Select '2' if you want to examine the created portfolio.
  - Enter the name of the JSON, sample_1.json in our case.
- Select '3' if you want to determine the value of the above created portfolio on any particular date. 
- Else exit the program by selecting '4'.



## A portfolio with 2 different stocks
- Execute the JAR on the console by navigating to the folder. 
- Select '2' to Sign Up as a new user. Enter the username and password.
- Select '1' to create an inflexible portfolio.
- Select '1' to create a new portfolio. 
  - Enter the name of the portfolio, say sample_2.json. 
  - Enter the number of stocks. 2 in this case.
  - Enter the data for the 2 stocks. For each stock you need to enter the stock name, buying date and the quantity. For example -
    - META
    - 2022-10-31
    - 200

    - MSFT
    - 2022-10-25
    - 250
- Select '2' if you want to examine the created portfolio.
  - Enter the name of the JSON, sample_2.json in our case.
- Select '3' if you want to determine the value of the above created portfolio on any particular date. 
- Else exit the program by selecting '4'.


# Create a portfolio externally and loading it to the program
- The user also has the option to create portfolio externally and load it to our program.
- The portfolio needs to be stored as a JSON.
- The user needs to copy the portfolio created externally to his/ her folder location.
- This location can be found in 'res/users/{username}'.
- Depending on the type of the portfolio, the portfolio must be stored in either -
  - 'res/users/{username}/flexible': For flexible portfolios.
  - 'res/users/{username}/inflexible': For inflexible portfolios.
- For example, for the username admin, the user needs to copy the JSON to 'res/users/admin/flexible' or 'res/users/admin/inflexible'.
- This folder is located where the JAR is executed.
- However for loading a portfolio externally, it needs to strictly follow the following format - 
  - The JSON SHOULD NOT be a nd-JSON (new line delimited JSON).
  - The stocks (multiple of them) should be in a JSON array, with the array itself acting as the "value" for the "stocks" key. 
  - For every stock, you need to have
      - transaction_date: the transaciton date
      - stock_name: the name of the stock
      - price: the price at which the transaction occurred
      - operation: is either of BUY/SELL/PORTFOLIO. Is "PORTFOLIO" in the case of Inflexible portfolios and "BUY" or "SELL" in the case of Flexible portfolios.
      - quantity: the number of stocks
    - For Flexible Portfolio only:
      - commission: the commission for that particular transaction
    
  - Example: For 2 stocks, the JSON should look like - {"stocks":[{"transaction_date":"2022-10-31","quantity":10,"stock_name":"GOOG","price":94.66,"operation":"PORTFOLIO"},{"transaction_date":"2022-10-25","quantity":10,"stock_name":"TSLA","price":222.415,"operation":"PORTFOLIO"}]}.



# List of stocks that our program supports, along with dates on which its value can be determined:

STOCK NAME           START DATE       START DATE VALUE       END DATE        END DATE VALUE
---------------------------------------------------------------------------------------------
ABC 		             1999-11-01         13.69               2022-11-01          156.32
ABNB                 2020-12-10         144.71              2022-11-01          109.05
AMC                  2013-12-18         18.9                2022-11-01          6.15
AMZN                 1999-11-01         69.13               2022-11-01          96.79
ATCO                 2020-02-28         9.72                2022-11-01          15.27
CLF                  1999-11-01         29.06               2022-11-01          13.35
CMCSA                1999-11-01         37.25               2022-11-01          31.56
CVNA                 2017-04-28         11.1                2022-11-01          15.28
DASH                 2020-12-09         189.51              2022-11-01          45.1
ET                   2018-10-17         17.08               2022-11-01          12.75
FCX                  1999-11-01         16.38               2022-11-01          32.9
FUBO                 2017-11-30         0.31                2022-11-01          3.55
GM                   2010-11-18         34.19               2022-11-01          39.35
GOLD                 1999-11-01         17.81               2022-11-01          15.14
GOOG                 2014-03-27         558.46              2022-11-01          90.5
GRAB                 2020-12-01         11.89               2022-11-01          2.64
JPM                  1999-11-01         83.56               2022-11-01          128.15
LYFT                 2019-03-29         78.29               2022-11-01          15.15
META                 2012-05-18         38.2318             2022-10-31          93.16
MSFT                 1999-11-01         92.37               2022-11-01          228.17
NCLH                 2013-01-18         24.79               2022-11-01          16.79
NVDA                 1999-11-01         23.5                2022-11-01          135.43
PLTR                 2020-09-30         9.5                 2022-11-01          8.65
SHOP                 2015-05-21         25.68               2022-11-01          34.79
SNAP                 2017-03-02         24.48               2022-11-01          10.25
TSLA                 2010-06-29         23.89               2022-11-01          227.82
UBER                 2019-05-10         41.57               2022-11-01          29.75
RIVN                 2021-11-10         100.73              2022-11-01          33.48
COIN                 2021-04-14         328.28              2022-11-01          63.29
FTI                  2001-06-14         22.0                2022-11-01          10.68
AFRM                 2021-01-13         97.24               2022-11-01          19.46
AAPL                 1999-11-01         77.62               2022-11-01          150.65
CHGG                 2013-11-13         9.68                2022-11-01          21.11
BAND                 2017-11-10         21.19               2022-11-01          12.3
CVS                  1999-11-01         40.36               2022-11-01          94.62
MTCH                 2015-11-19         14.74               2022-11-01          43.9
BA                   1999-11-01         44.63               2022-11-01          143.38