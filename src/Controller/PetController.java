package Controller;

import Model.Pessoa;
import Model.Pet;
import Service.PessoaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 07/02/2017.
 */
public class PetController implements Initializable {

        Pet pet =  new Pet();


    @FXML
    private TextField txtNome;
    @FXML
    private RadioButton radioMacho;
    @FXML
    private RadioButton radioFemea;
    @FXML
    private ToggleGroup groupSexo;
    private ComboBox<String> comboRaca;
    private TextField txtCor;
    private DatePicker dtDataNascimento;

    @FXML
    private void initialize() {
        List<String> racas = new ArrayList<String>();
        racas.add("Cão");
        racas.add("Gato");
        racas.add("passaro");
        comboRaca.getItems().addAll(racas);
    }

    private PessoaService pessoaService;
    ArrayList pessoas = new ArrayList();

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pessoaService = PessoaService.getNewInstance();
    }

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Pet pet) {
        pet.setNome(txtNome.getText());
        pet.setCor(txtCor.getText());
        pet.setDataNascimento(dtDataNascimento.getValue());
        pet.setRaca(comboRaca.getValue());



    }
    @FXML
    private void salvar(){
        pegaValores(pet);

        //pessoas.add(pessoa);
    }
}
