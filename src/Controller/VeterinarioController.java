package Controller;

import Model.Cliente;
import Model.Pet;
import Model.Veterinario;
import Negocio.VeterinarioNegocio;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 11/05/2017.
 */
public class VeterinarioController  implements Initializable{

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCRMV;
    @FXML
    private TextField txtEspecialidade;
    @FXML
    private TextField txt;
    @FXML
    private Pane panePrincipal;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFeminino;
    @FXML
    private TableView<Veterinario> tblVeterinarios;
    @FXML
    private TableColumn<Veterinario, Integer> clCodigo;
    @FXML
    private TableColumn<Veterinario, String> clNome;
    @FXML
    private TableColumn<Veterinario, String > clEspecialidade;
    @FXML
    private TableColumn<Veterinario, String> clSexo;
    @FXML
    private TableColumn<Veterinario, String > clCRMV;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private  Button btnSalvar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        veterinarios = listarVeterinarios();
        populaView(veterinarios);
    }

    List<Veterinario> veterinarios = new ArrayList<>();
    ObservableList<Veterinario> veterinariosView;
    Veterinario veterinario = new Veterinario();
    VeterinarioNegocio veterinarioNegocio = new VeterinarioNegocio();



    public void salvar() throws SQLException {
        String validar;
        boolean validacao = false;
        pegaValores(veterinario);
        validacao = validarCampos();
        if(validacao) {
            if (veterinario.getId() == 0) {
                validar = veterinarioNegocio.salvar(veterinario);
                if(validar.equals("salvo")) {
                    veterinarios = new ArrayList<Veterinario>();
                    veterinarios = listarVeterinarios();
                    populaView(veterinarios);
                    String msg = "veterinário inserido!";
                    exibeMensagem(msg);
                    limpaCampos();
                    veterinario = new Veterinario();
                }else{
                    exibeMensagem(validar);
                }
            } else {
                validar = veterinarioNegocio.editar(veterinario);
                if (validar.equals("salvo")) {
                    veterinarios = listarVeterinarios();
                    populaView(veterinarios);
                    String msg = "Veterinário editado com sucesso!";
                    exibeMensagem(msg);
                    limpaCampos();
                    btnSalvar.setText("Salvar");
                    veterinario = new Veterinario();
                }else{
                    exibeMensagem(validar);
                }
            }
        }

    }

    public List<Veterinario> listarVeterinarios(){
        List<Veterinario> veterinarios = new ArrayList<Veterinario>();
        veterinarios = veterinarioNegocio.listarVeterinarios();
        return veterinarios;
    }

    private void pegaValores(Veterinario veterinario) {

        veterinario.setNome(txtNome.getText());
        veterinario.setCRMV(txtCRMV.getText());
        veterinario.setEspecialidade(txtEspecialidade.getText());
        RadioButton rb = new RadioButton();
        rb = (RadioButton) sexo.getSelectedToggle();
        veterinario.setSexo(rb.getText());

    }
    private void setaValores(Veterinario veterinario) {

        txtNome.setText(veterinario.getNome());
        txtCRMV.setText(veterinario.getCRMV());
        txtEspecialidade.setText(veterinario.getEspecialidade());

            }

    public void limpaCampos() {


        txtNome.setText("");
        txtCRMV.setText("");
        txtEspecialidade.setText("");
        }

    public  void populaView(List<Veterinario> veterinarios){
        clCodigo.setCellValueFactory(new PropertyValueFactory<Veterinario, Integer>("id"));
        clNome.setCellValueFactory(new PropertyValueFactory<Veterinario, String>("nome"));
        clCRMV.setCellValueFactory(new PropertyValueFactory<Veterinario, String>("CRMV"));
        clSexo.setCellValueFactory(new PropertyValueFactory<Veterinario, String>("sexo"));
        clEspecialidade.setCellValueFactory(new PropertyValueFactory<Veterinario, String>("especialidade"));
        veterinariosView = FXCollections.observableArrayList(veterinarios);
        tblVeterinarios.getItems().removeAll();
        tblVeterinarios.setItems(veterinariosView);
    }

    public boolean validarCampos(){

        String nome =  txtNome.getText();
        String CRMV = txtCRMV.getText();


        List<Control>  controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(nome.equals("") || nome == null){
            sb.append("O nome não pode ser vazio!. \n");
            controls.add(txtNome);
        }
        if(CRMV.equals("") || CRMV == null){
            sb.append("O CPF não pode ser vazio!. \n");
            controls.add(txtCRMV);
        }

        if(!sb.equals("")) {
            exibeMensagem(sb.toString());
            animaCamposValidados(controls);
        }

        return sb.toString().isEmpty();
    }

    public void animaCamposValidados(List<Control> controls) {
        controls.forEach(control -> {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
            rotateTransition.setFromAngle(-4);
            rotateTransition.setToAngle(4);
            rotateTransition.setCycleCount(8);
            rotateTransition.setAutoReverse(true);
            rotateTransition.setOnFinished((ActionEvent event1) ->{
                control.setRotate(0);
            });
            rotateTransition.play();
        });
        if(!controls.isEmpty()){
            controls.get(0).requestFocus();
        }
    }

    public void exibeMensagem(String msg){
        Notifications.create()
                .title("Atenção")
                .text(String.valueOf(msg))
                .owner(panePrincipal)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();


    }

    @FXML
    public void edit(){

        veterinario = new Veterinario();
        veterinario = (Veterinario) tblVeterinarios.getSelectionModel().getSelectedItem();
        setaValores(veterinario);
        btnSalvar.setText("Editar");
        String msg  = "Veterinário pronto para edição!";
        exibeMensagem(msg);
    }
}
