package Structures;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.* ;

public class PremiumTests {
    Premium a = new Premium(1, "Eoghan", "Bradley", 1000);

    @Test
    public void testPremiumOverdraftAmount(){

        assertEquals(3000,a.getOverdraft(),0.01) ;

    }
    @Test
    public void testPremiumTransactionFee(){

        assertEquals(0,a.getTransactionCharge(),0.01) ;

    }
       @Test
    public void checkNameisSpelledCorrectly(){

        assertEquals("Eoghan Bradley", a.getName()) ;
    }
     @Test
    public void checkAccountInfo(){
         assertEquals("1 (Premium) - Eoghan Bradley - £1000.0", a.getAccountInfo()) ;

     }
    @Test
    public void checkSavings(){
        assertEquals( "Balance: \t£" + 1000.0 ,a.getSavings());
    }

    @Test
    public void checkDeposit(){
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










}



















