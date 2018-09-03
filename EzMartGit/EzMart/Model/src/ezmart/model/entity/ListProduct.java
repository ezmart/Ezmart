package ezmart.model.entity;

import ezmart.model.base.BaseEntity;

public class ListProduct extends BaseEntity {

    private Long listId;
    private Long produtcId;
    private Integer quantity;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getProdutcId() {
        return produtcId;
    }

    public void setProdutcId(Long produtcId) {
        this.produtcId = produtcId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer qauntity) {
        this.quantity = qauntity;
    }
}
