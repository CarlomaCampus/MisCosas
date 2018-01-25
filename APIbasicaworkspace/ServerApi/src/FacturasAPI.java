import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


	@Path("/facturas")
	public class FacturasAPI {
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response obtengaFactura(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {
			// if(Authorization.isAuthorized(token)){
			ArrayList<Factura> datos = new ArrayList<Factura>();
			try {
				Connection con = Conexion.createConnection();

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, producto, precio FROM sys.facturas");

				while (rs.next()) {
					datos.add(new Factura(rs.getString(1), rs.getString(2), rs.getDouble(3)));
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
				Response.status(500).build();
			}
			return Response.status(200).entity(new Gson().toJson(datos)).build();
			// }else{
			// return Response.status(Response.Status.UNAUTHORIZED).build();
			// }
		}

		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response obtenerFactura(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String id) {
			Factura facturaABuscar = null;
			// if (Authorization.isAuthorized(token)) {
			try {
				Connection con = Conexion.createConnection();
				PreparedStatement p = con.prepareStatement("SELECT id, producto, precio FROM sys.facturas WHERE id = ?");
				p.setInt(1, Integer.parseInt(id));
				ResultSet rs = p.executeQuery();
				if (rs.next()) {
					facturaABuscar = new Factura(rs.getString(1), rs.getString(2), rs.getDouble(3));
				}
				con.close();
			} catch (Exception e) {
				System.out.println("Error");
			}
			if (facturaABuscar != null) {
				Gson gson = new Gson();
				return Response.status(200).entity(gson.toJson(facturaABuscar)).build();
			} else {
				return Response.status(404).build();
			}
			// } else {
			// return Response.status(Response.Status.UNAUTHORIZED).build();
			// }
		}

		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response anyadirFactura(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {
			// if (Authorization.isAuthorized(token)) {
			Gson gson = new Gson();
			Factura f = gson.fromJson(json, Factura.class);
			try {
				Connection con = Conexion.createConnection();
				PreparedStatement p = con.prepareStatement("INSERT INTO sys.facturas(producto, precio) VALUES (?,?)",
						Statement.RETURN_GENERATED_KEYS);
				p.setString(1, f.getProducto());
				p.setDouble(2, f.getPrecio());
				p.executeUpdate();
				ResultSet rs = p.getGeneratedKeys();
				if (rs.next()) {
					f.setId(String.valueOf(rs.getLong(1)));
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return Response.status(201).entity(gson.toJson(f)).build();
			// } else {
			// return Response.status(Response.Status.UNAUTHORIZED).build();
			// }
		}

		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response actualizarFactura(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String id,
				String json) {
			// if (Authorization.isAuthorized(token)) {
			boolean actualizada = false;
			Factura f = new Gson().fromJson(json, Factura.class);
			try {
				Connection con = Conexion.createConnection();
				PreparedStatement p = con.prepareStatement("UPDATE sys.facturas SET producto = ?, precio = ? WHERE id = ?");
				p.setInt(3, Integer.parseInt(id));
				p.setString(1, f.getProducto());
				p.setDouble(2, f.getPrecio());
				p.executeUpdate();
				if (p.executeUpdate() > 0) {
					actualizada = true;
				}
				con.close();
			} catch (Exception e) {
				System.out.println("Error");
			}
			if (actualizada) {
				f.setId(id);
				return Response.status(201).entity(new Gson().toJson(f)).build();
			} else {
				return Response.status(404).build();
			}
			// } else {
			// return Response.status(Response.Status.UNAUTHORIZED).build();
			// }
		}

		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response borrarFactura(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String id) {
			// if (Authorization.isAuthorized(token)) {
			boolean borrada = false;
			try {
				Connection con = Conexion.createConnection();
				PreparedStatement p = con.prepareStatement("DELETE FROM sys.facturas WHERE id = ?");
				p.setInt(1, Integer.parseInt(id));
				if (p.executeUpdate() > 0) {
					borrada = true;
				}
				con.close();
			} catch (Exception e) {
				System.out.println("Error");
			}
			if (borrada) {
				return Response.status(200).entity("Borrado con éxito").build();
			} else {
				return Response.status(404).build();
			}
			// } else {
			// return Response.status(Response.Status.UNAUTHORIZED).build();
			// }
		}
	}
