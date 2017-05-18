package Dao;

import Model.Pet;
import Model.Usuario;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muril on 17/05/2017.
 */
public class UsuarioDao {

    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public UsuarioDao() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    String sqlSalvar = "INSERT INTO usuarios" +
            "(nome,login, senha,ativo,tipo)" +
            "VALUES(?,?,?,?,?)";

    String sqlBucarPorLogin = " select * from usuarios where login = ?";

    public String salvar(Usuario usuario) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setBoolean(4, usuario.getAtivo());
            stmt.setString(5, usuario.getTipo());


            stmt.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação");
                    con.rollback();
                } catch (SQLException e2) {
                    System.err.print("Erro na transação!" + e2);
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

    public Usuario buscarPorLogin(String login) {
        List<Usuario> list = new ArrayList<Usuario>();
        ResultSet res = null;

        try {

            stmt = con.prepareStatement(sqlBucarPorLogin);
            stmt.setString(1, login);
            res = stmt.executeQuery();


            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setLogin(res.getString("login"));
                usuario.setSenha(res.getString("senha"));
                usuario.setAtivo(res.getBoolean("ativo"));
                usuario.setTipo(res.getString("tipo"));

                list.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list.get(0);
    }
}
