package be.vdab.retrovideo.domain;

public class Genre {
    private final int id;
    private final String naam;

    public Genre(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
