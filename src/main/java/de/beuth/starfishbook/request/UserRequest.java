package de.beuth.starfishbook.request;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import de.beuth.starfishbook.model.ERoles;


public class UserRequest {
 

    private Long userid;
    private String email;
    private String password;
    private boolean enabled;
    private ERoles roles;

      
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