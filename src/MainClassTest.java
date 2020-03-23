import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Function returned incorrect value", 14, mainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertFalse("The returned value is bigger or equal 45", mainClass.getClassNumber() > 45);
    }
}
