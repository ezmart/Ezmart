package ezmart.consumer.controller;

import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.criteria.ShoppingListCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.ShoppingList;
import ezmart.model.entity.User;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ListProductService;
import ezmart.model.service.ShoppingListService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CosumerLocalMarketsController {

    //Chave: ID do Mercado | Valor: Os produtos do mercado
    private Map<Long, List<EstablishmentProduct>> productsByMarket;

    //Armazena os produtos da lista em questão
    private Map<Long, ListProductModel> productsConsumerOnList;

    @RequestMapping(value = "/localMarkets", method = RequestMethod.GET)
    public ModelAndView getLocalMarkets() throws Exception {
        ModelAndView mv = new ModelAndView("consumer/local_markets");

        return mv;
    }

    //Contem o id do estabelecimento
    @RequestMapping(value = "/localMarkets-{id}", method = RequestMethod.POST)
    public String getTEST(@PathVariable Long id, HttpSession session) throws Exception {

        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        productsConsumerOnList = null;
        productsConsumerOnList = new HashMap<>();

        productsByMarket = null;
        productsByMarket = new HashMap<>();

        //Trata o usuário consumidor
        if (auxSession instanceof Consumer) {
            user = (Consumer) auxSession;

            ConsumerService consumerService = new ConsumerService();
            Consumer consumer = consumerService.readById(user.getId());

            //Pega a lista de produtos do usuário armazenando os Ids dos produtos desta lista
            ListProductService listProductService = new ListProductService();
            ShoppingListService service = new ShoppingListService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.ID_EQ, consumer.getId());
            criteria.put(ShoppingListCriteria.LIST_FAVORITE_EQ, true);
            List<ShoppingList> shoppingList = service.readByCriteria(criteria, null, null);
            if (shoppingList.size() == 1) {
                List<ListProductModel> productsList = new ArrayList<>();
                for (ShoppingList list : shoppingList) {
                    criteria = null;
                    criteria = new HashMap<>();
                    criteria.put(ListProductCriteria.LIST_ID_EQ, id);
                    productsList = listProductService.readByCriteriaModel(criteria, null, null);

                }
                if (productsList != null && !productsList.isEmpty()) {
                    for (ListProductModel product : productsList) {
                        productsConsumerOnList.put(product.getProductId(), product);
                    }
                }

                //Pega a lista do mercado e caso tenha produto, a coloca no mapa
                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(id);
                List<EstablishmentProduct> productsListByMarket
                        = establishmentService.findAllEstablishmentProduct(establishment.getId());
                if (productsListByMarket != null && !productsListByMarket.isEmpty()) {
                    productsByMarket.put(establishment.getId(), productsListByMarket);
                }

            }
        }

        String jsonString = null;

        return jsonString;
    }
}
