package orm;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.SynchronizationType;

import javax.transaction.Transaction;

public class CreateEntityManager {
    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("in_mem_db");
        var em = emf.createEntityManager();
    }
}
