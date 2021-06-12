package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;

public class LabelTextFieldAction extends LabelTextField {
  private final InputRequest.FormulaAction action;

  public LabelTextFieldAction(String name, InputRequest.FormulaAction action) {
    super(name);
    this.action = action;
  }

  public void apply(InputValues values) {
    setValue(action.apply(values));
  }
}
