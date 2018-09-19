package ezmart.admin.controller;

import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.Product;
import ezmart.model.entity.Provider;
import ezmart.model.entity.Sector;
import ezmart.model.entity.State;
import ezmart.model.entity.UserSystem;
import ezmart.model.service.CityService;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.ProviderService;
import ezmart.model.service.SectorService;
import ezmart.model.service.StateService;
import ezmart.model.service.UserService;
import ezmart.model.util.SystemConstant.PAGE;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    SectorService sectorService = new SectorService();
    ProviderService providerService = new ProviderService();
    EstablishmentService establishmentService = new EstablishmentService();
    ProductService productService = new ProductService();
    UserService userService = new UserService();
    CityService cityService = new CityService();
    StateService stateService = new StateService();
    ConsumerService consumerService = new ConsumerService();

    @RequestMapping(value = "/sector", method = RequestMethod.GET)
    public ModelAndView findAllSector() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("admin/sector");
            List<Sector> sectorList = new ArrayList<>();
            sectorList = sectorService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null);
            mv.addObject("sectorList", sectorList);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/sector", method = RequestMethod.POST)
    public ModelAndView saveSector(String nameSector) {
        ModelAndView mv = new ModelAndView("redirect:/sector");

        try {

            Sector sector = new Sector();
            sector.setName(nameSector);
            sectorService.create(sector);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/sectorEdit", method = RequestMethod.POST)
    public ModelAndView editSector(Long sectorId, String sectorNameEdit) {
        ModelAndView mv = new ModelAndView("redirect:/sector");

        try {

            Sector sector = new Sector();
            sector.setId(sectorId);
            sector.setName(sectorNameEdit);
            sectorService.update(sector);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/sectorDelete", method = RequestMethod.POST)
    public ModelAndView deleteSector(Long sectorId) {
        ModelAndView mv = new ModelAndView("redirect:/sector");

        try {
            sectorService.delete(sectorId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public ModelAndView findAllProvider() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("admin/provider");
            List<Provider> providerList = new ArrayList<>();
            providerList = providerService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null);
            mv.addObject("providerList", providerList);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/provider", method = RequestMethod.POST)
    public ModelAndView saveProvider(String cnpjProvider, String nameProvider, String businessNameProvider) {
        ModelAndView mv = new ModelAndView("redirect:/provider");
        try {

            Provider provider = new Provider();
            provider.setCnpj(cnpjProvider);
            provider.setName(nameProvider);
            provider.setBusinessName(businessNameProvider);
            providerService.create(provider);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/providerEdit", method = RequestMethod.POST)
    public ModelAndView editProvider(Long providerId, String providerCnpjEdit, String providerNameEdit, String providerBusinessNameEdit) {
        ModelAndView mv = new ModelAndView("redirect:/provider");

        try {

            Provider provider = new Provider();
            provider.setId(providerId);
            provider.setCnpj(providerCnpjEdit);
            provider.setName(providerNameEdit);
            provider.setBusinessName(providerBusinessNameEdit);
            providerService.update(provider);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/providerDelete", method = RequestMethod.POST)
    public ModelAndView deleteProvider(Long providerId) {
        ModelAndView mv = new ModelAndView("redirect:/provider");

        try {
            providerService.delete(providerId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView findAllProduct() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("admin/product");
            mv.addObject("productList", productService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null));
            mv.addObject("sectorList", sectorService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null));
            mv.addObject("providerList", providerService.findAll(Integer.parseInt(PAGE.SIZE.LIMIT), null));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ModelAndView saveProduct(Long sectorIdProduct, Long providerIdProduct, String barCode, String nameProduct, MultipartFile productImage, String brandProduct) {
        ModelAndView mv = new ModelAndView("redirect:/product");
        try {

            Sector sector = new Sector();
            sector.setId(sectorIdProduct);

            Provider provider = new Provider();
            provider.setId(providerIdProduct);

            Product product = new Product();
            product.setSector(sector);
            product.setProvider(provider);
            product.setBarCode(barCode);
            product.setName(nameProduct);
            product.setImage(productImage.getBytes());
            product.setBrand(brandProduct);

            Long productId = productService.createWithReturn(product);

            if (productImage.getBytes() != null) {
                this.saveImageProduct(productImage.getBytes(), productId.toString());
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/productEdit", method = RequestMethod.POST)
    public ModelAndView editProduct(Long productIdEdit, Long sectorIdProductEdit, Long providerIdProductEdit, String productBarCodeEdit, String productNameEdit, String productBrandEdit) {
        ModelAndView mv = new ModelAndView("redirect:/product");

        try {

            Sector sector = new Sector();
            sector.setId(sectorIdProductEdit);

            Provider provider = new Provider();
            provider.setId(providerIdProductEdit);

            Product product = new Product();
            product.setSector(sector);
            product.setProvider(provider);
            product.setId(productIdEdit);
            product.setBarCode(productBarCodeEdit);
            product.setName(productNameEdit);
            product.setBrand(productBrandEdit);
            productService.update(product);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/productDelete", method = RequestMethod.POST)
    public ModelAndView deleteProduct(Long productId) {
        ModelAndView mv = new ModelAndView("redirect:/product");

        try {
            productService.delete(productId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/product/uploadImage", method = RequestMethod.POST)
    public ModelAndView uploadImage(MultipartFile productImage, Long idProductForImage) {
        ModelAndView mv = new ModelAndView("redirect:/product");

        try {
            byte[] imgBytes = productImage.getBytes();
            productService.uploadImage(imgBytes, idProductForImage);
            this.deleteImageProduct(idProductForImage.toString());
            this.saveImageProduct(imgBytes, idProductForImage.toString());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    private void saveImageProduct(byte[] productImage, String imageName) {
        try {
//            File outPutFile = new File("../EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/product/"+imageName+".jpg");

//            String path = getClass().getResource("../product/").toString() + imageName + ".jpg";
            String path = "c:/Users/marko/Desktop/EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/product/" + imageName + ".jpg";

            File outPutFile = new File(path);

            InputStream inputStream = new ByteArrayInputStream(productImage);
            BufferedImage imagem = ImageIO.read(inputStream);
            if (imagem != null) {
                ImageIO.write(imagem, "jpg", outPutFile);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteImageProduct(String imageName) {
        try {

            String path = "c:/Users/marko/Desktop/EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/product/" + imageName + ".jpg";

            File file = new File(path);
            file.delete();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView findAllUser() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("admin/user");
            mv.addObject("userList", userService.findAll(null, Integer.parseInt(PAGE.SIZE.LIMIT)));
            mv.addObject("cityList", cityService.findAll(null, Integer.parseInt(PAGE.SIZE.LIMIT)));
            mv.addObject("stateList", stateService.findAll(null, Integer.parseInt(PAGE.SIZE.LIMIT)));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView saveUser(String companyName, String userType, String businessName, String cnpj, String email,
            String secondEmail, String password, String addressLocation, Integer numberHouse, String neighborhood,
            Long stateId, Long cityId, String zipCode, String telephone, Boolean activeUser, Boolean admin, String name,
            String lastName, Integer plan, Date planStart, Date planFinish, String cpf, MultipartFile userImage) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/user");
        try {

            if (admin != null) {
                if (admin) {
                    userType = UserSystem.TIPO_ADMIN;
                } else {
                    userType = UserSystem.TIPO_CONSUMER;
                }
            }

            City city = new City();
            city.setId(cityId);

            State state = new State();
            state.setId(stateId);

            UserSystem user = new UserSystem();

            user.setActive(activeUser);
            user.setEmail(email);
            user.setNeighborhood(neighborhood);
            user.setTelephone(telephone);
            user.setPassword(password);
            user.setNumberHouse(numberHouse);
            user.setAddressLocation(addressLocation);
            user.setZipCode(zipCode);
            user.setUserType(userType);
            user.setCity(city);
            user.setState(state);
            user.setImage(userImage.getBytes());

            if (userType.equals(UserSystem.TIPO_EMPORIUM)) {
                Establishment establishment = new Establishment();
                establishment.setBusinessName(businessName);
                establishment.setCnpj(cnpj);
                establishment.setSecondEmail(secondEmail);
                establishment.setName(companyName);
                establishment.setPlan(plan);
                establishment.setPlanStartDate(planStart);
                establishment.setPlanFinalDate(planFinish);
                user.setEstablishment(establishment);
            } else {
                Consumer consumer = new Consumer();

                consumer.setName(name);
                consumer.setLastName(lastName);
                consumer.setCpf(cpf);

                user.setConsumer(consumer);
            }

            user = userService.createUserSystem(user);

            if (userImage.getBytes() != null) {
                this.saveImageUser(userImage.getBytes(), user.getId().toString());
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public ModelAndView editUser(Long userIdEdit, String nameEdit, String lastNameEdit, String cpfEdit, String emailEdit,
            String addressLocationEdit, Integer numberHouseEdit, String neighborhoodEdit, Long stateIdEdit,
            Long cityIdEdit, String zipCodeEdit, String telephoneEdit, Boolean adminEdit, Boolean activeUserEdit) {

        ModelAndView mv = new ModelAndView("redirect:/user");

        try {

            String userType = null;
            if (adminEdit != null) {
                if (adminEdit) {
                    userType = UserSystem.TIPO_ADMIN;
                } else {
                    userType = UserSystem.TIPO_CONSUMER;
                }
            }

            City city = new City();
            city.setId(cityIdEdit);

            State state = new State();
            state.setId(stateIdEdit);

            UserSystem user = new UserSystem();

            user.setId(userIdEdit);
            user.setActive(activeUserEdit);
            user.setEmail(emailEdit);
            user.setNeighborhood(neighborhoodEdit);
            user.setTelephone(telephoneEdit);
            user.setNumberHouse(numberHouseEdit);
            user.setAddressLocation(addressLocationEdit);
            user.setZipCode(zipCodeEdit);
            user.setUserType(userType);
            user.setCity(city);
            user.setState(state);

            Consumer consumer = new Consumer();

            consumer.setName(nameEdit);
            consumer.setLastName(lastNameEdit);
            consumer.setCpf(cpfEdit);

            user.setConsumer(consumer);

            userService.updateUserSystem(user);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/userEstEdit", method = RequestMethod.POST)
    public ModelAndView userEstEdit(Long userIdEstEdit, String companyNameEdit, String businessNameEdit, String cnpjEdit,
            String emailEstEdit, String secondEmailEdit, String addressLocationEstEdit, Integer numberHouseEstEdit,
            String neighborhoodEstEdit, Long stateIdEstEdit, Long cityIdEstEdit, String zipCodeEstEdit,
            String telephoneEstEdit, Integer planEdit, Date planStartEdit, Date planFinishEdit, Boolean activeUserEstEdit) {

        ModelAndView mv = new ModelAndView("redirect:/user");

        try {

            String userType = UserSystem.TIPO_EMPORIUM;

            City city = new City();
            city.setId(cityIdEstEdit);

            State state = new State();
            state.setId(stateIdEstEdit);

            UserSystem user = new UserSystem();

            user.setId(userIdEstEdit);
            user.setActive(activeUserEstEdit);
            user.setEmail(emailEstEdit);
            user.setNeighborhood(neighborhoodEstEdit);
            user.setTelephone(telephoneEstEdit);
            user.setNumberHouse(numberHouseEstEdit);
            user.setAddressLocation(addressLocationEstEdit);
            user.setZipCode(zipCodeEstEdit);
            user.setUserType(userType);
            user.setCity(city);
            user.setState(state);

            Establishment establishment = new Establishment();
            establishment.setBusinessName(businessNameEdit);
            establishment.setCnpj(cnpjEdit);
            establishment.setSecondEmail(secondEmailEdit);
            establishment.setName(companyNameEdit);
            establishment.setPlan(planEdit);
            establishment.setPlanStartDate(planStartEdit);
            establishment.setPlanFinalDate(planFinishEdit);

            user.setEstablishment(establishment);
            
            userService.updateUserSystem(user);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/userDelete", method = RequestMethod.POST)
    public ModelAndView deleteUser(Long userId
    ) {
        ModelAndView mv = new ModelAndView("redirect:/user");

        try {

            Consumer consumer = consumerService.readByUserId(userId);
            Establishment establishment = establishmentService.readByUserId(userId);

            UserSystem user = new UserSystem();
            user.setConsumer(consumer);
            user.setEstablishment(establishment);
            user.setId(userId);
            userService.deleteRelationship(user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/user/uploadImage", method = RequestMethod.POST)
    public ModelAndView uploadImageUser(MultipartFile userImage, Long idUserForImage
    ) {
        ModelAndView mv = new ModelAndView("redirect:/user");

        try {
            byte[] imgBytes = userImage.getBytes();
            this.deleteImageUser(idUserForImage.toString());
            this.saveImageUser(imgBytes, idUserForImage.toString());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    private void saveImageUser(byte[] productImage, String imageName) {
        try {
//            File outPutFile = new File("../EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/product/"+imageName+".jpg");

//            String path = getClass().getResource("../product/").toString() + imageName + ".jpg";
            String path = "c:/Users/marko/Desktop/EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/user/" + imageName + ".jpg";

            File outPutFile = new File(path);

            InputStream inputStream = new ByteArrayInputStream(productImage);
            BufferedImage imagem = ImageIO.read(inputStream);
            if (imagem != null) {
                ImageIO.write(imagem, "jpg", outPutFile);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteImageUser(String imageName) {
        try {

            String path = "c:/Users/marko/Desktop/EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/user/" + imageName + ".jpg";

            File file = new File(path);
            file.delete();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
