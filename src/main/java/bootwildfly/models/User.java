package bootwildfly.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    @JsonIgnore
    public String password;

    public boolean type;
    
    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordHash(String passcrypted){
		this.password = passcrypted;		
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() { }


}
