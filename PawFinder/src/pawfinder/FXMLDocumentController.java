/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pawfinder;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Advincula
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
//    @FXML
//    private Button minimize;
    
//    @FXML
//    private Button maximize;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

//    @FXML
//    private Button close;
    
//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
    public void login(){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD THEN PROCEED TO DASHBOARD 
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    getData.username = username.getText();
                    
                    // TO HIDE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                    
                    // LINK YOUR DASHBOARD FORM
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    // IF NOT THEN ERROR MESSAGE WILL APPEAR
                    
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                    
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
//    public void minimize() {
//        Stage stage = (Stage) main_form.getScene().getWindow();
//        stage.setIconified(true);
//    }
    
    @FXML
    void close(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
    }


    @FXML
    void max(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    void min(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }
    
//     public void maximize() {
//        Stage stage = (Stage) main_form.getScene().getWindow();
//        stage.setIconified(true);
//    }
//     
//    public void close(){
//        System.exit(0);
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
