package Structures;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.* ;

public class SaverTests {
    Saver a = new Saver(1, "Eoghan", "Bradley", 1000);

    @Test
    public void testPremiumOverdraftAmount(){

        assertEquals(0,a.getOverdraft(),0.01) ;

    }
    @Test
    public void testPremiumTransactionFee(){

        assertEquals(1,a.getTransactionCharge(),0.01) ;

    }
    @Test
    public void checkNameisSpelledCorrectly(){

        assertEquals("Eoghan Bradley", a.getName()) ;
    }
    @Test
    public void checkAccountInfo(){
        assertEquals("1 (Saver) - Eoghan Bradley - £1000.0", a.getAccountInfo()) ;

    }
    @Test
    public void checkSavings(){
        assertEquals( "Balance: \t£" + 1000.0 ,a.getSavings());
    }

    @Test
    public void checkDepositIsPositive(){
        try
        {
            assertEquals(990, a.deposit(-10));
            fail();
        }catch(Exception e)
        {
            final String expectedMessage = "Enter Positive Number";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void checkwithdrawisPositive(){
        try
        {
            assertEquals(1010, a.withdraw(-10));
            fail();
        } catch(Exception e )
        {
            final String expectedMessage = "Enter Positive Number";
            assertEquals(expectedMessage, e.getMessage()) ;
        }


    }
    @Test
    public void WithdrawLessThanmax(){
        double maxWithdrawal = 10000;
        try
        {
            assertEquals("Balance: \t£"+-1.0, a.withdraw(10001));
            fail();
        } catch(Exception e )
        {
            final String expectedMessage = "Max withdrawl £" + maxWithdrawal;
            assertEquals(expectedMessage, e.getMessage()) ;
        }


    }
    @Test
    public void allow£1lessforsaverwithdrawals(){
        double maxWithdrawal = 10000;
        String savings = a.getSavings();
        double sum = a.getSavingsDouble() + a.getOverdraft() - a.getTransactionCharge() ;

        try
        {
            assertEquals(0, a.withdraw(1000));
            fail();
        } catch(Exception e )
        {
            assertEquals("Cannot withdraw £1000.0 , only £999.0 available as there is a £1.0 transaction charge for Saver accounts. Balance: £1000.0", e.getMessage()) ;
        }
    }

    @Test
    public void getSavingsDouble_SuccessTest(){
        assertEquals(1000.0,a.getSavingsDouble(),0.1);
    }

    @Test
    public void deposit_SuccessTest(){
        try
        {
            assertEquals("Balance: \t£1010.0",a.deposit(10));
        }catch(Exception e){
            fail();
        }
    }

    @Test
    public void withdraw_SuccessTest(){
        try
        {
            assertEquals("Balance: \t£989.0",a.withdraw(10));
        }catch(Exception e){
            fail();
        }
    }

    @Test
    public void getAccountNumberTest(){
        assertEquals(1,a.getaccountnumber());
    }

    @Test
    public void getAccountType(){
        assertEquals(AccountType.Saver,a.getAccountType());
    }

}


















