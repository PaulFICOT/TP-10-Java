package fr.paulficot;

public enum NIVEAU {
    SIXIEME("6e"),
    CINQUIEME("5e"),
    QUATRIEME("4e"),
    TROISIEME("3e");

    public final String abreviation;

    NIVEAU(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return this.abreviation;
    }
}
