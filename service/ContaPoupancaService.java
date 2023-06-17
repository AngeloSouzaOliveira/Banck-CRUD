package service;

import java.util.Collection;

import Data.DbContext;
import dao.ContaPoupancaDao;
import domain.ContaPoupanca;

/* Classe ContaPoupancaService implementa a interface IServiceContaPoupanca, ela trata e contém a lógica de négocio relacionada a uma conta poupança  */

public class ContaPoupancaService implements  IServiceContaPoupanca{

    private ContaPoupancaDao contaPoupancaDao;
    private ClienteService clienteService;

    public ContaPoupancaService (DbContext dbContext){
        this.contaPoupancaDao = new ContaPoupancaDao(dbContext);
        this.clienteService = new ClienteService(dbContext);
    }
    @Override
    public Boolean cadastrarCP(ContaPoupanca contaPoupanca) {

        Long numeroCP = contaPoupanca.getNumero();
        Long cpf = contaPoupanca.getCpf();
        Double saldo = contaPoupanca.getSaldo();

        Boolean isCadastradoCP = clienteService.consultarCliente(contaPoupanca.getCpf(), false);

        if (contaPoupanca == null) {
            System.out.println("\nATENÇÃO: O objeto conta poupança é nulo!\n");
            return false;

        }else if (!(saldo instanceof Double)) {
            System.out.println("\nATENÇÃO: Saldo da conta poupança precisa ser do tipo Decimal e não pode ser nulo.\n");
            return false;

        } else if (!(cpf instanceof Long)) {
            System.out.println("ATENÇÃO: CPF da conta poupança precisa ser do tipo Long e não pode ser nulo.\n");
            return false;

        } else if (!(numeroCP instanceof Long)) {
            System.out.println("\nATENÇÃO: Número da conta poupança precisa ser do tipo Long e não pode ser nulo.\n");
            return false;

        }  else if ((cpf.toString()).length() != 11) {
            System.out.println("\nATENÇÃO: CPF tem que 11 caracteres!\n");

            if (!isCadastradoCP) {
                System.out.println("\nATENÇÃO: Não existe cliente com esse cpf. Por favor registre esse cliente!\n");
                return false;
            }

            return false;

        }  else if (contaPoupancaDao.consultarCP(contaPoupanca.getNumero()).getNumero() != null) {
            System.out.println("\nATENÇÃO: Número de conta poupança já cadastrado!\n");
            return false;

        } else if (saldo < 0){
                System.out.println("\nATENÇÃO: Saldo da conta poupança não pode ser negativo!\n");
                return false;

        }  else {
            contaPoupancaDao.cadastrarCP(contaPoupanca);
            System.out.println("\n>>>> Conta poupança cadastrada com sucesso:\n--Número: " + contaPoupanca.getNumero() + "\n--CPF: " + contaPoupanca.getCpf() + "\n--Saldo: " + contaPoupanca.getSaldo() + "\n--Taxa de juros: " + contaPoupanca.getTaxaJuros() + "\n");
        }

        return  true;

    }

    @Override
    public Boolean consultarCP(Long numero, Boolean visivel) {
        ContaPoupanca contaPoupanca = contaPoupancaDao.consultarCP(numero);

        if(!(numero instanceof Long)){
            System.out.println("\nATENÇÃO: O número da conta poupanca precisa ser do tipo long é/ou diferente de null\n");
            return  false;

        } else if (contaPoupancaDao.consultarCP(numero).getNumero() == null){
            System.out.println("\nATENÇÃO: Não existe registro de conta poupanca com esse número: " + numero + "\n");
            return  false;

        } else if (visivel){
            System.out.println("\n>>>> Conta poupança encontrada:\n--Número: " + contaPoupanca.getNumero() + "\n--CPF: " + contaPoupanca.getCpf() + "\n--Saldo: " + contaPoupanca.getSaldo() + "\n--Taxa de Juros: " + contaPoupanca.getTaxaJuros() + "\n");
        }

        return true;
    }

    @Override
    public Boolean atualizarCP(ContaPoupanca contaPoupanca) {
        ContaPoupanca contaPoupancaExiste = contaPoupancaDao.consultarCP(contaPoupanca.getNumero());

        // taxa de juros existente
        Double taxa_juros = contaPoupancaExiste.getTaxaJuros();

        // valor existente na CP
        Double saldo = contaPoupancaExiste.getSaldo();

        if (taxa_juros == null || saldo == null || contaPoupancaExiste.getNumero() == null || contaPoupancaExiste.getSaldo() == null) {
            System.out.println("\nATENÇÃO: Essa conta poupanca não existe!\n");
            return false;
        }


        if (!(saldo instanceof Double || taxa_juros instanceof Double)){
            System.out.println("\nATENÇÃO: O saldo da conta poupança e o taxa de juros não são to tipo Double!\n");
            return false;
        }

        Double saldoFinal = saldo + contaPoupanca.getSaldo();

        if(saldoFinal < saldo ){
            if(saldoFinal < 0){
                System.out.println("\nATENÇÃO: Você não tem saldo para realizar o saque!\n Seu saldo é de "+ saldo + "\n");
                return false;
            }

        }

        consultarCP(contaPoupanca.getNumero(), false);

        if(contaPoupancaExiste != null){
            contaPoupancaExiste.setNumero(contaPoupanca.getNumero());
            contaPoupancaExiste.setTaxaJuros(contaPoupanca.getTaxaJuros());
            contaPoupancaExiste.setSaldo(contaPoupanca.getSaldo());
            contaPoupancaDao.atualizarCP(contaPoupancaExiste);
            System.out.println("\n>>>> Conta poupança atualizada com sucesso!\n");
            return true;
        } else {
            System.out.println("\nATENÇÃO: Falha ao atualizar conta corrente!\n");
            return false;
        }

    }

    @Override
    public Boolean removerCP(Long numero) {

        ContaPoupanca contaPoupanca = contaPoupancaDao.consultarCP(numero);

        if(contaPoupancaDao.removerCP(numero) == false){
            System.out.println("\nATENÇÃO: Ocorreu um erro ao remover a conta poupança!\n");
            return false;

        } else if (contaPoupanca.getNumero() == null) {
            System.out.println("\nATENÇÃO: Não existe registro de conta poupança com o número: "+  numero + "\n");
            return false;

        } else {
            System.out.println("\n>>>> A conta poupança com o " +  numero + " foi removida com sucesso!\n");
            return true;
        }
    }

    @Override
    public Collection<ContaPoupanca> consultarTodasCP() {

        if(contaPoupancaDao.consultarTodasCP().size() < 1){
            System.out.println("\nNão existem contas poupancas cadastradas!\n");

        } else {
            System.out.println("    Quantidade total de conta poupanca: " + contaPoupancaDao.consultarTodasCP().size());
            System.out.println("--------------------------------------------");
            for (ContaPoupanca contaPoupanca : contaPoupancaDao.consultarTodasCP()) {
                System.out.println("-Número da conta: " + contaPoupanca.getNumero());
                System.out.println("-CPF: " + contaPoupanca.getCpf());
                System.out.println("-Saldo: " + contaPoupanca.getSaldo());
                System.out.println("-Taxa de Juros: " + contaPoupanca.getTaxaJuros());
                System.out.println("------------------");
            }
        }

        return contaPoupancaDao.consultarTodasCP();
    }

    @Override
    public Boolean depositar(Double valor, Long numeroConta) {
        ContaPoupanca contaPoupancaDeposito = contaPoupancaDao.consultarCP(numeroConta);

        if( valor <= 0){
            System.out.println("\nATENÇÃO: Não possivél realziar saque de 0 ou valores negativos!\n");
            return false;
        } else if (contaPoupancaDeposito.getNumero() == null) {
            System.out.println("\nATENÇÃO: Não existe conta poupança com número " + numeroConta + "!\n");
            return false;
        } else if (consultarCP(numeroConta, false) == false) {
            return false;
        }

        //cálculo dos juros no primeiro deposito caso a conta
        Double juros = valor * (contaPoupancaDeposito.getTaxaJuros() / 100);
        valor += juros;

        contaPoupancaDeposito.depositar( valor);
        atualizarCP(contaPoupancaDeposito);
        System.out.println("\n>>>> Deposito realizado na conta poupança, " + numeroConta + " no valor de " + valor + " seu saldo é " + contaPoupancaDeposito.getSaldo() + "\n");
        return true;
    }

    @Override
    public Boolean sacar(Double valor, Long numeroConta) {
        ContaPoupanca contaPoupancaSacar = contaPoupancaDao.consultarCP(numeroConta);

        if(valor > contaPoupancaSacar.getSaldo()){
            System.out.println("\nATENÇÃO: Saldo insuficiente da conta poupança! " + "Seu saldo é de: " + contaPoupancaSacar.getSaldo() + "\n");
            return  false;
        } else if (contaPoupancaSacar.getNumero() == null) {
            System.out.println("\nATENÇÃO: Não existe conta poupança com número " + numeroConta + "!\n");
            return false;
        } else if (consultarCP(numeroConta, false) == false) {
            return false;
        }
        contaPoupancaSacar.sacar(valor);
        atualizarCP(contaPoupancaSacar);
        System.out.println("\n>>>> Saque realizado na conta poupança, " + numeroConta + " no valor de " + valor + " seu saldo é " + contaPoupancaSacar.getSaldo() + "\n");
        return true;
    }




}
