package FTP_server;

import static ftp_server.bdusuario.connect;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

public class Autenticar_Usuario {
	String exito;
        private final static String url = "jdbc:postgresql://localhost:5432/redes";
        private final static String user = "postgres";
        private final static String password = "134679";
        private static String ubicacion="C:\\Users\\Daren\\Desktop\\servidor\\";
        
        public static int login(String correo, String contrasena) {
        String SQL = "SELECT id "
                + "FROM miembro "
                + "WHERE correo = ? and contraseña= MD5(?)";
 
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
        
	public Autenticar_Usuario(String log, String clave){
		exito = "no";
	}
        
        
        
    public static String buscararchivo(String nombre) {
        String SQL = "select a.nombre from carpeta c \n" +
           "join archivo a on c.id=a.id_carpeta\n" +
        "join miembro m on c.id_miembro =m.id\n" +
            "where m.correo=?;";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
 
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            String id="";
                   while (rs.next()) {
                       id = id+" "+rs.getString(1);
                       
                       
                    }

                   return id;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
    
public static String buscarch(){
    
    
    
    
    return "";
}        
        
public static int insertarc(String nombre, int ids, String ubicacion) {
       
        String SQL = "INSERT INTO carpeta(nombre, ubicacion, id_miembro, id_carpeta) " + "VALUES ( ?, ?, ?, null)";

        int id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1,nombre);
            pstmt.setString(2,ubicacion);
            pstmt.setInt(3,ids);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    
                    return id;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return id;}         

public static int insertaru(String nombre, String apellido, String  correo, String contraseña, String ubicacion) {

        String SQL = "INSERT INTO miembro(nombre,apellido, correo, contraseña) " + "VALUES(?,?,?,MD5(?))";

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
                    
                    id=insertarc(correo,id,ubicacion);
                    
                    return id;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;}

    
    public static String buscararchivos(String nombre) {
        String SQL = "select a.nombre from carpeta c \n" +
            "join archivo a on c.id=a.id_carpeta\n" +
            "join miembro m on c.id_miembro =m.id\n" +
            "where m.nombre=?;";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
 
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            int id=0;
            String cadena="";
                   if (rs.next()) {
                       cadena = cadena+rs.getString(1);
                       return cadena;
                    }
                   else {
                       return "nada";
                   }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "nada";
    }   
    
    
    public static int buscarcarp(String nombre) {
        String SQL = "select c.id from carpeta c \n" +
            "join miembro m on c.id_miembro=m.id\n" +
            "where m.correo=?;";
 
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
    
        public static int creararch(String nombre,String arch){
         String nom=nombre;
         int id= buscarcarp(nom);
         insertara( arch, 1, id);
         
         
     
         return 0;
     }
    
       
        
// METODO PARA AUTENTICAR USUARIO EXISTENTE
	public String Autenticar(String log, String clave) {
            
		
                int i=this.login(log,clave);
                if(i>=1){
                    exito="si";
                }
                else{
                    exito="no";
                }
                System.out.println("aqui");
		return exito;
	}

// METODO REGISTRAR USUARIO INEXISTENTE
	public int Registrar(String nombre, String apellido,String correo,String clave) {
            
            
                    String direccion=ubicacion+""+correo;    
                    System.out.println(direccion);
                File directorio = new File(direccion);
                System.out.println(direccion);
                
       int id=this.insertaru(nombre,apellido,correo,clave,direccion);
       if(id>=1){
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }    
       }
       else{
           
       }
        return id;
         
	}
        
        public String listararch(String nombre){
            
          String cadena=buscararchivos(nombre);  
            
          return "nada";  
        }
        
	
	        
                //Crea la carpeta en el servidor
public void crearNuevaCarpeta(int id_usuario, String nombreNuevaCarpeta, int id_carpetaPadre, String ubicacionCarpetaPadre) throws SQLException{
        
        String ubicacion = ubicacionCarpetaPadre +"\\"+nombreNuevaCarpeta;
        System.out.println(ubicacion);
        File carpeta = new File(ubicacion);
        carpeta.mkdir();
        
        insertarNuevaCarpeta(id_usuario, nombreNuevaCarpeta, ubicacion, id_carpetaPadre);
}

                        
                        //Muestra el contenido del primer nivel de la carpeta, mas no el de las sub carpetas
public ResultSet mostrarContenidoCarpeta(int id_carpeta)throws SQLException{
    try{
            ResultSet result = null;
            
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement stmt = conexion.createStatement();
            result = stmt.executeQuery("SELECT nombre, id FROM CARPETA WHERE id_carpeta = "+id_carpeta+" UNION SELECT nombre,id from archivo WHERE id_carpeta = "+id_carpeta+";");
            while (result.next()){
                System.out.println("ID = "+result.getInt("id"));
                System.out.print("Nombre = "+result.getString("nombre"));
            }
            stmt.close();
            conexion.close();
            return result;
            } catch (Exception e) {
            
               System.out.println(e.getMessage()); return null;}
}

public ResultSet seleccionarCarpeta(int id) throws SQLException{
        try{
            ResultSet result = null;
            
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement stmt = conexion.createStatement();
            result = stmt.executeQuery("SELECT * FROM CARPETA WHERE id = "+id+";");
            while (result.next()){
                System.out.println("ID = "+result.getInt("id"));
                System.out.print("Nombre = "+result.getString("nombre"));
            }
            stmt.close();
            conexion.close();
            return result;
            } catch (Exception e) {
            
               System.out.println(e.getMessage()); return null;}
}


                //Inserta una carpeta que no sea la principal
public void insertarNuevaCarpeta(int id_miembro, String nombre, String ubicacion, int id_carpetaPadre)throws SQLException{
        
        try{
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement stmt = conexion.createStatement();
        
            String sql = "INSERT INTO CARPETA (id_miembro, nombre, ruta, id_carpeta) VALUES ("+id_miembro+", '"+nombre+"', '"+ubicacion+"', "+id_carpetaPadre+")";
            stmt.executeUpdate(sql);
        
            stmt.close();
            conexion.close(); } catch (Exception e) {
            
               System.out.println(e.getMessage());
        }
}

public void insertarArchivo(String nombre, float peso, int id_carpetaPadre)throws SQLException{
        
        try{
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement stmt = conexion.createStatement();
        
            String sql = "INSERT INTO ARCHIVO (nombre, peso, id_carpeta) VALUES ('"+nombre+"', "+peso+", "+id_carpetaPadre+")";
            stmt.executeUpdate(sql);
        
            stmt.close();
            conexion.close(); } catch (Exception e) {
            
               System.out.println(e.getMessage());
        }
}
}


