package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BookInfo;
import model.BookType;
import util.JDBC;

public class BooktypeDao {
	public static List selectBooktype() {
		List list = new ArrayList();
		String sql = "select * from tb_booktype";
		ResultSet rs = JDBC.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType = new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typeName"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}



	public static int Insertbook(String id, String typeName, String days, Double fk) {
		int i = 0;
		try {
			String sql = "insert into tb_booktype(id,typeName,days,fk) values('" + id + "','" + typeName + "','" + days + "'," + "'" + fk
					+ "')";
			i = JDBC.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JDBC.close();
		return i;
	}

	public static int Updatebook(String id, String typeName, String days, Double fk) {
		int i = 0;
		try {
			String sql = "update tb_booktype set id='" + id + "',typeName='" + typeName + "',days='" + days
					+ "',fk='" + fk + "'  where id='" + id + "'";
			i = JDBC.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return i;
	}

	public static List selectBooktype(String id) {
		List list = new ArrayList<>();
		String sql = "select * from tb_booktype where id='" + id + "'";
		ResultSet rs = JDBC.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType = new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typename"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
}
