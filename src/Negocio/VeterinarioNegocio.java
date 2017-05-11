package Negocio;

import Dao.ClienteDAO;
import Dao.VeterinarioDao;
import Model.Cliente;
import Model.Veterinario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muril on 16/03/2017.
 */
public class VeterinarioNegocio {
    VeterinarioDao veterinarioDao = new VeterinarioDao();

    public String salvar(Veterinario  veterinario) throws SQLException {

        String salvo = "falha";
        StringBuilder sb = new StringBuilder();

        if ((veterinario.getNome() == null) ||(veterinario.getNome().equals(""))) {
            sb.append("nome é obrigarório. \n");
        }
        if ((veterinario.getCRMV() == null) || (veterinario.getCRMV().equals(""))) {
            sb.append("CRMV obrigatório. \n");
        }
        if (sb.toString().isEmpty()) {
          salvo = veterinarioDao.salvar(veterinario);
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }

    public String editar(Veterinario veterinario) throws SQLException {

        String salvo = "falha";
        StringBuilder sb = new StringBuilder();

        if ((veterinario.getNome() == null) ||(veterinario.getNome().equals(""))) {
            sb.append("nome é obrigarório. \n");
        }
        if ((veterinario.getCRMV() == null) || (veterinario.getCRMV().equals(""))) {
            sb.append("CRMV obrigatório. \n");
        }
        if (sb.toString().isEmpty()) {
            salvo = veterinarioDao.editar(veterinario);
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }

    public List<Veterinario> listarVeterinarios(){
        List<Veterinario> veterinarios = new ArrayList<Veterinario>();
        veterinarios = veterinarioDao.listarVeterinarios();
        return veterinarios;
    }

}
