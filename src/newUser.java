public abstract class newUser 
{
	protected String name;
	private String address;
	private String email;
	private String userId;
	private String password;
	
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUserID(String userID){
		this.userId = userID;
	}

	public String getUserId(){
		return userId;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}
}