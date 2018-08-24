package ezmart.register.controller;

import ezmart.model.entity.City;
import ezmart.model.entity.Establishment;
import ezmart.model.service.CityService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.util.SystemConstant;
import ezmart.model.util.UtilServices;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstablishmentRegisterController {

    @RequestMapping(value = "/register/emporium", method = RequestMethod.GET)
    public ModelAndView getConsumerRegister() {
        ModelAndView mv = new ModelAndView("register/register_emporium");

        return mv;
    }

    @RequestMapping(value = "/register/emporium", method = RequestMethod.POST)
    public ModelAndView create(String companyName, String secondEmail, String businessName, String cnpj, String email, String password,
            String passwordConfirm, String addressLocation, Integer numberHouse, String neighborhood, Long cityId, String zipCode,
            String telephone) {

        ModelAndView mv = null;
        String confirmationEmail = email;
        String name = companyName;

        try {
            EstablishmentService emporiumService = new EstablishmentService();

            Map<String, Object> fields = new HashMap<>();
            fields.put("companyName", name);
            fields.put("businessName", businessName);
            fields.put("cnpj", cnpj);
            fields.put("email", email);
            fields.put("secondEmail", secondEmail);
            fields.put("password", password);
            fields.put("passwordConfirm", passwordConfirm);
            fields.put("addressLocation", addressLocation);
            fields.put("numberHouse", numberHouse);
            fields.put("neighborhood", neighborhood);
            fields.put("cityId", cityId);
            fields.put("zipCode", zipCode);
            fields.put("telephone", telephone);
            fields.put("validationType", SystemConstant.VALIDATION.REGISTER.REGISTER_CONSUMER);

            Map<String, String> errors = emporiumService.validate(fields);

            if (errors.isEmpty()) {
                Establishment emporium = new Establishment();
                emporium.setName(companyName);
                emporium.setBusinessName(businessName);
                emporium.setCnpj(cnpj);
                emporium.setEmail(email);
                emporium.setPassword(password);
                emporium.setAddressLocation(addressLocation);
                emporium.setNumberHouse(numberHouse);
                emporium.setNeighborhood(neighborhood);
                emporium.setCity(cityId);
                emporium.setZipCode(zipCode);
                emporium.setTelephone(telephone);
                emporium.setLatitude("");
                emporium.setLongitude("");

                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(new File("D:\\TCC 2018\\EzMart\\Web\\web\\resources\\img\\avatar\\storm.jpg"));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "jpg", baos);
                    baos.flush();
                    byte[] imageInByte = baos.toByteArray();
                    baos.close();
                    emporium.setImg(imageInByte);

                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                }

                emporiumService.create(emporium);

                // Envia o email com a autenticação de cadastro
                Map<String, Object> values = new HashMap<>();
                UtilServices utilServices = new UtilServices();
                values.put("name", name);
                values.put("email", confirmationEmail);
                values.put("type", SystemConstant.EMAIL.AUTHENTICATION.CONFIRMATION_REGISTER);
                utilServices.sendEmail(values);

                mv = new ModelAndView("message/message_confirmation");
            } else {
                mv = new ModelAndView("register/emporium_consumer");

                //Faz com que não se perca o que já foi inserido no formulário
                if (companyName != null && !companyName.isEmpty()) {
                    mv.addObject("companyName", name);
                }
                if (businessName != null && !businessName.isEmpty()) {
                    mv.addObject("businessName", businessName);
                }
                if (cnpj != null && !cnpj.isEmpty()) {
                    mv.addObject("cnpj", cnpj);
                }
                if (email != null && !email.isEmpty()) {
                    mv.addObject("email", email);
                }
                if (secondEmail != null && !secondEmail.isEmpty()) {
                    mv.addObject("secondEmail", secondEmail);
                }
                if (numberHouse != null) {
                    mv.addObject("numberHouse", numberHouse);
                }
                if (neighborhood != null && !neighborhood.isEmpty()) {
                    mv.addObject("neighborhood", neighborhood);
                }
                if (addressLocation != null && !addressLocation.isEmpty()) {
                    mv.addObject("addressLocation", addressLocation);
                }
                if (zipCode != null && !zipCode.isEmpty()) {
                    mv.addObject("zipCode", zipCode);
                }
                if (telephone != null && !telephone.isEmpty()) {
                    mv.addObject("telephone", telephone);
                }
                if (password != null && !password.isEmpty()) {
                    mv.addObject("password", password);
                }
                if (passwordConfirm != null && !passwordConfirm.isEmpty()) {
                    mv.addObject("passwordConfirm", passwordConfirm);
                }
                if (cityId != null || cityId == null) {
                    CityService cityService = new CityService();
                    Map<Long, Object> criteria = new HashMap<>();
                    //criteria.put(new CityCriteria().STATE_ID_EQ, mv);
                    List<City> cityList = cityService.readByCriteria(null, null, null);
                    mv.addObject("cityList", cityList);
                }
                //Caso haja erros, será mostrado
                mv.addObject("errors", errors);
            }
        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/home");
        }

        return mv;
    }
}
