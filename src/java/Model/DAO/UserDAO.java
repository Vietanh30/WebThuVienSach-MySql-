package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Model.Bean.User;

public class UserDAO {

    private Connection connection;

    public UserDAO() throws ClassNotFoundException, SQLException {
        connection = ConnectDatabase.getMySQLConnection();
    }

    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE username=? AND password=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, username);
            pstm.setString(2, password);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    User user = new User();
                    user.setId(id);
                    user.setUsername(username);
                    user.setPassword(password);
                    return user;
                }
            }
        }
        return null;
    }

    public User findUser(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, username);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String password = rs.getString("password");
                    User user = new User();
                    user.setId(id);
                    user.setUsername(username);
                    user.setPassword(password);
                    return user;
                }
            }
        }
        return null;
    }
}
