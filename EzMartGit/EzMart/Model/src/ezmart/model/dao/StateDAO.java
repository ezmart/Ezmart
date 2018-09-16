package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.State;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateDAO implements BaseDAO<State> {

    @Override
    public void create(Connection conn, State entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from state WHERE state_id=?";
        State state = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                state.setId(resultSet.getLong("state_id"));
                state.setInitials(resultSet.getString("state_initials"));
                state.setName(resultSet.getString("state_name"));

            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return state;
    }

    @Override
    public List<State> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
                String sql = "SELECT * from state WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();

        if (criteria != null) {
//            if (criteria.containsKey(CategoriaCriteria.NOME_ILIKE)) {
//                String nome = (String) criteria.get(CategoriaCriteria.NOME_ILIKE);
//                sql += " AND nome ILIKE ?";
//                paramList.add("%" + nome + "%");
//            }
//
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

        sql += " ORDER BY state_id ASC ";

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
        List<State> stateList = new ArrayList<>();
        while (resultSet.next()) {
            State state = new State();
            state.setId(resultSet.getLong("state_id"));
            state.setName(resultSet.getString("state_name"));
            state.setInitials(resultSet.getString("state_initials"));
            
            stateList.add(state);
        }
        resultSet.close();
        statement.close();
        return stateList;
    }

    @Override
    public void update(Connection conn, State entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<State> findAll(Connection conn, Integer offset, Integer limit)throws Exception{
        String sql = "select * from state ";

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
        List<State> stateList = new ArrayList<>();
        while (rs.next()) {

            State state = new State();
            
            state.setId(rs.getLong("state_id"));
            state.setInitials(rs.getString("state_initials"));
            state.setName(rs.getString("state_name"));
            
            stateList.add(state);
        }

        rs.close();
        statement.close();
        return stateList;
    }
}
