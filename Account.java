package stage2_Kim_Khanh_Do;

public class Account {
	private String userName;
	private String cardNo;
	private String PIN;
	private double balance;

	public Account(String userName, String cardNo, String PIN, double balance) {
		this.userName = userName;
		this.cardNo = cardNo;
		this.PIN = PIN;
		this.balance = balance;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String PIN) {
		this.PIN = PIN;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void showBalance() {
		System.out.println("Your balance: " + this.balance + "$");
		System.out.println("---------------------");
	}

	public void deposit(double amount) {
		System.out.println("Deposit Successful");
		this.balance += amount;
		System.out.println("Your balance: " + this.balance + "$");
		System.out.println("---------------------");
	}

	public boolean validateBalance(double amount) {
		if (this.balance < amount) {
			return false;
		}
		return true;
	}

	public void withdraw(double amount) {
		if (validateBalance(amount)) {
			System.out.println("Withdrawal Successful");
			this.balance -= amount;
			System.out.println("Your Balance: " + this.balance + "$");
			System.out.println("---------------------");
		} else {
			System.out.println("Invalid amount to withdraw. Your balance: " + this.balance + "$");
			System.out.println("---------------------");
		}
	}

	@Override
	public String toString() {
		return userName;
	}

}
