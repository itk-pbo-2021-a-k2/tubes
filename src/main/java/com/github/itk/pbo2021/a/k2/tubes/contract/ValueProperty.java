package com.github.itk.pbo2021.a.k2.tubes.contract;

public interface ValueProperty {

  /**
   * Get this value name
   *
   * @return name
   */
  String getName();

  /**
   * Get this value description
   *
   * @return description
   */
  String getDescription();

  /**
   * Set description for this value
   *
   * @param description Value description
   * @return this
   */
  ValueProperty setDescription(String description);

  /**
   * Get default value
   *
   * @return Default value
   */
  String getDefault();

  /**
   * Set default value when value is empty or null
   *
   * @param defVal default value
   * @return this
   */
  ValueProperty setDefault(String defVal);

  /**
   * Ask if this value has a default value
   *
   * @return true if this value has default value
   */
  boolean hasDefault();
}
