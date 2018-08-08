package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseProductService;
import ezmart.model.dao.ProductDAO;
import ezmart.model.entity.Product;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ProductService implements BaseProductService {

    @Override
    public void create(Product entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ProductDAO dao = new ProductDAO();
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
    public Product readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ProductDAO dao = new ProductDAO();
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
            ProductDAO dao = new ProductDAO();
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
            ProductDAO dao = new ProductDAO();
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
            ProductDAO dao = new ProductDAO();
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

}
