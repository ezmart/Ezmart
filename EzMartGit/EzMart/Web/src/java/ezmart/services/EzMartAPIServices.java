package ezmart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ezmart.model.criteria.ProductCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Product;
import ezmart.model.entity.User;
import ezmart.model.model_entity.EstablishmentsLocationModel;
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
                jsonString = null;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                jsonString = mapper.writeValueAsString(productList);
            }
        } catch (Exception e) {
        }

        return jsonString;
    }
}
