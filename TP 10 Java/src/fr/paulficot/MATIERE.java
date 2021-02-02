package fr.paulficot;

/**
 *
 */
public enum MATIERE {
    MATHS("MATHEMATIQUES", false, 3, NIVEAU.SIXIEME),
    FRA("FRANCAIS", false, 3, NIVEAU.SIXIEME),
    ENG("ANGLAIS", false, 3, NIVEAU.CINQUIEME),
    HG("HISTOIRE GEOGRAPHIE", false, 3, NIVEAU.SIXIEME),
    PHY("PHYSIQUE", false, 3, NIVEAU.CINQUIEME),
    SVT("SCIENCES NATURELLES", false, 3, NIVEAU.SIXIEME),
    ART("ARTS", false, 3, NIVEAU.SIXIEME),
    MUS("MUSIQUE", false, 2, NIVEAU.SIXIEME),
    EPS("SPORT", false, 2, NIVEAU.SIXIEME),
    LV("LANGUE VIVANTE", false, 3, NIVEAU.CINQUIEME),
    LAT("LATIN", true, 3, NIVEAU.SIXIEME),
    GRE("GREC", true, 3, NIVEAU.SIXIEME),
    ADVENG("ANGLAIS AVANCE", true, 3, NIVEAU.SIXIEME);

    private final String nom;
    private final Boolean isOpt;
    private final int nbrEpreuve;
    private final NIVEAU debut;

    /**
     *
     * @param nom : Nom de la matière
     * @param isOpt : La matière est-elle optionnelle
     * @param debut :
     * @param nbrEpreuve :
     */
    MATIERE(String nom, Boolean isOpt, int nbrEpreuve, NIVEAU debut) {
        this.nom = nom;
        this.isOpt = isOpt;
        this.nbrEpreuve = nbrEpreuve;
        this.debut = debut;
    }

    public Boolean getOpt() {
        return isOpt;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNbrEpreuve() {
        return nbrEpreuve;
    }

    public NIVEAU getDebut() {
        return debut;
    }
}
