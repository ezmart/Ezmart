package ezmart.authentication.controller;

import ezmart.model.model_entity.UserModel;
import ezmart.model.service.UserService;
import ezmart.model.util.SystemConstant;
import ezmart.model.util.UtilServices;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecoveryPassword {

    @RequestMapping(value = "/recoveryPassword", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("authentication/recovery_password");

        return mv;
    }

    @RequestMapping(value = "/recoveryPassword", method = RequestMethod.POST)
    public ModelAndView update(String email) {
        ModelAndView mv = null;

        try {

            String userEmail = email;
            UserService userService = new UserService();
            Map<String, Object> fields = new HashMap<>();
            fields.put("email", userEmail);
            fields.put("validationType", SystemConstant.VALIDATION.USER.RECOVERY_PASSWORD);
            Map<String, String> errors = userService.validate(fields);
            if (errors.isEmpty()) {

                String newPassword = new UtilServices().generatePassword();

                UserModel userModel = new UserModel();
                userModel.setPassword(newPassword);
                userModel.setEmail(userEmail);
                userService.updatePasswordByEmail(userModel);

                Map<String, Object> values = new HashMap<>();
                values.put("email", userEmail);
                values.put("newPassword", newPassword);
                values.put("type", SystemConstant.EMAIL.AUTHENTICATION.RECOVERY_PASSWORD);

                new UtilServices().sendEmail(values);

                mv = new ModelAndView("redirect:/logout");
            } else {
                mv = new ModelAndView("authentication/recovery_password");
                mv.addObject("errors", errors);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
