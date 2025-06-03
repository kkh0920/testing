package test2.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import test2.calulator.adder.Adder;
import test2.calulator.adder.IAdder;

class AdderTest {

    private IAdder adder = new Adder();

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
}
