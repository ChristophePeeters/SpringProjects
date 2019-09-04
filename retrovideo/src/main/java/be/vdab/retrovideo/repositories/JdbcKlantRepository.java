package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcKlantRepository implements KlantRepository {
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantMapper =
            (result, rowNum) ->
                    new Klant(result.getInt("id"),
                            result.getString("familienaam"),
                            result.getString("voornaam"),
                            result.getString("straatNummer"),
                            result.getString("postcode"),
                            result.getString("gemeente"));

    JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Klant> findAll() {
        String sql = "select * from klanten order by id";
        return template.query(sql, klantMapper);
    }

    @Override
    public List<Klant> findName(String naam) {
        String sql = "select * from klanten where familienaam like ? order by familienaam";
        return template.query(sql, klantMapper, '%' + naam + '%');
    }

    @Override
    public Klant findById(int id) {
        String sql = "select * from klanten where id = ?";
        return template.queryForObject(sql, klantMapper, id);
    }
}
