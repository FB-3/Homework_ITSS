import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeartbleedTest {

    @Test
    public void onlyDuplicateInputTest() {
        assertEquals(Heartbleed.manipulateString("ciao", 8, 0), "ciaociao");
    }

    @Test
    public void onlyShiftInputTest() {
        assertEquals(Heartbleed.manipulateString("aaa", 3, 1), "bbb");
    }

    @Test
    public void shiftAndDuplicateInputTest() {
        assertEquals(Heartbleed.manipulateString("AAA", 6, 5), "FFFFFF");
    }

    @Test
    public void specificationTest() {

    }
}
