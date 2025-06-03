package test2.calulator.multiplier;

import test2.calulator.adder.IAdder;
import test2.calulator.flipper.IFlipper;

public class Multiplier implements IMultiplier {
    
    private IAdder adder;
    private IFlipper flipper;

    public Multiplier(IAdder adder, IFlipper flipper) {
        this.adder = adder;
        this.flipper = flipper;
    }
    
    @Override
    public int multiply(int a, int b) {
        if (b < 0) {
            a = flipper.flip(a);
            b = flipper.flip(b);
        }
        int result = 0;
        for (int i = 0; i < b; i++) {
            result = adder.add(result, a);
        }
        return result;
    }
}
