package ezmart.establishment.controller;

import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.User;
import ezmart.model.service.EstablishmentService;
import java.util.ArrayList;
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

                mv = new ModelAndView("establishment/quotation_result");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                List<EstablishmentProduct> establishmentProductList = establishmentService.findAllEstablishmentProduct(establishment.getId());
                if (establishmentProductList != null && !establishmentProductList.isEmpty()) {
                    establishment.setProductList(establishmentProductList);
                }

                Establishment competitor = establishmentService.readByEstablishmentId(competitorId);

                List<Long> productIdList = new ArrayList<>();
                for (EstablishmentProduct product : establishmentProductList) {
                    productIdList.add(product.getId());
                }

                List<EstablishmentProduct> competitorList = establishmentService.findAllEstablishmentProductByCompetitorId(competitorId, productIdList);
                if (competitorList != null && !competitorList.isEmpty()) {
                    competitor.setProductList(competitorList);
                }

                List<Establishment> establishmentList = new ArrayList<>();
                establishmentList.add(establishment);
                establishmentList.add(competitor);

                mv.addObject("establishmentList", establishmentList);
                mv.addObject("establishmentProductList", establishmentProductList);
                mv.addObject("competitorList", competitorList);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
