package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class Sidebar extends TitledPane {
  private VBox layout = new VBox();

  private Consumer<Formula> onFormulaButtonAction = formula -> {
  };

  public Sidebar() {
    setContent(layout);
    setCollapsible(false);
  }

  public void setOnFormulaButtonAction(Consumer<Formula> onFormulaButtonAction) {
    this.onFormulaButtonAction = onFormulaButtonAction;
  }

  protected FormulaGroupNode create(com.github.itk.pbo2021.a.k2.tubes.contract.FormulaGroup factory) {
    return new com.github.itk.pbo2021.a.k2.tubes.fx.FormulaGroupNode(factory);
  }

  public void add(com.github.itk.pbo2021.a.k2.tubes.contract.FormulaGroup factory) {
    var child = create(factory);
    child.setOnFormulaButtonAction(formula -> onFormulaButtonAction.accept(formula));
    layout.getChildren().add(child.toNode());
  }

  public interface FormulaGroupNode {
    Node toNode();

    void setOnFormulaButtonAction(Consumer<Formula> formula);
  }
}
