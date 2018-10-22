package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class PromotionDAO implements BaseDAO<Promotion> {

    @Override
    public void create(Connection conn, Promotion entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from promotion WHERE promotion_id=?;";
        Promotion promotion = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                promotion = new Promotion();
                promotion.setId(resultSet.getLong("promotion_id"));
                promotion.setName(resultSet.getString("promotion_name"));
                promotion.setStartDate(resultSet.getDate("promotion_startdate"));
                promotion.setFinalDate(resultSet.getDate("promotion_finaldate"));
            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return promotion;
    }

    @Override
    public List<Promotion> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Promotion entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
