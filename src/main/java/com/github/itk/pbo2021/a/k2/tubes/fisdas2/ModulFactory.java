package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.FormulaFactory;

import java.util.Iterator;
import java.util.List;

public class ModulFactory implements FormulaFactory {
  List<Formula> formulas = List.of(
      // tambah modul disini
      // contoh:
      // new Contoh()

  );

  @Override
  public List<Formula> get() {
    return formulas;
  }

  @Override
  public String name() {
    return "Fisdas 2";
  }

  @Override
  public Iterator<Formula> iterator() {
    return formulas.iterator();
  }
}
