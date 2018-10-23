package ezmart.establishment.controller;

import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import ezmart.model.service.EstablishmentService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuotationController {

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public ModelAndView findAllEstablishmentForQuotation(HttpSession session) {
        ModelAndView mv = null;
        try {
            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/quotation");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                List<Establishment> establishmentList = establishmentService.findAllEstablishmentForQuotation(establishment.getId());

                mv.addObject("establishment", establishment);
                mv.addObject("establishmentList", establishmentList);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/quotation", method = RequestMethod.POST)
    public ModelAndView findAllProductByCompetitor(Long competitorId, HttpSession session) {
        ModelAndView mv = null;
        try {
            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/quotation");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                //TODO
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
