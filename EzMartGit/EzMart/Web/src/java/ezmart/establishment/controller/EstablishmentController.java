package ezmart.establishment.controller;

import ezmart.model.entity.Avaliation;
import ezmart.model.entity.City;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Product;
import ezmart.model.entity.Promotion;
import ezmart.model.entity.PromotionEstablishmentProduct;
import ezmart.model.entity.State;
import ezmart.model.entity.User;
import ezmart.model.service.AvaliationService;
import ezmart.model.service.CityService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.PromotionEstablishmentProductService;
import ezmart.model.service.PromotionService;
import ezmart.model.service.StateService;
import java.text.DecimalFormat;
import java.sql.Date;
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
public class EstablishmentController {

    @RequestMapping(value = "/editProfileEstablishment", method = RequestMethod.GET)
    public ModelAndView getMyAddress(HttpSession session) {
        ModelAndView mv = null;
        try {
            String profileValue = "page_editProfile";
            Map<String, String> profile = new HashMap<>();
            profile.put("profileValue", profileValue);

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("profile/my_establishment_profile");

                CityService cityService = new CityService();
                List<City> cityList = cityService.readByCriteria(null, null, null);
                mv.addObject("cityList", cityList);

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                mv.addObject("establishment", establishment);
                mv.addObject("cityIdEstablishment", establishment.getCity());

                StateService stateService = new StateService();
                List<State> stateList = stateService.readByCriteria(null, null, null);
                mv.addObject("stateList", stateList);

                City city = cityService.readById(user.getCity());
                mv.addObject("stateIdEstablishment", city.getState().getId());
            }

            mv.addObject("profile", profile);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/editProfileEstablishment", method = RequestMethod.POST)
    public ModelAndView updateProfile(String businessName, String name, String secondEmail,
            String addressLocation, String neighborhood, Integer numberHouse, Long cityId,
            String zipCode, String telephone, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("profile/my_establishment_profile");
        String profileValue = "page_initial";
        Map<String, String> profile = new HashMap<>();

        profile.put("profileValue", profileValue);
        mv.addObject("profile", profile);
        Object auxSession = session.getAttribute("userLogged");
        User user = null;

        try {
            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;

                EstablishmentService service = new EstablishmentService();
                Establishment establishment = new Establishment();

                establishment.setId(user.getId());
                establishment.setBusinessName(businessName);
                establishment.setName(name);
                establishment.setSecondEmail(secondEmail);
                establishment.setAddressLocation(addressLocation);
                establishment.setNeighborhood(neighborhood);
                establishment.setNumberHouse(numberHouse);
                establishment.setCity(cityId);
                establishment.setZipCode(zipCode);
                establishment.setTelephone(telephone);

                service.update(establishment);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/establishmentProductList", method = RequestMethod.GET)
    public ModelAndView getConsumerRegisterForm(Long limit, Long offset) throws Exception {
        ModelAndView mv = new ModelAndView("list/establishment_product_list");

        ProductService service = new ProductService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Product> productList = service.readByCriteria(criteria, limit, offset);
        mv.addObject("productList", productList);

        return mv;
    }

    @RequestMapping(value = "/product_establishment", method = RequestMethod.GET)
    public ModelAndView findAllEstablishmentProduct(HttpSession session, Boolean isPromotion) throws Exception {

        ModelAndView mv = null;
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/product_establishment");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                mv.addObject("establishment", establishment);

//                mv.addObject("productList", establishmentService.findAllProductByEstablishmentId(establishment.getId()));
                List<EstablishmentProduct> establishmentProductList = establishmentService.findAllEstablishmentProduct(establishment.getId());

                DecimalFormat df = new DecimalFormat("0.00");

                for (EstablishmentProduct establishmentProduct : establishmentProductList) {
                    establishmentProduct.setPriceConvert(df.format(establishmentProduct.getPrice()));
                }

                mv.addObject("establishmentProductList", establishmentProductList);
                mv.addObject("isPromotion", isPromotion);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/product_establishment", method = RequestMethod.POST)
    public ModelAndView updatePriceEstablishmentProduct(String priceProduct, Long establishmentProductId, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/product_establishment");

        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;
                EstablishmentService establishmentService = new EstablishmentService();

                Double price = Double.parseDouble(priceProduct.replace(".", "").replace(",", "."));

                EstablishmentProduct establishmentProduct = new EstablishmentProduct();
                establishmentProduct.setId(establishmentProductId);
                establishmentProduct.setPrice(price);

                establishmentService.updatePriceEstablishmentProduct(establishmentProduct);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/product_establishment-product", method = RequestMethod.GET)
    public ModelAndView findAllProduct(HttpSession session) throws Exception {

        ModelAndView mv = null;
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/product_list");

                user = (Establishment) auxSession;
                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                mv.addObject("productList", establishmentService.findAllProductByEstablishmentId(establishment.getId()));
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/product_establishment-product", method = RequestMethod.POST)
    public ModelAndView savePriceProduct(String priceProduct, Long productId, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/product_establishment-product");

        try {
            Object auxSession = session.getAttribute("userLogged");

            if (auxSession instanceof Establishment) {

                EstablishmentService establishmentService = new EstablishmentService();

                Establishment establishment = new Establishment();
                Product product = new Product();

                Double price = Double.parseDouble(priceProduct.replace(".", "").replace(",", "."));
                User user = (Establishment) auxSession;
                establishment = establishmentService.readByUserId(user.getId());
                product.setId(productId);

                EstablishmentProduct establishmentProduct = new EstablishmentProduct();
                establishmentProduct.setEstablishment(establishment);
                establishmentProduct.setProduct(product);
                establishmentProduct.setPrice(price);

                establishmentService.saveProductEstablishment(establishmentProduct);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/product_establishment-productDelete", method = RequestMethod.POST)
    public ModelAndView deleteEstablishmentProduct(Long productDeleteId, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/product_establishment");
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;
                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                PromotionService promotionService = new PromotionService();

                boolean isPromotion = promotionService.isProductInPromotion(establishment.getId(), productDeleteId);

                if (!isPromotion) {
                    establishmentService.deleteEstablishmentProduct(productDeleteId);
                    mv.addObject("isPromotion", isPromotion);
                } else {
                    mv.addObject("isPromotion", isPromotion);
                }
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/promotion", method = RequestMethod.GET)
    public ModelAndView findAllPromotion(HttpSession session) throws Exception {

        ModelAndView mv = null;
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/promotion");

                user = (Establishment) auxSession;
                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());
                PromotionEstablishmentProductService promotionEstablishmentProductService = new PromotionEstablishmentProductService();

                mv.addObject("promotionList", promotionEstablishmentProductService.findAllPromotionEstablishmentProduct(establishment.getId()));
                mv.addObject("currentDate", new java.util.Date().getTime());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/promotion", method = RequestMethod.POST)
    public ModelAndView savePromotion(String namePromotion, Date promotionStart, Date promotionFinal, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/promotion");
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;
                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                PromotionEstablishmentProductService promotionEstablishmentProductService = new PromotionEstablishmentProductService();
                PromotionEstablishmentProduct promotionEstablishmentProduct = new PromotionEstablishmentProduct();

                Promotion promotion = new Promotion();
                promotion.setName(namePromotion);
                promotion.setStartDate(promotionStart);
                promotion.setFinalDate(promotionFinal);

                EstablishmentProduct establishmentProduct = new EstablishmentProduct();
                establishmentProduct.setEstablishment(establishment);

                promotionEstablishmentProduct.setPromotion(promotion);
                promotionEstablishmentProduct.setEstablishmentProduct(establishmentProduct);

                promotionEstablishmentProductService.savePromotion(promotionEstablishmentProduct);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/promotion-product", method = RequestMethod.GET)
    public ModelAndView findAllEstablishmentProductForPromotion(Long establishmentId, Long promotionId, boolean isVisualizar, HttpSession session) throws Exception {

        ModelAndView mv = null;
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/promotion_product");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();

                List<EstablishmentProduct> establishmentProductList = new ArrayList<>();

                if (!isVisualizar) {
                    establishmentProductList = establishmentService.findAllEstablishmentProductForPromotion(establishmentId, promotionId);
                } else {
                    establishmentProductList = establishmentService.findAllEstablishmentProductInPromotion(establishmentId, promotionId);
                }

                DecimalFormat df = new DecimalFormat("0.00");

                for (EstablishmentProduct establishmentProduct : establishmentProductList) {
                    establishmentProduct.setPriceConvert(df.format(establishmentProduct.getPrice()));
                }

                mv.addObject("establishmentProductList", establishmentProductList);
                mv.addObject("establishmentId", establishmentId);
                mv.addObject("promotionId", promotionId);
                mv.addObject("isVisualizar", isVisualizar);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/promotion-product", method = RequestMethod.POST)
    public ModelAndView saveEstablishmentProductForPromotion(Long establishmentId, Long promotionId, Long establishmentProductId, String priceProduct, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:promotion-product?establishmentId=" + establishmentId + "&promotionId=" + promotionId);
//        ModelAndView mv = new ModelAndView("promotion-product");
        try {

            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                user = (Establishment) auxSession;

                PromotionEstablishmentProductService promotionEstablishmentProductService = new PromotionEstablishmentProductService();
                PromotionEstablishmentProduct promotionEstablishmentProduct = new PromotionEstablishmentProduct();

                EstablishmentProduct establishmentProduct = new EstablishmentProduct();
                establishmentProduct.setId(establishmentProductId);

                Promotion promotion = new Promotion();
                promotion.setId(promotionId);

                promotionEstablishmentProduct.setEstablishmentProduct(establishmentProduct);
                promotionEstablishmentProduct.setPromotion(promotion);
                promotionEstablishmentProduct.setPromotionPrice(Double.parseDouble(priceProduct.replace(".", "").replace(",", ".")));

                promotionEstablishmentProductService.create(promotionEstablishmentProduct);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return mv;
    }

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public ModelAndView findAllEstablishmentForQuotation(HttpSession session) {
        ModelAndView mv = null;
        try {
            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/quotation");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                List<Establishment> establishmentList = establishmentService.findAllEstablishmentForQuotation(establishment.getId());

                mv.addObject("establishment", establishment);
                mv.addObject("establishmentList", establishmentList);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    @RequestMapping(value = "/quotation", method = RequestMethod.POST)
    public ModelAndView findAllProductByCompetitor(Long competitorId, HttpSession session) {
        ModelAndView mv = null;
        try {
            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/quotation_result");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());

                List<EstablishmentProduct> establishmentProductList = establishmentService.findAllEstablishmentProduct(establishment.getId());
                if (establishmentProductList != null && !establishmentProductList.isEmpty()) {
                    establishment.setProductList(establishmentProductList);
                }

                Establishment competitor = establishmentService.readByEstablishmentId(competitorId);

                List<Long> productIdList = new ArrayList<>();
                for (EstablishmentProduct establishmentProduct : establishmentProductList) {
                    productIdList.add(establishmentProduct.getProduct().getId());
                }

                List<EstablishmentProduct> competitorList = establishmentService.findAllEstablishmentProductByCompetitorId(competitorId, productIdList);
                if (competitorList != null && !competitorList.isEmpty()) {
                    competitor.setProductList(competitorList);
                }

                List<Establishment> establishmentList = new ArrayList<>();
                establishmentList.add(establishment);
                establishmentList.add(competitor);

                mv.addObject("establishmentList", establishmentList);
                mv.addObject("establishmentProductList", establishmentProductList);
                mv.addObject("competitorList", competitorList);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }
    
    
    @RequestMapping(value = "/avaliation-report", method = RequestMethod.GET)
    public ModelAndView getAvaliationReport(HttpSession session) {
        ModelAndView mv = null;
        try {
            Object auxSession = session.getAttribute("userLogged");
            User user = null;

            if (auxSession instanceof Establishment) {

                mv = new ModelAndView("establishment/avaliation_report");

                user = (Establishment) auxSession;

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readByUserId(user.getId());
                
                AvaliationService avaliationService = new AvaliationService();
                
                Avaliation avaliation = avaliationService.findAvgAvaliation(establishment.getId());

                mv.addObject("establishment", establishment);
                mv.addObject("avaliation", avaliation);

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return mv;
    }

    //barra de pesquisa
//    @RequestMapping(value = "/product_establishment-product-search", method = RequestMethod.POST)
//    public ModelAndView searchProduct(HttpSession session) throws Exception {
//
//        ModelAndView mv = new ModelAndView("redirect:/product_establishment-product");
//
//        try {
//            Object auxSession = session.getAttribute("userLogged");
//
//            if (auxSession instanceof Establishment) {
//
//                EstablishmentService establishmentService = new EstablishmentService();
//
//                Establishment establishment = new Establishment();
//                Product product = new Product();
//
//                Double price = Double.parseDouble(priceProduct.replace(".", "").replace(",", "."));
//                User user = (Establishment) auxSession;
//                establishment = establishmentService.readByUserId(user.getId());
//                product.setId(productId);
//
//                EstablishmentProduct establishmentProduct = new EstablishmentProduct();
//                establishmentProduct.setEstablishment(establishment);
//                establishmentProduct.setProduct(product);
//                establishmentProduct.setPrice(price);
//
//                establishmentService.saveProductEstablishment(establishmentProduct);
//            }
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
//
//        return mv;
//    }
}
