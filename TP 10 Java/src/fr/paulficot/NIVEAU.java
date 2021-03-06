package fr.paulficot;

/**
 * Enum that contains every grades
 */
public enum NIVEAU {
    SIXIEME("6e"),
    CINQUIEME("5e"),
    QUATRIEME("4e"),
    TROISIEME("3e");

    public final String abreviation;

    @Override
    public String toString() {
        return this.getAbreviation();
    }

    NIVEAU(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return this.abreviation;
    }
}
