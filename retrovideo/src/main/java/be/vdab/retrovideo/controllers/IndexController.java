package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final GenreService genreService;

    public IndexController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView genres(Mandje mandje) {
        ModelAndView modelAndView = new ModelAndView("index", "genrelijst", genreService.findAll());
        modelAndView.addObject(mandje);
        return modelAndView;
    }
}
