package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is ContactDao class.
 * This class is for getting data of contacts from the database.
 */
public class ContactDao {
    /**
     * This is the get all contacts method. This gets all contacts from the database.
     * @return the list of all contacts in database
     * @throws SQLException if the database not found
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            allContacts.add(new Contact(contactId, contactName, email));
        }
        return allContacts;
    }
}
