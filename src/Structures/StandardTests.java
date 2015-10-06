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










}
