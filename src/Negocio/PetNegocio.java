package Negocio;

import Dao.PetDao;
import Model.Cliente;
import Model.Pet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murilo on 13/04/2017.
 */
public class PetNegocio {
    PetDao petDao = new PetDao();

    public String Salvar(Pet pet, Cliente cliente) throws SQLException {
        Boolean validacao = false;
        String salvo = "Falha";
        StringBuilder sb = new StringBuilder();
        if(cliente == null){
            sb.append("Selecione o Cliente. \n");
        }
        validacao = validaNascimento(pet);
        if(!validacao){
            sb.append("Ano não pode ser menor que 1950");
        }
        if (sb.toString().isEmpty()) {
            salvo = petDao.salvar(pet, cliente);
        } else {
            sb.append(salvo);
            return sb.toString();
        }

        return salvo;
    }

    public boolean validaNascimento(Pet pet){
        Boolean anoValido = false;
        if(pet.getDataNascimento().getYear() > 1950){
            anoValido = true;
        }
        return anoValido;
    }

    public List<Pet> listarPetsPorCliente(Cliente cliente){
        List<Pet> pets = new ArrayList<Pet>();
        pets = petDao.listarPetsPorCliente(cliente);
        return pets;
    }
}
