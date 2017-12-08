package com.SuperMarketSystem.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.SuperMarketSystem.bean.Good;
import com.SuperMarketSystem.dbuntils.JDBCTools;

public class Operatergoods {
	// 查询数据库中的记录

	public List<Good> queryAllgood(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Good> list = new ArrayList<Good>();
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Good good = new Good();
				good.setGno(resultSet.getInt(1));
				good.setGname(resultSet.getString(2));
				good.setGprice(resultSet.getInt(3));
				good.setAccount(resultSet.getInt(4));
				good.setGtype(resultSet.getString(5));
				good.setGimagepath(resultSet.getString(6));
				good.setGdes(resultSet.getString(7));
				list.add(good);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 为table中的二维数组赋值
	public Object[][] Createdata(List<Good> list) {
		Object[][] data = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getGno();
			data[i][1] = list.get(i).getGname();
			data[i][2] = list.get(i).getGprice();
			data[i][3] = list.get(i).getAccount();
			data[i][4] = list.get(i).getGtype();
			data[i][5] = list.get(i).getGimagepath();
			data[i][6] = list.get(i).getGdes();
		}
		return data;
	}

}
