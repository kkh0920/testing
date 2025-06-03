package test2.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import test2.calulator.adder.*;
import test2.calulator.flipper.*;
import test2.calulator.substractor.*;

class SubstractorTest {

    private ISubstractor substractor;

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

        substractor = new Substractor(adder, flipper);
    }

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
}
