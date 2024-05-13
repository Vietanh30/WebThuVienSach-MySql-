package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.BO.BookBO;
import Model.Bean.Book;
import Model.Bean.Reader;

public class ReaderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ReaderDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectDatabase.getMySQLConnection();
    }

    public int insertReader(String name, String identify, String book_id, Timestamp end_day) throws SQLException {
        int result = 0;
        String insert = "INSERT INTO Reader (name, book_id, identity_card, end_day) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            preSt.setString(1, name);
            preSt.setString(2, book_id);
            preSt.setString(3, identify);
            preSt.setTimestamp(4, end_day);
            result = preSt.executeUpdate();
        }
        System.out.println("Ketqua" + result);
        return result;
    }

    public ArrayList<Reader> getListReader(String status) throws SQLException {
        ArrayList<Reader> list = new ArrayList<>();
        String sql = "SELECT * FROM Reader WHERE status=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, Integer.parseInt(status));
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String book_id = rs.getString("book_id");
                    String identity_card = rs.getString("identity_card");
                    Date start = rs.getDate("start_day");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String start_day = dateFormat.format(start);
                    Date end = rs.getDate("end_day");
                    String end_day = dateFormat.format(end);
                    Book book = null;
                    try {
                        BookBO bookBO = new BookBO();
                        book = bookBO.findBook(book_id);
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                    Reader reader = new Reader();
                    reader.setId(id);
                    reader.setBook_id(book_id);
                    reader.setBook(book);
                    reader.setName(name);
                    reader.setIdentify(identity_card);
                    reader.setStart_day(start_day);
                    reader.setEnd_day(end_day);
                    reader.setStatus(status);
                    list.add(reader);
                }
            }
        }
        return list;
    }

    public int updateStatus(String id) throws SQLException {
        int result = 0;
        String sql = "UPDATE Reader SET status=1 WHERE id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteAllReader() throws SQLException {
        int result = 0;
        String sql = "DELETE FROM Reader";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteReader(String id) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM Reader WHERE id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteReaderBook(String book_id) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM Reader WHERE book_id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, book_id);
            result = pstm.executeUpdate();
        }
        return result;
    }

    public int deleteReaderBookCategory(String category_id) throws SQLException {
        int result = 0;
        String sql = "DELETE Reader FROM Reader LEFT JOIN Book ON Reader.book_id = Book.id WHERE Book.category_id=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, category_id);
            result = pstm.executeUpdate();
        }
        return result;
    }

    public ArrayList<Reader> getListSearch(String data_search, String status) throws SQLException {
        data_search = "%" + data_search + "%";
        System.out.println(data_search + status + "");
        ArrayList<Reader> list = new ArrayList<>();
        String sql = "SELECT * FROM Reader WHERE name LIKE ? AND status=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, data_search);
            pstm.setInt(2, Integer.parseInt(status));
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String book_id = rs.getString("book_id");
                    String identity_card = rs.getString("identity_card");
                    Date start = rs.getDate("start_day");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String start_day = dateFormat.format(start);
                    Date end = rs.getDate("end_day");
                    String end_day = dateFormat.format(end);
                    Book book = null;
                    try {
                        BookBO bookBO = new BookBO();
                        book = bookBO.findBook(book_id);
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                    Reader reader = new Reader();
                    reader.setId(id);
                    reader.setBook_id(book_id);
                    reader.setBook(book);
                    reader.setName(name);
                    reader.setIdentify(identity_card);
                    reader.setStart_day(start_day);
                    reader.setEnd_day(end_day);
                    reader.setStatus(status);
                    list.add(reader);
                }
            }
        }
        return list;
    }
}
