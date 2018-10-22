package ezmart.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CosumerLocalMarketsController {

    @RequestMapping(value = "/localMarkets", method = RequestMethod.GET)
    public ModelAndView getLocalMarkets() throws Exception {
        ModelAndView mv = new ModelAndView("consumer/local_markets");

        return mv;
    }
}
