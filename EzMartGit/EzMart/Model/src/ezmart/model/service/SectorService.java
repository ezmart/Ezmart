package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseSectorService;
import ezmart.model.dao.SectorDAO;
import ezmart.model.entity.Sector;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SectorService implements BaseSectorService {

    SectorDAO dao = new SectorDAO();

    @Override
    public void create(Sector entity) throws Exception {
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
    public Sector readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sector> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Sector> productList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(Sector entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public List<Sector> findAll(Integer limit, Integer offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Sector> productList = dao.findAll(conn, limit, offset);
            conn.commit();
            conn.close();
            return productList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

}
