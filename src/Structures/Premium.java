package Structures;

public class Premium extends Account{
    public Premium(int accountNumber, String firstName, String lastName, double initialSavings){
        super(accountNumber, firstName, lastName, initialSavings);
    }

    public double getTransactionCharge(){
        return 0;
    }
    public AccountType getAccountType(){
        return AccountType.Premium;
    }

    public double getOverdraft() {

        return 3000;
    }

}
