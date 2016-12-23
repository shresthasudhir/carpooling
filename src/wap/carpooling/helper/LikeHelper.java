package wap.carpooling.helper;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import wap.carpooling.dto.DBConnection;

public class LikeHelper {
	
	public boolean insertLike(String userEmail, int postId) throws Exception {
		boolean flag = false;
		int userId = 0;
		Connection con = (Connection) DBConnection.getConnection();
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT userid from users WHERE email='" + userEmail + "'");
		ResultSet rs = ps1.executeQuery();
		while (rs.next()) {
			userId = Integer.parseInt(rs.getString("userid"));
		}
		String likeInsert = "INSERT INTO likes (userid,postid) VALUES(" + userId + "," + postId + ")";
		PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(likeInsert);
		ps2.executeUpdate();
		flag = true;
		return flag;
	}
	
	public boolean deleteLike(int postId) throws Exception {
		boolean flag = false;
		
		Connection con = (Connection) DBConnection.getConnection();
		Statement stmt = null;
		
		System.out.println(" -----------"+postId+"-------------------");
		
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM likes WHERE postid='" + postId + "'");
		ResultSet rs = ps1.executeQuery();
		
		if (!rs.isBeforeFirst() ) {
		    flag =  true; 
		}else{
			PreparedStatement ps2 = (PreparedStatement) con.prepareStatement("DELETE FROM likes WHERE postid='" + postId + "'");
			ps2.executeUpdate();
			flag = true;
		}
		
		return flag;
	}

}
