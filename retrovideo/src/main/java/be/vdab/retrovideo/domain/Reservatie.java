package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class Reservatie {
    @NotNull
    private final int klantid;
    @NotNull
    private final int filmid;
    @NotNull
    @DateTimeFormat (style = "S-")
    private final Timestamp reservatie;



    public Reservatie(int klantid, int filmid, Timestamp reservatie) {
        this.klantid = klantid;
        this.filmid = filmid;
        this.reservatie = reservatie;
    }

    public int getKlantid() {
        return klantid;
    }

    public int getFilmid() {
        return filmid;
    }

    public Timestamp getReservatie() {
        return reservatie;
    }
}
