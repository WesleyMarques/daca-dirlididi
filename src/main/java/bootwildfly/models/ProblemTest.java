package bootwildfly.models;

import javax.persistence.*;

@Entity
public class ProblemTest {

    @Id
    @GeneratedValue
    private Long id;

//    @Column(nullable = false)
    private String name;

    private String tip;
    private String input;
    private String output;
    private boolean visible;


    public ProblemTest() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
