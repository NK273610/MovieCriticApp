package com.dalhousie.moviecritic.Data;

public class User {

    private String username;
    private String userpass;
    private String firstname;
    private String lastname;
    private String useremail;
    private String salt;
    private String imagePath;
    

    public User() {

        super();
    }

    public User(String username, String userpass, String firstname, String lastname, String useremail,String salt,String imagePath) {
        this.username = username;
        this.userpass = userpass;
        this.firstname = firstname;
        this.lastname = lastname;
        this.useremail = useremail;
        this.salt = salt;
        this.imagePath = imagePath;
	
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
   
}
