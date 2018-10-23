package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseProductService;
import ezmart.model.base.service.BasePromotionService;
import ezmart.model.dao.PromotionDAO;
import ezmart.model.entity.Product;
import ezmart.model.entity.Promotion;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PromotionService implements BasePromotionService{

    @Override
    public void create(Promotion entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
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
    public Promotion readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
            Promotion promotion = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return promotion;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Promotion> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
            List<Promotion> list = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return list;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        } 
    }

    @Override
    public void update(Promotion entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public boolean isProductInPromotion(Long establishmentId, Long establishmentProductId) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PromotionDAO dao = new PromotionDAO();
            boolean isPromotion = dao.isProductInPromotion(conn, establishmentId, establishmentProductId);
            conn.commit();
            conn.close();
            return isPromotion;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
