package Controller;

import Model.Cliente;
import Model.Pet;
import Model.Veterinario;
import Model.Visita;
import Negocio.ClienteNegocio;
import Negocio.PetNegocio;
import Negocio.VeterinarioNegocio;
import Negocio.VisitaNegocio;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by murilo on 04/05/2017.
 */
public class VisitaController implements Initializable {



    List<Pet> pets = new ArrayList<>();
    ClienteNegocio clienteNegocio = new ClienteNegocio();
    VisitaNegocio visitaNegocio = new VisitaNegocio();
    List<Cliente> clientes = new ArrayList<Cliente>();
    String filter = "";
    Cliente cliente = new Cliente();
    PetNegocio petNegocio = new PetNegocio();
    Pet pet = new Pet();
    Visita visita;
    Veterinario veterinario = new Veterinario();
    List<Visita> visitas = new ArrayList<Visita>();
    ObservableList<Visita> visitasView ;


    @FXML
    private TextArea txtAnaminese;
    @FXML
    private DatePicker dtDataVisita;
    @FXML
    private TextArea txtExames;
    @FXML
    private ComboBox comboCliente;
    @FXML
    private ComboBox comboPet;
    @FXML
    private Pane panePrincipal;
    @FXML
    private TableView tblVisitas;
    @FXML
    private TableColumn<Visita, LocalDate> clDataVisita;
    @FXML
    private TableColumn<Visita, String > clVeterinario;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientes = clienteNegocio.listarCliente();

        clientes.forEach( cliente -> {
                    comboCliente.getItems().add(
                            cliente.getNome() + " " + cliente.getSobrenome());
                }

        );
        dtDataVisita.setValue(LocalDate.now());
    }

    public void salvar() throws SQLException {
        String salvo = "Falha";
        visita = new Visita();
        pegaValores(visita);
        salvo = visitaNegocio.Salvar(visita, pet, veterinario);
        if(salvo.equals("salvo")) {
            listarVisitasPorPets();
            limpaCampos();
            mostrarMsg("salvo com sucesso!");
        }


    }

    private void limpaCampos() {
        txtAnaminese.setText("");
        txtExames.setText("");
        dtDataVisita.setValue(LocalDate.now());
    }

    private void pegaValores(Visita visita) {
        try{
            selecionaPet();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        if(pet != null) {
            visita.setId_pet(pet.getId());
        }
        visita.setDataVisita(LocalDate.now());
        visita.setAnaminese(txtAnaminese.getText());
        visita.setExamesEMedicamentos(txtExames.getText());

        //seta os dados do veterinário para testes ***apagar depois
        VeterinarioNegocio veterinarioNegocio = new VeterinarioNegocio();
        List<Veterinario> veterinarios = new ArrayList<Veterinario>();
        veterinarios = veterinarioNegocio.listarVeterinarios();
        Random r = new Random();
        Collections.shuffle(veterinarios);
        veterinario = veterinarios.get(0);
        if (veterinario != null){
            visita.setId_veterinario(veterinario.getId());
        }

    }

    public void listarPets() {
        SelecionaCliente();
        pets.clear();
        pets = petNegocio.listarPetsPorCliente(cliente);
        comboPet.getItems().clear();
        if (!pets.isEmpty()) {
            pets.forEach(p -> {
                comboPet.getItems().add(p.getNome());
            });
        }
    }

    public void SelecionaCliente(){
        comboPet.getItems().clear();
        List<Cliente> clienteList = new ArrayList<Cliente>();
        String nome = (String) comboCliente.getSelectionModel().getSelectedItem();
        clientes.forEach( (Cliente cliente) -> {
                    String nomeCompleto = cliente.getNome()+ " " + cliente.getSobrenome();
                    if(nome.equals(nomeCompleto)){
                        clienteList.add(cliente);
                    }
                }

        );
        cliente = clienteList.get(0);
    }
    public void selecionaPet(){
        if(!pets.isEmpty() && pets != null) {
            for (Pet p : pets) {
                if (comboPet.getSelectionModel().getSelectedItem().equals(p.getNome())) {
                    pet = p;
                }
            }
        }
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

    public void listarVisitasPorPets(){
        selecionaPet();
        visitas = new ArrayList<Visita>();
        visitas = visitaNegocio.listarVisitasPorPet(pet);
        populaView(visitas);

    }

    public  void populaView(List<Visita> visitas){
        clDataVisita.setCellValueFactory(new PropertyValueFactory<Visita, LocalDate>("dataVisita"));
        clVeterinario.setCellValueFactory(new PropertyValueFactory<Visita, String>("nomeVeterinario"));
        visitasView = FXCollections.observableArrayList(visitas);
        tblVisitas.getItems().removeAll();
        tblVisitas.setItems(visitasView);
    }
}

