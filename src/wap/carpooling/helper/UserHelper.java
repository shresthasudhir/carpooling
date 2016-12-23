package wap.carpooling.helper;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import wap.carpooling.dto.DBConnection;
import wap.carpooling.model.Post;
import wap.carpooling.model.User;
import wap.carpooling.model.UserPost;

public class UserHelper {
	
	public int getUserId(String sessionEmail) throws Exception {
		try{
			int userId = 0;
			Connection con = (Connection) DBConnection.getConnection();
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT userid from users WHERE email='" + sessionEmail + "'");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("userid"));
			}
			return userId;
			
		}catch (Exception ex) {
			System.out.println(ex.toString());
			return 0;
		}
	}

	public boolean insertUser(User user) throws Exception {
		String passwordmd = "";
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(user.getPassword().getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			passwordmd = sb.toString();
			int m = 0;
			if (user.getGender().equals("Male")) {
				m = 1;
			} else if (user.getGender() == "" || user.getGender() == null || user.getGender().isEmpty()) {
				m = 1;
			} else {
				m = 0;
			}

			Connection con = (Connection) DBConnection.getConnection();
			System.out.println("after");
			String insert = "INSERT INTO users(fullname,gender,state,city,street,zipcode,birthyear,email,password) "
					+ "values ('" + user.getFullName() + "'," + m + ",'" + user.getState() + "','" + user.getCity()
					+ "','" + user.getStreet() + "'," + user.getZipCode() + "," + user.getBirthYear() + ",'"
					+ user.getEmail() + "'," + "'" + passwordmd + "')";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(insert);
			stat.executeUpdate();
			// System.out.println(passwordmd);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	public boolean checkUserLogin(String email, String password) throws Exception {
		String emailmd = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		emailmd = sb.toString();
		System.out.println(emailmd);
		Connection con = (Connection) DBConnection.getConnection();
		String query = "SELECT email,password FROM users";
		PreparedStatement stat = (PreparedStatement) con.prepareStatement(query);
		ResultSet res = stat.executeQuery();
		while (res.next()) {
			String mail = res.getString("email").toString();
			String pass = res.getString("password").toString();
			if (email.equals(mail) && pass.equals(emailmd)) {
				return true;
			}
		}

		return false;
	}

	public User getUserInformation(String userEmail) {
		User user = new User();
		
		try{
			Connection con = (Connection) DBConnection.getConnection();
			String query = "SELECT * from users WHERE email='" + userEmail + "'";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(query);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				user.setFullName(res.getString("fullname").toString());
				int gender = res.getInt("gender");
				if (gender == 1)
					user.setGender("Male");
				else
					user.setGender("Female");
				user.setState(res.getString("state").toString());
				user.setCity(res.getString("city").toString());
				user.setStreet(res.getString("street").toString());
				user.setZipCode(res.getInt("zipcode"));
				user.setBirthYear(res.getInt("birthyear"));
				user.setEmail(res.getString("email"));

			}
			return user;
			
		}catch(Exception ex){
			System.out.println(ex.toString());
			return user;
		}

	}
	
	public boolean updateUserProfile(User user,String sessionMail) throws Exception {
		String passwordmd = "";
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(user.getPassword().getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			passwordmd = sb.toString();
			int m = 0;
			if (user.getGender().equals("Male")) {
				m = 1;
			} else if (user.getGender() == "" || user.getGender() == null || user.getGender().isEmpty()) {
				m = 1;
			} else {
				m = 0;
			}

			int userId = 0;
			Connection con = (Connection) DBConnection.getConnection();
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT userid from users WHERE email='" + sessionMail + "'");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("userid"));
			}
			
			
			String update = "UPDATE users SET fullname='"+user.getFullName()+"', gender="+m+", state='"+user.getState()+"', "
					+ "city='"+user.getCity()+"', street='"+user.getStreet()+"', zipcode="+user.getZipCode()+", "
							+ "birthyear="+user.getBirthYear()+", email='"+user.getEmail()+"', password='"+passwordmd+"' "
					+ "WHERE userid="+userId+" ";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(update);
			stat.executeUpdate();
			
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
	
	public User getUserWeather(String sessionEmail) throws Exception {
		User user = new User();
		try{
			System.out.println(sessionEmail);
			int userId = 0;
			Connection con = (Connection) DBConnection.getConnection();
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("SELECT * from users WHERE email='" + sessionEmail + "'");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				user.setZipCode(Integer.parseInt(rs.getString("zipcode")));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
			}
			return user;
			
		}catch (Exception ex) {
			System.out.println(ex.toString());
			return user;
		}
	}
	
	public ArrayList<String> getUserNearByTrips(Double destinationLatitude, Double destinationLongitude) throws Exception{
		ArrayList userData = new ArrayList();
		try {
			PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
			"SELECT dest.destination,dest.latitudedestination,dest.longitudedestination,distance(orig.loc, dest.loc) as sdistance "
			+ "FROM posts orig,posts dest"
			+ " WHERE orig.latitudedestination ='"+destinationLatitude+"' and orig.longitudedestination='"+destinationLongitude
			+ "' having sdistance <= 20.0 ORDER BY sdistance limit 10;");
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				
				post.setDestination(rs.getString("destination"));
				
				userData.add(post);
			}
			return userData;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}
	
	public ArrayList<String> getAllTrips() throws Exception{
		ArrayList userData = new ArrayList();
		try {
			PreparedStatement ps = (PreparedStatement) DBConnection.getConnection().prepareStatement(
			"SELECT destination FROM posts");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				
				post.setDestination(rs.getString("destination"));
				
				userData.add(post);
			}
			return userData;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}

}
