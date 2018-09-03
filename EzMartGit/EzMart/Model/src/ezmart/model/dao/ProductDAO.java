package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.ProductCriteria;
import ezmart.model.entity.Product;
import ezmart.model.entity.Provider;
import ezmart.model.entity.Sector;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAO implements BaseDAO<Product> {

    @Override
    public void create(Connection conn, Product entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * from product"
                + " LEFT JOIN sector on sector_id = product_sectorid"
                + " LEFT JOIN provider on provider_id = product_providerid"
                + " WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (criteria != null) {

            if (criteria.containsKey(ProductCriteria.PRODUCT_BARCODE_IN)) {
                String barCode = (String) criteria.get(ProductCriteria.PRODUCT_BARCODE_IN);
                sql += " AND product_barcode = ?";
                paramList.add(barCode);
            }

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
//            if (criteria.containsKey(CategoriaCriteria.ID_NE)) {
//                Long id = (Long) criteria.get(CategoriaCriteria.ID_NE);
//                sql += " AND id != ?";
//                paramList.add(id);
//            }
        }

        sql += " ORDER BY product_name ASC ";

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
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            Sector sector = new Sector();
            Provider provider = new Provider();
                    
            sector.setId(resultSet.getLong("sector_id"));
            sector.setName(resultSet.getString("sector_name"));
            provider.setId(resultSet.getLong("provider_id"));
            provider.setCnpj(resultSet.getString("provider_cnpj"));
            provider.setName(resultSet.getString("provider_name"));
            provider.setBusinessName(resultSet.getString("provider_businessname"));
           
            product.setId(resultSet.getLong("product_id"));
            product.setBarCode(resultSet.getString("product_barcode"));
            product.setName(resultSet.getString("product_name"));
            product.setBrand(resultSet.getString("product_brand"));
            product.setSector(sector);
            product.setProvider(provider);
            //product.setImg(resultSet.getLong("city_stateid"));

            productList.add(product);
        }
        resultSet.close();
        statement.close();
        return productList;
    }

    @Override
    public void update(Connection conn, Product entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setImg(Connection conn, Long id, byte[] bytes) throws Exception {

        String sql = "UPDATE product SET product_img=? WHERE usersystem_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setBytes(++i, bytes);
        statement.setLong(++i, id);

        statement.execute();
        statement.close();
    }

    public byte[] getImg(Connection conn, Long id) throws Exception {
        byte[] bytes = null;
        String sql = "SELECT product_img FROM product WHERE usersystem_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            bytes = resultSet.getBytes("product_img");
        }
        resultSet.close();
        statement.close();
        return bytes;
    }

    public List<Product> findAll(Connection conn, Integer offset, Integer limit) throws Exception {
        String sql = "SELECT * FROM product"
                + " LEFT JOIN sector on sector_id = product_sectorid"
                + " LEFT JOIN provider on provider_id = product_providerid;";

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
        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
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
            product.setSector(sector);
            product.setProvider(provider);
        }

        rs.close();
        statement.close();
        return productList;
    }
}
