package prosangue;

import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
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
import objetos.Doador;
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
    private TableView<Doador> tableView;
    @FXML
    private TableColumn<Doador, String> tableColumnLogin;
    @FXML
    private TableColumn<Doador, String> tableColumnNome;
    @FXML
    private TableColumn<Doador, String> tableColumnEmail;

    private ObservableList<Doador> observableDoador;

    @FXML
    private Button btnInserir;
    @FXML
    private TextField textEndereco;

    @FXML
    void alterarDoador(ActionEvent event) {

    }

    @FXML
    void cadastrarDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();

        doador.setNome(textNome.getText());
        // doador.setDataNascimento(pickerNascimento.get);
        doador.setEndereco(textEndereco.getText());
        // doador.setID(dataNascimento);
        doador.setMae(textMae.getText());
        doador.setPai(textPai.getText());
        doador.setRg(textRG.getText());
//        if (radioFeminino.isSelected()) {
//            doador.setSexo("Feminino");
//        }
//        if (radioMasculino.isSelected()) {
//            doador.setSexo("Masculino");
//        }

        // doador.setUltimaDoacao(dataNascimento);
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
        observableDoador.remove(i);
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
        // novodoador.buscarIndividualBD(list);
        // novodoador.setLogin(/*login pego do banco*/);
        // novodoador.setNome(/*nome pego do banco*/);
        //novodoador.setEmail(/*email pego do banco*/);
        Doador doador = new Doador();
        observableDoador.add(doador);

    }

    public void initialize(URL location, ResourceBundle resources) throws SQLException {
        DoadorBD doadorBD;
        doadorBD = new DoadorBD();
        observableDoador = FXCollections.observableArrayList();
        observableDoador = doadorBD.buscarTodosBD();

        tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("loginDoador"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("emailDoador"));
        tableView.setItems(observableDoador);
    }

}
