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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarDoador(ActionEvent event) {
    }
    
}
