# CRUD JavaFx application
***
* JavaFx and Fxml code to make UI and logic of CRUD Javafx application.
* I have made this app by following these two Youtube tutorials
  1. Part 1 - https://www.youtube.com/watch?v=CGWRwpeihE8 
  2. Part 2 - https://www.youtube.com/watch?v=kpnnXit2br0
* I have updated the code to INSERT, UPDATE and DELETE operations by using PrepareStatement interface in java.sql.
* Follow this website for syntax and understanding - https://www.javatpoint.com/PreparedStatement-interface
* I have used 
  1. Intellij IDEA editor
  2.  Jdk 11.0.4
  3. mysql-connector-java-8.0.22
  4. javafx-sdk-11.0.2

![Screenshot](https://github.com/designeradi/CRUD_JavaFx_App/blob/master/Screenshot%202021-01-02%20211209.jpg)

## Copy the codes
---
Copy the code from  Main.java, Controller.java, Books.java and sample.fxml to the respective files in your Javafx appplication.
  
## Setting up the Mysql database
---
1. Run Mysql server on port localhost:3306 (it is default port in XAMPP).
2. Create database having the name - "library".
3. Create table "books" with schema - 

   ``` CREATE TABLE books (
        id INT(6) PRIMARY KEY,
        title VARCHAR(50) NOT NULL,
        author VARCHAR(50) NOT NULL,
        year INT(4) NOT NULL,
        pages INT(4) NOT NULL
       );

### Final step
---
One final thing you have to is, go to Edit configurations of Main.java and add VM options as -
  * --module-path "path to javafx lib" --add-modules javafx.controls,javafx.fxml
  * For example :- --module-path C:\Java\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml

Now try running your Main.java file and see if it runs. If not then try debugging or google the errors.
 
  
