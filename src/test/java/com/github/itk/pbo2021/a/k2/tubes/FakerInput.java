package com.github.itk.pbo2021.a.k2.tubes;

import com.github.itk.pbo2021.a.k2.tubes.contract.Input;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FakerInput extends Input {
  Map<String, FakerValue> values = new HashMap<>();
  Map<String, FormulaAction> actions = new HashMap<>();
  Faker faker = new Faker();

  @Override
  public Value requestInteger(String name) {
    var val = new FakerValue(name, Long.toString(faker.number().randomNumber()));
    return values.put(name, val);
  }

  @Override
  public Value requestDecimal(String name) {
    var val =
        new FakerValue(
            name, Double.toString(faker.number().randomDouble(10, Long.MIN_VALUE, Long.MAX_VALUE)));
    return values.put(name, val);
  }

  @Override
  public Value request(String name, Pattern pattern) {
    var val = new FakerValue(name, faker.regexify(pattern.pattern()));
    return values.put(name, val);
  }

  @Override
  public Value request(String name) {
    var val = new FakerValue(name, faker.random().hex());
    return values.put(name, val);
  }

  @Override
  public Input addAction(String name, FormulaAction action) {
    actions.put(name, action);
    return this;
  }

  public void output() {
    forEach(
        (name, value) -> System.out.printf("%s : %s%n", name, value),
        (name, value) -> System.out.printf("%s : %s%n", name, value));
  }

  public void forEach(FakerInputConsumer input, FakerValueConsumer value) {
    forEachInput(input);
    forEachValue(value);
  }

  public void forEachValue(FakerValueConsumer value) {
    var val = new FakerValues();
    actions.forEach((s, formulaAction) -> value.accept(s, formulaAction.apply(val)));
  }

  public void forEachInput(FakerInputConsumer input) {
    values.forEach((s, fakerValue) -> input.accept(s, fakerValue.getValue()));
  }

  public interface FakerInputConsumer {
    void accept(String name, String value);
  }

  public interface FakerValueConsumer {
    void accept(String name, String value);
  }

  public static class FakerValue implements Value {
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
    public Value setDefault(String defVal) {
      this.defVal = defVal;
      return this;
    }

    @Override
    public Value setDescription(String description) {
      this.description = description;
      return this;
    }
  }

  class FakerValues extends InputValues {
    Map<String, String> values = new HashMap<>();
    public FakerValues() {
      FakerInput.this.forEachValue((name, value) -> values.put(name, value));
    }

    @Override
    public String get(String name) {
      return values.get(name);
    }
  }
}
