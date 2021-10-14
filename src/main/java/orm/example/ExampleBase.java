package orm.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import org.junit.jupiter.api.*;
import orm.Constans;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class ExampleBase {
    @Getter
    private EntityManagerFactory emf;
    @Getter
    private EntityManager em;

    @BeforeAll
    void beforeAll() {
        emf = createEntityManagerFactory(Constans.PU_ONE_IN_MEM_DB);
    }

    @AfterAll
    void afterAll() {
        if(emf.isOpen()) emf.close();
    }

    @BeforeEach
    void tearUp() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        if (em.isOpen()) em.close();
    }

}
