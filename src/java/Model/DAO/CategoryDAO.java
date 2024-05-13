package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BO.BookBO;
import Model.Bean.Category;

public class CategoryDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public CategoryDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectDatabase.getMySQLConnection();
    }

    public Category findCategory(String id) throws SQLException {
        String sql = "SELECT * FROM Category WHERE id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int _id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    return new Category(_id, name);
                }
            }
        }
        return null;
    }

    public int insertCategory(Category category) throws SQLException {
        int result = 0;
        String insert = "INSERT INTO Category (name) VALUES (?)";
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            preSt.setString(1, category.getName());
            result = preSt.executeUpdate();
        }
        return result;
    }

    public ArrayList<Category> getAllCategory() throws SQLException {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try (PreparedStatement pstm = connection.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                list.add(category);
            }
        }
        return list;
    }

    public int updateCategory(Category category) throws SQLException {
        int result = 0;
        String sql = "UPDATE Category SET Name = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, category.getName());
            pstm.setInt(2, category.getId());
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteCategory(String id) throws SQLException, ClassNotFoundException {
        int result = 0;
        BookBO bookBO = new BookBO();
        bookBO.deleteBookCategory(id);
        String sql = "DELETE FROM Category WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            result = pstm.executeUpdate();
        }
        return result;
    }
}
