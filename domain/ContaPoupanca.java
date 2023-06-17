package domain;

/* Classe concreta que representa uma conta poupança*/

public class ContaPoupanca extends Conta {

    private  Double taxaJuros = 0.005;

    public ContaPoupanca() {
    }

    public ContaPoupanca(Long numero, Long cpf, Double saldo) {
        super(numero, cpf,saldo);
        this.taxaJuros  = 0.005;
        this.saldo = saldo;
    }

    public ContaPoupanca(Long numero, Double saldo, Double chequeEspecial) {
        super(numero, saldo);
        this.taxaJuros  = taxaJuros;
        this.saldo = saldo;
    }

    public ContaPoupanca(Long numero, Long cpf) {
        super(numero, cpf);
        this.taxaJuros  = 0.005;
        saldo=0D;
    }

    public ContaPoupanca(Long numero, Long cpf, Double saldo, Double taxaJuros) {
        super(numero, cpf, saldo);
        this.taxaJuros  = taxaJuros;
    }


    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }



    @Override
    public void sacar(Double value) {
        this.saldo = this.saldo  - value;
    }

    @Override
    public void depositar(Double value){
        this.saldo = this.saldo + value;
    }

}
