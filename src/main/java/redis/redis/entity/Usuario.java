package redis.redis.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String name;
    private Integer edad;

    public Usuario() {
    }

    public Usuario(String name, Integer edad) {
        this.name = name;
        this.edad = edad;
    }
 // toString, getters y setters
    @Override
    public String toString() {
        return "Usuario{" +
            "name='" + name + '\'' +
            ", edad=" + edad +
            '}';
    }


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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
