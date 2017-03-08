package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by murilo on 24/02/2017.
 */
public class InicialController implements Initializable {

    @FXML
    private MenuItem menuCadastrarCliente;
    @FXML
    private Pane paneInicial;


    public void abrirJanela(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/cliente.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
      //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
