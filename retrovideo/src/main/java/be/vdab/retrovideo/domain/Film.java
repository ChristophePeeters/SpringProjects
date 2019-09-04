package be.vdab.retrovideo.domain;

import java.math.BigDecimal;

public class Film {
    private final int id;
    private final int gendreid;
    private final String titel;
    private final int voorraad;
    private final int gereserveerd;
    private final BigDecimal prijs;

    public Film(int id, int gendreid, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.gendreid = gendreid;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    int aantalBeschikbaar() {
        return voorraad - gereserveerd;
    }

    public int getId() {
        return id;
    }

    public int getGendreid() {
        return gendreid;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getBeschikbaar() {
        return voorraad - gereserveerd;
    }
}
