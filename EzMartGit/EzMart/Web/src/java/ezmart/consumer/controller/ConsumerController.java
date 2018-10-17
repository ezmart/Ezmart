package ezmart.consumer.controller;

import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.ListProduct;
import ezmart.model.entity.ShoppingList;
import ezmart.model.entity.State;
import ezmart.model.entity.User;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.service.CityService;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.ListProductService;
import ezmart.model.service.ShoppingListService;
import ezmart.model.service.StateService;
import ezmart.model.util.SystemConstant;
import java.sql.Date;
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
public class ConsumerController {

    //Chama o formulário de para edição dos dados do perfil
    @RequestMapping(value = "/editProfileConsumer", method = RequestMethod.GET)
    public ModelAndView getMyAddress(HttpSession session) {
        ModelAndView mv = null;
        try {
            String profileValue = "page_editProfile";
            Map<String, String> profile = new HashMap<>();
            profile.put("profileValue", profileValue);

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            //Trata o usuário consumidor
            if (auxSession instanceof Consumer) {
                mv = new ModelAndView("profile/my_consumer_profile");

                CityService cityService = new CityService();
                List<City> cityList = cityService.readByCriteria(null, null, null);
                mv.addObject("cityList", cityList);

                user = (Consumer) auxSession;

                ConsumerService consumerService = new ConsumerService();
                Consumer consumer = consumerService.readByUserId(user.getId());

                mv.addObject("consumer", consumer);
                mv.addObject("cityIdConsumer", consumer.getCity());

                StateService stateService = new StateService();
                List<State> stateList = stateService.readByCriteria(null, null, null);
                mv.addObject("stateList", stateList);

                City city = cityService.readById(user.getCity());
                mv.addObject("stateIdConsumer", city.getState().getId());

            }

            mv.addObject("profile", profile);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    //Atualisa os dados do perfil
    @RequestMapping(value = "/editProfileConsumer", method = RequestMethod.POST)
    public ModelAndView updateProfile(String name, String lastName, String addressLocation, Integer numberHouse,
            String neighborhood, Long cityId, String zipCode, String telephone, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("profile/my_consumer_profile");
        String profileValue = "page_initial";
        Map<String, String> profile = new HashMap<>();

        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {

            if (auxSession instanceof Consumer) {

                user = (Consumer) auxSession;
                Long id = user.getId();
                ConsumerService service = new ConsumerService();
                Consumer consumer = new Consumer();

                consumer.setName(name);
                consumer.setLastName(lastName);

                consumer.setId(id);
                consumer.setAddressLocation(addressLocation);
                consumer.setNumberHouse(numberHouse);
                consumer.setNeighborhood(neighborhood);
                consumer.setCity(cityId);
                consumer.setZipCode(zipCode);
                consumer.setTelephone(telephone);
                consumer.setLatitude("");
                consumer.setLongitude("");

                service.update(consumer);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    //Chama todas as listas de compra do usuário 
    @RequestMapping(value = "/shoppingList", method = RequestMethod.GET)
    public ModelAndView getShoppingList(HttpSession session) {
        ModelAndView mv = new ModelAndView("consumer/consumer_shopping_list");

        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;

            } else {
                user = (Establishment) auxSession;
            }

            //Apresentar as listas referentes ao usuário
            ShoppingListService service = new ShoppingListService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.ID_EQ, user.getId());
            List<ShoppingList> shoppingList = service.readByCriteria(criteria, null, null);

            mv.addObject("shoppingList", shoppingList);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    // Atualisa, excluí e cria a lista
    @RequestMapping(value = "/shoppingList", method = RequestMethod.POST)
    public ModelAndView postShoppingList(String value, String type, Long idUpdateNameShoppingList, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/shoppingList");

        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        if (auxSession instanceof Consumer) {
            user = (Consumer) auxSession;
        } else {
            user = (Establishment) auxSession;
        }

        if (type != null && type.equals("CREATE")) {
            try {
                String name = value;
                ShoppingList shoppingList = new ShoppingList();
                shoppingList.setName(name);
                shoppingList.setConsumerId(user.getId());
                shoppingList.setFavorite(false);

                //Paga a data atual do sistema
                Date date = new Date(System.currentTimeMillis());
                shoppingList.setDate(date);

                //shoppingList.setProductList(null);
                ShoppingListService service = new ShoppingListService();
                service.create(shoppingList);

            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else if (type != null && type.equals("UPDATE")) {
            ShoppingListService service = new ShoppingListService();
            String listName = value;
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(listName);
            shoppingList.setId(idUpdateNameShoppingList);
            service.update(shoppingList);

        } else if (type != null && type.equals("DELETE")) {
            try {
                ShoppingListService service = new ShoppingListService();
                Long id = Long.parseLong(value);
                service.delete(id);

            } catch (Exception exception) {
                System.out.println(exception);
            }
        }

        return mv;
    }

    // Chama os produtos da lista em questão
    @RequestMapping(value = "/products-{id}", method = RequestMethod.GET)
    public ModelAndView getProductsList(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("consumer/consumer_products_list");

        try {
            ShoppingListService service = new ShoppingListService();
            ShoppingList shoppingList = service.readById(id);

            ListProductService listProductService = new ListProductService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(ListProductCriteria.LIST_ID_EQ, id);
            List<ListProductModel> productsList = listProductService.readByCriteriaModel(criteria, null, null);

            mv.addObject("shoppingList", shoppingList);
            mv.addObject("productsList", productsList);

            //Lista Atual
            mv.addObject("listId", id);

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    // Excluí e incluí produtos na lista no menu da lista
    @RequestMapping(value = "/products-{id}", method = RequestMethod.POST)
    public ModelAndView postProductList(@PathVariable Long id, String value, String type) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/products-" + id);

        ListProductService service = new ListProductService();

        if (type != null && type.equals("DELETE")) {
            try {

                Long productId = Long.parseLong(value);
                //id da lista / id do produto
                service.deleteFromList(id, productId);

            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {

            Long productId = Long.parseLong(value);
            Map<String, Object> fields = new HashMap<>();

            fields.put("listId", id);
            fields.put("productId", productId);
            fields.put("validationType", SystemConstant.VALIDATION.PRODUCT.ADD_PRODUCT_LIST);

            Map<String, String> errors = service.validate(fields);

            //Valida se o produto já está na lista em questão
            if (errors == null || errors.isEmpty()) {
                ListProduct listProduct = new ListProduct();
                listProduct.setListId(id);
                listProduct.setProdutcId(productId);
                listProduct.setQuantity(2);
                service.create(listProduct);
            }

        }

        return mv;
    }

    //Incluí produtos na lista no HOME
    @RequestMapping(value = "/newproducts", method = RequestMethod.POST)
    public ModelAndView postProductListHome(Long listId, Long productId) throws Exception {
        ModelAndView mv = null;
        ListProductService service = new ListProductService();
        
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("listId", listId);
            fields.put("productId", productId);
            fields.put("validationType", SystemConstant.VALIDATION.PRODUCT.ADD_PRODUCT_LIST);

            Map<String, String> errors = service.validate(fields);

            //Valida se o produto já está na lista em questão
            if (errors == null || errors.isEmpty()) {
                ListProduct listProduct = new ListProduct();
                listProduct.setListId(listId);
                listProduct.setProdutcId(productId);
                listProduct.setQuantity(2);
                service.create(listProduct);
            }
            
            //mv = new ModelAndView("redirect:/home");
            mv = new ModelAndView("redirect:/products-" + listId);

        } catch (Exception e) {
            mv = new ModelAndView();
        }

        return mv;
    }

    @RequestMapping(value = "/#", method = RequestMethod.GET)
    public ModelAndView getProducts() {
        ModelAndView mv = null;

        return mv;
    }

}
