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
    private Button btnAlterarDoacao;
    @FXML
    private Button btnEnviarNotificacao;
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

//        LocalDate localDate = pickerNascimento.getValue();
//        Date dataNascimentoConvertida = Date.valueOf(localDate); // Magic happens here!
        doacao.setFkDoador(Integer.parseInt(textCodigo.getText()));

        Timestamp horarioAtual = new Timestamp(System.currentTimeMillis());
        doacao.setHorario(horarioAtual);
        doacaoBD.cadastrar(doacao);

    }

    @FXML
    private void alterarDoacao(ActionEvent event) {
    }

    @FXML
    private void enviarNotificacao(ActionEvent event) {
    }

}
