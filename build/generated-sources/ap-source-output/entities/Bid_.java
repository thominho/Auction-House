package entities;

import embedded.Bidder;
import entities.Item;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-17T17:59:16")
@StaticMetamodel(Bid.class)
public class Bid_ { 

    public static volatile SingularAttribute<Bid, Item> item;
    public static volatile SingularAttribute<Bid, Float> amount;
    public static volatile SingularAttribute<Bid, Bidder> bidder;
    public static volatile SingularAttribute<Bid, Timestamp> time;
    public static volatile SingularAttribute<Bid, Integer> bidId;

}