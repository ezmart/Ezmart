package ezmart.admin.controller;

import ezmart.model.entity.Consumer;
import ezmart.model.entity.Sector;
import ezmart.model.entity.User;
import ezmart.model.service.SectorService;
import ezmart.model.util.SystemConstant.PAGE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    SectorService sectorService = new SectorService();

    @RequestMapping(value = "/sector", method = RequestMethod.GET)
    public ModelAndView findAllSector(HttpSession session) {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("admin/sector");
            List<Sector> sectorList = new ArrayList<>();
            sectorList = sectorService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null);
            mv.addObject("sectorList", sectorList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
}
