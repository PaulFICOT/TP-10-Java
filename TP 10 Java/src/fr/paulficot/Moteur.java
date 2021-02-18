package fr.paulficot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Contains every methods to create the schoolclasses
 * students and create the tests
 * Also process data for charts
 *
 * @author Paul FICOT
 * @version 1.0
 */
public class Moteur {

    private static Moteur moteur_instance = null;

    private static final int MAX_ELEVE = 20;

    private static List<MATIERE> listeMatiere;
    private static List<LETTRE> listeLettre;
    private static List<NIVEAU> listeNiveau;
    private static List<Classe> listeClasse;

    public Moteur() {
        this.listeMatiere = new ArrayList<>();
        this.listeLettre = new ArrayList<>();
        this.listeNiveau = new ArrayList<>();
        this.listeClasse = new ArrayList<>();
    }

    public static Moteur getInstance() {
        if (moteur_instance == null)
            moteur_instance = new Moteur();

        return moteur_instance;
    }

    public void fillLists() {
        listeMatiere.addAll(Arrays.asList(MATIERE.values()));
        listeLettre.addAll(Arrays.asList(LETTRE.values()));
        listeNiveau.addAll(Arrays.asList(NIVEAU.values()));
    }

    public void createClasses() {
        System.out.println("createClasses");
        //Boucle niveau
        for (NIVEAU niveau : NIVEAU.values()) {
            //Boucle lettre
            for (LETTRE lettre : LETTRE.values()) {
                Classe classe = new Classe(niveau, lettre);
                listeClasse.add(classe);
                //Boucle élèves
                for (int i = 0; i < MAX_ELEVE; i++) {
                    Eleve eleve = new Eleve();
                    //Ajout des élèves dans les classes
                    classe.getListEleves().add(eleve);
                }
            }
        }
    }

    public void interroSurprise() {
        System.out.println("interroSurprise");
        Random random = new Random();
        //Classe
        for (Classe classe : listeClasse) {
            //Eleve
            for (Eleve eleve : classe.getListEleves()) {
                //Matiere
                for (MATIERE matiere : MATIERE.values()) {
                    if (matiere.getOpt() && !eleve.getOptions().contains(matiere)) {
                        continue;
                    }
                    Bulletin bulletin = new Bulletin(matiere);
                    //Nombre d'épreuves
                    for (int i = 0; i < matiere.getNbrEpreuve(); i++) {
                        double note = random.nextGaussian()*3+12;
                        note = Math.round(note*2);
                        note /= 2;
                        bulletin.addNotes(note);
                    }
                    eleve.addBulletin(bulletin);
                }
            }
        }
    }

    public void conseilDeClasse() {
        System.out.println("conseilDeClasse");
        //Classe
        for (Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
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

    public static void notesClasses(List<Classe> listeClasse) {
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

    public static void notesEleves(List<Classe> listeClasse) {
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

    public static List<Double> getNotesEpreuves(NIVEAU niveau, LETTRE lettre, MATIERE matiere, int epreuve) {
        List<Double> listNotes = new ArrayList<>();

        for (Classe classe : getListeClasse()) {
            if(classe.getNiveau() != niveau && classe.getLettre() != lettre) {
                continue;
            }
            for(Eleve eleve : classe.getListEleves()) {
                for(Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getMatiere() != matiere) {
                        continue;
                    }
                    for(int i=0; i<=bulletin.getNotes().size();i++) {

                    }
                }
            }
        }

        return listNotes;
    }

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

    public static List<String> classesStats(List<Classe> listeClasse) {
        System.out.println("classesStats");
        Double min;
        Double max;
        Double avg= 0.0;
        Double med;
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> listStats = new ArrayList();
        //Classe
        for(Classe classe : listeClasse) {
            System.out.println(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            listStats.add(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            HashMap<MATIERE, List<Double>> stats = new HashMap<>();
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                listStats.add(eleve.getNom());
                //Matiere
                for(Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getNotes().isEmpty()) {
                        continue;
                    }
                    System.out.println(bulletin.getMatiere().getNom());
                    listStats.add(bulletin.getMatiere().getNom());
                    List<Double> dblList = new ArrayList<>();
                    for(Double note : bulletin.getNotes()) {
                        avg += note;
                        dblList.add(note);
                    }
                    stats.put(bulletin.getMatiere(), dblList);

                    avg /= bulletin.getMatiere().getNbrEpreuve();

                    listStats.add("Maximum: " + (Collections.max(dblList)));
                    listStats.add("Minumum: " + Collections.min(dblList));
                    listStats.add("Moyenne :" + df.format(avg));
                    listStats.add("Mediane :" + df.format(mediane(dblList)));
                }
                listStats.add("------------------------------------------------");
            }

        }

        return listStats;
    }

    public static void variance() {

    }

    public static Double tab1Moyenne(NIVEAU niveau, LETTRE lettre, MATIERE matiere) {
        double tmp = 0.0;
        int cpt = 0;
        //Classe
        for (Classe classe : getListeClasse()) {
            //Verifie le niveau et la lettre
            if (niveau != classe.getNiveau() || lettre != classe.getLettre()) {
                continue;
            }
            //Eleve
            for (Eleve eleve : classe.getListEleves()) {
                //Bulletin
                for (Bulletin bulletin : eleve.getBulletins()) {
                    if (matiere != bulletin.getMatiere()) {
                        continue;
                    }
                    for (Double note : bulletin.getNotes()) {
                        tmp = tmp+note;
                        cpt++;
                    }
                }
            }
        }
        return tmp/cpt;
    }

    public static Double tab2Gaussian(NIVEAU niveau, MATIERE matiere, int epreuve) {

        return null;
    }

    public static List<MATIERE> getListeMatiere() {
        return listeMatiere;
    }

    public static void setListeMatiere(List<MATIERE> listeMatiere) {
        Moteur.listeMatiere = listeMatiere;
    }

    public static List<LETTRE> getListeLettre() {
        return listeLettre;
    }

    public static void setListeLettre(List<LETTRE> listeLettre) {
        Moteur.listeLettre = listeLettre;
    }

    public static List<NIVEAU> getListeNiveau() {
        return listeNiveau;
    }

    public static void setListeNiveau(List<NIVEAU> listeNiveau) {
        Moteur.listeNiveau = listeNiveau;
    }

    public static List<Classe> getListeClasse() {
        return listeClasse;
    }

    public static void setListeClasse(List<Classe> listeClasse) {
        Moteur.listeClasse = listeClasse;
    }

    public void json() {
        System.out.println("JSON");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter filewriter = new FileWriter("data.json");
            gson.toJson(listeClasse, filewriter);
            filewriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void generer() {
        fillLists();
        createClasses();
        interroSurprise();
        //conseilDeClasse();
        json();
    }
}
