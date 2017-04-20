package Controller;

import Model.Cliente;
import Model.Pet;
import Negocio.ClienteNegocio;
import Negocio.PetNegocio;
import Service.PessoaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by murilo on 07/02/2017.
 */
public class PetController implements Initializable {
        Integer id = 0;
        Pet pet =  new Pet();
        ObservableList<Pet> petView = null;
        List<Pet> pets = new ArrayList<>();
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        List<Cliente> clientes = new ArrayList<Cliente>();
        String filter = "";
        private ObservableList<String> originalItems;
        Cliente clienteSelecionado = new Cliente();
        PetNegocio petNegocio = new PetNegocio();
        List<Pet> petsPorCliente = new ArrayList<Pet>();


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
    @FXML
    private ComboBox<String> ComboCliente;





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

        clientes = clienteNegocio.listarCliente();

        clientes.forEach( cliente -> {
            ComboCliente.getItems().add(cliente.getNome() + " " + cliente.getSobrenome());
                }

        );




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


    public void salvar() throws SQLException{
        String salvo = "Falha";
        pet = new Pet();
        pegaValores(pet);
        clienteSelecionado = SelecionaUsuario();
        salvo = petNegocio.Salvar(pet, clienteSelecionado);
        listarPets();
        limpaCampos();
        mostrarMsg(salvo);
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
        tblPets.getItems().removeAll();
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

    public void AutoCompleteCliente(Event e){
        originalItems = FXCollections.observableArrayList(ComboCliente.getItems());
        ComboCliente.setTooltip(new Tooltip());
        ComboCliente.setOnKeyPressed(this::handleOnKeyPressed);
        ComboCliente.setOnHidden(this::handleOnHiding);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        ComboCliente.getTooltip().hide();
        String s = ComboCliente.getSelectionModel().getSelectedItem();
        ComboCliente.getItems().setAll(originalItems);
        ComboCliente.getSelectionModel().select(s);
    }

    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            ComboCliente.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            ComboCliente.getTooltip().hide();
        } else {
            Stream<String> itens = ComboCliente.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            ComboCliente.getTooltip().setText(txtUsr);
            Window stage = ComboCliente.getScene().getWindow();
            double posX = stage.getX() + ComboCliente.getBoundsInParent().getMinX();
            double posY = stage.getY() + ComboCliente.getBoundsInParent().getMinY();
            ComboCliente.getTooltip().show(stage, posX, posY);
            ComboCliente.show();
        }
        ComboCliente.getItems().setAll(filteredList);
    }

    public Cliente SelecionaUsuario(){

        List<Cliente> clienteList = new ArrayList<Cliente>();
        String nome = ComboCliente.getSelectionModel().getSelectedItem();
        clientes.forEach( (Cliente cliente) -> {
           String nomeCompleto = cliente.getNome()+ " " + cliente.getSobrenome();
           if(nome.equals(nomeCompleto)){
               clienteList.add(cliente);
           }
                }

        );
        clienteSelecionado = clienteList.get(0);
        return  clienteSelecionado;
    }
    public void listarPets(){
        Cliente cliente = new Cliente();
        cliente = SelecionaUsuario();
        petsPorCliente = petNegocio.listarPetsPorCliente(cliente);
        if(!petsPorCliente.isEmpty()) {
            populaView(petsPorCliente);
        }else{
            tblPets.getItems().clear();
        }
    }

}
