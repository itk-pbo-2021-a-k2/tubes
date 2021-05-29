package com.github.itk.pbo2021.a.k2.tubes.fx;

import com.github.itk.pbo2021.a.k2.tubes.fisdas2.Fisdas2Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Objects;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    var frame = new MainPane();

    frame.addFormulaGroup(new Fisdas2Group());

    JMetro metro = new JMetro(Style.DARK);

    var scene = new Scene(frame);

    metro.setScene(scene);

    stage.setMaximized(true);
    stage.setTitle("Tubes");
    stage.setScene(scene);

    stage.show();
  }
}
