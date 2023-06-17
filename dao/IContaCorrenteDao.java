package dao;
import java.util.Collection;
import domain.ContaCorrente;

public interface IContaCorrenteDao {

    public Boolean cadastrarCC(ContaCorrente contaCorrente);
    public ContaCorrente consultarCC(Long numero);
    public ContaCorrente consultarCCCpf(Long cpf);
    public Boolean removerCC(Long numero);
    public void atualizarCC(ContaCorrente contaCorrente);
    public Collection<ContaCorrente> consultarTodasCC();
    
}

