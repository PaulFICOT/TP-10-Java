package fr.paulficot;

import java.util.ArrayList;
import java.util.List;

public class Bulletin {
    private MATIERE matiere;
    private List<Double> notes;

    public Bulletin(MATIERE matiere) {
        this.matiere = matiere;
        this.notes = new ArrayList<>();
    }

    public MATIERE getMatiere() {
        return matiere;
    }

    public void setMatiere(MATIERE matiere) {
        this.matiere = matiere;
    }

    public List<Double> getNotes() {
        return notes;
    }

    public void setNotes(List<Double> notes) {
        this.notes = notes;
    }

    public void addNotes(double note) {
        if(note > 20) {
            note = 20;
        }
        if(note < 0) {
            note = 0;
        }
        this.notes.add(note);
    }
}
