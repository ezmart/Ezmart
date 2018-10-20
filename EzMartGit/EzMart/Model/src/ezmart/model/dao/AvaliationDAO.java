package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Avaliation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class AvaliationDAO implements BaseDAO<Avaliation> {

    @Override
    public void create(Connection conn, Avaliation entity) throws Exception {
        
        String sql = "INSERT INTO avaliation(avaliation_satisfaction, avaliation_productprice, avaliation_diversity, "
                + "avaliation_employees, avaliation_ambience, avaliation_commentary, avaliation_consumerid, "
                + "avaliation_establishmentid, avaliation_date) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setInt(++i, entity.getSatisfaction());
        statement.setInt(++i, entity.getProductPrice());
        statement.setInt(++i, entity.getDiversity());
        statement.setInt(++i, entity.getEmployees());
        statement.setInt(++i, entity.getAmbience());
        statement.setString(++i, entity.getCommentary());
        statement.setLong(++i, entity.getConsumer().getId());
        statement.setLong(++i, entity.getEstablishment().getId());
        statement.setDate(++i, entity.getDateAvaliation(  ));

        statement.execute();

        statement.close();
    }

    @Override
    public Avaliation readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliation> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Avaliation entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
