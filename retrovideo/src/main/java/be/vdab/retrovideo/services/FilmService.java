package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> findAll();
    Optional<Film> findByGenreId(int id);
    Film findById(int id);
}
