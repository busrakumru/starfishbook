package de.beuth.starfishbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "user_id")
    private Long userid;

    
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String emailId;

    @Column(unique = true)
    private String email;

    //@JsonIgnore
    private String password;

    private boolean isEnabled;



    public User(Long userid, String email, String emailId, String password, boolean isEnabled) {
       this. userid = userid;
       this.email = email;
       this.emailId = emailId;
       this.password = password;
       this.isEnabled = isEnabled;


    }

    public User() {
    }

    public String getEmailId(){
        return emailId;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
