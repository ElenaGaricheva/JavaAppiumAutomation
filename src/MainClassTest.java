import org.junit.Test;

import static org.junit.Assert.*;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        assertEquals("Function returned incorrect value", 14, mainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        assertFalse("The returned value is bigger or equal 45", mainClass.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        assertTrue("Message doesn't contain the string", mainClass.getClassString().contains("hello") || mainClass.getClassString().contains("Hello"));
    }
}
