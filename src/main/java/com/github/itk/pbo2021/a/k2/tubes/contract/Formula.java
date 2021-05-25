package com.github.itk.pbo2021.a.k2.tubes.contract;

public interface Formula {

  String name();

  /**
   * Apply formula to input
   * @param input input for formula to apply to
   */
  void apply(Input input);
}
