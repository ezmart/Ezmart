package ezmart.consumer.controller;

import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.State;
import ezmart.model.entity.User;
import ezmart.model.service.CityService;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
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
public class ConsumerController {

    @RequestMapping(value = "/editProfileConsumer", method = RequestMethod.GET)
    public ModelAndView getMyAddress(HttpSession session) {
        ModelAndView mv = null;
        try {
            String profileValue = "page_editProfile";
            Map<String, String> profile = new HashMap<>();
            profile.put("profileValue", profileValue);

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            //Trata o usuário consumidor
            if (auxSession instanceof Consumer) {
                mv = new ModelAndView("profile/my_consumer_profile");

                CityService cityService = new CityService();
                List<City> cityList = cityService.readByCriteria(null, null, null);
                mv.addObject("cityList", cityList);

                user = (Consumer) auxSession;

                ConsumerService consumerService = new ConsumerService();
                Consumer consumer = consumerService.readByUserId(user.getId());

                mv.addObject("consumer", consumer);
                mv.addObject("cityIdConsumer", consumer.getCity());

                StateService stateService = new StateService();
                List<State> stateList = stateService.readByCriteria(null, null, null);
                mv.addObject("stateList", stateList);

                City city = cityService.readById(user.getCity());
                mv.addObject("stateIdConsumer", city.getState().getId());

            }

            mv.addObject("profile", profile);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/editProfileConsumer", method = RequestMethod.POST)
    public ModelAndView updateProfile(String name, String lastName, String addressLocation, Integer numberHouse,
            String neighborhood, Long cityId, String zipCode, String telephone, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("profile/my_consumer_profile");
        String profileValue = "page_initial";
        Map<String, String> profile = new HashMap<>();

        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            
            if (auxSession instanceof Consumer) {

                user = (Consumer) auxSession;
                Long id = user.getId();
                ConsumerService service = new ConsumerService();
                Consumer consumer = new Consumer();

                consumer.setName(name);
                consumer.setLastName(lastName);

                consumer.setId(id);
                consumer.setAddressLocation(addressLocation);
                consumer.setNumberHouse(numberHouse);
                consumer.setNeighborhood(neighborhood);
                consumer.setCity(cityId);
                consumer.setZipCode(zipCode);
                consumer.setTelephone(telephone);
                consumer.setLatitude("");
                consumer.setLongitude("");

                service.update(consumer);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }
}