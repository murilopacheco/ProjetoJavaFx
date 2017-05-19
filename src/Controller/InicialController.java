package Controller;

        import Model.Usuario;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.control.Control;
        import javafx.scene.control.MenuBar;
        import javafx.scene.control.MenuItem;
        import javafx.scene.layout.Pane;
        import sample.Main;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.ResourceBundle;

/**
 * Created by murilo on 24/02/2017.
 */
public class InicialController implements Initializable {

    static Usuario usuario = new Usuario();



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
    @FXML
    private MenuItem menuRelatorios;





    public void abrirJanelaCliente() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/cliente.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }

    public void abrirJanelaPet() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/pet.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaVisita() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/visita.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaVeterinario() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/veterinario.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);
        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaUsuario() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/usuario.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);

        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }
    public void abrirJanelaRelatorioClientes() throws IOException {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/relatorioCliente.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        paneInicial.getChildren().clear();
        paneInicial.getChildren().add(fxmlParent);

        //  Scene cena = new Scene(fxmlParent, 600, 400);
        //  ((Stage)paneInicial.getScene().getWindow()).setScene(cena);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        menuPrincipal.setDisable(false);
        formataAcessoUsuario(usuario);
    }
    @FXML
    public void SetarUsuarioSessao(Usuario usuario){
        this.usuario = usuario;

    }
    public void formataAcessoUsuario(Usuario usuario){
        if(usuario.getTipo().equals("veterinário")){
            menuCadastrarUsuario.setDisable(true);
            menuCadastrarVeterinarios.setDisable(true);
        }
    }
    public static void setarUsuario(Usuario user){
        usuario = user;
    }
}
