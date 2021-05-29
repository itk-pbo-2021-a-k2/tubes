package com.github.itk.pbo2021.a.k2.tubes;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var input = new SystemInputRequest(scanner);
    input.requestInteger("Wew");
    input.requestDecimal("qwe");
    input.addAction("asd", values -> values.get("qwe"));
    var values = input.askInput();
    input.output(values);

    values = input.askInput();
    var map = values.getAll();
    input.output(values);
  }
}
