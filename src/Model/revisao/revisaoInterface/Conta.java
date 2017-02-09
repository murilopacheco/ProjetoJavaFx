package Model.revisao.revisaoInterface;

/**
 * Created by murilo on 09/02/2017.
 */
public interface Conta{
        void depositar(double valor);
        void sacar(double valor);
        double getSaldo();
}
