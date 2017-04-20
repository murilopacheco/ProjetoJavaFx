package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by murilo on 23/02/2017.
 */
public class Cliente {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty nome = new SimpleStringProperty();
    private final SimpleStringProperty sobrenome = new SimpleStringProperty();
    private final SimpleStringProperty rg = new SimpleStringProperty();
    private final SimpleStringProperty cpf = new SimpleStringProperty();
    private final SimpleStringProperty endereco = new SimpleStringProperty();
    private final SimpleStringProperty telefone = new SimpleStringProperty();
    private final SimpleStringProperty celular = new SimpleStringProperty();
    private final SimpleStringProperty email = new SimpleStringProperty();
    private List<Pet> pets;

    public Cliente(String cpf, String nome, String telefone, String celular, String endereco, String rg) {
        this.nome.set(nome);
        this.cpf.set(cpf);
        this.rg.set(rg);
        this.endereco.set(endereco);
        this.telefone.set(telefone);
        this.celular.set(celular);

    }

    public Cliente() {
        
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSobrenome() {
        return sobrenome.get();
    }

    public SimpleStringProperty sobrenomeProperty() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }

    public String getRg() {
        return rg.get();
    }

    public SimpleStringProperty rgProperty() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg.set(rg);
    }

    public String getCpf() {
        return cpf.get();
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getEndereco() {
        return endereco.get();
    }

    public SimpleStringProperty enderecoProperty() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public String getCelular() {
        return celular.get();
    }

    public SimpleStringProperty celularProperty() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
