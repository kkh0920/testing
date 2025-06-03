package test2.calulator.divider;

import test2.calulator.multiplier.IMultiplier;
import test2.calulator.substractor.ISubstractor;

public class Divider implements IDivider {

    private IMultiplier multiplier;
    private ISubstractor substractor;

    public Divider(IMultiplier multiplier, ISubstractor substractor) {
        this.multiplier = multiplier;
        this.substractor = substractor;
    }

    @Override
    public int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        int sign = multiplier.multiply(a, b) < 0 ? -1 : 1;
        
        if (a < 0) {
            a = multiplier.multiply(a, -1);
        }
        if (b < 0) {
            b = multiplier.multiply(b, -1);
        }
        
        int result;
        for (result = 0; a >= b; result++) {
            a = substractor.substract(a, b);
        }
            
        return multiplier.multiply(sign, result);
    }
}
