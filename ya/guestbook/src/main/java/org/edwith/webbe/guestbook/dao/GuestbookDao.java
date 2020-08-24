package org.edwith.webbe.guestbook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

public class GuestbookDao {
	
	private static String dburl = "jdbc:mysql://localhost:3306/guestbook?useUnicode=true&characterEncoding=utf8&useSSL=FALSE&serverTimezone=UTC";
	private static String dbUser = "guestbookuser";
	private static String dbpasswd = "0000";
	
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

        // 코드를 작성하세요.

        String sql = "SELECT id, name, content, regdate FROM guestbook order by id desc";
        Connection conn = DBUtil.getConnection(dburl, dbUser, dbpasswd);
    	try{
    		PreparedStatement ps = conn.prepareStatement(sql);
    		try(ResultSet rs = ps.executeQuery()) {
    			while(rs.next()) {
    				Long id = rs.getLong(1);
    				String name = rs.getString(2);
    				String content = rs.getString(3);
    				Date regdate = rs.getDate(4);
    				Guestbook guestbook = new Guestbook(id,name,content,regdate);
    				list.add(guestbook);
    				
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        
        
        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
    	Connection conn = DBUtil.getConnection(dburl, dbUser,dbpasswd);
    	PreparedStatement ps = null;
    	try {
    	String sql = "INSERT INTO guestbook (id, name, content, regdate) VALUES (?,?,?,?)";
    	
    	ps = conn.prepareStatement(sql);
    	
    	java.util.Date utilDate = guestbook.getRegdate();
    	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
  
    	ps.setLong(1, guestbook.getId());
    	ps.setString(2, guestbook.getName());
    	ps.setString(3, guestbook.getContent());
    	ps.setDate(4, sqlDate);
    	
    	ps.executeUpdate();
    	
    	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}finally {
    		if(ps!=null) {
    			try {
    				ps.close();
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    		if(conn!=null) {
    			try {
    				conn.close();
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    }
}
