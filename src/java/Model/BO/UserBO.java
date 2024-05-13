package Model.BO;

import java.sql.SQLException;

import Model.Bean.User;
import Model.DAO.UserDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBO {
	UserDAO userDAO;

    public UserBO() {
            try {
                this.userDAO = new UserDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

	public User getAccount(String username, String password) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userDAO.getUser(username, password);
	}
}
