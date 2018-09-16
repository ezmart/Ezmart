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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAO implements BaseDAO<Product> {

    public Long createWithReturn(Connection conn, Product entity) throws Exception {
        String sql = " INSERT INTO product (product_sectorid, product_providerid, product_barcode, product_name, product_image, product_brand)"
                + " VALUES(?,?,?,?,?,?) RETURNING product_id; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, entity.getSector().getId());
        statement.setLong(++i, entity.getProvider().getId());
        statement.setString(++i, entity.getBarCode());
        statement.setString(++i, entity.getName());
        if (entity.getImage() != null) {
            statement.setBytes(++i, entity.getImage());
        } else {
            statement.setNull(++i, Types.ARRAY);
        }
        statement.setString(++i, entity.getBrand());

       ResultSet resultSet = statement.executeQuery();
 
        Long productId = null;
        if(resultSet.next()){
            productId = resultSet.getLong("product_id");
        }
        resultSet.close();
        statement.close();
        
        return productId;
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

        String sql = " UPDATE product SET product_sectorid = ?, product_providerid = ?, product_barcode = ?, product_name = ?, product_brand = ?"
                + " WHERE product_id = ?; ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, entity.getSector().getId());
        statement.setLong(++i, entity.getProvider().getId());
        statement.setString(++i, entity.getBarCode());
        statement.setString(++i, entity.getName());
        statement.setString(++i, entity.getBrand());
        statement.setLong(++i, entity.getId());

        statement.execute();

        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = " DELETE FROM product WHERE product_id = ?;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, id);

        statement.execute();

        statement.close();
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
        String sql = "SELECT product_img FROM product WHERE product_id=?;";
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
                + " LEFT JOIN provider on provider_id = product_providerid"
                + " ORDER BY product_name ";

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
            product.setImage(rs.getBytes("product_image"));
            product.setSector(sector);
            product.setProvider(provider);

            productList.add(product);
        }

        rs.close();
        statement.close();
        return productList;
    }

    public void uploadImage(Connection conn, byte[] productImage, Long idProduct) throws Exception {
        String sql = " UPDATE product SET product_image = ? WHERE product_id = ?;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setBytes(++i, productImage);
        statement.setLong(++i, idProduct);

        statement.execute();

        statement.close();
    }

    @Override
    public void create(Connection conn, Product entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
