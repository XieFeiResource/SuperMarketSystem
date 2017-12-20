package com.SuperMarketSystem.dbuntils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class JDBCTools {

	/**
	 * 执行 SQL 语句, 使用 PreparedStatement
	 * 
	 * @param sql
	 * @param args:
	 *            填写 SQL 占位符的可变参数
	 */
	public static void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, preparedStatement, connection);
		}
	}

	// 通用的查询方法：可以根据传入的 SQL、Class 对象返回 SQL 对应的记录的对象
	public static <T> T get(Class<T> clazz, String sql, Object... args) {
		T entity = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// 1. 得到 ResultSet 对象
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();

			// 2. 得到 ResultSetMetaData 对象
			ResultSetMetaData rsmd = resultSet.getMetaData();

			// 3. 创建一个 Map<String, Object> 对象, 键: SQL 查询的列的别名,
			// 值: 列的值
			Map<String, Object> values = new HashMap<>();

			// 4. 处理结果集. 利用 ResultSetMetaData 填充 3 对应的 Map 对象
			if (resultSet.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);

					values.put(columnLabel, columnValue);
				}
			}

			// 5. 若 Map 不为空集, 利用反射创建 clazz 对应的对象
			if (values.size() > 0) {
				entity = clazz.newInstance();

				// 5. 遍历 Map 对象, 利用反射为 Class 对象的对应的属性赋值.
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					String fieldName = entry.getKey();
					Object value = entry.getValue();
					ReflectionUtils.setFieldValue(entity, fieldName, value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(resultSet, preparedStatement, connection);
		}

		return entity;
	}

	public static void release(ResultSet rs, Statement statement, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void release(Statement statement, Connection conn) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws Exception {

		Properties properties = new Properties();

		InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");

		properties.load(in);

		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driver = properties.getProperty("driver");

		Class.forName(driver);

		return DriverManager.getConnection(jdbcUrl, user, password);
	}

}
