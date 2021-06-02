package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class RRI implements Formula {
    @Override
    public String name() {
        return "RRI";
    }

    @Override
    public void apply(InputRequest input) {
        var r =input.requestDecimal("R");
        r.setDescription("Nilai Resistor");
        input.requestDecimal("frekuensi");
        input.requestDecimal("L");
        input.addAction("Z", this::besarImpedansi);
    }
    public String besarImpedansi(InputValues rumus){
        var r= rumus.getDouble("R");
        var f= rumus.getDouble("frekuensi");
        var l= rumus.getDouble("L");


        var r2= r*r;
        var phi2fl = Math.PI*2*f*l;
        var z= Math.sqrt(r2+phi2fl*phi2fl);
        return Double.toString(z);
    }

}
