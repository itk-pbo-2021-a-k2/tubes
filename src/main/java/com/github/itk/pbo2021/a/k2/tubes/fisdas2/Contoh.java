package com.github.itk.pbo2021.a.k2.tubes.fisdas2;

import com.github.itk.pbo2021.a.k2.tubes.contract.Formula;
import com.github.itk.pbo2021.a.k2.tubes.contract.Input;

/**
 * Contoh rumus
 *
 * <p>1. implement interface Formula di package contract
 */
public class Contoh implements Formula {

  /**
   * 2. Buat method name yang return Nama Modul
   *
   * @return Formula's name
   */
  @Override
  public String name() {
    return "Contoh";
  }

  /**
   * 3. Buat method apply untuk meminta input PS: di paling bawah masih ada
   *
   * @param input input for formula to apply to
   */
  @Override
  public void apply(Input input) {
    // minta input integer dengan nama foo
    input.requestInteger("foo");
    // minta input bilangan desimal dengan nama bar
    input.requestDecimal("bar");

    // bisa buat method sendiri kayak gini
    // ini akan memanggil method contoh
    input.addAction("rumus", this::contoh);

    // atau menggunakan lambda
    // seperti ini
    // ini juga akan memanggil method contoh
    input.addAction("contoh kedua", values -> contoh(values));

    // atau seperti ini
    input.addAction(
        "contoh ketiga",
        values -> values.getBigDecimal("foo").multiply(values.getBigDecimal("bar")).toString());

    // atau seperti ini
    input.addAction(
        "contoh keempat",
        values -> {
          return Double.toString(values.getDouble("foo") * values.getDouble("bar"));
        });
  }

  public String contoh(Input.InputValues values) {
    // ambil input foo
    // as double
    var fooDouble = values.getDouble("foo");
    // as int
    var fooInt = values.getInt("foo");
    // as Big Integer
    var fooBigInt = values.getBigInteger("foo");
    // as Big Decimal
    var fooBigDecimal = values.getBigDecimal("foo");
    // as float
    var fooFloat = values.getFloat("foo");

    // ambil input bar
    // kalo pake inputDecimal di atas, jangan pakai getInt atau getBigInteger
    // as double
    var barDouble = values.getDouble("bar");
    // as float
    var barFloat = values.getFloat("bar");
    // as Big Decimal
    var barBigDecimal = values.getBigDecimal("bar");

    // return harus String
    // abaikan if ( true )
    // return BigDecimal
    if (true) return barBigDecimal.multiply(fooBigDecimal).toString();

    // return float
    if (true) return Float.toString(fooFloat * barFloat);

    // return Double
    if (true) return Double.toString(fooDouble * barDouble);

    // konversi int ke double
    if (true) return Double.toString((double) fooInt * barDouble);

    // konversi double ke int ( narrowing )
    if (true) return Integer.toString(fooInt * (int) barDouble);

    return "";
  }
}

// 4. Masukkan Class ke dalam ModulFactory
// 5. Jalankan AppTest di folder test/java/.../
// 6. Pastikan tidak ada error
// 7. Apabila sudah tidak ada error, buat branch baru di git. di intellij lewat
// git->new branch->isi nama branch dengan nama modul
// 8. commit codenya
