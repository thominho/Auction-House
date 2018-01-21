package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entities.Item;
import daos.ItemDAO;
import daos.UserDAO;
import entities.Users;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class ItemCreationBean {
    private String name;
    private List<String> categories;
    private String location;
    private String longitude;
    private String latitude;
    private String category;
    private float currently;
    private float buyprice;
    private float firstbid;
    private int numberofbids;
    private String country;
    private String description;
    private Date date;
    private Date date2;
    String errormsg;
    String successmsg;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String Longitude) {
        this.longitude = Longitude;
    }

    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongtitude() {
        return longitude;
    }

    public void setLongtitude(String Longitude) {
        this.longitude = Longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String Latitude) {
        this.latitude = Latitude;
    }

     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCurrently() {
        return currently;
    }

    public void setCurrently(float currently) {
        this.currently = currently;
    }

    public float getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(float buyprice) {
        this.buyprice = buyprice;
    }

    public float getFirstbid() {
        return firstbid;
    }

    public void setFirstbid(float firstbid) {
        this.firstbid = firstbid;
    }

    public int getNumberofbids() {
        return numberofbids;
    }

    public void setNumberofbids(int numberofbids) {
        this.numberofbids = numberofbids;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
    public String registeritem()
    {   
        if(date.before(date2)==false)
        {
            successmsg=null;
            errormsg="Please Enter Valid Time";
            return "/protected/newAuction";
        }
        Item newItem=new Item();
        newItem.setName(name);
        categories=new LinkedList<String>(Arrays.asList(category.split(",")));
        newItem.setCategories(categories);
        newItem.setFirstBid(firstbid);
        newItem.setCurrently(firstbid);
        newItem.setBuyPrice(buyprice);
        newItem.setNumberOfBids(0);
        newItem.setNewLocation(location, longitude, latitude);
        newItem.setCountry(country);
        Timestamp stime=new Timestamp(date.getTime());
        Timestamp etime=new Timestamp(date2.getTime());
        newItem.setStartTime(stime);
        newItem.setEndTime(etime);
        newItem.setDescription(description);
        newItem.setBids(null);
        
        HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserBean userBean=(UserBean)session.getAttribute("userBean");
        if(userBean==null) return "/errorPages/noUser";
        Users user=UserDAO.login(userBean.getUsername(),userBean.getPassword());
        
        newItem.setSeller(user);
        newItem.setCountry(user.getBidder().getCountry());
        
        ItemDAO controller=new ItemDAO();
        if(controller.addItem(newItem))
        {
            errormsg=null;
            successmsg="Auction Added Successfully";
            userBean.setCurrentUser(user);
        }
        else
        {
            successmsg=null;
            errormsg="FATALITY";
        }
        return "/protected/newAuction";
    }
    
    public String home()
    {
        errormsg=null;
        successmsg=null;
        return "/protected/homepage";
    }
    
    public String back()
    {
        errormsg=null;
        successmsg=null;
        return "/protected/manageAuctions";
    }
}