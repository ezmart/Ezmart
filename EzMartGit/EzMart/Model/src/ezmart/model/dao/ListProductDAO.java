package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.ListProductCriteria;
import ezmart.model.entity.ListProduct;
import ezmart.model.model_entity.ListProductModel;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListProductDAO implements BaseDAO<ListProduct> {

    @Override
    public void create(Connection conn, ListProduct entity) throws Exception {
        String sql = "INSERT INTO listproduct(listproduct_listid, listproduct_productid, listproduct_quantity) "
                + "VALUES (?,?,?) ";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, entity.getListId());
        statement.setLong(++i, entity.getProdutcId());
        statement.setInt(++i, entity.getQuantity());

        statement.execute();

        statement.close();
    }

    @Override
    public ListProduct readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ListProduct> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public List<ListProductModel> readByCriteriaModel(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * from listproduct INNER JOIN product ON product_id=listproduct_productid "
                + "WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (criteria != null) {

            if (criteria.containsKey(ListProductCriteria.PRODUCT_ID_EQ)) {
                Long productId = (Long) criteria.get(ListProductCriteria.PRODUCT_ID_EQ);
                sql += " AND product_id = ?";
                paramList.add(productId);
            }

            if (criteria.containsKey(ListProductCriteria.LIST_ID_EQ)) {
                Long listId = (Long) criteria.get(ListProductCriteria.LIST_ID_EQ);
                sql += " AND listproduct_listid = ?";
                paramList.add(listId);
            }
        }

        sql += " ORDER BY listproduct_id ASC ";

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
        List<ListProductModel> listProduct = new ArrayList<>();
        while (resultSet.next()) {
            ListProductModel value = new ListProductModel();
            value.setId(resultSet.getLong("listproduct_id"));
            value.setListId(resultSet.getLong("listproduct_listid"));
            value.setProductName(resultSet.getString("product_name"));
            value.setProductId(resultSet.getLong("product_id"));
            value.setQuantity(resultSet.getInt("listproduct_quantity"));

            listProduct.add(value);
        }
        resultSet.close();
        statement.close();
        return listProduct;
    }

    @Override
    public void update(Connection conn, ListProduct entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM listproduct WHERE listproduct_listid=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

    //Deleta com base no ID da Lista e do ID do produto
    public void deleteFromList(Connection conn, Long id, Long productId) throws Exception {
        String sql = "DELETE FROM listproduct WHERE listproduct_listid=? AND listproduct_productid=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.setLong(2, productId);
        statement.execute();
        statement.close();
    }
}
