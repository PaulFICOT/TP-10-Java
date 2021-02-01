package fr.paulficot;

import java.util.*;

/**
 *
 */
public class Classe {

    private NIVEAU niveau;
    private LETTRE lettre;
    private List<Eleve> listEleves;

    /**
     *
     * @param niveau : Niveau de la classe (6e, 5e, etc.)
     * @param lettre : Lettre permettant d'identifier chaque classe d'un même niveau
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
}
