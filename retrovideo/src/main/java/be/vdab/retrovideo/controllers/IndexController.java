package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.GenreService;
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
    public ModelAndView genres() {
        return new ModelAndView("index", "genrelijst", genreService.findAll());
    }
}