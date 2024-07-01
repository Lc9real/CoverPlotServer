
import java.io.Serializable;
import java.sql.*;



public class Series implements Serializable
{
    public int id;
    public String name;
    public String description;
    public String icon;
    public Date createdAt;

    public Series() 
    {

    }

    // Constructor with all fields
    public Series(int id, String name, String description, String icon, Date createdAt) 
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.createdAt = createdAt;
    }

    // Constructor without 'id' and 'createdAt' fields
    public Series(String name, String description, String icon) 
    {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    // toString method for a well-formatted overview
    @Override
    public String toString() 
    {
        return "Series " + id +
                ":\nName: " + name +
                "\nDescription: " + description +
                "\nIcon: " + icon +
                "\nCreated At: " + createdAt;
    }


    public static void addSeries(Series series, Connection connection) throws SQLException
    {
        if(getSeriesID(series.name.toLowerCase(), connection) == null)
        {
            String sql = "INSERT INTO series (name, description, icon) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, series.name);
                pstmt.setString(2, series.description);
                pstmt.setString(3, series.icon);
                pstmt.executeUpdate();
            }
        }
    }


    public static Integer getSeriesID(String series, Connection connection) throws SQLException
    {
        String sql = "SELECT id FROM series WHERE LOWER(name) = LOWER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, series);
            ResultSet rs = pstmt.executeQuery();
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


    public static Series getSeriesInfo(int id, Connection connection) throws SQLException
    {
        Series series = new Series();
        String sql = "SELECT * FROM series WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) 
            {
                series.id = id;
                series.name = rs.getString("name");
                series.description = rs.getString("description");
                series.icon = rs.getString("icon");
                series.createdAt = rs.getDate("created_at");
            }
        }
        
        return series;
    }
}   
