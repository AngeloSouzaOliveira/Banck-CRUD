package service;

import java.util.Collection;
import domain.ContaCorrente;

public interface IContaCorrenteService {
    Boolean cadastrarCC(ContaCorrente contaCorrente);
    Boolean consultarCC(Long numero, Boolean visivel);
    Boolean atualizarCC(ContaCorrente contaCorrente,  Boolean visivel);
    Boolean  removerCC(Long numero);
    Collection<ContaCorrente> consultarTodasCC();
    public Boolean depositar(Double valor, Long numeroConta);
    public Boolean sacar(Double valor, Long numeroConta);

}
