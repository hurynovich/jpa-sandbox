package orm.entity.wrong;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import static jakarta.persistence.AccessType.PROPERTY;

/**
 * This is example of wrongly configured entity.
 * {@link Access} is set to {@code PROPERTY} but annotations {@link Id}
 * and {@link Column} are placed on fields which is mismatch.
 */
@Entity
@Access(PROPERTY)
public class Penguin {

//  Wrong
    @Id
    private Integer id;
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

//  Wrong
    @Column
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

//  Correct
    private Double wight;
    @Column
    public Double getWight() {return wight;}
    public void setWight(Double wight) {this.wight = wight;}
}
