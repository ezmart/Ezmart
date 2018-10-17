package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.CityCriteria;
import ezmart.model.entity.City;
import ezmart.model.entity.State;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityDAO implements BaseDAO<City> {

    @Override
    public void create(Connection conn, City entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from city INNER JOIN state ON state_id=city_stateid "
                + "WHERE city_id=?;";
        City city = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getLong("city_id"));
                city.setCodeIbge(resultSet.getString("city_codeibge"));
                city.setName(resultSet.getString("city_name"));

                State state = new State();
                state.setId(resultSet.getLong("state_id"));
                state.setName(resultSet.getString("state_name"));
                state.setInitials(resultSet.getString("state_initials"));

                city.setState(state);
            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return city;
    }

    @Override
    public List<City> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * from city INNER JOIN state ON state_id=city_stateid "
                + "WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (criteria != null) {
//            if (criteria.containsKey(CityCriteria.STATE_ID_EQ)) {
//                String cityStateId = (String) criteria.get(CityCriteria.STATE_ID_EQ);
//                sql += " AND state_id ILIKE ?";
//                paramList.add("%" + nome + "%");
//            }

//            if (criteria.containsKey(CategoriaCriteria.NOME_EQ)) {
//                String nome = (String) criteria.get(CategoriaCriteria.NOME_EQ);
//                sql += " AND nome = ?";
//                paramList.add(nome);
//            }
//
//            if (criteria.containsKey(CategoriaCriteria.QUANTIDADE_PRODUTO_GT)) {
//                Integer quantidadeProdutos = (Integer) criteria.get(CategoriaCriteria.QUANTIDADE_PRODUTO_GT);
//                sql += " AND id IN(SELECT categoria_fk FROM produto GROUP BY categoria_fk HAVING count(*) > ?)";
//                paramList.add(quantidadeProdutos);
//            }
//
            if (criteria.containsKey(CityCriteria.STATE_ID_EQ)) {
                Long cityStateId = (Long) criteria.get(CityCriteria.STATE_ID_EQ);
                sql += " AND city_stateid = ?";
                paramList.add(cityStateId);
            }

        }

        sql += " ORDER BY city_name ASC ";

        if (limit != null) {
            sql += " LIMIT ?";
            paramList.add(limit);
        }

        if (offset != null) {
            sql += " OFFSET ?";
            paramList.add(offset);
        }

        PreparedStatement statement = PreparedStatementBuilder.build(conn, sql, paramList);

        ResultSet resultSet = statement.executeQuery();
        List<City> cityList = new ArrayList<>();
        while (resultSet.next()) {
            City city = new City();
            State state = new State();
            state.setId(resultSet.getLong("state_id"));
            state.setName(resultSet.getString("state_name"));
            state.setInitials(resultSet.getString("state_initials"));

            city.setState(state);
            city.setId(resultSet.getLong("city_id"));
            city.setName(resultSet.getString("city_name"));
            city.setCodeIbge(resultSet.getString("city_codeibge"));

            cityList.add(city);
        }
        resultSet.close();
        statement.close();
        return cityList;

    }

    @Override
    public void update(Connection conn, City entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<City> findAll(Connection conn, Integer offset, Integer limit) throws Exception {

        String sql = "select * from city ";

        List<Object> paramList = new ArrayList<>();

        if (offset != null) {
            sql += " OFFSET ?";
            paramList.add(offset);
        }
        if (limit != null) {
            sql += " LIMIT ?";
            paramList.add(limit);
        }

        PreparedStatement statement = PreparedStatementBuilder.build(conn, sql, paramList);
        ResultSet rs = statement.executeQuery();
        List<City> cityList = new ArrayList<>();
        while (rs.next()) {
            City city = new City();
            
            city.setCodeIbge(rs.getString("city_codeibge"));
            city.setId(rs.getLong("city_id"));
            city.setName(rs.getString("city_name"));
            cityList.add(city);
        }
        rs.close();
        statement.close();
        return cityList;
    }
}
