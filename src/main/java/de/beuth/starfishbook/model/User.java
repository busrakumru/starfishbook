package de.beuth.starfishbook.model;


import javax.persistence.*;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long userid;


  @Enumerated(EnumType.STRING)
  private ERoles roles;


  @Column(unique = true)
  private String email;

  // @JsonIgnore
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

  private String userName;

  public User(Long userid, String email, String password,ERoles roles ) {
    this.userid = userid;
    this.email = email;
    this.password = password;
    this.enabled = false;
    this.roles =roles;

  }

  public User() {
  }
	
  public String getUserName() {
    return userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getEnabled() {
    return enabled;
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

  public ERoles getRoles() {
    return roles;
  }

  public void setRoles(ERoles roles) {
    this.roles = roles;
  }
 
}
