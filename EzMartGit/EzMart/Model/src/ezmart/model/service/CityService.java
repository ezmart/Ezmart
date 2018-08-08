package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseCityService;
import ezmart.model.dao.CityDAO;
import ezmart.model.entity.City;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class CityService implements BaseCityService {

    @Override
    public void create(City entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CityDAO dao = new CityDAO();
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
    public City readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CityDAO dao = new CityDAO();
            City city = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return city;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<City> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CityDAO dao = new CityDAO();
            List<City> cityList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return cityList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }

    }

    @Override
    public void update(City entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CityDAO dao = new CityDAO();
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
            CityDAO dao = new CityDAO();
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
