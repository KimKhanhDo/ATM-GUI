package stage2_Kim_Khanh_Do;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMGUI extends JFrame implements ActionListener {

	// Banking system components
	private Bank bank;
	private Account currentAccount;

	// GUI components for user input
	private JTextField cardNumberField, depositAmountField, withdrawAmountField;
	private JPasswordField pinField;

	// Labels to display information and instructions
	private JLabel statusLabel, balanceLabel;

	// Tabbed pane to organize different sections of the GUI
	private JTabbedPane tabbedPane;

	// Panels for different functional areas
	private JPanel loginPanel, balancePanel, depositPanel, withdrawPanel;

	// Buttons for user actions
	private JButton loginButton, depositButton, withdrawButton;

	// Counter for failed login attempts
	private int failedLoginAttempts = 0;

	// Constructs the user interface and initializes the GUI components
	public ATMGUI() {
		setTitle("ATM Machine");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center on screen

		initializeBank(); // Initialize bank data
		initializeComponents(); // Set up GUI components
		setVisible(true); // Make window visible
	}

	public static void main(String[] args) throws Exception {
		new ATMGUI();
	}

	// Sample data.

	private void initializeBank() {
		ArrayList<Account> accounts = new ArrayList<>();
		accounts.add(new Account("Jacqui Le", "12345", "111", 1265));
		accounts.add(new Account("Jamie Do", "12346", "222", 2200));
		accounts.add(new Account("Sam Martin", "12347", "333", 58.9));
		accounts.add(new Account("Kim Do", "12348", "444", 750));
		bank = new Bank(accounts);

		// use toString method to print the sample data on the console
		System.out.println(bank);
	}

	// Sets up the components of the GUI.
	private void initializeComponents() {

		// Grid Layout for login area
		loginPanel = new JPanel(new GridLayout(3, 2));
		cardNumberField = new JTextField();
		pinField = new JPasswordField();
		loginButton = new JButton("Login");
		statusLabel = new JLabel("Enter card number and PIN.");

		// Adding components to the login panel
		loginPanel.add(new JLabel("Card Number:"));
		loginPanel.add(cardNumberField);
		loginPanel.add(new JLabel("PIN:"));
		loginPanel.add(pinField);
		loginPanel.add(loginButton);
		loginPanel.add(statusLabel);

		// Setup of tabbed pane for account management
		tabbedPane = new JTabbedPane();
		balancePanel = new JPanel();
		balanceLabel = new JLabel("Balance will be shown here.");
		balancePanel.add(balanceLabel);

		// Setup deposit panel
		depositPanel = new JPanel();
		depositAmountField = new JTextField(10);
		depositButton = new JButton("Deposit");
		depositPanel.add(new JLabel("Amount to deposit:"));
		depositPanel.add(depositAmountField);
		depositPanel.add(depositButton);

		// Setup withdrawal panel
		withdrawPanel = new JPanel();
		withdrawAmountField = new JTextField(10);
		withdrawButton = new JButton("Withdraw");
		withdrawPanel.add(new JLabel("Amount to withdraw:"));
		withdrawPanel.add(withdrawAmountField);
		withdrawPanel.add(withdrawButton);

		// Add tabs to tabbed pane
		tabbedPane.addTab("Balance", balancePanel);
		tabbedPane.addTab("Deposit", depositPanel);
		tabbedPane.addTab("Withdraw", withdrawPanel);

		// Add main components to the frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(loginPanel, BorderLayout.NORTH);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(false); // Initially hide tabs until login

		// Register action listeners for buttons
		loginButton.addActionListener(this);
		depositButton.addActionListener(this);
		withdrawButton.addActionListener(this);
	}

	// Implements ActionListener to handle action events from user interactions.
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			performLogin();
		} else if (e.getSource() == depositButton) {
			depositFunds();
		} else if (e.getSource() == withdrawButton) {
			withdrawFunds();
		}
	}

	// Processes login attempts and handles login functionality.
	public void performLogin() {
		if (failedLoginAttempts >= 3) {
			JOptionPane.showMessageDialog(this, "ATM is locked due to too many failed attempts.");
			return;
		}

		String cardNo = cardNumberField.getText();
		String pin = new String(pinField.getPassword());
		currentAccount = bank.findAccount(cardNo, pin);
		if (currentAccount != null) {
			loginPanel.setVisible(false);
			tabbedPane.setVisible(true);
			updateBalanceDisplay();
			JOptionPane.showMessageDialog(this, "Hello " + currentAccount); // Welcome message using toString
			failedLoginAttempts = 0; // Reset counter on successful login
		} else {
			failedLoginAttempts++;
			statusLabel.setText("Login Failed. Try again. Attempts left: " + (3 - failedLoginAttempts));
			if (failedLoginAttempts >= 3) {
				loginButton.setEnabled(false); // Disable login after 3 attempts
				JOptionPane.showMessageDialog(this, "ATM is locked due to too many failed attempts.");
			}
		}
	}

	// Updates the displayed balance of the current account.
	public void updateBalanceDisplay() {
		balanceLabel.setText("Current Balance: " + currentAccount.getBalance() + "$");
	}

	// Processes deposit transactions and updates the account balance.
	public void depositFunds() {
		try {
			double amount = Double.parseDouble(depositAmountField.getText());
			currentAccount.deposit(amount);
			updateBalanceDisplay();
			JOptionPane.showMessageDialog(this, "Deposit Successful");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
		}
	}

	// Processes withdrawal transactions and updates the account balance.
	public void withdrawFunds() {
		try {
			double amount = Double.parseDouble(withdrawAmountField.getText());
			if (currentAccount.validateBalance(amount)) {
				currentAccount.withdraw(amount);
				updateBalanceDisplay();
				JOptionPane.showMessageDialog(this, "Withdrawal Successful");
			} else {
				JOptionPane.showMessageDialog(this, "Invalid amount to withdraw");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
		}
	}

}
