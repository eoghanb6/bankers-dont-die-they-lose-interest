import java.util.Scanner;
public class Main {

    public static void main(String[] args) {


    }
        public void menuChoice(){

            System.out.println("Welcome to Bank");
            System.out.println("Press 1 to create account, press 2 to exit");
            Scanner myScanner = new Scanner(System.in);
            myScanner.nextLine();
            int menuChoice1 = 0;
            try{
            menuChoice1 = myScanner.nextInt(); }
            catch (Exception Error){
                System.out.println("Please enter a suitable value");
                menuChoice();
            }

            switch (menuChoice1) {
                case 1:
                    //create account
                    break;

                case 2:
                    System.out.println("Bye");
                    System.exit(2);
                    break;

                default:
                    System.out.println("Please enter a suitable value");
                    menuChoice();}
        }
}

























