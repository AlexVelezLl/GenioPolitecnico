/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akinator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alex Velez
 */
public class GenioPolitecnico extends Application {

    @Override
    public void start(Stage primaryStage) {
        VistaIndex vistaIndex = new VistaIndex();
        Scene sc = new Scene(vistaIndex.getRoot());
        primaryStage.setScene(sc);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
