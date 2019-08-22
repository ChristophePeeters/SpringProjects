package be.vdab.frituurFrida.controllers;

import be.vdab.frituurFrida.domain.Adres;
import be.vdab.frituurFrida.domain.Gemeente;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@RestController
@RequestMapping("/")
class IndexController {
    private String vandaagOpen() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
            case Calendar.THURSDAY:
                return "GESLOTEN";
            default:
                return "OPEN";
        }
    }

    @GetMapping
    public ModelAndView index(@CookieValue(name = "reedsBezocht", required = false)
                                      String reedsBezocht, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("index",
                "vandaagOpen", vandaagOpen());
        modelAndView.addObject("adres",
                new Adres("Laathoflaan", "28", new Gemeente("Ekeren", 2180)));
        Cookie cookie = new Cookie("reedsBezocht", "ja");
        cookie.setMaxAge(31_536_000);
        cookie.setPath("/");
        response.addCookie(cookie);
        if (reedsBezocht != null) {
            modelAndView.addObject("reedsBezocht", true);
        }
        return modelAndView;
    }
}