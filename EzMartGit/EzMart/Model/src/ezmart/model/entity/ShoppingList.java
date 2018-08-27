package ezmart.model.entity;

import ezmart.model.base.BaseEntity;
import java.sql.Date;
import java.util.List;

public class ShoppingList extends BaseEntity{
    private String name;
    private Long consumerId;
    private Boolean faborite;
    private Date date;
    private List<Product> productList;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Boolean getFaborite() {
        return faborite;
    }

    public void setFaborite(Boolean faborite) {
        this.faborite = faborite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
