package orm.entity;

import jakarta.persistence.Access;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import static jakarta.persistence.AccessType.FIELD;

@Entity
@Access(FIELD)
@Table(name = "DOCUMENT")
@Data
public class DocumentEmbedding {

    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private DocPK id = new DocPK();

    private String text;
}

