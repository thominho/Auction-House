package entities;

import embedded.Bidder;
import entities.Item;
import entities.Messages;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-17T17:59:16")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, Bidder> bidder;
    public static volatile SingularAttribute<Users, Integer> sellerRating;
    public static volatile ListAttribute<Users, Messages> messagesSent;
    public static volatile SingularAttribute<Users, String> postalCode;
    public static volatile SingularAttribute<Users, String> taxNumber;
    public static volatile SingularAttribute<Users, Boolean> accepted;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile ListAttribute<Users, Messages> messagesReceived;
    public static volatile ListAttribute<Users, Item> items;
    public static volatile SingularAttribute<Users, String> email;

}