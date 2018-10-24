package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Avaliation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        statement.setDate(++i, entity.getDateAvaliation());

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

    public Avaliation findAvgAvaliation(Connection conn, Long establishmentId) throws Exception {
        String sql = "select\n"
                + "	avg( avaliation_satisfaction ) as satisfaction,\n"
                + "	avg( avaliation_productprice ) as product,\n"
                + "	avg( avaliation_diversity ) as diversity,\n"
                + "	avg( avaliation_employees ) as employees,\n"
                + "	avg( avaliation_ambience ) as ambience\n"
                + "from\n"
                + "	avaliation\n"
                + "where\n"
                + "	avaliation_establishmentid = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, establishmentId);
        ResultSet rs = statement.executeQuery();

        Avaliation avaliation = new Avaliation();
        if (rs.next()) {
            
            avaliation.setSatisfactionConvert(rs.getDouble("satisfaction"));
            avaliation.setProductPriceConvert(rs.getDouble("product"));
            avaliation.setDiversityConvert(rs.getDouble("diversity"));
            avaliation.setEmployeesConvert(rs.getDouble("employees"));
            avaliation.setAmbienceConvert(rs.getDouble("ambience"));
        }
        rs.close();
        statement.close();

        return avaliation;
    }

}
