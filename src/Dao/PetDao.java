package Dao;

import Model.Cliente;
import Model.Pet;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by murilo on 13/04/2017.
 */
public class PetDao {
    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public PetDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    String sqlSalvar = "INSERT INTO petshop.pets" +
            "(nome, cor, raca,dataNascimento ,sexo, id_Cliente)" +
            "VALUES(?,?,?,?,?,?)" ;

    String sqlListarPorCliente = "SELECT * FROM pets WHERE id_Cliente = ?";


    public String salvar(Pet pet, Cliente cliente) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getCor());
            stmt.setString(3, pet.getRaca());
            stmt.setDate(4, Date.valueOf(pet.getDataNascimento()));
            stmt.setString(5, pet.getSexo());
            stmt.setInt(6, cliente.getId());


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

    public List<Pet> listarPetsPorCliente(Cliente cliente) {
        List<Pet> list = new ArrayList<Pet>();
        ResultSet res = null;

        try {

            stmt = con.prepareStatement(sqlListarPorCliente);
            stmt.setInt(1, cliente.getId());
            res = stmt.executeQuery();

            while (res.next()){

                Pet pet = new Pet();

                pet.setId(res.getInt("id"));
                pet.setNome(res.getString("nome"));
                pet.setRaca(res.getString("raca"));
                pet.setCor(res.getString("cor"));
                pet.setSexo(res.getString("sexo"));
                Date dataNascimento = res.getDate("dataNascimento");
                pet.setDataNascimento(dataNascimento.toLocalDate());

                list.add(pet);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }

}
