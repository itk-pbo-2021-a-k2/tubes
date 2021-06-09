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
        input.requestDecimal("R");
        input.requestDecimal("frekuensi");
        input.requestDecimal("L");
        input.requestDecimal("Vm");
        input.requestDecimal("R");
        input.requestDecimal("Xl");
        input.addAction("Z", this::besarImpedansi);
        input.addAction("Im", this::arusMaksimal);
        input.addAction("Vr", this::teganganresistor);
        input.addAction("Vl",this::tegannganinduktor);
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
    public String arusMaksimal(InputValues rumus){
        var vm=rumus.getDouble("Vm");



        double z = Double.parseDouble(besarImpedansi(rumus));
        var Im = (vm/z);
        return Double.toString(Im);
    }
    public String teganganresistor(InputValues rumus){
        var R=rumus.getDouble("R");



        double Im = Double.parseDouble(arusMaksimal(rumus));
        var Vr = (Im*R);
        return Double.toString(Vr);
    }
    public String tegannganinduktor(InputValues rumus){
        var Xl=rumus.getDouble("Xl");



        double Im = Double.parseDouble(arusMaksimal(rumus));
        var Vl = (Im*Xl);
        return Double.toString(Vl);
    }
}
