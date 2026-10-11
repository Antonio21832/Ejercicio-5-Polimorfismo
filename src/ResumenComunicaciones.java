import java.util.ArrayList;
//Estuardo Antonio Molina 26878
//Clase que almacena la informacion del resumen de comunicaciones
public class ResumenComunicaciones {
    //Atributos que guardan los datos del reporte
    private int totalModulosTierra;
    private int modulosActivos;
    private double capacidadTotal;
    private double totalDescargado;
    private double mayorDescarga;
    private ArrayList<ModuloTierra> modulosMayorDescarga;
    //Constructor que inicializa los datos del resumen
    public ResumenComunicaciones() {
        //Inicializa los contadores y acumuladores
        this.totalModulosTierra = 0;
        this.modulosActivos = 0;
        this.capacidadTotal = 0;
        this.totalDescargado = 0;
        this.mayorDescarga = 0;
        //Crea la lista para guardar los modulos con mayor descarga
        this.modulosMayorDescarga = new ArrayList<ModuloTierra>();
    }
    //Metodo que agrega la informacion de un modulo de tierra al resumen
    public void agregarModuloTierra(ModuloTierra modulo) {
        //Aumenta la cantidad total de modulos de tierra
        totalModulosTierra = totalModulosTierra + 1;
        //Verifica si el modulo esta activo y tiene salud
        if (modulo.getActivo() == true && modulo.getSalud() > 0) {
            //Cuenta el modulo activo
            modulosActivos = modulosActivos + 1;
            //Suma su capacidad de descarga
            capacidadTotal = capacidadTotal + modulo.getCapacidadDescarga();
        }
        //Suma los datos historicos descargados por el modulo
        totalDescargado = totalDescargado + modulo.getTotalDescargado();
        //Verifica si es el primer modulo de tierra registrado
        if (modulosMayorDescarga.size() == 0) {
            //Guarda el primer modulo como el de mayor descarga
            mayorDescarga = modulo.getTotalDescargado();
            modulosMayorDescarga.add(modulo);
        }
        //Verifica si el modulo supera la mayor descarga registrada
        else if (modulo.getTotalDescargado() > mayorDescarga) {
            //Actualiza la mayor cantidad descargada
            mayorDescarga = modulo.getTotalDescargado();
            //Elimina los modulos que tenian un valor menor
            modulosMayorDescarga.clear();
            //Agrega el nuevo modulo con mayor descarga
            modulosMayorDescarga.add(modulo);
        }
        //Verifica si existe un empate en la mayor descarga
        else if (modulo.getTotalDescargado() == mayorDescarga) {
            //Agrega el modulo que comparte la mayor descarga
            modulosMayorDescarga.add(modulo);
        }
    }
    //Devuelve la cantidad total de modulos de tierra
    public int getTotalModulosTierra() {
        return totalModulosTierra;
    }
    //Devuelve la cantidad de modulos de tierra activos
    public int getModulosActivos() {
        return modulosActivos;
    }
    //Devuelve la capacidad total de descarga de los modulos activos
    public double getCapacidadTotal() {
        return capacidadTotal;
    }
    //Devuelve el total historico de datos descargados
    public double getTotalDescargado() {
        return totalDescargado;
    }
    //Devuelve la mayor cantidad historica descargada por un modulo
    public double getMayorDescarga() {
        return mayorDescarga;
    }
    //Devuelve los modulos que comparten la mayor descarga
    public ArrayList<ModuloTierra> getModulosMayorDescarga() {
        return modulosMayorDescarga;
    }
}
