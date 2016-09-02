package bootwildfly.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    @JsonIgnore
    public String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="problem_id")})
	public List<Problem> resolvidos = new ArrayList<>();

    public boolean type;
    
    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;

    public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
