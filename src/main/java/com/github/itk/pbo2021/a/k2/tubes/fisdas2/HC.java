package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import java.math.BigDecimal;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class HC implements Formula {
    @Override
    public String name() {
        return "HC";
    }

    @Override
    public void apply(InputRequest input) {
        input.requestDecimal("muatan1");
        input.requestDecimal("muatan2");
        input.requestDecimal("jarak antar muatan");
        input.addAction("F", this::hukumColoumb);
    }
    public String hukumColoumb(InputValues rumus){
        var q1= rumus.getDouble("muatan1");
        var q2= rumus.getDouble("muatan2");
        var r = rumus.getDouble("jarak antar muatan");

        double a = 10;
        double b = 9;

        var k = b*Math.pow(a,b);
        var F = k*((q1*q2)/(r*r));

        return Double.toString(F);
    }
}