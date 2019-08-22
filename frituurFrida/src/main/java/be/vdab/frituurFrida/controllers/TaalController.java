package be.vdab.frituurFrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("taal")
class TaalController {
    private static final String[] Nederlands = {"nl"};
    @GetMapping
    public ModelAndView taal(@RequestHeader("Accept-Language") String acceptLanguage) {
        ModelAndView modelAndView = new ModelAndView("taal");
        Arrays.stream(Nederlands).filter(taal -> acceptLanguage.contains(taal))
                .findFirst().ifPresent(taal -> modelAndView.addObject("taal", taal));
        return modelAndView;
    }
}
