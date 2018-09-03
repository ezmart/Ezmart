package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseProviderService;
import ezmart.model.dao.ProviderDAO;
import ezmart.model.entity.Provider;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ProviderService implements BaseProviderService {

    ProviderDAO dao = new ProviderDAO();

    @Override
    public List<Provider> findAll(Integer limit, Integer offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Provider> providerList = dao.findAll(conn, limit, offset);
            conn.commit();
            conn.close();
            return providerList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void create(Provider entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
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
    public Provider readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Provider entity) throws Exception {
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

}
