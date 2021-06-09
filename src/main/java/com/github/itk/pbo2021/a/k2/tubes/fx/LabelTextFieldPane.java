package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.ValueProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.function.Consumer;

public class LabelTextFieldPane extends VBox implements ValueProperty {
  private final Label name;
  private final TextField textField = new TextField();
  private String defVal;

  private StringConverter<?> textConverter = new DefaultStringConverter();
  private Consumer<String> onTextChanged = s -> {
  };

  public LabelTextFieldPane(String labelName) {
    name = new Label(labelName);

    getChildren().add(name);
    getChildren().add(textField);

    textField.setOnAction(
        actionEvent -> onTextChanged.accept(textField.getText()));
  }

  public String getName() {
    return name.getText();
  }

  @Override
  public String getDescription() {
    return textField.getTooltip().getText();
  }

  @Override
  public ValueProperty setDescription(String description) {
    Tooltip tip = new Tooltip();
    tip.setText(description);
    textField.setTooltip(tip);
    return this;
  }

  @Override
  public String getDefault() {
    return defVal;
  }

  @Override
  public ValueProperty setDefault(String defVal) {
    this.defVal = defVal;
    return this;
  }

  @Override
  public boolean hasDefault() {
    return defVal != null;
  }

  public LabelTextFieldPane setOnTextChanged(Consumer<String> onTextChanged) {
    this.onTextChanged = onTextChanged;
    return this;
  }

  public StringConverter<?> getTextConverter() {
    return textConverter;
  }

  public void setTextConverter(StringConverter<?> converter) {
    textConverter = converter;
    textField.setTextFormatter(new TextFormatter<>(converter));
  }

  public TextField getTextField() {
    return textField;
  }

  public String getValue() {
    return textField.getText();
  }

  public void setValue(String value) {
    textField.setText(value);
  }

  public void setOnValueChanged(Consumer<String> action) {
    textField.setOnAction(actionEvent -> {
    });
  }

  public void setEditable(boolean cond) {
    textField.setEditable(cond);
  }
}
