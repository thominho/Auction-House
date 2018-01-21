package daos;

import embedded.Bidder;
import entities.Admin;
import entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class UserDAO {
    public static List<Users> getAllUsers(){
        EntityManager em=Factory.factory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        
        Query q=em.createQuery("SELECT u FROM Users u");
        List<Users> list=q.getResultList();
        
        et.commit();
        em.close();
        
        return list;
    }
    
    public static boolean insert(Users user)
    {
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        
        try 
        {
            em.persist(user);
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
    
    public static boolean update(Users user)
    {
        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
	tx.begin();
        
        try 
        {
            em.merge(user);
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
    
    public static Users findUser(String username)
    {
        Users user = null;

        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from Users u where u.bidder.username = :username");
        q.setParameter("username", username);
        List users =  q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1)
        {
            user = (Users) users.get(0);
        }

        return user;

    }
    
    public static Admin adminLogin(String username, String password)
    {
        Admin admin = null;

        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from Admin u where u.username = :username and u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List list =  q.getResultList();
        tx.commit();
        em.close();

        if (list != null && list.size() == 1)
        {
            admin = (Admin) list.get(0);
        }

        return admin;
    }
    
    public static Users login(String username, String password)
    {
        Users user = null;

        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from Users u where u.bidder.username = :username and u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List users =  q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1)
        {
            user = (Users) users.get(0);
        }

        return user;
    }
    
    public static Bidder findBidder(String username)
    {
        Bidder bidder = null;

        EntityManager em = Factory.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("SELECT u FROM Users u WHERE u.bidder.username= :username");
        q.setParameter("username",username);
        List users =  q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1)
        {
            Users user = (Users) users.get(0);
            bidder=user.getBidder();
        }

        return bidder;

    }
}
