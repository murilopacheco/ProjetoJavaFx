package Controller;

import Model.Usuario;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by murilo on 16/05/2017.
 */
public class UsuarioController implements Initializable{


    Usuario usuario = new Usuario();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtUsuario;
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private ToggleButton tgButonAtivo;
    @FXML
    private ToggleSwitch tgSwAtivo;

    public void Salvar(){

    }

    public void pegaValores(){
        usuario.setNome(txtNome.getText());
        usuario.setLogin(txtUsuario.getText());
        usuario.setAtivo(tgSwAtivo.isSelected());

    }

    public void teste(Event e){
        if(tgSwAtivo.isSelected()){
            tgSwAtivo.setText("off");
        }else{
            tgSwAtivo.setText("on");
        }
    }

}
