# ABOUT/ OVERVIEW:

For one to be successful, the user must have a basic understanding of stocks, hence instead of hiring professional help, this application will help users who are newbies at investing. In this program, it will allow the user to create investment portfolios, to try and sell stocks and strategies that grows or shrinks with time.

# FEATURES:

The below listed features are implemented in our program and has been completed:
- Two types of User Interfaces. The user can opt for either a GUI, or a console based UI.
  - For GUI, run the command java -jar stocks.jar GUI
  - For console based UI, run the command java -jar stocks.jar
- We have a menu where we have a login, signup and exit options.
- There is a signup feature where multiple users can login with their username and password.
- The usernames and passwords are saved in a CSV file, and both the username and password are hashed for the purpose of validation(encrypted and decrypted).
- If a user does not exist, then a new user is created.
- For every user, a new directory is created in 'res/users'. This directory stores all the flexible and inflexible portfolios for a particular user.
- Once a user is created and logged in, the user can create two types of portfolios:
  - FLEXIBLE PORTFOLIO (CREATE & EXAMINE A PORTFOLIO)
  - FLEXIBLE PORTFOLIO (BUY & SELL SHARES)
- The user can also decide to create an automated "Dollar Cost Averaging" investment strategy, where the program automatically purchases stocks with specified weightages after a certain number of days.
- The program validates the stock names by checking the user input against a list of all the valid stock names. This list is initialized from the 'valid_symbols.csv' when the program is executed.
- The program maintains a static database of the 50 most traded stocks. If the input is from this list of stocks, then the ticker data is returned from the static database, else the AlphaVantage API is called.
# INFLEXIBLE PORTFOLIO:

## CREATE A NEW PORTFOLIO

- Allows users to create a new portfolio with a custom name, with any number of stocks.
- This portfolio is saved in a user readable JSON format and can be accessed outside of the program.
- A user can have multiple portfolios. For eg, savings, retirements, etc.
- Each portfolio that the user creates is stored in his/ her respective folder in 'res/users/inflexible'.
- The user enters the number of stocks. We check if the given number is greater than or equal to one. If not, we ask the user for a valid input.
- From the above input given by the user, the interface presents the user with three inputs for each of his stock.
  - STOCK NAME:
    - The name of the stock. This is validated against a 'valid_symbols.csv' that contains a list of all the valid stock names. Every time the program is executed, the valid stock names are set as an ArrayList. If not valid, we ask the user for a valid input.
  - DATE:
    - The buying date or the purchase date cannot be on a weekend, as the stock market is closed every weekend. If not valid, we ask the user for a valid input.
  - QUANTITY:
    - The quantity or the number of stocks bought must be greater than or equal to one. If not valid, we ask the user for a valid input.
- The buying price is calculated by taking the 'closing price' on any particular date.
- The program maintains a list of the 50 most traded stocks and the ticker data. If the stock name is from this list of 55 stocks, then the buy price is determined from this static database, or else it calls the AlphaVantage API.

## EXAMINE COMPOSITION OF EXISTING PORTFOLIO

- This feature allows the user to view the data or the stocks in any portfolio, in a graphical format.
- The user needs to enter a name of any of his/ her portfolios in order to examine them.
- In case the portfolio is not in his/ her folder in 'res/users/inflexible', then we cannot examine that portfolio.
- Examination of the portfolio displays the following for every stock in the portfolio.
  - Stock Name
  - Quantity
  - First Buy Date
  - Last Transaction Date
  - Last Buy Price
  - Avg Buy Price
  - Last Traded Price
- The last traded price returns the last closing price, or the last price that the stock was available for, for that particular stock.

## GET TOTAL VALUE OF PORTFOLIO ON A DATE

- This feature allows the user to get the total value of a portfolio on any given date.
- In other words, it tells the total value of all the stocks in that particular portfolio on any given day.
- The user needs to enter a name of any of his/ her portfolio, and the date for which he/ she wants to determine the value.
- In case the portfolio is not in his/ her folder in 'res/users/inflexible/inflexible', then we cannot get the value of that portfolio.

# FLEXIBLE PORTFOLIO:

## BUY SHARES

- Allows the user to buy shares in a portfolio.
- The user enters a portfolio name. If the portfolio already exists, then the buy order is appended to the existing portfolio; else a new portfolio is created with the buy order.
- A user can have multiple portfolios. For eg, savings, retirements, etc.
- Each portfolio that the user creates is stored in his/ her respective folder in 'res/users/flexible'.
- The user enters the number of stocks. We check if the given number is greater than or equal to one. If not, we ask the user for a valid input.
- From the above input given by the user, the interface presents the user with four inputs for each of his stock.
  - STOCK NAME:
    - The name of the stock. This is validated against a 'valid_symbols.csv' that contains a list of all the valid stock names. Every time the program is executed, the valid stock names are set as an ArrayList. If not valid, we ask the user for a valid input.
  - DATE:
    - The buying date or the purchase date cannot be on a weekend, as the stock market is closed every weekend. If not valid, we ask the user for a valid input.
  - QUANTITY:
    - The quantity or the number of stocks bought must be greater than or equal to one. If not valid, we ask the user for a valid input.
  - COMMISSION:
    - This is the commission charged by the broker or the stock exchange for this particular transaction. The commission is a floating point value and must be greater than or equal to zero.
- The buying price is calculated by taking the 'closing price' on any particular date.
- The program maintains a list of the 50 most traded stocks and the ticker data. If the stock name is from this list of 50 stocks, then the buy price is determined from this static database, or else it calls the AlphaVantage API.

## SELL SHARES

- Allows the users to sell shares that have already been bought in a particular portfolio.
- The user enters the name of a portfolio. This portfolio must already exist.
- A user can only sell shares that he/ she has bought on a prior date, and in quantity which is greater than the number of shares that he/ she is trying to sell right now.
- The user enters the number of stocks. We check if the given number is greater than or equal to one. If not, we ask the user for a valid input.
- From the above input given by the user, the interface presents the user with four inputs for each of his stock.
  - STOCK NAME:
    - The name of the stock. This is validated against the stock names that have already been bought in the specified portfolio. A user is only allowed to sell the shares that he/ she has bought prior to placing the current sell order.
  - DATE:
    - The selling date cannot be on a weekend, as the stock market is closed every weekend. If not valid, we ask the user for a valid input.
  - QUANTITY:
    - The quantity or the number of stocks sold must be greater than or equal to one, and must be less than the total number of stocks bought on or before that daye. If not valid, we ask the user for a valid input.
  - COMMISSION:
    - This is the commission charged by the broker or the stock exchange for this particular transaction. The commission is a floating point value and must be greater than or equal to zero.

## EXAMINE COMPOSITION OF EXISTING PORTFOLIO

- This feature allows the user to view the data or the stocks in any portfolio, in a graphical format.
- The user needs to enter a name of any of his/ her portfolios in order to examine them.
- In case the portfolio is not in his/ her folder in 'res/users/inflexible', then we cannot examine that portfolio.
- Examination of the portfolio displays the following for every stock in the portfolio.
  - Stock Name
  - Quantity
  - First Buy Date
  - Last Transaction Date
  - Last Buy Price
  - Avg Buy Price
  - Last Traded Price
  - Commission
- The last traded price returns the last closing price, or the last price that the stock was available for, for that particular stock.

## DOLLAR COST AVERAGING

- This feature allows the user to create an automated investment strategy.
- The user needs to enter a portfolio name, the stock names, weightages, the value to be invested and the time interval (in days) between 2 successive investments.
- The program will automatically invest the specified amount after every interval, by purchasing specified stocks in the ratios as given by the user.
- This allows the user to be a passive investor, passively investing a set amount, after a given time interval.
- For example, 
  - The user can create a FANG portfolio (Facebook, Apple, Netflix, Google).
  - Specify to invest $2000 in the portfolio on a specific date, such that 40% goes towards Facebook, 20% towards Apple, 30% towards Netflix and 10% towards Google).
- In case of a holiday, the program executes the buy order on the next day.

## COST VALUE BASIS

- Gives the total amount of money invested in a portfolio till that date.
- Asks the user for a portfolio name. This portfolio must exist in the 'res/users/{username}/flexible' folder, before we can calculate the cost value basis for it.
- The cost value basis includes all the purchases and the total commission upto a certain date.
- Thus, the user also needs to input a date till which we need to calculate the cost value basis.
- The cost value basis is calculated using the following formula - Commission for every transaction + (Buy Price * Quantity) per buy order.

## GET TOTAL VALUE OF PORTFOLIO ON A DATE

- This feature allows the user to get the total value of a portfolio on any given date.
- In other words, it tells the total value of all the stocks in that particular portfolio on any given day.
- The user needs to enter a name of any of his/ her portfolio, and the date for which he/ she wants to determine the value.
- In case the portfolio is not in his/ her folder in 'res/users/{username}/inflexible', then we cannot get the value of that portfolio.

## PORTFOLIO PERFORMANCE OVER TIME

- Gives a graphical overview of how the portfolio has performed over a period of time.
- Gives the value of the portfolio in the form of a horizontal bar chart, over a given date range that is input by the user.
- The user enters a portfolio name, that he/ she needs to get the performance for. This portfolio must exist in the 'res/users/{username}/flexible' folder, before we can get the portfolio performance for it.
- The user then enters a start date and an end date, for providing the date range. The end date must be later than the start date, for it to be valid. The program checks the above stated condition and asks for an end date until its valid.
- The portfolio performance is either
    - Daily
    - Weekly
    - Monthly
    - Yearly
depending on the number of days between the start date and the end date. A suitable time range is determined automatically by the program.

## LINE CHART OF PORTFOLIO PERFORMANCE OVER TIME

- Gives a line chart of the portfolio value over a given period of time.
- The time ranges is on the X-Axis, with the values of the portfolio on the Y-Axis.
- The user enters a portfolio name, that he/ she needs to get the performance for. This portfolio must exist in the 'res/users/{username}/flexible' folder, before we can get the portfolio performance for it.
- The user then enters a start date and an end date, for providing the date range. The end date must be later than the start date, for it to be valid. The program checks the above stated condition and asks for an end date until its valid.
- The portfolio performance is either
  - Daily
  - Weekly
  - Monthly
  - Yearly
depending on the number of days between the start date and the end date. A suitable time range is determined automatically by the program.

# HANDLING LIMITATIONS OF THE ALPHAVANTAGE API:

- The AlphaVantage API is used to get the ticker data for any stock.
- The AlphaVantage API restricts the number of calls that can be made to its server and is comparatively slower to fetch the data.
- To counter this we've a static database of the 50 most traded stocks. If a stock name input by the user is from this list of stocks, then the ticker data is fetched from the static database, instead of calling the AlphaVantage API.
- If the stock name is not in this list, then the API is called for that particular stock name and the data is stored in a local cache on the user system. The next time the program is executed, it checks if the stock data is available in the static database and the local cache and makes the call to the API only if it is not present in the both of them.
