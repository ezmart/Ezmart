package ezmart.model.model_entity;

import ezmart.model.base.BaseEntity;

public class PromotionEProductModel extends BaseEntity {

    private Long promotionId;
    private Long establishmentProductId;
    private Double promotionalPrice;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getEstablishmentProductId() {
        return establishmentProductId;
    }

    public void setEstablishmentProductId(Long establishmentProductId) {
        this.establishmentProductId = establishmentProductId;
    }

    public Double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(Double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

}
