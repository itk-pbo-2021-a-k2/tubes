package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class KD implements Formula {
    @Override
    public String name() {
        return "KD";
    }

    @Override
    public void apply(InputRequest input) {
        input.requestDecimal("k");
        input.requestDecimal("A");
        input.requestDecimal("d");
        input.requestDecimal("V");
        input.addAction("C", this::nilaikapasitansi);
        input.addAction("Q", this::jumlahmuatan);
        input.addAction("W", this::energitersimpan);
    }
    public String nilaikapasitansi(InputValues rumus){
        var k= rumus.getDouble("k");
        var A= rumus.getDouble("A");
        var d= rumus.getDouble("d");

        var p= (10);
        var e= (8.85*(Math.pow(p, -12)));
        var C= (e*k*(A/d));
        return Double.toString(C);
    }
    public String jumlahmuatan(InputValues rumus){
        var V=rumus.getDouble("V");

        double C = Double.parseDouble(nilaikapasitansi(rumus));
        var Q = (C*V);
        return Double.toString(Q);
    }
    public String energitersimpan(InputValues rumus){
        var V=rumus.getDouble("V");

        double C = Double.parseDouble(nilaikapasitansi(rumus));
        var v2 = (V*V);
        var W = (0.5*(C*v2));
        return Double.toString(W);
    }
}
