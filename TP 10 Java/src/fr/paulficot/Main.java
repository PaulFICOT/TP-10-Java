package fr.paulficot;

import com.github.javafaker.Faker;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class Main extends Application {

    static final int MAX_ELEVE = 20;

    /**
     *
     * @param primaryStage : stage
     * @throws Exception : exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.getIcons().add(new Image("jetbrains.png"));
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Setting the stage

        //Group root = new Group(menu(), setBarChart());
        primaryStage.setTitle("TP 10 Java PFICOT");
        //primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

    /**
     *
     * @return : full set menubar
     */
    /*public HBox menu(){
        ComboBox matiereCombo = new ComboBox();
        for(MATIERE matiere : MATIERE.values()) {
            matiereCombo.getItems().add(matiere.getNom());
        }

        ComboBox classeCombo = new ComboBox();
        for(NIVEAU niveau : NIVEAU.values()) {
            classeCombo.getItems().add(niveau.getAbreviation());
        }

        HBox hBox = new HBox(matiereCombo, classeCombo);

        return hBox;
    }*/


    /**
     *
     * @return :
     */
    /*public VBox setBarChart(List<Classe> listeClasse) {

        //Define x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("X Axis");

        //Define y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y Axis");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("XD");

        for()
        dataSeries1.getData().add(new XYChart.Data("Desktop", 10));
        dataSeries1.getData().add(new XYChart.Data("Phone", 15.2));
        dataSeries1.getData().add(new XYChart.Data("Tablet", 13.3));

        barChart.getData().add(dataSeries1);

        VBox vBox = new VBox(barChart);

        return vBox;
    }*/

    /**
     *
     * @param listeClasse : liste de classes
     */
    public static void createClasses(List<Classe> listeClasse) {
        System.out.println("createClasses");
        //Boucle niveau
        for(NIVEAU niveau : NIVEAU.values()) {
            //Boucle lettre
            for(LETTRE lettre : LETTRE.values()) {
                Classe classe = new Classe(niveau, lettre);
                listeClasse.add(classe);
                //Boucle élèves
                for (int i= 0; i < MAX_ELEVE; i++) {
                    Eleve eleve = new Eleve();
                    //Ajout des élèves dans les classes
                    classe.getListEleves().add(eleve);
                }
            }
        }
    }

    /**
     *
     * @param listeClasse : liste de classes
     */
    public static void interroSurprise(List<Classe> listeClasse){
        System.out.println("interroSurprise");
        Faker faker = new Faker();
        //Classe
        for(Classe classe : listeClasse) {
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                //Matiere
                for(MATIERE matiere : MATIERE.values()) {
                    Bulletin bulletin = new Bulletin(matiere);
                    //Nombre d'épreuves
                    for(int i=0; i < matiere.getNbrEpreuve(); i++) {
                        if(matiere.getOpt() && !eleve.getOptions().contains(matiere)) {
                            continue;
                        }
                        double note = faker.number().randomDouble(2, 0, 20);
                        bulletin.addNotes(note);
                    }
                    eleve.addBulletin(bulletin);
                }
            }
        }
    }

    /**
     *
     * @param listeClasse : liste de classes
     */
    public static void conseilDeClasse(List<Classe> listeClasse) {
        System.out.println("conseilDeClasse");
        //Classe
        for(Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                System.out.println(eleve.getNom());
                //Matiere
                for(Bulletin bulletin : eleve.getBulletins()) {
                    System.out.println(bulletin.getMatiere().getNom());
                    for(Double note : bulletin.getNotes()) {
                        System.out.println(note);
                    }
                }
            }
        }
    }

    /**
     *
     * @param listeClasse :
     */
    public static void notesClasse(List<Classe> listeClasse) {
        System.out.println("notes par classe");
        //Classe
        for(Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            //Matiere
            for(MATIERE matiere : MATIERE.values()) {
                System.out.println(matiere.getNom());
                //Eleve
                List<Double> tmp = new ArrayList<>();
                for(Eleve eleve : classe.getListEleves()) {
                    //Bulletin
                    for(Bulletin bulletin : eleve.getBulletins()) {
                        if(matiere != bulletin.getMatiere()) {
                            continue;
                        }
                        for(Double note : bulletin.getNotes()) {
                            System.out.println(note);
                            tmp.add(note);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param listeClasse :
     */
    public static void notesEleve(List<Classe> listeClasse) {
        System.out.println("notes par eleve");
        //Classe
        for(Classe classe : listeClasse) {
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                System.out.println(eleve.getNom());
                //Matiere
                for(Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getNotes().isEmpty()) {
                        continue;
                    }
                    System.out.println(bulletin.getMatiere().getNom());
                    for(Double note : bulletin.getNotes()) {
                        System.out.println(note);
                    }
                }
            }
        }
    }

    /**
     *
     * @param listeClasse :
     */
    public static void notesNiveau(List<Classe> listeClasse) {
        System.out.println("notes par classe");
        for (NIVEAU niveau : NIVEAU.values()) {
            System.out.println(niveau.getAbreviation());
            //Classe
            for(Classe classe : listeClasse) {
                if(niveau != classe.getNiveau()) {
                    continue;
                }
                //Matiere
                for(MATIERE matiere : MATIERE.values()) {
                    System.out.println(matiere.getNom());
                    //Eleve
                    List<Double> tmp = new ArrayList<>();
                    for(Eleve eleve : classe.getListEleves()) {
                        //Bulletin
                        for(Bulletin bulletin : eleve.getBulletins()) {
                            if(matiere != bulletin.getMatiere()) {
                                continue;
                            }
                            for(Double note : bulletin.getNotes()) {
                                System.out.println(note);
                                tmp.add(note);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param values :
     * @return :
     */
    public static double mediane(List<Double> values) {
        Collections.sort(values);

        if (values.size() % 2 == 1)
            return values.get((values.size() + 1) / 2 - 1);
        else {
            double lower = values.get(values.size() / 2 - 1);
            double upper = values.get(values.size() / 2);

            return (lower + upper) / 2.0;
        }
    }

    public static void classesStats(List<Classe> listeClasse) {
        System.out.println("classesStats");
        Double min;
        Double max;
        Double avg= 0.0;
        Double med;
        DecimalFormat df = new DecimalFormat("0.00");
        //Classe
        for(Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            HashMap<MATIERE, List<Double>> stats = new HashMap<>();
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                //Matiere
                for(Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getNotes().isEmpty()) {
                        continue;
                    }
                    List<Double> dblList = new ArrayList<>();
                    for(Double note : bulletin.getNotes()) {
                        avg += note;
                        dblList.add(note);
                    }
                    stats.put(bulletin.getMatiere(), dblList);

                    avg /= bulletin.getMatiere().getNbrEpreuve();
                }
            }
            //System.out.println("Maximum: " + Collections.max(stats.values()));
            //System.out.println("Minimum: " + Collections.min(dblList));
            //System.out.println("Moyenne: " + df.format(avg));
            //System.out.println("Mediane: " + df.format(mediane(dblList)));
        }
    }

    /**
     *
     * @param args : main args
     */
    public static void main(String[] args) {
        //JavaFX
        //launch(args);

        //Liste de classes
        List<Classe> listeClasse = new  ArrayList<>();

        createClasses(listeClasse);
        interroSurprise(listeClasse);
        //conseilDeClasse(listeClasse);

        //notesClasse(listeClasse);
        //notesEleve(listeClasse);
        notesNiveau(listeClasse);
        //moyenneParNiveau(NIVEAU.CINQUIEME, listeClasse);
        //classesStats(listeClasse); //TODO à fixer
        System.out.println("End");
    }
}
