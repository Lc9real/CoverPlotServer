import java.io.Serializable;
import java.sql.*;

public class User implements Serializable
{
    public int id;
    public String username;
    public String email;
    public int vote;
    public String description;
    public Date createdAt;

    public User() 
    {

    }

    private User(int id, String username, String email, String description) 
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.description = description;
    }

    public User(String username, String email, String description) 
    {
        this.username = username;
        this.email = email;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User " +
                id +
                ":\nUsername: " + username +
                "\nEmail: " + email +
                "\nVote: " + vote +
                "\nDescription: " + description;
    }

    public static boolean checkPassword(String email, int password, Connection connection) throws SQLException
    {
        String sql = "SELECT password FROM users WHERE LOWER(email) = LOWER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) 
            {
                if (rs.getInt("password") == password)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static void addUser(User user, int password, Connection connection) throws SQLException
    {
        String sql = "INSERT INTO users (username, email, password, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, user.username.toLowerCase());
            pstmt.setString(2, user.email.toLowerCase());
            pstmt.setInt(3, password);
            pstmt.setString(4, user.description);
            pstmt.executeUpdate();
        }
    }

    public static Integer getUserIDFromUsername(String username, Connection connection) throws SQLException
    {
        String sql = "SELECT id FROM users WHERE LOWER(username) = LOWER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return rs.getInt("id");
                } 
                else 
                {
                    return null;
                }
            }
        }
    }

    public static Integer getUserIDFromEmail(String email, Connection connection) throws SQLException
    {
        String sql = "SELECT id FROM users WHERE LOWER(email) = LOWER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return rs.getInt("id");
                } 
                else 
                {
                    return null;
                }
            }
        }
    }

    public static User getUserInfo(int id, Connection connection) throws SQLException
    {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.id = id;
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.vote = rs.getInt("vote");
                user.description = rs.getString("description");
                user.createdAt = rs.getDate("created_at");

            }
        }
        
        return user;
    }

    



}
