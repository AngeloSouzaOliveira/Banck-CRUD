package domain;

/* Classe concreta que representa um cliente*/

public class Cliente implements ICliente {
    private String nome;
    private Long cpf;

    public Cliente(String nome, Long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return this.cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }


    public String toString() {
        return "Cliente{nome='" + this.nome + "', cpf='" + this.cpf + "'}";
    }
}
