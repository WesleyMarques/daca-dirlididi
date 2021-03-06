package bootwildfly.models;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

	private String socialId;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<Problem> resolvidos = new ArrayList<>();
    
    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Solution> solutions = new ArrayList<>();

	@Transient
	private Principal principal;

	public String getSocialId() {
		return socialId;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public List<Problem> getResolvidos() {
		return resolvidos;
	}

	public void setResolvidos(List<Problem> resolvidos) {
		this.resolvidos = resolvidos;
	}

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
