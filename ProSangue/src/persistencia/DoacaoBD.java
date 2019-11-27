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
            declaracao.executeUpdate("INSERT INTO Doacao (fk_doador_id, hepatite_b, hepatite_c, sifilis, aids, htlv, teste_anemia, triagem_clinica, horario, imunohematologia) " + "VALUES('"
                    + doacao.getFkDoador() + "','"
                    + doacao.isHepatiteB() + "','"
                    + doacao.isHepatiteC() + "','"
                    + doacao.isSifilis() + "','"
                    + doacao.isAids() + "','"
                    + doacao.isHtlv() + "','"
                    + doacao.isTesteAnemia() + "','"
                    + doacao.isTriagemClinica() + "','"
                    + doacao.getHorario() + "','"
                    + doacao.getImunohematologia() + "')");
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
                doacao.setChagas(consulta.getBoolean("chagas"));
                doacao.setFkDoador(consulta.getInt("fk_doador_id"));
                doacao.setHepatiteB(consulta.getBoolean("hepatite_b"));
                doacao.setHepatiteC(consulta.getBoolean("hepatite_c"));
                doacao.setHorario(consulta.getTimestamp("horario"));
                doacao.setHtlv(consulta.getBoolean("htlv"));
                doacao.setId(consulta.getInt("id"));
                doacao.setImunohematologia(consulta.getString("imunohematologia"));
                doacao.setSifilis(consulta.getBoolean("sifilis"));
                doacao.setTesteAnemia(consulta.getBoolean("teste_anemia"));
                doacao.setTriagemClinica(consulta.getBoolean("triagem_clinica"));
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
                doacao.setChagas(consulta.getBoolean("chagas"));
                doacao.setFkDoador(consulta.getInt("fk_doador_id"));
                doacao.setHepatiteB(consulta.getBoolean("hepatite_b"));
                doacao.setHepatiteC(consulta.getBoolean("hepatite_c"));
                doacao.setHorario(consulta.getTimestamp("horario"));
                doacao.setHtlv(consulta.getBoolean("htlv"));
                doacao.setId(consulta.getInt("id"));
                doacao.setImunohematologia(consulta.getString("imunohematologia"));
                doacao.setSifilis(consulta.getBoolean("sifilis"));
                doacao.setTesteAnemia(consulta.getBoolean("teste_anemia"));
                doacao.setTriagemClinica(consulta.getBoolean("triagem_clinica"));
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
                    + ", chagas  =" + doacao.isChagas()
                    + ", hepatite_b =" + doacao.isHepatiteB()
                    + ", hepatite_c =" + doacao.isHepatiteC()
                    + ", horario =" + doacao.getHorario()
                    + ", htlv =" + doacao.isHtlv()
                    + ", imunohematologia =" + doacao.getImunohematologia()
                    + ", sifilis =" + doacao.isSifilis()
                    + ", teste_anemia =" + doacao.isTesteAnemia()
                    + ", triagem_clinica =" + doacao.isTriagemClinica()
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
