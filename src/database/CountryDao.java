package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDao {
    public static ObservableList<Country> getAllCountries() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            allCountries.add(new Country(countryId, countryName));
        }
        return allCountries;
    }
}
