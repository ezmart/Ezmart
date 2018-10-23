package ezmart.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezmart.model.criteria.CityCriteria;
import ezmart.model.criteria.ProductCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.City;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.Product;
import ezmart.model.entity.User;
import ezmart.model.model_entity.EstablishmentsLocationModel;
import ezmart.model.service.CityService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EzMartAPIServices {

    //Busca os mercados cadastrados no sistema
    @ResponseBody
    @RequestMapping(value = "api/system/localMarkets", method = RequestMethod.GET)
    public String getApiLocalMarkets() throws Exception {
        String jsonString = null;
        try {
            UserService service = new UserService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.USER_TYPE_EQ, "emporium");
            List<User> establishmentList = service.readByCriteria(criteria, null, null);

            EstablishmentService establishmentService = new EstablishmentService();

            EstablishmentsLocationModel model = null;
            List<EstablishmentsLocationModel> modeList = new ArrayList<>();
            for (User user : establishmentList) {
                Establishment establishment = establishmentService.readByUserId(user.getId());
                model = new EstablishmentsLocationModel();
                model.setEstablishmentsName(establishment.getName());
                model.setId(user.getId());
                model.setLatitude(user.getLatitude());
                model.setLongitude(user.getLongitude());

                modeList.add(model);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(modeList);
        } catch (Exception e) {
        }

        return jsonString;
    }

    //Busca os produtos de acordo com a pesquisa
    @ResponseBody
    @RequestMapping(value = "api/system/products-{value}", method = RequestMethod.GET)
    public String getApiProducts(@PathVariable String value) throws Exception {
        String jsonString = null;
        String productName = value;
        try {

            ProductService productService = new ProductService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(ProductCriteria.PRODUCT_ILIKE, productName);
            List<Product> productList = productService.readByCriteria(criteria, null, null);
            if (productList == null || productList.size() == 0) {
                jsonString = "ERROR!";
            } else {
                ObjectMapper mapper = new ObjectMapper();
                jsonString = mapper.writeValueAsString(productList);
            }
        } catch (Exception e) {
            jsonString = "ERROR!";
        }

        return jsonString;
    }

    //Retorna as cidades de acordo com o estado selecionado
    @ResponseBody
    @RequestMapping(value = "register/api/system/getCities-{value}", method = RequestMethod.GET)
    public String getCitiesWithState(@PathVariable Long value) {
        //ModelAndView mv = new ModelAndView();
        String jsonString = null;
        Long stateId = value;
        try {
            CityService service = new CityService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(CityCriteria.STATE_ID_EQ, stateId);
            List<City> cityList = service.readByCriteria(criteria, null, null);
            if (cityList == null || cityList.isEmpty()) {
                jsonString = "ERROR!";
            } else {
                //mv.addObject("cityList", cityList);
                ObjectMapper mapper = new ObjectMapper();
                jsonString = mapper.writeValueAsString(cityList);
            }
        } catch (Exception e) {
            jsonString = "ERROR!";
        }

        return jsonString;
    }
}
