package bootwildfly.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

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
    //public List<Test> tests;
    public boolean published;

    public Problem() { }


}
