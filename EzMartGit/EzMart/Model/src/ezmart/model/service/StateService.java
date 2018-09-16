package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseStateService;
import ezmart.model.dao.StateDAO;
import ezmart.model.entity.State;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class StateService implements BaseStateService {

    @Override
    public void create(State entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            StateDAO dao = new StateDAO();
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
    public State readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            StateDAO dao = new StateDAO();
            State state = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return state;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<State> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            StateDAO dao = new StateDAO();
            List<State> stateList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return stateList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(State entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            StateDAO dao = new StateDAO();
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
            StateDAO dao = new StateDAO();
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
    
    public List<State> findAll(Integer offset, Integer limit) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            StateDAO dao = new StateDAO();
            List<State> stateList = dao.findAll(conn, offset, limit);
            conn.commit();
            conn.close();
            return stateList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
