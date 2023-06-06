package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is DivisionDao class.
 * This class is for getting data of divisions from the database.
 */
public class DivisionDao {
    /**
     * Gets all divisions from the database.
     * @return the list of all divisions in database
     * @throws SQLException if the database not found
     */
    public static ObservableList<Division> getAllDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            allDivisions.add(new Division(divisionId, divisionName, countryId, countryName));
        }
        return allDivisions;
    }

    /**
     * Gets divisions of a specific country from the database.
     * @param countryId the id of a specific country
     * @return the list of divisions of a specific country
     * @throws SQLException if the database not found
     */
    public static ObservableList<Division> getDivisionsByCountry(int countryId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID = countries.Country_ID AND " +
                "countries.Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();
        ObservableList<Division> divisionsByCountry = FXCollections.observableArrayList();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            divisionsByCountry.add(new Division(divisionId, divisionName, countryId, countryName));
        }
        return divisionsByCountry;
    }
}
