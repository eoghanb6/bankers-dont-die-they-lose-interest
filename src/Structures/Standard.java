package Structures;

public class Standard extends Account{

    public Standard(int accountNumber, String firstName, String lastName, double initialSavings){
        super(accountNumber, firstName, lastName, initialSavings);
    }

    public double getTransactionCharge(){
        return 0;
    }
    public AccountType getAccountType(){
        return AccountType.Standard;
    }
}
