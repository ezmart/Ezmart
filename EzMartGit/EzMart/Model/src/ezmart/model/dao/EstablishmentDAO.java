package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.EstablishmentCriteria;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Product;
import ezmart.model.entity.Provider;
import ezmart.model.entity.Sector;
import ezmart.model.entity.User;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EstablishmentDAO implements BaseDAO<Establishment> {

    @Override
    public void create(Connection conn, Establishment entity) throws Exception {

        String sql = "INSERT INTO establishment(establishment_cnpj, establishment_secondemail, establishment_name, "
                + "establishment_businessname, establishment_plan, establishment_planstartdate, "
                + "establishment_planfinaldate, establishment_userid) "
                + "VALUES (?,?,?,?,?,?,?,?) RETURNING establishment_userid;";

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
        String sql = "SELECT establishment_id, establishment_name, establishment_businessname, "
                + "establishment_secondemail, establishment_cnpj, establishment_plan, "
                + "establishment_planstartdate, establishment_planfinaldate, "
                + "establishment_userid "
                + "FROM establishment "
                + "WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {
            if (criteria.containsKey(EstablishmentCriteria.CNPJ_EQ)) {
                String cnpj = (String) criteria.get(EstablishmentCriteria.CNPJ_EQ);
                sql += " AND establishment_cnpj = ?";
                paramList.add(cnpj);
            }

            if (criteria.containsKey(EstablishmentCriteria.SECOND_EMAIL_EQ)) {
                String secondEmail = (String) criteria.get(EstablishmentCriteria.SECOND_EMAIL_EQ);
                sql += " AND establishment_secondemail = ?";
                paramList.add(secondEmail);
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
        List<Establishment> establishmentList = new ArrayList<>();
        Long aux = null;
        if (rs.next()) {

            Establishment establishment = new Establishment();
            establishment.setId(rs.getLong("establishment_id"));
            establishment.setName(rs.getString("establishment_name"));
            establishment.setBusinessName(rs.getString("establishment_businessname"));
            establishment.setSecondEmail(rs.getString("establishment_secondemail"));
            establishment.setCnpj(rs.getString("establishment_cnpj"));
            establishment.setPlan(rs.getInt("establishment_plan"));
            establishment.setPlanStartDate(rs.getDate("establishment_planstartdate"));
            establishment.setPlanFinalDate(rs.getDate("establishment_planfinaldate"));

            establishmentList.add(establishment);
        }

        rs.close();
        statement.close();
        return establishmentList;
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
        String sql = " DELETE FROM establishment WHERE establishment_id = ?;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, id);

        statement.execute();

        statement.close();
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

                establishment.setId(resultSet.getLong("establishment_id"));
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

    public List<EstablishmentProduct> findAllEstablishmentProduct(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM establishmentproduct "
                + " LEFT JOIN product ON product_id = establishmentproduct_productid"
                + " WHERE establishmentproduct_establishmentid=?;";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        List<EstablishmentProduct> establishmentProductList = new ArrayList<>();
        if (resultSet.next()) {
            EstablishmentProduct establishmentProduct = new EstablishmentProduct();
            Establishment establishment = new Establishment();
            Product product = new Product();
            
            establishment.setId(id);
            product.setId(resultSet.getLong("establishmentproduct_productid"));
            
            establishmentProduct.setId(resultSet.getLong("establishmentproduct_id"));
            establishmentProduct.setDateAlteration(resultSet.getDate("establishmentproduct_date"));
            establishmentProduct.setPrice(resultSet.getDouble("establishmentproduct_price"));
            establishmentProduct.setEstablishment(establishment);
            establishmentProduct.setProduct(product);
            
            establishmentProductList.add(establishmentProduct);
        }
        resultSet.close();
        statement.close();
        
        return establishmentProductList;
    }
    
    public List<Product> findAllProductByEstablishmentId(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM product "
                + " LEFT JOIN sector ON sector_id = product_sectorid "
                + " LEFT JOIN provider ON provider_id = product_providerid "
                + " WHERE product_id NOT IN "
                + "( SELECT establishmentproduct_productid FROM establishmentproduct WHERE establishmentproduct_establishmentid = ? );";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();

        List<Product> productList = new ArrayList<>();
        if (rs.next()) {
            Product product = new Product();
            
            Provider provider = new Provider();
            Sector sector = new Sector();

            sector.setId(rs.getLong("sector_id"));
            sector.setName(rs.getString("sector_name"));
            provider.setId(rs.getLong("provider_id"));
            provider.setCnpj(rs.getString("provider_cnpj"));
            provider.setName(rs.getString("provider_name"));
            provider.setBusinessName(rs.getString("provider_businessname"));

            product.setId(rs.getLong("product_id"));
            product.setBarCode(rs.getString("product_barcode"));
            product.setName(rs.getString("product_name"));
            product.setBrand(rs.getString("product_brand"));
            product.setImage(rs.getBytes("product_image"));
            product.setSector(sector);
            product.setProvider(provider);
            
            productList.add(product);
        }
        rs.close();
        statement.close();
        
        return productList;
    }
}
