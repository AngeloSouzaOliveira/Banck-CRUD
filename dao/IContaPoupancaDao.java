package dao;
import java.util.Collection;
import domain.ContaPoupanca;

public interface IContaPoupancaDao {
    
    public Boolean cadastrarCP(ContaPoupanca contaPoupanca);
    public ContaPoupanca consultarCP(Long numero);
    public ContaPoupanca consultarCPCpf(Long cpf);
    public Boolean removerCP(Long numero);
    public void atualizarCP(ContaPoupanca contaPoupanca);
    public Collection<ContaPoupanca> consultarTodasCP();

}
