package daos;

import entities.Bid;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class BidDAO {
    public static boolean insert(Bid bid){
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        try 
        {
            em.persist(bid);
            tx.commit();
            return true;
        }
        catch (PersistenceException e)
        {
            if (tx.isActive()) tx.rollback();
            return false;
        }
        finally 
        {
            em.close();
        }
    }
}
