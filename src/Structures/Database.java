package Structures;

import java.security.PrivateKey;
import java.sql.*;
import java.util.Scanner;

public class Database {

    private String url = "jdbc:mysql://localhost/new";
    private String user = "root";
    private String password = "ch@ngeme1";
    private Connection conn;

    public void Database(){
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {

            // handle the error
            System.out.println(ex);
        }

        this.conn = null;
        try
        {
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

    }

    public void setConn(){
        try
        {
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public void addAccount(String firstName, String lastName, AccountType accountType, double savings){
        try
        {
            this.setConn();
            Statement st = conn.createStatement();
            String AccountInsertQuery = " insert into account (first_name, last_name, type_id, balance)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement preparedAccountStmt = conn.prepareStatement(AccountInsertQuery);
            preparedAccountStmt.setString (1, firstName);
            preparedAccountStmt.setString (2, lastName);
            if(accountType == AccountType.Standard){preparedAccountStmt.setInt (3, 1);}
            else if(accountType == AccountType.Saver){preparedAccountStmt.setInt (3, 2);}
            else if(accountType == AccountType.Premium){preparedAccountStmt.setInt (3, 3);}
            preparedAccountStmt.setDouble (4, savings);
            preparedAccountStmt.execute();;

            st.close();
            conn.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void listAccounts(){
        try
        {
            this.setConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from  beatifulview1");

            while(rs.next()) {
                String out = String.format("Account Number: " + rs.getString("account_number") +  "\tName: " + rs.getString("first_name") + " " + rs.getString("last_name") + "\tAccount Type: " +  rs.getString("account_type") + "\tBalance: " + rs.getString("balance"));
                System.out.println(out);
            }

            st.close();
            conn.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void withdrawDeposit(){
        Scanner scan = new Scanner(System.in);
        Boolean accountIdIsVerified = false;
        double balance = 0;
        double transactionFee = 0;
        double overdraft = 0;
        int accountId = 0;
        do
        {
            System.out.println("Enter Account ID");
            try
            {
                accountId = scan.nextInt();
                scan.nextLine();
            }catch(Exception e){}
            try
            {
                this.setConn();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from  beatifulview2 where account_number =" + accountId);

                while(rs.next()) {
                    String out = String.format("Account Number: " + rs.getString("account_number") +  "\tName: " + rs.getString("first_name") + " " + rs.getString("last_name") + "\tAccount Type: " +  rs.getString("account_type") + "\tBalance: " + rs.getString("balance") + "\tOverdraft : " + rs.getString("overdraft") + "\tTransaction Fee : " + rs.getString("transaction_fee"));
                    balance = rs.getDouble("balance");
                    transactionFee = rs.getDouble("transaction_fee");
                    overdraft = rs.getDouble("overdraft");
                    System.out.println(out);
                }

                st.close();
                conn.close();
                accountIdIsVerified = true;
            }
            catch(SQLException e) {
                System.out.println(e);
                accountIdIsVerified = false;
            }
        }while(!accountIdIsVerified);
        System.out.println("Press 1: To Withdraw \nPress 2: To Deposit");
        int choose = 0;
        Boolean choice = false;
        do{
            try
            {
                choose = scan.nextInt();
                scan.nextLine();
            }catch(Exception e){}
            if(choose == 1 || choose == 2){choice = true;}
        }while(!choice);
        int savingsChange;
        switch(choose)
        {
            case 1:
                Boolean transVer = false;
                double trans = 0;
                do
                {
                    Boolean inputver = false;
                    do
                    {
                        try
                        {
                            System.out.println("How much would you like to withdraw?");
                            trans = scan.nextDouble();
                            scan.nextLine();
                            if(trans>0 || trans<=balance + overdraft){inputver = true;}
                            //TODO transaction fee and overdraft
                        }catch(Exception e){}
                    }while(!inputver);
                    balance -= trans + transactionFee;
                    this.transcation(accountId, balance);
                    transVer = true;
                }while (!transVer);

                break;
            case 2:
                Boolean transVer2 = false;
                double trans2 = 0;
                do
                {
                    Boolean inputver = false;
                    do
                    {
                        try
                        {
                            System.out.println("How much would you like to deposit");
                            trans2 = scan.nextDouble();
                            scan.nextLine();
                            if(trans2>0){inputver = true;}
                            //TODO transaction fee and overdraft
                        }catch(Exception e){}
                    }while(!inputver);
                    balance += trans2;
                    this.transcation(accountId, balance);
                    transVer2 = true;
                }while (!transVer2);

                break;
        }
    }

    public void transcation(int accountID, double balance){
        try
        {
            this.setConn();
            Statement st = conn.createStatement();
            String AccountInsertQuery = " update account set balance = "
                    + balance + " where account_number = " + accountID;
            PreparedStatement preparedAccountStmt = conn.prepareStatement(AccountInsertQuery);
            preparedAccountStmt.execute();;

            st.close();
            conn.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
