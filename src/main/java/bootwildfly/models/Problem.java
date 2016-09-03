package bootwildfly.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
    private String code;
    private String tip;
    private boolean published = false;
    private boolean deleted = false;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "resolvidos")
    private List<User> usersResolvidos;

    public Problem() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<User> getUsersResolvidos() {
        return usersResolvidos;
    }

    public void setUsersResolvidos(List<User> usersResolvidos) {
        this.usersResolvidos = usersResolvidos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @PreRemove
    private void removeResolvidosFromUsers() {
        for (User u : getUsersResolvidos()) {
            u.getResolvidos().remove(this);
        }
    }
}
