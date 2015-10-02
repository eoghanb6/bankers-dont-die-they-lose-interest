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
        try{
        menuChoice = myScanner.nextInt(); }
        catch (Exception Error){
            System.out.println("Please enter a suitable value");
        }
        switch (menuChoice) {
            case 1:
                createAccount();
                break;
            case 2:
                listAccounts();
                break;
            case 3:
                accountsearch();
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
    public static void createAccount(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter First Name");
        String firstName = myScanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = myScanner.nextLine();
        AccountType accountType = null;
        while(accountType == null){
        accountType = makeAccountType();}
        double savings = 0;
        Boolean foo = false;
        while(!foo) {
        try{savings = addSavings();
            switch(accountType){
                case Standard: accountList.add( new Standard((accountList.size()+1) ,firstName, lastName, savings));
                    break;
                case Saver: accountList.add( new Saver((accountList.size()+1) ,firstName, lastName, savings));
                    break;
                case Premium: accountList.add( new Premium((accountList.size()+1) ,firstName, lastName, savings));
                    break;
            }
            System.out.println(accountList.get(accountList.size() - 1).getAccountInfo());
            foo = true;
        }
        catch(Exception e){foo = false;}
        }
    }
    public static AccountType makeAccountType(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Choose account type");
        System.out.println("1: Standard \t Overdraft: 500;");
        System.out.println("2: Saver \t \t Overdraft: 0");
        System.out.println("3: Premium \t \t Overdraft: 3000");
        int choice = 0;
        AccountType accountType = null;
        try{
            choice = myScanner.nextInt();
        }
        catch (Exception Error){
            System.out.println("Please enter a suitable value");
        }
        switch(choice){
            case 1: accountType = AccountType.Standard;
                break;
            case 2: accountType = AccountType.Saver;
                break;
            case 3: accountType = AccountType.Premium;
                break;
        }
        if (choice>3){
            System.out.println("Please enter a suitable value");
            }
        return accountType;
    }
    public static double addSavings() throws Exception{
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Add initial savings or press enter for no savings");
        String input = myScanner.nextLine();
        double savings = 0;
        try{
            savings = Double.parseDouble(input);
        }
        catch(Exception e){
            if (input.equals("")){return 0;}
            else {
                System.out.println("Please enter a suitable value");
                throw new Exception(); }
        }
        if (savings < 0){
            System.out.println("Must be greater than 0");
            throw new Exception();}
        else{
        return savings;}
    }
    public static void listAccounts(){
        for(Account account: accountList){
            System.out.println(account.getAccountInfo());
        }
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Press any key to go back to the menu");
        String pass = myScanner.nextLine();
    }
    public static void accountsearch(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter your account number");
        int inputAccount = 0;
        try{
        inputAccount = myScanner.nextInt();}
        catch(Exception e){System.out.print("Please enter a number. ");}
        Boolean success = false;
        for(Account account: accountList) {
            if (inputAccount == account.getaccountnumber())  {
               while(!success){
               try{ success = choose(success, inputAccount, account);}
               catch(Exception e){System.out.println("Not a valid account number");
                   accountsearch();}
            }}
        }
        //{System.out.println("Not a valid account number");}

 }
    public static Boolean choose(Boolean worked, int inputAccount, Account account) throws Exception{

        System.out.println("Account number: " + inputAccount + ". Press 1 to withdraw cash, press 2 to deposit cash");
        Scanner myScanner = new Scanner(System.in);
        int pass = 0;
        try{pass=  myScanner.nextInt();}
        catch(Exception e){
            System.out.println("Please enter a number");
            }
        Boolean success = false;
        if (pass == 1)  {
           while(!success){
           success = withdrawMethod(success, account);}
           worked = true;
        }
        else if(pass == 2){
           while(!success){
           success = depositmethod(success, account);}
           worked = true;}
        return worked;
        }


    public static Boolean withdrawMethod(Boolean success, Account account){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("how much would you like to withdraw?");
        double amount = 0;
        try{amount =  myScanner.nextDouble();
            try {
                System.out.println(account.withdraw(amount));
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        catch(Exception e){System.out.println("Please enter a number");
        }
        return success;
    }
    public static Boolean depositmethod(Boolean success, Account account) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("how much would you like to deposit?");
        double amount = 0;
        try{amount =  myScanner.nextDouble();
            try {
                System.out.println(account.deposit(amount));
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        catch(Exception e){System.out.println("Please enter a number");
        }
        return success;
    }
}













