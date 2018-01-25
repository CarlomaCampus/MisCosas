
public class Factura {
    private String id;
    private String producto;
    private double precio;

    
    public Factura(String id, String producto, double precio) {
        this.id=id;
        this.producto=producto;
        this.precio=precio;

    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

}