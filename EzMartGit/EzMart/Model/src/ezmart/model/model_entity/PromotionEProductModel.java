package ezmart.model.model_entity;

import ezmart.model.base.BaseEntity;

public class PromotionEProductModel extends BaseEntity {

    private Long promationPromotionId;
    private Long establishmentProductId;
    private Double promotionalPrice;

    public Long getPromationPromotionId() {
        return promationPromotionId;
    }

    public void setPromationPromotionId(Long promationPromotionId) {
        this.promationPromotionId = promationPromotionId;
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
