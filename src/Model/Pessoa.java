package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Created by murilo on 07/02/2017.
 */
public class Pessoa {

    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty sobrenome;
    private StringProperty CPF;
    private StringProperty endereco;
    private StringProperty telefone;
    private ObjectProperty<LocalDate> dataNascimento;

    public Pessoa(IntegerProperty id, StringProperty nome, StringProperty sobrenome,
                  StringProperty cpf, StringProperty endereco, StringProperty telefone,
                  ObjectProperty<LocalDate> dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        CPF = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSobrenome() {
        return sobrenome.get();
    }

    public StringProperty sobrenomeProperty() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }

    public String getCPF() {
        return CPF.get();
    }

    public StringProperty CPFProperty() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF.set(CPF);
    }

    public String getEndereco() {
        return endereco.get();
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento.get();
    }

    public ObjectProperty<LocalDate> dataNascimentoProperty() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }
}
