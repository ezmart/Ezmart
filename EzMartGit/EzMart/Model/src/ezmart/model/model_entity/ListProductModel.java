package ezmart.model.model_entity;

import ezmart.model.base.BaseEntity;

public class ListProductModel extends BaseEntity {

    private Long listId;
    private String productName;
    private Integer quantity;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer qauntity) {
        this.quantity = qauntity;
    }
}
