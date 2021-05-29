package com.github.itk.pbo2021.a.k2.tubes;

import com.github.itk.pbo2021.a.k2.tubes.fisdas2.Fisdas2Group;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void shouldNotException() {

    try {

      var factory = new Fisdas2Group();
      FakerInputRequest faker = new FakerInputRequest();
      ZeroInputRequest zero = new ZeroInputRequest();

      for ( var formula : factory ) {
        formula.apply(faker);
        formula.apply(zero);

        System.out.println("Faker");
        faker.output();
        System.out.println("Zero");
        zero.output();
      }

    } catch (Exception ex) {
      ex.printStackTrace();
      Assert.fail(ex.getMessage());
    }
  }
}
