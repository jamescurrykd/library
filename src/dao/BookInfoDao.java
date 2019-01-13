package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import javax.naming.spi.DirStateFactory.Result;

import model.BookInfo;
import model.BookType;
import util.JDBC;

public class BookInfoDao {
	public static List selectBookInfo() {
		List list=new ArrayList();
		String sql="select * from tb_bookInfo";
		ResultSet rs=JDBC.executeQuery(sql);
		try {
			while(rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeId(rs.getString("typeid"));
				bookinfo.setBookName(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
	public static List selectBookCategory() {
		List list=new ArrayList();
		String sql="select * from tb_bookType";
		ResultSet rs=JDBC.executeQuery(sql);
		try {
			while(rs.next()) {
				BookType bookType=new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typename"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
	public static int Insertbook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Date date,Double price) {
		int i=0;
		try {
			String sql="insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,"+"publisher,date,price) values('"+ISBN+"','"+typeId+"','"+bookname+"',"+"'"+writer+"','"+translator+"','"+publisher+"','"+date+"','"+price+"')";
			i=JDBC.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		JDBC.close();
		return i;
	}
	public static int Updatebook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Date date,Double price) {
		int i=0;
		try {
			String sql="update tb_bookInfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"+bookname+"',writer='"+writer+"',translator='"+translator+"',publisher='"+publisher+"',date='"+date+"',price='"+price+"' where ISBN='"+ISBN+"'";
			i=JDBC.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();			
		}
		JDBC.close();
		return i;
	}
	public static List selectBookInfo(String ISBN) {
		List list=new ArrayList<>();
		String sql="select * from tb_bookInfo where ISBN='"+ISBN+"'";
		ResultSet rs=JDBC.executeQuery(sql);
		try {
			while(rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeId(rs.getString("typeid"));
				bookinfo.setBookName(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JDBC.close();
		return list;
	}
}
