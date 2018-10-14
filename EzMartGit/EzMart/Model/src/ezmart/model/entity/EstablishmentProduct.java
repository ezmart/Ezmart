package ezmart.model.entity;

import ezmart.model.base.BaseEntity;
import java.util.Date;

public class EstablishmentProduct extends BaseEntity {
    
    private Establishment establishment;
    
    private Product product;
    
    private Double price;
    
    private String priceConvert;
    
    private Date dateAlteration;

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateAlteration() {
        return dateAlteration;
    }

    public void setDateAlteration(Date dateAlteration) {
        this.dateAlteration = dateAlteration;
    }

    public String getPriceConvert() {
        return priceConvert;
    }

    public void setPriceConvert(String priceConvert) {
        this.priceConvert = priceConvert;
    }
    
}
