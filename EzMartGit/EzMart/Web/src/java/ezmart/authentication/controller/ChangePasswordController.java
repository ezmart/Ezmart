package ezmart.authentication.controller;

import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ezmart.model.entity.User;

@Controller
public class ChangePasswordController {

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView changePassword() {
        ModelAndView mv = new ModelAndView("authentication/change_password");

        return mv;
    }
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView update(String currentPassword, String newPassword, String confirmNewPassword, HttpSession session) {
        ModelAndView mv = null;

        Object auxSession = session.getAttribute("userLogged");
        User user = null;
        if (auxSession instanceof Consumer) {
            user = (Consumer) auxSession;
        } else {
            user = (Establishment) auxSession;
        }
        try {
            Map<String, Object> form = new HashMap<>();

            //form.put("databasePassword", user.getPassword());
            form.put("currentPassword", currentPassword);
            form.put("newPassword", newPassword);
            form.put("confirmNewPassword", confirmNewPassword);

            UserService userService = new UserService();
            Map<String, String> errors = userService.comparePassword(form, user);
            if (errors.isEmpty()) {
                user.setId(user.getId());
                user.setPassword(newPassword);
                userService.updatePasswordByUser(user);
                mv = new ModelAndView("message/message_password_change");
                
                session.invalidate();
            } else {
                mv = new ModelAndView("authentication/change_password");
                mv.addObject("errors", errors);
                //mv.addObject("form", form);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
