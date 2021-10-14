package orm.example;

import static jakarta.persistence.Persistence.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import org.hibernate.AnnotationException;
import org.junit.jupiter.api.*;
import orm.Constans;
import orm.entity.Monkey;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static orm.Constans.PU_WRONG_ONE;

/**
 * See spec: <a href="https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html#a113">Access Type</a>
 */

public class AccessTypeExample extends ExampleBase{

    /**
     * Attributes of entity are discovered according to Access type.
     * It is not necessary to annotate attribute with {@link jakarta.persistence.Basic} or {@link jakarta.persistence.Column}.
     * If Access type is {@link jakarta.persistence.AccessType#FIELD} then all fields are considered as entity attributes
     * regardless of access modifier or getter/setter presence.
     * Annotation {@link jakarta.persistence.Transient} is used to mark attribute as not persistent.
     *
     * @see orm.entity.Monkey
     */
    @Test
    void attributesDiscovery() {
        Monkey monkey = new Monkey();
        //explicit attribute
        monkey.setName("Sofa");

        //implicit attribute
        monkey.setYear(2021);

        //implicit attribute without getters and setters
        monkey.paint("Red");

        //ignored attribute
        monkey.setTmpNote("Fluffy");


        //save entity to DB
        getEm().getTransaction().begin();
        getEm().persist(monkey);
        Long id = monkey.getId();
        assertNotNull(id, "Entity was not stored since Id is empty.");
        getEm().getTransaction().commit();

        //detach entity to remove it from cache (persistence context)
        getEm().detach(monkey);

        //read entity and check which attributes were persisted
        Monkey monkeyRestored = getEm().find(Monkey.class, id);
        assertEquals("Sofa", monkeyRestored.getName());
        assertEquals(2021, monkeyRestored.getYear());
        assertEquals("Red", monkeyRestored.see());
        assertNull(monkeyRestored.getTmpNote());
    }

    /**
     * If annotation place contradicts Access type then such annotations will not be processed.
     * When Access type is {@code FIELD} than annotations must be placed on field.
     * When Access type is {@code PROPERTY} than annotations must be placed on getter.
     *
     * @see orm.entity.wrong.Penguin
     */
    @Test
    void wrongAccessTypeConfiguration() {
        var ex = assertThrows(AnnotationException.class, () -> createEntityManagerFactory(PU_WRONG_ONE));
        assertTrue(ex.getMessage().startsWith("No identifier specified for entity"));
    }
}
