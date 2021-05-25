package com.github.itk.pbo2021.a.k2.tubes;

import com.github.itk.pbo2021.a.k2.tubes.contract.Input;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ZeroInput extends Input {
  private final Map<String, Value> names = new HashMap<>();
  private final Map<String, FormulaAction> actions = new HashMap<>();

  @Override
  public Value requestInteger(String name) {
    var val = new Value(name);
    names.put(name, val);
    return val;
  }

  @Override
  public Value requestDecimal(String name) {
    var val = new Value(name);
    names.put(name, val);
    return val;
  }

  @Override
  public Value request(String name, Pattern pattern) {
    var val = new Value(name);
    names.put(name, val);
    return val;
  }

  @Override
  public Value request(String name) {
    var val = new Value(name);
    names.put(name, val);
    return val;
  }

  @Override
  public Input addAction(String name, FormulaAction action) {
    actions.put(name, action);
    return this;
  }

  public void output() {
    actions.forEach(
        (s, action) -> System.out.printf("%s : %s%n", s, action.apply(new ZeroValues())));
  }

  static class Value implements Input.Value {
    String defVal, name, description;

    public Value(String name) {
      this.name = name;
    }

    @Override
    public String getDefault() {
      return defVal;
    }

    @Override
    public Input.Value setDefault(String defVal) {
      this.defVal = defVal;
      return this;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public boolean hasDefault() {
      return defVal != null;
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
  }

  static class ZeroValues extends InputValues {
    @Override
    public String get(String name) {
      return "0";
    }
  }
}
