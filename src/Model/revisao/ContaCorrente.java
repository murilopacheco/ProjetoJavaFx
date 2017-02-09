package Model.revisao;

/**
 * Created by murilo on 09/02/2017.
 */
public class ContaCorrente extends Conta {

    private String tipo;
    private double valorPacote;

    @Override
    public void imprimeExtrato() {
        System.out.println("Saldo: "+this.getSaldo());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(double valorPacote) {
        this.valorPacote = valorPacote;
    }


}
