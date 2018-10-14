package ezmart.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.User;
import ezmart.model.model_entity.EstablishmentsLocationModel;
import ezmart.model.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EzMartAPIServices {

    //
    @ResponseBody
    @RequestMapping(value = "api/localMarkets", method = RequestMethod.GET)
    public String getApiLocalMarkets() throws Exception {

        UserService service = new UserService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(UserCriteria.USER_TYPE_EQ, "emporium");
        List<User> establishmentList = service.readByCriteria(criteria, null, null);

        EstablishmentsLocationModel model = null;
        List<EstablishmentsLocationModel> modeList = new ArrayList<>();
        for (User user : establishmentList) {
            model = new EstablishmentsLocationModel();
            model.setId(user.getId());
            model.setLatitude(user.getLatitude());
            model.setLongitude(user.getLongitude());
            modeList.add(model);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(modeList);

        return jsonString;
    }
}
