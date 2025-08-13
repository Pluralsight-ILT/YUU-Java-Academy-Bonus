package com.pluralsight.BookRecommendationAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private DataSource dataSource;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, title, author, genre, rating, publication_year, description, is_available FROM books"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = mapResultSetToBook(resultSet);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book getBookById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, title, author, genre, rating, year_published, description, is_available FROM books WHERE id = ?")) {

            statement.setInt(0, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBook(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, title, author, genre, rating, year_published, description, is_available FROM books WHERE genre = ?")) {

            statement.setString(1, genre);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = mapResultSetToBook(resultSet);
                    books.add(book);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book addBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO books (title, author, genre, rating, year_published, description, is_available) VALUES (?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setDouble(4, book.getRating());
            statement.setInt(5, book.getYearPublished());
            statement.setString(6, book.getDescription());
            statement.setBoolean(7, book.isAvailable());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating book failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public Book updateBookRating(int id, double rating) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE books SET rating = ? WHERE id = ?")) {

            statement.setDouble(1, rating);
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                return getBookById(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("genre"),
                resultSet.getDouble("rating"),
                resultSet.getInt("year_published"),
                resultSet.getString("description"),
                resultSet.getBoolean("is_available")
        );
    }
}