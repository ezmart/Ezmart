package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseListProductService;
import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.dao.ListProductDAO;
import ezmart.model.entity.ListProduct;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.util.SystemConstant;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListProductService implements BaseListProductService {

    @Override
    public void create(ListProduct entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
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
    public ListProduct readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
            ListProduct listProduct = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return listProduct;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<ListProduct> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
            List<ListProduct> productList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return productList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public List<ListProductModel> readByCriteriaModel(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
            List<ListProductModel> productList = dao.readByCriteriaModel(conn, criteria, limit, offset);
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
    public void update(ListProduct entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
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
            ListProductDAO dao = new ListProductDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public void deleteFromList(Long listId, Long productId) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ListProductDAO dao = new ListProductDAO();
            dao.deleteFromList(conn, listId, productId);
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

        Map<String, String> errors = new HashMap<>();

        String validationType = (String) fields.get("validationType");
        Long listId = (Long) fields.get("listId");
        Long productId = (Long) fields.get("productId");

        if (validationType.equals((SystemConstant.VALIDATION.PRODUCT.ADD_PRODUCT_LIST))) {
            //Validação de preenchimento
            if (listId == null || productId == null) {
                errors.put("error", "*Campo vazio!");
            } else {
                ListProductService listProductService = new ListProductService();
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(ListProductCriteria.LIST_ID_EQ, listId);
                criteria.put(ListProductCriteria.PRODUCT_ID_EQ, productId);

                List<ListProductModel> productsList = listProductService.readByCriteriaModel(criteria, null, null);
                if(productsList.size() > 0 ){
                    errors.put("error", "*Produto já cadastrado nesta lista!");
                }
            }

        }
        return errors;
    }

}
