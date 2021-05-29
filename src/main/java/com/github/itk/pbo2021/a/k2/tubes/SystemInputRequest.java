package com.github.itk.pbo2021.a.k2.tubes;

import com.github.itk.pbo2021.a.k2.tubes.contract.InputRequest;
import com.github.itk.pbo2021.a.k2.tubes.contract.InputValues;
import com.github.itk.pbo2021.a.k2.tubes.contract.ValueProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class SystemInputRequest extends InputRequest {
  Map<String, FormulaAction> actions = new HashMap<>();
  Map<String, Value> values = new HashMap<>();
  Scanner scanner;

  public SystemInputRequest(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public InputRequest addAction(String name, FormulaAction action) {
    actions.put(name, action);
    return this;
  }

  private void printInput(String name) {
    System.out.printf("Input %s : ", name);
  }

  @Override
  public Value requestInteger(String name) {
    var val =
        new Value(
            name,
            value -> {
              printInput(value.getName());
              return scanner.nextBigInteger().toString();
            });
    values.put(name, val);
    return val;
  }

  @Override
  public Value requestDecimal(String name) {
    var val =
        new Value(
            name,
            value -> {
              printInput(value.getName());
              return scanner.nextBigDecimal().toString();
            });
    values.put(name, val);
    return val;
  }

  @Override
  public Value request(String name, Pattern pattern) {
    var val =
        new Value(
            name,
            value -> {
              printInput(value.getName());
              return scanner.next(pattern);
            });
    values.put(name, val);
    return val;
  }

  @Override
  public Value request(String name) {
    var val =
        new Value(
            name,
            value -> {
              printInput(name);
              return scanner.next();
            });
    values.put(name, val);
    return val;
  }

  public void output(SystemValues values) {
    actions.forEach((s, action) -> System.out.printf("%s : %s%n", s, action.apply(values)));
  }

  public void forEachAction(BiConsumer<String, FormulaAction> action) {
    actions.forEach(action);
  }

  public SystemValues askInput() {
    values.forEach((s, value) -> value.input());
    return getLastInput();
  }

  public SystemValues getLastInput() {
    return new SystemValues();
  }

  public static class Value implements ValueProperty {
    String name, description, defVal, value;
    Function<Value, String> action;

    public Value(String name, Function<Value, String> action) {
      this.name = name;
      this.action = action;
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
    public Value setDescription(String description) {
      this.description = description;
      return this;
    }

    @Override
    public String getDefault() {
      return defVal;
    }

    @Override
    public boolean hasDefault() {
      return getDefault() != null;
    }

    @Override
    public ValueProperty setDefault(String defVal) {
      this.defVal = defVal;
      return this;
    }

    /**
     * Ask action to get value
     *
     * @return value from action
     */
    public String input() {
      value = action.apply(this);
      if (value == null || value.isEmpty()) value = getDefault();
      return value;
    }

    public String getValue() {
      return value;
    }
  }

  public class SystemValues extends InputValues {
    Map<String, String> values = new HashMap<>();

    public SystemValues() {
      SystemInputRequest.this.values.forEach((s, value) -> values.put(s, value.getValue()));
    }

    @Override
    public String get(String name) {
      return values.get(name);
    }

    public Map<String, String> getAll() {
      return Map.copyOf(values);
    }
  }
}
