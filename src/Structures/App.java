package Structures;

import java.sql.*;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {

            // handle the error
            System.out.println(ex);
        }

        Connection conn = null;


        while(true)
        {
            System.out.println("Press 1: Add Account \nPress 2: List Accounts");
            Scanner scan = new Scanner(System.in);
            int menuChoice = 0;
            try
            {
                menuChoice = scan.nextInt();
                scan.nextLine();
            }catch(Exception e){}
            switch(menuChoice)
            {
                case 1:
                    System.out.println("Enter First Name");
                    String firstName = scan.nextLine();
                    System.out.println("Enter Last Name");
                    String lastName = scan.nextLine();

                    //Add Account Type
                    AccountType accountType = null;
                    do
                    {
                        Scanner accountTypeChoiceScanner = new Scanner(System.in);
                        System.out.println("Choose account type");
                        System.out.println("1: Standard \t Overdraft: 500;");
                        System.out.println("2: Saver \t \t Overdraft: 0");
                        System.out.println("3: Premium \t \t Overdraft: 3000");
                        int choice = 0;
                        String accountTypeErrorMessage = "Please enter a suitable value";
                        try
                        {
                            choice = accountTypeChoiceScanner.nextInt();
                            accountTypeChoiceScanner.nextLine();
                        }
                        catch (Exception Error)
                        {
                        }
                        switch(choice)
                        {
                            case 1: accountType = AccountType.Standard;
                                break;
                            case 2: accountType = AccountType.Saver;
                                break;
                            case 3: accountType = AccountType.Premium;
                                break;
                        }
                        if (choice>3 || choice < 1)
                        {
                            System.out.println(accountTypeErrorMessage);
                        }
                    }while(accountType == null);
                    double savings = 0;
                    Boolean initialSavingsIsValid = false;
                    do{
                        System.out.println("Add initial savings or press enter for no savings");
                        String input = scan.nextLine();
                        savings = 0;
                        try
                        {
                            savings = Double.parseDouble(input);
                            initialSavingsIsValid = true;
                        }
                        catch(Exception e)
                        {
                            if (input.equals(""))
                            {
                                initialSavingsIsValid = true;
                            }
                            else
                            {
                                System.out.println("Please enter a suitable value");
                            }
                        }
                        if (savings < 0)
                        {
                            System.out.println("Must be greater than 0");
                            initialSavingsIsValid = false;
                        }
                    }while(!initialSavingsIsValid);

                    try
                    {
                        String url = "jdbc:mysql://localhost/new";
                        String user = "root";
                        String password = "ch@ngeme1";

                        conn = DriverManager.getConnection(url, user, password);

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

                    break;
                case 2:

                    try
                    {
                        String url = "jdbc:mysql://localhost/new";
                        String user = "root";
                        String password = "ch@ngeme1";

                        conn = DriverManager.getConnection(url, user, password);

                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("select * from  account");

                        while(rs.next()) {
                            String out = String.format("Account Number: " + rs.getString("account_number") +  "\tName: " + rs.getString("first_name") + " " + rs.getString("last_name") + "\tAccount Type ID: " +  rs.getString("type_id") + "\tBalance: " + rs.getString("balance"));
                            System.out.println(out);
                        }

                        st.close();
                        conn.close();
                    }
                    catch(SQLException e) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    System.out.println("Enter Account ID");
                    Boolean accountIdIsVerified = false;
                    int accountId;
                    double balance = 0;
                    do
                    {
                        try
                        {
                            accountId = scan.nextInt();
                            scan.nextLine();
                            try
                            {
                                String url = "jdbc:mysql://localhost/new";
                                String user = "root";
                                String password = "ch@ngeme1";

                                conn = DriverManager.getConnection(url, user, password);

                                Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select * from  account where account_id =" + accountId);

                                while(rs.next()) {
                                    String out = String.format("Account Number: " + rs.getString("account_number") +  "\tName: " + rs.getString("first_name") + " " + rs.getString("last_name") + "\tAccount Type ID: " +  rs.getString("type_id") + "\tBalance: " + rs.getString("balance"));
                                    balance = rs.getDouble("balance");
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

                        }catch(Exception e){}

                    }while(!accountIdIsVerified);
                    Boolean choice = false;
                    int choose = 0;
                    do
                    {
                        System.out.println("Press 1: To Withdraw \n Press 2: To Deposit");
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
                            System.out.println("How much would you like to withdraw?");

                            break;
                        case 2:
                            System.out.println("How much would you like to deposit?");
                            break;
                    }

            }
        }

    }
}