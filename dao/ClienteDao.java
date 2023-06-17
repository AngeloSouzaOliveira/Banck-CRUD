package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Data.DbContext;
import domain.Cliente;

/*
* Implementa a interface IClienteDao para realizar operações de
* acesso a dados relacionadas a clientes no banco de dados.
* */

public class ClienteDao implements IClienteDao {

    private DbContext dbContext;

    public ClienteDao(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public Boolean cadastrarCliente(Cliente cliente) {

        String nome = cliente.getNome();
        Long cpf = cliente.getCpf();

        try {
            dbContext.conectarBanco();
            String query = String.format("INSERT INTO clientes (name, cpf) VALUES ('%s', '%s')", nome, cpf);
             dbContext.executarUpdateSql(query);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            dbContext.desconectarBanco();
        }

    }

    @Override
    public Cliente consultarCliente(Long cpf) {
        Cliente clienteConsulta = new Cliente();
        dbContext.conectarBanco();

        try{
            String sql = String.format("SELECT * FROM clientes WHERE cpf = %s", cpf);
            ResultSet clienteData = dbContext.executarQuerySql(sql);

            if(clienteData.next()){
                clienteConsulta.setNome(clienteData.getString("name"));
                clienteConsulta.setCpf(clienteData.getLong("cpf"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            clienteConsulta = null;

        } finally {
            dbContext.desconectarBanco();
        }

        return clienteConsulta;
    }

    @Override
    public Boolean remover(Long cpf) {

        dbContext.conectarBanco();
        try {
            String query = String.format("DELETE FROM clientes WHERE cpf= '%s'", cpf);
            dbContext.executarUpdateSql(query);
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            dbContext.desconectarBanco();
        }

    }

    @Override
    public void atualizar(Cliente cliente) {
        dbContext.conectarBanco();

        // verificar como atualizar o CPF

        String nome = cliente.getNome();
        Long cpf = cliente.getCpf();


        try{
            String query = String.format("UPDATE clientes SET  name='%s' WHERE cpf='%s'", nome,  cpf);
            dbContext.executarUpdateSql(query);

        } catch(Exception e){
            e.printStackTrace();

        } finally {
           dbContext.desconectarBanco();
        }

    }

    @Override
    public Collection<Cliente> consultarTodos() {
        dbContext.conectarBanco();

        List<Cliente> clienteList = new ArrayList<>();

        try {
            String query = String.format("SELECT * FROM clientes");
            ResultSet todosClientes = dbContext.executarQuerySql(query);

            while (todosClientes.next()){
                String nome = todosClientes.getString("name");
                Long cpf = todosClientes.getLong("cpf");
                Cliente cliente = new Cliente(nome, cpf);
                clienteList.add(cliente);
            }

        } catch (Exception e){

        }finally {
            dbContext.desconectarBanco();
        }

        return clienteList;
    }
}
