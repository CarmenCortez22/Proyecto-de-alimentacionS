
package proyectodealimentacions.entidadesdenegocio;

public class ControlPublicacion {
    private int id;
    private String nombre;
    private String Descripcion;
    private int top_aux;

    public ControlPublicacion() {
    }

    public ControlPublicacion(int id, String nombre, String Descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
}
