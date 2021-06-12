package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;

public interface FormulaForm {
  Formula getFormula();

  void addRequest(String name, LabelTextField field);

  void addAction(String name, LabelTextFieldAction field);
}
