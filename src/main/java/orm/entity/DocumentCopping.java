
package orm.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.AccessType.FIELD;

@Entity
@Access(FIELD)
@IdClass(DocPK.class)
@Table(name = "DOCUMENT")
@Data
public class DocumentCopping {

    @Id
    private String name;

    @Id
    private Integer version;

    private String text;
}

