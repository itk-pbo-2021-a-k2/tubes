package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class JW implements Formula{
    @Override
    public String name() {return "JW"; }

    @Override
    public void apply(InputRequest input) {
        input.requestDecimal("R1");
        input.requestDecimal("R2");
        input.requestDecimal("R3");
        input.requestDecimal("R4");
        input.requestDecimal("R5");
        input.addAction("Ra",this::Ra);
        input.addAction("Rb",this::Rb);
        input.addAction("Rc",this::Rc);
        input.addAction("Rb3",this::Rb3);
        input.addAction("Rc4",this::Rc4);
        input.addAction("RTotal",this::RTotal);
        input.requestDecimal("V");
        input.addAction("I",this::I);
    }
    public String Ra(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r5= rumus.getDouble("R5");

        var ra= r1*r2/r1+r2+r5;
        return Double.toString(ra);
    }
    public String Rb(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r5= rumus.getDouble("R5");

        var rb= r1*r5/r1+r2+r5;
        return Double.toString(rb);
    }
    public String Rc(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r5= rumus.getDouble("R5");

        var rc= r2*r5/r1+r2+r5;
        return Double.toString(rc);
    }
    public String Rb3(InputValues rumus){
        var r3= rumus.getDouble("R3");

        double rb = Double.parseDouble(Rb(rumus));
        var rb3 = (rb+r3);
        return Double.toString(rb3);
    }
    public String Rc4(InputValues rumus) {
        var r4 = rumus.getDouble("R4");

        double rc = Double.parseDouble(Rc(rumus));
        var rc4 = (rc + r4);
        return Double.toString(rc4);
    }
    public String RTotal(InputValues rumus) {

        double rb3 = Double.parseDouble(Rb3(rumus));
        double rc4 = Double.parseDouble(Rc4(rumus));
        var b3c4 = (1 / rb3) + (1 / rc4);
        double ra = Double.parseDouble(Ra(rumus));
        var rtol = (ra+b3c4);
        return Double.toString(rtol);
    }
    public String I(InputValues rumus) {
        var V = rumus.getDouble("V");

        double rtol = Double.parseDouble(RTotal(rumus));
        var I = (V/rtol);
        return Double.toString(I);
    }
}
