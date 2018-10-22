package ezmart.model.entity;

import ezmart.model.base.BaseEntity;

public class PromotionEstablishmentProduct extends BaseEntity{
    
    private EstablishmentProduct establishmentProduct;
    
    private Promotion promotion;
    
    private Double promotionPrice;

    public EstablishmentProduct getEstablishmentProduct() {
        return establishmentProduct;
    }

    public void setEstablishmentProduct(EstablishmentProduct establishmentProduct) {
        this.establishmentProduct = establishmentProduct;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
