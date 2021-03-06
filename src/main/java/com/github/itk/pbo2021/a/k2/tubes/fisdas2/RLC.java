package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest.FormulaAction;

public class RLC implements Formula {

  @Override
  public String name() {
    return "RLC";
  }

  @Override
  public void apply(InputRequest input) {
    var l = input.requestDecimal("L");
    l.setDescription("Nilai induktor");
    var c = input.requestDecimal("C");
    c.setDescription("Nilai kapasitor");
    var r = input.requestDecimal("R");
    r.setDescription("Nilai resistor yang digunakan didalam rangkaian");
    var f = input.requestDecimal("frekuensi");
    f.setDescription("Frekuensi sinyal tegangan");
    var z = input.addAction("Z", this::besarImpedansi);
    // z.setDescription("Besar impedansi rangkaian");
    input.requestDecimal("Vm");
    input.addAction("Im", this::arusMaksimal);
    input.requestDecimal("R");
    input.addAction("Vr", this::teganganresistor);
    input.requestDecimal("Xl");
    input.addAction("Vl", this::tegannganinduktor);
  }


  public String besarImpedansi(InputValues rumus) {
    var r = rumus.getDouble("R");
    var f = rumus.getDouble("frekuensi");
    var l = rumus.getDouble("L");
    var c = rumus.getDouble("C");

    var r2 = r * r;
    var phi2fl = Math.PI * 2 * f * l;
    var phi2fc = Math.PI * 2 * f * c;
    var z = Math.sqrt(r2 + phi2fl - 1 / phi2fc * phi2fl - 1 / phi2fc);
    return Double.toString(z);

  }

  public String arusMaksimal(InputValues rumus) {
    var vm = rumus.getDouble("Vm");


    double z = Double.parseDouble(besarImpedansi(rumus));
    var Im = (vm / z);
    return Double.toString(Im);

  }

  public String teganganresistor(InputValues rumus) {
    var R = rumus.getDouble("R");


    double Im = Double.parseDouble(arusMaksimal(rumus));
    var Vr = (Im * R);
    return Double.toString(Vr);
  }

  public String tegannganinduktor(InputValues rumus) {
    var Xl = rumus.getDouble("Xl");

    double Im = Double.parseDouble(arusMaksimal(rumus));
    var Vl = (Im * Xl);
    return Double.toString(Vl);
  }

  public String teganganKapasitor(InputValues rumus) {
    var Xc = rumus.getDouble("Xc");

    double Im = Double.parseDouble(arusMaksimal(rumus));
    var Vc = (Im * Xc);
    return Double.toString(Vc);
  }
}
