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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Location")
@Embeddable
public class Location implements Serializable {
    @XmlValue
    @Column(name="LOCATION",nullable = false,table = "ITEMS")
    private String name;
    
    @XmlAttribute(name="Longitude",required = false)
    @Column(name="LONGITUDE",nullable = true,table = "ITEMS")
    private String longitude;
    
    @XmlAttribute(name="Latitude",required = false)
    @Column(name="LATITUDE",nullable = true,table = "ITEMS")
    private String latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
}
