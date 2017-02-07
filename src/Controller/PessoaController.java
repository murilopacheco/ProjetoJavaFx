package Controller;

import Model.Pessoa;
import Service.PessoaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Controller;

import java.net.URL;
import java.util.Date;
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
    TextField txtNome;
    @FXML
    TextField txtCPF;


    private PessoaService pessoaService;

    // Esse método é chamado ao inicializar a aplicação, igual um construtor.
    // Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pessoaService = PessoaService.getNewInstance();
    }

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Pessoa pessoa) {
        pessoa.setNome(txtNome.getText());
        pessoa.setCPF(txtCPF.getText());

    }
    @FXML
    private void Salvar(){
        Pessoa pessoa = new Pessoa();
        pegaValores(pessoa);
        pessoaService.Salvar(pessoa);
    }
}
