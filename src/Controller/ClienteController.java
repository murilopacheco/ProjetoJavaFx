package Controller;

import Model.Cliente;
import Service.PessoaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by murilo on 07/02/2017.
 */
public class ClienteController implements Initializable {


    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaTelefone;
    @FXML
    private TableColumn<Cliente, String> colunaCPF;
    @FXML
    private TableColumn<Cliente, Integer>  colunaId;
    @FXML
    private Cliente cliente;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtRg;



    private PessoaService pessoaService;
    List<Cliente> clientes = new ArrayList();

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Cliente cliente) {
        Random gerador = new Random();
        cliente.setId(gerador.nextInt());
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCpf.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setSobrenome(txtSobrenome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setRg(txtRg.getText());



    }
    @FXML
    private void salvar(){
        cliente = new Cliente();
        tabelaClientes = new TableView<Cliente>();
        pegaValores(cliente);
        clientes.add(cliente);
        colunaNome = new TableColumn<Cliente, String>("nome");
        colunaCPF = new TableColumn<Cliente, String>("cpf");
        colunaId = new TableColumn<Cliente, Integer>("id");
        colunaTelefone = new TableColumn<Cliente, String>("telefone");
        colunaNome.setCellValueFactory(new PropertyValueFactory<Cliente, String >("nome"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cpf"));
        colunaId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
       final ObservableList<Cliente> clientesView = FXCollections.observableArrayList(clientes);
        tabelaClientes.setItems(clientesView);
        tabelaClientes.getColumns().addAll(colunaId, colunaNome, colunaCPF, colunaTelefone);
        String teste  = "";
    }
}
