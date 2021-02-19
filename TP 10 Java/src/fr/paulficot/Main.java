package fr.paulficot;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main class
 * contains JavaFX frame
 *
 * @author Paul FICOT
 * @version 1.0
 */
public class Main extends Application {

    /**
     * JavaFX Start method
     *
     * @param primaryStage : stage
     * @throws Exception : exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            TabPane tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            Tab tab0 = new Tab("0");
            Tab tab1 = new Tab("1");
            Tab tab2 = new Tab("2");
            Tab tab3 = new Tab("3");
            Tab tab4 = new Tab("4");
            Tab tab5 = new Tab("5");

            tabPane.getTabs().add(tab0);
            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);
            tabPane.getTabs().add(tab5);

            tab0.setContent(tab0());
            tab1.setContent(tab1());
            tab2.setContent(tab2());
            tab3.setContent(tab3());
            tab4.setContent(tab4());
            tab5.setContent(tab5());

            Scene scene = new Scene(tabPane);
            primaryStage.setTitle("TP 10 Java PFICOT");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
       catch(Exception e)    {
            e.printStackTrace();
        }
    }

    /**
     * Tab 0 of the JavaFX frame
     * contains list of every marks
     * per student
     *
     * @return JavaFX group for tab0
     */
    public static Group tab0() {
        Graphs.getInstance();
        GridPane mainGrid = new GridPane();

        ListView tab0List = Graphs.setupListView0();

        mainGrid.add(tab0List, 0, 1, 1, 1);

        for(String line : Moteur.listeNotes0(Moteur.getListeClasse())) {
            tab0List.getItems().add(line);
        }

        return new Group(mainGrid);
    }

    /**
     * Tab 1 of the JavaFX frame
     * contains list of stats per student
     *
     * @return JavaFX group for tab0
     */
    public static Group tab1() {
        Graphs.getInstance();
        GridPane mainGrid = new GridPane();

        ListView tab0List = Graphs.setupListView1();

        mainGrid.add(tab0List, 0, 1, 1, 1);

        for(String line : Moteur.classesStats1(Moteur.getListeClasse())) {
            tab0List.getItems().add(line);
        }

        return new Group(mainGrid);
    }

    /**
     * Tab 2 of the JavaFX frame
     * Barchar average per class per topic
     *
     * @return JavaFX group for tab1
     */
    public static Group tab2() {
        Graphs.getInstance();
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab2Combo1 = new ComboBox();
        for(MATIERE matiere : MATIERE.values()) {
            tab2Combo1.getItems().add(matiere);
        }
        ComboBox tab2Combo2 = new ComboBox();
        for(NIVEAU niveau : NIVEAU.values()) {
            tab2Combo2.getItems().add(niveau);
        }

        tab2Combo1.getSelectionModel().selectFirst();
        tab2Combo2.getSelectionModel().selectFirst();

        ChangeListener changeListener = (observableValue, o, t1) -> Graphs.updateBarChart2((MATIERE) tab2Combo1.getValue(), (NIVEAU) tab2Combo2.getValue());

        tab2Combo1.valueProperty().addListener(changeListener);
        tab2Combo2.valueProperty().addListener(changeListener);

        sousGrid.add(tab2Combo1, 0, 0, 1, 1);
        sousGrid.add(tab2Combo2, 1, 0, 1, 1);

        mainGrid.add(sousGrid, 0, 0, 1, 1);

        BarChart barChart = Graphs.setupBarChart2();
        mainGrid.add(barChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    /**
     * Tab 3 of the JavaFX frame
     * Gaussian curve of the results for a test
     *
     * @return JavaFX group for tab2
     */
    public static Group tab3() {
        Graphs.getInstance();
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab2Combo1 = new ComboBox();
        ComboBox tab2Combo2 = new ComboBox();
        ComboBox tab2Combo3 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab2Combo1.getItems().add(niveau);
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab2Combo2.getItems().add(matiere);
        }
        for(int epreuve= 1; epreuve < 4; epreuve++) {
            tab2Combo3.getItems().add(epreuve);
        }

        ChangeListener changeListener = null;
        for(LETTRE lettre : LETTRE.values()) {
            changeListener = (observableValue, o, t1) -> Graphs.updateLineChart3((NIVEAU) tab2Combo1.getValue(), (MATIERE) tab2Combo2.getValue(), (int) tab2Combo3.getValue());
        }
            tab2Combo1.getSelectionModel().selectFirst();
            tab2Combo2.getSelectionModel().selectFirst();
            tab2Combo3.getSelectionModel().selectFirst();

            tab2Combo1.valueProperty().addListener(changeListener);
            tab2Combo2.valueProperty().addListener(changeListener);
            tab2Combo3.valueProperty().addListener(changeListener);

            sousGrid.add(tab2Combo1, 0, 0, 1, 1);
            sousGrid.add(tab2Combo2, 1, 0, 1, 1);
            sousGrid.add(tab2Combo3, 2, 0, 1, 1);
            mainGrid.add(sousGrid, 0, 0, 1, 1);
            
        LineChart lineChart = Graphs.setupLineChart3();

        mainGrid.add(lineChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    /**
     * Tab 4 of the JavaFX frame
     * Gaussian curve results of a
     * test per class per topic
     *
     * @return JavaFX group for tab3
     */
    public static Group tab4() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab4Combo1 = new ComboBox();
        ComboBox tab4Combo2 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab4Combo1.getItems().add(niveau);
        }
        for(MATIERE matiere : MATIERE.values()) {
            tab4Combo2.getItems().add(matiere);
        }

        tab4Combo1.getSelectionModel().selectFirst();
        tab4Combo2.getSelectionModel().selectFirst();

        ChangeListener changeListener = (observableValue, o, t1) -> Graphs.updatePieChart4((NIVEAU) tab4Combo1.getValue(), (MATIERE) tab4Combo2.getValue());

        tab4Combo1.valueProperty().addListener(changeListener);
        tab4Combo2.valueProperty().addListener(changeListener);

        sousGrid.add(tab4Combo1, 0, 0, 1, 1);
        sousGrid.add(tab4Combo2, 1, 0, 1, 1);
        mainGrid.add(sousGrid, 0, 0, 1, 1);

        PieChart pieChart = Graphs.setupPieChart4();
        mainGrid.add(pieChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    /**
     * Tab 5 of the JavaFX frame
     * Gaussian curve compare overall
     * average per grade
     *
     * @return JavaFX group for tab4
     */
    public static Group tab5() {
        GridPane mainGrid = new GridPane();
        GridPane sousGrid = new GridPane();

        ComboBox tab5Combo1 = new ComboBox();

        for (NIVEAU niveau : NIVEAU.values()) {
            tab5Combo1.getItems().add(niveau);
        }

        tab5Combo1.getSelectionModel().selectFirst();

        ChangeListener changeListener = (observableValue, o, t1) -> Graphs.updatePieChart5((NIVEAU) tab5Combo1.getValue());

        tab5Combo1.valueProperty().addListener(changeListener);

        sousGrid.add(tab5Combo1, 0, 0, 1, 1);
        mainGrid.add(sousGrid, 0, 0, 1, 1);

        PieChart pieChart = Graphs.setupPieChart5();
        mainGrid.add(pieChart, 0, 1, 1, 1);

        return new Group(mainGrid);
    }

    /**
     * Main function
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
