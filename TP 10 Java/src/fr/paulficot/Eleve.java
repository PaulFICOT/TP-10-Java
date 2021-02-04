package fr.paulficot;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Eleve {

    Faker faker = new Faker(Locale.FRENCH);

    private String nom;
    private List<Bulletin> bulletins;
    private final List<MATIERE> options;

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
