package service;

import java.util.Collection;
import domain.Cliente;

public interface IClienteService {
    
    Boolean cadastrarCliente(Cliente cliente);
    Boolean  consultarCliente(Long cpf,  Boolean visivel);
    Boolean remover(Long cpf);
    Boolean atualizar(Cliente cliente);
    Collection<Cliente> consultarTodos();

}