package Structures;

public class Saver extends Account {
    public Saver(int accountNumber, String firstName, String lastName, double initialSavings){
        super(accountNumber, firstName, lastName, initialSavings);
    }

    public double getTransactionCharge(){
        return 1;
    }
    public AccountType getAccountType(){
        return AccountType.Saver;
    }
}
