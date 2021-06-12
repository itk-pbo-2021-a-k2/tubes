package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.util.LinkedHashMap;
import java.util.Map;

public class FormulaFormNode extends BorderPane implements FormulaForm {
  private final Formula formula;
  private final Map<String, LabelTextField> values = new LinkedHashMap<>();
  private final Map<String, LabelTextFieldAction> actionValue = new LinkedHashMap<>();
  private final SplitPane center;

  private final FlowPane requestLayout = new FlowPane();
  private final FlowPane actionLayout = new FlowPane();

  public FormulaFormNode(Formula formula) {
    this.formula = formula;

    FormulaFormFactory factory = new FormulaFormFactory();
    factory.apply(this);

    center = new SplitPane(requestLayout, actionLayout);

    setCenter(center);
  }

  @Override
  public void addRequest(String name, LabelTextField field) {
    values.put(name, field);
    field.getTextField().setOnAction(actionEvent -> refresh());
    requestLayout.getChildren().add(field);
  }

  public void refresh() {
    var val = new Values();
    try {
      actionValue.forEach((s, labelTextFieldActionPane) -> labelTextFieldActionPane.apply(val));
    } catch (Exception exc) {
      //
    }
  }

  @Override
  public void addAction(String name, LabelTextFieldAction field) {
    values.put(name, field);
    actionValue.put(name, field);
    actionLayout.getChildren().add(field);
  }

  @Override
  public Formula getFormula() {
    return formula;
  }

  class Values extends InputValues {
    @Override
    public String get(String name) {
      var val = values.get(name);
      return val != null ? val.getValue() : "";
    }
  }
}
