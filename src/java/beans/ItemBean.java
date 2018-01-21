package beans;

import daos.BidDAO;
import daos.ItemDAO;
import daos.UserDAO;
import entities.Bid;
import entities.Item;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@ManagedBean
@SessionScoped
public class ItemBean {
    private String category;
    private String description;
    private float price;
    private String location;
    private String errorMsg;
    private String successMsg;
    private List<Item> results;
    private Item currentItem;
    private float amount;
    
    public String back(){
        errorMsg=null;
        successMsg=null;
        currentItem=null;
        
        return "/search";
    }
    
    public String home()
    {
        errorMsg=null;
        successMsg=null;
        currentItem=null;
        
        HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserBean userBean=(UserBean)session.getAttribute("userBean");
        if(userBean==null) return "/index";
        if(userBean.isLoggedAsAdmin()) return "/adminPages/userManagement";
        if(userBean.isLogged()) return "/protected/homepage";
        return "/index";
    }
    
    public void categoriesToStr()
    {
        category=currentItem.getCategories().toString();
        category=category.substring(1, category.length()-1);
    }
    
    public void removeCategory(String cat){
        Date date=new Date();
        if(currentItem.getStartTime().before(new Timestamp(date.getTime())))
        {
            errorMsg="You cannot change the data of an auction that has already started";
            successMsg=null;
            return;
        }
        if(currentItem.removeCategory(cat)==false)
        {
            errorMsg="Failed to remove category form categories list";
            successMsg=null;
        }
    }
    
    public void addCategory(){
        Date date=new Date();
        Timestamp currentTime=new Timestamp(date.getTime());
        if(currentItem.getStartTime().before(currentTime))
        {
            errorMsg="You cannot change the data of an auction that has already started";
            successMsg=null;
            return;
        }
        if(currentItem.addCategory(category)==false)
        {
            errorMsg="Failed to add category on the list";
            successMsg=null;
        }
    }
    
    public void update()
    {
        Date date=new Date();
        if(!currentItem.getStartTime().before(new Timestamp(date.getTime())))
        {
            if(ItemDAO.updateItem(currentItem)==false)
            {
                errorMsg="Failed to update database";
                successMsg=null;
                return;
            }
            errorMsg=null;
            successMsg="Item was successfully updated";
        }
        else
        {
            errorMsg="You cannot change the data of an auction that has already started";
            successMsg=null;
        }
    }
    
    public String search_item(){
        if(category=="")
        {
            errorMsg="The category must be given";
            successMsg=null;
            return "/search";
        }
        if(description=="" && price==0.0 && location=="")
        {
            results=ItemDAO.findItems(category);
            if(results==null)
            {
                errorMsg="No results found";
                successMsg=null;
                return "/search";
            }
            errorMsg=null;
            return "/searchResults";
        }
        if(description!="" && price!=0.0 && location!="")
        {
            results=ItemDAO.findItems(category, description, price, location);
            if(results==null)
            {
                errorMsg="No results found";
                successMsg=null;
                return "/search";
            }
            errorMsg=null;
            return "/searchResults";
        }
        
        errorMsg="The description, price and location fields must either be all filled, or all left empty";
        successMsg=null;
        return "/search";
    }
    
    public String bid(){
        if(currentItem==null) return "/errorPages/itemNotFound";
        if(currentItem.getBuyPrice()!=0)
        {
            if(amount>currentItem.getBuyPrice())
            {
                successMsg=null;
                errorMsg="You can buy the item for the Buy Price you know. No need to bid so much money!";
                return "/searchResults";
            }
            if(amount==currentItem.getBuyPrice())
            {
                successMsg="The item was bought successfully";
                errorMsg=null;
                Bid newBid=new Bid();
                newBid.setAmount(amount);
                newBid.setItem(currentItem);
                
                Date date=new Date();
                newBid.setTime(new Timestamp(date.getTime()));
                
                HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                UserBean userBean=(UserBean)session.getAttribute("userBean");
                
                newBid.setBidder(UserDAO.findBidder(userBean.getUsername()));
                
                if(BidDAO.insert(newBid)==false)
                {
                    errorMsg="Failed to insert bid to database";
                    successMsg=null;
                }
                else if(ItemDAO.updateItem(currentItem)==false)
                {
                    errorMsg="Failed to add bid to item in database";
                    successMsg=null;
                }
                else
                {
                    successMsg="You successfully bidded on the item.";
                    errorMsg=null;
                }
                
                return "/searchResults";
            }
        }
        if(amount<=currentItem.getCurrently())
        {
            successMsg=null;
            errorMsg="You must bid more than the current bid.";
            return "/searchResults";
        }
        
        Bid newBid=new Bid();
        newBid.setAmount(amount);
        newBid.setItem(currentItem);

        Date date=new Date();
        newBid.setTime(new Timestamp(date.getTime()));

        HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserBean userBean=(UserBean)session.getAttribute("userBean");

        newBid.setBidder(UserDAO.findBidder(userBean.getUsername()));
        
        currentItem.addBid(newBid);
        
        if(BidDAO.insert(newBid)==false)
        {
            errorMsg="Failed to insert bid to database";
            successMsg=null;
        }
        else if(ItemDAO.updateItem(currentItem)==false)
        {
            errorMsg="Failed to add bid to item in database";
            successMsg=null;
        }
        else
        {
            successMsg="You successfully bidded on the item.";
            errorMsg=null;
        }
        
        return "/searchResults";
    }
    
    public String exportXML() throws JAXBException{
        if(currentItem==null) return "/errorPages/itemNotFound";
        
        JAXBContext context=JAXBContext.newInstance(Item.class);
        Marshaller marshaller=context.createMarshaller();
        marshaller.marshal(currentItem,new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/marshalled")+"/"+currentItem.getItemID()+".xml"));
        
        successMsg="xml outputed successully";
        
        return "/searchResults";
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
}
