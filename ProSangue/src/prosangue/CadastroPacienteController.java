package prosangue;

import java.net.URL;
import java.sql.Array;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import persistencia.DoadorBD;

public class CadastroPacienteController {

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
    private ToggleGroup sexoRadioGroup;

    @FXML
    private RadioButton radioMasculino;

    @FXML
    private RadioButton radioFeminino;
    @FXML
    private TableView<DoadorBD> tableView;
    @FXML
    private TableColumn<DoadorBD, String> tableColumnLogin;
    @FXML
    private TableColumn<DoadorBD, String> tableColumnNome;
    @FXML
    private TableColumn<DoadorBD, String> tableColumnEmail;
    @FXML
    private Button btnInserir;
    
    private ObservableList<DoadorBD> observableDoadorBD;
    
    DoadorBD novodoador = new DoadorBD();
    
    
    @FXML
    void alterarDoador(ActionEvent event) {

    }

    @FXML
    void cadastrarDoador(ActionEvent event) {

    }

    @FXML
    void cancelarCadastro(ActionEvent event) {

    }

    @FXML
    void excluirDoador(ActionEvent event) {

    }

    @FXML
    private void excluirTabela(ActionEvent event) {
        int i = tableView.getSelectionModel().getSelectedIndex();
        observableDoadorBD.remove(i);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Pedido Excluido");
        alert.setHeaderText(null);
        alert.setContentText("Pedido excluído com sucesso!");
        alert.showAndWait();
        tableView.refresh();
    }

    @FXML
    private void alterarTabela(ActionEvent event) {
    }

    @FXML
    private void inserirTabela(ActionEvent event) {
        int i = 0;
        novodoador.buscarIndividualBD(i);
        novodoador.setLogin(/*login pego do banco*/);
        novodoador.setNome(/*nome pego do banco*/);
        novodoador.setEmail(/*email pego do banco*/);
        observableDoadorBD.add(novodoador);
        
    }
    public void initialize(URL location, ResourceBundle resources) {
      observableDoadorBD = FXCollections.observableArrayList();      
      tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("loginDoador"));
      tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeDoador"));
      tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("emailDoador"));
      tableView.setItems(observableDoadorBD);
    }

}
