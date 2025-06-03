package test2.integration.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import test2.calulator.adder.Adder;
import test2.calulator.adder.IAdder;
import test2.calulator.divider.Divider;
import test2.calulator.divider.IDivider;
import test2.calulator.flipper.Flipper;
import test2.calulator.flipper.IFlipper;
import test2.calulator.multiplier.IMultiplier;
import test2.calulator.multiplier.Multiplier;
import test2.calulator.substractor.ISubstractor;
import test2.calulator.substractor.Substractor;

public class ThirdStepOfIntegrationTest {

    private IDivider divider;

    private IMultiplier multiplier;
    private ISubstractor substractor;

    private IAdder adder;
    private IFlipper flipper;

    @BeforeEach
    void setUp() {
        adder = new Adder();
        flipper = new Flipper();
        multiplier = new Multiplier(adder, flipper);
        substractor = new Substractor(adder, flipper);
        divider = new Divider(multiplier, substractor);
    }

    /* ---------------- Divider Tests ---------------- */

    @ParameterizedTest
    @CsvSource({
        "10, 2, 5",
        "10, -2, -5",
        "0, 1, 0",
        "15, 2, 7",
        "15, -2, -7",
        "100, 20, 5",
        "-100, 20, -5",
    })
    void divideTest(int a, int b, int expected) {
        int actual = divider.divide(a, b);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 0",
        "0, 0",
        "-10, 0"
    })
    void divideByZeroTest(int a, int b) {
        assertThrows(ArithmeticException.class, () -> {
            divider.divide(a, b);
        });
    }

    /* ---------------- Multiplier Tests ---------------- */

    @ParameterizedTest
    @CsvSource({
        "2, 3, 6",
        "0, 5, 0",
        "5, 0, 0",
        "-1, 4, -4",
        "4, -1, -4",
        "100, 200, 20000",
        "-100, -50, 5000",
    })
    void multiplyTest(int a, int b, int expected) {
        int actual = multiplier.multiply(a, b);
        assertEquals(expected, actual);
    }

    /* ---------------- Substractor Tests ---------------- */

    @ParameterizedTest
    @CsvSource({
        "5, 3, 2",
        "0, 0, 0",
        "-1, -1, 0",
        "100, 50, 50",
        "-100, -50, -50",
        "123456789, 987654321, -864197532"
    })
    void substractTest(int a, int b, int expected) {
        int actual = substractor.substract(a, b);
        assertEquals(expected, actual);
    }

    /* ---------------- Adder Tests ---------------- */

    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "0, 0, 0",
        "-1, -1, -2",
        "100, 200, 300",
        "-100, 50, -50",
        "123456789, 987654321, 1111111110"
    })
    void addTest(int a, int b, int expected) {
        int result = adder.add(a, b);
        assertEquals(expected, result);
    }

    /* ---------------- Flipper Tests ---------------- */

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
