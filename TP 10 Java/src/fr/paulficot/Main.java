package fr.paulficot;

import com.github.javafaker.Faker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class Main extends Application {

    static final int MAX_ELEVE = 20;

    /**
     *
     * @param primaryStage :
     * @throws Exception :
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

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
     * @param eleve : eleve
     */
    public static void moyenneParEleve(Eleve eleve) {
        for(Bulletin bulletin : eleve.getBulletins()) {
            System.out.println(bulletin.getMatiere().getNom());
            for(Double note : bulletin.getNotes()) {
                System.out.println(note);
            }
        }
    }

    /**
     *
     * @param classe : classe
     */
    public static void moyenneParClasse(Classe classe) {
        for (Eleve eleve : classe.getListEleves()) {
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

    /**
     *
     * @param niveau : niveau choisi
     * @param listeClasse :liste de classe
     */
    public static void moyenneParNiveau(NIVEAU niveau, List<Classe> listeClasse) {
        //Classe
        for(Classe classe : listeClasse) {
            //Si niveau = getNiveau()
            if (niveau == classe.getNiveau()) {
                System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre());
                //Eleve
                for (Eleve eleve : classe.getListEleves()) {
                    System.out.println(eleve.getNom());
                    //Matiere
                    for (Bulletin bulletin : eleve.getBulletins()) {
                        System.out.println(bulletin.getMatiere().getNom());
                        for (Double note : bulletin.getNotes()) {
                            System.out.println(note);
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
    public static double Mediane(List<Double> values) {
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
        Double med= 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        //Classe
        for(Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
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
                        avg += note;
                    }


                    min = Collections.min(bulletin.getNotes());
                    max = Collections.max(bulletin.getNotes());
                    avg /= Math.round(bulletin.getMatiere().getNbrEpreuve());
                    med = Mediane(bulletin.getNotes());

                    System.out.println("Maximum: " + max);
                    System.out.println("Minimum: " + min);
                    System.out.println("Moyenne: " + df.format(avg));
                    System.out.println("Mediane: " + med);
                }
            }

        }
    }


    /**
     *
     * @param args : main args
     */
    public static void main(String[] args) {
        //JavaFX
        launch(args);

        //Liste de classes
        List<Classe> listeClasse = new  ArrayList<>();

        createClasses(listeClasse);
        interroSurprise(listeClasse);
        conseilDeClasse(listeClasse);
        //moyenneParEleve(eleve);
        //moyenneParClasse(classe);
        //moyenneParNiveau(NIVEAU.CINQUIEME, listeClasse);
        //classesStats(listeClasse); //TODO à fixer
        System.out.println("End");
    }
}
