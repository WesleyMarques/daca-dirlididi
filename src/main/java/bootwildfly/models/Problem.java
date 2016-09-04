package bootwildfly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="problem_id")
    private List<ProblemTest> tests = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Solution> solutions= new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "resolvidos")
    @JsonIgnore
    private List<User> usersResolvidos;

    public Problem() { }

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

    public List<ProblemTest> getTests() {
        return tests;
    }

    public void setTests(List<ProblemTest> tests) {
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
