package entities;

import entities.Users;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-17T17:59:16")
@StaticMetamodel(Messages.class)
public class Messages_ { 

    public static volatile SingularAttribute<Messages, Timestamp> date;
    public static volatile SingularAttribute<Messages, Users> receiver;
    public static volatile SingularAttribute<Messages, Users> sender;
    public static volatile SingularAttribute<Messages, Boolean> unread;
    public static volatile SingularAttribute<Messages, String> topic;
    public static volatile SingularAttribute<Messages, Integer> id;
    public static volatile SingularAttribute<Messages, String> content;

}