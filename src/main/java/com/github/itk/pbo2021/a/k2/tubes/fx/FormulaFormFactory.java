package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.ValueProperty;

import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.BigIntegerStringConverter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormulaFormFactory extends InputRequest {
  Map<String, LabelTextField> request = new LinkedHashMap<>();
  Map<String, LabelTextFieldAction> actions = new LinkedHashMap<>();

  @Override
  public ValueProperty request(String name) {
    return request(name, new LabelTextField(name));
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
    var form = new LabelTextFieldAction(name, action);
    form.setEditable(false);
    actions.put(name, form);
    return this;
  }

  @Override
  public ValueProperty requestDecimal(String name) {
    var form = new LabelTextField(name);
    form.setTextConverter(new BigDecimalStringConverter());
    return request(name, form);
  }

  @Override
  public ValueProperty requestInteger(String name) {
    var converter = new BigIntegerStringConverter();
    return request(name, converter);
  }

  public ValueProperty request(String name, LabelTextField pane) {
    request.put(name, pane);
    return pane;
  }

  public <T> ValueProperty request(String name, StringConverter<T> converter) {
    var form = new LabelTextField(name);
    form.setTextConverter(converter);
    return request(name, form);
  }

  public void apply(FormulaForm form) {
    form.getFormula().apply(this);
    request.forEach(form::addRequest);
    actions.forEach(form::addAction);
  }

}
