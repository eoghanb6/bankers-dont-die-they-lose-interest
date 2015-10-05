import java.util.ArrayList;
import java.util.Scanner;
import Structures.*;


public class Main {

    public static ArrayList<Account> accountList = new ArrayList<Account>();

    public static void main(String[] args) {
        System.out.println("Welcome to Bank");
        while(true){
        menuChoice();}
    }

    public static void menuChoice(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Press 1 to create account, press 2 to list accounts, press 3 to sign in, press 4 to exit");
        int menuChoice = 0;
        try
        {
            menuChoice = myScanner.nextInt();
        }
        catch (Exception Error)
        {
            System.out.println("Please enter a suitable value");
        }

        switch (menuChoice)
        {
            case 1:

            //Create Account


                //Add First and Second name
                System.out.println("Enter First Name");
                String firstName = myScanner.nextLine();
                myScanner.nextLine();
                System.out.println("Enter Last Name");
                String lastName = myScanner.nextLine();

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
                    String input = myScanner.nextLine();
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
                switch(accountType){
                    case Standard: accountList.add( new Standard((accountList.size()+1) ,firstName, lastName, savings));
                        break;
                    case Saver: accountList.add( new Saver((accountList.size()+1) ,firstName, lastName, savings));
                        break;
                    case Premium: accountList.add( new Premium((accountList.size()+1) ,firstName, lastName, savings));
                        break;
                }
                System.out.println(accountList.get(accountList.size() - 1).getAccountInfo());
                break;
            case 2:
                for(Account account: accountList){
                    System.out.println(account.getAccountInfo());
                }
                System.out.println("Press any key to go back to the menu");
                String pass = myScanner.nextLine();
                myScanner.nextLine();
                break;
            case 3:
                Boolean accountNumberIsValid = false;
                String accountNumberError = "Please enter a valid number";
                do{
                    Scanner AccountNumberScanner = new Scanner(System.in);
                    System.out.println("Please enter your account number");
                    int inputAccount = 0;
                    try
                    {
                        inputAccount = AccountNumberScanner.nextInt();
                        AccountNumberScanner.nextLine();
                    }
                    catch(Exception e)
                    {
                        accountNumberError = "Please enter a number";
                    }
                    int input = 0;
                        for(Account account: accountList)
                        {
                            if (inputAccount == account.getaccountnumber())
                            {
                                Boolean choiceIsValid = false;
                                accountNumberIsValid = true;
                                do{

                                    Scanner InputNumberScanner = new Scanner(System.in);
                                    System.out.println("Account number: " + inputAccount + ". Press 1 to withdraw cash, press 2 to deposit cash");
                                    try
                                    {
                                        input=  InputNumberScanner.nextInt();
                                        InputNumberScanner.nextLine();
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("Please enter a number");
                                    }

                                    if (input == 1 || input== 2)
                                    {
                                       choiceIsValid = true;
                                    }
                                }while(!choiceIsValid);

                                switch(input)
                                {
                                    case 1:
                                        Boolean withdrawIsValid = false;
                                        do{
                                            Scanner TransactionScanner = new Scanner(System.in);
                                            System.out.println("how much would you like to withdraw?");
                                            double amount = 0;
                                            try
                                            {
                                                amount =  TransactionScanner.nextDouble();
                                                TransactionScanner.nextLine();
                                                try
                                                {
                                                    System.out.println(account.withdraw(amount));
                                                    withdrawIsValid = true;
                                                }
                                                catch (Exception e)
                                                {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            catch(Exception e)
                                            {
                                                System.out.println("Please enter a number");
                                            }
                                        }while(!withdrawIsValid);
                                        break;
                                    case 2:
                                        Boolean depositIsValid = false;
                                        do{
                                            Scanner TransactionScanner = new Scanner(System.in);
                                            System.out.println("how much would you like to deposit?");
                                            double amount = 0;
                                            try
                                            {
                                                amount =  TransactionScanner.nextDouble();
                                                TransactionScanner.nextLine();
                                                try
                                                {
                                                    System.out.println(account.deposit(amount));
                                                    depositIsValid = true;
                                                }
                                                catch (Exception e)
                                                {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            catch(Exception e)
                                            {
                                                System.out.println("Please enter a number");
                                            }
                                        }while(!depositIsValid);
                                        break;
                                    default: System.out.print("Please enter a valid number");
                                }
                            }
                        }
                        System.out.println(accountNumberError);
                }while(!accountNumberIsValid);
                break;
            case 4:
                System.out.println("Bye");
                System.exit(2);
                break;
            default:
                System.out.println("Please enter a suitable value");
                break;
        }
    }
}













