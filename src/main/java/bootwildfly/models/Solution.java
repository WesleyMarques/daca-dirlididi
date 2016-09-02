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
    public String body;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Output> outputs = new ArrayList<>();

    public Solution() { }
}
