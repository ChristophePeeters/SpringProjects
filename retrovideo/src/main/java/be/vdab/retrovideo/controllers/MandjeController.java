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
    private final Mandje mandje;

    public MandjeController(FilmService filmService, Mandje mandje) {
        this.filmService = filmService;
        this.mandje = mandje;
    }

    @GetMapping
    public ModelAndView mandjeTonen() {
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
    public String verwijderen(Integer mijnId) {
        if (mijnId != null) {
            mandje.verwijderen(mijnId);
        }
        return "redirect:/mandje";
    }

    public @PostMapping
    String toevoegen(int id) {
        mandje.toevoegen(id);
        return "redirect:/mandje";
    }

}
