package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.FormulaGroup;
import javafx.scene.layout.*;

public class MainPane extends BorderPane {
  GridPane layout = new GridPane();

  Sidebar sidebar = new Sidebar();

  FormulaFormGroup formulaPane = new FormulaFormGroup();
  ColumnConstraints formulaPaneColumnConstraint;

  ColumnConstraints sidebarColumnConstraint;

  public MainPane() {
    setCenter(layout);

    formulaPane.setCollapsible(false);

    sidebar.setMaxWidth(Double.MAX_VALUE);
    sidebar.setMaxHeight(Double.MAX_VALUE);

    formulaPane.setMaxWidth(Double.MAX_VALUE);
    formulaPane.setMaxHeight(Double.MAX_VALUE);

    layout.add(sidebar, 0, 0);
    layout.add(formulaPane, 1, 0);

    sidebarColumnConstraint = new ColumnConstraints();
    setSidebarPercentWidth(20);
    getSidebarColumnConstraint().setHgrow(Priority.ALWAYS);

    var row0 = new RowConstraints();
    row0.setVgrow(Priority.ALWAYS);
    row0.setPercentHeight(100);

    formulaPaneColumnConstraint = new ColumnConstraints();
    setFormulaPanelPercentWidth(80);

    layout.getColumnConstraints().add(sidebarColumnConstraint);
    layout.getColumnConstraints().add(formulaPaneColumnConstraint);

    layout.getRowConstraints().add(row0);

    sidebar.setOnFormulaButtonAction(formulaPane::add);
  }

  public void addFormulaGroup(FormulaGroup factory) {
    sidebar.add(factory);
  }

  public void setSidebarPercentWidth(double percent) {
    sidebarColumnConstraint.setPercentWidth(percent);
  }

  public void setFormulaPanelPercentWidth(double percent) {
    formulaPaneColumnConstraint.setPercentWidth(percent);
  }

  public ColumnConstraints getSidebarColumnConstraint() {
    return sidebarColumnConstraint;
  }

  public ColumnConstraints getFormulaPaneColumnConstraint() {
    return formulaPaneColumnConstraint;
  }
}
