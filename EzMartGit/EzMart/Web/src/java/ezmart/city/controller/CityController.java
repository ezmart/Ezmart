package ezmart.city.controller;

import ezmart.model.service.CityService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ezmart.model.entity.City;

@Controller
public class CityController {

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ModelAndView read(Long limit, Long offset) {
        ModelAndView mv = new ModelAndView("/city/list");
        try {
            CityService service = new CityService();
            Map<Long, Object> criteria = new HashMap<>();
            List<City> cityList = service.readByCriteria(criteria, limit, offset);
            mv.addObject("cityList", cityList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
