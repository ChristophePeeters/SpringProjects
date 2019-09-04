package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    List<Film> findAll();
    Optional<Film> findByGenreId(int id);
    Film findById(int id);
}
