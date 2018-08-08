package ezmart.profile.controller;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import ezmart.model.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyProfileController {

    //Encaminha para o perfil do usuário em questão
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpSession session) {
        ModelAndView mv = null;
        Object auxSession = session.getAttribute("userLogged");
        if (auxSession instanceof Consumer) {
            mv = new ModelAndView("profile/my_consumer_profile");
        } else {
            mv = new ModelAndView("profile/my_establishment_profile");
        }

        String profileValue = "page_initial";
        Map<String, String> profile = new HashMap<>();
        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);

        return mv;
    }

    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    public ModelAndView getMyTelephone(HttpSession session) {
        ModelAndView mv = null;

        Object auxSession = session.getAttribute("userLogged");

        try {
            if (auxSession instanceof Consumer) {
                mv = new ModelAndView("profile/my_profile");
                String profileValue = "page_myList";
                Map<String, String> profile = new HashMap<>();
                profile.put("profileValue", profileValue);
                mv.addObject("profile", profile);
            } else {

                //mv = new ModelAndView("list/establishment_list_product");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;

    }

    @RequestMapping(value = "/updatePhoto", method = RequestMethod.GET)
    public ModelAndView getUpdatePhoto(HttpSession session) {
        ModelAndView mv = new ModelAndView("templates/sobre");

        try {
            Object auxSession = session.getAttribute("userLogged");

            if (auxSession instanceof Consumer) {
                mv = new ModelAndView("profile/my_consumer_profile");
                String profileValue = "page_photo";
                Map<String, String> profile = new HashMap<>();
                profile.put("profileValue", profileValue);
                mv.addObject("profile", profile);
            } else {
                mv = new ModelAndView("profile/my_establishment_profile");
                String profileValue = "page_photo";
                Map<String, String> profile = new HashMap<>();
                profile.put("profileValue", profileValue);
                mv.addObject("profile", profile);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/updatePhoto", method = RequestMethod.POST)
    public ModelAndView postUpdatePhoto(MultipartFile imgTop, HttpSession session) throws IOException, Exception {
        ModelAndView mv = new ModelAndView("templates/sobre");
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;
                mv = new ModelAndView("profile/my_consumer_profile");
            } else {
                user = (Establishment) auxSession;
                mv = new ModelAndView("profile/my_establishment_profile");
            }
            byte[] imgBytes = imgTop.getBytes();
            UserService service = new UserService();

            service.setImg(user.getId(), imgBytes);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        //saveImgToFile(user.getId(), imgBytes);
        String profileValue = "page_photo";
        Map<String, String> profile = new HashMap<>();
        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);

        return mv;

    }

    @RequestMapping(value = "/imgProfile", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> streamImg(HttpSession session) throws Exception {
        Object auxSession = session.getAttribute("userLogged");
        byte bytes[] = null;
        User user = null;
        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;
            } else {
                user = (Establishment) auxSession;
            }

            UserService service = new UserService();
            bytes = service.getImg(user.getId());

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(
                        new InputStreamResource(
                                new ByteInputStream(bytes, bytes.length)));
    }
}
