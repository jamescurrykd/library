package dao;

import java.sql.ResultSet;
import model.Operater;
import util.JDBC;

public class LoginDao {

	public static Operater check(String name, String password) {
		Operater operater = new Operater();
		String sql = "select * from tb_operator where name='" + name + "' and password='" + password + "'and admin=1";
		ResultSet rs = JDBC.executeQuery(sql);
		try {
			while (rs.next()) {
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("admin"));
				operater.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return operater;
	}
}
