/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp_server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class bdusuario {
    private final static String url = "jdbc:postgresql://localhost:5432/redes";
    private final static String user = "postgres";
    private final static String password = "134679";

    public static Connection connect() throws SQLException {
        
        
        return DriverManager.getConnection(url, user, password);
    }

    public static int insertaru(String nombre, String apellido, String correo, String contraseña, String ubicacion) {

        String SQL = "INSERT INTO miembro(nombre,apellido, correo, contraseña) " + "VALUES(?,?,?,?)";

        int id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1,nombre);
            pstmt.setString(2,apellido);
            pstmt.setString(3,correo);
            pstmt.setString(4,contraseña);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {

                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    bdusuario b= new bdusuario();
                    b.insertarc(correo,id,ubicacion);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;}
    
    
    
public static int insertarc(String nombre, int ids, String ubicacion) {
        String ubi=new String(ubicacion+"/"+nombre);
        String SQL = "INSERT INTO carpeta(nombre, ubicacion, id_miembro, id_carpeta) " + "VALUES ( ?, ?, ?, null)";

        int id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1,nombre);
            pstmt.setString(2,ubi);
            pstmt.setInt(3,ids);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return id;}
        
        
public static int insertara(String nombre, double peso, int idcarpeta) {
        
        String SQL = "INSERT INTO archivo(nombre, peso, id_carpeta) " + "VALUES ( ?, ?, ?)";

        int id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1,nombre);
            pstmt.setDouble(2,peso);
            pstmt.setInt(3,idcarpeta);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return id;}   


    public static int buscarcarp(String nombre) {
        String SQL = "SELECT id "
                + "FROM carpeta "
                + "WHERE nombre = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
 
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            int id=0;
                   if (rs.next()) {
                       id = rs.getInt(1);
                       return id;
                    }
                   else {
                       return 0;
                   }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public static int buscarusuario(String nombre) {
        String SQL = "SELECT id "
                + "FROM miembro "
                + "WHERE nombre = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
 
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            int id=0;
                   if (rs.next()) {
                       id = rs.getInt(1);
                       return id;
                    }
                   else {
                       return 0;
                   }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
        public static int login(String correo, String contrasena) {
        String SQL = "SELECT id "
                + "FROM miembro "
                + "WHERE correo = ? and contraseña= ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
 
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
            int id=0;
                   if (rs.next()) {
                       id = rs.getInt(1);
                       return id;
                    }
                   else {
                       return 0;
                   }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    

        
    
}

