import java.util.ArrayList;
import java.util.Scanner;
import Structures.*;


public class Main {

    public static ArrayList<Account> accountList = new ArrayList<Account>();

    public static void main(String[] args) {
        System.out.println("Welcome to Bank");
        menuChoice();
    }
    public static void menuChoice(){
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Press 1 to create account, press 2 to list accounts, press 3 to sign in, press 4 to exit");
        int menuChoice1 = 0;
        try{
        menuChoice1 = myScanner.nextInt(); }
        catch (Exception Error){
            System.out.println("Please enter a suitable value");
            menuChoice();
        }

        switch (menuChoice1) {
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
                menuChoice();
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
        while(!foo) { // "I PITY THE FOO" - Mr T
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
        menuChoice();
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

        menuChoice();
    }
    public static void accountsearch(){
        boolean f = true;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter your account number");
        int inputAccount = myScanner.nextInt();
        for(Account account: accountList) {

            if (inputAccount == account.getaccountnumber())  {
               f = true;
               boolean foo = false;
               while(!foo){
               foo = choose(foo, inputAccount, account);
               }
                break;

            }
            f = false;

        }
        if(f = false){System.out.println("Not a valid account number");
        accountsearch();}
 }
    public static Boolean choose(Boolean foo, int inputAccount, Account account){

        System.out.println("Account number: " + inputAccount + ". Press 1 to withdraw cash, press 2 to deposit cash");
        Scanner myScanner = new Scanner(System.in);
        int pass = 0;
        Boolean s = false;
        try{pass=  myScanner.nextInt();}
        catch(Exception e){
            System.out.println("Please enter a number");
            foo = false;}
        if (pass == 1)  {
            while(!s)  {
            s = withdrawMethod(s, account);}
            foo = true;
        }
        else if(pass == 2) {
            while(!s){
            s = depositmethod(s, account);}
            foo = true;
        }
        else{foo = false;}
        return foo;
    }
    public static Boolean withdrawMethod(Boolean s, Account account){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("how much would you like to withdraw?");
        double amount = 0;
        try{amount =  myScanner.nextDouble();
            try {
                System.out.println(account.withdraw(amount));
                s = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s = false;
            }}
        catch(Exception e){System.out.println("Please enter a number");
        s = false;}
        menuChoice();
        return s;

    }
    public static Boolean depositmethod(Boolean s, Account account) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("how much would you like to deposit?");
        double amount = 0;
        try{amount =  myScanner.nextDouble();
            try {
                System.out.println(account.deposit(amount));
                s = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s = false;
            }}
        catch(Exception e){System.out.println("Please enter a number");
            s = false;}

        return s;
    }
}













