package com.github.itk.pbo2021.a.k2.tubes.fx;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;


public class ButtonedTitledPane extends TitledPane {
  Button button = new Button();

  ButtonedTitledPane(String title, Node node) {
    super(title, node);
    init();
  }

  ButtonedTitledPane() {
    init();
  }

  private void init() {
    setAlignment(Pos.CENTER);

    setGraphic(button);
    button.translateXProperty().bind(Bindings.createDoubleBinding(
        () -> getWidth() - button.getLayoutX() - button.getWidth() - 20,
        widthProperty()
    ));
  }

  public Button getButton() {
    return button;
  }

  public void setButtonText(String text) {
    button.setText(text);
  }

}
