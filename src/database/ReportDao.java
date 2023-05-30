package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDao {
    public static ObservableList<Report> getReportsByMonthType() throws SQLException {
        String sql = "SELECT monthname(Start) as Month, Type, count(*) as Count FROM appointments GROUP BY monthname(Start), Type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Report> reportsByMonthType = FXCollections.observableArrayList();
        while (rs.next()) {
            String month = rs.getString("Month");
            String type = rs.getString("Type");
            int monthTypeCount = rs.getInt("Count");
            reportsByMonthType.add(new Report(month, type, monthTypeCount));
        }
        return reportsByMonthType;
    }

    public static ObservableList<Report> getReportsByCountry() throws SQLException {
        String sql = "SELECT countries.Country, count(*) as Count FROM client_schedule.appointments " +
                "INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID " +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID " +
                "GROUP BY countries.Country";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Report> reportsByCountry = FXCollections.observableArrayList();
        while (rs.next()) {
            String country = rs.getString("Country");
            int countryCount = rs.getInt("Count");
            reportsByCountry.add(new Report(country, countryCount));
        }
        return reportsByCountry;
    }
}
