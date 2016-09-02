package bootwildfly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public User() { }


}
