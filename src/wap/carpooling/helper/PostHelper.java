package wap.carpooling.helper;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;

import wap.carpooling.dto.DBConnection;
import wap.carpooling.model.Post;
import wap.carpooling.model.UserPost;

public class PostHelper {
	
	public boolean insertPost(String userEmail, Post post) throws Exception {
		boolean flag = false;
		int userId = 0;
		int postType = 0;
		Connection con = (Connection) DBConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT userid FROM users where email='" + userEmail + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			userId = Integer.parseInt(rs.getString("userid"));
		}
		if (post.getPostType().equals("offerride")) {
			postType = 1;
		}
		String insert = "INSERT into posts(userid,post,posttype,source,destination,latitudedestination,longitudedestination,loc)"
				+ " VALUES (" + userId + ",'" + post.getPost() + "'," + postType + ",'"+post.getSource()+"','"+post.getDestination()+"'"
						+ ", "+post.getDestinationLatitude()+","+post.getDestinationLongitude()+","
								+ "Point("+post.getDestinationLatitude()+","+post.getDestinationLongitude() +"))";
		System.out.println(insert);
		PreparedStatement psu = (PreparedStatement) con.prepareStatement(insert);
		psu.executeUpdate();
		flag = true;
		return flag;
	}

	public ArrayList<String> getOfferedRide25Post() {
		ArrayList postData = new ArrayList();
		try {
			PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
					"SELECT * FROM users u INNER JOIN posts p on u.userid=p.userid WHERE p.posttype=1 ORDER BY  p.dateupdated DESC LIMIT 3;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserPost userPost = new UserPost();
				
				userPost.setPostid(Integer.parseInt(rs.getString("postid")));
				userPost.setUserid(Integer.parseInt(rs.getString("userid")));
				userPost.setPosttype(Integer.parseInt(rs.getString("posttype")));
				userPost.setPost(rs.getString("post"));
				userPost.setDatecreated(rs.getString("datecreated"));
				userPost.setDatecreated(rs.getString("dateupdated"));
				userPost.setName(rs.getString("fullname"));
				
				postData.add(userPost);
			}
			return postData;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;

	}
	
	
	public ArrayList<String> getTakeRide25Post() {
		ArrayList postData = new ArrayList();
		try {
			PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
					"SELECT * FROM users u INNER JOIN posts p on u.userid=p.userid WHERE p.posttype=0 ORDER BY p.dateupdated DESC LIMIT 3;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserPost userPost = new UserPost();
				
				userPost.setPostid(Integer.parseInt(rs.getString("postid")));
				userPost.setUserid(Integer.parseInt(rs.getString("userid")));
				userPost.setPosttype(Integer.parseInt(rs.getString("posttype")));
				userPost.setPost(rs.getString("post"));
				userPost.setDatecreated(rs.getString("datecreated"));
				userPost.setDatecreated(rs.getString("dateupdated"));
				userPost.setName(rs.getString("fullname"));
				
				postData.add(userPost);
			}
			return postData;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}
	
	public ArrayList<String> getNext15Post(String postType,int lastPostId) {
		ArrayList postData = new ArrayList();
		int pt;
		if(postType.equals("Offers")){
			pt = 1;
		}else{
			pt = 0;
		}
		try {
			PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
					"SELECT * FROM users u INNER JOIN posts p on u.userid=p.userid WHERE p.posttype="+pt+" AND p.postid < "+lastPostId+" ORDER BY  p.dateupdated DESC LIMIT 2;");
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserPost userPost = new UserPost();
				
				userPost.setPostid(Integer.parseInt(rs.getString("postid")));
				userPost.setUserid(Integer.parseInt(rs.getString("userid")));
				userPost.setPosttype(Integer.parseInt(rs.getString("posttype")));
				userPost.setPost(rs.getString("post"));
				userPost.setDatecreated(rs.getString("datecreated"));
				userPost.setDatecreated(rs.getString("dateupdated"));
				userPost.setName(rs.getString("fullname"));
				//System.out.println("UserID: "+rs.getString("userid") + "UserName: "+ rs.getString("fullname"));
				postData.add(userPost);
			}
			return postData;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}
	
	public boolean deletePost(int postId) throws Exception {
		boolean flag = false;
		
		Connection con = (Connection) DBConnection.getConnection();
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM posts WHERE postid='" + postId + "'");
		ResultSet rs = ps1.executeQuery();
		
		if (!rs.isBeforeFirst() ) {    
		    flag =  true; 
		}else{
			PreparedStatement ps2 = (PreparedStatement) con.prepareStatement("DELETE FROM posts WHERE postid='" + postId + "'");
			ps2.executeUpdate();
			flag = true;
		}
		
		return flag;
	}

}
