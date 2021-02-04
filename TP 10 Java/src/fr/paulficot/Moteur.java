package fr.paulficot;

import com.github.javafaker.Faker;

import java.text.DecimalFormat;
import java.util.*;

public class Moteur {

    private static Moteur moteur_instance = null;

    private static final int MAX_ELEVE = 20;

    private List<MATIERE> listeMatiere;
    private List<LETTRE> listeLettre;
    private List<NIVEAU> listeNiveau;
    private List<Classe> listeClasse;

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
        Faker faker = new Faker();
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
                        double note = faker.number().randomDouble(2, 0, 20) * 2;
                        note = Math.round(note);
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

    //public HashMap<MATIERE, Double> moyenne(NIVEAU niveau, LETTRE lettre) {
    public Double moyenne(NIVEAU niveau, LETTRE lettre) {
        //HashMap<MATIERE, Double> hashMap = new HashMap<MATIERE, Double>();
        double tmp = 0.0;
        //Classe
        for (Classe classe : getListeClasse()) {
            //Verifie le niveau et la lettre
            if (niveau != classe.getNiveau() && lettre != classe.getLettre()) {
                continue;
            }
            //Matiere
            for (MATIERE matiere : MATIERE.values()) {
                //Eleve
                for (Eleve eleve : classe.getListEleves()) {
                    //Bulletin
                    for (Bulletin bulletin : eleve.getBulletins()) {
                        if (matiere != bulletin.getMatiere()) {
                            continue;
                        }
                        for (Double note : bulletin.getNotes()) {
                            tmp += tmp;
                        }
                    }
                }
                //hashMap.put(matiere, tmp);
            }
        }
        return tmp;
    }

    public int occurence(Classe classe) {
        int occurence= 1;

        return occurence;
    }

    public void notesClasses(List<Classe> listeClasse) {
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

    public void notesEleves(List<Classe> listeClasse) {
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

    public void notesNiveau(List<Classe> listeClasse) {
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

    public double mediane(List<Double> values) {
        Collections.sort(values);

        if (values.size() % 2 == 1)
            return values.get((values.size() + 1) / 2 - 1);
        else {
            double lower = values.get(values.size() / 2 - 1);
            double upper = values.get(values.size() / 2);

            return (lower + upper) / 2.0;
        }
    }

    public void classesStats(List<Classe> listeClasse) {
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

    public List<MATIERE> getListeMatiere() {
        return listeMatiere;
    }

    public void setListeMatiere(List<MATIERE> listeMatiere) {
        this.listeMatiere = listeMatiere;
    }

    public List<LETTRE> getListeLettre() {
        return listeLettre;
    }

    public void setListeLettre(List<LETTRE> listeLettre) {
        this.listeLettre = listeLettre;
    }

    public List<NIVEAU> getListeNiveau() {
        return listeNiveau;
    }

    public void setListeNiveau(List<NIVEAU> listeNiveau) {
        this.listeNiveau = listeNiveau;
    }

    public List<Classe> getListeClasse() {
        return listeClasse;
    }

    public void setListeClasse(List<Classe> listeClasse) {
        this.listeClasse = listeClasse;
    }

    public void json() {

    }

    public void generer() {
        fillLists();
        createClasses();
        interroSurprise();
        conseilDeClasse();
    }
}
