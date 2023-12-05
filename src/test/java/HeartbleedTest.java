import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

public class HeartbleedTest {

    /**
     * These three tests are simple example of Heartbleed.java
     */
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

    /**
     * All Specification-based Testing
     * described in the documentation
     */
    @ParameterizedTest
    @MethodSource("specificationGenerator")
    public void specificationTest(String manipulatingString, int outputLength, int caesarKey, String manipulatedString) {
        assertEquals(Heartbleed.manipulateString(manipulatingString, outputLength, caesarKey), manipulatedString);
    }

    /**
     * The generating method for specification-based testing
     * @return Stream of input
     */
    static Stream<Arguments> specificationGenerator() {
        return Stream.of(
                of(null, 1, 1, ""), // Null Array - T1
                of("", 1, 1, ""), // Empty Array - T2
                of("a", 0, 1, ""), // Zero Length - T3
                of("a", -1, 1, ""), // Negative Length - T4
                of("a", 1, 0, "a"), // Lowercase single Input, no Shift - T5
                of("a", 1, -1, "a"), // Lowercase single Input, negative Shift - T6
                of("A", 1, 5, "F"), // Uppercase single Input, max Shift - T7
                of("A", 1, 6, "F"), // Uppercase single Input, over Shift - T8
                of("Zorro", 10, -1, "ZorroZorro"), // Double String, negative Shift - T9
                of("Zorro", 10, 0, "ZorroZorro"), // Double String, no Shift - T10
                of("Zorro", 10, 3, "CruurCruur"), // Double String - T11
                of("Zorro", 3, 3, "Cru"), // Trunked String - T12
                of("Zorro", 13, 3, "CruurCruurCru"), // Double and Trunked String - T13
                of("Zorro", 10, 5, "EtwwtEtwwt"), // Double String, max Shift - T14
                of("Zorro", 10, 6, "EtwwtEtwwt"), // Double String, over Shift - T15
                of("z", 1, 1, "a"), // Boundary: Lowercase single Input - T16
                of("Z", 1, 1, "A"), // Boundary: Uppercase single Input - T17
                of("@", 2, 1, "@@"), // Boundary: Next to 'z' Input - T18
                of("[", 2, 1, "[["), // Boundary: Next to 'Z' Input - T19
                of("a a", 3, 1, "b b"), // Extra: Blank Input - T20
                of("a&a", 3, 2, "c&c"), // Extra: Special Character Input - T21
                of("a\na", 3, 3, "d\nd") // Extra: Sequence escape Input - T22

        );
    }
}
