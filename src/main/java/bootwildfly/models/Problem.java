package bootwildfly.models;

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
    public String name;

    @Column(nullable = false)
    public String description;

    public String code;
    public String tip;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Test> tests = new ArrayList<>();

    public boolean published = false;

    public Problem() { }


}
