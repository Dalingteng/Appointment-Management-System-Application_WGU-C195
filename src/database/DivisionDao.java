package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDao {
    public static ObservableList<Division> getAllDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID" + "= countries.Country_ID";
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
}
