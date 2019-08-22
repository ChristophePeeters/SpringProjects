package be.vdab.frituurFrida.domain;

import java.util.Arrays;
import java.util.stream.Stream;

public class Saus {
    private final long nummer;
    private final String naam;
    private final String[] ingredienten;

    public Saus(long nummer, String naam, String[] ingredienten) {
        this.nummer = nummer;
        this.naam = naam;
        this.ingredienten = ingredienten;
    }

    public long getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getIngredienten() {
        return Arrays.toString(ingredienten).substring(1, Arrays.toString(ingredienten).length()-1);
    }
}
