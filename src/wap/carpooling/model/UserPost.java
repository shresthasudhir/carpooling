package wap.carpooling.model;

public class UserPost {
	private int postid;
	private int userid;
	private String post;
	private int posttype;
	private String datecreated;
	private String dateupdated;
	private String name;
	
	public UserPost(){
		
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public int getPosttype() {
		return posttype;
	}

	public void setPosttype(int posttype) {
		this.posttype = posttype;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	public String getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(String dateupdated) {
		this.dateupdated = dateupdated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
