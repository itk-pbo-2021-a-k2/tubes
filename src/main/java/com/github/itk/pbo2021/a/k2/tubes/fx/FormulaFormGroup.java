package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetroStyleClass;

public class FormulaFormGroup extends TitledPane {
  ScrollPane scrollPane = new ScrollPane();
  VBox layout = new VBox();

  public FormulaFormGroup() {
    layout.setSpacing(20);
    layout.setAlignment(Pos.TOP_LEFT);
    scrollPane.setContent(layout);
    scrollPane.setFitToWidth(true);

    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    setContent(scrollPane);

    scrollPane.getContent().setOnScroll(scrollEvent -> {
      double deltaY = scrollEvent.getDeltaY() * 8;
      double width = scrollPane.getContent().getBoundsInLocal().getWidth();
      double vvalue = scrollPane.getVvalue();
      scrollPane.setVvalue(vvalue + (-deltaY / width));
    });
  }

  public Node add(Formula formula) {
    var node = create(formula);
    layout.getChildren().add(node);
    layout();
    return node;
  }

  public void remove(Node node) {
    layout.getChildren().remove(node);
  }

  protected Node create(Formula formula) {
    var formNode = new FormulaFormNode(formula);
    var node = new ButtonedTitledPane(formula.name(), formNode);

    node.getButton().setText("X");
    node.getButton().getStyleClass().add(JMetroStyleClass.TABLE_GRID_LINES);

    node.getButton().setOnAction(actionEvent -> remove(node));
    return node;
  }
}
