package beans;

import daos.UserDAO;
import embedded.Bidder;
import entities.Messages;
import entities.Users;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
    private String postalCode;
    private String city;
    private String address;
    private String taxNumber;
    private String errorMsg;
    
    private Users currentUser;
    
    private boolean accepted;

    private boolean logged;
    private boolean loggedAsAdmin;
    
    public UserBean(){
        logged=false;
        loggedAsAdmin=false;
    }
    
    public String logout(){
        logged=false;
        loggedAsAdmin=false;
        currentUser=null;
        return "/index";
    }
    
    public String login(){
        if(username==null || password==null)
        {
            errorMsg="Please fill both username and password fields.";
            return "/login";
        }
        currentUser=UserDAO.login(username, password);
        if(currentUser==null)
        {
            if(UserDAO.adminLogin(username, password)!=null)
            {
                loggedAsAdmin=true;
                errorMsg=null;
                return "/adminPages/userManagement";
            }
            errorMsg="Wrong username or password.";
            return "/login";
        }
        if(currentUser.isAccepted()==false)
        {
            errorMsg="user "+currentUser.getBidder().getUsername()+" is not yet accepted by the admin, please be patient";
            return "/login";
        }
        logged=true;
        return "/protected/homepage";
    }
    
    private boolean nullRegField()
    {
        if(this.username==null) return true;
        if(this.password==null) return true;
        if(this.confirmPassword==null) return true;
        if(this.firstName==null) return true;
        if(this.lastName==null) return true;
        if(this.email==null) return true;
        if(this.phone==null) return true;
        if(this.country==null) return true;
        if(this.postalCode==null) return true;
        if(this.city==null) return true;
        if(this.address==null) return true;
        
        return this.taxNumber==null;
    }
    
    public String register(){
        if(this.nullRegField())
        {
            this.errorMsg="Please fill all the fields";
            return "/register";
        }
        if(!password.equals(confirmPassword))
        {
            errorMsg="password and confirm password fields do not match";
            return "/register";
        }
        if(UserDAO.findUser(username)!=null)
        {
            errorMsg="this username is already in use";
            return "/register";
        }
        Users newUser=new Users();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        
        /*Bidder class contains location data for user
          Bidder class is embedded to user class entity*/
        Bidder bidder=new Bidder();
        bidder.setCountry(country);
        bidder.setLocation(city);
        bidder.setRating(0);
        bidder.setUsername(username);
        
        newUser.setBidder(bidder);
        newUser.setPostalCode(postalCode);
        newUser.setAddress(address);
        newUser.setTaxNumber(taxNumber);
        newUser.setItems(null);
        newUser.setMessagesReceived(null);
        newUser.setMessagesSent(null);
        newUser.setSellerRating(0);
        
        if(UserDAO.insert(newUser))
        {
            errorMsg=null;
            return "/registersuccess";
        }
        else
        {
            errorMsg="Failed to insert data in database.";
            return "/register";
        }
    }
    
    public String home()
    {
        errorMsg=null;
        return "/index";
    }
    
    public String enterAsGuest(){
        errorMsg=null;
        return "/search";
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLoggedAsAdmin() {
        return loggedAsAdmin;
    }

    public void setLoggedAsAdmin(boolean loggedAsAdmin) {
        this.loggedAsAdmin = loggedAsAdmin;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
    
    
}
