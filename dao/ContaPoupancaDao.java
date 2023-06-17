package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Data.DbContext;
import domain.ContaPoupanca;

/*
 * Implementa a interface IContaPoupancaDao para realizar operações de
 * acesso a dados relacionadas a Conta Poupança no banco de dados.
 */

public class ContaPoupancaDao implements IContaPoupancaDao {

    private DbContext dbContext;

    public ContaPoupancaDao(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public Boolean cadastrarCP(ContaPoupanca contaPoupanca) {

        Long numeroCP = contaPoupanca.getNumero();
        Long cliente_cpf = contaPoupanca.getCpf();
        Double saldoCP = contaPoupanca.getSaldo();
        Double taxaJuros = contaPoupanca.getTaxaJuros();

        dbContext.conectarBanco();

        try {

            String query  = String.format("INSERT INTO contaPoupanca (numero, cliente_cpf, saldo, taxajuros ) VALUES ('%s', '%s', '%s', '%s')", numeroCP, cliente_cpf, saldoCP, taxaJuros);
            boolean cadastrado = dbContext.executarUpdateSql(query);

            if (!cadastrado) {
                System.out.println("- A inserção da conta poupança falhou.");
                return false;
            }

            return true;

        } catch (Exception e){

            e.printStackTrace();
            return false;

        } finally {
            dbContext.desconectarBanco();
        }
    }

    @Override
    public ContaPoupanca consultarCP(Long numero) {
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        dbContext.conectarBanco();

        try {
            String query = String.format("SELECT * FROM contaPoupanca WHERE numero= '%s'", numero);
            ResultSet CPData =  dbContext.executarQuerySql(query);

            if(CPData.next()){
                contaPoupanca.setNumero(CPData.getLong("numero"));
                contaPoupanca.setCpf(CPData.getLong("cliente_cpf"));
                contaPoupanca.setSaldo(CPData.getDouble("saldo"));
                contaPoupanca.setTaxaJuros(CPData.getDouble("taxajuros"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }

        return contaPoupanca;
    }

    @Override
    public ContaPoupanca consultarCPCpf(Long cpf) {

        ContaPoupanca contaPoupanca = new ContaPoupanca();
        dbContext.conectarBanco();

        try {
            String query = String.format("SELECT * FROM contaPoupanca WHERE cliente_cpf= '%s'", cpf);
            ResultSet CPData =  dbContext.executarQuerySql(query);

            if(CPData.next()){
                contaPoupanca.setNumero(CPData.getLong("numero"));
                contaPoupanca.setCpf(CPData.getLong("cliente_cpf"));
                contaPoupanca.setSaldo(CPData.getDouble("saldo"));
                contaPoupanca.setTaxaJuros(CPData.getDouble("taxajuros"));
            }

        } catch (Exception e){
            e.printStackTrace();
            contaPoupanca = null;

        } finally {
            dbContext.desconectarBanco();
        }
        return contaPoupanca;
    }

    @Override
    public Boolean removerCP(Long numero) {

        dbContext.conectarBanco();
        try {
            String query = String.format("DELETE FROM contaPoupanca WHERE numero= '%s'", numero);
            Boolean dataRemovido =  dbContext.executarUpdateSql(query);

            if(!dataRemovido){
                System.out.println("Falha ao remover a conta conta corrente: " +  numero);
                return false;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }
        return true;
    }

    @Override
    public void atualizarCP(ContaPoupanca contaPoupanca) {
        ContaPoupanca contaPoupancaAtualizar = consultarCP(contaPoupanca.getNumero());
        Long numero = contaPoupancaAtualizar.getNumero();

        Double saldo = contaPoupanca.getSaldo();
        Double taxa_juros = contaPoupanca.getTaxaJuros();

        dbContext.conectarBanco();

        try {
            String query = String.format("UPDATE contaPoupanca SET  saldo='%s', taxajuros='%s' WHERE numero='%s'", saldo,  taxa_juros, numero);
            dbContext.executarUpdateSql(query);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }
    }

    @Override
    public Collection<ContaPoupanca> consultarTodasCP() {
        List<ContaPoupanca> contaPoupancas = new ArrayList<>();
        dbContext.conectarBanco();
        try {
            String query = String.format("SELECT * FROM contaPoupanca");
            ResultSet dataTAll = dbContext.executarQuerySql(query);

            while (dataTAll.next()) {
                Long numero = dataTAll.getLong("numero");
                Long cpf = dataTAll.getLong("cliente_cpf");
                Double saldo = dataTAll.getDouble("saldo");
                Double taxa_juros = dataTAll.getDouble("taxajuros");

                ContaPoupanca contaPoupanca = new ContaPoupanca(numero, cpf, taxa_juros);
                contaPoupancas.add(contaPoupanca);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }

        return  contaPoupancas;
    }

}
