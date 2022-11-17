package config;

import aquality.selenium.browser.AqualityServices;
import model.Field;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
    }

    public static Map<Integer, Map<String, Field>> executeQuery(String query) {
        try (Connection connection = DbUtil.getConnection(); final Statement statement = connection.createStatement()) {
            Map<Integer, Map<String, Field>> resultMap = new HashMap<>();
            Map<String, String> columnLabels = new HashMap<>();
            final ResultSet resultSet = statement.executeQuery(query);
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnLabels.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getMetaData().getColumnTypeName(i));
            }
            int count = 0;
            while (resultSet.next()) {
                Map<String, Field> row = new HashMap<>();
                for (String columnLabel : columnLabels.keySet()) {
                    final String columnType = columnLabels.get(columnLabel);
                    final Field field = new Field();
                    field.setType(columnType);
                    field.setValue(resultSet.getString(columnLabel));
                    row.put(columnLabel, field);
                }
                resultMap.put(count, row);
                count++;
            }
            return resultMap;
        } catch (SQLException exception) {
            AqualityServices.getLogger().error(exception.getMessage());
            throw new RuntimeException("Result set parsing error");
        }
    }
}
