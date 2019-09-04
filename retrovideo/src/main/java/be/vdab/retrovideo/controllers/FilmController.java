package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView klanten() {
        return new ModelAndView("film");
    }

    @GetMapping("{id}")
    public ModelAndView filmMetId(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("film", "huidigeFilm", filmService.findById(id));
        return modelAndView;
    }
}


