package Dao;

import Model.Cliente;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murilo on 06/04/2017.
 */
public class ClienteDao {

    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public ClienteDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }



    String sqlSalvar = "INSERT INTO petshop.clientes" +
      "(nome,sobrenome, CPF,rg,telefone,celular,endereco,email)" +
      "VALUES(?,?,?,?,?,?,?,?)" ;


    public String salvar(Cliente cliente) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getEmail());

            stmt.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação!"+e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }

    public String  Editar(Cliente cliente) {
        String salvo = "falha";
        try {
            String sql;
            sql  = "UPDATE clientes SET nome = ?, sobrenome = ?, cpf = ?, rg = ? , telefone = ?,celular = ?, endereco = ?, email = ?";
            sql += "WHERE id = ?";

            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getEmail());
            stmt.setInt(9, cliente.getId());


            stmt.executeUpdate();
            salvo = "salvo";

        } catch (SQLException e) {
            System.out.println("Erro na alteracao:" + e.getMessage());
        }

        return salvo;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> list = new ArrayList<Cliente>();
        ResultSet res = null;

        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM clientes");

            while (res.next()){

                Cliente cliente = new Cliente();

                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setSobrenome(res.getString("sobrenome"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setRg(res.getString("rg"));
                cliente.setEndereco(res.getString("endereco"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCelular(res.getString("celular"));
                cliente.setEmail(res.getString("email"));

                list.add(cliente);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }
}
