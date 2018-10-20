package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseAvaliationService;
import ezmart.model.dao.AvaliationDAO;
import ezmart.model.entity.Avaliation;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AvaliationService implements BaseAvaliationService{

    @Override
    public void create(Avaliation entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AvaliationDAO dao = new AvaliationDAO();
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
    public Avaliation readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliation> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Avaliation entity) throws Exception {
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
    
}
