package Dao;

import Model.Pet;
import Model.Veterinario;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murilo on 13/04/2017.
 */
public class VeterinarioDao {
    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public VeterinarioDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    String sqlSalvar = "INSERT INTO veterinarios" +
            "(nome, CRMV, especialidade,sexo)" +
            "VALUES(?,?,?,?)" ;

    String sqlListar = "SELECT * FROM veterinarios";

    String sqlEditar = "UPDATE veterinarios SET nome = ?, CRMV = ?," +
            "especialidade = ?, sexo = ? WHERE id = ?";


    public String salvar(Veterinario veterinario) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCRMV());
            stmt.setString(3, veterinario.getEspecialidade());
            stmt.setString(4, veterinario.getSexo());



            stmt.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação ");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação!"+e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
            System.out.println("Erro na execução:" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }

    public List<Veterinario> listarVeterinarios() {
        List<Veterinario> list = new ArrayList<Veterinario>();
        ResultSet res = null;

        try {

            stm = con.createStatement();
            res = stm.executeQuery(sqlListar);

            while (res.next()){

                Veterinario veterinario = new Veterinario();

                veterinario.setId(res.getInt("id"));
                veterinario.setNome(res.getString("nome"));
                veterinario.setCRMV(res.getString("CRMV"));
                veterinario.setEspecialidade(res.getString("especialidade"));
                veterinario.setSexo(res.getString("sexo"));

                list.add(veterinario);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }

    public String editar(Veterinario veterinario) throws SQLException {
        String salvo = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlEditar);

            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCRMV());
            stmt.setString(3, veterinario.getEspecialidade());
            stmt.setString(4, veterinario.getSexo());
            stmt.setInt(5, veterinario.getId());

            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";


        }catch (Exception e){
            System.out.println("erro ao atualizar " + e.getMessage());
            salvo = e.getMessage();
        }
        return salvo;
    }

}
