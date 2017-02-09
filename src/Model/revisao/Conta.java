package Model.revisao;

/**
 * Created by murilo on 09/02/2017.
 */
public abstract class Conta {

    private int id;
    private double Saldo;
    private double lancamento;

    public abstract void imprimeExtrato();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double saldo) {
        Saldo = saldo;
    }

    public double getLancamento() {
        return lancamento;
    }

    public void setLancamento(double lancamento) {
        this.lancamento = lancamento;
    }
}
