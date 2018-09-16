package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Provider;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProviderDAO implements BaseDAO<Provider> {

    @Override
    public void create(Connection conn, Provider entity) throws Exception {
        String sql = " INSERT INTO provider (provider_cnpj, provider_name, provider_businessname) VALUES(?,?,?); ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getBusinessName());

        statement.execute();

        statement.close();
    }

    @Override
    public Provider readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Provider entity) throws Exception {
        String sql = " UPDATE provider set provider_cnpj = ?, provider_name = ?, provider_businessname = ?"
                + " WHERE provider_id = ?; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getBusinessName());
        statement.setLong(++i, entity.getId());

        statement.execute();

        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = " DELETE FROM provider WHERE provider_id = ?; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, id);

        statement.execute();

        statement.close();
    }

    public List<Provider> findAll(Connection conn, Integer limit, Integer offset) throws Exception {
        String sql = "SELECT * FROM provider ORDER BY provider_name ";

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
        List<Provider> providerList = new ArrayList<>();
        while (rs.next()) {
            Provider provider = new Provider();

            provider.setId(rs.getLong("provider_id"));
            provider.setCnpj(rs.getString("provider_cnpj"));
            provider.setName(rs.getString("provider_name"));
            provider.setBusinessName(rs.getString("provider_businessname"));
            providerList.add(provider);
        }

        rs.close();
        statement.close();
        return providerList;
    }

}
