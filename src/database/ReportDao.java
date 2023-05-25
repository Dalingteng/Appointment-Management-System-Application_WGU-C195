package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDao {
    public ObservableList<Report> getReportsByMonthType() throws SQLException {
        String sql = "SELECT * FROM appointments";
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
}
