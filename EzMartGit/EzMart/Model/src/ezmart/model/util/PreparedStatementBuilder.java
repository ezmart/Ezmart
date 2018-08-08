package ezmart.model.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PreparedStatementBuilder {

    public static PreparedStatement build(Connection conn, String sql, List<Object> paramList) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(sql);

        for (int i = 0; i < paramList.size(); i++) {
            Object param = paramList.get(i);
            if (param instanceof String) {
                statement.setString((i + 1), (String) param);
            } else {
                if (param instanceof Integer) {
                    statement.setInt((i + 1), (Integer) param);
                } else {
                    if (param instanceof Long) {
                        statement.setLong((i + 1), (Long) param);
                    } else {
                        if (param instanceof BigDecimal) {
                            statement.setBigDecimal((i + 1), (BigDecimal) param);
                        }
                    }
                }
            }
        }
        return statement;
    }
}
