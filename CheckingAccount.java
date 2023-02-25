package Banking;

public class CheckingAccount extends Account {

	private double credit;

	public CheckingAccount(double amount, double credit) {

		super(amount);
		balance = amount;
		this.credit = credit;

	}

	CheckingAccount() {

	}

	public double getCredit() {

		return credit;

	}

	public void setCredit(double credit) {

		this.credit = credit;

	}

}
