package Banking;

import java.io.*;

public class CustomerStorage {

	public static double getBalance() throws IOException {
        FileInputStream fin = new FileInputStream("Customer.dat");
        DataInputStream din = new DataInputStream(fin);
        double balance = din.readDouble();
        din.close();
        fin.close();
        return balance;
    }
    
    public static void saveBalance(double balance) throws IOException {
        FileOutputStream fout = new FileOutputStream("Customer.dat");
        DataOutputStream dout = new DataOutputStream(fout);
        dout.writeDouble(balance);
        dout.close();
        fout.close();
    }
}
