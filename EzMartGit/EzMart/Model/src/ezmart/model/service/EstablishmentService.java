package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.dao.EstablishmentDAO;
import ezmart.model.dao.UserDAO;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import ezmart.model.base.service.BaseEstablishmentService;

public class EstablishmentService implements BaseEstablishmentService {

    @Override
    public void create(Establishment entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            entity.setUserType("emporium");
            UserDAO userDao = new UserDAO();
            userDao.create(conn, entity);

            EstablishmentDAO dao = new EstablishmentDAO();
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
    public Establishment readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            Establishment emporium = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return emporium;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Establishment> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            List<Establishment> emporiumList = dao.readByCriteria(conn, criteria, 0L, 0L);
            conn.commit();
            conn.close();
            return emporiumList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Establishment entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO usuarioDao = new UserDAO();
            usuarioDao.update(conn, entity);
            EstablishmentDAO dao = new EstablishmentDAO();
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
            EstablishmentDAO dao = new EstablishmentDAO();
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

    public void readById(User user) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            dao.readById(conn, user);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public Establishment readByUserId(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            Establishment establishment = dao.readByUserId(conn, id);
            conn.commit();
            conn.close();
            return establishment;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
