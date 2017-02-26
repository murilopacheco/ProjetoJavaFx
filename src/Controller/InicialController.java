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
    void goTo(ActionEvent event){
        menuCadastrarCliente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) event.getTarget();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Visao/cliente.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(InicialController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void AbreTelaCadastroCliente() throws IOException {
        Stage stage = null;
        Parent root = FXMLLoader.load(getClass().getResource("/Visao/cliente.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();

    }

}
