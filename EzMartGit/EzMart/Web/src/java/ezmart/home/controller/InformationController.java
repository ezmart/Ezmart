package ezmart.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformationController {
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView mv = new ModelAndView("templates/information");

        return mv;
    }
}
