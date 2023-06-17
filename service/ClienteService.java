package service;

import java.util.Collection;

import Data.DbContext;
import dao.ClienteDao;
import dao.ContaCorrenteDao;
import dao.ContaPoupancaDao;
import domain.Cliente;
import domain.ContaCorrente;
import domain.ContaPoupanca;

/* Classe ClienteService implementa a interface IClienteService, ela trata e contém a lógica de négocio relacionada ao um cliente  */

public class ClienteService implements IClienteService{

    private ClienteDao clienteDao;
    private ContaCorrenteDao correnteDao;
    private ContaPoupancaDao contaPoupancaDao;
    
    public ClienteService(DbContext dbContext){
        this.clienteDao = new ClienteDao(dbContext);
        this.correnteDao = new ContaCorrenteDao(dbContext);
        this.contaPoupancaDao = new ContaPoupancaDao(dbContext);
    }

    @Override
    public Collection<Cliente> consultarTodos() {

        if(clienteDao.consultarTodos().size() < 1){
            System.out.println("ATENÇÃO: Não existem clientes cadastrados!\n");
        }  else {


            System.out.println("      Quantidade total de clientes: " + clienteDao.consultarTodos().size());
            System.out.println("--------------------------------------------");
            for (Cliente cliente : clienteDao.consultarTodos()){
                System.out.println("-Nome: " + cliente.getNome());
                System.out.println("-CPF:  " + cliente.getCpf());
                System.out.println("------------------");
            }
        }
        return  clienteDao.consultarTodos();
    }

    @Override
    public Boolean cadastrarCliente(Cliente cliente) {

        Long isExist = clienteDao.consultarCliente(cliente.getCpf()).getCpf();

        if(cliente == null){
            System.out.println("\nATENÇÃO: O cliente é nulo.\n");
            return false;
        } else if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            System.out.println("\nATENÇÃO: O campo nome está vazio ou é nulo!\n");
            return false;
        }  else if (cliente.getCpf() == null) {
            System.out.println("\nATENÇÃO: O campo CPF está vazio e nulo.");
            return false;
        } else if (cliente.getCpf().toString().length() != 11) {
            System.out.println("\nATENÇÃO: O campo CPF deve ter 11 caracteres numéricos!\n");
            return false;
        } else if (isExist != null) {
            System.out.println("\nATENÇÃO: Cliente com o cpf " + cliente.getCpf() + " já está cadastrado.\n");
            return false;
        } else {
            clienteDao.cadastrarCliente(cliente);
            System.out.println("\n>>> Cliente " + cliente.getNome() + " de CPF " + cliente.getCpf() + " cadastrado com sucesso!\n");
            return true;
        }
    }

    @Override
    public Boolean atualizar(Cliente cliente) {
        Boolean isExist = consultarCliente(cliente.getCpf(), false);
        Cliente clienteAtualizarService = clienteDao.consultarCliente(cliente.getCpf());

        String nome  = cliente.getNome();
        Long cpf = cliente.getCpf();

        if( nome == null || cpf == null){
            System.out.println("\nATENÇÃO: Nome e/ou cpf são nulos!\n");
            return false;
        }

        if(isExist != false){
            clienteAtualizarService.setNome(cliente.getNome());
            clienteDao.atualizar(cliente);
            System.out.println("\n>>> Cliente atualizado com sucesso!\n");
            return true;
        } else {
            System.out.println("\nATENÇÃO: Não foi possivel atualizar cliente!\n");
            return false;
        }
    }

    @Override
    public Boolean consultarCliente(Long cpf, Boolean visivel) {

        if(!(cpf instanceof Long)){
            System.out.println("\nATENÇÃO: O número do cpf  precisa ser do tipo long é/ou diferente de null\n");
            return  false;
        }


        if(visivel){
            if(clienteDao.consultarCliente(cpf).getCpf() == null ){
                System.out.println("\nATENÇÃO: Não existe registro de cliente com esse cpf: " + cpf +"\n");
                return  false;
            }

            System.out.println("\n>>> O Cliente foi encontrado: " + "\n-Nome: "  + clienteDao.consultarCliente(cpf).getNome() + "\n-Cpf: " + clienteDao.consultarCliente(cpf).getCpf() + "\n");
        }

        return true;
    }

    @Override
    public Boolean remover(Long cpf) {

        Boolean isExist = consultarCliente(cpf, false);
        Boolean isRemove  = clienteDao.remover(cpf);
        ContaCorrente contasCorrente = correnteDao.consultarCCCpf(cpf);
        ContaPoupanca contaPoupanca = contaPoupancaDao.consultarCPCpf(cpf);

        if (contasCorrente != null) {
            System.out.println("\nATENÇÃO: O cliente está associado a uma conta corrente.\nRemova a conta corrente antes de excluir o cliente!\n");
            return false;

        } else if (contaPoupanca != null) {
            System.out.println("\nATENÇÃO: O cliente está associado a uma conta poupança.\nRemova a contas poupança antes de excluir o cliente!\n");
            return false;

        } else if (isExist == false){
            return false;

        } else if (isRemove != true){
            System.out.println("\nATENÇÃO: Ocorreu um erro ao remover o cliente.\n");
            return false;

        } else {
            System.out.println("\n>>> O cliente " + clienteDao.consultarCliente(cpf).getNome() + " de cpf: " +  cpf + " foi removida com sucesso!\n");
            return true;
        }
    }



}
