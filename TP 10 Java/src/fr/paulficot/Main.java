package fr.paulficot;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application {

    /**
     *
     * @param primaryStage : stage
     * @throws Exception : exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            TabPane tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            Tab tab1 = new Tab("1");
            Tab tab2 = new Tab("2");
            Tab tab3 = new Tab("3");
            Tab tab4 = new Tab("4");

            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);

            tab1.setContent(tab1());
            tab2.setContent(tab2());
            tab3.setContent(tab3());
            tab4.setContent(tab4());

            Scene scene = new Scene(tabPane);
            primaryStage.setTitle("TP 10 Java PFICOT");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
       catch(Exception e)    {
            e.printStackTrace();
        }
    }

    public static Group tab1() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab1Combo1 = new ComboBox();
        for(MATIERE matiere : MATIERE.values()) {
            tab1Combo1.getItems().add(matiere);
        }
        ComboBox tab1Combo2 = new ComboBox();
        for(NIVEAU niveau : NIVEAU.values()) {
            tab1Combo2.getItems().add(niveau);
        }

        tab1Combo1.getSelectionModel().selectFirst();
        tab1Combo2.getSelectionModel().selectFirst();

        //tab1Combo1.valueProperty().addListener(changeListener);
        //tab1Combo2.valueProperty().addListener(changeListener);

        sousGrid.add(tab1Combo1, 0, 0, 1, 1);
        sousGrid.add(tab1Combo2, 1, 0, 1, 1);

        mainGrid.add(sousGrid, 0, 0, 1, 1);

        //Define x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Classes");

        //Define y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Moyenne");

        BarChart barChart = new BarChart(xAxis, yAxis);

        updateCharts(barChart);

        mainGrid.add(barChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    public static Group tab2() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab2Combo1 = new ComboBox();
        ComboBox tab2Combo2 = new ComboBox();
        ComboBox tab2Combo3 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab2Combo1.getItems().add(niveau);
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab2Combo2.getItems().add(matiere.getNom());
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab2Combo3.getItems().add(matiere.getNbrEpreuve());
        }

        tab2Combo1.getSelectionModel().selectFirst();
        tab2Combo2.getSelectionModel().selectFirst();
        tab2Combo3.getSelectionModel().selectFirst();

        sousGrid.add(tab2Combo1, 0, 0, 1, 1);
        sousGrid.add(tab2Combo2, 1, 0, 1, 1);
        sousGrid.add(tab2Combo3, 2, 0, 1, 1);
        mainGrid.add(sousGrid, 0, 0, 1, 1);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y axis");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("XD");


        int x=0;
        for(Classe classe : Moteur.getInstance().getListeClasse()) {
            x+=5;
            dataSeries1.getData().add(new XYChart.Data(x, Moteur.getInstance().occurence(classe)));
        }
        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);
        mainGrid.add(lineChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    public static Group tab3() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab3Combo1 = new ComboBox();
        ComboBox tab3Combo2 = new ComboBox();
        ComboBox tab3Combo3 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab3Combo1.getItems().add(niveau);
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab3Combo2.getItems().add(matiere.getNom());
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab3Combo3.getItems().add(matiere.getNbrEpreuve());
        }

        sousGrid.add(tab3Combo1, 0, 0, 1, 1);
        sousGrid.add(tab3Combo2, 1, 0, 1, 1);
        sousGrid.add(tab3Combo3, 2, 0, 1, 1);
        mainGrid.add(sousGrid, 0, 0, 1, 1);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y axis");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);
        mainGrid.add(lineChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    public static Group tab4() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab4Combo1 = new ComboBox();
        ComboBox tab4Combo2 = new ComboBox();
        ComboBox tab4Combo3 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab4Combo1.getItems().add(niveau);
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab4Combo2.getItems().add(matiere.getNom());
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab4Combo3.getItems().add(matiere.getNbrEpreuve());
        }

        sousGrid.add(tab4Combo1, 0, 0, 1, 1);
        sousGrid.add(tab4Combo2, 1, 0, 1, 1);
        sousGrid.add(tab4Combo3, 2, 0, 1, 1);
        mainGrid.add(sousGrid, 0, 0, 1, 1);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y axis");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);
        mainGrid.add(lineChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    public static void updateCharts(BarChart barChart) {
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Moyenne | matière | Niveau");

        MATIERE matiere = MATIERE.MATHS;
        for(NIVEAU niveau : NIVEAU.values()) {
            for(LETTRE lettre : LETTRE.values()) {
                dataSeries1.getData().add(new XYChart.Data(niveau + " " + lettre, Moteur.moyenne(niveau, lettre, matiere)));
            }
            break;
        }

        barChart.getData().addAll(dataSeries1);

        ChangeListener changeListener =  new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
            }
        };
    }

    /**
     *
     * @param args : main args
     */
    public static void main(String[] args) {
        //Moteur
        Moteur.getInstance().generer();

        //JavaFX
        launch(args);

        System.out.println("End");
    }
}
