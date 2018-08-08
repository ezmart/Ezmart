package ezmart.authentication.controller;

import ezmart.model.entity.Consumer;
import ezmart.model.entity.User;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.UserService;
import ezmart.model.util.SystemConstant;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("authentication/login");

        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String email, String password, HttpSession session) throws Exception {
        ModelAndView mv = null;
        try {
            Map<String, Object> form = new HashMap<>();
            form.put("validationType", SystemConstant.VALIDATION.USER.AUTHENTICATION);
            form.put("email", email);
            form.put("password", password);

            UserService service = new UserService();
            Map<String, String> errors = service.validate(form);

            if (errors.isEmpty()) {
                User user = service.authenticate(email, password);
                if (user != null) {
                    if (user.isActive().equals(true)) {
                        if (user instanceof Consumer) {
                            ConsumerService consumerService = new ConsumerService();
                            consumerService.readById(user);

                        } else {
                            EstablishmentService emporiumService = new EstablishmentService();
                            emporiumService.readById(user);
                        }
                        session.setAttribute("userLogged", user);
                        mv = new ModelAndView("redirect:/home");
                    } else {
                        errors.put("information", "Seu cadastro ainda não confirmado. Acesse seu email e confirme.");
                        mv = new ModelAndView("authentication/login");
                    }
                    mv.addObject("errors", errors);
                } else {
                    errors.put("information", "E-mail e/ou Senha incorreto(s).Tente outra vez.");
                    mv = new ModelAndView("authentication/login");
                    mv.addObject("errors", errors);
                    mv.addObject("login", form);
                }
            } else {
                mv = new ModelAndView("authentication/login");
                mv.addObject("errors", errors);
                mv.addObject("login", form);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/{email}/authentication", method = RequestMethod.GET)
    public ModelAndView authenticationRegister(@PathVariable String email) {
        ModelAndView mv = new ModelAndView("redirect:/");

        try {
            
            UserService service = new UserService();
            service.activeProfile(email);
            
        } catch (Exception exception) {
            System.out.println(exception);
        }
        
        return mv;
    }
}
