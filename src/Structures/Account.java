package Structures;

public class Account {
    private String firstName;
    private String lastName;
    private AccountType accountType;
    private double Savings;
    private int overdraft;

    public Account(String firstName, String lastName, AccountType accountType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        getOverdraft();
    }
    public Account(String firstName, String lastName, AccountType accountType, int initialSavings){
        this(firstName, lastName, accountType);
        this.Savings = initialSavings;
    }
    private void getOverdraft(){
        switch(this.accountType){
            case Standard:  this.overdraft = 500;
                break;
            case Saver:     this.overdraft = 0;
                break;
            case Premium:   this.overdraft = 3000;
                break;
        }
    }
}
