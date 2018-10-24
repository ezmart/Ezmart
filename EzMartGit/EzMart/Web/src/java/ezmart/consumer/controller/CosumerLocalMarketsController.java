package ezmart.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.criteria.ShoppingListCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Promotion;
import ezmart.model.entity.ShoppingList;
import ezmart.model.entity.User;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.model_entity.PriceComparisonModel;
import ezmart.model.model_entity.ProductModel;
import ezmart.model.model_entity.PromotionEProductModel;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ListProductService;
import ezmart.model.service.PromotionEstablishmentProductService;
import ezmart.model.service.PromotionService;
import ezmart.model.service.ShoppingListService;
import ezmart.model.util.Mascara;
import ezmart.model.util.TrataNumero;
import ezmart.model.util.UtilServices;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CosumerLocalMarketsController {

    //Chave: ID do Mercado | Valor: Os produtos do mercado
    private Map<Long, List<EstablishmentProduct>> productsByMarket;

    //Armazena os produtos da lista em questão
    private Map<Long, ListProductModel> productsConsumerOnList;

    //Armazena todas promoções do sistema
    private List<PromotionEProductModel> promotionEstablishmentProductlist;

    //Armazena os produtos encontrados por mercado
    private List<PriceComparisonModel> productsByMarketOnListConsumer;

    @RequestMapping(value = "/localMarkets", method = RequestMethod.GET)
    public ModelAndView getLocalMarkets() throws Exception {
        ModelAndView mv = new ModelAndView("consumer/local_markets");

        return mv;
    }

    //Contem o id do estabelecimento
    @ResponseBody
    @RequestMapping(value = "/localMarkets-{id}", method = RequestMethod.GET)
    public String getTEST(@PathVariable Long id, HttpSession session) throws Exception {
        String jsonString = null;

        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        PromotionEstablishmentProductService service = new PromotionEstablishmentProductService();
        promotionEstablishmentProductlist = service.findAllPromotionEstablishmentProductByCriteria(null, null, null);

        productsByMarketOnListConsumer = null;
        productsByMarketOnListConsumer = new ArrayList<>();

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
            ShoppingListService serviceAux = new ShoppingListService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.ID_EQ, consumer.getId());
            criteria.put(ShoppingListCriteria.LIST_FAVORITE_EQ, true);
            List<ShoppingList> shoppingList = serviceAux.readByCriteria(criteria, null, null);
            if (shoppingList.size() == 1) {
                List<ListProductModel> productsList = new ArrayList<>();
                for (ShoppingList list : shoppingList) {
                    criteria = null;
                    criteria = new HashMap<>();
                    criteria.put(ListProductCriteria.LIST_ID_EQ, list.getId());
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

                productsByMarket.forEach((key, value) -> {
                    PriceComparisonModel priceComparisonModel = new PriceComparisonModel();

                    //Armazena os preços
                    List<Double> pricesList = new ArrayList<>();

                    ProductModel productModel = new ProductModel();
                    String establishmentName = "";
                    Establishment establishmentAux = null;
                    List<ProductModel> productModelList = new ArrayList<>();
                    List<EstablishmentProduct> list = value;

                    int flag = 0;
                    for (EstablishmentProduct establishmentProduct : list) {

                        Long productId = establishmentProduct.getProduct().getId();

                        if (productsConsumerOnList.containsKey(productId)) {

                            productModel = new ProductModel();
                            ListProductModel product = new ListProductModel();
                            product = productsConsumerOnList.get(productId);
                            productModel.setProductId(productId);
                            establishmentAux = new Establishment();

                            try {
                                establishmentAux = establishmentService.readById(establishmentProduct.getEstablishment().getId());
                            } catch (Exception ex) {
                                //Logger.getLogger(ComparePricesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //productModel = new ProductModel();
                            productModel.setBarCode(establishmentProduct.getProduct().getBarCode());
                            //model.setDescription(establishmentProduct.getProduct().);
                            establishmentName = establishmentAux.getName();
                            productModel.setProductName(product.getProductName());

                            //vefica se há promoção para este produto
                            Double valuePromation = 0.0;
                            try {
                                valuePromation = checkPromotion(establishmentProduct.getId());
                            } catch (Exception ex) {
                                //Logger.getLogger(ComparePricesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (valuePromation != 0.0) {
                                pricesList.add(valuePromation);
                                productModel.setPrice(valuePromation);
                            } else {
                                pricesList.add(establishmentProduct.getPrice());
                                productModel.setPrice(establishmentProduct.getPrice());
                            }

                            productModelList.add(productModel);

                            flag = flag + 1;
                        }
                    }

                    if (pricesList != null && !pricesList.isEmpty()) {

                        if (flag != productsConsumerOnList.size()) {
                            priceComparisonModel.setNote("**Não contém todos produtos");
                        }

                        Double totalPrice = new UtilServices().totalPrice(pricesList);
                        String aux = TrataNumero.editarNumero(TrataNumero.mult2(totalPrice), Mascara.getMascaraDuasCasas());
                        priceComparisonModel.setTotalPrice(aux);
                        priceComparisonModel.setEstablishmentName(establishmentName);
                        priceComparisonModel.setProductModelList(productModelList);

                        //if (productsByMarketList != null && !productsByMarketList.isEmpty()) {
                        productsByMarketOnListConsumer.add(priceComparisonModel);
                        //}
                    }
                });

            }
        }

        //Caso a comparação esteja com os produtos
        if (productsByMarketOnListConsumer != null && !productsByMarketOnListConsumer.isEmpty()) {

        } else {

        }

        ObjectMapper mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(productsByMarketOnListConsumer);

        return jsonString;
    }

    //Verifica se há promoção do produto no mercado
    private Double checkPromotion(Long establishmentProductId) throws Exception {
        Double valuePromation = 0.0;
        //Paga a data atual do sistema
        Date date = new Date(System.currentTimeMillis());
        PromotionService service = new PromotionService();
        Promotion promotion = null;

        //Valida a promoção
        for (PromotionEProductModel promotionEProductModel : promotionEstablishmentProductlist) {
            if (promotionEProductModel.getEstablishmentProductId().equals(establishmentProductId)) {
                promotion = new Promotion();
                promotion = service.readById(promotionEProductModel.getPromotionId());

                String datePromation = promotion.getFinalDate().toString();
                String[] splitPromation = datePromation.split(" ");
                String datePromationValue = splitPromation[0];

                String dateSystem = date.toString();
                String[] splitSystem = dateSystem.split(" ");
                String dateSystemValue = splitSystem[0];

                int finalDate = date.compareTo(promotion.getFinalDate());
                if (finalDate == -1) {
                    int startDate = date.compareTo(promotion.getStartDate());
                    if (startDate == 1) {
                        valuePromation = promotionEProductModel.getPromotionalPrice();
                    }
                } else {
                    if (dateSystemValue.equals(datePromationValue)) {
                        valuePromation = promotionEProductModel.getPromotionalPrice();
                    }
                }
            }
        }

        return valuePromation;
    }
}
