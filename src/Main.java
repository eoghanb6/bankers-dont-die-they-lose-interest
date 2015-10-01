import java.util.Scanner;
import Structures.*;
public class Main {


    public static void main(String[] args) {
        menuChoice();
    }
    public static void menuChoice(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welcome to Bank");
        System.out.println("Press 1 to create account, press 2 to exit");
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
        AccountType accountType;
        accountType = makeAccountType();
        int savings = addSavings();
        if(savings == 0 ){
          Account account = new Account(firstName, lastName, accountType);
          System.out.println(account.getAccountInfo());
        }
        else{
            Account account = new Account(firstName, lastName, accountType, savings);
            System.out.println(account.getAccountInfo());
        }


    }
    public static AccountType makeAccountType(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Choose account type");
        System.out.println("1: Standard \t Overdraft: 500;");
        System.out.println("2: Saver \t \t Overdraft: 0");
        System.out.println("3: Premium \t \t Overdraft: 3000");
        int choice = 0;
        try{
            choice = myScanner.nextInt(); }
        catch (Exception Error){
            System.out.println("Please enter a suitable value");
            makeAccountType();
        }
        AccountType accountType = null;
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
        makeAccountType();}

        return accountType;
    }
    public static int addSavings(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Add initial savings or press enter for no savings");
        String input = myScanner.nextLine();
        int savings = 0;
        try{
            savings = Integer.parseInt(input);
        }
        catch(Exception e){
            if (input.equals("")){return 0;}
            else {
                System.out.println("Please enter a suitable value");
                addSavings(); }
        }
        if (savings < 0){
            System.out.println("Must be greater than 0");
            addSavings(); return 0;}
        else{
        return savings;}


    }
}

























