package domain;

/* Classe abstrada que representa uma conta que serve de base para as classes conta corrente e conta poupança*/

public abstract class Conta implements IConta {

    protected  Long numero;
    protected  Long cpf;
    protected Double saldo;


    public Conta(Long numero, Long cpf, Double saldo) {
        this.numero = numero;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public Conta(Long numero, Long cpf) {
        this.numero = numero;
        this.cpf = cpf;
        this.saldo = 0D;
    }

    public Conta(Long numero, Double saldo) {
        this.saldo = saldo;
        this.numero = numero;
    }


    public Conta() {

    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Double getSaldo() {
        return saldo;
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

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", cpf =" + cpf +
                ", saldo=" + saldo +
                '}';
    }
}

