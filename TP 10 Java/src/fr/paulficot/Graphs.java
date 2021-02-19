package fr.paulficot;

import javafx.scene.chart.*;
import javafx.scene.control.ListView;

import javax.naming.ldap.HasControls;
import java.util.HashMap;

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
    private static PieChart pieChart4;
    private static PieChart pieChart5;

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

    /**
     * Setup the listview of tab0
     *
     * @return str of all students and marks
     */
    public static ListView setupListView0() {
        listView = new ListView();

        return listView;
    }

    public static ListView setupListView1() {
        listView = new ListView();

        return listView;
    }

    public static BarChart setupBarChart2() {
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

    public static LineChart setupLineChart3() {
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

    public static PieChart setupPieChart4() {
        pieChart4 = new PieChart();

        return pieChart4;
    }

    public static PieChart setupPieChart5() {
        pieChart5 = new PieChart();

        return pieChart5;
    }

    public static void updateBarChart2(MATIERE matiere, NIVEAU niveau) {
        if(barChart == null) {
            setupBarChart2();
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

    public static void updateLineChart3(NIVEAU niveau, MATIERE matiere, int epreuve) {
        if(lineChart == null) {
            setupLineChart3();
        }
        lineChart.getData().clear();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Niveau | Matiere | Epreuve");

        int x=0;
        for(Classe classe : Moteur.getInstance().getListeClasse()) {
            x+=5;
            for(LETTRE lettre : LETTRE.values()) {
                dataSeries.getData().add(new XYChart.Data(x, Moteur.tab2Gaussian(niveau, lettre, matiere, epreuve)));
            }
        }
        lineChart.getData().addAll(dataSeries);
    }

    public static void updatePieChart4(NIVEAU niveau, MATIERE matiere) {
        if(pieChart4 == null) {
            setupPieChart4();
        }
        pieChart4.getData().clear();

        HashMap<String, Integer> hashMap = Moteur.tab4AvgPerTopicPerGrade(niveau, matiere);

        for(String key : hashMap.keySet()) {
            PieChart.Data slice = new PieChart.Data(key + "= " + hashMap.get(key), hashMap.get(key));
            pieChart4.getData().add(slice);
        }
    }

    public static void updatePieChart5(NIVEAU niveau) {
        if(pieChart5 == null) {
            setupPieChart5();
        }
        pieChart5.getData().clear();

        HashMap<String, Integer> hashMap = Moteur.tab5AvgPerGrade(niveau);

        for(String key : hashMap.keySet()) {
            PieChart.Data slice = new PieChart.Data(key + "= " + hashMap.get(key), hashMap.get(key));
            pieChart5.getData().add(slice);
        }
    }
}
