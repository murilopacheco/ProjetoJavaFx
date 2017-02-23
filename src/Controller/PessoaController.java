package Controller;

import Model.Pessoa;
import Service.PessoaService;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by murilo on 07/02/2017.
 */
public class PessoaController implements Initializable {


    @FXML
    private TableView<Pessoa> tabelaPessoas;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableColumn<Pessoa, String> colunaSobreNome;
    @FXML
    private TableColumn<Pessoa, String> colunaCPF;
    @FXML
    private TableColumn<Pessoa, Date>  colunaDataNascimento;
    @FXML
    private Pessoa pessoa;
    @FXML
    private TextField nome;
    @FXML
    private TextField CPF;
    @FXML
    private TextField endereco;
    @FXML
    private TextField sobrenome;
    @FXML
    private TextField telefone;




    @FXML
    private void initialize() {
        pessoa = new Pessoa();
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
    private void pegaValores(Pessoa pessoa) {
        pessoa.setNome(nome.getText());
        pessoa.setCPF(CPF.getText());
        pessoa.setEndereco(endereco.getText());
        pessoa.setSobrenome(sobrenome.getText());
        pessoa.setTelefone(telefone.getText());


    }
    @FXML
    private void Salvar(){
        pegaValores(pessoa);
        pessoaService.Salvar(pessoa);
        pessoas.add(pessoa);
    }
}
