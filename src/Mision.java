import java.util.ArrayList;
//Estuardo Antonio Molina 26878
//Clase que administra los modulos y recursos de la mision Quetzal-2
public class Mision {
    //Atributos que guardan los modulos y recursos de la mision
    private ArrayList<Modulo> modulos;
    private double energiaDisponible;
    private double datosOrbita;
    private double datosDescargados;
    private int numeroCiclo;
    //Constructor que inicializa los recursos de la mision
    public Mision(double energiaInicial) {
        //Valida que la energia inicial no sea negativa
        if (energiaInicial < 0) {
            throw new IllegalArgumentException("La energia inicial no puede ser negativa.");
        }
        //Crea la lista polimorfica de modulos
        this.modulos = new ArrayList<Modulo>();
        //Inicializa los recursos y el contador de ciclos
        this.energiaDisponible = energiaInicial;
        this.datosOrbita = 0;
        this.datosDescargados = 0;
        this.numeroCiclo = 0;
    }
    //Metodo que agrega un modulo a la mision
    public boolean agregarModulo(Modulo modulo) {
        //Verifica que el modulo no sea nulo
        if (modulo == null) {
            return false;
        }
        //Busca si ya existe un modulo con el mismo ID
        if (buscarPorId(modulo.getId()) != null) {
            return false;
        }
        //Agrega el modulo al final de la lista para conservar el orden
        modulos.add(modulo);
        return true;
    }
    //Metodo que busca un modulo por su ID
    public Modulo buscarPorId(String id) {
        //Recorre la lista de modulos
        int i = 0;
        while (i < modulos.size()) {
            //Obtiene el modulo actual
            Modulo modulo = modulos.get(i);
            //Compara el ID del modulo
            if (modulo.getId().equals(id)) {
                return modulo;
            }
            i = i + 1;
        }
        //Devuelve null si no encuentra el modulo
        return null;
    }
    //Metodo que busca un modulo por su nombre
    public Modulo buscarPorNombre(String nombre) {
        //Recorre la lista de modulos
        int i = 0;
        while (i < modulos.size()) {
            //Obtiene el modulo actual
            Modulo modulo = modulos.get(i);
            //Compara el nombre del modulo
            if (modulo.getNombre().equals(nombre)) {
                return modulo;
            }
            i = i + 1;
        }
        //Devuelve null si no encuentra el modulo
        return null;
    }
    //Metodo que devuelve los modulos registrados
    public ArrayList<Modulo> getModulos() {
        return new ArrayList<Modulo>(modulos);
    }
    //Metodo que devuelve el catalogo ordenado por costo de construccion
    public ArrayList<Modulo> obtenerCatalogoOrdenado() {
        //Crea una copia para no modificar el orden original
        ArrayList<Modulo> catalogo = new ArrayList<Modulo>(modulos);
        //Ordena los modulos de menor a mayor costo utilizando ciclos
        int i = 0;
        while (i < catalogo.size() - 1) {
            int j = i + 1;
            while (j < catalogo.size()) {
                //Compara los costos de construccion
                if (catalogo.get(i).getCostoConstruccion() > catalogo.get(j).getCostoConstruccion()) {
                    //Intercambia los modulos de posicion
                    Modulo temporal = catalogo.get(i);
                    catalogo.set(i, catalogo.get(j));
                    catalogo.set(j, temporal);
                }
                j = j + 1;
            }
            i = i + 1;
        }
        //Devuelve la copia ordenada
        return catalogo;
    }
    //Metodo que ejecuta un ciclo completo del simulador
    public ArrayList<String> avanzarCiclo() {
        //Crea una lista para guardar los resultados
        ArrayList<String> resultados = new ArrayList<String>();
        //Aumenta el numero de ciclo
        numeroCiclo = numeroCiclo + 1;
        resultados.add("CICLO " + numeroCiclo);
        //Recorre los modulos en el orden original
        int i = 0;
        while (i < modulos.size()) {
            //Obtiene el modulo actual
            Modulo modulo = modulos.get(i);
            //Ejecuta el metodo correspondiente mediante polimorfismo
            String resultado = modulo.procesarCiclo(this);
            //Guarda el resultado de la operacion
            resultados.add(resultado);
            //Guarda el estado de los recursos despues de cada modulo
            resultados.add("Energia disponible: " + energiaDisponible + " | Datos en orbita: " + datosOrbita + " MB | Datos descargados: " + datosDescargados + " MB");
            i = i + 1;
        }
        //Devuelve todos los resultados del ciclo
        return resultados;
    }
    //Metodo que devuelve la energia disponible
    public double getEnergiaDisponible() {
        return energiaDisponible;
    }
    //Metodo que devuelve los datos pendientes en orbita
    public double getDatosOrbita() {
        return datosOrbita;
    }
    //Metodo que devuelve el total historico descargado
    public double getDatosDescargados() {
        return datosDescargados;
    }
    //Metodo que devuelve el numero de ciclos ejecutados
    public int getNumeroCiclo() {
        return numeroCiclo;
    }
    //Metodo que agrega energia a la mision
    public void agregarEnergia(double cantidad) {
        //Evita agregar cantidades negativas
        if (cantidad >= 0) {
            energiaDisponible = energiaDisponible + cantidad;
        }
    }
    //Metodo que consume energia si existe suficiente
    public boolean consumirEnergia(double cantidad) {
        //Verifica que la cantidad sea valida y que exista energia suficiente
        if (cantidad >= 0 && energiaDisponible >= cantidad) {
            energiaDisponible = energiaDisponible - cantidad;
            return true;
        }
        //No modifica los recursos si no hay energia suficiente
        return false;
    }
    //Metodo que agrega datos recolectados a los pendientes en orbita
    public void agregarDatos(double cantidad) {
        //Evita agregar cantidades negativas
        if (cantidad >= 0) {
            datosOrbita = datosOrbita + cantidad;
        }
    }
    //Metodo que descarga datos y actualiza el total historico
    public void descargarDatos(double cantidad) {
        //Verifica que la cantidad sea valida y no supere los datos pendientes
        if (cantidad >= 0 && cantidad <= datosOrbita) {
            //Resta los datos descargados de los pendientes
            datosOrbita = datosOrbita - cantidad;
            //Agrega los datos al total historico
            datosDescargados = datosDescargados + cantidad;
        }
    }
    //Metodo que genera el resumen de comunicaciones
    public ResumenComunicaciones generarResumenComunicaciones() {
        //Crea un resumen nuevo
        ResumenComunicaciones resumen = new ResumenComunicaciones();
        //Recorre todos los modulos de la mision
        int i = 0;
        while (i < modulos.size()) {
            //Solicita al modulo que aporte sus datos mediante polimorfismo
            modulos.get(i).aportarResumenComunicaciones(resumen);
            i = i + 1;
        }
        //Devuelve el resumen completo
        return resumen;
    }
}
