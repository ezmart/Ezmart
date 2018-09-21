package ezmart.home.controller;

import ezmart.model.service.ProductService;
import ezmart.model.util.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView mv = new ModelAndView("home/home");
        Object imgProfile = null;
        try {
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
