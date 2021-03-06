package ezmart.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView mv = new ModelAndView("register/register");

        return mv;
    }

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public ModelAndView getTerms() {
        ModelAndView mv = new ModelAndView("templates/terms_of_use");

        return mv;
    }
}
