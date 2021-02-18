package fr.paulficot;

import javafx.scene.chart.*;
import javafx.scene.control.ListView;

/**
 * Setup and refresh every JavaFX
 * datas in charts
 *
 * @author Paul FICOT
 * @version 1.0
 */
public class Graphs {

    private static Graphs graphs_instance = null;

    private static ListView listView;
    private static BarChart barChart;
    private static LineChart lineChart;

    /**
     * Graph object constructor
     */
    private Graphs() {
    }

    /**
     * Singleton of graph
     * Create a Graph and return it
     * if already exists return it
     *
     * @return Graph instance
     */
    public static Graphs getInstance() {
        if (graphs_instance == null)
            graphs_instance = new Graphs();

        return graphs_instance;
    }

    public static ListView setupListView() {
        listView = new ListView();

        return listView;
    }

    public static BarChart setupBarChart() {
        //Define x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Classes");
        xAxis.setAnimated(false);

        //Define y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Moyenne");
        yAxis.setAnimated(false);

        barChart = new BarChart(xAxis, yAxis);

        return barChart;
    }

    public static void updateBarChart(MATIERE matiere, NIVEAU niveau) {
        if(barChart == null) {
            setupBarChart();
        }
        barChart.getData().clear();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Moyenne | matière | Niveau");

        for(NIVEAU niveau1 : NIVEAU.values()) {
            for(LETTRE lettre : LETTRE.values()) {
                dataSeries.getData().add(new XYChart.Data(niveau + " " + lettre, Moteur.tab1Moyenne(niveau, lettre, matiere)));
            }
            break;
        }
        barChart.getData().addAll(dataSeries);
    }

    public static LineChart setupLineChart() {
        //Define x axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x axis");
        xAxis.setAnimated(false);

        //Define y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y axis");
        yAxis.setAnimated(false);

        lineChart = new LineChart(xAxis, yAxis);

        return lineChart;
    }

    public static void updateLineChart(NIVEAU niveau, MATIERE matiere, int epreuve) {
        if(lineChart == null) {
            setupLineChart();
        }
        lineChart.getData().clear();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Niveau | Matiere | Epreuve");

        int x=0;
        for(Classe classe : Moteur.getInstance().getListeClasse()) {
            x+=5;
            dataSeries.getData().add(new XYChart.Data(x, Moteur.tab2Gaussian(niveau, matiere, epreuve)));
        }
        lineChart.getData().addAll(dataSeries);
    }
}
