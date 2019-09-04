package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFilmRepository implements FilmRepository {
    private final JdbcTemplate template;
    private final RowMapper<Film> filmMapper =
            (result, rowNum) ->
                    new Film(result.getInt("id"),
                            result.getInt("genreid"),
                            result.getString("titel"),
                            result.getInt("voorraad"),
                            result.getInt("gereserveerd"),
                            result.getBigDecimal("prijs")
                    );

    JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Film> findAll() {
        String sql = "select * from films order by id";
        return template.query(sql, filmMapper);
    }

    @Override
    public Optional<Film> findByGenreId(int id) {
        try {
            String sql = "select * from films where genreid=?";
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    @Override
    public Film findById(int id) {
        try {
            String sql = "select * from films where id=?";
            return template.queryForObject(sql, filmMapper, id);

//            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
}
