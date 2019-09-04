package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.AchternaamForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("klant")
public class KlantController {
    private final KlantService klantService;
    private final ReservatieService reservatieService;
    private final FilmService filmService;

    public KlantController(KlantService klantService, ReservatieService reservatieService, FilmService filmService) {
        this.klantService = klantService;
        this.reservatieService = reservatieService;
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView klanten() {
        return new ModelAndView("klant").addObject(new AchternaamForm(""));
    }

    @GetMapping("zoeken")
    public ModelAndView klantZoeken(AchternaamForm form) {
        return new ModelAndView("klant", "zoeken",
                klantService.findName(form.getNaam()));
    }

    @GetMapping("{id}")
    public ModelAndView klantKeuze(@PathVariable int id, Mandje mandje) {
        ModelAndView modelAndView = new ModelAndView("bevestiging", "klant", klantService.findById(id));
        modelAndView.addObject("totaalAantal", mandje.getSize());
        return modelAndView;
    }

    @GetMapping("{id}/rapport")
    public ModelAndView rapport(@PathVariable int id, Mandje mandje) {
        ModelAndView modelAndView = new ModelAndView("rapport", "rapport", reserveren(id, mandje));
        return modelAndView;
    }

    private int reserveren(int id, Mandje mandje) {
        if (mandje.isNietLeeg()) {
            mandje.getIds().stream().forEach(filmid ->
                    reservatieService.reserveren(new Reservatie(id, filmid, Timestamp.valueOf(LocalDateTime.now()))));
            mandje.clear();
            return 1;
        }
        return 0;
    }
}
