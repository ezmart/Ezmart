package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseProductService;
import ezmart.model.dao.ProductDAO;
import ezmart.model.entity.Product;
import ezmart.model.model_entity.ProductModel;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ProductService implements BaseProductService {

    ProductDAO dao = new ProductDAO();

    public Long createWithReturn(Product entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Long productId = dao.createWithReturn(conn, entity);
            conn.commit();
            conn.close();
            return productId;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Product readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Product product = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return product;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Product> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Product> productList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return productList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Product entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
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
            dao.delete(conn, id);
            conn.commit();
            conn.close();
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

    public List<Product> findAll(Integer offset, Integer limit) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Product> productList = dao.findAll(conn, offset, limit);
            conn.commit();
            conn.close();
            return productList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public List<ProductModel> findAllProductModel(Integer offset, Integer limit) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<ProductModel> productList = dao.findAllProductModel(conn, offset, limit);
            conn.commit();
            conn.close();
            return productList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public void uploadImage(byte[] productImage, Long idProduct) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            dao.uploadImage(conn, productImage, idProduct);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public Integer countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Integer count = 0;
        try {
            count = dao.countByCriteria(conn, criteria);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return count;
    }

    @Override
    public void create(Product entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
