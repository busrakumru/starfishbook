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

import de.beuth.starfishbook.model.Role;

public class UserRequest {
 

    private Long userid;
    private Set<String> roles = new HashSet<>();
    private String email;
    private String password;
    private boolean enabled;

      
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
    
    public Set<String> getRoles() {
          return roles;
      }
  
      public void setRoles(Set<String> roles) {
          this.roles = roles;
      }
  }