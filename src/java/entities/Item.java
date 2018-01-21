package entities;

import embedded.Location;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Item")
@Entity
@Table(name="ITEMS")
public class Item implements Serializable {
    @XmlAttribute(name="ItemID",required=true)
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;
    
    @XmlElement(name="Name",required=true)
    @Column(name="NAME",nullable=false)
    private String name;
    
    @XmlElement(name="Category",required=true)
    @Column(name="CATEGORIES",nullable=false)
    private List<String> categories;
    
    @XmlElement(name="Currently",required=true)
    @Column(name="CURRENT_BID",nullable=false)
    private float currently;
    
    @XmlElement(name="Buy_Price",required=false)
    @Column(name="BUY_PRICE",nullable=true)
    private float buyPrice;
    
    @XmlElement(name="First_Bid",required = true)
    @Column(name = "FIRST_BID",nullable = false)
    private float firstBid;
    
    @XmlElement(name="Number_of_Bids",required = true)
    @Column(name = "NUMBER_OF_BIDS",nullable = false)
    private int numberOfBids;
    
    @XmlElementWrapper(name="Bids")
    @XmlElement(name="Bid",required = false)
    @OneToMany(mappedBy = "item")
    private List<Bid> bids;
    
    @XmlElement(name="Location",required = true)
    @Embedded
    private Location location;
    
    @XmlElement(name="Country",required = true)
    @Column(name="COUNTRY",nullable = false)
    private String country;
    
    @XmlElement(name="Started",required = true)
    @Column(name="START_TIME",nullable = false)
    private Timestamp startTime;
    
    @XmlElement(name="Ends",required = true)
    @Column(name="END_TIME",nullable = false)
    private Timestamp endTime;
    
    @XmlElement(name="Seller",required=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SELLER_ID")
    private Users seller;
    
    @XmlElement(name="Description",required = true)
    @Column(name="DESCRIPTION",nullable = false)
    private String description;

    public void setNewLocation(String city,String longitude,String latitude){
        this.location=new Location();
        this.location.setName(city);
        this.location.setLongitude(longitude);
        this.location.setLatitude(latitude);
    }
    
    public boolean removeCategory(String cat){
        return categories.remove(cat);
    }
    
    public boolean addCategory(String cat){
        return categories.add(cat);
    }
    
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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

    public float getCurrently() {
        return currently;
    }

    public void setCurrently(float currently) {
        this.currently = currently;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(float firstBid) {
        this.firstBid = firstBid;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
    public Date getStartDate(){
        return startTime==null ? null : new Date(startTime.getTime());
    }
    
    public void setStartDate(Date date){
        this.startTime= date==null ? null : new Timestamp(date.getTime());
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    public Date getEndDate(){
        return endTime==null ? null : new Date(endTime.getTime());
    }
    
    public void setEndDate(Date date){
        this.endTime= date==null ? null : new Timestamp(date.getTime());
    }

    public Users getSeller() {
        return seller;
    }

    public void setSeller(Users seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addBid(Bid bid){
        bids.add(bid);
        numberOfBids++;
        currently=bid.getAmount();
    }
}
