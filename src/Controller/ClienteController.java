package Controller;

import Model.Cliente;
import Service.PessoaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.*;

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
    @FXML
    private TextField txtId;
    @FXML
    private Button btnAcao;
    @FXML
    private Button btnLimpar;



    private PessoaService pessoaService;
    List<Cliente> clientes = new ArrayList();
    Main main = null;
    ObservableList<Cliente> clientesView = null;

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setVisible(false);
        cliente = new Cliente();

    }

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Cliente cliente) {
       cliente.setId(Integer.parseInt(txtId.getText()));
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCpf.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setSobrenome(txtSobrenome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setRg(txtRg.getText());
    }

    private void setaValores(Cliente cliente) {

        txtId.setText(String.valueOf(cliente.getId()));
        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtEndereco.setText(cliente.getEndereco());
        txtSobrenome.setText(cliente.getSobrenome());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtRg.setText(cliente.getRg());

    }

    public void limpaCampos() {

        txtId.setText("0");
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtSobrenome.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtRg.setText("");
    }

    @FXML
    private void salvar(){
        cliente = new Cliente();
        pegaValores(cliente);
        if(cliente.getId() == 0) {
            Random gerador = new Random();
            cliente.setId(gerador.nextInt());
            clientes.add(cliente);
            populaView(clientes);

            String msg = "Objeto inserido na tabela!";
            Notifications.create()
                    .title("Informações inseridas")
                    .text(msg)
                    .owner(main)
                    .hideAfter(Duration.seconds(3))
                    .darkStyle()
                    .position(Pos.TOP_RIGHT)
                    .showInformation();

            limpaCampos();
        }else {
            List<Cliente> clientesAuxRemove = new ArrayList<Cliente>();
            for(Cliente clienteLista  : clientes){
                if(clienteLista.getId() == cliente.getId()) {
                    clientesAuxRemove.add(clienteLista);
                }
            }
                clientes.removeAll(clientesAuxRemove);
                clientes.add(cliente);
                populaView(clientes);

            String msg = "Objeto editado com sucesso!";
            Notifications.create()
                    .title("Informações editadas")
                    .text(msg)
                    .owner(main)
                    .hideAfter(Duration.seconds(3))
                    .darkStyle()
                    .position(Pos.TOP_RIGHT)
                    .showInformation();

            limpaCampos();
            }

    }

    @FXML
    public void edit(){

        cliente = new Cliente();
        cliente = (Cliente) tabelaClientes.getSelectionModel().getSelectedItem();
        setaValores(cliente);
        btnAcao.setText("Editar");
        btnLimpar.setText("Cancelar");
        String msg  = "Cliente pronto para edição!";
        Notifications.create()
                .title("Informações inseridas")
                .text(msg)
                .owner(main)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();
    }

    public void populaView(List<Cliente> clientes){
        colunaNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        colunaId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
        clientesView = FXCollections.observableArrayList(clientes);
        tabelaClientes.setItems(clientesView);

    }
}

