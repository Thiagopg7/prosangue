/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.Doacao;

/**
 *
 * @author PMPALMEIRA15
 */
public class DoacaoBD {

    public Banco conexao;

    public DoacaoBD() {
        conexao = new Banco();
    }

    public void cadastrar(Doacao doacao) {
        try {

            conexao.conectar();
            Statement declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("INSERT INTO Doacao (fk_doador_id, chagas, hepatite_b, hepatite_c, sifilis, aids, htlv, teste_anemia, triagem_clinica, horario) "
                    + "VALUES("
                    + doacao.getFkDoador() + ",'"
                    + doacao.getChagas() + "','"
                    + doacao.getHepatiteB() + "','"
                    + doacao.getHepatiteC() + "','"
                    + doacao.getSifilis() + "','"
                    + doacao.getAids() + "','"
                    + doacao.getHtlv() + "','"
                    + doacao.getTesteAnemia() + "','"
                    + doacao.getTriagemClinica() + "','"
                    + doacao.getHorario() + "')");
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso."); //Mensagem de confirmação da operação

        } catch (SQLException ex) {
            Logger.getLogger(DoacaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Número de doação já cadastrado. Tente novamente.", "Número inválido", JOptionPane.ERROR_MESSAGE);
        }

    }

    public ArrayList<Doacao> buscarTodosBD() throws SQLException {
        ArrayList<Doacao> doacaoList = new ArrayList();
        ResultSet consulta;
        conexao.conectar();
        Statement declaracao;
        try {
            declaracao = conexao.con.createStatement();
            consulta = declaracao.executeQuery("SELECT * FROM Doacao");
            while (consulta.next()) {
                Doacao doacao = new Doacao();
                doacao.setChagas(consulta.getInt("chagas"));
                doacao.setFkDoador(consulta.getInt("fk_doador_id"));
                doacao.setHepatiteB(consulta.getInt("hepatite_b"));
                doacao.setHepatiteC(consulta.getInt("hepatite_c"));
                doacao.setHorario(consulta.getTimestamp("horario"));
                doacao.setHtlv(consulta.getInt("htlv"));
                doacao.setId(consulta.getInt("id"));
                doacao.setSifilis(consulta.getInt("sifilis"));
                doacao.setTesteAnemia(consulta.getInt("teste_anemia"));
                doacao.setTriagemClinica(consulta.getInt("triagem_clinica"));
                doacaoList.add(doacao);
            }
            conexao.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(DoacaoBD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return doacaoList;
    }

    public Doacao buscarIndividualBD(int ID) {
        try {
            Doacao doacao = new Doacao();
            conexao.conectar();
            ResultSet consulta;
            Statement declaracao = conexao.con.createStatement();
            consulta = declaracao.executeQuery("SELECT * FROM Doacao WHERE id =" + ID);
            while (consulta.next()) {
                doacao.setChagas(consulta.getInt("chagas"));
                doacao.setFkDoador(consulta.getInt("fk_doador_id"));
                doacao.setHepatiteB(consulta.getInt("hepatite_b"));
                doacao.setHepatiteC(consulta.getInt("hepatite_c"));
                doacao.setHorario(consulta.getTimestamp("horario"));
                doacao.setHtlv(consulta.getInt("htlv"));
                doacao.setId(consulta.getInt("id"));
                doacao.setSifilis(consulta.getInt("sifilis"));
                doacao.setTesteAnemia(consulta.getInt("teste_anemia"));
                doacao.setTriagemClinica(consulta.getInt("triagem_clinica"));
            }
            //System.out.println("Resultado da busca - IDDoacao:" + doacao.getID() + ". Lugares:" + doacao.getLugares() + ". Ocupada:" + doacao.getOcupada());
            conexao.desconectar();
            return doacao;
        } catch (SQLException ex) {
            Logger.getLogger(DoacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void alterar(Doacao doacao) {
        try {
            conexao.conectar();
            Statement declaracao;
            declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("UPDATE doacao SET "
                    + "fk_doador_id = " + doacao.getFkDoador()
                    + ", chagas  =" + doacao.getChagas()
                    + ", hepatite_b =" + doacao.getHepatiteB()
                    + ", hepatite_c =" + doacao.getHepatiteC()
                    + ", horario =" + doacao.getHorario()
                    + ", htlv =" + doacao.getHtlv()
                    + ", sifilis =" + doacao.getSifilis()
                    + ", teste_anemia =" + doacao.getTesteAnemia()
                    + ", triagem_clinica =" + doacao.getTriagemClinica()
                    + " WHERE id = " + doacao.getId());
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Alteração da doação " + doacao.getId() + " feita com sucesso."); //Mensagem de confirmação

        } catch (SQLException ex) {
            Logger.getLogger(DoacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(Doacao doacao) {
        try {
            conexao.conectar();
            Statement declaracao = conexao.con.createStatement();
            declaracao.executeUpdate("DELETE FROM doacao WHERE id = " + doacao.getId());
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Doação " + doacao.getId() + " excluída com sucesso."); //Mensagem de confirmação da operação

        } catch (SQLException ex) {
            Logger.getLogger(DoacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
