/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosangue;

import java.net.URL;
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
    private void geraPizza(ActionEvent event) {
        paneGrafico.getChildren().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Ótimo", 25),
                new PieChart.Data("Bom", 35),
                new PieChart.Data("Ruim", 15),
                new PieChart.Data("Regular", 25));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Desempenho em matemática");
        paneGrafico.getChildren().add(chart);
        //return chart;
    }

    @FXML
    private void geraLinha(ActionEvent event) {
        paneGrafico.getChildren().clear();
        final CategoryAxis eixoX = new CategoryAxis();
        final NumberAxis eixoY = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<>(eixoX, eixoY);

        lineChart.setTitle("Desempenho em matemática");
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Ótimo", 25));
        series.getData().add(new XYChart.Data("Bom", 35));
        series.getData().add(new XYChart.Data("Regular", 25));
        series.getData().add(new XYChart.Data("Ruim", 15));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        paneGrafico.getChildren().add(lineChart);

    }

    @FXML
    private void geraBarra(ActionEvent event) {
                paneGrafico.getChildren().clear();
        final CategoryAxis eixoX = new CategoryAxis();
        final NumberAxis eixoY = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(eixoX, eixoY);

        barChart.setTitle("Desempenho em matemática");
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Ótimo", 25));
        series.getData().add(new XYChart.Data("Bom", 35));
        series.getData().add(new XYChart.Data("Regular", 25));
                series.getData().add(new XYChart.Data("Ruim", 15));
        barChart.setLegendVisible(false);

        barChart.getData().add(series);
        paneGrafico.getChildren().add(barChart);

    }

}
