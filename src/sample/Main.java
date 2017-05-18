package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage principal) throws Exception{
            VBox raiz = new VBox(10); // 1
            raiz.setAlignment(Pos.CENTER); // 2
         URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/login.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
            Scene cena = new Scene(fxmlParent, 800, 600);
             principal.setTitle("Janela Principal");
             principal.setScene(cena);
             principal.setResizable(true);
            principal.show();


    }

    public void handle(ActionEvent evento) {
        System.out.println("Evento tratado na próxima classe!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
