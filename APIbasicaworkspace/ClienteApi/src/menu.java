import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class menu {
	
	static Scanner entrada = new Scanner(System.in);
	

	public static void main(String[] args) {
		
		String opcion;
		
		do {
		System.out.println( "***********MENU PRINCIPAL*************\n"
						  + "1. Ver facturas\n"
						  + "2. Ver factura concreta\n"
						  + "3. Insertar factura\n"
						  + "4. Actualizar factura\n"
						  + "5. Borrar factura\n"
						  + "6. Salir\n"
						  + "**************************************");
		
		
		
		opcion = entrada.nextLine();
		
		switch(opcion) {
		case "1": try { getFacturas(); } catch (IOException e) { e.printStackTrace(); }; break;
		case "2": try { getFactura(); } catch (IOException e) { e.printStackTrace(); }; break;
		case "3": try { postFactura(); } catch (IOException e) { e.printStackTrace(); }; break;
		case "4": try { putFactura(); } catch (IOException e) { e.printStackTrace(); }; break;
		case "5": try { deleteFactura(); } catch (IOException e) { e.printStackTrace(); }; break;
		case "6": break;
		default: System.out.println("Numero invalido");break;
		
		}
		
	
	}while(!opcion.equals("6"));
		
		
	}

		private static void deleteFactura() throws IOException {
			
			String lineaoutput;
			
			System.out.println("Introduce el id que quieras eliminar:");
			HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8888/ServerApi/facturas/"+ entrada.nextLine()).openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Accept", "application/json");
			int codigorespuesta = con.getResponseCode();
			System.out.println("Código de respuesta: "+ codigorespuesta+ " y los datos de retorno son: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while((lineaoutput = br.readLine()) != null) {
				System.out.println(lineaoutput);
			}
			
			con.disconnect();
		
	}

		private static void getFacturas() throws IOException {
		
		String lineaoutput;
		
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8888/ServerApi/facturas").openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		System.out.println("Código de respuesta: "+ con.getResponseCode() + " y los datos de retorno son: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while((lineaoutput = br.readLine()) != null) {
			System.out.println(lineaoutput);
		}
		
		con.disconnect();
		
	}

	private static void getFactura() throws IOException {
		
		String lineaoutput;
		
		System.out.println("Introduce el id que quieras buscar:");
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8888/ServerApi/facturas/"+ entrada.nextLine()).openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		int codigorespuesta = con.getResponseCode();
		System.out.println("Código de respuesta: "+ codigorespuesta+ " y los datos de retorno son: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while((lineaoutput = br.readLine()) != null) {
			System.out.println(lineaoutput);
		}
		
		con.disconnect();
		
	}
	
	private static void postFactura() throws IOException {
		
		String lineaoutput;
		
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8888/ServerApi/facturas").openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		System.out.println("Elige un nombre para el prodcucto y seguidamente indica su precio: ");
		OutputStream os = con.getOutputStream();
		os.write(("{\"producto\":\""+entrada.nextLine()+"\",\"precio\":"+entrada.nextLine()+"}").getBytes());
		os.flush();
		
		System.out.println("Código de respuesta: "+ con.getResponseCode() + " y los datos de retorno son: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while((lineaoutput = br.readLine()) != null) {
			System.out.println(lineaoutput);
		}
		
		con.disconnect();
		
		
	}
	
	
	private static void putFactura() throws IOException {
		
		String lineaoutput;
		
		System.out.println("Introduce el id del producto que quieres actualizar");
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8888/ServerApi/facturas/"+entrada.nextLine()).openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/json");
		System.out.println("Introduce un nuevo nombre para el prodcucto y seguidamente su nuevo precio: ");
		OutputStream os = con.getOutputStream();
		os.write(("{\"producto\":\""+entrada.nextLine()+"\",\"precio\":"+entrada.nextLine()+"}").getBytes());
		os.flush();
		
		System.out.println("Código de respuesta: "+ con.getResponseCode() + " y los datos de retorno son: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while((lineaoutput = br.readLine()) != null) {
			System.out.println(lineaoutput);
		}
		
		con.disconnect();
		
		
		
	}



}
