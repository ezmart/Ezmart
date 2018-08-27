package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.dao.ShoppingListDAO;
import ezmart.model.entity.ShoppingList;
import java.sql.Connection;
import java.util.Map;
import java.util.List;
import ezmart.model.base.service.BaseShoppingListService;

public class ShoppingListService implements BaseShoppingListService {

    @Override
    public void create(ShoppingList entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ShoppingListDAO dao = new ShoppingListDAO();
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
    public ShoppingList readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ShoppingListDAO dao = new ShoppingListDAO();
            ShoppingList list = dao.readById(conn, id);
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
    public List<ShoppingList> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ShoppingListDAO dao = new ShoppingListDAO();
            List<ShoppingList> list = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(ShoppingList entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ShoppingListDAO dao = new ShoppingListDAO();
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
            ShoppingListDAO dao = new ShoppingListDAO();
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
