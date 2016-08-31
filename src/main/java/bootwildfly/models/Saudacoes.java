package bootwildfly.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Saudacoes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    public String assunto;

    @Column()
    public String remetente;

    protected Saudacoes() { }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAssunto() { return this.assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }

    public String getRemetente() { return remetente;}
    public void setRemetente(String remetente) { this.remetente = remetente; }
    
}
