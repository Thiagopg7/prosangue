/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosangue;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author felip
 */
public class CadastroPacienteController implements Initializable {

    @FXML
    private Button buscaDoador;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textCodigo;
    @FXML
    private TextField textNome;
    @FXML
    private DatePicker pickerNascimento;
    @FXML
    private TextField textPai;
    @FXML
    private TextField textMae;
    @FXML
    private TextField textRG;
    @FXML
    private RadioButton radioMasculino;
    @FXML
    private RadioButton radioFeminino;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarDoador(ActionEvent event) {
    }

    @FXML
    private void alterarDoador(ActionEvent event) {
    }

    @FXML
    private void excluirDoador(ActionEvent event) {
    }

    @FXML
    private void cancelarCadastro(ActionEvent event) {
    }
    
}
