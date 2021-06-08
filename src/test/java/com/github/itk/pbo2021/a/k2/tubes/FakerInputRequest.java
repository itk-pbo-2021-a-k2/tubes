package com.github.itk.pbo2021.a.k2.tubes;

import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;
import com.github.itk.pbo2021.a.k2.tubes.contract.ValueProperty;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FakerInputRequest extends InputRequest {
  Map<String, FakerValue> values = new HashMap<>();
  Map<String, FormulaAction> actions = new HashMap<>();
  Faker faker = new Faker();

  @Override
  public ValueProperty requestInteger(String name) {
    var val = new FakerValue(name, Long.toString(faker.number().randomNumber()));
    return request(name,val);
  }

  @Override
  public ValueProperty requestDecimal(String name) {
    var val =
        new FakerValue(
            name, Double.toString(faker.number().randomDouble(10, Long.MIN_VALUE, Long.MAX_VALUE)));
    return request(name,val);
  }

  @Override
  public ValueProperty request(String name, Pattern pattern) {
    var val = new FakerValue(name, faker.regexify(pattern.pattern()));
    return request(name,val);
  }

  @Override
  public ValueProperty request(String name) {
    var val = new FakerValue(name, faker.random().hex());
    return request(name,val);
  }

  public ValueProperty request(String name, FakerValue val) {
    values.put(name,val);
    return val;
  }

  @Override
  public InputRequest addAction(String name, FormulaAction action) {
    actions.put(name, action);
    return this;
  }

  public void output() {
    forEach(
        (name, value) -> System.out.printf("%s : %s%n", name, value),
        (name, value) -> System.out.printf("%s : %s%n", name, value));
  }

  public void forEach(FakerRequestConsumer requestConsumer, FakerResultConsumer resultConsumer) {
    forEachRequest(requestConsumer);
    forEachResult(resultConsumer);
  }

  public void forEachRequest(FakerRequestConsumer requestConsumer) {
    values.forEach((s, fakerValue) -> requestConsumer.accept(s, fakerValue.getValue()));
  }

  public void forEachResult(FakerResultConsumer resultConsumer) {
    var val = new FakerValues();
    actions.forEach((s, formulaAction) -> resultConsumer.accept(s, formulaAction.apply(val)));
  }

  public interface FakerRequestConsumer {
    void accept(String name, String value);
  }

  public interface FakerResultConsumer {
    void accept(String name, String value);
  }

  public static class FakerValue implements ValueProperty {
    String name, defVal, description, value;

    public FakerValue(String name, String value) {
      this.name = name;
      this.value = value;
    }

    @Override
    public String getDefault() {
      return defVal;
    }

    @Override
    public boolean hasDefault() {
      return defVal != null;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getDescription() {
      return description;
    }

    @Override
    public ValueProperty setDefault(String defVal) {
      this.defVal = defVal;
      return this;
    }

    @Override
    public ValueProperty setDescription(String description) {
      this.description = description;
      return this;
    }
  }

  class FakerValues extends InputValues {
    @Override
    public String get(String name) {
      return values.get(name).getValue();
    }
  }
}
