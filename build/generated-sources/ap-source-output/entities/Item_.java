package entities;

import embedded.Location;
import entities.Bid;
import entities.Users;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-17T17:59:16")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Float> buyPrice;
    public static volatile SingularAttribute<Item, Users> seller;
    public static volatile SingularAttribute<Item, String> country;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile SingularAttribute<Item, Integer> numberOfBids;
    public static volatile SingularAttribute<Item, Integer> itemID;
    public static volatile SingularAttribute<Item, Float> currently;
    public static volatile SingularAttribute<Item, Float> firstBid;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile ListAttribute<Item, Bid> bids;
    public static volatile SingularAttribute<Item, Location> location;
    public static volatile SingularAttribute<Item, Timestamp> startTime;
    public static volatile SingularAttribute<Item, List> categories;
    public static volatile SingularAttribute<Item, Timestamp> endTime;

}