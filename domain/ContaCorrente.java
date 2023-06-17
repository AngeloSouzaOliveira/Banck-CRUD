package domain;

/* Classe concreta que representa uma conta corrente*/

public class ContaCorrente extends Conta {

    private Double chequeEspecial = 500.0D;


    public ContaCorrente() {
        super();
    }

    public ContaCorrente(Long numero, Double saldo,Double chequeEspecial) {
        super(numero, saldo);
        this.chequeEspecial= chequeEspecial;
    }

    public ContaCorrente(Long numero, Long cpf) {
        super(numero, cpf);
        saldo=0D;
        this.chequeEspecial= 500.0D;
    }

    public ContaCorrente(Long numero, Long cpf, Double saldo, Double cheque_especial) {
        super(numero, cpf, saldo);
        this.setNumero(numero);
        this.setCpf(cpf);
        this.setSaldo(saldo);
        this.setChequeEspecial(cheque_especial);
    }

    public Double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public Double getSaldo(){
        return saldo ;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void sacar(Double value){
        this.saldo = this.saldo - value;
    }

    public void depositar(Double value){
        this.saldo = this.saldo + value;
    }



}
