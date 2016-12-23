package wap.carpooling.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import wap.carpooling.dto.DBConnection;
import wap.carpooling.model.PostComment;

public class CommentHelper {

	public ArrayList<String> getPostComment(int postId) throws SQLException, Exception {
		ArrayList comObject = new ArrayList();
		PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
				"SELECT " + "u.fullname,c.userid,c.commentid,c.postid,c.datecreated,c.dateupdated,c.comment "
						+ "FROM users u INNER JOIN comments c INNER JOIN posts p on p.postid=c.postid and u.userid=c.userid "
						+ "WHERE p.postid=" + postId + " ");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PostComment postComment = new PostComment();

			postComment.setFullName(rs.getString("fullname"));
			postComment.setCommentId(Integer.parseInt(rs.getString("commentid")));
			postComment.setUserId(Integer.parseInt(rs.getString("userid")));
			postComment.setPostId(Integer.parseInt(rs.getString("postid")));
			postComment.setComment(rs.getString("comment"));
			postComment.setDateCreated(rs.getString("datecreated"));
			postComment.setDateUpdated(rs.getString("dateupdated"));

			comObject.add(postComment);
		}

		System.out.println(comObject.toString());
		return comObject;
	}

	public boolean insertComment(String userEmail, int postId, String comment) throws Exception {
		boolean flag = false;
		int userId = 0;
		Connection con = (Connection) DBConnection.getConnection();
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT userid from users WHERE email='" + userEmail + "'");
		ResultSet rs = ps1.executeQuery();
		while (rs.next()) {
			userId = Integer.parseInt(rs.getString("userid"));
		}
		String commentInsert = "INSERT INTO comments (userid,postid,comment) VALUES(" + userId + "," + postId + ",'" + comment
				+ "')";
		PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(commentInsert);
		ps2.executeUpdate();
		flag = true;
		return flag;
	}
	
	public boolean deleteComment(int postId) throws Exception {
		boolean flag = false;
		
		Connection con = (Connection) DBConnection.getConnection();
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM comments WHERE postid='" + postId + "'");
		ResultSet rs = ps1.executeQuery();
		
		if (!rs.isBeforeFirst() ) {
		    flag =  true; 
		}else{
			PreparedStatement ps2 = (PreparedStatement) con.prepareStatement("DELETE FROM comments WHERE postid='" + postId + "'");
			ps2.executeUpdate();
			flag = true;
		}
		
		return flag;
	}

}
