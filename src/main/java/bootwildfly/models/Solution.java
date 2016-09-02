package bootwildfly.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Solution {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    public String body;

    //public List<Output> outputs;

    public Solution() { }
}
