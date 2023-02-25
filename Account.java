package Banking;

public class Account {

	protected double balance;

	Account() {

	}

	public Account(double amount) {

		balance = amount;

	}

	public void deposit(double amount) {

		balance = (balance + amount);

	}

	public void withdraw(double amount) throws WithdrawException {

		if (balance >= amount) {
			balance = balance - amount;
		} else {
			throw new WithdrawException();
		}

	}

	public void showBalance() {

		System.out.println(balance);

	}

	public double getBalance() {

		return balance;

	}

}
