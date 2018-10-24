package ezmart.product.controller;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import ezmart.model.criteria.ProductCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Product;
import ezmart.model.entity.Promotion;
import ezmart.model.entity.ShoppingList;
import ezmart.model.entity.User;
import ezmart.model.model_entity.PriceComparisonModel;
import ezmart.model.model_entity.PromotionEProductModel;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.PromotionEstablishmentProductService;
import ezmart.model.service.PromotionService;
import ezmart.model.service.ShoppingListService;
import ezmart.model.service.UserService;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private String searthProduct = "";
    private String establishmentName = "";

    //Armazena todas promoções do systema
    private List<PromotionEProductModel> promotionEstablishmentProductlist;

    //Chave: ID do Mercado | Valor: Os produtos do mercado
    private Map<Long, List<EstablishmentProduct>> productsByMarket;

    private Double vPromation = Double.MAX_VALUE;
    private Double vPromationAux = Double.MAX_VALUE;

    @RequestMapping(value = "/updatePhotoProduct", method = RequestMethod.POST)
    public ModelAndView postUpdatePhoto(MultipartFile imgTop, HttpSession session) throws IOException, Exception {
        ModelAndView mv = new ModelAndView("templates/sobre");
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;
                mv = new ModelAndView("profile/my_consumer_profile");
            } else {
                user = (Establishment) auxSession;
                mv = new ModelAndView("profile/my_establishment_profile");
            }
            byte[] imgBytes = imgTop.getBytes();
            UserService service = new UserService();

            service.setImg(user.getId(), imgBytes);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        //saveImgToFile(user.getId(), imgBytes);
        String profileValue = "page_photo";
        Map<String, String> profile = new HashMap<>();
        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);

        return mv;

    }

    @RequestMapping(value = "/imgProduct", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> streamImg(HttpSession session) throws Exception {
        Object auxSession = session.getAttribute("userLogged");
        byte bytes[] = null;
        User user = null;
        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;
            } else {
                user = (Establishment) auxSession;
            }

            UserService service = new UserService();
            bytes = service.getImg(user.getId());

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(
                        new InputStreamResource(
                                new ByteInputStream(bytes, bytes.length)));
    }

    //Pesquisa os produtos
    @ResponseBody
    @RequestMapping(value = "/searthProduct", method = RequestMethod.GET)
    public ModelAndView searthProduct(HttpSession session, Long limit, Long offset, String search) throws Exception {
        ModelAndView mv = new ModelAndView("product/searth_product");
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        if (search != null) {
            searthProduct = search;
        }

        try {
            if (auxSession instanceof Consumer) {
                user = (Consumer) auxSession;
                //Apresentar as listas referentes ao usuário
                ShoppingListService service = new ShoppingListService();
                Map<Long, Object> criteria = new HashMap<>();
                ConsumerService consumerService = new ConsumerService();
                Consumer consumer = consumerService.readById(user.getId());
                criteria.put(UserCriteria.ID_EQ, consumer.getId());
                List<ShoppingList> shoppingList = service.readByCriteria(criteria, null, null);

                mv.addObject("shoppingList", shoppingList);
            } else if (auxSession instanceof Establishment) {
                user = (Establishment) auxSession;
                //mv = new ModelAndView("profile/my_establishment_profile");
            } else {
                //mv = new ModelAndView("profile/my_establishment_profile");
            }

            if (limit != null && offset != null) {
                buildMarket();

                ProductService productService = new ProductService();
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(ProductCriteria.PRODUCT_ILIKE, searthProduct);
                List<Product> productList = productService.readByCriteria(criteria, limit, offset);
                //List<Product> productList = productService.findAll(offset, limit);
                Integer count = productService.countByCriteria(criteria);

                //Verifica se há alguma promoção nos produtos
                Double testPromation = 0.0;

                List<Product> productListAux = new ArrayList<>();
                if (productList != null && !productList.isEmpty()) {
                    for (Product product : productList) {
                        Product productAux = new Product();
                        testPromation = productBuilderByMarket(product.getId());

                        DecimalFormat formato = new DecimalFormat("#.##");
                        testPromation = Double.valueOf(formato.format(testPromation + 0.00));

                        productAux.setId(product.getId());
                        productAux.setBrand(product.getBrand());
                        productAux.setValue(testPromation);
                        productAux.setAux(establishmentName);
                        productAux.setName(product.getName());
                        productAux.setProvider(product.getProvider());
                        productAux.setSector(product.getSector());

                        productListAux.add(productAux);

                    }
                }

                mv.addObject("productList", productListAux);
                mv.addObject("limit", limit);
                mv.addObject("offset", offset);
                mv.addObject("count", count);

            } else {

                String url = "redirect:/searthProduct?limit=9&offset=0";
                mv = new ModelAndView(url);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    private Double productBuilderByMarket(Long productId) throws Exception {
        vPromation = Double.MAX_VALUE;
        establishmentName = "";
        productsByMarket.forEach((key, value) -> {
            EstablishmentService establishmentService = new EstablishmentService();
            Establishment establishment = new Establishment();
            try {
                establishment = establishmentService.readById(key);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            PriceComparisonModel priceComparisonModel = new PriceComparisonModel();

            List<EstablishmentProduct> list = value;

            for (EstablishmentProduct establishmentProduct : list) {
                Long establishmentProductId = establishmentProduct.getProduct().getId();
                if (establishmentProductId.equals(productId)) {

                    //vefica se há promoção para este produto
                    try {
                        Double response = checkPromotion(establishmentProduct.getId());
                        if (!response.equals(0.0)) {
                            if (response < vPromation) {
                                vPromation = response;
                                establishmentName = establishment.getBusinessName();
                            }
                        }

                    } catch (Exception ex) {
                        //Logger.getLogger(ComparePricesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        if (vPromationAux.equals(vPromation)) {
            return 0.0;
        } else {
            return vPromation;
        }
    }

    private void buildMarket() throws Exception {

        promotionEstablishmentProductlist = null;
        promotionEstablishmentProductlist = new ArrayList<>();

        productsByMarket = null;
        productsByMarket = new HashMap<>();

        PromotionEstablishmentProductService PromotionEPService = new PromotionEstablishmentProductService();
        promotionEstablishmentProductlist = PromotionEPService.findAllPromotionEstablishmentProductByCriteria(null, null, null);

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
