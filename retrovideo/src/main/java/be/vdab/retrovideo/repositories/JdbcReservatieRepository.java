package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.services.FilmService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final FilmService filmService;
    private final RowMapper<Reservatie> reservatieMapper =
            (result, rowNum) ->
                    new Reservatie(result.getInt("klantid"),
                            result.getInt("filmid"),
                            result.getTimestamp("reservatie"));

    JdbcReservatieRepository(JdbcTemplate template, FilmService filmService) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        this.filmService = filmService;
        insert.withTableName("reservaties");
    }

    @Override
    public int reserveren(Reservatie r) {
        if (filmService.findById(r.getFilmid()).getBeschikbaar() > 0) {
            Map<String, Object> kolomWaarden = new HashMap<>();
            kolomWaarden.put("klantid", r.getKlantid());
            kolomWaarden.put("filmid", r.getFilmid());
            kolomWaarden.put("reservatie", r.getReservatie());
            insert.execute(kolomWaarden);
            int gereserveerd = filmService.findById(r.getFilmid()).getGereserveerd();
            String sql = "update retrovideo.films set gereserveerd = ? where id = ?";
            System.out.println("Reservatie gelukt");
            return template.update(sql, ++gereserveerd, r.getFilmid());
        }
        System.out.println("Reservatie Mislukt");
        return 0;
    }

    @Override
    public List<Reservatie> findAll() {
        String sql = "select * from reservaties order by klantid";
        return template.query(sql, reservatieMapper);
    }
}
