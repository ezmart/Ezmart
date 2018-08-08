package ezmart.register.controller;

import ezmart.model.entity.Establishment;
import ezmart.model.service.EstablishmentService;
import ezmart.model.util.SystemConstant;
import ezmart.model.util.UtilServices;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmporiumRegisterController {

    @RequestMapping(value = "/register/emporium", method = RequestMethod.GET)
    public ModelAndView getConsumerRegister() {
        ModelAndView mv = new ModelAndView("register/register_emporium");

        return mv;
    }

    @RequestMapping(value = "/register/emporium", method = RequestMethod.POST)
    public ModelAndView create(String companyName, String businessName, String cnpj, String email, String password, 
            String addressLocation, Integer numberHouse, String neighborhood, Long city, String zipCode, String telephone) {
        ModelAndView mv = null;
        String confirmationEmail = email;
        String name = companyName;
        
        try {
            EstablishmentService emporiumService = new EstablishmentService();

            Establishment emporium = new Establishment();
            emporium.setName(companyName);
            emporium.setBusinessName(businessName);
            emporium.setCnpj(cnpj);
            emporium.setEmail(email);
            emporium.setPassword(password);
            emporium.setAddressLocation(addressLocation);
            emporium.setNumberHouse(numberHouse);
            emporium.setNeighborhood(neighborhood);
            emporium.setCity(city);
            emporium.setZipCode(zipCode);
            emporium.setTelephone(telephone);

            emporiumService.create(emporium);

            // Envia o email com a autenticação de cadastro
            try {
                Map<String, Object> values = new HashMap<>();
                UtilServices utilServices = new UtilServices();
                values.put("name", name);
                values.put("email", confirmationEmail);
                values.put("type", SystemConstant.EMAIL.AUTHENTICATION.CONFIRMATION_REGISTER);
                utilServices.sendEmail(values);
            } catch (Exception e) {
                mv = new ModelAndView("register/register");
            }

            mv = new ModelAndView("message/message_confirmation");

        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/home");
        }

        return mv;
    }
}
