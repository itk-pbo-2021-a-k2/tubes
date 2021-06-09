package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class RSP implements Formula {
    @Override
    public String name() {
        return "RSP";
    }

    @Override
    public void apply(InputRequest input) {
        input.requestDecimal("R");
        input.requestDecimal("R1");
        input.requestDecimal("R2");
        input.requestDecimal("R3");
        input.requestDecimal("Tegangan");
        input.requestDecimal("Arus");

        input.addAction("Rs", this::Rseritotal);
        input.addAction("Is", this::arusRS);

        input.addAction("Rp", this::Rparaleltotal);
        input.addAction("Ip", this::arusRP);
        
        input.addAction("Rsp", this::RSPtotal);
        input.addAction("Isp", this::arusRSP);

        input.addAction("V", this::tegangan);
    }

    // Rangkaian Seri
    public String Rseritotal(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");

        var Rs= r1+r2+r3;
        return Double.toString(Rs);
    }

    public String arusRS(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");
        var Vs= rumus.getDouble("Tegangan");
       

        var Rs= r1+r2+r3;
        var Is= Vs/Rs;
        return Double.toString(Is);
    }


    // Rangkaian Paralel
    public String Rparaleltotal(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");
        
        var Rt = (1/r1)+(1/r2)+(1/r3);
        var Rp= 1/Rt;
        return Double.toString(Rp);
    }

    public String arusRP(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");
        var Vp= rumus.getDouble("Tegangan");
       
        var Rt = (1/r1)+(1/r2)+(1/r3);
        var Rp= 1/Rt;
        var Ip= Vp/Rp;
        return Double.toString(Ip);
    }


    // Rangkaian Seri Paralel
    public String RSPtotal(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");
        
        var Rs = r1+r2;
        var Rp = (1/Rs)+(1/r3);
        var Rsp= 1/Rp;
        return Double.toString(Rsp);
    }

    public String arusRSP(InputValues rumus){
        var r1= rumus.getDouble("R1");
        var r2= rumus.getDouble("R2");
        var r3= rumus.getDouble("R3");
        var Vsp= rumus.getDouble("Tegangan");
       
        var Rs = r1+r2;
        var Rp = (1/Rs)+(1/r3);
        var Rsp= 1/Rp;
        var Isp= Vsp/Rsp;
        return Double.toString(Isp);
    }


    // Tegangan semua rangkaian
    public String tegangan(InputValues rumus){
        var R= rumus.getDouble("R");
        var I= rumus.getDouble("Arus");
       
        var V= I*R;
        return Double.toString(V);
    }
}