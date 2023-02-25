package Banking;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class TellerGUI1 implements ActionListener, ItemListener {

	private JButton bn1, bn2, bn3;
	private JTextField tf1, tf2, tf3;
	private JLabel l1, l2, l3, l4;
	private JTextField err;
	private JPanel p1, p2, p3;
	private JFrame fr;
	private Choice list;
	private static Customer cust;
	int i = 1;
	
	File f = new File("Customer.dat");
	
	public static void main(String[] args) throws IOException {
		Customer cust = new Customer("Yaowares", "Huadkhuntod");
		TellerGUI1 tl = new TellerGUI1(cust);
		
		

		
		tl.init();
	}

	public TellerGUI1(Customer c) {

		cust = c;

	}

	public TellerGUI1() {

	}

	public void init() throws IOException {
		
		 if (f.exists()) {
		        // กรณีมีไฟล์ Customer.dat อยู่ให้อ่านยอดเงินคงเหลือจากไฟล์
		        double initAmount = CustomerStorage.getBalance();
		      
				Account ac = new SavingAccount(initAmount);
				cust.addAccount(ac);
				double initAmount2 = CustomerStorage.getBalance();
				Account ac1 = new SavingAccount(initAmount2);
				cust.addAccount(ac1);
				double initAmount3 = CustomerStorage.getBalance();
				Account ac2 = new SavingAccount(initAmount3);
				cust.addAccount(ac2);
		        
		    } else {
		        // กรณีไม่มีไฟล์ Customer.dat อยู่ให้สร้าง Account ใหม่ที่มีเงินเปิดบัญชี 4,000 บาท
		    	Account ac = new SavingAccount(4000);
				cust.addAccount(ac);
				Account ac2 = new SavingAccount(4000);
				cust.addAccount(ac2);
				Account ac3 = new SavingAccount(4000);
				cust.addAccount(ac3);
		    }

		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		err = new JTextField();

		l1 = new JLabel("Name : ");
		l1.setForeground(new Color(0, 95, 115));
		l2 = new JLabel("Account : ");
		l2.setForeground(new Color(33, 158, 188));
		l3 = new JLabel("Balance : ");
		l3.setForeground(new Color(10, 147, 150));
		l4 = new JLabel("Amount : ");
		l4.setForeground(new Color(34, 87, 122));

		bn1 = new JButton("Deposit");
		bn1.setBackground(new Color(29, 53, 87));
		bn1.setForeground(new Color(241, 250, 238));
		bn2 = new JButton("Withdraw");
		bn2.setBackground(new Color(69, 123, 157));
		bn2.setForeground(new Color(241, 250, 238));
		bn3 = new JButton("Exit");
		bn3.setBackground(new Color(230, 57, 70));
		bn3.setForeground(new Color(241, 250, 238));


		bn1.addActionListener(this);
		bn2.addActionListener(this);
		bn3.addActionListener(this);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		list = new Choice();
		list.setBackground(new Color(168, 218, 220));
		list.addItemListener(this);
		list.add("1");
		list.add("2");
		list.add("3");

		p1.setLayout(new GridLayout(4, 10));
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(list);
		p1.add(l3);
		p1.add(tf2);
		p1.add(l4);
		p1.add(tf3);

		p2.add(bn1);
		p2.add(bn2);
		p2.add(bn3);

		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.SOUTH);

		fr = new JFrame("Banking");
		fr.setSize(300, 600);
		fr.setLayout(new BorderLayout(10,2));
		

		
		fr.add(p3, BorderLayout.CENTER);
		fr.add(err, BorderLayout.SOUTH);
		

		tf2.setText(cust.getAccount(i-1).getBalance() + "");
		tf1.setText(cust.getFirstName() + " " + cust.getLastName());

		fr.setLocationRelativeTo(null);

		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		fr.getContentPane().add(p3, BorderLayout.CENTER);
		fr.getContentPane().add(err, BorderLayout.SOUTH);

		tf1.setEditable(false);
		tf2.setEditable(false);

		err.setEditable(false);
		err.setBackground(new Color(168, 218, 220));

		tf2.setText(cust.getAccount(i-1).getBalance() + "");
		tf1.setText(cust.getFirstName() + " " + cust.getLastName());
		
		

		
		

		fr.pack();
		fr.show();

	}
	public void itemStateChanged1(ItemEvent e) {
		// TODO Auto-generated method stub
		i = Integer.parseInt(list.getSelectedItem());
	}

	public void itemStateChanged(ItemEvent e) {

		i = Integer.parseInt(list.getSelectedItem());

		tf2.setText(cust.getAccount(i-1).getBalance() + "");

	}
	public void actionPerformed(ActionEvent ev) {

		String cmd = ev.getActionCommand();
		double amt;
		try {

			if (cmd.equals("Exit")) {
				CustomerStorage.saveBalance(cust.getAccount(i-1).getBalance());
				System.exit(0);
			}

			else if (cmd.equals("Withdraw")) {
				amt = Double.parseDouble(tf3.getText());
				try {
					cust.getAccount(i-1).withdraw(amt);
					err.setText("successful withdraw");
					tf3.setText(" ");
				} catch (WithdrawException ex) {
					err.setText("Not enough money");
					tf3.setText(" ");

				}

			} else if (cmd.equals("Deposit")) {
				amt = Double.parseDouble(tf3.getText());
				cust.getAccount(i-1).deposit(amt);
				err.setText("successful deposit");
				tf3.setText(" ");

			}
			tf2.setText(cust.getAccount(i-1).getBalance() + " ");
		} catch (NumberFormatException e) {
			err.setText("Enter numbers ");
			tf3.setText(" ");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
