package bootwildfly.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    public String name;

    public String tip;
    public String input;
    public String output;
    public boolean visible;

    public Test() { }

}
