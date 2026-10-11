//Estuardo Antonio Molina 26878
//Clase que representa un modulo de tierra de la mision Quetzal-2
public class ModuloTierra extends Modulo {
    //Atributos propios del modulo de tierra
    private String estacion;
    private double capacidadDescarga;
    private double energiaDescarga;
    private double totalDescargado;
    //Constructor que inicializa los datos del modulo de tierra
    public ModuloTierra(String id, String nombre, int salud, boolean activo, double costoConstruccion, String estacion, double capacidadDescarga, double energiaDescarga) {
        //Inicializa los atributos heredados de Modulo
        super(id, nombre, salud, activo, costoConstruccion);
        //Valida que la estacion no este vacia
        if (estacion == null || estacion.trim().length() == 0) {
            throw new IllegalArgumentException("La estacion no puede estar vacia.");
        }
        //Valida la capacidad de descarga
        if (capacidadDescarga <= 0) {
            throw new IllegalArgumentException("La capacidad de descarga debe ser mayor que 0.");
        }
        //Valida la energia necesaria para descargar
        if (energiaDescarga < 0) {
            throw new IllegalArgumentException("La energia de descarga no puede ser negativa.");
        }
        //Asigna los valores propios del modulo
        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaDescarga = energiaDescarga;
        this.totalDescargado = 0;
    }
    //Devuelve el nombre de la estacion
    public String getEstacion() {
        return estacion;
    }
    //Devuelve la capacidad maxima de descarga
    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }
    //Devuelve la energia necesaria para descargar
    public double getEnergiaDescarga() {
        return energiaDescarga;
    }
    //Devuelve el total historico descargado
    public double getTotalDescargado() {
        return totalDescargado;
    }
    //Metodo que realiza una descarga durante un ciclo
    @Override
    public String procesarCiclo(Mision mision) {
        //Verifica si el modulo esta inactivo o no tiene salud
        if (getActivo() == false || getSalud() == 0) {
            return "El modulo de tierra " + getNombre() + " no descargo datos porque esta inactivo.";
        }
        //Verifica si existen datos pendientes en orbita
        if (mision.getDatosOrbita() <= 0) {
            return "El modulo de tierra " + getNombre() + " no descargo datos porque no hay datos pendientes en orbita.";
        }
        //Verifica si existe energia suficiente
        if (mision.getEnergiaDisponible() < energiaDescarga) {
            return "El modulo de tierra " + getNombre() + " no descargo datos por energia insuficiente.";
        }
        //Calcula la cantidad de datos que puede descargar
        double datosDescargar = capacidadDescarga;
        //Evita descargar mas datos de los disponibles
        if (datosDescargar > mision.getDatosOrbita()) {
            datosDescargar = mision.getDatosOrbita();
        }
        //Consume la energia completa de la descarga
        mision.consumirEnergia(energiaDescarga);
        //Actualiza los datos pendientes y el total historico de la mision
        mision.descargarDatos(datosDescargar);
        //Actualiza el total historico del modulo
        totalDescargado = totalDescargado + datosDescargar;
        //Devuelve el resultado de la operacion
        return "El modulo de tierra " + getNombre() + " descargo " + datosDescargar + " MB y consumio " + energiaDescarga + " unidades de energia.";
    }
    //Metodo que agrega la informacion al resumen de comunicaciones
    @Override
    public void aportarResumenComunicaciones(ResumenComunicaciones resumen) {
        //Envia el modulo actual para actualizar los datos del reporte
        resumen.agregarModuloTierra(this);
    }
    //Metodo que muestra toda la informacion del modulo
    @Override
    public String toString() {
        return super.toString() + "\nEstacion: " + estacion + "\nCapacidad de descarga: " + capacidadDescarga + " MB" + "\nEnergia por descarga: " + energiaDescarga + "\nTotal descargado: " + totalDescargado + " MB";
    }
}
