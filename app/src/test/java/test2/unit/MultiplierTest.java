package test2.unit;

import test2.calulator.multiplier.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import test2.calulator.adder.*;
import test2.calulator.flipper.*;

class MultiplierTest {

    private IMultiplier multiplier;

    @Mock private IAdder adder;
    @Mock private IFlipper flipper;

    @BeforeEach
    void setUp() {
        adder = Mockito.mock(IAdder.class);
        flipper = Mockito.mock(IFlipper.class);

        Mockito.when(adder.add(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
            int arg1 = invocation.getArgument(0);
            int arg2 = invocation.getArgument(1);
            return arg1 + arg2;
        });
        Mockito.when(flipper.flip(Mockito.anyInt())).thenAnswer(invocation -> {
            int arg = invocation.getArgument(0);
            return -arg;
        });
        
        multiplier = new Multiplier(adder, flipper);
    }

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
}
