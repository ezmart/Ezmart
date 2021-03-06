package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.criteria.ShoppingListCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.entity.ShoppingList;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingListDAO implements BaseDAO<ShoppingList> {

    @Override
    public void create(Connection conn, ShoppingList entity) throws Exception {
        String sql = "INSERT INTO list(list_consumerid, list_favorite, list_name, list_date) "
                + "VALUES (?,?,?,?) RETURNING list_id;";

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setLong(++i, entity.getConsumerId());
        statement.setBoolean(++i, entity.getFavorite());
        statement.setString(++i, entity.getName());
        statement.setDate(++i, entity.getDate());

        statement.executeQuery();

        statement.close();
    }

    @Override
    public ShoppingList readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from list WHERE list_id=?";
        ShoppingList shoppingList = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                shoppingList = new ShoppingList();
                shoppingList.setId(resultSet.getLong("list_id"));
                shoppingList.setConsumerId(resultSet.getLong("list_consumerid"));
                shoppingList.setName(resultSet.getString("list_name"));
                shoppingList.setDate(resultSet.getDate("list_date"));
                shoppingList.setFavorite(resultSet.getBoolean("list_favorite"));

            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            throw e;
        }
        return shoppingList;
    }

    @Override
    public List<ShoppingList> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * from list WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (criteria != null) {
            if (criteria.containsKey(UserCriteria.ID_EQ)) {
                Long userId = (Long) criteria.get(UserCriteria.ID_EQ);
                sql += " AND list_consumerid = ?";
                paramList.add(userId);
            }
            if (criteria.containsKey(ShoppingListCriteria.LIST_FAVORITE_EQ)) {
                boolean favorite = (boolean) criteria.get(ShoppingListCriteria.LIST_FAVORITE_EQ);
                sql += " AND list_favorite = TRUE";
                paramList.add(favorite);
            }
        }

        sql += " ORDER BY list_id ASC ";

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
        List<ShoppingList> shoppingList = new ArrayList<>();
        while (resultSet.next()) {
            ShoppingList list = new ShoppingList();

            list.setId(resultSet.getLong("list_id"));
            list.setConsumerId(resultSet.getLong("list_consumerid"));
            list.setName(resultSet.getString("list_name"));
            list.setDate(resultSet.getDate("list_date"));
            list.setFavorite(resultSet.getBoolean("list_favorite"));

            shoppingList.add(list);
        }
        resultSet.close();
        statement.close();
        return shoppingList;
    }

    @Override
    public void update(Connection conn, ShoppingList entity) throws Exception {
        String sql = "UPDATE list SET list_name=? WHERE list_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, entity.getName());
        statement.setLong(2, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM list WHERE list_id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
