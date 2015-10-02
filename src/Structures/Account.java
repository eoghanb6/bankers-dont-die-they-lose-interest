package Structures;

public class Account {
    private int accountNumber;
    private String firstName;
    private String lastName;
    private AccountType accountType;
    private double Savings = 0;
    private int saverTransactionCharge = 1;
    private int overdraft;

    public Account(int accountNumber, String firstName, String lastName, AccountType accountType){
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        getOverdraft();
    }
    public Account(int accountNumber, String firstName, String lastName, AccountType accountType, double initialSavings){
        this(accountNumber, firstName, lastName, accountType);
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
    public String getAccountInfo(){
        //<account number> (<account type>) - <first name> <last name> - £<balance>
        return this.accountNumber + " (" + this.accountType + ") - " + this.firstName + " " + this.lastName + " - £" + this.Savings;
    }
    public String getSavings(){
        return "Balance: \t£" + this.Savings;
    }
    public String deposit(double x) throws Exception{
        this.Savings += x;
        return getSavings();
    }
    public String withdraw(double x) throws Exception{
        double sum = this.Savings + this.overdraft;
        double sub = this.Savings - this.saverTransactionCharge;
        if(x > 10000){throw new Exception("Max withdrawl £10,000");}
        else{
            if (this.accountType == AccountType.Saver && this.Savings < x + this.saverTransactionCharge){throw new Exception("Cannot withdraw £" + x + " , only £" + sub + " available as there is a £" + this.saverTransactionCharge +" transaction charge for Saver accounts. Balance: £" + this.Savings );}
            else if(this.Savings + this.overdraft < x){throw new Exception("Cannot withdraw £" + x + " , only £" + sum + " available.");}
            else {
                if (this.accountType == AccountType.Saver){this.Savings -= x+this.saverTransactionCharge; return getSavings();}
                else{
                this.Savings -= x;
            return getSavings();}
            }
        }
    }
    public int getaccountnumber(){
        return this.accountNumber;
    }
}


