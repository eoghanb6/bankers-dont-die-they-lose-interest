package Structures;
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
    public void checkSavings(){
        a.getSavings();
        assertEquals("Balance: £" + 1000,a.getSavings(),0.1);

    }

}



















