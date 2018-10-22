package ezmart.consumer.controller;

import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Promotion;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.model_entity.PriceComparisonModel;
import ezmart.model.model_entity.ProductModel;
import ezmart.model.model_entity.PromotionEProductModel;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ListProductService;
import ezmart.model.service.PromotionEstablishmentProductService;
import ezmart.model.service.PromotionService;
import ezmart.model.util.UtilServices;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComparePricesController {

    //Chave: ID do Mercado | Valor: Os produtos do mercado
    private Map<Long, List<EstablishmentProduct>> productsByMarket;

    //Armazena os produtos encontrados por mercado
    private List<PriceComparisonModel> productsByMarketOnListConsumer;

    //Armazena os produtos da lista em questão
    private Map<Long, ListProductModel> productsConsumerOnList;

    //Armazena todas promoções do systema
    private List<PromotionEProductModel> promotionEstablishmentProductlist;

    //Verica os preços dos produtos
    @RequestMapping(value = "/comparePrices?{id}", method = RequestMethod.GET)
    public ModelAndView comparePrices(@PathVariable Long id) throws Exception {
        ModelAndView mv = null;
        try {
            productsByMarket = null;
            productsByMarket = new HashMap<>();

            productsByMarketOnListConsumer = null;
            productsByMarketOnListConsumer = new ArrayList<>();

            productsConsumerOnList = null;
            productsConsumerOnList = new HashMap<>();

            promotionEstablishmentProductlist = null;
            promotionEstablishmentProductlist = new ArrayList<>();

            PromotionEstablishmentProductService service = new PromotionEstablishmentProductService();
            promotionEstablishmentProductlist = service.findAllPromotionEstablishmentProductByCriteria(null, null, null);

            //Pega a lista de produtos do usuário armazenando os Ids dos produtos desta lista
            ListProductService listProductService = new ListProductService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(ListProductCriteria.LIST_ID_EQ, id);
            List<ListProductModel> productsList = listProductService.readByCriteriaModel(criteria, null, null);

            if (productsList == null || productsList.isEmpty()) {

                mv = new ModelAndView("message/message_list_empty");

            } else {
                
                for (ListProductModel product : productsList) {
                    productsConsumerOnList.put(product.getProductId(), product);
                }

                productBuilderByMarket();

                //Caso a comparação esteja com os produtos
                if (productsByMarketOnListConsumer != null && !productsByMarketOnListConsumer.isEmpty()) {

                    mv = new ModelAndView("consumer/compare_prices");

                } else {
                    mv = new ModelAndView("redirect:/home");
                }

                mv.addObject("productsByMarketOnListConsumer", productsByMarketOnListConsumer);
            }
        } catch (Exception exception) {
            mv = new ModelAndView("message/message_system_error");
            throw exception;
        }

        return mv;

    }

    private void productBuilderByMarket() throws Exception {

        //Pega todos os mercados
        EstablishmentService establishmentService = new EstablishmentService();
        List<Establishment> establishmentList = establishmentService.readByCriteria(null, null, null);

        for (Establishment establishment : establishmentList) {

            //Pega a lista do mercado e caso tenha produto, a coloca no mapa
            List<EstablishmentProduct> productsListByMarket
                    = establishmentService.findAllEstablishmentProduct(establishment.getId());
            if (productsListByMarket != null && !productsListByMarket.isEmpty()) {
                productsByMarket.put(establishment.getId(), productsListByMarket);
            }
        }

        productsByMarket.forEach((key, value) -> {
            PriceComparisonModel priceComparisonModel = new PriceComparisonModel();

            //Armazena os preços
            List<Double> pricesList = new ArrayList<>();

            ProductModel productModel = new ProductModel();
            String establishmentName = "";
            Establishment establishment = null;
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
                    establishment = new Establishment();

                    try {
                        establishment = establishmentService.readById(establishmentProduct.getEstablishment().getId());
                    } catch (Exception ex) {
                        //Logger.getLogger(ComparePricesController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //productModel = new ProductModel();
                    productModel.setBarCode(establishmentProduct.getProduct().getBarCode());
                    //model.setDescription(establishmentProduct.getProduct().);
                    establishmentName = establishment.getName();
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
                priceComparisonModel.setTotalPrice(totalPrice);
                priceComparisonModel.setEstablishmentName(establishmentName);
                priceComparisonModel.setProductModelList(productModelList);

                //if (productsByMarketList != null && !productsByMarketList.isEmpty()) {
                productsByMarketOnListConsumer.add(priceComparisonModel);
                //}
            }
        });
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

//    private void productBuilderByMarket() throws Exception {
//
//        //Pega todos os mercados
//        EstablishmentService establishmentService = new EstablishmentService();
//        List<Establishment> establishmentList = establishmentService.readByCriteria(null, null, null);
//
//        //Armazena os preços
//        List<Double> pricesList = new ArrayList<>();
//
//        for (Establishment establishment : establishmentList) {
//
//            //Pega a lista do mercado e caso tenha produto, a coloca no mapa
//            List<EstablishmentProduct> productsListByMarket
//                    = establishmentService.findAllEstablishmentProduct(establishment.getId());
//            if (productsListByMarket != null && !productsListByMarket.isEmpty()) {
//                productsByMarket.put(establishment.getId(), productsListByMarket);
//            }
//        }
//        //productsByMarketOnListConsumer = null;
//        productsByMarket.forEach((key, value) -> {
//            PriceComparisonModel model = new PriceComparisonModel();
//            List<PriceComparisonModel> productsByMarketList = productsByMarketList = new ArrayList<>();
//            Establishment establishment = null;
//
//            List<EstablishmentProduct> list = value;
//            for (EstablishmentProduct establishmentProduct : list) {
//                
//                Long productId = establishmentProduct.getProduct().getId();
//
//                if (productsMap.containsKey(productId)) {
//                    model = null;
//                    ListProductModel test = new ListProductModel();
//                    test = productsMap.get(productId);
//                    
//                    establishment = new Establishment();
//
//                    try {
//                        establishment = establishmentService.readById(establishmentProduct.getEstablishment().getId());
//                    } catch (Exception ex) {
//                        Logger.getLogger(ComparePricesController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    model = new PriceComparisonModel();
//                    model.setBarCode(establishmentProduct.getProduct().getBarCode());
//                    //model.setDescription(establishmentProduct.getProduct().);
//                    model.setPrice(establishmentProduct.getPrice());
//
//                    //Adiciona os preços
//                    pricesList.add(establishmentProduct.getPrice());
//
//                    model.setEstablishmentName(establishment.getName());
//                    model.setProductName(test.getProductName());
//
//                    if (model != null) {
//                        productsByMarketList.add(model);
//                    }
//                }
//            }
//
//            if (pricesList != null && !pricesList.isEmpty()) {
//                Double totalPrice = new UtilServices().totalPrice(pricesList);
//                model.setTotalPrice(totalPrice);
//
////                if (model != null) {
////
////                    productsByMarketList.add(model);
////                }
//
//                if (productsByMarketList != null && !productsByMarketList.isEmpty()) {
//                    productsByMarketOnListConsumer.add(productsByMarketList);
//                }
//            }
//
//        });
//
//    }
//}
