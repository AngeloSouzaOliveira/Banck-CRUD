import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import Data.DbContext;
import domain.Cliente;
import domain.ContaCorrente;
import domain.ContaPoupanca;
import service.ClienteService;
import service.ContaCorrenteService;
import service.ContaPoupancaService;

public class Main {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        DbContext context = new DbContext();
        ClienteService clienteService = new ClienteService(context);
        ContaCorrenteService contaCorrenteService = new ContaCorrenteService(context);
        ContaPoupancaService contaPoupancaService = new ContaPoupancaService(context);

        int opcaoMenuPrincipal = 0;
        while (opcaoMenuPrincipal != 4) {

            exibirMenuPrincipal();

            if (ler.hasNextInt()) {
                opcaoMenuPrincipal = ler.nextInt();

                switch (opcaoMenuPrincipal) {
                    case 1:
                        subMenuClientesLoop(clienteService, ler);
                        break;

                    case 2:
                        subMenuContaCorrenteLoop(contaCorrenteService, ler);
                        break;

                    case 3:
                        subMenuContaPoupancaLoop(contaPoupancaService, ler);
                        break;

                    case 4:
                        imprimir("\nObrigado! Saindo do JAVAVERSE BANK...\n");
                        break;

                    default:
                        imprimir("\nATENÇÃO: Opção inválida no Menu Principal!\n");
                        break;
                }

            } else {
                ler.next();
                imprimir("\nATENÇÃO: Entrada inválida. Digite um número válido!\n");
            }
        }

    }

    // método imprimir
    public static void imprimir(String text){
        System.out.println(text);
    }
    
    //Menu Principal
    public static void exibirMenuPrincipal() {
        imprimir("\n***** BEM-VINDO AO JAVAVERSE BANK *****");
        imprimir("----------- Menu Principal ------------\n");
        imprimir("Digite 1: Opções de Cliente");
        imprimir("Digite 2: Opções de Conta Corrente");
        imprimir("Digite 3: Opções de Conta Poupança");
        imprimir("[SAIR] -> Digite 4");
        imprimir("\n-> Digite o número da opção desejada:");
    }

    
    // Menu Cliente
    public static void exibirSubMenuClientes() {
        imprimir("\n----- Opções de Clientes -----");
        imprimir("1. Ver Todos os Cliente");
        imprimir("2. Criar um Cliente");
        imprimir("3. Alterar um Cliente");
        imprimir("4. Consultar um Cliente");
        imprimir("5. Remover um Cliente");
        imprimir("6. Voltar ao Menu Principal");
        imprimir("\n-> Digite o número da opção desejada:");
    }
    public static void subMenuClientesLoop(ClienteService clienteService, Scanner ler) {
        int opcaoMenuCliente = 0;
        while (opcaoMenuCliente != 6) {
            exibirSubMenuClientes();

            if (ler.hasNextInt()) {
                opcaoMenuCliente = ler.nextInt();

                switch (opcaoMenuCliente) {
                    case 1:
                        verTodosClientesCommand(clienteService, ler);
                        break;

                    case 2:
                        cadastarClienteCommand(clienteService, ler);
                        break;

                    case 3:
                        alterarNomeClienteCommand(clienteService, ler);
                        break;

                    case 4:
                        consultarClienteCommand(clienteService, ler);
                        break;

                    case 5:
                        removerClienteCommand(clienteService, ler);
                        break;

                    case 6:
                        imprimir("\nVoltando ao menu principal!\n");
                        break;

                    default:
                        imprimir("\nATENÇÃO: Opção inválida no menu clientes!\n");
                        break;
                }
            } else {
                ler.next();
                imprimir("\nATENÇÃO: Entrada inválida. Digite um número válido!\n");
            }
        }
    }

    
    // Menu Conta Corrente
    public static void exibirSubMenuContaCorrente() {
        imprimir("\n----- Opções de Transação Conta Corrente -----");
        imprimir("1. Ver Todas Contas Correntes");
        imprimir("2. Criar uma Conta Corrente");
        imprimir("3. Alterar uma Conta Corrente");
        imprimir("4. Consultar uma Conta Corrente");
        imprimir("5. Remover uma Conta Corrente");
        imprimir("6. Sacar Dinheiro uma Conta Corrente");
        imprimir("7. Depositar Dinheiro uma Conta Corrente");
        imprimir("8. Voltar ao Menu Principal");
        imprimir("\n-> Digite o número da opção desejada:");
    }
    public static void subMenuContaCorrenteLoop(ContaCorrenteService contaCorrenteService, Scanner ler){
        int opcaoMenuContaCorrente = 0;
        while (opcaoMenuContaCorrente != 8) {

            exibirSubMenuContaCorrente();

            if (ler.hasNextInt()) {
                opcaoMenuContaCorrente = ler.nextInt();

                switch (opcaoMenuContaCorrente) {
                    case 1:
                        verTodasContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 2:
                        criarContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 3:
                        atualizarContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 4:
                        consultarContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 5:
                        removerContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 6:
                        saqueContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 7:
                        depositoContaCorrenteCommand(contaCorrenteService, ler);
                        break;

                    case 8:
                        imprimir("\nVoltando ao menu principal!\n");
                        break;

                    default:
                        imprimir("\nATENÇÃO: Opção inválida no menu conta corrente!\n");
                        break;
                }

            } else {
                ler.next();
                imprimir("\nATENÇÃO: Entrada inválida. Digite um número válido!\n");
            }
        }
    }

    
    // Menu Conta Poupança
    public static void exibirSubMenuContaPoupanca() {
        imprimir("\n----- Opções de Transação Conta Poupança -----");
        imprimir("1. Ver Todas Contas Poupanças");
        imprimir("2. Criar uma Conta Poupança");
        imprimir("3. Alterar uma Conta Poupança");
        imprimir("4. Consultar uma Conta Poupança");
        imprimir("5. Remover uma Conta Poupança");
        imprimir("6. Sacar Dinheiro de uma Conta Poupança");
        imprimir("7. Depositar Dinheiro de uma Conta Poupança");
        imprimir("8. Voltar");
        imprimir("\n-> Digite o número da opção desejada:");
    }
    public static void subMenuContaPoupancaLoop(ContaPoupancaService contaPoupancaService, Scanner ler){
        int opcaoMenuContaPoupanca = 0;
        while (opcaoMenuContaPoupanca != 8) {
            exibirSubMenuContaPoupanca();
            if (ler.hasNextInt()) {
                opcaoMenuContaPoupanca = ler.nextInt();

                switch (opcaoMenuContaPoupanca) {
                    case 1:
                        verTodasContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 2:
                        criarContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 3:
                        atualizarContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 4:
                        consultarContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 5:
                        removerContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 6:
                        saqueContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 7:
                        depositoContaPoupancaCommand(contaPoupancaService, ler);
                        break;

                    case 8:
                        imprimir("\nVoltando ao menu principal!\n");
                        break;

                    default:
                        imprimir("\nATENÇÃO: Opção inválida no menu conta poupança!\n");
                        break;
                }
            } else {
                ler.next();
                imprimir("\nATENÇÃO: Entrada inválida. Digite um número válido!\n");
            }
        }
    }


    // AÇÕES DE CLIENTE
    public  static void verTodosClientesCommand(ClienteService clienteService, Scanner ler){
        imprimir("\n********************************************");
        imprimir("       CLIENTES REGISTRADOS NO BANCO:       ");
        imprimir("********************************************");
        clienteService.consultarTodos();
        imprimir("********************************************\n");
    }

    public static void cadastarClienteCommand(ClienteService clienteService, Scanner ler){

        imprimir("Digite o nome do cliente a ser cadastrado:");
        String nome = ler.next();
        imprimir("Digite o cpf do cliente a ser cadastrado");

        try {
            String input = ler.next().replaceAll("[.,-/ ]", "");
            long cpf = Long.parseLong(input);
            Cliente cliente = new Cliente(nome, cpf);
            clienteService.cadastrarCliente(cliente);

        } catch (NumberFormatException e) {
            imprimir("Error: Entrada inválida. Digite um número de 11 caracteres numéricos!\n");
        }
    }
    public static  void alterarNomeClienteCommand(ClienteService clienteService, Scanner ler){
        imprimir("Informe o CPF do cliente a ser alterado: ");
        try {
            String input = ler.next().replaceAll("[.,-/ ]", "");
            Long cpf = Long.parseLong(input);

            imprimir("Informe o novo nome do cliente: ");
            String nome = ler.next();

            Cliente cliente = new Cliente(nome, cpf);
            clienteService.atualizar(cliente);

        } catch (NumberFormatException e) {
            imprimir("Error: Cliente não foi atualizado. Digite um número de 11 caracteres numéricos!\n");
        }

    }
    public static  void consultarClienteCommand(ClienteService clienteService, Scanner ler){
        imprimir("Informe o CPF do cliente a ser pesqusiado:");
        try {
            String input = ler.next().replaceAll("[.,-/ ]", "");
            long cpf = Long.parseLong(input);
            clienteService.consultarCliente(cpf, true);

        } catch (NumberFormatException e) {
            imprimir("Error: Cliente não localizado. Digite um número de 11 caracteres numéricos!\n");
        }
    }

    public  static void removerClienteCommand(ClienteService clienteService, Scanner ler){
        imprimir("Informe o CPF do cliente que será removido:");
        try {
            long cpf = Long.parseLong(ler.next().replaceAll("[.,-/ ]", ""));
            clienteService.remover(cpf);

        } catch (NumberFormatException e) {
            imprimir("Error: Cliente não foi removido. Digite um número de 11 caracteres numéricos!\n");
        }

    }
    //-----------------------------------


    // AÇÕES DE CONTA CORRENTE
    public static void verTodasContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){
        imprimir("\n********************************************");
        imprimir("    CONTAS CORRENTES REGISTRADOS NO BANCO:    ");
        imprimir("********************************************");
        contaCorrenteService.consultarTodasCC();
        imprimir("********************************************\n");
    }

    public static void criarContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){

        try {
            imprimir("Informe o numero da conta corrente a ser cadastrada:");
            String input = ler.next().replaceAll("[.,-/]", "");
            Long numeroCC = Long.parseLong(input);

            imprimir("Informe o cpf do cliente a ser associado a essa conta corrente:");
            long cpf = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));
            ContaCorrente contaCorrente = new ContaCorrente(numeroCC, cpf);
            contaCorrenteService.cadastrarCC(contaCorrente);

        } catch (NumberFormatException e) {
            imprimir("Error: Entrada inválida. Digite um valor numéricos para conta e CPF(11 números)!\n");
        }

    }

    public static void atualizarContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){
        try {
            imprimir("Informe o número da conta corrente a ser atualizada:");
            Long numero = Long.parseLong(ler.next().replaceAll("[.,-]", ""));

            imprimir("Informe o valor do saldo da conta corrente a ser atualizada:");
            Double saldo = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            imprimir("Informe o valor do cheque especial a ser atualizado:");
            Double chequeEspecial = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            ContaCorrente contaCorrente = new ContaCorrente(numero, saldo, chequeEspecial);
            contaCorrenteService.atualizarCC(contaCorrente, true);
        } catch (NumberFormatException e) {
            imprimir("Error: Entrada inválida. Digite um valor numérico!");
        }
    }
    public static void consultarContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){

        try {
            imprimir("Digite o número da conta corrente que deseja consulta:");
            String input = ler.next().replaceAll("[,-/]", "");
            Long numeroCC = Long.parseLong(input);

            contaCorrenteService.consultarCC(numeroCC, true);

        } catch (NumberFormatException e) {
            imprimir("Error: Entrada inválida. Digite um valor numéricos para conta!\n");
        }
    }

    public static void removerContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){
        try {
            imprimir("Digite o número da conta corrente a ser removida:");
            String input = ler.next().replaceAll("[.,-]", "");
            Long numeroCC = Long.parseLong(input);
            contaCorrenteService.removerCC(numeroCC);

        } catch (NumberFormatException e) {
            imprimir("Error: Entrada inválida. Digite um valor numéricos para conta!\n");
        }
    }

    public static void saqueContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){

        try {
           imprimir("Informe o número da conta corrente que deseja realizar saque:");
           Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-]", ""));

           imprimir("Informe o valor do saque:");
           Double valorSaque = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

           contaCorrenteService.sacar(valorSaque, numeroCC);

       } catch (NumberFormatException e) {
           imprimir("\nError: Entrada inválida. Digite um valor numérico!\n");
       }

    }

    public static void depositoContaCorrenteCommand(ContaCorrenteService contaCorrenteService, Scanner ler){
        try {
            imprimir("Informe o número da conta corrente que deseja realizar o deposito:");
            Long numeroCC = Long.parseLong(ler.next());

            imprimir("Informe o valor do deposito:");
            Double valorDeposito = Double.parseDouble(ler.next().replaceAll("[,-/]", "."));

            contaCorrenteService.depositar(valorDeposito, numeroCC);
        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numérico!\n");
        }
    }
    //-----------------------------------


    // AÇÕES DE CONTA POUPANÇA
    public static void verTodasContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){
        imprimir("\n********************************************");
        imprimir("    CONTAS POUPANÇA REGISTRADOS NO BANCO:    ");
        imprimir("********************************************");
        contaPoupancaService.consultarTodasCP();
        imprimir("********************************************\n");
    }

    // Taxa de juros não é atualizada
    public static void criarContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {
            imprimir("Informe o numero da conta poupança a ser cadastrada:");
            Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));

            imprimir("Informe o cpf do cliente a ser associado a essa conta poupança:");
            Long cpf = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));

            ContaPoupanca contaPoupanca  = new ContaPoupanca(numeroCC, cpf);
            contaPoupancaService.cadastrarCP(contaPoupanca);

        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numéricos para conta e CPF(11 números)!\n");
        } catch (InputMismatchException e){
            imprimir("\nError: Entrada inválida. Digite um valor numéricos para conta e CPF(11 números)!\n");
        }
    }

    public static void atualizarContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {
            imprimir("Informe o numero da conta poupança a ser atualizado:");
            Long numero = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));

            imprimir("Informe o valor do saldo da conta poupança a ser atualizado:");
            Double saldo = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            imprimir("Informe o valor da taxa de juros a ser atualizado:");
            Double taxaJuros = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            ContaPoupanca contaPoupanca  = new ContaPoupanca(numero, saldo, taxaJuros);
            contaPoupancaService.atualizarCP(contaPoupanca);


        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numérico!\n");
        }
    }

    public static void consultarContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {
            imprimir("Digite o número da conta poupança que deseja consulta:");
            Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));
            contaPoupancaService.consultarCP(numeroCC, true);
        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numéricos para conta!\n");
        }
    }

    public static void removerContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {
            imprimir("Digite o número da conta poupança a ser removida:");
            Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));
            contaPoupancaService.removerCP(numeroCC);
        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numéricos para conta!\n");
        }

    }

    public static void saqueContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {

            imprimir("Informe o número da conta poupança que deseja realizar saque:");
            Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));

            imprimir("Informe o valor do saque:");
            Double valorSaque = Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            contaPoupancaService.sacar(valorSaque, numeroCC);

        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numérico!\n");
        }
    }

    public static void depositoContaPoupancaCommand(ContaPoupancaService contaPoupancaService, Scanner ler){

        try {
            imprimir("Informe o número da conta poupança que deseja realizar o deposito:");
            Long numeroCC = Long.parseLong(ler.next().replaceAll("[.,-/]", ""));

            imprimir("Informe o valor do deposito:");
            Double valorDeposito= Double.parseDouble(ler.next().replaceAll("[,-]", "."));

            contaPoupancaService.depositar(valorDeposito, numeroCC);
        } catch (NumberFormatException e){
            imprimir("\nError: Entrada inválida. Digite um valor numérico!\n");
        }
    }

}