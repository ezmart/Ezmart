package ezmart.home.controller;

import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.ShoppingList;
import ezmart.model.entity.User;
import ezmart.model.service.ProductService;
import ezmart.model.service.ShoppingListService;
import ezmart.model.util.SystemConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("home/home");
        Object imgProfile = null;
        User user = null;
        
        try {
            //Para tratar o conteúdo do usuário no Home
            Object auxSession = session.getAttribute("userLogged");
            if (auxSession != null) {
                if (auxSession instanceof Consumer) {
                    user = (Consumer) auxSession;

                } else {
                    user = (Establishment) auxSession;
                }

                //Apresentar as listas referentes ao usuário
                ShoppingListService service = new ShoppingListService();
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(UserCriteria.ID_EQ, user.getId());
                List<ShoppingList> shoppingList = service.readByCriteria(criteria, null, null);

                mv.addObject("shoppingList", shoppingList);
            }

            ProductService productService = new ProductService();

            mv.addObject("productList", productService.findAll(Integer.parseInt(SystemConstant.PAGE.SIZE.LIMIT), null));
            //mv.addObject("sectorList", sectorService.findAll(Integer.parseInt(SystemConstant.PAGE.SIZE.LIMIT), null));
            //mv.addObject("providerList", providerService.findAll(Integer.parseInt(SystemConstant.PAGE.SIZE.LIMIT), null));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        mv.addObject("imgProfile", imgProfile);

        return mv;
    }
}
