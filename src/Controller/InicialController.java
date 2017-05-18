package Controller;

import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
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

    Usuario usuario = new Usuario();

    @FXML
    private MenuItem menuCadastrarCliente;
    @FXML
    private Pane paneInicial;
    @FXML
    private  MenuItem menuCadastrarVeterinarios;
    @FXML
    private MenuItem menuCadastrarUsuario;
    @FXML
    private MenuBar menuPrincipal;




    public void abrirJanelaCliente(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/cliente.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
      //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }

    public void abrirJanelaPet(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/pet.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaVisita(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/visita.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaVeterinario(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/veterinario.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaUsuario(ActionEvent t) throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/usuario.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuPrincipal.setDisable(true);
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/login.fxml");
        Parent fxmlParent = null;
        try {
            fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void SetarUsuarioSessao(Usuario usuario){
        this.usuario = usuario;
        this.menuPrincipal.setDisable(false);
        paneInicial.getChildren().clear();

    }

}
