package test2.calulator.substractor;

import test2.calulator.adder.IAdder;
import test2.calulator.flipper.IFlipper;

public class Substractor implements ISubstractor {

    private IAdder adder;
    private IFlipper flipper;

    public Substractor(IAdder adder, IFlipper flipper) {
        this.adder = adder;
        this.flipper = flipper;
    }
    
    @Override
    public int substract(int a, int b) {
        return adder.add(a, flipper.flip(b));
    }
}
