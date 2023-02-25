package Banking;

import java.util.ArrayList;

public class Customer {

	private String firstName;

	private String lastName;

	private ArrayList<Account> acct;

	private int numOfAccount = 0;

	public Customer() {
		acct = new ArrayList<Account>();
	}

	public Customer(String fName, String lName) {

		firstName = fName;
		lastName = lName;
		acct = new ArrayList<Account>();

	}

	public String getFirstName() {

		return firstName;

	}

	public void setFirstName(String fName) {

		firstName = fName;

	}

	public String getLastName() {

		return lastName;

	}

	public void setLastName(String lName) {

		lastName = lName;

	}

	public void addAccount(Account acct) {

		if (acct instanceof CheckingAccount) {

			this.acct.add((CheckingAccount) acct);

		} else if (acct instanceof SavingAccount) {

			this.acct.add((SavingAccount) acct);

		} else {

			this.acct.add((Account) acct);
		}
		numOfAccount++;

	}

	public int getNumOfAccount() {
		return numOfAccount;
	}

	public Account getAccount(int index) {
		if (acct.get(index) == null) {
			return acct.get(0);
		}
		return acct.get(index);
	}

	public void printReport() {
		System.out.println(firstName + " " + lastName + "");
		for (int i = 0; i < numOfAccount; i++) {
			if (acct.get(i) instanceof CheckingAccount) {
				System.out.println(
						"Checking Account " + (i + 1) + " : credit = " + ((CheckingAccount) acct.get(i)).getCredit()
								+ " current balance = " + ((CheckingAccount) acct.get(i)).getBalance());
			} else if (this.acct.get(i) instanceof SavingAccount) {
				System.out.println("Saving Account " + (i + 1) + " : current balance = "
						+ ((SavingAccount) acct.get(i)).getBalance());
			} else {
				System.out.println(
						"Saving Account " + (i + 1) + " : current balance = " + ((Account) acct.get(i)).getBalance());
			}
		}
	}

	@Override
	public String toString() {

		return String.format(firstName + " " + lastName + "");

	}

}
