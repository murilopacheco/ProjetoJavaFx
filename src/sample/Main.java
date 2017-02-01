package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage principal) throws Exception{
            VBox raiz = new VBox(10); // 1
            raiz.setAlignment(Pos.CENTER); // 2
            Label rotuloDemo = new Label("Sou um rótulo de texto!"); // 3
            rotuloDemo.setTooltip(new Tooltip(
                    "Esse é um rótulo para mostrar textos de forma simples")); // 4
            TextField campoTexto = new TextField(); // 5
            campoTexto.setTooltip(new Tooltip(
                    "Campo de texto para entrada de uma só linha "));
            TextArea areaTexto = new TextArea(); // 6
            areaTexto.setTooltip(new Tooltip(
                    "Campo de texto para entrada de múltiplas linhas"));
            Separator separadorHorizontal = new Separator(); // 7
            Separator separadorVertical = new Separator(Orientation.VERTICAL); // 8
            Slider deslizante = new Slider(); // 9
            deslizante.setShowTickLabels(true); // 10
            deslizante.setShowTickMarks(true); // 11
            deslizante
                    .setTooltip(new Tooltip(
                            "O controle deslizante tem um valor numérico de acordo com sua posição"));
            Button botao1 = new Button("Clique em mim! (Tratador externo)");
                 // usamos a classe TratadorEvento para cuidar dos eventos
                    botao1.setOnAction(new TratadorEvento());
            raiz.getChildren().addAll(rotuloDemo, campoTexto, areaTexto, botao1,
                    separadorVertical, separadorHorizontal, deslizante);
            Scene cena = new Scene(raiz, 300, 400);
             principal.setTitle("Janela Principal");
             principal.setScene(cena);
            principal.show();

    }

    public void handle(ActionEvent evento) {
        System.out.println("Evento tratado na próxima classe!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
