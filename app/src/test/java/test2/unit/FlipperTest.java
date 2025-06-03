package test2.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import test2.calulator.flipper.Flipper;
import test2.calulator.flipper.IFlipper;

class FlipperTest {
    
    private IFlipper flipper = new Flipper();
    
    @ParameterizedTest
    @CsvSource({
        "1, -1",
        "0, 0",
        "-1, 1",
        "100, -100",
        "-100, 100",
        "123456789, -123456789"
    })
    void flipTest(int input, int expected) {
        int result = flipper.flip(input);
        assertEquals(expected, result);
    }
}
