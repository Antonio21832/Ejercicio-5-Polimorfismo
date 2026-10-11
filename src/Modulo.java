//Estuardo Antonio Molina 26878
import java.util.Objects;

//Clase padre que representa un modulo de la mision Quetzal-2
public abstract class Modulo {
    //Atributos comunes de todos los modulos
    private String id;
    private String nombre;
    private int salud;
    private boolean activo;
    private double costoConstruccion;
    //Constructor que inicializa los datos comunes
    public Modulo(String id, String nombre, int salud, boolean activo, double costoConstruccion) {
        //Valida que el ID no este vacio
        if (id == null || id.trim().length() == 0) {
            throw new IllegalArgumentException("El ID no puede estar vacio.");
        }
        //Valida el rango de salud
        if (salud < 0 || salud > 100) {
            throw new IllegalArgumentException("La salud debe estar entre 0 y 100.");
        }
        //Valida el costo de construccion
        if (costoConstruccion < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }
        //Valida que el nombre no este vacio
        if (nombre == null || nombre.trim().length() == 0) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
}
        //Asigna los valores recibidos
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.activo = activo;
        this.costoConstruccion = costoConstruccion;
    }
    //Devuelve el ID del modulo
    public String getId() {
        return id;
    }
    //Devuelve el nombre del modulo
    public String getNombre() {
        return nombre;
    }
    //Devuelve el estado de salud
    public int getSalud() {
        return salud;
    }
    //Indica si el modulo esta activo
    public boolean getActivo() {
        return activo;
    }

    //Devuelve el costo de construccion
    public double getCostoConstruccion() {
        return costoConstruccion;
    }
    //Metodo abstracto que cada clase hija debe implementar
    //Recibe la mision para consultar y modificar recursos
    public abstract String procesarCiclo(Mision mision);
    //Metodo que permite aportar informacion al resumen
    //Las clases que no son de tierra no agregan datos
    public void aportarResumenComunicaciones(ResumenComunicaciones resumen) {
    }
    //Devuelve la informacion comun del modulo
    @Override
    public String toString() {
        String estado = "Inactivo";

        if (activo == true && salud > 0) { estado = "Activo";}
        return "ID: " + id + "\nNombre: " + nombre + "\nSalud: " + salud + "\nEstado: " + estado + "\nCosto de construccion: Q" + String.format("%.2f", costoConstruccion);
    }
    //Compara dos modulos utilizando su ID
    @Override
    public boolean equals(Object obj) {
        //Verifica si se compara con el mismo objeto
        if (this == obj) {
            return true;
        }
        //Verifica si el objeto es nulo
        if (obj == null) {
            return false;
        }
        //Verifica que el objeto sea un Modulo
        if (!(obj instanceof Modulo)) {
            return false;
        }
        //Convierte el objeto para comparar los ID
        Modulo otro = (Modulo) obj;

        return id.equals(otro.getId());
    }
    //Genera un codigo hash basado en el ID
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
