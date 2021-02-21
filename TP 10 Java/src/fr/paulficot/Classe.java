package fr.paulficot;

import java.util.*;

/**
 * A classe is a list
 * of students, a grade
 * and a letter
 *
 * @author Paul FICOT
 * @version 1.0
 */
public class Classe {

    private NIVEAU niveau;
    private LETTRE lettre;
    private List<Eleve> listEleves;

    /**
     * @param niveau : Grade of the schoolclass (6e, 5e, etc.)
     * @param lettre : Allow you to identity a schoolclass
     */
    public Classe(NIVEAU niveau, LETTRE lettre) {
        this.niveau = niveau;
        this.lettre = lettre;
        this.listEleves = new ArrayList<>();
    }

    public NIVEAU getNiveau() {
        return niveau;
    }

    public void setNiveau(NIVEAU niveau) {
        this.niveau = niveau;
    }

    public LETTRE getLettre() {
        return lettre;
    }

    public void setLettre(LETTRE lettre) {
        this.lettre = lettre;
    }

    public List<Eleve> getListEleves() {
        return listEleves;
    }

    public void setListEleves(List<Eleve> listEleves) {
        this.listEleves = listEleves;
    }

    @Override
    public String toString() {
        return niveau.getAbreviation() + lettre;
    }
}
