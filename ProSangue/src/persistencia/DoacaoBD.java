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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        public Doacao buscarUltimaDoacaoIndividual(int idDoador) {
        try {
            Doacao doacao = new Doacao();
            conexao.conectar();
            ResultSet consulta;
            Statement declaracao = conexao.con.createStatement();
            consulta = declaracao.executeQuery("SELECT * FROM Doacao WHERE fk_doador_id = " + idDoador + " AND horario = (SELECT MAX(horario)) ORDER BY horario");
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

    public ObservableList<Integer> buscarQtdSexo() throws SQLException {
        ObservableList<Integer> qtd = FXCollections.observableArrayList();
        ResultSet consulta;
        conexao.conectar();
        Statement declaracao = conexao.con.createStatement();
        consulta = declaracao.executeQuery("SELECT COUNT(sexo) "
                + "FROM doador "
                + "WHERE sexo = 'Masculino'");

        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(sexo)"));
        }
        consulta = declaracao.executeQuery("SELECT COUNT(sexo) "
                + "FROM doador "
                + "WHERE sexo = 'Feminino'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(sexo)"));
        }

        conexao.desconectar();
        return qtd;
    }

    public ObservableList<Integer> buscarQtdTipoSanguineo() throws SQLException {
        ObservableList<Integer> qtd = FXCollections.observableArrayList();
        ResultSet consulta;
        conexao.conectar();
        Statement declaracao = conexao.con.createStatement();

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'A+'");

        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'A-'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'B+'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'B-'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'AB+'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'AB-'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'O+'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(tipo_sangue) "
                + "FROM doacao "
                + "JOIN doador "
                + "WHERE doacao.fk_doador_id = doador.id AND tipo_sangue = 'O-'");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(tipo_sangue)"));
        }

        conexao.desconectar();
        return qtd;
    }

    public ObservableList<Integer> buscarQtdProblemas() throws SQLException {
        ObservableList<Integer> qtd = FXCollections.observableArrayList();
        ResultSet consulta;
        conexao.conectar();
        Statement declaracao = conexao.con.createStatement();

//          private int hepatiteC;;
//    private int chagas;
//    private int sifilis;
//    private int aids;
//    private int htlv;
//    private int testeAnemia;
//    private int triagemClinica;
        consulta = declaracao.executeQuery("SELECT COUNT(hepatite_b) "
                + "FROM doacao "
                + "WHERE hepatite_b = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(hepatite_b)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(hepatite_c) "
                + "FROM doacao "
                + "WHERE hepatite_c = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(hepatite_c)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(chagas) "
                + "FROM doacao "
                + "WHERE chagas = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(chagas)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(sifilis) "
                + "FROM doacao "
                + "WHERE sifilis = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(sifilis)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(aids) "
                + "FROM doacao "
                + "WHERE aids = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(aids)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(htlv) "
                + "FROM doacao "
                + "WHERE htlv = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(htlv)"));
        }


        consulta = declaracao.executeQuery("SELECT COUNT(teste_anemia) "
                + "FROM doacao "
                + "WHERE teste_anemia = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(teste_anemia)"));
        }

        consulta = declaracao.executeQuery("SELECT COUNT(triagem_clinica) "
                + "FROM doacao "
                + "WHERE triagem_clinica = 1");
        while (consulta.next()) {
            qtd.add(consulta.getInt("COUNT(triagem_clinica)"));
        }

        conexao.desconectar();
        return qtd;
    }

}
