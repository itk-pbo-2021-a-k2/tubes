package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import java.lang.invoke.VarHandle;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class HK implements  Formula{
    @Override
    public String name() {
        return "HK";
    }

    @Override
    public void apply(InputRequest input) {
        var I1 = input.requestDecimal("I1");
        I1.setDescription("Arus Listrik Pada Rangkaian 1"); 
        var I2 = input.requestDecimal("I2");
        I2.setDescription("Arus Listrik Pada Rangkaian 2");
        var I3 = input.requestDecimal("I3");
        I3.setDescription("Arus Listrik Pada Rangkaian 3");
        var R1 = input.requestDecimal("R1")
        R1.setDescription("Nilai Resistensi 1");
        var R2 = input.requestDecimal("R2");
        R2.setDescription("Nilai Resistensi 2");
        var R3 = input.requestDecimal("R3");
        R3.setDescription("Nilai Resistensi 3");
        input.addAction("V1", this::V1);
        input.addAction("V2", this::V2);
        input.addAction("V3", this::V3);

    }
    
    public String V1(InputValues rumus) {
        var I1 = rumus.getDouble("I1");
        var R1 = rumus.getDouble("R1");

        var v1 = I1 * R1 ;

        return Double.toString(v1);
        
    }

    public String V2(InputValues rumus) {
        var I2 = rumus.getDouble("I2");
        var R2 = rumus.getDouble("R2");
    
        var v2 = I2 * R2 ;

        return Double.toString(v2);


    }
    
    public String V3(InputValues rumus) {
        var I3 = rumus.getDouble("I3");
        var R3 = rumus.getDouble("R3");

        var v3 = I3 * R3 ;

        return Double.toString(v3);

    }

}
