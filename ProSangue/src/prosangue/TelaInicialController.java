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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author felip
 */
public class TelaInicialController implements Initializable {

    @FXML
    private TextField textCodPaciente;
    @FXML
    private Button btnPesquisar;
    @FXML
    private MenuItem menuItemCadastroDoador;
    @FXML
    private MenuItem menuItemRelatorios;
    @FXML
    private MenuItem menuItemRegistroDoacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void abreTelaCadastroDoador(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastroPaciente.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar a página");
            System.out.println(e);

        }
    }

    @FXML
    private void pesquisarDoador(ActionEvent event) {

    }

    @FXML
    private void abreTelaRelatorios(ActionEvent event) {

    }

    @FXML
    private void abreTelaRegistroDoacao(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistroDoacao.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar a página");
            System.out.println(e);

        }
    }

}
