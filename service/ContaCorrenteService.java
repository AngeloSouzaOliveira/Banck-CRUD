package service;

import java.util.Collection;
import Data.DbContext;
import dao.ContaCorrenteDao;
import domain.ContaCorrente;

/* Classe ContaCorrenteService implementa a interface IContaCorrenteService, ela trata e contém a lógica de négocio relacionada a uma conta corrente  */

public class ContaCorrenteService implements IContaCorrenteService{
    private ContaCorrenteDao contaCorrenteDao;
    private ClienteService clienteService;

    public ContaCorrenteService(DbContext dbContext) {
        this.contaCorrenteDao = new ContaCorrenteDao(dbContext);
        this.clienteService = new ClienteService(dbContext);
    }

    @Override
    public Boolean cadastrarCC(ContaCorrente contaCorrente) {

        Long numeroCC = contaCorrente.getNumero();
        Long cpf = contaCorrente.getCpf();
        Double saldo = contaCorrente.getSaldo();

        Boolean isCadastrado = clienteService.consultarCliente(contaCorrente.getCpf(), false);

        if (contaCorrente == null) {
            System.out.println("\nATENÇÃO: O objeto conta corrente é nulo!\n");
            return false;

        }else if (!(saldo instanceof Double)) {
            System.out.println("\nATENÇÃO: Saldo da conta corrente precisa ser do tipo Decimal e não pode ser nulo.\n");
            return false;

        } else if (!(cpf instanceof Long)) {
            System.out.println("\nATENÇÃO: CPF da conta corrente precisa ser do tipo Long e não pode ser nulo.\n");
            return false;

        } else if (!(numeroCC instanceof Long)) {
            System.out.println("\nATENÇÃO: Número da conta  corrente precisa ser do tipo Long e não pode ser nulo.\n");
            return false;

        }  else if ((cpf.toString()).length() != 11) {
            System.out.println("\nATENÇÃO: CPF tem que 11 caracteres!");
            if (!isCadastrado) {
                System.out.println("\nATENÇÃO: Não existe cliente com esse cpf. Por favor registre esse cliente!\n");
                return false;
            }
            return false;

        }  else if (contaCorrenteDao.consultarCC(contaCorrente.getNumero()).getNumero() != null){
            System.out.println("\nATENÇÃO: Número de conta corrente já cadastrado!\n");
            return false;

        }  else {
            contaCorrenteDao.cadastrarCC(contaCorrente);
            System.out.println("\n>>>> Conta corrente cadastrada com sucesso:\n--Número: " + contaCorrente.getNumero() + "\n--CPF: " + contaCorrente.getCpf() + "\n--Saldo: " + contaCorrente.getSaldo() + "\n--Cheque especial: " + contaCorrente.getChequeEspecial() +"\n");
        }

        return  true;
    }

    @Override
    public Boolean consultarCC(Long numero, Boolean visivel) {

        ContaCorrente contaCorrenteConsulta = contaCorrenteDao.consultarCC(numero);

        if( !(numero instanceof Long)){
            System.out.println("\nATENÇÃO: O número da conta corrente precisa ser do tipo long é/ou diferente de null\n");
            return  false;

        } else if (contaCorrenteConsulta.getNumero() == null){
            System.out.println("\nATENÇÃO: Não existe registro de conta corrente com o número: " + numero + "\n");
            return  false;

        } else if (visivel) {
            System.out.println("\n>>>> Conta corrente encontrada:");
            System.out.println("-Número da conta: " + contaCorrenteConsulta.getNumero());
            System.out.println("-CPF: " + contaCorrenteConsulta.getCpf());
            System.out.println("-Saldo: " + contaCorrenteConsulta.getSaldo());
            System.out.println("-Cheque especial:  " + contaCorrenteConsulta.getChequeEspecial() + "\n");
        }

        return true;
    }

    @Override
    public Boolean removerCC(Long numero) {

        ContaCorrente contaCorrente = contaCorrenteDao.consultarCC(numero);

        if(contaCorrenteDao.removerCC(numero) == false){
            System.out.println("\nATENÇÃO: Ocorreu um erro ao remover a conta corrente!\n");
            return false;
        } else if (contaCorrente.getNumero() == null) {
            System.out.println("\nATENÇÃO: Não existe registro de conta corrente com o número: "+  numero + "\n");
            return false;
        } else {
            System.out.println("\n>>>> A conta corrente com número " +  numero + " foi removida com sucesso!\n");

        }
        return true;

    }

    @Override
    public Boolean atualizarCC(ContaCorrente contaCorrente, Boolean visivel) {

        ContaCorrente contaCorrenteExiste = contaCorrenteDao.consultarCC(contaCorrente.getNumero());
        Double chequeEspecial = contaCorrenteExiste.getChequeEspecial();
        Double saldo = contaCorrente.getSaldo();

        consultarCC(contaCorrente.getNumero(), false);

        if(contaCorrenteExiste.getNumero() == null){
            System.out.println("\nATENÇÃO: Essa conta corrente não existe!");
            return false;

        } else if (saldo == null || chequeEspecial == null) {
            System.out.println("\nATENÇÃO:Cheque especial e/ou saldo é nulos\n");
            return false;

        } else if (!(saldo instanceof Double || chequeEspecial instanceof Double)){
            System.out.println("\nATENÇÃO: O saldo da conta e o cheque especial não são to tipo Double!\n");
            return false;

        } else if (contaCorrenteExiste != null) {
            contaCorrenteExiste.setSaldo(contaCorrente.getSaldo());
            contaCorrenteExiste.setChequeEspecial(contaCorrente.getChequeEspecial());
            contaCorrenteDao.atualizarCC(contaCorrenteExiste);
            if(visivel){
                System.out.println("\n>>>> Conta corrente atualizada com sucesso!\n--Número: " + contaCorrente.getNumero() + "\n--CPF: " + contaCorrenteExiste.getCpf() + "\n--Saldo: " + contaCorrente.getSaldo() + "\n--Cheque especial: " + contaCorrente.getChequeEspecial() + "\n");
            }
            return true;

        } else {
            System.out.println("\nATENÇÃO: Falha ao atualizar conta corrente!");
            return false;
        }
    }

    @Override
    public Collection<ContaCorrente> consultarTodasCC() {

        if(contaCorrenteDao.consultarTodasCC().size() < 1){
            System.out.println("\nATENÇÃO: Não existem contas corrente cadastradas!\n");
        } else {
            System.out.println("    Quantidade total de conta corrente: " + contaCorrenteDao.consultarTodasCC().size());
            System.out.println("--------------------------------------------");
            for (ContaCorrente contaCorrente : contaCorrenteDao.consultarTodasCC()) {
                System.out.println("-Número da conta: " + contaCorrente.getNumero());
                System.out.println("-CPF: " + contaCorrente.getCpf());
                System.out.println("-Saldo: " + contaCorrente.getSaldo());
                System.out.println("-Cheque especial:  " + contaCorrente.getChequeEspecial());
                System.out.println("------------------");
            }
        }

        return contaCorrenteDao.consultarTodasCC();
    }

    @Override
    public Boolean depositar(Double valor, Long numeroConta) {
        ContaCorrente contaCorrenteDepositar = contaCorrenteDao.consultarCC(numeroConta);

       if (consultarCC(numeroConta, false) == false) {
            return false;
       } else if (contaCorrenteDepositar.getNumero() == null) {
           System.out.println("\nATENÇÃO: Não existe conta corrente com número " + numeroConta + "!\n");
           return false;
       } else if (valor <= 0) {
           System.out.println("\nATENÇÃO: Não possivél realizar saque de 0 ou valores negativos!\n");
           return false;
       } else {
           contaCorrenteDepositar.depositar(valor);
           atualizarCC(contaCorrenteDepositar, false);
           System.out.println("\n>>>> Depósito realizado na conta corrente de número " + numeroConta + "\n--Valor do depósito: " + valor + "\n--Saldo Atual: " + contaCorrenteDepositar.getSaldo() + "\n");
           return true;
       }

    }

    @Override
    public Boolean sacar(Double valor, Long numeroConta) {

        ContaCorrente contaCorrenteSacar =  contaCorrenteDao.consultarCC(numeroConta);
        Double saldoAtual = contaCorrenteSacar.getSaldo();
        Double chequeEspecial = contaCorrenteSacar.getChequeEspecial();

        if( valor <= 0){
            System.out.println("\nATENÇÃO: Não possivél realizar saque de 0 ou valores negativos!\n");
            return false;
        } else if (contaCorrenteSacar.getNumero() == null) {
            System.out.println("\nATENÇÃO: Não existe conta corrente com número " + numeroConta + "!\n");
            return false;
        } else if (valor <= saldoAtual + chequeEspecial) {
            saldoAtual -= valor;
            contaCorrenteSacar.setSaldo(saldoAtual);
            atualizarCC(contaCorrenteSacar, false);

            System.out.println("\n>>>> Saque realizado na conta corrente " + numeroConta + "\n--Valor do saque: " + valor + "\n--Saldo Atual: " + contaCorrenteSacar.getSaldo() + "\n");
            return true;
        } else {
            System.out.println("\nATENÇÃO: Saldo insuficiente na conta corrente " + numeroConta + "\n -Saldo Atual: " + contaCorrenteSacar.getSaldo() + "\n");
            return false;
        }

    }
}

