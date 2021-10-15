package orm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.io.Serializable;

import static jakarta.persistence.AccessType.FIELD;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Access(FIELD)
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocPK implements Serializable {
    private String name;

    @GeneratedValue(strategy = SEQUENCE)
    private Integer version;
}