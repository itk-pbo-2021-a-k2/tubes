package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;
import com.github.itk.pbo2021.a.k2.tubes.contract.ValueProperty;
import javafx.scene.Node;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.BigIntegerStringConverter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormulaInputRequestPaneFactory extends InputRequest {
  Map<String, LabelTextFieldPane> request = new LinkedHashMap<>();
  Map<String, LabelTextFieldActionPane> actions = new LinkedHashMap<>();

  @Override
  public ValueProperty request(String name) {
    return request(name, new LabelTextFieldPane(name));
  }

  @Override
  public ValueProperty request(String name, Pattern pattern) {
    var converter =
        new StringConverter<String>() {
          String last;

          @Override
          public String toString(String s) {
            var matcher = pattern.matcher(s);
            if (matcher.find()) {
              last = s;
              return s;
            } else return last;
          }

          @Override
          public String fromString(String s) {
            return toString(s);
          }
        };
    return request(name, converter);
  }

  @Override
  public InputRequest addAction(String name, FormulaAction action) {
    var form = new LabelTextFieldActionPane(name, action);
    form.setEditable(false);
    actions.put(name, form);
    return this;
  }

  @Override
  public ValueProperty requestDecimal(String name) {
    var form = new LabelTextFieldPane(name);
    form.setTextConverter(new BigDecimalStringConverter());
    return request(name, form);
  }

  @Override
  public ValueProperty requestInteger(String name) {
    var converter = new BigIntegerStringConverter();
    return request(name, converter);
  }

  public ValueProperty request(String name, LabelTextFieldPane pane) {
    return request.put(name, pane);
  }

  public <T> ValueProperty request(String name, StringConverter<T> converter) {
    var form = new LabelTextFieldPane(name);
    form.setTextConverter(converter);
    return request(name, form);
  }

  public void apply(FormulaForm form) {
    form.getFormula().apply(this);
    request.forEach(form::addRequest);
    actions.forEach(form::addAction);
  }

  public interface FormulaForm {
    Formula getFormula();

    void addRequest(String name, LabelTextFieldPane field);

    void addAction(String name, LabelTextFieldActionPane field);
  }

  public static class LabelTextFieldActionPane extends LabelTextFieldPane {
    private final FormulaAction action;

    public LabelTextFieldActionPane(String name, FormulaAction action) {
      super(name);
      this.action = action;
    }

    public void apply(InputValues values) {
      setValue(action.apply(values));
    }
  }

}
