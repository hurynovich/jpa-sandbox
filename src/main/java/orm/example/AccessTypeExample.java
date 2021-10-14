package orm.example;

import static jakarta.persistence.Persistence.*;
import org.hibernate.AnnotationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static orm.Constans.PU_WRONG_ONE;

/**
 * See spec: <a href="https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html#a113">Access Type</a>
 */
public class AccessTypeExample {
    /**
     * Example when {@link jakarta.persistence.Access} contradicts other entity annotations.
     * @see orm.entity.wrong.Penguin
     */
    @Test
    void wrongAccessTypeConfiguration() {
        var ex = assertThrows(AnnotationException.class, () -> createEntityManagerFactory(PU_WRONG_ONE));
        assertTrue(ex.getMessage().startsWith("No identifier specified for entity"));
    }
}
