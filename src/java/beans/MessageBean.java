package beans;

import daos.MessageDAO;
import daos.UserDAO;
import entities.Messages;
import entities.Users;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean {
    private List<Messages> messageList;
    private Messages message;
    private String recieverUsername;
    private String topic;
    private String content;
    
    private String errorMsg;
    private String successMsg;
    
    public void deleteSentMessage(Users sender)
    {
        message.setSender(null);
        sender.removeMessageSent(message);
        if(message.getReceiver()==null)
            MessageDAO.delete(message);
        else{
            message.setSender(null);
            MessageDAO.update(message);
        }
        message=null;
    }
    
    public void deleteReceivedMessage(Users receiver)
    {
        message.setReceiver(null);
        receiver.removeMessageReceived(message);
        if(message.getSender()==null)
            MessageDAO.delete(message);
        else{
            message.setReceiver(null);
            MessageDAO.update(message);
        }
        message=null;
    }

    public String back(){
        message=null;
        return "/protected/inbox";
    }
    
    public List<Messages> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Messages> messageList) {
        this.messageList = messageList;
    }
    
    public void sendMessage(Users sender){
        Messages newMessage=new Messages();
        
        Users reciever=UserDAO.findUser(recieverUsername);
        
        if(reciever==null)
        {
            successMsg=null;
            errorMsg="Reciever not found";
            return;
        }
        
        newMessage.setContent(content);
        Date date=new Date();
        newMessage.setDate(new Timestamp(date.getTime()));
        newMessage.setReceiver(reciever);
        newMessage.setSender(sender);
        newMessage.setTopic(topic);
        newMessage.setUnread(true);
        newMessage.setReceiverName(reciever.getBidder().getUsername());
        newMessage.setSenderName(sender.getBidder().getUsername());
        
        List<Messages> tempList=reciever.getMessagesReceived();
        if(tempList==null) tempList=new LinkedList<Messages>();
        tempList.add(newMessage);
        reciever.setMessagesReceived(tempList);
        
        tempList=sender.getMessagesSent();
        if(tempList==null) tempList=new LinkedList<Messages>();
        tempList.add(newMessage);
        sender.setMessagesSent(tempList);
        
        if(MessageDAO.insert(newMessage)==false)
        {
            successMsg=null;
            errorMsg="Failed to insert new message to database";
            return;
        }
        
        if(UserDAO.update(sender)==false)
        {
            successMsg=null;
            errorMsg="Failed to update sender message list";
            return;
        }
        
        if(UserDAO.update(reciever)==false)
        {
            successMsg=null;
            errorMsg="Failed to update receiver message list";
            return;
        }
        
        successMsg="The message was sent successfully";
    }
    
    public String home()
    {
        errorMsg=null;
        successMsg=null;
        return "/protected/homepage";
    }

    public void recieveMessage(Messages msg){
        msg.setUnread(false);
        message=msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getRecieverUsername() {
        return recieverUsername;
    }

    public void setRecieverUsername(String recieverUsername) {
        this.recieverUsername = recieverUsername;
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
    
    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }
}
