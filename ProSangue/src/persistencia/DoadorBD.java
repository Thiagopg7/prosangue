/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import objetos.Doador;

/**
 *
 * @author PMPALMEIRA15
 */
public class DoadorBD {

    public Banco conexao;

    public DoadorBD() {
        conexao = new Banco();
    }
//    private int id;
//    private int fkDoador;
//    private boolean hepatiteB;
//    private boolean hepatiteC;
//    private boolean chagas;
//    private boolean sifilis;
//    private boolean aids;
//    private boolean htlv;
//    private boolean testeAnemia;
//    private boolean triagemClinica;
//    private Timestamp horario;
//    private String imunohematologia;
    
    public void cadastrar(Doador doador) {
        try {

            conexao.conectar();
            Statement declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("INSERT INTO Doador (nome, endereco, data_nascimento, nome_pai, nome_mae, rg, sexo) " + "VALUES('"
                    + doador.getNome() + "','"
                    + doador.getEndereco() + "','"
                    + doador.getDataNascimento() + "','"
                    + doador.getPai() + "','"
                    + doador.getMae() + "','"
                    + doador.getRg() + "','"
                    + doador.getSexo() + "')");
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso."); //Mensagem de confirmação da operação

        } catch (SQLException ex) {
            Logger.getLogger(DoadorBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Número de doador já cadastrado. Tente novamente.", "Número inválido", JOptionPane.ERROR_MESSAGE);
        }

    }

    public ObservableList<Doador> buscarTodosBD() throws SQLException {
        ObservableList<Doador> doadorList = FXCollections.observableArrayList();      

        ResultSet consulta;
        conexao.conectar();
        Statement declaracao;
        try {
            declaracao = conexao.con.createStatement();
            consulta = declaracao.executeQuery("SELECT * FROM Doador");
            while (consulta.next()) {
                Doador doador = new Doador();
                doador.setID(consulta.getInt("id"));
                doador.setDataNascimento(consulta.getDate("data_nascimento"));
                doador.setNome(consulta.getString("nome"));
                doador.setEndereco(consulta.getString("endereco"));
                doador.setPai(consulta.getString("nome_pai"));
                doador.setMae(consulta.getString("nome_mae"));
                doador.setRg(consulta.getString("rg"));
                doador.setUltimaDoacao(consulta.getTimestamp("ultima_doacao"));
                doador.setSexo(consulta.getString("sexo"));
                doadorList.add(doador);
            }
            conexao.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(DoadorBD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return doadorList;
    }

    public Doador buscarIndividualBD(int ID) {
        try {
            Doador doador = new Doador();
            conexao.conectar();
            ResultSet consulta;
            Statement declaracao = conexao.con.createStatement();
            consulta = declaracao.executeQuery("SELECT * FROM Doador WHERE id =" + ID);
            while (consulta.next()) {
                doador.setID(consulta.getInt("id"));
                doador.setDataNascimento(consulta.getDate("data_nascimento"));
                doador.setNome(consulta.getString("nome"));
                doador.setEndereco(consulta.getString("endereco"));
                doador.setPai(consulta.getString("nome_pai"));
                doador.setMae(consulta.getString("nome_mae"));
                doador.setRg(consulta.getString("rg"));
                doador.setUltimaDoacao(consulta.getTimestamp("ultima_doacao"));
                doador.setSexo(consulta.getString("sexo"));
            }
            //System.out.println("Resultado da busca - IDDoador:" + doador.getID() + ". Lugares:" + doador.getLugares() + ". Ocupada:" + doador.getOcupada());
            conexao.desconectar();
            return doador;
        } catch (SQLException ex) {
            Logger.getLogger(DoadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void alterar(Doador doador) {
        try {
            conexao.conectar();
            Statement declaracao;
            declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("UPDATE doador SET nome = '" + doador.getNome()
                    + "', data_nascimento ='" + doador.getDataNascimento()
                    + "', endereco ='" + doador.getEndereco()
                    + "', nome_pai ='" + doador.getPai()
                    + "', nome_mae ='" + doador.getMae()
                    + "', rg ='" + doador.getRg()
                    + "', ultima_doacao =" + doador.getUltimaDoacao()
                    + ", sexo ='" + doador.getSexo()
                    + "' WHERE id = " + doador.getID());
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Alteração da doador " + doador.getID() + " feita com sucesso."); //Mensagem de confirmação

        } catch (SQLException ex) {
            Logger.getLogger(DoadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(Doador doador) {
        try {
            conexao.conectar();
            Statement declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("DELETE FROM doador WHERE id = " + doador.getID());
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Doador " + doador.getID() + " excluída com sucesso."); //Mensagem de confirmação da operação

        } catch (SQLException ex) {
            Logger.getLogger(DoadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
