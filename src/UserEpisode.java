import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEpisode implements Serializable
{
    public int id;
    public int userID;
    public int seriesID;
    public int season;
    public int episode;


    public UserEpisode() {}

    public UserEpisode(int userID, int seriesID, int season, int episode) {
        this.userID = userID;
        this.seriesID = seriesID;
        this.season = season;
        this.episode = episode;
    }
    public UserEpisode(int id, int userID, int seriesID, int season, int episode) {
        this.id = id;
        this.userID = userID;
        this.seriesID = seriesID;
        this.season = season;
        this.episode = episode;
    }

    public static void addUserEpisode(UserEpisode userEpisode, Connection connection) throws SQLException 
    {
        String sql = "";
        if (getUserEpisode(userEpisode.userID, userEpisode.seriesID, connection) == null) sql = "INSERT INTO userEpisode (season, episode, userID, seriesID) VALUES (?, ?, ?, ?)";
        else sql = "UPDATE userEpisode SET season = ?, episode = ? WHERE userID = ? AND seriesID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userEpisode.season);
            pstmt.setInt(2, userEpisode.episode);
            pstmt.setInt(3, userEpisode.userID);
            pstmt.setInt(4, userEpisode.seriesID);
            pstmt.executeUpdate();
        }
    }

    public static UserEpisode getUserEpisode(int userID, int seriesID, Connection connection) throws SQLException 
    {
        String sql = "SELECT * FROM userEpisode WHERE userID = ? AND seriesID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, seriesID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new UserEpisode(
                        rs.getInt("userID"),
                        rs.getInt("seriesID"),
                        rs.getInt("season"),
                        rs.getInt("episode")
                );
            } else {
                return null;
            }
        }
    }


    public static List<Series> getJoinedSeries(User user, Connection connection) throws SQLException
    {
        List<Series> series = new ArrayList<>();
        String sql = "SELECT * FROM userEpisode WHERE userID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, user.id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) 
            {
                series.add(Series.getSeriesInfo(rs.getInt("seriesID"), connection));
            }

            return series;
        }
    }



}
