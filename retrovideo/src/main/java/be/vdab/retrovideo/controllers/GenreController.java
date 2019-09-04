package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("genre")
public class GenreController {
    private final GenreService genreService;
    private final FilmService filmService;

    public GenreController(GenreService genreService, FilmService filmService) {
        this.genreService = genreService;
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView genreMetId(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("genre", "films", filmService.findAll());
        genreService.findById(id).ifPresent(genre -> {
            modelAndView.addObject("huidigGenre", genre);
        });
        return modelAndView;
    }
}
