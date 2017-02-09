package Model.revisao;

/**
 * Created by murilo on 09/02/2017.
 */
public class ContaPoupanca  extends Conta{
    @Override
    public void imprimeExtrato() {
        System.out.println("Saldo: "+this.getSaldo());
    }

    private double rendimento;

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
}
