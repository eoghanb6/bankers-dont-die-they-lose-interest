package Structures;

public abstract class Account {
    private int accountNumber;
    private String firstName;
    private String lastName;
    private double Savings = 0;
    public double MaxWithdrawal = 10000;

    public Account(int accountNumber, String firstName, String lastName, double initialSavings){
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        getOverdraft();
        this.Savings = initialSavings;
    }

    public String getAccountInfo(){
        //<account number> (<account type>) - <first name> <last name> - £<balance>
        return this.accountNumber + " (" + this.getAccountType() + ") - " + this.firstName + " " + this.lastName + " - £" + this.Savings;
    }

    public String getSavings(){
        return "Balance: \t£" + this.Savings;
    }



    public String deposit(double x) throws Exception{
        if(x < 0)
            {throw new Exception("Enter Positive Number");}
        else{
            this.Savings += x;
            return getSavings();}
    }

    public String withdraw(double x) throws Exception{
        if(x < 0)
            {throw new Exception("Enter Positive Number");}
        else{
            double sum = this.Savings + this.getOverdraft() - this.getTransactionCharge();
                if(x > MaxWithdrawal){throw new Exception("Max withdrawl £" + MaxWithdrawal);}
                else{
                    if (this.Savings + this.getOverdraft() < x + this.getTransactionCharge()){throw new Exception("Cannot withdraw £" + x + " , only £" + sum + " available as there is a £" + this.getTransactionCharge() +" transaction charge for Saver accounts. Balance: £" + this.Savings );}
                    else {
                    this.Savings -= (x + this.getTransactionCharge());
                    return getSavings();}
                    }
            }
    }











    public int getaccountnumber(){
        return this.accountNumber;
    }

    public abstract double getTransactionCharge();
    public abstract AccountType getAccountType();
    public abstract double getOverdraft() ;
}


