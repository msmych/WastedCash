package wasted.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryExecutor {

    private static final Logger log = LoggerFactory.getLogger(QueryExecutor.class);

    private final HikariDataSource dataSource;

    public QueryExecutor(String propsPath) {
        dataSource = new HikariDataSource(new HikariConfig(propsPath));
    }

    public void execute(String query) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            log.error("Error executing query", e);
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Error closing statement", e);
                throw new RuntimeException(e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Error closing connection", e);
                throw new RuntimeException(e);
            }
        }
    }


}