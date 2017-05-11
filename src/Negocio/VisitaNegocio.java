package Negocio;

import Dao.VisitaDao;
import Model.Cliente;
import Model.Pet;
import Model.Veterinario;
import Model.Visita;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muril on 08/05/2017.
 */
public class VisitaNegocio {

    VisitaDao visitaDao = new VisitaDao();

    public String Salvar(Visita visita, Pet pet, Veterinario veterinario) throws SQLException {
        Boolean validacao = false;
        String salvo = "Falha";
        StringBuilder sb = new StringBuilder();
       if(pet == null){
            sb.append("Selecione o Animal. \n");
        }
        if(veterinario == null){
            sb.append("Efetue login!");
        }
        validacao = validaDataVsisita(visita);
        if(!validacao){
            sb.append("Data não pode ser menor que data atual");
        }
        if (sb.toString().isEmpty()) {
            salvo = visitaDao.salvar(visita, pet, veterinario);
        } else {
            sb.append(salvo);
            return sb.toString();
        }

        return salvo;
    }

    public List<Visita> listarVisitasPorPet(Pet pet){
        List<Visita> visitas = new ArrayList<Visita>();
        return visitaDao.listarVisitasPorPet(pet);
    }

    private Boolean validaDataVsisita(Visita visita) {
        if(visita.getDataVisita().isEqual(LocalDate.now())){
            return true;
        }else {
            return false;
        }
    }

}
