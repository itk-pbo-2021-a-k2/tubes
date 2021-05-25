package com.github.itk.pbo2021.a.k2.tubes.contract;

import java.util.List;

public interface FormulaFactory extends Iterable<Formula> {
  /**
   * Get formula
   * @return immutable list of Formula
   */
  List<Formula> get();

  /**
   * Get group name
   * @return Factory's group name
   */
  String name();
}
