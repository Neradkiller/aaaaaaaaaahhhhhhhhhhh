/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.*;
import java.net.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente_FTP;
import vista.App;
import vista.Login;
import vista.Registro;
import vista.selector;

public class Controlador implements ActionListener {
    private Login vista;
    private Registro registro;
    private App app;
    private selector selector;
    private String usuario;
    private String password;
    private Cliente_FTP cliente;
    
    private String log;
	private String clave;
	static String nomb;
	static String direccion;
	static int puerto;
	static String comando;
 	Socket servidor;
	int numCliente=0;
	static String mensaje;
	static directorio_cliente listar = new directorio_cliente();
	static int archivo_len;
	static String nombre_archivo_subir;
	static String nombre_archivo_bajar;
        String operacion;
        Socket conexion;
        InputStream flujoentrada;
        BufferedReader entrada;
        OutputStream flujosalida;
        PrintStream salida;
    
	public Controlador(Login vista,Registro registro,App app,selector selector) {
		this.vista = vista;
                this.registro = registro;
                this.app = app;
                this.selector = selector;
                app.setVisible(true);
                app.setEnabled(false);
                vista.iniciarSesionbtn1.addActionListener(this);
                vista.nuevouser.addActionListener(this);
                registro.registro.addActionListener(this);
                app.upload.addActionListener(this);
		hacerVisible();
	}

	private void hacerVisible() {
		vista.setVisible(true);
	}
	public void actionPerformed(ActionEvent evento) {
            
            if (vista.iniciarSesionbtn1 == evento.getSource()){
                direccion = "localhost";
                int puerto = 5972;
                log = vista.correo.getText();
                clave = vista.password.getText();
                operacion = "1";
		try {
			conexion = new Socket(direccion,puerto);
			flujoentrada = conexion.getInputStream();
			entrada = new BufferedReader(new InputStreamReader(flujoentrada));
			flujosalida = conexion.getOutputStream();
			salida = new PrintStream (flujosalida);
                        
			mensaje = entrada.readLine();
                        mensaje = operacion + " " + log + " " + clave;
			salida.println(mensaje);
			salida.flush();
			System.out.println(mensaje);
                        mensaje = entrada.readLine();
			System.out.println(mensaje);
                        
			if(!"Usuario no existente".equals(mensaje)){  // si el usuario si existe ejecutar codigo para transferir archivos
				boolean finalizar = false;
                                vista.setVisible(false);
                                app.setEnabled(true);
					
					/*
					//case "ds":						
						System.out.println("\nARCHIVOS DEL SERVIDOR:");
						salida.println(comando);
						salida.flush();
						mensaje = entrada.readLine();
						listar.listar_directorio_servidor(mensaje);
				
					
                                        //case "dc":
						System.out.println("\nARCHIVOS LOCALES:");
						listar.listar_directorio_cliente();
					
					
                                        */
                                        //case "sa":
                                        
                                        if (app.upload == evento.getSource()){
                                            selector.setVisible(true);
                                            salida.println("sa");
                                            salida.flush();
                                            nombre_archivo_subir = System.console().readLine("Seleccione archivo a subir: ");
                                            salida.println(nombre_archivo_subir);
                                            salida.flush();
                                        }
				        //new Cliente_FTP().send(conexion, nombre_archivo_subir, salida);
					
					
                                        
                                        //case "ba":
					/*System.out.println("\nlisto para recibir archivo");
				        salida.println(comando);
				        salida.flush();
					nombre_archivo_bajar = System.console().readLine("Seleccione archivo a descargar: ");
				        salida.println(nombre_archivo_bajar);
				        salida.flush();
				        mensaje = entrada.readLine();
				        archivo_len = Integer.parseInt(mensaje);
				        System.out.println("tamaño archivo: " + mensaje);
				        //new Cliente_FTP().receiveFile(conexion, archivo_len, nombre_archivo_bajar);
					
                                        //case "h":
					System.out.println("\nAyuda para comandos disponibles:\nds: listar directorio de archivos del servidor\ndc: listar directorio de archivos locales\n"
								+ "sa: subir archivo al servidor\nba: bajar archivo desde el servidor\nfin: salir del programa");

					*/

			} // fin if
		} // fin try
		catch (Exception e) {
		    e.printStackTrace();
		}
                    
            }
            
            if (vista.nuevouser == evento.getSource()){
                vista.setVisible(false);
                registro.setVisible(true);
            }
            if (app.upload == evento.getSource()){
                                            selector.setVisible(true);
                                            salida.println("sa");
                                            salida.flush();
                                            nombre_archivo_subir = selector.archivo.getSelectedFile().getAbsolutePath();
                                            salida.println(nombre_archivo_subir);
                                            salida.flush();
                
                try {
                    this.send(conexion, nombre_archivo_subir, salida);
                } catch (Exception ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                                        
                                        }
            if(registro.registro == evento.getSource()){
                
                if (registro.password1.getText().equals(registro.password2.getText())){
                direccion = "localhost";
                int puerto = 5972;
                String nombre = registro.nombre.getText();
                String apellido = registro.apellido1.getText();
                String password = registro.password1.getText();
                String correo = registro.correo.getText();
                operacion = "2";
		try {
			Socket conexion = new Socket(direccion,puerto);
			InputStream flujoentrada = conexion.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(flujoentrada));
			OutputStream flujosalida = conexion.getOutputStream();
			PrintStream salida = new PrintStream (flujosalida);
			mensaje = entrada.readLine();
                        System.out.println(mensaje);
                        mensaje = operacion + " " + nombre + " " + apellido + " " + correo + " " + password;
			salida.println(mensaje);
			salida.flush();
			System.out.println(mensaje);
                        mensaje = entrada.readLine();
			System.out.println(mensaje);
			conexion.close();
		} // fin try
		catch (Exception e) {
		    e.printStackTrace();
		}
                }
            registro.setVisible(false);
            vista.setVisible(true);
            }
            
            

		
	}
        public void send(Socket socket_cliente, String archivo, PrintStream salida) throws Exception {
	    int archivo_len = 0;
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
		os = socket_cliente.getOutputStream();		
	    File myFile = new File("./FTP/Cliente/" + archivo); // ENVIAR ARCHIVO
	    byte[] mybytearray = new byte[(int) myFile.length()];
	    fis = new FileInputStream(myFile);
	    bis = new BufferedInputStream(fis);
	    bis.read(mybytearray, 0, mybytearray.length);
	    os = socket_cliente.getOutputStream();
	    System.out.println("Sending...");
	    System.out.println("Sending " + "./FTP/Servidor/ArchivoServidor.txt" + "(" + mybytearray.length + " bytes)");
	    archivo_len = mybytearray.length;
	    salida.println(archivo_len);
	    salida.flush();
	    os.write(mybytearray, 0, mybytearray.length);
	    os.flush();
	    System.out.println("Done.");
	}

public void receiveFile(Socket socket_cliente, int filesize, String nombre_archivo) throws Exception {
	    //int filesize = archivo_len;
	    int bytesRead;
	    int current = 0;

	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    byte[] mybytearray = new byte[filesize];
	    InputStream is = socket_cliente.getInputStream();
	    fos = new FileOutputStream("./FTP/Cliente/" + nombre_archivo);
	    bos = new BufferedOutputStream(fos);
	    bytesRead = is.read(mybytearray, 0, mybytearray.length);
	    current = bytesRead;
	    do {
	        bytesRead =
	           is.read(mybytearray, current, (mybytearray.length-current));
	        if(bytesRead >= 0) current += bytesRead;
	    } while(current < filesize);
	    bos.write(mybytearray, 0 , current);
	    bos.flush();
	    System.out.println("File " + "prueba.txt" + " downloaded (" + current + " bytes read)");

}

}

class directorio_cliente {
	String s = null;
	String[] arr = null;
	
	public void listar_directorio_cliente () {
		File folder = new File("./FTP/Cliente");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }
		}
	}
	
	public void listar_directorio_servidor(String s){
		 String[] arr = s.split("&");    

		 for ( String ss : arr) {
		       System.out.println(ss);
		  }		
	}
}

