package ezmart.admin.controller;

import ezmart.model.entity.Product;
import ezmart.model.entity.Provider;
import ezmart.model.entity.Sector;
import ezmart.model.service.EstablishmentService;
import ezmart.model.service.ProductService;
import ezmart.model.service.ProviderService;
import ezmart.model.service.SectorService;
import ezmart.model.util.SystemConstant.PAGE;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/linha", method = RequestMethod.GET)
    public ModelAndView findAllSector(HttpSession session) {
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

    @RequestMapping(value = "/linha", method = RequestMethod.POST)
    public ModelAndView saveSector(String nameSector) {
        ModelAndView mv = new ModelAndView("redirect:/linha");

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
        ModelAndView mv = new ModelAndView("redirect:/linha");

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
        ModelAndView mv = new ModelAndView("redirect:/linha");

        try {
            sectorService.delete(sectorId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/fornecedor", method = RequestMethod.GET)
    public ModelAndView findAllProvider(HttpSession session) {
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

    @RequestMapping(value = "/fornecedor", method = RequestMethod.POST)
    public ModelAndView saveProvider(String cnpjProvider, String nameProvider, String businessNameProvider) {
        ModelAndView mv = new ModelAndView("redirect:/fornecedor");
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
        ModelAndView mv = new ModelAndView("redirect:/fornecedor");

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
        ModelAndView mv = new ModelAndView("redirect:/fornecedor");

        try {
            providerService.delete(providerId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/produto", method = RequestMethod.GET)
    public ModelAndView findAllProduct(HttpSession session) {
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

    @RequestMapping(value = "/produto", method = RequestMethod.POST)
    public ModelAndView saveProduct(Long sectorIdProduct, Long providerIdProduct, String barCode, String nameProduct, MultipartFile productImage, String brandProduct) {
        ModelAndView mv = new ModelAndView("redirect:/produto");
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
        ModelAndView mv = new ModelAndView("redirect:/produto");

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
        ModelAndView mv = new ModelAndView("redirect:/produto");

        try {
            productService.delete(productId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/product/uploadImage", method = RequestMethod.POST)
    public ModelAndView uploadImage(MultipartFile productImage, Long idProductForImage) {
        ModelAndView mv = new ModelAndView("redirect:/produto");

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

    public void saveImageProduct(byte[] productImage, String imageName) {
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

    public void deleteImageProduct(String imageName) {
        try {

            String path = "c:/Users/marko/Desktop/EzMartGit2018/Ezmart/EzMartGit/EzMart/Web/web/resources/img/product/" + imageName + ".jpg";

            File file = new File(path);
            file.delete();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
