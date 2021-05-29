package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.FormulaGroup;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class DefaultFormulaFactoryNode extends BorderPane implements Sidebar.FormulaGroupNode {
  private final FormulaGroup factory;

  Consumer<Formula> action = formula -> {
  };

  public DefaultFormulaFactoryNode(FormulaGroup factory) {
    this.factory = factory;

    TitledPane pane = new TitledPane(factory.name(), createChild(factory));
    pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    setCenter(pane);
  }

  @Override
  public Node toNode() {
    return this;
  }

  @Override
  public void setOnFormulaButtonAction(Consumer<Formula> consumer) {
    action = consumer;
  }

  protected Button createChildNode(Formula formula) {
    return new Button(formula.name());
  }

  protected Node createChild(FormulaGroup factory) {
    var node = new VBox();
    for (var formula : factory) {
      var child = createChildNode(formula);
      child.setOnAction(actionEvent -> action.accept(formula));
      node.getChildren().add(child);
    }
    return node;
  }

  public FormulaGroup getFactory() {
    return factory;
  }
}
