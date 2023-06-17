package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Data.DbContext;
import domain.ContaCorrente;

/*
 * Implementa a interface IContaCorrenteDao para realizar operações de
 * acesso a dados relacionadas a Conta Corrente no banco de dados.
 */

public class ContaCorrenteDao implements IContaCorrenteDao {

    private DbContext dbContext;

    public ContaCorrenteDao(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public Boolean cadastrarCC(ContaCorrente contaCorrente) {

        Long numeroCC = contaCorrente.getNumero();
        Long cliente_cpf = contaCorrente.getCpf();
        Double saldoCC = contaCorrente.getSaldo();
        Double chequeEspecial = contaCorrente.getChequeEspecial();
        dbContext.conectarBanco();

        try {
            String query  = String.format("INSERT INTO contaCorrente (numero, cliente_cpf, saldo, cheque_especial ) VALUES ('%s', '%s', '%s', '%s')", numeroCC, cliente_cpf, saldoCC, chequeEspecial);
            dbContext.executarUpdateSql(query);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            dbContext.desconectarBanco();
        }

    }

    @Override
    public ContaCorrente consultarCC(Long numero) {

        ContaCorrente contaCorrente = new ContaCorrente();
        dbContext.conectarBanco();

        try {
            String query = String.format("SELECT * FROM contaCorrente WHERE numero= '%s'", numero);
            ResultSet CCData =  dbContext.executarQuerySql(query);

            if(CCData.next()){
                contaCorrente.setNumero(CCData.getLong("numero"));
                contaCorrente.setCpf(CCData.getLong("cliente_cpf"));
                contaCorrente.setSaldo(CCData.getDouble("saldo"));
                contaCorrente.setChequeEspecial(CCData.getDouble("cheque_especial"));
            }

        } catch (Exception e){
            e.printStackTrace();
            contaCorrente = null;

        } finally {
            dbContext.desconectarBanco();
        }
        return contaCorrente;
    }

    @Override
    public ContaCorrente consultarCCCpf(Long cpf) {

        ContaCorrente contaCorrente = new ContaCorrente();
        dbContext.conectarBanco();

        try {
            String query = String.format("SELECT * FROM contaCorrente WHERE cliente_cpf= '%s'", cpf);
            ResultSet CCData =  dbContext.executarQuerySql(query);

            if(CCData.next()){
                contaCorrente.setNumero(CCData.getLong("numero"));
                contaCorrente.setCpf(CCData.getLong("cliente_cpf"));
                contaCorrente.setSaldo(CCData.getDouble("saldo"));
                contaCorrente.setChequeEspecial(CCData.getDouble("cheque_especial"));
            }

        } catch (Exception e){
            e.printStackTrace();
            contaCorrente = null;

        } finally {
            dbContext.desconectarBanco();
        }
        return contaCorrente;
    }

    @Override
    public Boolean removerCC(Long numero) {
        dbContext.conectarBanco();
        try {
            String query = String.format("DELETE FROM contaCorrente WHERE numero= '%s'", numero);
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
    public void atualizarCC(ContaCorrente contaCorrente) {
        ContaCorrente contaCorrenteAtualizar = consultarCC(contaCorrente.getNumero());
        Long numero = contaCorrenteAtualizar.getNumero();
        Double saldo = contaCorrente.getSaldo();
        Double novoChequeEspecial = contaCorrente.getChequeEspecial();
        dbContext.conectarBanco();

        try {
            String query = String.format("UPDATE contaCorrente SET  saldo='%s', cheque_especial='%s' WHERE numero='%s'", saldo,  novoChequeEspecial, numero);
            dbContext.executarUpdateSql(query);

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }

    }

    @Override
    public Collection<ContaCorrente> consultarTodasCC() {

        List<ContaCorrente>  contaCorrentes = new ArrayList<>();
        dbContext.conectarBanco();

        try{
            String query = String.format("SELECT * FROM contaCorrente");
            ResultSet dataTodas = dbContext.executarQuerySql(query);

            while (dataTodas.next()) {
                Long numero = dataTodas.getLong("numero");
                Long cpf = dataTodas.getLong("cliente_cpf");
                Double saldo = dataTodas.getDouble("saldo");
                Double chequeEspecial = dataTodas.getDouble("cheque_especial");
                ContaCorrente contaCorrente = new ContaCorrente(numero, cpf, saldo, chequeEspecial);
                contaCorrentes.add(contaCorrente);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dbContext.desconectarBanco();
        }

        return contaCorrentes;
    }
}
