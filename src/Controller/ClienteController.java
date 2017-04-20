package Controller;

import Model.Cliente;
import Negocio.ClienteNegocio;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.Main;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by murilo on 07/02/2017.
 */
public class ClienteController implements Initializable {

   // private final static int rowsPerPage = 1000;


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
    private TextField txtEmail;
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




    List<Cliente> clientes = new ArrayList();
    Main main = null;
    ObservableList<Cliente> clientesView = null;
    ClienteNegocio clienteNegocio = new ClienteNegocio();

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setVisible(false);
        cliente = new Cliente();
        clientes = listarClientes();
        populaView(clientes);

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
        cliente.setEmail(txtEmail.getText());
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
        txtEmail.setText(cliente.getEmail());

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
        txtEmail.setText("");
    }

    @FXML
    private void salvar() throws SQLException {
        String validar;
        boolean validacao = false;
        cliente = new Cliente();
        pegaValores(cliente);
        validacao = validarCampos();
        if(validacao) {
            if (cliente.getId() == 0) {
                 validar = clienteNegocio.salvar(cliente);
                if(validar.equals("salvo")) {
                    clientes = listarClientes();
                    populaView(clientes);

                    String msg = "Cliente inserido!";
                    exibeMensagem(msg);
                    limpaCampos();
                }else{
                    exibeMensagem(validar);
                }
            } else {
                validar = clienteNegocio.editar(cliente);
                if (validar.equals("salvo")) {
                    clientes = listarClientes();
                    populaView(clientes);
                    String msg = "Objeto editado com sucesso!";
                    exibeMensagem(msg);

                    limpaCampos();
                    btnAcao.setText("Salvar");
                }else{
                    exibeMensagem(validar);
                }
            }
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
        exibeMensagem(msg);
    }

    public void populaView(List<Cliente> clientes){
        colunaNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        colunaId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
        clientesView = FXCollections.observableArrayList(clientes);
        tabelaClientes.setItems(clientesView);

    }
    public List<Cliente> listarClientes(){
        clientes = clienteNegocio.listarCliente();
        return clientes;
    }

    public boolean validarCampos(){

            String nome =  txtNome.getText();
            String cpf = txtCpf.getText();
            String endereco =  txtEndereco.getText();
            String sobrenome =  txtSobrenome.getText();
            String telefone =  txtTelefone.getText();
            String celular =  txtCelular.getText();
            String rg =  txtRg.getText();
            String email = txtEmail.getText();

            List<Control>  controls = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append("");
            if(nome.equals("") || nome == null){
                sb.append("O nome não pode ser vazio!. \n");
                controls.add(txtNome);
            }
            if(cpf.equals("") || cpf == null){
                sb.append("O CPF não pode ser vazio!. \n");
                controls.add(txtCpf);
            }
            if(email.equals("") || email == null){
                sb.append("O E-mail não pode ser vazio!. \n");
                controls.add(txtEmail);
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
                .owner(main)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();


    }

}

