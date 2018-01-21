package beans;

import daos.UserDAO;
import entities.Users;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserManagementBean {
    private List<Users> userList;
    private Users currentUser;
    private String successMsg;
    private String errorMsg;
    
    public String loadUsers(){
        userList=UserDAO.getAllUsers();
        if(userList==null) return "/errorPages/noUser";
        return "/adminPages/userInfo";
    }
    
    public void acceptUser(){
        currentUser.setAccepted(true);
        if(UserDAO.update(currentUser))
        {
            errorMsg=null;
            successMsg="The user was successfully accepted";
        }
        else
        {
            errorMsg="Could not update user accepted status on database";
            successMsg=null;
        }
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
}
