/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosangue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import persistencia.DoacaoBD;

/**
 * FXML Controller class
 *
 * @author a1589482
 */
public class GraficosController implements Initializable {

    @FXML
    private Button buttonPizza;
    @FXML
    private Button buttonLinha;
    @FXML
    private Button buttonBarra;
    @FXML
    private HBox hboxPrincipal;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    private Pane paneGrafico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void geraPizza(ActionEvent event) throws SQLException {
        paneGrafico.getChildren().clear();

        DoacaoBD doacaoBD = new DoacaoBD();
        ObservableList<Integer> qtdSexo;
        qtdSexo = FXCollections.observableArrayList();
        qtdSexo = doacaoBD.buscarQtdSexo();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Masculino(" + qtdSexo.get(0) + ")", qtdSexo.get(0)),
                new PieChart.Data("Feminino(" + qtdSexo.get(1) + ")", qtdSexo.get(1))
        );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Doador Por Sexo");
        paneGrafico.getChildren().add(chart);
        //return chart;
    }

    @FXML
    private void geraLinha(ActionEvent event) throws SQLException {
        paneGrafico.getChildren().clear();
        final CategoryAxis eixoX = new CategoryAxis();
        final NumberAxis eixoY = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<>(eixoX, eixoY);

        DoacaoBD doacaoBD = new DoacaoBD();
        ObservableList<Integer> qtd;
        qtd = FXCollections.observableArrayList();
        qtd = doacaoBD.buscarQtdTipoSanguineo();

        lineChart.setTitle("Doação por Tipo Sanguíneo");
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("A+(" + qtd.get(0) + ")", qtd.get(0)));
        series.getData().add(new XYChart.Data("A-(" + qtd.get(1) + ")", qtd.get(1)));
        series.getData().add(new XYChart.Data("B+(" + qtd.get(2) + ")", qtd.get(2)));
        series.getData().add(new XYChart.Data("B-(" + qtd.get(3) + ")", qtd.get(3)));
        series.getData().add(new XYChart.Data("AB+(" + qtd.get(4) + ")", qtd.get(4)));
        series.getData().add(new XYChart.Data("AB-(" + qtd.get(5) + ")", qtd.get(5)));
        series.getData().add(new XYChart.Data("O+(" + qtd.get(6) + ")", qtd.get(6)));
        series.getData().add(new XYChart.Data("O-(" + qtd.get(7) + ")", qtd.get(7)));

        //       series.getData().add(new XYChart.Data("A+(" + qtd.get(0) + ")", qtd.get(0)));
        //       series.getData().add(new XYChart.Data("A+(" + qtd.get(0) + ")", qtd.get(0)));
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        paneGrafico.getChildren().add(lineChart);

    }

    @FXML
    private void geraBarra(ActionEvent event) throws SQLException {
        paneGrafico.getChildren().clear();
        final CategoryAxis eixoX = new CategoryAxis();
        final NumberAxis eixoY = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(eixoX, eixoY);

        DoacaoBD doacaoBD = new DoacaoBD();
        ObservableList<Integer> qtd;
        qtd = FXCollections.observableArrayList();
        qtd = doacaoBD.buscarQtdProblemas();

        barChart.setTitle("Problemas Em Doações");
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Hepatite B(" + qtd.get(0) + ")", qtd.get(0)));
        series.getData().add(new XYChart.Data("Hepatite C(" + qtd.get(1) + ")", qtd.get(1)));
        series.getData().add(new XYChart.Data("Chagas(" + qtd.get(2) + ")", qtd.get(2)));
        series.getData().add(new XYChart.Data("Sífilis(" + qtd.get(3) + ")", qtd.get(3)));
        series.getData().add(new XYChart.Data("AIDS(" + qtd.get(4) + ")", qtd.get(4)));
        series.getData().add(new XYChart.Data("HTLV(" + qtd.get(5) + ")", qtd.get(5)));
        series.getData().add(new XYChart.Data("Anemia(" + qtd.get(6) + ")", qtd.get(6)));
        series.getData().add(new XYChart.Data("Triagem Clínica(" + qtd.get(7) + ")", qtd.get(7)));

        barChart.setLegendVisible(false);
        barChart.getData().add(series);
        paneGrafico.getChildren().add(barChart);

    }

}
