package Structures;

import java.sql.*;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) {

        Database db = new Database();
        while(true)
        {
            System.out.println("Press 1: Add Account \nPress 2: List Accounts \nPress 3: Log in to Withdraw or Deposit");
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

                    db.addAccount(firstName,lastName,accountType,savings);

                    break;
                case 2:
                    db.listAccounts();
                    break;
                case 3:

                    db.withdrawDeposit();

                            break;

            }
        }

    }
}