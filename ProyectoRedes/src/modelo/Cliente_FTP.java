/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Daren
 */
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cliente_FTP {
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
	
	
    public Cliente_FTP(String log,String clave,String operacion){
        
		direccion = "localhost";
                int puerto = 5972;
                this. log = log;
                this. clave = clave;
                this.operacion = operacion;
		try {
			Socket conexion = new Socket(direccion,puerto);
			InputStream flujoentrada = conexion.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(flujoentrada));
			OutputStream flujosalida = conexion.getOutputStream();
			PrintStream salida = new PrintStream (flujosalida);
			mensaje = entrada.readLine();
                        mensaje = operacion + " " + log + " " + clave;
			salida.println(mensaje);
			salida.flush();
			System.out.println(mensaje);
                        mensaje = entrada.readLine();
			System.out.println(mensaje);
                        
			if(!"Usuario no existente".equals(mensaje)){  // si el usuario si existe ejecutar codigo para transferir archivos
				boolean finalizar = false;
                            
				while(finalizar==false)	{
					comando = System.console().readLine("\nintroduzca un comando: ds, dc, sa, ba, fin, (h para ayuda) -> ");
					switch(comando.toLowerCase()){
					case "ds":						
						System.out.println("\nARCHIVOS DEL SERVIDOR:");
						salida.println(comando);
						salida.flush();
						mensaje = entrada.readLine();
						listar.listar_directorio_servidor(mensaje);
						break;
					case "dc":
						System.out.println("\nARCHIVOS LOCALES:");
						listar.listar_directorio_cliente();
						break;
					
                                        
                                        case "sa":
					System.out.println("\nListo para subir archivo:");
				        salida.println(comando);
				        salida.flush();
						nombre_archivo_subir = System.console().readLine("Seleccione archivo a subir: ");
				        salida.println(nombre_archivo_subir);
				        salida.flush();
				        //new Cliente_FTP().send(conexion, nombre_archivo_subir, salida);
						break;
					
                                        
                                        case "ba":
						System.out.println("\nlisto para recibir archivo");
				        salida.println(comando);
				        salida.flush();
						nombre_archivo_bajar = System.console().readLine("Seleccione archivo a descargar: ");
				        salida.println(nombre_archivo_bajar);
				        salida.flush();
				        mensaje = entrada.readLine();
				        archivo_len = Integer.parseInt(mensaje);
				        System.out.println("tamaño archivo: " + mensaje);
				        //new Cliente_FTP().receiveFile(conexion, archivo_len, nombre_archivo_bajar);
						break;
					case "h":
						System.out.println("\nAyuda para comandos disponibles:\nds: listar directorio de archivos del servidor\ndc: listar directorio de archivos locales\n"
								+ "sa: subir archivo al servidor\nba: bajar archivo desde el servidor\nfin: salir del programa");
						break;
					case "fin":
						System.out.println("\nFinalizando...Hasta luego.");
				        salida.println(comando);
				        salida.flush();
						finalizar = true;
						break;
					default:
						System.out.println("\nSeleccione un comando:");
					}
				} // fin while

			} // fin if
			conexion.close();
		} // fin try
		catch (Exception e) {
		    e.printStackTrace();
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
