package orm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.AccessType.FIELD;
import static jakarta.persistence.AccessType.PROPERTY;

/**
 * This example shows that if Access type is field than all
 * fields are automatically considered to be persisted.
 */
@Entity
@Access(FIELD)
public class Monkey {

    public Monkey(){}

    //Id must be annotated
    @Id @GeneratedValue
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Column
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    //this field is not annotated but it will be persisted
    private Integer year;
    public Integer getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}

    //This field don't nither getter nor setter but will be persisted
    private String color;
    public void paint(String color){this.color = color;}
    public String see(){return color;}

    //field must be marked Transient to be not persisted
    @Transient
    private String tmpNote;
    public String getTmpNote() {return tmpNote;}
    public void setTmpNote(String tmpNote) {this.tmpNote = tmpNote;}
}
