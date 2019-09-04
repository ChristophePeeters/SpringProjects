package be.vdab.retrovideo.services;

import be.vdab.retrovideo.controllers.MandjeController;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultFilmService implements FilmService {
    private final FilmRepository filmRepository;

    DefaultFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> findByGenreId(int id) {
        return filmRepository.findByGenreId(id);
    }

    @Override
    public Film findById(int id) {return filmRepository.findById(id);}

}
