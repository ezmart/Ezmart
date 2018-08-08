package ezmart.register.controller;

import ezmart.model.criteria.CityCriteria;
import ezmart.model.criteria.ConsumerCriteria;
import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.service.CityService;
import ezmart.model.service.ConsumerService;
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
public class ConsumerRegisterController {

    @RequestMapping(value = "/register/consumer", method = RequestMethod.GET)
    public ModelAndView getConsumerRegisterForm() {
        ModelAndView mv = new ModelAndView("register/register_consumer");

        try {
            //StateService stateService = new StateService();

            CityService cityService = new CityService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(new CityCriteria().STATE_ID_EQ, mv);
            List<City> cityList = cityService.readByCriteria(null, null, null);
            mv.addObject("cityList", cityList);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/register/consumer", method = RequestMethod.POST)
    public ModelAndView create(String name, String lastName, String cpf, String email, String password, String addressLocation,
            Integer numberHouse, String neighborhood, Long cityId, String zipCode, String telephone) {
        ModelAndView mv = null;
        String confirmationEmail = email;
        String userName = name;

        try {
            ConsumerService consumerService = new ConsumerService();
            Map<String, Object> fields = new HashMap<>();
            fields.put("cpf", cpf);
            fields.put("email", email);
            fields.put("password", password);
            fields.put("validationType", SystemConstant.VALIDATION.REGISTER.REGISTER_CONSUMER);

            Map<String, String> errors = consumerService.validate(fields);
            if (errors.isEmpty()) {
                Consumer consumer = new Consumer();
                consumer.setName(name);
                consumer.setLastName(lastName);
                consumer.setCpf(cpf);
                consumer.setEmail(email);
                consumer.setPassword(password);
                consumer.setAddressLocation(addressLocation);
                consumer.setNumberHouse(numberHouse);
                consumer.setNeighborhood(neighborhood);
                consumer.setCity(cityId);
                consumer.setZipCode(zipCode);
                consumer.setTelephone(telephone);
                consumer.setLatitude("");
                consumer.setLongitude("");

                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(new File("D:\\TCC 2018\\EzMart\\Web\\web\\resources\\img\\avatar\\storm.jpg"));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "jpg", baos);
                    baos.flush();
                    byte[] imageInByte = baos.toByteArray();
                    baos.close();
                    consumer.setImg(imageInByte);

                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                }

                consumerService.create(consumer);

                // Envia o email com a autenticação de cadastro
                Map<String, Object> values = new HashMap<>();
                UtilServices utilServices = new UtilServices();
                values.put("name", userName);
                values.put("email", confirmationEmail);
                values.put("type", SystemConstant.EMAIL.AUTHENTICATION.CONFIRMATION_REGISTER);
                utilServices.sendEmail(values);

                mv = new ModelAndView("message/message_confirmation");
            } else {
                mv = new ModelAndView("register/register_consumer");
                mv.addObject("errors", errors);
            }
        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }
}
