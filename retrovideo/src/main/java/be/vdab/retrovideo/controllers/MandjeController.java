package be.vdab.retrovideo.controllers;


import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final FilmService filmService;
    private BigDecimal totaalPrijs;

    public MandjeController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView mandjeTonen(Mandje mandje) {
        List<Film> films = filmService.findAll();
        ModelAndView modelAndView = new ModelAndView("mandje").addObject("films", films);
        if (mandje.isNietLeeg()) {
            modelAndView.addObject("gekozenFilms", films.stream().filter(
                    film -> mandje.bevat(film.getId())).collect(Collectors.toList()));
        }
        totaalPrijs = films.stream().filter(film -> mandje.bevat(film.getId())).map(Film::getPrijs).reduce(BigDecimal.ZERO, BigDecimal::add);
        modelAndView.addObject("totaalPrijs", totaalPrijs);
        return modelAndView;
    }

    @PostMapping("verwijderen")
    public String verwijderen(Mandje mandje) {
        mandje.verwijderen(1);
        return "redirect:/mandje";
    }

    public @PostMapping
    String toevoegen(int id, Mandje mandje) {
        mandje.toevoegen(id);
        return "redirect:/mandje";
    }

}
