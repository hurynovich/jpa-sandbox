package orm.entity.wrong;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import static jakarta.persistence.AccessType.PROPERTY;

/**
 * This is example of wrongly configured entity.
 * {@link Access} is set to {@code PROPERTY} but annotations {@link Id}
 * are placed on field which is mismatch.
 */
@Entity
@Access(PROPERTY)
public class Penguin {

    // Wrong, access type is property but field was annotated
    @Id
    private Integer id;
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    // Correct, getter was annotated since Access type is property
    private String name;
    @Column
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
