package stage2_Kim_Khanh_Do;

import java.util.ArrayList;

public class Bank {

	private ArrayList<Account> accounts;

	public Bank(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public Account findAccount(String cardNo, String PIN) {
		for (Account account : accounts) {
			if (account.getCardNo().equals(cardNo) && account.getPIN().equals(PIN)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String result = "------Sample Profiles For Testing Purpose------\n";
		for (Account account : accounts) {
			result += "Name: " + account.getUserName() + ", Card No: " + account.getCardNo() + ", PIN: "
					+ account.getPIN() + ", Balance: " + account.getBalance() + "\n";
		}
		return result;

	}

}
