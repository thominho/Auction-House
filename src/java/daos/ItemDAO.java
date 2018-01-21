package daos;

import entities.Bid;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.PersistenceException;
import java.util.List;


import entities.Item;
import java.util.Iterator;
import javax.faces.context.FacesContext;

public class ItemDAO
{
    
    public static List<Item> findItems(String category)
    {
        EntityManager em=Factory.factory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        
        Query q=em.createQuery("SELECT i FROM Item i");
        
        List<Item> items=q.getResultList();
        
        et.commit();
        em.close();
        
        Iterator<Item> iter=items.iterator();
        while(iter.hasNext())
        {
            Item item=iter.next();
            if(item.getCategories().contains(category)==false)
                iter.remove();
        }
        
        return items;
    }
    
    public static List<Item> findItems(String category,String description,float price,String location)
    {
        EntityManager em=Factory.factory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        
        Query q=em.createQuery("SELECT i FROM Item i WHERE i.description=:description and i.currently<=:price and i.location.name=:location");
        q.setParameter("description", description);
        q.setParameter("price", price);
        q.setParameter("location", location);
        
        List<Item> items=q.getResultList();
        
        et.commit();
        em.close();
        
        Iterator<Item> iter=items.iterator();
        while(iter.hasNext())
        {
            Item item=iter.next();
            if(item.getCategories().contains(category)==false)
                iter.remove();
        }
        
        return items;
    }
    
    public static boolean updateItem(Item item){
        EntityManager em=Factory.factory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        try
        {
            em.merge(item);
            et.commit();
            return true;
        }
        catch(PersistenceException e)
        {
            FacesContext.getCurrentInstance().getExternalContext().log(e.getMessage());
            if(et.isActive())et.rollback();
            return false;
        }
        finally
        {
         em.close();
        }
    }
    
    public boolean addItem(Item addItem)
    {
        EntityManager em=Factory.factory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        try
        {
            em.persist(addItem);
            et.commit();
            return true;
        }
        catch(PersistenceException e)
        {
            FacesContext.getCurrentInstance().getExternalContext().log(e.getMessage());
            if(et.isActive())et.rollback();
            return false;
        }
        finally
        {
         em.close();
        }
    }
    
}
