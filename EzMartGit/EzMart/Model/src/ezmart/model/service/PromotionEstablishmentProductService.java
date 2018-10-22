package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BasePromotionEstablishmentProductService;
import ezmart.model.dao.PromotionEstablishmentProductDAO;
import ezmart.model.entity.PromotionEstablishmentProduct;
import ezmart.model.model_entity.PromotionEProductModel;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PromotionEstablishmentProductService implements BasePromotionEstablishmentProductService {

    @Override
    public void create(PromotionEstablishmentProduct entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionEstablishmentProductDAO dao = new PromotionEstablishmentProductDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public PromotionEstablishmentProduct readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PromotionEstablishmentProduct> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PromotionEstablishmentProduct entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<PromotionEProductModel> findAllPromotionEstablishmentProductByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionEstablishmentProductDAO dao = new PromotionEstablishmentProductDAO();
            List<PromotionEProductModel> promotionEProductModelList = dao.findAllPromotionEstablishmentProductByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return promotionEProductModelList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public List<PromotionEstablishmentProduct> findAllPromotionEstablishmentProduct(Long establishmentId) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionEstablishmentProductDAO dao = new PromotionEstablishmentProductDAO();
            List<PromotionEstablishmentProduct> promotionEstablishmentProductList = dao.findAllPromotionEstablishmentProduct(conn, establishmentId);
            conn.commit();
            conn.close();
            return promotionEstablishmentProductList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public void savePromotion(PromotionEstablishmentProduct promotionEstablishmentProduct) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionEstablishmentProductDAO dao = new PromotionEstablishmentProductDAO();
            dao.savePromotion(conn, promotionEstablishmentProduct);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
