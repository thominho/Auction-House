package entities;

import embedded.Bidder;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name="USERS")
public class Users implements Serializable {
    @XmlTransient
    @Id
    @GeneratedValue
    @Column(name="ID",nullable = false)
    private int userID;
    
    @XmlTransient
    @OneToMany(mappedBy = "seller")
    private List<Item> items;
    
    @XmlElement(name="UserID",required = true)
    @Transient
    private String username;
    
    @XmlTransient
    @Column(name="PASSWORD",nullable = false)
    private String password;
    
    @XmlTransient
    @Column(name="FIRST_NAME",nullable = false)
    private String firstName;
    
    @XmlTransient
    @Column(name="LAST_NAME",nullable = false)
    private String lastName;
    
    @XmlTransient
    @Column(name="EMAIL",nullable = false)
    private String email;
    
    @XmlTransient
    @Column(name="PHONE",nullable = false)
    private String phone;
    
    @XmlTransient
    @Embedded
    private Bidder bidder;
    
    @XmlTransient
    @Column(name="ADDRESS",nullable = false)
    private String address;
    
    @XmlTransient
    @Column(name="POSTAL_CODE",nullable = false)
    private String postalCode;
    
    @XmlTransient
    @Column(name="TAX_NUMBER",nullable = false)
    private String taxNumber;
    
    @XmlAttribute(name="Rating",required = true)
    @Column(name="SELLER_RATING",nullable = false)
    private int sellerRating;
    
    @XmlTransient
    @OneToMany(mappedBy = "sender")
    private List<Messages> messagesSent;
    
    @XmlTransient
    @OneToMany(mappedBy = "receiver")
    private List<Messages> messagesReceived;
    
    @XmlTransient
    @Column(name="ACCEPTED")
    private boolean accepted;

    public Users(){
        accepted=false;
    }

    public List<Messages> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<Messages> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<Messages> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<Messages> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }
    
    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public int getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(int sellerRating) {
        this.sellerRating = sellerRating;
    }
    
    public void removeMessageSent(Messages message){
        this.messagesSent.remove(message);
    }
    
    public void removeMessageReceived(Messages message){
        this.messagesReceived.remove(message);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.userID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (this.userID != other.userID) {
            return false;
        }
        return true;
    }
    
    public void addItem(Item newItem){
        if(items!=null)
            this.items.add(newItem);
        else
            this.items=new LinkedList<Item>();
    }
}
