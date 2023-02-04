/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pawfinder;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Advincula
 */
public class database {
    
     public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/pawrfinder", "root", ""); // root is the default username : ) 
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
