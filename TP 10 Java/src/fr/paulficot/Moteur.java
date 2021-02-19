package fr.paulficot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.math3.distribution.NormalDistribution;

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

    /**
     * Moteur constructor
     */
    public Moteur() {
        this.listeMatiere = new ArrayList<>();
        this.listeLettre = new ArrayList<>();
        this.listeNiveau = new ArrayList<>();
        this.listeClasse = new ArrayList<>();
    }

    /**
     * Moteur singleton
     * Instantiate a new moteur and return it
     * if instance exists return it
     *
     * @return moteur instance
     */
    public static Moteur getInstance() {
        if (moteur_instance == null)
            moteur_instance = new Moteur();

        return moteur_instance;
    }

    /**
     * Fullfill all the lists with enums content
     */
    public void fillLists() {
        listeMatiere.addAll(Arrays.asList(MATIERE.values()));
        listeLettre.addAll(Arrays.asList(LETTRE.values()));
        listeNiveau.addAll(Arrays.asList(NIVEAU.values()));
    }

    /**
     * Create every schoolclasses and their
     * structures, and create all the students
     */
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

    /**
     * Put marks into students
     * bulletins for each test
     */
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

    /**
     * Display every marks of every student
     */
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

    /**
     * Display marks per schoolclass
     *
     * @param listeClasse list of every schoolclasses
     */
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

    /**
     * Display marks per student
     *
     * @param listeClasse list of every schoolclasses
     */
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

    /**
     * Display marks per grade
     *
     * @param listeClasse list of every schoolclasses
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
     * Return a list of every marks per test
     *
     * @param niveau grade of the class who took the test
     * @param lettre lettre of the class who took the test
     * @param matiere topic of the test
     * @param epreuve number of the test
     *
     * @return Marks that match arguments
     */
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

    /**
     * Return the median for a list of double
     *
     * @param values list of doubles
     * @return median for values
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

    /**
     * List all marks per student
     *
     * @param listeClasse list of every schoolclasses
     * @return a list of every marks per student
     */
    public static List<String> listeNotes0(List<Classe> listeClasse) {
        List<String> listNotes = new ArrayList();

        //Classe
        for (Classe classe : listeClasse) {
            listNotes.add(classe.getNiveau().getAbreviation() + " " + classe.getLettre().toString());
            //Eleve
            for (Eleve eleve : classe.getListEleves()) {
                listNotes.add(eleve.getNom());
                //Matiere
                for (Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getNotes().isEmpty()) {
                        continue;
                    }
                    listNotes.add(bulletin.getMatiere().getNom());
                    for (Double note : bulletin.getNotes()) {
                        listNotes.add(note.toString());
                    }
                }
            }
            listNotes.add("---------------------------------------------------");
        }
        return listNotes;
    }

    /**
     * Calculate statistics related to each student
     *
     * @param listeClasse list of every schoolclasses
     * @return a list of every stats per students
     */
    public static List<String> classesStats1(List<Classe> listeClasse) {
        System.out.println("classesStats");
        Double min;
        Double max;
        Double avg= 0.0;
        Double med;
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> listStats = new ArrayList();
        //Classe
        for(Classe classe : listeClasse) {
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

    /**
     * Return an average of marks per topic per schoolclass
     *
     * @param niveau grade of the class who took the test
     * @param lettre lettre of the class who took the test
     * @param matiere topic of the test
     * @return Average per schoolclass
     */
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

    /**
     * Return arguments for a normalized law
     *
     * @param niveau grade of the class who took the test
     * @param lettre lettre of the class who took the test
     * @param matiere topic of the test
     * @param epreuve number of the test
     *
     * @return average mark and average deviation
     */
    public static Double tab2Gaussian(NIVEAU niveau, LETTRE lettre, MATIERE matiere, int epreuve) {
        double tmp = 0.0;
        int cpt = 0;
        int cpt2= 0;
        double avg;
        double diffAvg= 0.0;
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
        avg = tmp/cpt;

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
                        diffAvg += note - avg;
                        cpt2++;
                    }
                }
            }
        }

        return normalizedLaw(avg, diffAvg);
    }

    /**
     * Average grade per level
     * per topic
     *
     * @param niveau Grade
     * @param matiere Tests topic
     * @return average grade for a niveau level
     * and a select topic
     */
    public static HashMap<String, Integer> tab4AvgPerTopicPerGrade(NIVEAU niveau, MATIERE matiere) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Très bien", 0);
        hashMap.put("Bien", 0);
        hashMap.put("Assez bien", 0);
        hashMap.put("Passable", 0);
        hashMap.put("Insuffisant", 0);

        //Classe
        for(Classe classe : listeClasse) {
            if(niveau != classe.getNiveau()) {
                continue;
            }
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                int cpt = 0;
                double avg = 0.0;
                //Bulletin
                for(Bulletin bulletin : eleve.getBulletins()) {
                    if(bulletin.getMatiere() != matiere) {
                        continue;
                    }
                    //Test result
                    for(Double note : bulletin.getNotes()) {
                        cpt++;
                        avg = avg+note;
                    }
                }
                if(avg/cpt > 16) {
                    hashMap.put("Très bien", hashMap.get("Très bien") + 1);
                } else if(avg/cpt > 14) {
                    hashMap.put("Bien", hashMap.get("Bien") + 1);
                } else if(avg/cpt > 12) {
                    hashMap.put("Assez bien", hashMap.get("Assez bien") + 1);
                } else if(avg/cpt > 10) {
                    hashMap.put("Passable", hashMap.get("Passable") + 1);
                } else {
                    hashMap.put("Insuffisant", hashMap.get("Insuffisant") + 1);
                }
            }
        }
        return hashMap;
    }

    /**
     * Average grade per level
     *
     * @param niveau Grade
     * @return average grade for niveau level
     */
    public static HashMap<String, Integer> tab5AvgPerGrade(NIVEAU niveau) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Très bien", 0);
        hashMap.put("Bien", 0);
        hashMap.put("Assez bien", 0);
        hashMap.put("Passable", 0);
        hashMap.put("Insuffisant", 0);

        //Classe
        for(Classe classe : listeClasse) {
            if(niveau != classe.getNiveau()) {
                continue;
            }
            //Eleve
            for(Eleve eleve : classe.getListEleves()) {
                int cpt = 0;
                double avg = 0.0;
                //Bulletin
                for(Bulletin bulletin : eleve.getBulletins()) {
                    //Test result
                    for(Double note : bulletin.getNotes()) {
                        cpt++;
                        avg = avg+note;
                    }
                }
                if(avg/cpt > 16) {
                    hashMap.put("Très bien", hashMap.get("Très bien") + 1);
                } else if(avg/cpt > 14) {
                    hashMap.put("Bien", hashMap.get("Bien") + 1);
                } else if(avg/cpt > 12) {
                    hashMap.put("Assez bien", hashMap.get("Assez bien") + 1);
                } else if(avg/cpt > 10) {
                    hashMap.put("Passable", hashMap.get("Passable") + 1);
                } else {
                    hashMap.put("Insuffisant", hashMap.get("Insuffisant") + 1);
                }
            }
        }
        return hashMap;
    }

    /**
     * Calculate normalized Law
     *
     * @param avg average of an arraylist
     * @param diffAvg average deviation
     * @return normalized value for x
     */
    public static double normalizedLaw(double avg, double diffAvg) {
        NormalDistribution normalDistribution = new NormalDistribution(avg, diffAvg);

        return normalDistribution.density(avg);
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

    /**
     * Generate a JSON file that
     * contains every students and his marks
     */
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

    /**
     * Run every method to
     * setup the project
     */
    public void generer() {
        fillLists();
        createClasses();
        interroSurprise();
        json();
    }
}
