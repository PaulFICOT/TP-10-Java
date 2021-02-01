package fr.paulficot;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Eleve {

    Faker faker = new Faker();

    private String nom;
    private List<Bulletin> bulletins;
    private final List<MATIERE> options;

    public void genererOptions(){
        switch (faker.number().numberBetween(0, 3)) {
            case 0 -> {
                options.remove(MATIERE.ADVENG);
                options.remove(MATIERE.LAT);
                options.remove(MATIERE.GRE);
            }
            case 1 -> {
                options.add(MATIERE.ADVENG);
                options.remove(MATIERE.LAT);
                options.remove(MATIERE.GRE);
            }
            case 2 -> {
                options.add(MATIERE.ADVENG);
                options.add(MATIERE.LAT);
                options.remove(MATIERE.GRE);
            }
            case 3 -> {
                options.add(MATIERE.ADVENG);
                options.add(MATIERE.GRE);
                options.remove(MATIERE.LAT);
            }
            default -> {
                options.add(null);
                options.remove(MATIERE.ADVENG);
                options.remove(MATIERE.LAT);
                options.remove(MATIERE.GRE);
            }
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
