import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTest01 {

    @Test
    public void test01(){

    assertEquals(1,1);//assertEquals() methodunda parantez içindeki parametreler birbirne eşitse test geçer değilse kalır.
    assertTrue("Merhaba".contains("a"));//assertTrue() methodunun parantez içindeki koşul true ise test geçer, değilse kalır
    assertFalse("google.com".contains("x"));//negative test--> parantez içi durum false dönerse test geçer
    assertEquals(65,'A');


    }
}
