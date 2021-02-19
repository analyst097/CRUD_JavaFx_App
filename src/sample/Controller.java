package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField tfId;
    public TextField tfTitle;
    public TextField tfAuthor;
    public TextField tfYear;
    public TextField tfPages;
    public TableView<Books> tBooks;
    public TableColumn<Books, Integer> colId;
    public TableColumn<Books, String> colTitle;
    public TableColumn<Books, String> colAuthor;
    public TableColumn<Books, Integer> colYear;
    public TableColumn<Books, Integer> colPages;
    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    Connection connection=null;


    public void handleButtonAction(ActionEvent actionEvent) {
        System.out.println("Button clicked");
        if(actionEvent.getSource() == btnInsert){
            insertReord();
        }
        else if (actionEvent.getSource() == btnUpdate){
            updateRecord();
        }
        else if (actionEvent.getSource() == btnDelete){
            deleteRecord();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }

    public Connection getConnection(){

        String user = "root";
        String password = "password";

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
//            System.out.println(connection);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public ObservableList<Books> getBooksList() {
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String sql = "SELECT * FROM books";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Books books;

            while (resultSet.next()){
                books = new Books(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
//                System.out.println(books);
                bookList.add(books);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    public void showBooks() {
        ObservableList<Books> list = getBooksList();

        colId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));

        tBooks.setItems(list);
        System.out.println(list);
    }

    private void insertReord(){
        Connection connection = getConnection();
        PreparedStatement stmt;
        try{
            stmt = connection.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?)");
            stmt.setInt(1, Integer.parseInt(tfId.getText()));
            stmt.setString(2, tfTitle.getText());
            stmt.setString(3, tfAuthor.getText());
            stmt.setInt(4, Integer.parseInt(tfYear.getText()));
            stmt.setInt(5, Integer.parseInt(tfPages.getText()));
            int i = stmt.executeUpdate();
            System.out.println(i);
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        showBooks();
    }

    private void deleteRecord() {
        Connection connection = getConnection();
        PreparedStatement stmt;

       try {
           stmt=connection.prepareStatement("delete from books where id=?");
           stmt.setInt(1,Integer.parseInt(tfId.getText()));

           int i=stmt.executeUpdate();
           System.out.println(i+" records deleted");
       }catch (Exception e){
           e.printStackTrace();
       }
       showBooks();
    }

    private void updateRecord() {
        Connection connection = getConnection();
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement("UPDATE books SET title=?, author=?, year=?, pages=? WHERE id=?");
            stmt.setInt(5, Integer.parseInt(tfId.getText()));
            stmt.setString(1, tfTitle.getText());
            stmt.setString(2, tfAuthor.getText());
            stmt.setInt(3, Integer.parseInt(tfYear.getText()));
            stmt.setInt(4, Integer.parseInt(tfPages.getText()));
            int i = stmt.executeUpdate();

            System.out.println(i);
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        showBooks();
    }




}
