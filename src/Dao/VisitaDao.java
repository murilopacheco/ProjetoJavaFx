package Dao;

import Model.Cliente;
import Model.Pet;
import Model.Veterinario;
import Model.Visita;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murilo on 13/04/2017.
 */
public class VisitaDao {
    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public VisitaDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    String sqlSalvar = "insert into visitas" +
            "(id_pet, id_veterinario, dataVisita, anaminese, medicamentos)" +
            "values(?,?,?,?,?);";

    String sqlListarPorPets = "select vis.*, vet.nome as nomeVeterinario from visitas vis\n" +
            "    inner join veterinarios vet where vet.id = vis.id_Veterinario and vis.id_Pet = ?;";


    public String salvar(Visita visita, Pet pet, Veterinario veterinario) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setInt(1, visita.getId_pet());
            stmt.setInt(2, visita.getId_veterinario());
            stmt.setDate(3, Date.valueOf(visita.getDataVisita()));
            stmt.setString(4, visita.getAnaminese());
            stmt.setString(5, visita.getExamesEMedicamentos());

            stmt.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação! ");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação!"+e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
            System.out.println("erro na execução: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }

    public List<Visita> listarVisitasPorPet(Pet pet) {
        List<Visita> visitas = new ArrayList<Visita>();
        ResultSet res = null;

        try {

            stmt = con.prepareStatement(sqlListarPorPets);
            stmt.setInt(1, pet.getId());
            res = stmt.executeQuery();

            while (res.next()){

                Visita visita = new Visita();

                visita.setId(res.getInt("id"));
                Date dataVisita = res.getDate("dataVisita");
                visita.setDataVisita(dataVisita.toLocalDate());
                visita.setAnaminese(res.getString("anaminese"));
                visita.setExamesEMedicamentos(res.getString("medicamentos"));
                visita.setId_veterinario(res.getInt("id_veterinario"));
                visita.setNomeVeterinario(res.getString("nomeVeterinario"));
                visita.setId_pet(res.getInt("id_pet"));


                visitas.add(visita);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return visitas;
    }

}
