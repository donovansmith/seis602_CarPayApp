//Isha
package application;

import static org.junit.Assert.*;

public class CarAppTest {
    CarApp carapp = new CarApp(30000,8000,5.0,24);
    
    @org.junit.Test
    public void moPay() {
        double result = carapp.moPay();
        assertEquals(965.18,result,0);
    }
    @org.junit.Test
    public void totalPaid() {
        double result = carapp.totalPaid();
        assertEquals(23164.32,result,0);
    }
    @org.junit.Test
    public void totalInterest() {
        double result = carapp.totalInterest();
        assertEquals(1164.3199999999997,result,0);
    }
}
