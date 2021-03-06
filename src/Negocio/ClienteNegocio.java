package Negocio;

import Dao.ClienteDao;
import Model.Cliente;
import relatorios.GeraRelatorios.RelatorioClientes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muril on 16/03/2017.
 */
public class ClienteNegocio {
    ClienteDao clienteDao = new ClienteDao();
    RelatorioClientes relatorio = new RelatorioClientes();

    public String salvar(Cliente cliente) throws SQLException {

        boolean cpfValido = false;
        boolean emailValido = false;
        String salvo = "falha";
        StringBuilder sb = new StringBuilder();
        cpfValido = validaCPF(cliente.getCpf());
        if (!cpfValido) {
            sb.append("cpf inválido. \n");
        }
        emailValido = isEmailValid(cliente.getEmail());
        if (!emailValido) {
            sb.append("email inválido. \n");
        }
        if (sb.toString().isEmpty()) {
          salvo = clienteDao.salvar(cliente);
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }

    public String editar(Cliente cliente) throws SQLException {

        boolean cpfValido = false;
        boolean emailValido = false;
        String salvo = "falha";
        StringBuilder sb = new StringBuilder();
        cpfValido = validaCPF(cliente.getCpf());
        if (!cpfValido) {
            sb.append("cpf inválido. \n");
        }
        emailValido = isEmailValid(cliente.getEmail());
        if (!emailValido) {
            sb.append("email inválido. \n");
        }
        if (sb.toString().isEmpty()) {
            salvo = clienteDao.editar(cliente);
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }

    public List<Cliente> listarCliente(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = clienteDao.listarClientes();
        return clientes;
    }

        public boolean validaCPF(String CPF){

// considera-se erro CPF's formados por uma sequencia de numeros iguais
            if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                    CPF.equals("22222222222") || CPF.equals("33333333333") ||
                    CPF.equals("44444444444") || CPF.equals("55555555555") ||
                    CPF.equals("66666666666") || CPF.equals("77777777777") ||
                    CPF.equals("88888888888") || CPF.equals("99999999999") ||
                    (CPF.length() != 11))
                return(false);

            char dig10, dig11;
            int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
// Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i=0; i<9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posicao de '0' na tabela ASCII)
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for(i=0; i<10; i++) {
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                    return(true);
                else return(false);
            } catch (InputMismatchException erro) {
                return(false);
            }
        }



        public  boolean isEmailValid(String email) {
            if ((email == null) || (email.trim().length() == 0))
                return false;

            String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
            Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }


}
