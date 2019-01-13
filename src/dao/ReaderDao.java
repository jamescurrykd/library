package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BookType;
import model.Reader;
import util.JDBC;

public class ReaderDao {
	public static List selectreaderInfo() {
		List list=new ArrayList();
		String sql="select * from tb_reader";
		ResultSet rs=JDBC.executeQuery(sql);
		try {
			while(rs.next()) {
				Reader reader=new Reader();
				reader.setISBN(rs.getString("ISBN"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identitycard"));
				reader.setMaxNum(rs.getString("maxnum"));
				reader.setDate(rs.getDate("date"));
				reader.setKeepMoney(rs.getDouble("keepmoney"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
//	public static List selectBookCategory() {
//		List list=new ArrayList();
//		String sql="select * from tb_bookType";
//		ResultSet rs=JDBC.executeQuery(sql);
//		try {
//			while(rs.next()) {
//				BookType bookType=new BookType();
//				bookType.setId(rs.getString("id"));
//				bookType.setTypeName(rs.getString("typename"));
//				bookType.setDays(rs.getString("days"));
//				bookType.setFk(rs.getString("fk"));
//				list.add(bookType);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		JDBC.close();
//		return list;
//	}
	public static int Insertreader(String name,String sex,String age,String identitycard,Date date,String maxnum,String tel,Double keepmoney,int zj,String zy,String ISBN,Date bztime) {
		int i=0;
		try {
			String sql="insert into tb_reader values('"+name+"','"+sex+"','"+age+"','"+identitycard+"','"+date+"','"+maxnum+"','"+tel+"','"+keepmoney+"',"+zj+",'"+zy+"','"+ISBN+"','"+bztime+"');";
			i=JDBC.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		JDBC.close();
		return i;
	}
	public static int Updatereader(String name,String sex,String age,String identitycard,Date date,String maxnum,String tel,Double keepmoney,int zj,String zy,String ISBN,Date bztime) {
		int i=0;
		try {
			String sql="update tb_reader set name='"+name+"',sex='"+sex+"',age='"+age+"',identitycard='"+identitycard+"',date='"+date+"',maxnum='"+maxnum+"',tel='"+tel+"',keepmoney='"+keepmoney+"',zj="+zj+",zy='"+zy+"',ISBN='"+ISBN+"',bztime='"+bztime+"' where ISBN='"+ISBN+"'";
			i=JDBC.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();			
		}
		JDBC.close();
		return i;
	}
	public static List selectreader(String ISBN) {
		List list=new ArrayList<>();
		String sql="select * from tb_reader where ISBN='"+ISBN+"'";
		ResultSet rs=JDBC.executeQuery(sql);
		try {
			while(rs.next()) {
				Reader reader=new Reader();
				reader.setISBN(rs.getString("ISBN"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identitycard"));
				reader.setMaxNum(rs.getString("maxnum"));
				reader.setDate(rs.getDate("date"));
				reader.setKeepMoney(rs.getDouble("keepmoney"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
}
