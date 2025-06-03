package test2.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import test2.calulator.multiplier.*;
import test2.calulator.substractor.*;
import test2.calulator.divider.*;

class DividerTest {

    private IDivider divider;

    @Mock private IMultiplier multiplier;
    @Mock private ISubstractor substractor;

    @BeforeEach
    void setUp() {
        multiplier = Mockito.mock(IMultiplier.class);
        substractor = Mockito.mock(ISubstractor.class);

        Mockito.when(multiplier.multiply(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
            int arg1 = invocation.getArgument(0);
            int arg2 = invocation.getArgument(1);
            return arg1 * arg2;
        });
        Mockito.when(substractor.substract(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
            int arg1 = invocation.getArgument(0);
            int arg2 = invocation.getArgument(1);
            return arg1 - arg2;
        });

        divider = new Divider(multiplier, substractor);
    }

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
}
