/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embedded;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Bidder")
@Embeddable
public class Bidder implements Serializable {
    @XmlAttribute(name="UserID",required = true)
    @Column(name="USERNAME",nullable = false,table = "USERS")
    private String username;
    
    @XmlAttribute(name="Rating",required = true)
    @Column(name="BIDDER_RATING",nullable = false,table = "USERS")
    private int rating;
    
    @XmlElement(name="Location",required = false)
    @Column(name="LOCATION",nullable = true,table = "USERS")
    /*city in the bean*/
    private String location;
    
    @XmlElement(name="Country",required = false)
    @Column(name="COUNTRY",nullable = true,table = "USERS")
    private String country;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
