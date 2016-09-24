package bootwildfly.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Output {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name="test_id", nullable = false)
    private ProblemTest test;

    public Output() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProblemTest getTest() {
        return test;
    }

    public void setTest(ProblemTest test) {
        this.test = test;
    }
}
