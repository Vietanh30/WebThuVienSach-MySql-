package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.BO.CategoryBO;
import Model.BO.ReaderBO;
import Model.Bean.Book;
import Model.Bean.Category;

public class BookDAO {
    private Connection connection;
    private CategoryBO categoryBO;
    private ReaderBO readerBO;

    public BookDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectDatabase.getMySQLConnection();
        categoryBO = new CategoryBO();
        readerBO = new ReaderBO();
    }

    public Book findBook(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Book WHERE id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String category_id = rs.getString("category_id");
                    Date date = rs.getDate("create_day");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = dateFormat.format(date);
                    Category category = categoryBO.findCategory(category_id);
                    String amount = rs.getString("amount");
                    String image = rs.getString("image");
                    Book book = new Book();
                    book.setId(id);
                    book.setName(name);
                    book.setCategory(category);
                    book.setAmount(amount);
                    book.setImage(image);
                    book.setDay(strDate);
                    return book;
                }
            }
        }
        return null;
    }

    public int insertBook(Book book) throws SQLException {
        int result = 0;
        String insert = "INSERT INTO Book (name, category_id, amount, image) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            preSt.setString(1, book.getName());
            preSt.setInt(2, book.getCategory().getId());
            preSt.setString(3, book.getAmount());
            preSt.setString(4, book.getImage());
            result = preSt.executeUpdate();
            System.out.println("Ketqua" + result);
        }
        return result;
    }

    public ArrayList<Book> getAllBook() throws SQLException, ClassNotFoundException {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book ORDER BY create_day DESC";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String category_id = rs.getString("category_id");
                Date date = rs.getDate("create_day");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(date);
                Category category = categoryBO.findCategory(category_id);
                String amount = rs.getString("amount");
                String image = rs.getString("image");
                Book book = new Book();
                book.setId(id);
                book.setName(name);
                book.setCategory(category);
                book.setAmount(amount);
                book.setImage(image);
                book.setDay(strDate);
                list.add(book);
            }
        }
        return list;
    }

    public ArrayList<Book> getSearchBook(String name_search) throws SQLException, ClassNotFoundException {
        System.out.println("Day" + name_search);
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE name LIKE ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, "%" + name_search + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String category_id = rs.getString("category_id");
                    Date date = rs.getDate("create_day");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = dateFormat.format(date);
                    Category category = categoryBO.findCategory(category_id);
                    String amount = rs.getString("amount");
                    String image = rs.getString("image");
                    Book book = new Book();
                    book.setId(id);
                    book.setName(name);
                    book.setCategory(category);
                    book.setAmount(amount);
                    book.setImage(image);
                    book.setDay(strDate);
                    list.add(book);
                }
            }
        }
        System.out.println(list);
        return list;
    }

    public int updateBook(Book book) throws SQLException {
        int result = 0;
        String sql = "UPDATE Book SET name = ?, category_id = ?, amount = ?, image = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, book.getName());
            pstm.setInt(2, book.getCategory().getId());
            pstm.setString(3, book.getAmount());
            pstm.setString(4, book.getImage());
            pstm.setString(5, book.getId());
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteAllBook() throws SQLException, ClassNotFoundException {
        int result = 0;
        readerBO.deleteAllReader();
        String sql = "DELETE FROM Book";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteBook(String id) throws SQLException, ClassNotFoundException {
        int result = 0;
        readerBO.deleteBookReader(id);
        String sql = "DELETE FROM Book WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteBookCategory(String category_id) throws SQLException, ClassNotFoundException {
        int result = 0;
        readerBO.deleteBookReaderCategory(category_id);
        String sql = "DELETE FROM Book WHERE category_id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, category_id);
            result = pstm.executeUpdate();
        }
        return result;
    }
}
