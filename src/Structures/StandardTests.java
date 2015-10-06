package Structures;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.* ;

public class StandardTests {
    Standard a = new Standard(1, "Eoghan", "Bradley", 500);

    @Test
    public void testStandardOverdraftAmount(){

        assertEquals(500,a.getOverdraft(),0.01) ;

    }
    @Test
    public void testStandardTransactionFee(){

        assertEquals(0,a.getTransactionCharge(),0.01) ;

    }

    @Test
    public void checkAccountInfo(){
        assertEquals("1 (Standard) - Eoghan Bradley - £500.0", a.getAccountInfo()) ;

    }
    @Test
    public void checkSavings(){
        assertEquals( "Balance: \t£" + 500.0 ,a.getSavings());
    }
    @Test
    public void checkDepositIsPositive(){
        try
        {
            assertEquals(540, a.deposit(-10));
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
            assertEquals(510, a.withdraw(-10));
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
        double x = 5000;
        try
        {
            assertEquals(sum, a.withdraw(x));
            fail();
        } catch(Exception e )
        {
            assertEquals("Cannot withdraw £5000.0 , only £1000.0 available as there is a £0.0 transaction charge for Standard accounts. Balance: £500.0", e.getMessage()) ;
        }
    }

    @Test
    public void getSavingsDouble_SuccessTest(){
        assertEquals(500.0,a.getSavingsDouble(),0.1);
    }

    @Test
    public void deposit_SuccessTest(){
        try
        {
            assertEquals("Balance: \t£510.0",a.deposit(10));
        }catch(Exception e){}
    }

    @Test
    public void withdraw_SuccessTest(){
        try
        {
            assertEquals("Balance: \t£490.0",a.withdraw(10));
        }catch(Exception e){}
    }

    @Test
    public void getAccountNumberTest(){
        assertEquals(1,a.getaccountnumber());
    }

    @Test
    public void getAccountType(){
        assertEquals(AccountType.Standard,a.getAccountType());
    }










}
