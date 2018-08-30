package ezmart.establishment.controller;

import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.Product;
import ezmart.model.entity.State;
import ezmart.model.entity.User;
import ezmart.model.service.CityService;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.StateService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstablishmentController {

    @RequestMapping(value = "/editProfileEstablishment", method = RequestMethod.GET)
    public ModelAndView getMyAddress(HttpSession session) {
        ModelAndView mv = null;
        try {
            String profileValue = "page_editProfile";
            Map<String, String> profile = new HashMap<>();
            profile.put("profileValue", profileValue);

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("profile/my_establishment_profile");

                CityService cityService = new CityService();
                List<City> cityList = cityService.readByCriteria(null, null, null);
                mv.addObject("cityList", cityList);

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                mv.addObject("establishment", establishment);
                mv.addObject("cityIdEstablishment", establishment.getCity());

                StateService stateService = new StateService();
                List<State> stateList = stateService.readByCriteria(null, null, null);
                mv.addObject("stateList", stateList);

                City city = cityService.readById(user.getCity());
                mv.addObject("stateIdEstablishment", city.getState().getId());
            }

            mv.addObject("profile", profile);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/editProfileEstablishment", method = RequestMethod.POST)
    public ModelAndView updateProfile(String businessName, String name, String secondEmail,
            String addressLocation, String neighborhood, Integer numberHouse, Long cityId,
            String zipCode, String telephone, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("profile/my_establishment_profile");
        String profileValue = "page_initial";
        Map<String, String> profile = new HashMap<>();

        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;
 
                EstablishmentService service = new EstablishmentService();
                Establishment establishment = new Establishment();

                establishment.setId(user.getId());
                establishment.setBusinessName(businessName);
                establishment.setName(name);
                establishment.setSecondEmail(secondEmail);
                establishment.setAddressLocation(addressLocation);
                establishment.setNeighborhood(neighborhood);
                establishment.setNumberHouse(numberHouse);
                establishment.setCity(cityId);
                establishment.setZipCode(zipCode);
                establishment.setTelephone(telephone);
                
                service.update(establishment);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/establishmentProductList", method = RequestMethod.GET)
    public ModelAndView getConsumerRegisterForm(Long limit, Long offset) throws Exception {
        ModelAndView mv = new ModelAndView("list/establishment_product_list");

        ProductService service = new ProductService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Product> productList = service.readByCriteria(criteria, limit, offset);
        mv.addObject("productList", productList);

        return mv;
    }
}
