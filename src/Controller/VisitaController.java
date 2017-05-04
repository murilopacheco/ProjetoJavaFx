package Controller;

import Model.Cliente;
import Model.Pet;
import Negocio.ClienteNegocio;
import Negocio.PetNegocio;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 04/05/2017.
 */
public class VisitaController implements Initializable {



    List<Pet> pets = new ArrayList<>();
    ClienteNegocio clienteNegocio = new ClienteNegocio();
    List<Cliente> clientes = new ArrayList<Cliente>();
    String filter = "";
    private ObservableList<String> originalItems;
    Cliente clienteSelecionado = new Cliente();
    PetNegocio petNegocio = new PetNegocio();
    Pet pet = new Pet();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientes = clienteNegocio.listarCliente();

        clientes.forEach( cliente -> {
                    comboCliente.getItems().add(cliente.getNome() + " " + cliente.getSobrenome());
                }

        );
    }

    public void listarPets(){
        Cliente cliente = new Cliente();
        cliente = SelecionaUsuario();
        pets = petNegocio.listarPetsPorCliente(cliente);
        if(!pets.isEmpty()) {
           pets.forEach(pet -> {
               comboPet.getItems().add(pet.getNome());
           });
        }
    }

    public Cliente SelecionaUsuario(){

        List<Cliente> clienteList = new ArrayList<Cliente>();
        String nome = (String) comboCliente.getSelectionModel().getSelectedItem();
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
    public void SelecionaPet(){
        for (Pet p : pets) {
            if(comboPet.getSelectionModel().getSelectedItem().equals(p.getNome())){
                pet = p;
            }
        }
    }
}
