package daos;

import entities.Messages;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class MessageDAO {
    public static boolean insert(Messages message){
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        try 
        {
            em.persist(message);
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
    
    public static boolean update(Messages message){
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        try 
        {
            em.merge(message);
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
    
    public static boolean delete(Messages message){
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        try 
        {
            em.remove(em.merge(message));
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
