package dao;
import java.util.Collection;
import domain.Cliente;

public interface IClienteDao {
    
    public Boolean cadastrarCliente(Cliente cliente);
    public Cliente consultarCliente(Long cpf);
    public Boolean remover(Long cpf);
    public void atualizar(Cliente cliente);
    public Collection<Cliente> consultarTodos();

}