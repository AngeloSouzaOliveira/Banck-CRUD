package service;

import java.util.Collection;
import domain.ContaPoupanca;

public interface IServiceContaPoupanca {

    public Boolean cadastrarCP(ContaPoupanca contaPoupanca);
    public Boolean consultarCP(Long numero, Boolean visivel);
    public Boolean removerCP(Long numero);
    public Boolean atualizarCP(ContaPoupanca contaPoupanca);
    public Collection<ContaPoupanca> consultarTodasCP();
    public Boolean depositar(Double valor, Long numeroConta);
    public Boolean sacar(Double valor, Long numeroConta);
    
}