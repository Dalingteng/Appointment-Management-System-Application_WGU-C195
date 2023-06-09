TITLE: Appointment Management System Application (WGU C195 - Software II - Advanced Java Concepts)

PURPOSE: This application provides users a GUI based scheduling application and allows access to existing database
for users to manage customers and schedule appointments

AUTHOR: Sochandaling Teng

CONTACT INFO: Student ID: 010309126, Email: steng5@wgu.edu

APPLICATION VERSION: 1.0

DATE: 06/08/2023

IDE VERSION: Intellij IDEA Community Edition 2021.1.3 x64
JDK VERSION: JDK Version 17.0.1
JAVAFX VERSION: JavaFX Version 17.0.1
MYSQL CONNECTOR DRIVER VERSION NUMBER: mysql-connector-java-8.0.25

DIRECTIONS:
As the program is launched, the user is required to enter username and password in the Log In Screen.
If the username and password are correct, the user is logged in successfully and is alerted any upcoming appointments
within 15 minutes from logging in before navigating to Main Appointment Screen.

From the Main Appointment Screen, the user can view appointments by week or month or view all appointments. Also,
the user can manage the appointments by adding, modifying and deleting appointments. If the user adds appointment,
it switches from Main Appointment Screen to Add Appointment Screen for users to fill out information of the
appointment to add to the database. Similarly, if the user modifies appointment, it switches from Main Appointment
Screen to Modify Appointment Screen for users to modify existing information of the appointment. Furthermore, the
user can click on Customer Button to navigate to Main Customer Screen or click on Report Button to navigate to
Report Screen or click on Log Out Button to exit the program.

From the Main Customer Screen, the user can manage customers by adding, modifying and deleting customers. If the
user adds customer, it switches from Main Customer Screen to Add Customer Screen for users to fill out information
of the customer to add to the database. Similarly, if the user modifies customer, it switches from Main Customer
Screen to Modify Customer Screen for users to modify existing information of the customer. Also, the user can
click on Back Button in Main Customer Screen to return back to the Main Appointment Screen or click on Log Out
Button to exit the program.

From the Report Screen, it generates 3 different reports. The first report is report by contact id. This report
is populated with all appointments and prompts the user to select a contact id to re-populate the table with
appointments of that specific contact id. The second report is report by month and type. This report displays the
number of total appointments grouped by month and type of the appointment. The last report is report by country.
This report displays the number of total appointments grouped by each country. Also, the user can click on Back
Button in Report Screen to return back to the Main Appointment Screen or click on Log Out Button to exit the program.

DESCRIPTION OF ADDITIONAL REPORT: The additional custom report is the report by country. This report displays the
number of total appointments of each country by accessing the database by calling the get reports by country method
in ReportDao class. It accesses the database by creating a query to combine tables using inner join of primary key
and foreign key of each table and display country column from countries table and count column, grouped by country.
