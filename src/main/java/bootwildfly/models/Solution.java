package bootwildfly.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solution {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String body;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Output> outputs = new ArrayList<>();

    public Solution() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
}
