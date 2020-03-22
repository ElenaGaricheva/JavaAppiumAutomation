import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Function returned incorrect value", 14, mainClass.getLocalNumber());
    }
}
