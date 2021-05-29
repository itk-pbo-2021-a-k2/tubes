package com.github.itk.pbo2021.a.k2.tubes.contract;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class InputValues {
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

  /**
   * Get value as int
   *
   * @param name value's name
   * @return value as int
   */
  public int getInt(String name) {
    return Integer.parseInt(get(name));
  }
}
