package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.ConsumerCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsumerDAO implements BaseDAO<Consumer> {

    @Override
    public void create(Connection conn, Consumer entity) throws Exception {

        String sql = "INSERT INTO consumer(consumer_name, consumer_lastname, consumer_cpf, consumer_userid) "
                + "VALUES (?,?,?,?) RETURNING consumer_userid;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getLastName());
        statement.setString(++i, entity.getCpf());
        statement.setLong(++i, entity.getId());

        statement.executeQuery();

        statement.close();
    }

    @Override
    public Consumer readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consumer> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT consumer__id, consumer_name, consumer_lastname, consumer_cpf, "
                + "consumer_userid, "
                + "FROM consumer "
                + "WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {
            if (criteria.containsKey(ConsumerCriteria.CPF_EQ)) {
                String cpf = (String) criteria.get(ConsumerCriteria.CPF_EQ);
                sql += " AND consumer_cpf = ?";
                paramList.add(cpf);
            }

//            if (criteria.containsKey(UserCriteria.ID_NE)) {
//                Long id = (Long) criteria.get(UserCriteria.ID_NE);
//                sql += " AND usersystem.usersystem_id != ?";
//                paramList.add(id);
//            }
            //Add criterio ....
        }

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
        List<Consumer> consumerList = new ArrayList<>();
        Long aux = null;
        if (rs.next()) {

            Consumer consumer = new Consumer();
            consumer.setId(rs.getLong("consumer_id"));
            consumer.setName(rs.getString("consumer_name"));
            consumer.setLastName(rs.getString("consumer_name"));
            consumer.setCpf(rs.getString("consumer_name"));

            //consumerList.add(user);
        }

        rs.close();
        statement.close();
        return consumerList;
    }

    @Override
    public void update(Connection conn, Consumer entity) throws Exception {
        String sql = "UPDATE consumer SET consumer_name=?, consumer_lastname=? WHERE consumer_userid =?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getLastName());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void readById(Connection conn, User user) throws Exception {
        String sql = "SELECT * FROM consumer WHERE consumer_userid=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, user.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ((Consumer) user).setName(resultSet.getString("consumer_name"));
            ((Consumer) user).setLastName(resultSet.getString("consumer_lastname"));
            ((Consumer) user).setCpf(resultSet.getString("consumer_cpf"));
        }
        resultSet.close();
        statement.close();
    }

    public Consumer readByUserId(Connection conn, Long userId) throws Exception {
        String sql = "SELECT * from consumer INNER JOIN usersystem ON usersystem_id=consumer_userid "
                + "WHERE consumer_userid=?;";
        Consumer consumer = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                consumer = new Consumer();
                consumer.setName(resultSet.getString("consumer_name"));
                consumer.setLastName(resultSet.getString("consumer_lastname"));
                consumer.setCpf(resultSet.getString("consumer_cpf"));

                consumer.setEmail(resultSet.getString("usersystem_email"));
                consumer.setAddressLocation(resultSet.getString("usersystem_addresslocation"));
                consumer.setNumberHouse(resultSet.getInt("usersystem_numberhouse"));
                consumer.setNeighborhood(resultSet.getString("usersystem_Neighborhood"));
                consumer.setCity(resultSet.getLong("usersystem_cityid"));
                consumer.setZipCode(resultSet.getString("usersystem_zipcode"));
                consumer.setTelephone(resultSet.getString("usersystem_telephone"));
            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return consumer;
    }
}
