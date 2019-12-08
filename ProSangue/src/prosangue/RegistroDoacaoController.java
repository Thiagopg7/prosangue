/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosangue;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import objetos.Doacao;
import objetos.Doador;
import persistencia.DoacaoBD;
import persistencia.DoadorBD;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class RegistroDoacaoController implements Initializable {

    @FXML
    private TextField textCodigo;
    @FXML
    private Button buscaDoador;
    @FXML
    private CheckBox checkHepatiteB;
    @FXML
    private CheckBox checkHepatiteC;
    @FXML
    private CheckBox checkChagas;
    @FXML
    private CheckBox checkSifilis;
    @FXML
    private CheckBox checkAids;
    @FXML
    private CheckBox checkHtlv;
    @FXML
    private CheckBox checkAnemia;
    @FXML
    private CheckBox checkTriagemClinica;
    @FXML
    private Button btnCadastrarDoacao;
    @FXML
    private TextField textNome;
    @FXML
    private ComboBox<String> comboSangue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buscarDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();

        int doadorID = (Integer.parseInt(textCodigo.getText()));
        doador = doadorBD.buscarIndividualBD(doadorID);
        textNome.setText(doador.getNome());
        comboSangue.setValue(doador.getTipoSangue());

        int diasNecessarios;
        if (doador.getSexo().equals("Masculino")) {
            diasNecessarios = 90;
        } else {
            diasNecessarios = 120;
        }
        Timestamp horarioUltimaDoacao = doador.getUltimaDoacao();
        Timestamp horarioAtual = new Timestamp(System.currentTimeMillis());
        long diff = horarioAtual.getTime() - horarioUltimaDoacao.getTime();
        int dias = (int) (diff / (1000 * 60 * 60 * 24)); //Convertando de milisegundos para dias
        if (((doador.getSexo().equals("Masculino")) && (dias < diasNecessarios)) || ((doador.getSexo().equals("Feminino")) && (dias < diasNecessarios))) {
            textCodigo.setText("");
            Stage stage = (Stage) textCodigo.getScene().getWindow();
            stage.close();
            JOptionPane.showMessageDialog(null, "Fazem " + dias + " dias desde a última doação de " + doador.getNome() + ". O mínimo de dias necessários para doar novamente são " + diasNecessarios + ".");
        }
        btnCadastrarDoacao.setDisable(false);
    }

    
    @FXML
    private void cadastrarDoacao(ActionEvent event) {
        Doacao doacao;
        DoacaoBD doacaoBD;
        doacao = new Doacao();
        doacaoBD = new DoacaoBD();

        if (checkAids.isSelected()) {
            doacao.setAids(1);
        } else {
            doacao.setAids(0);
        }

        if (checkAnemia.isSelected()) {
            doacao.setTesteAnemia(1);
        } else {
            doacao.setTesteAnemia(0);
        }

        if (checkChagas.isSelected()) {
            doacao.setChagas(1);
        } else {
            doacao.setChagas(0);
        }

        if (checkHepatiteB.isSelected()) {
            doacao.setHepatiteB(1);
        } else {
            doacao.setHepatiteB(0);
        }

        if (checkHepatiteC.isSelected()) {
            doacao.setHepatiteC(1);
        } else {
            doacao.setHepatiteC(0);
        }

        if (checkHtlv.isSelected()) {
            doacao.setHtlv(1);
        } else {
            doacao.setHtlv(0);
        }

        if (checkSifilis.isSelected()) {
            doacao.setSifilis(1);
        } else {
            doacao.setHtlv(0);
        }

        if (checkTriagemClinica.isSelected()) {
            doacao.setTriagemClinica(1);
        } else {
            doacao.setTriagemClinica(0);
        }

        if (checkAids.isSelected()) {
            doacao.setAids(1);
        } else {
            doacao.setAids(0);
        }

        doacao.setFkDoador(Integer.parseInt(textCodigo.getText()));
        Timestamp horarioAtual = new Timestamp(System.currentTimeMillis());
        doacao.setHorario(horarioAtual);

        //Alterando o horário da última doação do doador no banco
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();
        doador = doadorBD.buscarIndividualBD(Integer.parseInt(textCodigo.getText()));
        doador.setUltimaDoacao(horarioAtual);
        doadorBD.alterar(doador);

        //Notificando o doador que a sua doação anterior não acusava um problema e atual acusa.
        Doacao doacaoAnterior;
        DoacaoBD doacaoAnteriorBD;
        doacaoAnterior = new Doacao();
        doacaoAnteriorBD = new DoacaoBD();
        ArrayList<String> mensagensNotificacao = new ArrayList<String>();

        doacaoAnterior = doacaoAnteriorBD.buscarUltimaDoacaoIndividual(doacao.getFkDoador());

        if (doacao.getAids() != doacaoAnterior.getAids()) {
            if (doacao.getAids() == 1) {
                mensagensNotificacao.add("AIDS foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("AIDS foi de positivo para negativo. ");
            }
        }
        if (doacao.getChagas() != doacaoAnterior.getChagas()) {
            if (doacao.getChagas() == 1) {
                mensagensNotificacao.add("Chagas foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Chagas foi de positivo para negativo. ");
            }
        }
        if (doacao.getHepatiteB() != doacaoAnterior.getHepatiteB()) {
            if (doacao.getHepatiteB() == 1) {
                mensagensNotificacao.add("Hepatite B foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Hepatite B foi de positivo para negativo. ");
            }
        }
        if (doacao.getHepatiteC() != doacaoAnterior.getHepatiteC()) {
            if (doacao.getHepatiteC() == 1) {
                mensagensNotificacao.add("Hepatite C foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Hepatite C foi de positivo para negativo. ");
            }
        }
        if (doacao.getHtlv() != doacaoAnterior.getHtlv()) {
            if (doacao.getHtlv() == 1) {
                mensagensNotificacao.add("HTLV foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("HTLV foi de positivo para negativo. ");
            }
        }
        if (doacao.getSifilis() != doacaoAnterior.getSifilis()) {
            if (doacao.getSifilis() == 1) {
                mensagensNotificacao.add("Sífilis foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Sífilis foi de positivo para negativo. ");
            }
        }
        if (doacao.getTesteAnemia() != doacaoAnterior.getTesteAnemia()) {
            if (doacao.getTesteAnemia() == 1) {
                mensagensNotificacao.add("Anemia foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Anemia foi de positivo para negativo. ");
            }
        }
        if (doacao.getTriagemClinica() != doacaoAnterior.getTriagemClinica()) {
            if (doacao.getTriagemClinica() == 1) {
                mensagensNotificacao.add("Triagem Clínica foi de negativo para positivo. ");
            } else {
                mensagensNotificacao.add("Triagem Clínica foi de positivo para negativo. ");
            }
        }

        if (mensagensNotificacao.size() > 0) {
            String mensagemFinal = "Houve alterações da última doação para a atual. ";
            for (int i = 0; i < mensagensNotificacao.size(); i++) {
                mensagemFinal = mensagemFinal + mensagensNotificacao.get(i);
            }
            mensagemFinal = mensagemFinal + "Deseja notificar o doador " + doador.getNome() + "?";
            JOptionPane.showConfirmDialog(null, mensagemFinal);
        }

        doacaoBD.cadastrar(doacao);

    }


    private void enviarNotificacao(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Doador notificado com sucesso.");
    }

}
