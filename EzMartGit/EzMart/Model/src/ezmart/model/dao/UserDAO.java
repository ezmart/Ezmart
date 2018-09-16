package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.City;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.State;
import ezmart.model.entity.User;
import ezmart.model.entity.UserSystem;
import ezmart.model.model_entity.UserModel;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO implements BaseDAO<User> {

    @Override
    public void create(Connection conn, User entity) throws Exception {

        String sql = "INSERT INTO "
                + "usersystem(usersystem_email, usersystem_password, usersystem_addresslocation, usersystem_numberhouse, "
                + "usersystem_neighborhood, usersystem_cityid, usersystem_zipcode, usersystem_telephone, "
                + "usersystem_usertype, usersystem_active, usersystem_latitude, usersystem_longitude, usersystem_img) "
                + "VALUES (?,md5('Ez'||?||'Mart'),?,?,?,?,?,?,?,?,?,?,?) RETURNING usersystem_id;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getPassword());
        statement.setString(++i, entity.getAddressLocation());
        statement.setInt(++i, entity.getNumberHouse());
        statement.setString(++i, entity.getNeighborhood());
        statement.setLong(++i, entity.getCity());
        statement.setString(++i, entity.getZipCode());
        statement.setString(++i, entity.getTelephone());
        statement.setString(++i, entity.getUserType());
        statement.setBoolean(++i, entity.isActive());
        statement.setString(++i, entity.getLatitude());
        statement.setString(++i, entity.getLongitude());
        statement.setBytes(++i, entity.getImg());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("usersystem_id"));
        }
        resultSet.close();
        statement.close();
    }

    @Override
    public User readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from usersystem WHERE usersystem_id=?";
        User user = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                user.setEmail(resultSet.getString("usersystem_email"));
                user.setAddressLocation(resultSet.getString("usersystem_addresslocation"));
                user.setNumberHouse(resultSet.getInt("usersystem_numberhouse"));
                user.setNeighborhood(resultSet.getString("usersystem_Neighborhood"));
                user.setCity(resultSet.getLong("usersystem_cityid"));
                user.setZipCode(resultSet.getString("usersystem_zipcode"));
                user.setTelephone(resultSet.getString("usersystem_telephone"));

            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    @Override
    public List<User> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT usersystem_id, usersystem_email, usersystem_password, usersystem_addresslocation, "
                + "usersystem_numberhouse, usersystem_neighborhood, usersystem_cityid, usersystem_zipcode, "
                + "usersystem_telephone, usersystem_usertype, usersystem_active, usersystem_latitude, usersystem_longitude "
                + "FROM usersystem "
                + "WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {
            if (criteria.containsKey(UserCriteria.EMAIL_EQ)) {
                String email = (String) criteria.get(UserCriteria.EMAIL_EQ);
                sql += " AND usersystem_email = ?";
                paramList.add(email);
            }

            if (criteria.containsKey(UserCriteria.ID_NE)) {
                Long id = (Long) criteria.get(UserCriteria.ID_NE);
                sql += " AND usersystem.usersystem_id != ?";
                paramList.add(id);
            }
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
        List<User> userList = new ArrayList<>();
        Long aux = null;
        if (rs.next()) {
            User user = null;
            if (rs.getString("usersystem_usertype").equals("consumer")) {
                user = new Consumer();
            } else {
                user = new Establishment();
            }

            user.setId(rs.getLong("usersystem_id"));
            user.setEmail(rs.getString("usersystem_email"));
            user.setPassword(rs.getString("usersystem_password"));
            user.setAddressLocation(rs.getString("usersystem_addresslocation"));
            user.setNumberHouse(rs.getInt("usersystem_numberhouse"));
            user.setNeighborhood(rs.getString("usersystem_neighborhood"));
            user.setCity(rs.getLong("usersystem_cityid"));
            user.setZipCode(rs.getString("usersystem_zipcode"));
            user.setTelephone(rs.getString("usersystem_telephone"));
            user.setUserType(rs.getString("usersystem_usertype"));
            user.setActive(rs.getBoolean("usersystem_active"));
            user.setLatitude(rs.getString("usersystem_latitude"));
            user.setLongitude(rs.getString("usersystem_longitude"));

            userList.add(user);
        }

        rs.close();
        statement.close();
        return userList;
    }

    @Override
    public void update(Connection conn, User entity) throws Exception {
        String sql = "UPDATE usersystem "
                + "SET usersystem_addresslocation=?, usersystem_numberhouse=?, usersystem_neighborhood=?, usersystem_cityid=?, "
                + "usersystem_zipcode=?, usersystem_telephone=? "
                + "WHERE usersystem_id = ?;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getAddressLocation());
        statement.setInt(++i, entity.getNumberHouse());
        statement.setString(++i, entity.getNeighborhood());
        statement.setLong(++i, entity.getCity());
        statement.setString(++i, entity.getZipCode());
        statement.setString(++i, entity.getTelephone());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User authenticate(Connection conn, String email, String password) throws Exception {
        String sql = "SELECT * FROM usersystem "
                + " WHERE "
                + " usersystem_email = '" + email + "' "
                + " AND usersystem_password = md5('Ez'||'" + password + "'||'Mart') ";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        User user = null;

        while (resultSet.next()) {
            if (resultSet.getString("usersystem_usertype").equals("consumer")
                    || resultSet.getString("usersystem_usertype").equals("admin")) {
                user = new Consumer();
            } else {
                user = new Establishment();
            }

            user.setId(resultSet.getLong("usersystem_id"));
            user.setEmail(resultSet.getString("usersystem_email"));
            user.setPassword(resultSet.getString("usersystem_password"));
            user.setAddressLocation(resultSet.getString("usersystem_addresslocation"));
            user.setNumberHouse(resultSet.getInt("usersystem_numberhouse"));
            user.setNeighborhood(resultSet.getString("usersystem_neighborhood"));
            user.setCity(resultSet.getLong("usersystem_cityid"));
            user.setZipCode(resultSet.getString("usersystem_zipcode"));
            user.setTelephone(resultSet.getString("usersystem_telephone"));
            user.setUserType(resultSet.getString("usersystem_usertype"));
            user.setActive(resultSet.getBoolean("usersystem_active"));
            user.setLatitude(resultSet.getString("usersystem_latitude"));
            user.setLongitude(resultSet.getString("usersystem_longitude"));

            userList.add(user);
        }
        resultSet.close();
        statement.close();

        if (userList == null || userList.isEmpty() || userList.size() > 1) {
            return null;
        }

        return user;
    }

    public Boolean comparePassword(User entity, String pass, Connection conn) throws SQLException {
        Boolean test = false;

        String sql = "SELECT * FROM usersystem "
                + " WHERE "
                + " usersystem_id = '" + entity.getId() + "' "
                + " AND usersystem_password = md5('Ez'||" + pass + "||'Mart') "
                + " AND usersystem_email = '" + entity.getEmail() + "'";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        User user = null;

        while (resultSet.next()) {
            if (resultSet.getString("usersystem_usertype").equals("consumer")) {
                user = new Consumer();
            } else if (resultSet.getString("usersystem_usertype").equals("admin")) {
                user = new Consumer();
            } else {
                user = new Establishment();
            }

            user.setId(resultSet.getLong("usersystem_id"));
            user.setEmail(resultSet.getString("usersystem_email"));
            user.setPassword(resultSet.getString("usersystem_password"));

            userList.add(user);
        }

        if (userList != null && userList.size() > 0) {
            test = true;
        }

        return test;
    }

    public void updatePasswordByUser(Connection conn, User entity) throws SQLException {
        String sql = "UPDATE usersystem SET usersystem_password=md5('Ez'||?||'Mart') WHERE usersystem_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getPassword());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    public void updatePasswordByEmail(Connection conn, UserModel entity) throws SQLException {
        String sql = "UPDATE usersystem SET usersystem_password=md5('Ez'||?||'Mart') WHERE usersystem_email=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getPassword());
        statement.setString(++i, entity.getEmail());
        statement.execute();
        statement.close();
    }

    public void setImg(Connection conn, Long id, byte[] bytes) throws Exception {

        String sql = "UPDATE usersystem SET usersystem_img=? WHERE usersystem_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setBytes(++i, bytes);
        statement.setLong(++i, id);

        statement.execute();
        statement.close();
    }

    public byte[] getImg(Connection conn, Long id) throws Exception {
        byte[] bytes = null;
        String sql = "SELECT usersystem_img FROM usersystem WHERE usersystem_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            bytes = resultSet.getBytes("usersystem_img");
        }
        resultSet.close();
        statement.close();
        return bytes;
    }

    public Boolean activeUser(Connection conn, String email) throws SQLException {
        String sql = "UPDATE usersystem SET usersystem_active=? WHERE usersystem_email=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setBoolean(++i, true);
        statement.setString(++i, email);
        statement.execute();
        statement.close();

        return true;
    }

    public List<UserSystem> findAll(Connection conn, Integer offset, Integer limit) throws SQLException {
        String sql = "select\n"
                + "	*\n"
                + "from\n"
                + "	usersystem\n"
                + "left join city on\n"
                + "		usersystem_cityid = city_id\n"
                + "left join state on\n"
                + "		state_id = city_stateid\n"
                + "left join consumer on\n"
                + "		consumer_userid = usersystem_id\n"
                + "left join establishment on\n"
                + "		establishment_userid = usersystem_id";

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
        List<UserSystem> userSystemList = new ArrayList<>();
        while (rs.next()) {

            UserSystem userSystem = new UserSystem();
            Consumer consumer = new Consumer();
            Establishment establishment = new Establishment();
            City city = new City();
            State state = new State();

            if (rs.getObject("consumer_id") != null) {
                consumer.setId(rs.getLong("consumer_id"));
                consumer.setName(rs.getString("consumer_name"));
                consumer.setLastName(rs.getString("consumer_lastname"));
                consumer.setCpf(rs.getString("consumer_cpf"));
            }

            if (rs.getObject("establishment_id") != null) {
                establishment.setId(rs.getLong("establishment_id"));
                establishment.setCnpj(rs.getString("establishment_cnpj"));
                establishment.setSecondEmail(rs.getString("establishment_secondemail"));
                establishment.setName(rs.getString("establishment_name"));
                establishment.setBusinessName(rs.getString("establishment_businessname"));
                establishment.setPlan(rs.getInt("establishment_plan"));
                establishment.setPlanStartDate(rs.getDate("establishment_planstartdate"));
                establishment.setPlanFinalDate(rs.getDate("establishment_planfinaldate"));
            }
            city.setName(rs.getString("city_name"));

            state.setInitials(rs.getString("state_initials"));

            userSystem.setId(rs.getLong("usersystem_id"));
            userSystem.setEmail(rs.getString("usersystem_email"));
            userSystem.setAddressLocation(rs.getString("usersystem_addresslocation"));
            userSystem.setNumberHouse(rs.getInt("usersystem_numberhouse"));
            userSystem.setNeighborhood(rs.getString("usersystem_neighborhood"));
            userSystem.setTelephone(rs.getString("usersystem_telephone"));
            if (rs.getString("usersystem_usertype").equals(UserSystem.TIPO_CONSUMER)) {
                userSystem.setUserType("CONSUMIDOR");
            } else if (rs.getString("usersystem_usertype").equals(UserSystem.TIPO_EMPORIUM)) {
                userSystem.setUserType("ESTABELECIMENTO");
            } else if (rs.getString("usersystem_usertype").equals(UserSystem.TIPO_ADMIN)) {
                userSystem.setUserType("ADMINISTRADOR");
            }
            userSystem.setActive(rs.getBoolean("usersystem_active"));
            if (userSystem.getActive()) {
                userSystem.setActiveString("SIM");
            } else {
                userSystem.setActiveString("NÃO");
            }

            userSystem.setConsumer(consumer);
            userSystem.setEstablishment(establishment);
            userSystem.setCity(city);
            userSystem.setState(state);

            userSystemList.add(userSystem);
        }

        rs.close();
        statement.close();
        return userSystemList;
    }
    
    public UserSystem createUserSystem (Connection conn, UserSystem entity) throws Exception {

        String sql = "INSERT INTO "
                + "usersystem(usersystem_email, usersystem_password, usersystem_addresslocation, usersystem_numberhouse, "
                + "usersystem_neighborhood, usersystem_cityid, usersystem_zipcode, usersystem_telephone, "
                + "usersystem_usertype, usersystem_active) "
                + "VALUES (?,md5('Ez'||?||'Mart'),?,?,?,?,?,?,?,?) RETURNING usersystem_id;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getPassword());
        statement.setString(++i, entity.getAddressLocation());
        statement.setInt(++i, entity.getNumberHouse());
        statement.setString(++i, entity.getNeighborhood());
        statement.setLong(++i, entity.getCity().getId());
        statement.setString(++i, entity.getZipCode());
        statement.setString(++i, entity.getTelephone());
        statement.setString(++i, entity.getUserType());
        statement.setBoolean(++i, entity.getActive());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("usersystem_id"));
        }
        resultSet.close();
        statement.close();
        
        return entity;
    }
}
