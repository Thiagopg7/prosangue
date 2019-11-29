package prosangue;

import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import objetos.Doador;
import persistencia.DoadorBD;

public class CadastroPacienteController implements Initializable {

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
    private TableColumn<Doador, String> tableColumnNome;

    private ObservableList<Doador> observableDoador;

    @FXML
    private Button btnInserir;
    @FXML
    private TextField textEndereco;
    @FXML
    private TableColumn<Doador, Integer> tableColumnCod;
    @FXML
    private TableColumn<Doador, String> tableColumnRG;
    @FXML
    private RadioButton radioFeminino;
    @FXML
    private RadioButton radioMasculino;

    @FXML
    void alterarDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();

        doador.setNome(textNome.getText());
        LocalDate localDate = pickerNascimento.getValue();
        Date dataNascimentoConvertida = Date.valueOf(localDate); // Magic happens here!
        doador.setDataNascimento(dataNascimentoConvertida);
        doador.setEndereco(textEndereco.getText());
        doador.setID(Integer.parseInt(textCodigo.getText()));
        doador.setMae(textMae.getText());
        doador.setPai(textPai.getText());
        doador.setRg(textRG.getText());

        if (radioFeminino.isSelected()) {;;
            doador.setSexo("Feminino");
        }
        if (radioMasculino.isSelected()) {
            doador.setSexo("Masculino");
        }

        doadorBD.alterar(doador);

    }

    @FXML
    void cadastrarDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();

        doador.setNome(textNome.getText());
        LocalDate localDate = pickerNascimento.getValue();
        Date dataNascimentoConvertida = Date.valueOf(localDate); // Magic happens here!

        doador.setDataNascimento(dataNascimentoConvertida);
        doador.setEndereco(textEndereco.getText());
        // doador.setID(dataNascimento);
        doador.setMae(textMae.getText());
        doador.setPai(textPai.getText());
        doador.setRg(textRG.getText());
        if (radioFeminino.isSelected()) {;;
            doador.setSexo("Feminino");
        }
        if (radioMasculino.isSelected()) {
            doador.setSexo("Masculino");
        }

        // doador.setUltimaDoacao(dataNascimento);
        doadorBD.cadastrar(doador);
        observableDoador.add(doador);
    }

    @FXML
    void cancelarCadastro(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void excluirDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();
        doador.setID(Integer.parseInt(textCodigo.getText()));
        doadorBD.excluir(doador);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoadorBD doadorBD;
        doadorBD = new DoadorBD();
        observableDoador = FXCollections.observableArrayList();
        try {
            observableDoador = doadorBD.buscarTodosBD();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("id"));

        ToggleGroup toggleGroup = new ToggleGroup();;
        radioFeminino.setToggleGroup(toggleGroup);
        radioMasculino.setToggleGroup(toggleGroup);

        tableColumnCod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableView.setItems(observableDoador);
    }

    @FXML
    private void buscarDoador(ActionEvent event) {
        Doador doador;
        DoadorBD doadorBD;
        doador = new Doador();
        doadorBD = new DoadorBD();

        int doadorID = (Integer.parseInt(textCodigo.getText()));
        doador = doadorBD.buscarIndividualBD(doadorID);
        textEndereco.setText(doador.getEndereco());
        textNome.setText(doador.getNome());
        textMae.setText(doador.getMae());
        textPai.setText(doador.getPai());
        textRG.setText(doador.getRg());
        if (doador.getSexo().equals("Masculino")) {
            radioMasculino.setSelected(true);
        } else if (doador.getSexo().equals("Feminino")) {
            radioFeminino.setSelected(true);
        }
        Date date = doador.getDataNascimento();
        LocalDate localD = date.toLocalDate();
        pickerNascimento.setValue(localD);

        //pickerNascimento.set
    }

}
