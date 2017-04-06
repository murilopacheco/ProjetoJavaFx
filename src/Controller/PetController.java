package Controller;

import Model.Pet;
import Service.PessoaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 07/02/2017.
 */
public class PetController implements Initializable {
        Integer id = 0;
        Pet pet =  new Pet();
        ObservableList<Pet> petView = null;
        List<Pet> pets = new ArrayList<>();


    @FXML
    private TextField txtNome;
    @FXML
    private RadioButton radioMacho;
    @FXML
    private RadioButton radioFemea;
    @FXML
    private ToggleGroup groupSexo;
    @FXML
    private ComboBox<String> comboRaca;
    @FXML
    private TextField txtCor;
    @FXML
    private DatePicker dtDataNascimento;
    @FXML
    private TableView<Pet> tblPets;
    @FXML
    private TableColumn<Pet,Integer> clCodigo;
    @FXML
    private TableColumn<Pet,String> clNome;
    @FXML
    private TableColumn<Pet,String> clRaca;
    @FXML
    private TableColumn<Pet,String> clSexo;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAlterar;





    private PessoaService pessoaService;
    ArrayList pessoas = new ArrayList();

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<String> racas = new ArrayList<String>();
        racas.add("Cão");
        racas.add("Gato");
        racas.add("passaro");
        comboRaca.getItems().addAll(racas);
    }

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Pet pet) {
        pet.setNome(txtNome.getText());
        pet.setCor(txtCor.getText());
        pet.setDataNascimento(dtDataNascimento.getValue());
        pet.setRaca(comboRaca.getValue());
        RadioButton rb = new RadioButton();
        rb =(RadioButton) groupSexo.getSelectedToggle();
        pet.setSexo(rb.getText());



    }


    public void salvar(){
        pet = new Pet();
        pegaValores(pet);
        if(id == 0){
            id = id + 1;
        }
        pet.setId(id);
        id = id + 1;
        pets.add(pet);
        populaView(pets);
        limpaCampos();
        mostrarMsg("Salvo com sucesso!");
    }

    public void alterar() {
        pegaValores(pet);
        List<Pet> petAuxRemove = new ArrayList<Pet>();
        for (Pet p : pets) {
            if (p.getId() == pet.getId()) {
                petAuxRemove.add(p);
            }
            pets.removeAll(petAuxRemove);
            pets.add(pet);
            populaView(pets);
            limpaCampos();
            mostrarMsg("Pet editado com sucesso");
        }
    }
    public  void populaView(List<Pet> pets){
        clCodigo.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        clNome.setCellValueFactory(new PropertyValueFactory<Pet, String>("nome"));
        clRaca.setCellValueFactory(new PropertyValueFactory<Pet, String>("raca"));
        clSexo.setCellValueFactory(new PropertyValueFactory<Pet, String>("sexo"));
        petView = FXCollections.observableArrayList(pets);
        tblPets.setItems(petView);
    }
    public void limpaCampos(){
        txtNome.setText("");
        txtCor.setText("");
        dtDataNascimento.setValue(null);
        comboRaca.setValue("");
        btnSalvar.setDisable(false);
        btnAlterar.setDisable(true);
    }

    public void mostrarMsg(String msg){
        Notifications.create()
                .title("Informação")
                .text(msg)
                .owner(panePrincipal)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showConfirm();
    }
    public void prepararEdicao(){
        pet = new Pet();
        pet = tblPets.getSelectionModel().getSelectedItem();
        setarDadosTela(pet);
        mostrarMsg("Pet pronto para edição");
        btnSalvar.setDisable(true);
        btnAlterar.setDisable(false);
    }
    public void setarDadosTela(Pet pet){
        txtNome.setText(pet.getNome());
        txtCor.setText(pet.getCor());
        dtDataNascimento.setValue(pet.getDataNascimento());
        comboRaca.setValue(pet.getRaca());
        if(pet.getSexo().equals("Macho")) {
            groupSexo.selectToggle(radioMacho);
        }else{
            groupSexo.selectToggle(radioFemea);
        }
    }
}
