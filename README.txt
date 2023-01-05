Appointment Tracker version 1.0

author: William Mundall
        808-232-5099
        wmundal@wgu.edu
        12/29/2022

IDE used:              IntelliJ IDEA 2022.1 (Community Edition)
Java version:          Java SE 18.0.1
JavaFX version:        JavaFX-SDK-18.0.1
Java mysql connector:  mysql-connector-java-8.0.25

 
    This application is designed to make keeping track of customers and appointments easy. After verifying user credentials, 
the user may choose to work with customers or appointments which occupy different parts of the application, although have a 
similar design. The user can select existing appointments or customers in one of 2 ways. Entering an id into a search field 
or selecting from a table populated with customers or apppointments will bring the user to a form where attributes of the 
customer or appointment can be modified via text input or entry selection from a drop down list of menu items.
    New customers and appointments may also be added from the same forms. Appointments are added and viewed in times that are 
local to the user but those times must adhere to the operating hours of the NY branch. This application automatically enforces
these constraints. 
    In the custom report section of the application, the user will find unique ways to aggregate customers or appointments.
This area of the application will be exapanded to support more unique user queries in the future.  
    All customer and appointment data exist in a database and the application simply faciliates interaction between the user 
and the database.



Additional report for section A.3.F:

    My additional report allows the user to see how many customers occur in any given first level division.  After a country
is selected from a Combo Box, the first level divisions associated with that country appear in the division Combo Box. After
selecting the division, the sum total of customers that exist in that division is shown in the result textbox.
