package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Sector;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SectorDAO implements BaseDAO<Sector> {

    @Override
    public void create(Connection conn, Sector entity) throws Exception {
        String sql = " INSERT INTO sector (sector_name) VALUES(?); ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getName());

        statement.execute();

        statement.close();
    }

    @Override
    public Sector readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sector> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Sector entity) throws Exception {
        String sql = " UPDATE sector set sector_name = ? WHERE sector_id = ?; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getName());
        statement.setLong(++i, entity.getId());

        statement.execute();

        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = " DELETE FROM sector WHERE sector_id = ?; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, id);

        statement.execute();

        statement.close();
    }

    public List<Sector> findAll(Connection conn, Integer limit, Integer offset) throws Exception {
        String sql = "SELECT * FROM sector WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (limit != null) {
            sql += " LIMIT ?";
            paramList.add(limit);
        }
        if (offset != null) {
            sql += " OFFSET ?";
            paramList.add(offset);
        }

        PreparedStatement statement = PreparedStatementBuilder.build(conn, sql, paramList);
        ResultSet rs = statement.executeQuery();
        List<Sector> sectorList = new ArrayList<>();
        while (rs.next()) {
            Sector sector = new Sector();

            sector.setId(rs.getLong("sector_id"));
            sector.setName(rs.getString("sector_name"));
            sectorList.add(sector);
        }

        rs.close();
        statement.close();
        return sectorList;
    }

}
