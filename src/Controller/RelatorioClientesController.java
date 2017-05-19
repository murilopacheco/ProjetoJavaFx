package Controller;

import Model.Cliente;
import Negocio.ClienteNegocio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import relatorios.GeraRelatorios.RelatorioClientes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 19/05/2017.
 */
public class RelatorioClientesController implements Initializable{

    ClienteNegocio clienteNegocio = new ClienteNegocio();
    List<Cliente> clientes = new ArrayList<Cliente>();
    ObservableList<Cliente> clientesView ;
    RelatorioClientes relatorioClientes = new RelatorioClientes();


    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> clNome;
    @FXML
    private TableColumn<Cliente, String> clSobrenome;
    @FXML
    private TableColumn<Cliente, String> clCpf;
    @FXML
    private TableColumn<Cliente, Integer>  clCodigo;
    @FXML
    private Button btnListar;
    @FXML
    private Button btnGerarRelatorio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void listar(){
        clientes = clienteNegocio.listarCliente();
        if(!clientes.isEmpty()) {
            populaView(clientes);
        }
    }


    public void populaView(List<Cliente> clientes){
        clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        clCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        clCodigo.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        clSobrenome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sobrenome"));
        clientesView = FXCollections.observableArrayList(clientes);
        tblClientes.setItems(clientesView);

    }

    public void gerarRelatorio(){
        try {
            relatorioClientes.imprimir(clientes);
        }catch (Exception e ){
            System.out.println("erro: " + e.getMessage());
        }
    }

}
