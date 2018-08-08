package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class EstablishmentDAO implements BaseDAO<Establishment> {

    @Override
    public void create(Connection conn, Establishment entity) throws Exception {

        String sql = "INSERT INTO establishment(establishment_cnpj, establishment_secondemail, establishment_name, "
                + "establishment_businessname, establishment_plan, establishment_planstartdate, "
                + "establishment_planfinaldate, establishment_userid) "
                + "VALUES (?,?,?,?) RETURNING establishment_userid;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getSecondEmail());
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getBusinessName());
        statement.setInt(++i, entity.getPlan());
        statement.setDate(++i, entity.getPlanStartDate());
        statement.setDate(++i, entity.getPlanFinalDate());
        statement.setLong(++i, entity.getId());

        statement.executeQuery();

        statement.close();
    }

    @Override
    public Establishment readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Establishment> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Establishment entity) throws Exception {
        String sql = "UPDATE establishment SET establishment_secondemail=?, "
                + "establishment_name=?, establishment_businessname=? "
                + "WHERE establishment_userid =?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        //statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getSecondEmail());
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getBusinessName());
        //statement.setDate(++i, entity.getPlanStartDate());
        //statement.setDate(++i, entity.getPlanFinalDate());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void readById(Connection conn, User user) throws Exception {
        String sql = "SELECT * FROM establishment WHERE establishment_userid=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, user.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ((Establishment) user).setCnpj(resultSet.getString("establishment_cnpj"));
            ((Establishment) user).setSecondEmail(resultSet.getString("establishment_secondemail"));
            ((Establishment) user).setName(resultSet.getString("establishment_name"));
            ((Establishment) user).setBusinessName(resultSet.getString("establishment_businessname"));
            ((Establishment) user).setPlan(resultSet.getInt("establishment_plan"));
            ((Establishment) user).setPlanStartDate(resultSet.getDate("establishment_planstartdate"));
            ((Establishment) user).setPlanFinalDate(resultSet.getDate("establishment_planfinaldate"));
        }
        resultSet.close();
        statement.close();
    }

    public Establishment readByUserId(Connection conn, Long userId) throws Exception {
        String sql = "SELECT * FROM establishment INNER JOIN usersystem ON usersystem_id=establishment_userid "
                + "WHERE establishment_userid=?;";
        Establishment establishment = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                establishment = new Establishment();

                establishment.setCnpj(resultSet.getString("establishment_cnpj"));
                establishment.setSecondEmail(resultSet.getString("establishment_secondemail"));
                establishment.setName(resultSet.getString("establishment_name"));
                establishment.setBusinessName(resultSet.getString("establishment_businessname"));
                establishment.setPlanStartDate(resultSet.getDate("establishment_planstartdate"));
                establishment.setPlanFinalDate(resultSet.getDate("establishment_planfinaldate"));

                establishment.setEmail(resultSet.getString("usersystem_email"));
                establishment.setAddressLocation(resultSet.getString("usersystem_addresslocation"));
                establishment.setNumberHouse(resultSet.getInt("usersystem_numberhouse"));
                establishment.setNeighborhood(resultSet.getString("usersystem_Neighborhood"));
                establishment.setCity(resultSet.getLong("usersystem_cityid"));
                establishment.setZipCode(resultSet.getString("usersystem_zipcode"));
                establishment.setTelephone(resultSet.getString("usersystem_telephone"));

            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return establishment;
    }
}
