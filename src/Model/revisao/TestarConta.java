package Model.revisao;

/**
 * Created by murilo on 09/02/2017.
 */
public class TestarConta {
    public static void main(String[] args) {
        Conta cp = new ContaPoupanca();
        cp.setSaldo(2121);
        cp.imprimeExtrato();

    }
}
