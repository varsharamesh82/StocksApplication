package stocks.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import stocks.controller.Feature;
import org.jfree.chart.ChartFactory;


/**
 * This class extends the JFrame class and implements the StocksViewGUIInterface interface,
 * facilitating the Swing-based GUI for the user. This class thus implements all the methods in the
 * StocksViewGUIInterface.
 */
public class StocksViewGUI extends JFrame implements StocksViewGUIInterface {

  JTextField usernameInput;
  JTextField passwordInput;
  JTextField portfolioNameInput;
  JTextField stockNameInput;
  JSpinner yearInput;
  JSpinner monthInput;
  JSpinner dateInput;
  JSpinner stockQuantityInput;
  JSpinner commissionInput;
  JSpinner weightageInput;
  JSpinner yearInput2;
  JSpinner monthInput2;
  JSpinner dateInput2;
  JSpinner investmentValueInput;
  JSpinner intervalInput;
  JButton login;
  JButton signUp;
  JButton exit;
  JButton buy;
  JButton sell;
  JButton dollarCostAveraging;
  JButton examineComposition;
  JButton costValueBasis;
  JButton totalPortfolioValue;
  JButton portfolioPerformance;
  JButton next;
  JButton addAnother;
  JButton done;
  JButton back;
  JButton getComposition;
  JButton mainMenu;
  JButton getCostValueBasis;
  JButton getTotalPortfolioValue;
  JButton getPortfolioPerformance;
  JButton lineChart;
  JButton getLineChart;
  JCheckBox ongoing;
  GridBagConstraints gridBagConstraints;


  /**
   * A constructor of the StocksViewGUI. Creates a window, sets its dimensions, its visibility to
   * true, and the title.
   *
   * @param caption the title of the window.
   */
  public StocksViewGUI(String caption) {
    super(caption);

    this.gridBagConstraints = new GridBagConstraints();

    this.setTitle(caption);
    this.setSize(1000, 700);
    this.setLocation(0, 0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridBagLayout());
    this.setResizable(true);

    this.setVisible(true);
  }


  /**
   * Prints an error message on the console for the user to see.
   *
   * @param message the error message.
   */
  @Override
  public void printMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }


  /**
   * Prints the login or sign-up menu on the console.
   */
  @Override
  public void loginMenu() {
    this.getContentPane().removeAll();
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new FlowLayout());
    titlePanel.setSize(1000, 700);

    JLabel title = new JLabel("WELCOME TO THE STOCK MARKET APPLICATION");
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setVerticalAlignment(JLabel.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 30));
    title.setForeground(Color.blue);
    title.setBackground(Color.black);
    titlePanel.add(title);

    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JLabel username = new JLabel("Username");
    username.setFont(new Font("Arial", Font.BOLD, 16));
    username.setForeground(Color.black);
    username.setBackground(Color.black);
    panel1.add(username);
    usernameInput = new JTextField(10);
    panel1.add(usernameInput);

    JLabel password = new JLabel("Password");
    panel1.add(password);
    password.setFont(new Font("Arial", Font.BOLD, 16));
    password.setForeground(Color.black);
    password.setBackground(Color.black);
    passwordInput = new JPasswordField(10);
    panel1.add(passwordInput);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());

    login = new JButton("Login");
    login.setActionCommand("loginButton");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    login.setBorder(compound);
    panel2.add(login);

    signUp = new JButton("Sign up");
    signUp.setActionCommand("signUpButton");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    signUp.setBorder(compound);
    panel2.add(signUp);

    exit = new JButton("Exit");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    exit.setBorder(compound);
    exit.setActionCommand("exitButton");
    panel2.add(exit);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(titlePanel, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = -3;
    this.add(panel2, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the loginMenu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void loginMenuFeatures(Feature feature) {
    login.addActionListener(e -> feature.login(usernameInput.getText(), passwordInput.getText()));
    signUp.addActionListener(e -> feature.signUp(usernameInput.getText(), passwordInput.getText()));
    exit.addActionListener(e -> feature.exit());
  }


  /**
   * Prints the main menu of the program.
   */
  @Override
  public void mainMenu() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    buy = new JButton("Buy Stocks");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    buy.setBorder(compound);
    buy.setActionCommand("buyButton");
    sell = new JButton("Sell Stocks");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    sell.setBorder(compound);
    sell.setActionCommand("sellButton");
    dollarCostAveraging = new JButton("Dollar Cost Averaging Portfolio");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    dollarCostAveraging.setBorder(compound);
    dollarCostAveraging.setActionCommand("dollarCostAveragingButton");
    examineComposition = new JButton("Examine Composition of a Portfolio");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    examineComposition.setBorder(compound);
    examineComposition.setActionCommand("examineCompositionButton");
    costValueBasis = new JButton("Cost Value Basis");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    costValueBasis.setBorder(compound);
    costValueBasis.setActionCommand("costValueBasisButton");
    totalPortfolioValue = new JButton("Get the Value of Portfolio");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    totalPortfolioValue.setBorder(compound);
    totalPortfolioValue.setActionCommand("totalPortfolioValueButton");
    portfolioPerformance = new JButton("Portfolio Performance over Time");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    portfolioPerformance.setBorder(compound);
    portfolioPerformance.setActionCommand("portfolioPerformanceButton");

    lineChart = new JButton("Line Chart of Portfolio Performance over Time");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    lineChart.setBorder(compound);
    lineChart.setActionCommand("lineChartButton");
    exit = new JButton("Exit");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    exit.setBorder(compound);
    exit.setActionCommand("exitButton");

    panel1.add(buy);
    panel1.add(sell);
    panel1.add(dollarCostAveraging);

    panel2.add(examineComposition);
    panel2.add(costValueBasis);
    panel2.add(totalPortfolioValue);

    panel3.add(portfolioPerformance);
    panel3.add(lineChart);
    panel3.add(exit);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the main menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void mainMenuFeatures(Feature feature) {
    buy.addActionListener(e -> feature.buy());
    sell.addActionListener(e -> feature.sell());
    dollarCostAveraging.addActionListener(e -> feature.dollarCostAveraging());
    examineComposition.addActionListener(e -> feature.examineComposition());
    costValueBasis.addActionListener(e -> feature.costValueBasis());
    totalPortfolioValue.addActionListener(e -> feature.totalPortfolioValue());
    portfolioPerformance.addActionListener(e -> feature.portfolioPerformance());
    lineChart.addActionListener(e -> feature.lineChart());
    exit.addActionListener(e -> feature.exit());
  }


  /**
   * Prints the menu that asks for a portfolio name.
   */
  @Override
  public void portfolioNameMenu() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel2.add(back);

    next = new JButton("Next");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    next.setBorder(compound);
    next.setActionCommand("nextButton");
    panel2.add(next);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the portfolio name menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void portfolioNameMenuFeatures(Feature feature, String operation) {
    back.addActionListener(e -> feature.backToMainMenu());
    next.addActionListener(e -> feature.validPortfolioParameters(portfolioNameInput.getText(),
            operation));
  }


  /**
   * Prints the main for taking the stock data (name, commission, etc) as input from the  user.
   */
  @Override
  public void getStockData(boolean flag) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel stockName = new JLabel("Stock Name");
    stockName.setFont(new Font("Arial", Font.BOLD, 16));
    stockName.setForeground(Color.black);
    stockName.setBackground(Color.black);
    panel1.add(stockName);
    stockNameInput = new JTextField(10);
    panel1.add(stockNameInput);

    JLabel quantity = new JLabel("Quantity");
    quantity.setFont(new Font("Arial", Font.BOLD, 16));
    quantity.setForeground(Color.black);
    quantity.setBackground(Color.black);
    panel1.add(quantity);
    stockQuantityInput = new JSpinner(new SpinnerNumberModel(1.0, 1.0, Integer.MAX_VALUE, 1.0));
    panel1.add(stockQuantityInput);

    JLabel commission = new JLabel("Commission");
    commission.setFont(new Font("Arial", Font.BOLD, 16));
    commission.setForeground(Color.black);
    commission.setBackground(Color.black);
    panel1.add(commission);
    commissionInput = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 0.5));
    panel1.add(commissionInput);

    JLabel year = new JLabel("Year");
    year.setFont(new Font("Arial", Font.BOLD, 16));
    year.setForeground(Color.black);
    year.setBackground(Color.black);
    panel2.add(year);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor);
    panel2.add(yearInput);

    JLabel month = new JLabel("Month");
    month.setFont(new Font("Arial", Font.BOLD, 16));
    month.setForeground(Color.black);
    month.setBackground(Color.black);
    panel2.add(month);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date = new JLabel("Date");
    date.setFont(new Font("Arial", Font.BOLD, 16));
    date.setForeground(Color.black);
    date.setBackground(Color.black);
    panel2.add(date);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel3.add(back);

    addAnother = new JButton("Add Another");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    addAnother.setBorder(compound);
    addAnother.setActionCommand("addAnotherButton");
    panel3.add(addAnother);

    if (flag) {
      done = new JButton("Done");
      done.setActionCommand("doneButton");
      panel3.add(done);
    }

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the get stock data menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void getStockDataFeatures(Feature feature, boolean flag, String operation) {
    if (operation.equals("BUY")) {
      back.addActionListener(e -> feature.buy());
    } else if (operation.equals("SELL")) {
      back.addActionListener(e -> feature.sell());
    }
    addAnother.addActionListener(e -> feature.setStockData(operation, stockNameInput.getText(),
            ((Number) this.stockQuantityInput.getValue()).doubleValue(),
            ((Number) this.commissionInput.getValue()).doubleValue(),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue())));

    if (flag) {
      done.addActionListener(e -> feature.writeToPortfolio(operation));
    }
  }


  /**
   * Prints the menu for examining composition of a portfolio.
   */
  @Override
  public void examineComposition() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel year = new JLabel("Year");
    year.setFont(new Font("Arial", Font.BOLD, 16));
    year.setForeground(Color.black);
    year.setBackground(Color.black);
    panel2.add(year);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor);
    panel2.add(yearInput);

    JLabel month = new JLabel("Month");
    month.setFont(new Font("Arial", Font.BOLD, 16));
    month.setForeground(Color.black);
    month.setBackground(Color.black);
    panel2.add(month);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date = new JLabel("Date");
    date.setFont(new Font("Arial", Font.BOLD, 16));
    date.setForeground(Color.black);
    date.setBackground(Color.black);
    panel2.add(date);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel3.add(back);

    getComposition = new JButton("Get Composition");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    getComposition.setBorder(compound);
    getComposition.setActionCommand("getComposition");
    panel3.add(getComposition);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the examine composition menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void examineCompositionFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    getComposition.addActionListener(e -> feature.getComposition(portfolioNameInput.getText(),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue())));
  }


  /**
   * Prints the composition of a portfolio.
   */
  @Override
  public void displayComposition(Object[][] rows, Object[] columns) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JTable table = new JTable(rows, columns);
    TableColumnModel columnModel = table.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(100);
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(2).setPreferredWidth(170);
    columnModel.getColumn(3).setPreferredWidth(170);
    columnModel.getColumn(4).setPreferredWidth(130);
    columnModel.getColumn(5).setPreferredWidth(130);
    columnModel.getColumn(6).setPreferredWidth(130);
    columnModel.getColumn(7).setPreferredWidth(100);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(700, 500));

    this.add(scrollPane);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);

    back.setActionCommand("back");

    panel2.add(back);

    mainMenu = new JButton("Back to Main Menu");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    mainMenu.setBorder(compound);
    mainMenu.setActionCommand("mainMenuButton");
    panel2.add(mainMenu);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    pack();
    this.setVisible(true);
  }


  /**
   * The features for the print composition page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void displayCompositionFeatures(Feature feature) {
    back.addActionListener(e -> feature.examineComposition());
    mainMenu.addActionListener(e -> feature.backToMainMenu());
  }


  /**
   * Prints the menu for calculating cost value basis of a portfolio.
   */
  @Override
  public void costValueBasis() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel year = new JLabel("Year");
    year.setFont(new Font("Arial", Font.BOLD, 16));
    year.setForeground(Color.black);
    year.setBackground(Color.black);
    panel2.add(year);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor);
    panel2.add(yearInput);

    JLabel month = new JLabel("Month");
    month.setFont(new Font("Arial", Font.BOLD, 16));
    month.setForeground(Color.black);
    month.setBackground(Color.black);
    panel2.add(month);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date = new JLabel("Date");
    date.setFont(new Font("Arial", Font.BOLD, 16));
    date.setForeground(Color.black);
    date.setBackground(Color.black);
    panel2.add(date);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel3.add(back);

    getCostValueBasis = new JButton("Get Cost Value Basis");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    getCostValueBasis.setBorder(compound);
    getCostValueBasis.setActionCommand("getCostValueBasisButton");
    panel3.add(getCostValueBasis);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the calculate cost value basis menu. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void costValueBasisFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    getCostValueBasis.addActionListener(e -> feature.getCostValueBasis(portfolioNameInput.getText(),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue())));
  }


  /**
   * Prints the cost value basis of a portfolio.
   *
   * @param costBasis     the cost value.
   * @param portfolioName the name of the portfolio.
   * @param date          the date on which the cost value is calculated.
   */
  @Override
  public void displayCostValueBasis(double costBasis, String portfolioName, String date) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JLabel costValueBasis = new JLabel(
            "Cost Value Basis of " + portfolioName + " on " + date + " = $" + String.format("%.2f",
                    costBasis));
    costValueBasis.setFont(new Font("Arial", Font.BOLD, 16));
    costValueBasis.setForeground(Color.black);
    costValueBasis.setBackground(Color.black);
    panel1.add(costValueBasis);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);

    back.setActionCommand("back");
    panel2.add(back);

    mainMenu = new JButton("Back to Main Menu");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    mainMenu.setBorder(compound);
    mainMenu.setActionCommand("mainMenuButton");
    panel2.add(mainMenu);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the cost value basis page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void displayCostValueBasisFeatures(Feature feature) {
    back.addActionListener(e -> feature.costValueBasis());
    mainMenu.addActionListener(e -> feature.backToMainMenu());
  }


  /**
   * Prints the total portfolio value menu.
   */
  @Override
  public void totalPortfolioValue() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel year = new JLabel("Year");
    year.setFont(new Font("Arial", Font.BOLD, 16));
    year.setForeground(Color.black);
    year.setBackground(Color.black);
    panel2.add(year);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor);
    panel2.add(yearInput);

    JLabel month = new JLabel("Month");
    month.setFont(new Font("Arial", Font.BOLD, 16));
    month.setForeground(Color.black);
    month.setBackground(Color.black);
    panel2.add(month);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date = new JLabel("Date");
    date.setFont(new Font("Arial", Font.BOLD, 16));
    date.setForeground(Color.black);
    date.setBackground(Color.black);
    panel2.add(date);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel3.add(back);

    getTotalPortfolioValue = new JButton("Total Portfolio Value");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    getTotalPortfolioValue.setBorder(compound);
    getTotalPortfolioValue.setActionCommand("totalPortfolioValueButton");
    panel3.add(getTotalPortfolioValue);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the total portfolio value page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void totalPortfolioValueFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    getTotalPortfolioValue.addActionListener(e -> feature.getTotalPortfolioValue(
            portfolioNameInput.getText(),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue())));
  }


  /**
   * Prints the total portfolio value on the UI.
   *
   * @param portfolioValue the portfolio value.
   * @param portfolioName  the name of the portfolio.
   * @param date           the date for which the value is calculated.
   */
  @Override
  public void displayTotalPortfolioValue(double portfolioValue, String portfolioName, String date) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JLabel portfolioValueLabel = new JLabel(
            "Total portfolio value of  " + portfolioName + " on " + date + " = $" + String.format(
                    "%.2f",
                    portfolioValue));
    portfolioValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioValueLabel.setForeground(Color.black);
    portfolioValueLabel.setBackground(Color.black);
    panel1.add(portfolioValueLabel);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border  margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel2.add(back);

    mainMenu = new JButton("Back to Main Menu");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    mainMenu.setBorder(compound);
    mainMenu.setActionCommand("mainMenuButton");
    panel2.add(mainMenu);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the display portfolio value page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void displayTotalPortfolioValueFeatures(Feature feature) {
    back.addActionListener(e -> feature.totalPortfolioValue());
    mainMenu.addActionListener(e -> feature.backToMainMenu());
  }


  /**
   * Prints the portfolio performance menu.
   */
  @Override
  public void portfolioPerformance() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout());
    panel4.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel startDate = new JLabel("Start Date");
    startDate.setFont(new Font("Arial", Font.BOLD, 16));
    startDate.setForeground(Color.black);
    startDate.setBackground(Color.black);
    panel2.add(startDate);

    JLabel year1 = new JLabel("Year");
    panel2.add(year1);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor1);
    panel2.add(yearInput);

    JLabel month1 = new JLabel("Month");
    panel2.add(month1);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date1 = new JLabel("Date");
    panel2.add(date1);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    JLabel endDate = new JLabel("End Date");
    endDate.setFont(new Font("Arial", Font.BOLD, 16));
    endDate.setForeground(Color.black);
    endDate.setBackground(Color.black);
    panel3.add(endDate);
    JLabel year2 = new JLabel("Year");
    panel3.add(year2);
    yearInput2 = new JSpinner(
            new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(yearInput2, "#");
    yearInput2.setEditor(editor2);
    panel3.add(yearInput2);

    JLabel month2 = new JLabel("Month");
    panel3.add(month2);
    monthInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel3.add(monthInput2);

    JLabel date2 = new JLabel("Date");
    panel3.add(date2);
    dateInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel3.add(dateInput2);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel4.add(back);

    getPortfolioPerformance = new JButton("Get Portfolio Performance");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    getPortfolioPerformance.setBorder(compound);
    getPortfolioPerformance.setActionCommand("getPortfolioPerformanceButton");
    panel4.add(getPortfolioPerformance);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    this.add(panel4, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the portfolio performance page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void portfolioPerformanceFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    getPortfolioPerformance.addActionListener(e -> feature.getPortfolioPerformance(
            portfolioNameInput.getText(),
            String.valueOf(((Number) this.yearInput2.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput2.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput2.getValue()).intValue()),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue()), "BAR"));
  }


  /**
   * Prints the portfolio performance helper menu.
   */
  @Override
  public void displayPortfolioPerformance(Map<String, Double> portfolioPerformanceMap,
          String jsonName,
          String startDate, String endDate, double scale) {
    this.getContentPane().removeAll();

    int rows = portfolioPerformanceMap.size() + 2;
    JPanel panel1 = new JPanel(new GridLayout(rows, 1));
    panel1.setSize(1000, 700);
    JLabel title = new JLabel(
            "PERFORMANCE OF PORTFOLIO " + jsonName + " FROM " + startDate + " TO " + endDate);
    title.setFont(new Font("Arial", Font.BOLD, 16));
    title.setForeground(Color.black);
    title.setBackground(Color.black);
    panel1.add(title);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    for (String key : portfolioPerformanceMap.keySet()) {
      if (!key.equals("portfolio_performance_scale")) {
        double value = portfolioPerformanceMap.get(key);
        int asterisksCount = (int) Math.round(value / scale);
        JLabel label = new JLabel(key + ": " + "*".repeat(Math.max(0, asterisksCount)));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.black);
        label.setBackground(Color.black);
        panel1.add(label);
      }
    }

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel2.add(back);

    mainMenu = new JButton("Back to Main Menu");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    mainMenu.setBorder(compound);
    mainMenu.setActionCommand("mainMenuButton");
    panel2.add(mainMenu);

    JLabel scaleLabel = new JLabel("Scale: * = " + scale);
    scaleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    scaleLabel.setForeground(Color.black);
    scaleLabel.setBackground(Color.black);
    panel1.add(scaleLabel);
    this.add(panel1);
    this.add(panel2);
    this.setVisible(true);
  }


  /**
   * The features for the portfolio performance helper page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void displayPortfolioPerformanceFeatures(Feature feature) {
    back.addActionListener(e -> feature.portfolioPerformance());
    mainMenu.addActionListener(e -> feature.backToMainMenu());
  }


  /**
   * Prints the menu for the dollar cost averaging page.
   */
  @Override
  public void dollarCostAveraging() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout());
    panel4.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel investment = new JLabel("Investment Value");
    investment.setFont(new Font("Arial", Font.BOLD, 16));
    investment.setForeground(Color.black);
    investment.setBackground(Color.black);
    panel2.add(investment);
    investmentValueInput = new JSpinner(new SpinnerNumberModel(100, 1, Integer.MAX_VALUE, 100));
    panel2.add(investmentValueInput);

    JLabel interval = new JLabel("Investment Interval (in days)");
    interval.setFont(new Font("Arial", Font.BOLD, 16));
    interval.setForeground(Color.black);
    interval.setBackground(Color.black);
    panel2.add(interval);
    intervalInput = new JSpinner(new SpinnerNumberModel(15, 1, Integer.MAX_VALUE, 1));
    panel2.add(intervalInput);

    ongoing = new JCheckBox("Is the strategy ongoing?", false);
    panel3.add(ongoing);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel4.add(back);

    next = new JButton("Next");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    next.setBorder(compound);
    next.setActionCommand("nextButton");
    panel4.add(next);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    this.add(panel4, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the dollar cost averaging page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void dollarCostAveragingFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    next.addActionListener(e -> feature.dollarCostAveragingGetDataOne(portfolioNameInput.getText(),
            ((Number) this.investmentValueInput.getValue()).doubleValue(),
            ((Number) this.intervalInput.getValue()).intValue(), ongoing.isSelected()));
  }


  /**
   * Print the menu for the dollar cost averaging get data page.
   *
   * @param flag to set the 'Done' button.
   */
  @Override
  public void dollarCostAveragingGetDataOne(boolean flag) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel stockName = new JLabel("Stock Name");
    stockName.setFont(new Font("Arial", Font.BOLD, 16));
    stockName.setForeground(Color.black);
    stockName.setBackground(Color.black);
    panel1.add(stockName);
    stockNameInput = new JTextField(10);
    panel1.add(stockNameInput);

    JLabel commission = new JLabel("Commission");
    commission.setFont(new Font("Arial", Font.BOLD, 16));
    commission.setForeground(Color.black);
    commission.setBackground(Color.black);
    panel2.add(commission);
    commissionInput = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 0.5));
    panel2.add(commissionInput);

    JLabel weightage = new JLabel("Weightage");
    weightage.setFont(new Font("Arial", Font.BOLD, 16));
    weightage.setForeground(Color.black);
    weightage.setBackground(Color.black);
    panel2.add(weightage);
    weightageInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.5));
    panel2.add(weightageInput);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel3.add(back);

    addAnother = new JButton("Add Another");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    addAnother.setBorder(compound);
    addAnother.setActionCommand("addAnotherButton");
    panel3.add(addAnother);

    if (flag) {
      done = new JButton("Done");
      done.setActionCommand("doneButton");
      panel3.add(done);
    }

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the dollar cost averaging get data page. Helps communicate with the
   * controller.
   *
   * @param feature an object of the controller.
   * @param flag    to set the 'Done' button
   */
  @Override
  public void dollarCostAveragingGetDataOneFeatures(Feature feature, boolean flag) {
    back.addActionListener(e -> feature.dollarCostAveraging());
    addAnother.addActionListener(e -> feature.dollarCostAveragingSetDataOne(
            stockNameInput.getText(),
            ((Number) this.commissionInput.getValue()).doubleValue(),
            ((Number) this.weightageInput.getValue()).doubleValue()));
    if (flag) {
      done.addActionListener(e -> feature.dollarCostAveragingGetDataTwo());
    }
  }


  /**
   * Prints the menu for the dollar cost averaging get data helper page.
   *
   * @param isOngoing to determine if a strategy has an end date.
   */
  @Override
  public void dollarCostAveragingGetDataTwo(boolean isOngoing) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JLabel startDate = new JLabel("Start Date");
    startDate.setFont(new Font("Arial", Font.BOLD, 16));
    startDate.setForeground(Color.black);
    startDate.setBackground(Color.black);
    panel1.add(startDate);

    JLabel year1 = new JLabel("Year");
    panel1.add(year1);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    NumberEditor editor1 = new NumberEditor(yearInput, "#");
    yearInput.setEditor(editor1);
    panel1.add(yearInput);

    JLabel month1 = new JLabel("Month");
    panel1.add(month1);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel1.add(monthInput);

    JLabel date1 = new JLabel("Date");
    panel1.add(date1);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel1.add(dateInput);

    if (!isOngoing) {
      JLabel endDate = new JLabel("End Date");
      endDate.setFont(new Font("Arial", Font.BOLD, 16));
      endDate.setForeground(Color.black);
      endDate.setBackground(Color.black);
      panel2.add(endDate);
      JLabel year2 = new JLabel("Year");
      panel2.add(year2);
      yearInput2 = new JSpinner(
              new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
                      Calendar.getInstance().get(Calendar.YEAR), 1));
      JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(yearInput2, "#");
      yearInput2.setEditor(editor2);
      panel2.add(yearInput2);

      JLabel month2 = new JLabel("Month");
      panel2.add(month2);
      monthInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
      panel2.add(monthInput2);

      JLabel date2 = new JLabel("Date");
      panel2.add(date2);
      dateInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
      panel2.add(dateInput2);
    }

    done = new JButton("Done");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    done.setBorder(compound);
    done.setActionCommand("doneButton");
    panel3.add(done);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    if (!isOngoing) {
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 5;
      this.add(panel2, gridBagConstraints);
    }

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    this.add(panel3, gridBagConstraints);

    this.setVisible(true);
  }


  /**
   * The features for the dollar cost averaging get data helper page. Helps communicate with the
   * controller.
   *
   * @param feature   an object of the controller.
   * @param isOngoing to determine if a strategy has an end date.
   */
  @Override
  public void dollarCostAveragingGetDataTwoFeatures(Feature feature, boolean isOngoing) {
    if (isOngoing) {
      done.addActionListener(e -> feature.dollarCostAveragingSetDataTwo(
              String.valueOf(((Number) this.yearInput.getValue()).intValue()),
              String.valueOf(((Number) this.monthInput.getValue()).intValue()),
              String.valueOf(((Number) this.dateInput.getValue()).intValue()), "", "", ""));
    } else {
      done.addActionListener(e -> feature.dollarCostAveragingSetDataTwo(
              String.valueOf(((Number) this.yearInput.getValue()).intValue()),
              String.valueOf(((Number) this.monthInput.getValue()).intValue()),
              String.valueOf(((Number) this.dateInput.getValue()).intValue()),
              String.valueOf(((Number) this.yearInput2.getValue()).intValue()),
              String.valueOf(((Number) this.monthInput2.getValue()).intValue()),
              String.valueOf(((Number) this.dateInput2.getValue()).intValue())));
    }
  }


  /**
   * Gets the data for a line chart.
   */
  @Override
  public void lineChart() {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new FlowLayout());
    panel3.setSize(1000, 700);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout());
    panel4.setSize(1000, 700);

    JLabel portfolioName = new JLabel("Portfolio Name");
    portfolioName.setFont(new Font("Arial", Font.BOLD, 16));
    portfolioName.setForeground(Color.black);
    portfolioName.setBackground(Color.black);
    panel1.add(portfolioName);
    portfolioNameInput = new JTextField(10);
    panel1.add(portfolioNameInput);

    JLabel startDate = new JLabel("Start Date");
    startDate.setFont(new Font("Arial", Font.BOLD, 16));
    startDate.setForeground(Color.black);
    startDate.setBackground(Color.black);
    panel2.add(startDate);

    JLabel year1 = new JLabel("Year");
    panel2.add(year1);
    yearInput = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
            Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(yearInput, "#");
    yearInput.setEditor(editor1);
    panel2.add(yearInput);

    JLabel month1 = new JLabel("Month");
    panel2.add(month1);
    monthInput = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel2.add(monthInput);

    JLabel date1 = new JLabel("Date");
    panel2.add(date1);
    dateInput = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel2.add(dateInput);

    JLabel endDate = new JLabel("End Date");
    endDate.setFont(new Font("Arial", Font.BOLD, 16));
    endDate.setForeground(Color.black);
    endDate.setBackground(Color.black);
    panel3.add(endDate);
    JLabel year2 = new JLabel("Year");
    panel3.add(year2);
    yearInput2 = new JSpinner(
            new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1999,
                    Calendar.getInstance().get(Calendar.YEAR), 1));
    JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(yearInput2, "#");
    yearInput2.setEditor(editor2);
    panel3.add(yearInput2);

    JLabel month2 = new JLabel("Month");
    panel3.add(month2);
    monthInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    panel3.add(monthInput2);

    JLabel date2 = new JLabel("Date");
    panel3.add(date2);
    dateInput2 = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    panel3.add(dateInput2);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border  margin = new EmptyBorder(5, 15, 5, 15);
    Border  compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel4.add(back);

    getLineChart = new JButton("Get Line Chart");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    getLineChart.setBorder(compound);
    getLineChart.setActionCommand("getLineChartButton");
    panel4.add(getLineChart);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(panel1, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(panel2, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    this.add(panel3, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    this.add(panel4, gridBagConstraints);

    this.setVisible(true);
  }



  /**
   * The features for the get line chart data page. Helps communicate with the controller.
   *
   * @param feature an object of the controller.
   */
  @Override
  public void lineChartFeatures(Feature feature) {
    back.addActionListener(e -> feature.backToMainMenu());
    getLineChart.addActionListener(e -> feature.getPortfolioPerformance(
            portfolioNameInput.getText(),
            String.valueOf(((Number) this.yearInput2.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput2.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput2.getValue()).intValue()),
            String.valueOf(((Number) this.yearInput.getValue()).intValue()),
            String.valueOf(((Number) this.monthInput.getValue()).intValue()),
            String.valueOf(((Number) this.dateInput.getValue()).intValue()), "LINE"));
  }


  /**
   * Prints a line chart on the page.
   *
   * @param portfolioPerformanceMap   the time, price data.
   * @param jsonName                  the portfolio name.
   * @param startDate                 the start date as input by the user.
   * @param endDate                   the end date as input by the user.
   * @param scale                     the scale for our graph.
   */
  @Override
  public void displayPortfolioLineChart(Map<String, Double> portfolioPerformanceMap,
          String jsonName, String startDate, String endDate, double scale) {
    this.getContentPane().removeAll();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setSize(1000, 700);

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.setSize(1000, 700);

    String series = "Portfolio Value";
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (String key : portfolioPerformanceMap.keySet()) {
      if (!key.equals("portfolio_performance_scale")) {
        double value = portfolioPerformanceMap.get(key);
        dataset.addValue(value, series, key);
      }
    }

    String chartTitle = "LINE CHART REPRESENTING VALUE OF " + jsonName + " VS TIME";
    JFreeChart chart = ChartFactory.createLineChart(chartTitle, "Time",
            "Portfolio Value (in $)", dataset);
    ChartPanel chartPanel = new ChartPanel(chart);

    back = new JButton("Back");
    Border line = new LineBorder(Color.BLACK);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    back.setBorder(compound);
    back.setActionCommand("back");
    panel2.add(back);

    mainMenu = new JButton("Back to Main Menu");
    line = new LineBorder(Color.BLACK);
    margin = new EmptyBorder(5, 15, 5, 15);
    compound = new CompoundBorder(line, margin);
    mainMenu.setBorder(compound);
    mainMenu.setActionCommand("mainMenuButton");
    panel2.add(mainMenu);

    panel1.add(chartPanel);

    this.add(panel1);
    this.add(panel2);
    this.setVisible(true);
  }


  @Override
  public void displayPortfolioLineChartFeatures(Feature feature) {
    back.addActionListener(e -> feature.lineChart());
    mainMenu.addActionListener(e -> feature.backToMainMenu());
  }
}
