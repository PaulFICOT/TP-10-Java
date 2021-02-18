package fr.paulficot;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Eleve class that contains his name
 * his marks and the optional topics he took
 */
public class Eleve {

    private transient Faker faker = new Faker(Locale.FRENCH);

    private String nom;
    private List<Bulletin> bulletins;
    private final List<MATIERE> options;

    /**
     * Randomize the options the ELEVE will be doing
     */
    public void genererOptions(){
        switch (faker.number().numberBetween(0, 5)) {
            case 1 -> options.add(MATIERE.ADVENG);
            case 2 -> options.add(MATIERE.LAT);
            case 3 -> options.add(MATIERE.GRE);
            case 4 -> {
                options.add(MATIERE.ADVENG);
                options.add(MATIERE.LAT);
            }
            case 5 -> {
                options.add(MATIERE.ADVENG);
                options.add(MATIERE.GRE);
            }
            default -> options.add(null);
        }
    }

    /**
     * ELEVE constructor
     * Generate a name with faker
     * create arraylist for marks
     * and create arraylist for optional
     * topics and fill it
     */
    public Eleve() {
        this.nom = faker.name().fullName();
        this.bulletins = new ArrayList<>();
        this.options = new ArrayList<>();
        genererOptions();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    public void addBulletin(Bulletin bulletin) {
        this.bulletins.add(bulletin);
    }

    public List<MATIERE> getOptions() {
        return options;
    }

}
