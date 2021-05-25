package com.github.itk.pbo2021.a.k2.tubes.contract;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

public abstract class Input {
  public abstract Value requestInteger(String name);

  public abstract Value requestDecimal(String name);

  public abstract Value request(String name, Pattern pattern);

  public abstract Value request(String name);

  /**
   * Add Formula actions with name
   *
   * @param name Formula's name
   * @param action Action
   * @return this
   */
  public abstract Input addAction(String name, FormulaAction action);

  public interface Value {

    /**
     * Get this value name
     * @return name
     */
    String getName();

    /**
     * Get this value description
     * @return description
     */
    String getDescription();

    /**
     * Set description for this value
     * @param description Value description
     * @return this
     */
    Value setDescription(String description);

    /**
     * Get default value
     * @return Default value
     */
    String getDefault();

    /**
     * Ask if this value has a default value
     * @return true if this value has default value
     */
    boolean hasDefault();

    /**
     * Set default value when value is empty or null
     * @param defVal default value
     * @return this
     */
    Value setDefault(String defVal);
  }

  public interface FormulaAction {
    String apply(InputValues values);
  }

  public abstract static class InputValues {
    /**
     * Get name's value
     *
     * @param name value's name
     * @return value
     */
    public abstract String get(String name);

    /**
     * Get value as BigInteger
     *
     * @param name value's name
     * @return value
     */
    public BigInteger getBigInteger(String name) {
      return new BigInteger(get(name));
    }

    /**
     * Get value as BigDecimal
     *
     * @param name value's name
     * @return value as BigDecimal
     */
    public BigDecimal getBigDecimal(String name) {
      return new BigDecimal(get(name));
    }

    /**
     * Get value as float
     *
     * @param name value's name
     * @return value as float
     */
    public float getFloat(String name) {
      return Float.parseFloat(get(name));
    }

    /**
     * Get value as double
     *
     * @param name value's name
     * @return value as double
     */
    public double getDouble(String name) {
      return Double.parseDouble(get(name));
    }

    public int getInt(String name) {
      return Integer.parseInt(get(name));
    }
  }
}
