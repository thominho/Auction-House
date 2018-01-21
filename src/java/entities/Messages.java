package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name="MESSAGES")
public class Messages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="SENDER_ID",nullable = true)
    private Users sender;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="RECEIVER_ID",nullable=true)
    private Users receiver;
            
    @Column(name="TOPIC",nullable = false)
    private String topic;
        
    @Column(name="CONTENT",nullable = false)
    private String content;
    
    @Column(name="UNREAD",nullable=false)
    private boolean unread;
    
    @Column(name="SENT_DATE",nullable=false)
    private Timestamp date;
    
    @Column(name="SENDER_NAME",nullable=false)
    private String senderName;
    
    @Column(name="RECEIVER_NAME",nullable=false)
    private String receiverName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
