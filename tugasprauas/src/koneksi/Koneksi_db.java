package koneksi;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;




public class Koneksi_db {

    public static Object getKoneksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Statement stat;
    public ResultSet res;
    public Connection con;
    public PreparedStatement pst;
    
    public Koneksi_db(){
        if(con == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/catering","root","");
                stat = con.createStatement();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Koneksi = "+e);
            }
        }
        
    }

    public ResultSet executeQuery(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//      private static Connection conn;
//      public static Connection getKoneksi( ){
//               String host = "jdbc:mysql://localhost/catering",
//                      user = "root",
//                      pass = "";
//               try{
//                      conn = (Connection) DriverManager.getConnection(host, user, pass);
//               }catch (SQLException err){
//                       JOptionPane.showMessageDialog(null, err.getMessage( ) );
//               }
//               return conn;
//      }
//
//    public ResultSet executeQuery(String SQL) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
}
