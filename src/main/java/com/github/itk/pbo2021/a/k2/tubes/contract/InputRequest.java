package com.github.itk.pbo2021.a.k2.tubes.contract;

import java.util.regex.Pattern;

public abstract class InputRequest {
  public abstract ValueProperty requestInteger(String name);

  public abstract ValueProperty requestDecimal(String name);

  public abstract ValueProperty request(String name, Pattern pattern);

  public abstract ValueProperty request(String name);

  /**
   * Add Formula actions with name
   *
   * @param name   Formula's name
   * @param action Action
   * @return this
   */
  public abstract InputRequest addAction(String name, FormulaAction action);

    public abstract void setDescription(String s);

    public interface FormulaAction {
    String apply(InputValues values);
  }
}
