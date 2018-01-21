package daos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("FinalPU");
}
